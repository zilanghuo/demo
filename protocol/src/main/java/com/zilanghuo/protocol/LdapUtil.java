package com.zilanghuo.protocol;

import cn.hutool.core.codec.Base64;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

/**
 * Created by laiwufa on 2019-10-29
 */
public class LdapUtil {

    /**
     * eg:onnectLDAP("192.168.1.242", "389", "test", "12345","dc=times,dc=home")
     */
    public static DirContext connectLDAP(String ip, String port,
                                         String root_user, String root_password, String root) {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://" + ip + ":" + port + "/" + root);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=" + root_user + "," + root + "");
        env.put(Context.SECURITY_CREDENTIALS, root_password);
        DirContext ctx = null;
        try {
            // 链接 ldap
            ctx = new InitialDirContext(env);
            System.out.println("root 认证成功");
            return ctx;
        } catch (javax.naming.AuthenticationException e) {
            System.out.println("root 认证失败");
            try {
                if (ctx != null) {
                    ctx.close();
                }
            } catch (NamingException namingException) {
                namingException.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("root 认证出错：");
            try {
                if (ctx != null) {
                    ctx.close();
                }
            } catch (NamingException namingException) {
                namingException.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param usr
     * @param pwd
     * @param ctx
     * @return 1：密码不正确，-1：验证程序错误
     * @throws NoSuchAlgorithmException
     */
    public static String checkUser(String usr, String pwd, DirContext ctx) {
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            @SuppressWarnings("rawtypes")
            NamingEnumeration en = ctx.search("", "uid=" + usr + "",constraints); // 查询所有用户
            while (en != null && en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj instanceof SearchResult) {
                    SearchResult si = (SearchResult) obj;
                    Attributes attrs = si.getAttributes();
                    if (attrs == null) {
                        System.out.println("No   attributes");
                    } else {
                        Attribute attr = attrs.get("userPassword");
                        Object o = attr.get();
                        byte[] s = (byte[]) o;
                        String pwd2 = new String(s);
                        // boolean success = LdapUtil.verifySHA(pwd2, pwd);
                        boolean success = pwd.equals(pwd2);
                        if (success) {
                            attr = attrs.get("cn");
                            System.out.println("name:" + usr + "验证成功！");
                            return attr.toString().split(" ")[1];
                        } else {
                            System.out.println("name:" + usr + "密码错误！");
                            return "1"; // 密码不正确
                        }
                    }
                } else {
                    System.out.println(obj);
                }
            }
            System.out.println("无此用户 :" + usr + "");
            ctx.close();
        } catch (NamingException ex) {
            try {
                if (ctx != null) {
                    ctx.close();
                }
            } catch (NamingException namingException) {
                namingException.printStackTrace();
            }
        }
        return "-1";
    }

    public static void main(String[] args) throws Exception {
        String s = checkUser(
                "test04",
                "123456",
                connectLDAP("10.0.68.236", "389", "Manager", "123456", "dc=yonghui_test,dc=cn"));
        System.out.println(s);

    }

}
