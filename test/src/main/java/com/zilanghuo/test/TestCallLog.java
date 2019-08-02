package com.zilanghuo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description : 模拟优化逻辑
 * @Author :      laiwufa
 * @Package :     com.zilanghuo.test
 * @Date :        2019/7/2 17:17
 */
public class TestCallLog {

    public static void main(String[] args) {

        // 1、根据sql，获取到记录
        List<ResultDto> resultDtoList = initResult();
        // 2、根据callId 获取到 mobile
        List<CallLog> callList = initCallLog(resultDtoList);
        // 3、根据mobileId 获取到 学生信息
        List<Stu> stuList = initStu(callList);
        Map<String, String> map = new HashMap();
        for (CallLog log : callList) {
            for (Stu stu : stuList) {
                if (log.mobile.equals(stu.mobile)) {
                    map.put(log.callId + "", stu.stuName);
                }
            }
        }
        // 计算，resultList如何与stuList 关联
        for (ResultDto resultDto : resultDtoList) {
            int callId = resultDto.callId;
            resultDto.stuName = map.get(callId + "");
        }
        // 打印
        resultDtoList.stream().forEach(resultDto -> System.out.println(resultDto.toString()));

    }

    /**
     * 初始化学生信息
     *
     * @param callList
     * @return
     */
    private static List<Stu> initStu(List<CallLog> callList) {
        List<Stu> stuList = new ArrayList();
        callList.stream().forEach(callLog -> {
            Stu stu = new Stu();
            stu.mobile = callLog.mobile;
            stu.stuName = "李红" + callLog.mobile;
            stuList.add(stu);
        });
        return stuList;
    }

    /**
     * 初始化log 集
     *
     * @param resultDtoList
     * @return
     */
    private static List<CallLog> initCallLog(List<ResultDto> resultDtoList) {
        List<CallLog> callLogList = new ArrayList();
        resultDtoList.stream().forEach(resultDto -> {
            CallLog log = new CallLog();
            log.callId = resultDto.callId;
            log.mobile = "0001" + resultDto.callId;
            callLogList.add(log);
        });
        return callLogList;
    }

    /**
     * 初始化结果集
     *
     * @return
     */
    private static List<ResultDto> initResult() {
        List<ResultDto> resultDtoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ResultDto resultDto = new ResultDto();
            resultDto.callId = i;
            resultDtoList.add(resultDto);
        }
        return resultDtoList;
    }

}


class ResultDto {

    public int callId;

    public String stuName;

    @Override
    public String toString() {
        return callId + ":" + stuName;
    }
}

class CallLog {

    public int callId;

    public String mobile;
}

class Stu {

    public String stuName;

    public String mobile;
}