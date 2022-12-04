package com.zilanghuo.canal.ssh;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * SSH连接配置
 */
@AllArgsConstructor
@Getter
public class ConnectionSetting {

    /**
     * 主机
     */
    private  String host;

    /**
     * 端口
     */
    private  int port;

    /**
     * 用户名
     */
    private  String username;

    /**
     * 密码
     */
    @Setter
    public   String password;

    /**
     * 超时时间，0表示阻塞直到连接，大于零表示毫秒单位的超时时间
     */
    @Setter
    private int connectTimeout;

    public ConnectionSetting(String host, int port, String username, String password) {
        this(host, port, username, password, 0);
    }

    public ConnectionSetting(String host, int port, String username) {
        this(host, port, username, null);
    }

    public ConnectionSetting(String host, int port) {
        this(host, port, System.getProperty("user.name"));
    }

    public ConnectionSetting(String host) {
        this(host, 22);
    }

    public ConnectionSetting() {
        this("localhost");
    }
}
