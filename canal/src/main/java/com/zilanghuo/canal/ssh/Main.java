package com.zilanghuo.canal.ssh;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by laiwufa on 2022-06-29
 */
public class Main {

    public static void main(String[] args) throws Exception{
       //restartcaishi();

        //agent199();
       //agent198();
       //agent187();
       // agent73();
       // agent92();
       //wms73();
       //wms92();
       //one92();
       //two73();
        //agent25();
        //agent22();
        //agent36();
        //agent10();
        //agent29();
        //start3312();
        //  ycbi_wms_shard02_009
        //System.out.println(catInstance92());
        //stopycbi_wms_shard01_008_29();
        //stopycbi_wms_shard01_008_10();
        //stopycbi_wms_shard01_008_29Pid();
        System.out.println(decrypt("lJtJDY8RXVeRhV6TvGPYfF7rp314Im2dmuSxgS0MWRfGvuU4M+rBm7HJmiEuLSRNLME3xx68i+wzAOCFoBkAPA=="));
    }


    public static String decrypt(String ciphertext) throws Exception{
        java.security.Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
        Cipher decryptCipher = Cipher.getInstance("RSA");
        ClassPathResource resource = new ClassPathResource("rsa_private.key");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] privateKey = IOUtils.toByteArray(resource.getInputStream());
        KeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        Key key = keyFactory.generatePrivate(privateKeySpec);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherByte = Base64.decodeBase64(ciphertext);
        byte[] content = decryptCipher.doFinal(cipherByte);
        return new String(content, StandardCharsets.UTF_8);
    }

    public static String catInstance92() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.128.92", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("dHeCkoTVUDyDUng6Z/eM07jNVj8idmCvqq7KJnVOLJFpeev1oQrWzFfUKr1tk9miM6xQ2K+acjXkg1LLC2wjTw=="));
        SshSession sshSession = new SshSession(setting);

        String exec = sshSession.exec("source /etc/profile; cat /home/cdcx/cdcx/canal/wms_02_canal_new/conf/rep_wms_02_canal_new/instance.properties");

        return exec;
    }

    public static void wms92() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.128.92", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("dHeCkoTVUDyDUng6Z/eM07jNVj8idmCvqq7KJnVOLJFpeev1oQrWzFfUKr1tk9miM6xQ2K+acjXkg1LLC2wjTw=="));
        SshSession sshSession = new SshSession(setting);

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/wms_02_canal_new/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/wms_02_canal_new/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/wms_01_canal_new_ha/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/wms_01_canal_new_ha/bin/startup.sh");

        sshSession.close();
    }

    public static void wms73() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.128.73", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("iU7RPOf+dem8BO8c///4KXCmm07jM2+ry6BQZ4JTeBfLNIIQy6gtbts76oG2n9Kt7gXNuD2yuv7mv/Gk7e41jw=="));
        SshSession sshSession = new SshSession(setting);

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/wms_02_canal_new_ha/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/wms_02_canal_new_ha/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/wms_01_canal_new/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/wms_01_canal_new/bin/startup.sh");

        sshSession.close();
    }


    public static void one92() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.128.92", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("dHeCkoTVUDyDUng6Z/eM07jNVj8idmCvqq7KJnVOLJFpeev1oQrWzFfUKr1tk9miM6xQ2K+acjXkg1LLC2wjTw=="));
        SshSession sshSession = new SshSession(setting);

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_1_2_canal_HA/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_1_2_canal_HA/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/ms_order_db1_3_4_canal_HA/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/ms_order_db1_3_4_canal_HA/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_5_6_canal/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_5_6_canal/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_7_8_canal/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_7_8_canal/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_1_2_canal/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_1_2_canal/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_3_4_canal/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_3_4_canal/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_5_6_canal/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_5_6_canal/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_7_8_canal/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_7_8_canal/bin/startup.sh");

        sshSession.close();
    }

    public static void two73() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.128.73", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("iU7RPOf+dem8BO8c///4KXCmm07jM2+ry6BQZ4JTeBfLNIIQy6gtbts76oG2n9Kt7gXNuD2yuv7mv/Gk7e41jw=="));
        SshSession sshSession = new SshSession(setting);

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_7_8_canal_HA/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_7_8_canal_HA/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_5_6_canal_HA/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_5_6_canal_HA/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_3_4_canal_HA/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_3_4_canal_HA/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_1_2_canal_HA/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db2_1_2_canal_HA/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_7_8_canal_HA/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_7_8_canal_HA/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_5_6_canal_HA/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_5_6_canal_HA/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/ms_order_db1_3_4_canal/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/ms_order_db1_3_4_canal/bin/startup.sh");

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_1_2_canal/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/oms_order_db1_1_2_canal/bin/startup.sh");

        sshSession.close();
    }


    public static void agent198() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.0.74.198", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("xba+aum39Qc4L8Oub0BHVNroxz1u5fwnHuv0cbylLp0DN3V7ALPBVLrDteepQ/+Y4R2/iGTpeAeBf5hULMZ+Hg=="));
        SshSession sshSession = new SshSession(setting);
        String exec = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        Thread.sleep(1000);
        String exec1 = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void agent199() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.0.74.199", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("lJtJDY8RXVeRhV6TvGPYfF7rp314Im2dmuSxgS0MWRfGvuU4M+rBm7HJmiEuLSRNLME3xx68i+wzAOCFoBkAPA=="));
        SshSession sshSession = new SshSession(setting);
        String exec = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        Thread.sleep(1000);
        String exec1 = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void agent187() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.0.76.187", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("poXwHw730wTM7P68oY3d758qlBK6fg1tmo+Flax9gfouTEOa4DgjpDA1cYsmSJzDCyQvjRxuqUrcFgYkBp4tNw=="));
        SshSession sshSession = new SshSession(setting);
        String exec = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        System.out.println(exec);
        Thread.sleep(1000);
        String exec1 = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        System.out.println(exec1);
        sshSession.close();
    }

    public static void agent73() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.128.73", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("iU7RPOf+dem8BO8c///4KXCmm07jM2+ry6BQZ4JTeBfLNIIQy6gtbts76oG2n9Kt7gXNuD2yuv7mv/Gk7e41jw=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        Thread.sleep(1000);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void agent92() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.128.92", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("dHeCkoTVUDyDUng6Z/eM07jNVj8idmCvqq7KJnVOLJFpeev1oQrWzFfUKr1tk9miM6xQ2K+acjXkg1LLC2wjTw=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        Thread.sleep(1000);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void agent21() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.21", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void agent25() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.25", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void agent22() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.22", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }


    public static void agent36() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.36", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void agent10() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.10", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void agent29() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.29", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/agentx/bin/start.sh");
        sshSession.close();
    }

    public static void start3312() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.128.92", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("dHeCkoTVUDyDUng6Z/eM07jNVj8idmCvqq7KJnVOLJFpeev1oQrWzFfUKr1tk9miM6xQ2K+acjXkg1LLC2wjTw=="));
        SshSession sshSession = new SshSession(setting);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/OTB_shard_3312/bin/stop.sh");
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/OTB_shard_3312/bin/startup.sh");
        sshSession.close();
    }


    public static void stopycbi_wms_shard01_008_29() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.29", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        String exec = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/ycbi_wms_shard01_008/bin/stop.sh");
        System.out.println(exec);
        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/ycbi_wms_shard01_008/bin/startup.sh");
        sshSession.close();
    }

    public static void stopycbi_wms_shard01_008_10() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.10", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        String exec = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/ycbi_wms_shard01_008_HA/bin/stop.sh");
        String exec1 = sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/ycbi_wms_shard01_008_HA/bin/startup.sh");
        System.out.println(exec);
        System.out.println(exec1);
        sshSession.close();
    }

    public static void stopycbi_wms_shard01_008_29Pid() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.251.129.29", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("L6RfvA8zxeba6+7iPfrNjunfNC88f+NB9kfdCTlH8txSYoaHbAk61AvdBZdrnW+TUzOLTq2mjrMPnJSRp1MVDQ=="));
        SshSession sshSession = new SshSession(setting);
        String exec = sshSession.exec("source /etc/profile; ps -ef|grep ycbi_wms_shard01_008");
        System.out.println(exec);
        sshSession.close();
    }


    public static void restartcaishi() throws Exception{
        ConnectionSetting setting = new ConnectionSetting("10.0.76.187", 22, "cdcx");
        setting.setConnectTimeout(300000);
        setting.setPassword(decrypt("poXwHw730wTM7P68oY3d758qlBK6fg1tmo+Flax9gfouTEOa4DgjpDA1cYsmSJzDCyQvjRxuqUrcFgYkBp4tNw=="));
        System.out.println(setting.getPassword());
        SshSession sshSession = new SshSession(setting);
        String exec = sshSession.exec("source /etc/profile; cat /home/cdcx/cdcx/canal/test_read_canal/conf/rep_test_read_canal/instance.properties");
        System.out.println(exec);

        sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/test_read_canal/bin/stop.sh");



        //sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/test_read_canal/bin/stop.sh");
        //sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/test_read_canal/bin/stop.sh");
        //sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/test_read_canal/bin/stop.sh");
        //sshSession.exec("source /etc/profile; sh /home/cdcx/cdcx/canal/test_read_canal/bin/startup.sh");

        sshSession.close();
    }


}
