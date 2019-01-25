package com.zilanghuo.web.utils;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author laiwufa
 * @date 2019/1/25
 */
public class TraceThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        Executor executor = getThreadPoolExecutor();

        LcbTraceRunnable lcbTraceRunnable = new LcbTraceRunnable() {
            @Override
            public void concreteRun() {
                task.run();
            }
        };

        try {
            executor.execute(lcbTraceRunnable);
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }
}
