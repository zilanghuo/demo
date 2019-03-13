package com.zilanghuo.java8.io.excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zilanghuo.java8.io.json.DataResult;
import com.zilanghuo.java8.io.json.ServiceResult;
import com.zilanghuo.java8.io.json.TotalResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laiwufa
 * @date 2019/3/4 0004 上午 10:03
 */
public class Java2ExcelConvert {

    public static void writeWithoutHead(DataResult result) throws IOException {
        try (OutputStream out = new FileOutputStream("E:\\2007.xls");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS, false);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("20190304-20190310");
            List<List<Object>> data = new ArrayList<>();
            TotalResult totalResult = result.getData();
            List<ServiceResult> slowServiceList = totalResult.getGetTopNSlowService();
            for (int i = 0; i < slowServiceList.size(); i++) {
                List<Object> item = new ArrayList<>();
                if (i == 0) {
                    List<Object> headItem = new ArrayList<>();
                    headItem.add("系统名");
                    headItem.add("服务名");
                    headItem.add("耗时(ms)");
                    data.add(headItem);
                }
                item.add(slowServiceList.get(i).getService().getApplicationName());
                item.add(slowServiceList.get(i).getService().getLabel());
                item.add(slowServiceList.get(i).getValue() + "");
                data.add(item);
            }
            writer.write1(data, sheet1);
            writer.finish();
        }
    }
}
