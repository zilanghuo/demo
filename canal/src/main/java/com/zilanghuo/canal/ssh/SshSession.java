package com.zilanghuo.canal.ssh;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BooleanSupplier;

/**
 * SSH会话
 */
public class SshSession implements Closeable {

    private final ConnectionSetting connectionSetting;

    private Session session;

    public SshSession(ConnectionSetting connectionSetting) {
        this.connectionSetting = connectionSetting;
    }

    private Session getSession() throws JSchException {
        if (session == null) {
            JSch jsch = new JSch();
            session = jsch.getSession(
                    connectionSetting.getUsername(),
                    connectionSetting.getHost(),
                    connectionSetting.getPort()
            );
            session.setUserInfo(new FixedPasswordUserInfo(connectionSetting.getPassword()));
            session.connect(connectionSetting.getConnectTimeout());
        }
        return session;
    }

    private ChannelExec getExecChannel() throws JSchException {
        return (ChannelExec) getSession().openChannel("exec");
    }

    /**
     * 执行脚本，并返回脚本输出的内容
     * @param command 脚本
     * @return 脚本输出的内容。包含stdout和stderr
     * @throws IOException 执行期间出现异常将抛出
     */
    public String exec(String command) throws IOException {
        ChannelExec channelExec = null;
        try {
            channelExec = getExecChannel();
            channelExec.setCommand(command);
            channelExec.setInputStream(null);
            try (
                InputStream in = channelExec.getInputStream();
                InputStream err = channelExec.getErrStream();
            ) {
                channelExec.connect(connectionSetting.getConnectTimeout());
                BooleanSupplier checkClosed = channelExec::isClosed;
                String inContent = readContent(in, checkClosed);
                String errContent = readContent(err, checkClosed);
                StringBuilder contentBuilder = new StringBuilder(inContent);
                if (!errContent.isEmpty()) {
                    if (!inContent.isEmpty()) {
                        contentBuilder.append('\n');
                    }
                    contentBuilder.append(errContent);
                }
                return contentBuilder.toString();
            }
        } catch (JSchException e) {
            throw new IOException(e.getMessage(), e.getCause());
        } finally {
            if (channelExec != null) {
                channelExec.disconnect();
            }
        }
    }

    /**
     * 执行脚本，并将脚本的输入输出重定向至指定的输出流
     * @param command 脚本
     * @param redirectOut 重定向的标准输出流
     * @param redirectErr 重定向的错误输出流
     * @throws IOException 执行期间出现异常将抛出
     */
    public void exec(String command, OutputStream redirectOut, OutputStream redirectErr) throws IOException {
        ChannelExec channelExec = null;
        try {
            channelExec = getExecChannel();
            channelExec.setCommand(command);
            channelExec.setOutputStream(redirectOut, true);
            channelExec.setErrStream(redirectErr, true);
            channelExec.connect(connectionSetting.getConnectTimeout());
            while (!channelExec.isClosed());
        } catch (JSchException e) {
            throw new IOException(e.getMessage(), e.getCause());
        } finally {
            if (channelExec != null) {
                channelExec.disconnect();
            }
        }
    }

    /**
     * 执行脚本，并将脚本的输入输出重定向至指定的输出流
     * @param command 脚本
     * @param redirectOutAndErr 重定向的标准输出流和错误输出流
     * @throws IOException 执行期间出现异常将抛出
     */
    public void exec(String command, OutputStream redirectOutAndErr) throws IOException {
        exec(command, redirectOutAndErr, redirectOutAndErr);
    }

    /**
     * 将输入流传输到远程服务器上指定的文件
     * @param inputStream 输入流
     * @param bytesLength 字节流长度
     * @param remoteFile 远程文件绝对路径
     * @throws IOException 传输期间出现异常
     */
    public void scpTo(InputStream inputStream, long bytesLength, String remoteFile) throws IOException {
        ChannelExec channelExec = null;
        try {
            channelExec = getExecChannel();
            channelExec.setCommand("scp -t '" + remoteFile + "'");
            try (InputStream in = channelExec.getInputStream();
                 OutputStream out = channelExec.getOutputStream()
            ) {
                channelExec.connect(connectionSetting.getConnectTimeout());
                checkScpAck(in);

                String command = String.format("C0644 %d %s\n", bytesLength, getFileName(remoteFile));
                out.write(command.getBytes());
                out.flush();

                checkScpAck(in);

                IOUtils.copy(inputStream, out);
                out.write(0);
                out.flush();

                checkScpAck(in);
            }
        } catch (JSchException e) {
            throw new IOException(e.getMessage(), e.getCause());
        } finally {
            if (channelExec != null) {
                channelExec.disconnect();
            }
        }
    }

    /**
     * 将本地文件通过scp的方式传输到远程服务器
     * @param localFile 本地文件
     * @param remoteFile 远程服务器保存的位置
     * @throws IOException 传输期间出现异常
     */
    public void scpTo(String localFile, String remoteFile) throws IOException {
        Path localFilePath = Paths.get(localFile);
        if (!Files.exists(localFilePath)) {
            throw new FileNotFoundException(localFile);
        }
        if (!Files.isRegularFile(localFilePath)) {
            throw new UnsupportedOperationException();
        }
        InputStream in = new BufferedInputStream(Files.newInputStream(localFilePath));
        long fileSize = Files.size(localFilePath);
        scpTo(in, fileSize, remoteFile);
    }

    private void checkScpAck(InputStream in) throws IOException {
        ScpAck scpAck = ScpAck.valueOf(in.read());
        if (scpAck == ScpAck.OK) {
            return;
        }
        StringBuilder messageBuilder = new StringBuilder();
        int ch;
        while ((ch = in.read()) != '\n') {
            messageBuilder.append((char) ch);
        }
        throw new IllegalStateException(scpAck.name() + ": " + messageBuilder.toString());
    }

    private String readContent(InputStream in, BooleanSupplier checkClosed) throws IOException {
        byte[] buffer = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();
        do {
            int len;
            while (in.available() > 0 && (len = in.read(buffer, 0, buffer.length)) != -1) {
                stringBuilder.append(new String(buffer, 0, len));
            }
        } while (!checkClosed.getAsBoolean() || in.available() > 0);
        return stringBuilder.toString();
    }

    private String getFileName(String path) {
        return Paths.get(path).getFileName().toString();
    }

    @Override
    public void close() {
        if (session != null) {
            session.disconnect();
        }
    }

    public boolean isConnected() throws JSchException {
        return getSession().isConnected();
    }

    private enum ScpAck {
        OK,   //ordinal=0
        WARN, //ordinal=1
        ERROR //ordinal=2
        ;
        static ScpAck valueOf(int code) {
            ScpAck[] codes = values();
            if (code < 0 || code >= codes.length) {
                throw new IllegalArgumentException("No such ack code");
            }
            return codes[code];
        }
    }

    /**
     * 固定密码
     */
    private static class FixedPasswordUserInfo implements UserInfo {

        private final String password;

        public FixedPasswordUserInfo(String password) {
            this.password = password;
        }

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean promptPassword(String message) {
            return true;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return true;
        }

        @Override
        public boolean promptYesNo(String message) {
            return true;
        }

        @Override
        public void showMessage(String message) {

        }
    }
}
