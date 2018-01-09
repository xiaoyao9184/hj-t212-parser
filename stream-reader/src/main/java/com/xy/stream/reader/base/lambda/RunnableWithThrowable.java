package com.xy.stream.reader.base.lambda;

/**
 * Created by xiaoyao9184 on 2018/1/5.
 */
@FunctionalInterface
public interface RunnableWithThrowable<T extends Throwable> {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */
    public abstract void run() throws T;
}
