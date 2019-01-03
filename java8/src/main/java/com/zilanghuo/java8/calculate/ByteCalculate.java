package com.zilanghuo.java8.calculate;

import com.zilanghuo.java8.calculate.dto.DotApplicationListReqDto;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author laiwufa
 * @date 2019/1/3
 * use: 计算字节大小
 */
public class ByteCalculate {

    public static int byteSize(Object object) throws Exception {
        ByteArrayOutputStream buf = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(buf);
        out.writeObject(object);
        out.flush();
        buf.close();
        return buf.size();
    }

    public static void main(String[] args) throws Exception {
        DotApplicationListReqDto reqDto = new DotApplicationListReqDto();
        reqDto.setMac("14:9D:09:EA:7E:5A");
        reqDto.setImei("865216036212310");
        reqDto.setTriggerTime(new Date());
        reqDto.setUserId("207291");
        List<DotApplicationListReqDto.AppInfo> appInfoList = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            DotApplicationListReqDto.AppInfo one = new DotApplicationListReqDto.AppInfo();
            one.setAppName("滴滴出行");
            one.setFirstInstallTime(System.currentTimeMillis());
            one.setLastUpdateTime(System.currentTimeMillis());
            one.setPackageName("com.sdu.didi.psnge");
            one.setVersionName("5.2.30");
            appInfoList.add(one);
        }
        reqDto.setAppInfoList(appInfoList);
        System.out.println(ByteCalculate.byteSize(reqDto));
    }

}
