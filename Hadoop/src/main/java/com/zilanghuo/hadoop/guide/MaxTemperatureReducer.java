package com.zilanghuo.hadoop.guide;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;


/**
 * Created by laiwufa on 2019-10-30
 */
public class MaxTemperatureReducer implements Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    public void reduce(Text text, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        int max = Integer.MAX_VALUE;
        for (Iterator<IntWritable> it = values; it.hasNext(); ) {
            IntWritable value = it.next();
            max = Math.max(max,value.get());
        }
        outputCollector.collect(text,new IntWritable(max));

    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf jobConf) {

    }
}
