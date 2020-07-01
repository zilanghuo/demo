package com.zilanghuo.test.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.junit.Test;

import java.io.*;
import java.net.URI;

/**
 * Created by laiwufa on 2020-06-29
 */
public class HdfsClientUtil {

    private static final String HDFS_PATH = "hdfs://hadoop02-dev.bigdata.cn:8020";

    private static final String HDFS_USER = "admin";

    private static final String PREFIX_PATH = "/user/admin/silkworm-work/logs/10.0.90.57/2020-06-18";

    private static FileSystem fileSystem;

    static {
        Configuration configuration = new Configuration();
        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
             fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, HDFS_USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDir() throws IOException {
        Path path = new Path(PREFIX_PATH);
        fileSystem.mkdirs(path, FsPermission.getDefault());
    }

    @Test
    public void deleteFile() throws IOException {
        Path path = new Path(PREFIX_PATH);
        fileSystem.delete(path, true);
    }

    /**
     * 创建文件
     *
     * @throws IOException
     */
    @Test
    public void testHdfsCreateFile() throws IOException {
        Path path = new Path(PREFIX_PATH+"/hello.txt");
        FSDataOutputStream fos = fileSystem.create(path);
        fos.write("你好".getBytes());
        fos.close();
    }

    @Test
    public void uploadFile() throws Exception{

    }

    @Test
    public void read() throws Exception{
        // FSDataInputStream inputStream = fileSystem.open(new Path(PREFIX_PATH+"/gen_parameteer_rec.ktr"));
        FSDataInputStream inputStream = fileSystem.open(new Path("/user/admin/silkworm-work/logs/10.0.90.57/2020-06-18/121635_task-param1_2020-06-18.log"));
        String context = inputStreamToString(inputStream, "utf-8");
        System.out.println(context);

    }

    @Test
    public void isFile() throws Exception{
        boolean file = fileSystem.isFile(new Path(PREFIX_PATH+"/121635_task-param1_2020-06-18.stdout"));
        System.out.println(file);
    }

    @Test
    public void copyFromLocalFile() throws Exception {
        // 如果指定的是目录，则会把目录及其中的文件都复制到指定目录下
        Path src = new Path("/Users/admin/Documents/121635_task-param1_2020-06-18.error");
        Path dst = new Path(PREFIX_PATH);
        fileSystem.copyFromLocalFile(src, dst);
    }

    @Test
    public void copyToLocalFile() throws Exception {
        Path src = new Path("/user/admin/gen_parameteer_rec.ktr");
        Path dst = new Path("/Users/admin/Desktop");
        /*
         * 第一个参数控制下载完成后是否删除源文件,默认是 true,即删除;
         * 最后一个参数表示是否将 RawLocalFileSystem 用作本地文件系统;
         * RawLocalFileSystem 默认为 false,通常情况下可以不设置,
         * 但如果你在执行时候抛出 NullPointerException 异常,则代表你的文件系统与程序可能存在不兼容的情况 (window 下常见),
         * 此时可以将 RawLocalFileSystem 设置为 true
         */
        fileSystem.copyToLocalFile(false, src, dst, true);
    }

    @Test
    public void ls() throws Exception {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(PREFIX_PATH));
        for (int i = 0; i < fileStatuses.length; i++) {
            System.out.println(fileStatuses[i].getPath().getName());
        }
    }

    /**
     * 把输入流转换为指定编码的字符
     *
     * @param inputStream 输入流
     * @param encode      指定编码类型
     */
    private static String inputStreamToString(InputStream inputStream, String encode) {
        try {
            if (encode == null || ("".equals(encode))) {
                encode = "utf-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encode));
            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                builder.append(str).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
