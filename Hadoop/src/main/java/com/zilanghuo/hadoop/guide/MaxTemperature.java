package com.zilanghuo.hadoop.guide;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;

/**
 * Created by laiwufa on 2019-10-30
 */
public class MaxTemperature {

    public static void main(String[] args) throws Exception {
        if (args.length != 2){
            System.err.println("ddd");
            System.exit(-1);
        }
        JobConf job = new JobConf();
        job.setJarByClass(MaxTemperature.class);
        job.setJobName("max temperature");
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
       // System.exit(job.wait()?0:1);
    }
}
