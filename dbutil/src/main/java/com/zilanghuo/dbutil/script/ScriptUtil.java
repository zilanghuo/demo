package com.zilanghuo.dbutil.script;

import com.alibaba.fastjson.JSON;
import com.zilanghuo.dbutil.DatabaseUtil;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by laiwufa on 2021-10-29
 */
public class ScriptUtil {

    public static void main(String[] args) throws Exception{

        List<Map<String, Object>> result = DatabaseUtil.sql("SELECT * FROM script_version where script_type = 'hive'");

        List<String> sqlList = new ArrayList<>();
        for (Map<String,Object> map : result){
            try {
                String object = map.get("script_text").toString();
                if (null == object || "".equals(object)){
                    continue;
                }
                SimpleDto simpleDto = JSON.parseObject(object, SimpleDto.class);
                String script = simpleDto.getScript();
                if (CollectionUtils.isNotEmpty(simpleDto.getScriptParameters())){
                    for (SubParam subParam : simpleDto.getScriptParameters()){
                        String key = "${"+subParam.getName()+"}";
                        String value = subParam.getValue();
                        if (subParam.getType().equals("time")){
                            Date now = new Date();
                            SimpleDateFormat format = new SimpleDateFormat(subParam.getDateFormat());
                            value = format.format(now);
                        }
                        script = script.replace(key,value);
                    }
                }
                String sql ="insert INTO test (script) VALUES(\""+script+"\")";
                sqlList.add(sql);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        DatabaseUtil.exeuteSql(sqlList);
    }
}
