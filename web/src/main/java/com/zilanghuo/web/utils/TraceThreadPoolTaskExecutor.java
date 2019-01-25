package com.zilanghuo.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author laiwufa
 * @date 2019/1/25
 */
@Slf4j
public class TraceThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        ExecutorService executor = getThreadPoolExecutor();
        TraceCallable traceCallable = new TraceCallable() {
            @Override
            public Object concreteCall() throws Exception {
                return task.call();
            }
        };
        try {
            return executor.submit(traceCallable);
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

    @Override
    public void execute(Runnable task) {
        Executor executor = getThreadPoolExecutor();
        TraceRunnable traceRunnable = new TraceRunnable() {
            @Override
            public void concreteRun() {
                task.run();
            }
        };
        try {
            executor.execute(traceRunnable);
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }
}
