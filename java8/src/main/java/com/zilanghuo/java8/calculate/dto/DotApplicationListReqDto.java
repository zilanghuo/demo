package com.zilanghuo.java8.calculate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author laiwufa
 * @date 2018/12/5
 * use: 收集应用列表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DotApplicationListReqDto implements Serializable {

    private static final long serialVersionUID = 9097913828828070626L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * mac 地址
     */
    private String mac;

    /**
     * imei
     */
    private String imei;

    /**
     * 触发时间
     */
    private Date triggerTime;

    /**
     * 应用列表
     */
    private List<AppInfo> appInfoList = new ArrayList();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public List<AppInfo> getAppInfoList() {
        return appInfoList;
    }

    public void setAppInfoList(List<AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppInfo implements Serializable{

        private static final long serialVersionUID = 5722292723483194958L;

        /**
         * app 名字
         */
        private String appName;

        /**
         * 包名
         */
        private String packageName;

        /**
         * 版本号
         */
        private String versionName;

        /**
         * 第一次安装时间
         */
        private Long firstInstallTime;

        /**
         * 最后更新时间
         */
        private Long lastUpdateTime;

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public Long getFirstInstallTime() {
            return firstInstallTime;
        }

        public void setFirstInstallTime(Long firstInstallTime) {
            this.firstInstallTime = firstInstallTime;
        }

        public Long getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(Long lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }
    }


}
