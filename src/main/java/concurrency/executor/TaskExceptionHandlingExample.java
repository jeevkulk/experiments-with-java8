package concurrency.executor;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TaskExceptionHandlingExample {

    private Logger logger = LogManager.getLogger(TaskExceptionHandlingExample.class);
    int workerShiftDuration = 8;

    public static void main(String[] args) {
        TaskExceptionHandlingExample taskExceptionHandlingExample = new TaskExceptionHandlingExample();
        taskExceptionHandlingExample.processWithRunnable(64);
        taskExceptionHandlingExample.processWithCallable(64);
    }

    /**
     * Though future.get() used here is a blocking method call - this mainly is to demonstrate how exceptions can be
     * handled in case of runnable
     * @param totalTaskDuration
     */
    public void processWithRunnable(int totalTaskDuration) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int taskDurationCompleted = 0;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        while (taskDurationCompleted < totalTaskDuration) {
            try {
                Runnable runnableTask = () -> doRunnableTask(workerShiftDuration);
                Future<?> future = executorService.submit(runnableTask);
                future.get();
                taskDurationCompleted += workerShiftDuration;
                logger.info("Total work done: "+taskDurationCompleted);
            } catch (ExecutionException executionException) {
                logger.info(executionException.getCause());
            } catch (InterruptedException ie) {
                logger.info(ie.getCause());
            } catch (Exception ex) {
                logger.info(ex.getCause());
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(totalTaskDuration, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        logger.info(stopWatch);
    }

    private List<Runnable> getTaskList(Runnable runnableTask, int totalTaskDuration) {
        List<Runnable> taskList = new ArrayList<>();
        int taskDurationAdded = 0;
        while (taskDurationAdded < totalTaskDuration) {
            taskList.add(runnableTask);
            taskDurationAdded += workerShiftDuration;
        }
        return taskList;
    }

    /**
     * A worker cannot work for more than X secs amongst
     */
    public void doRunnableTask(long shiftDurationInSecs) {
        try {
            logger.info("Worker "+Thread.currentThread().getName()+" now will work for "+shiftDurationInSecs+" seconds");
            Thread.sleep(shiftDurationInSecs * 1000);
            if (System.currentTimeMillis() % 2 == 1)
                throw new RuntimeException();
            else
                logger.info("Worker "+Thread.currentThread().getName()+" done with work");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Though future.get() used here is a blocking method call - this mainly is to demonstrate how exceptions can be
     * handled in case of callable
     * @param totalTaskDuration
     */
    public void processWithCallable(int totalTaskDuration) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int taskDurationCompleted = 0;
        int workDoneInSecs = 0;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        while (taskDurationCompleted < totalTaskDuration) {
            try {
                Callable callableTask = () -> {
                    return doCallableTask(workerShiftDuration);
                };
                Future<Integer> future = executorService.submit(callableTask);
                workDoneInSecs = future.get();
                taskDurationCompleted += workDoneInSecs;
                logger.info("Total work done: "+taskDurationCompleted);
            } catch (ExecutionException executionException) {
                logger.info(executionException.getCause());
            } catch (InterruptedException ie) {
                logger.info(ie.getCause());
            } catch (Exception ex) {
                logger.info(ex.getCause());
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(totalTaskDuration, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        logger.info(stopWatch);
    }

    public int doCallableTask(int shiftDurationInSecs) {
        try {
            logger.info("Worker "+Thread.currentThread().getName()+" now will work for "+shiftDurationInSecs+" seconds");
            Thread.sleep(shiftDurationInSecs * 1000);
            if (System.currentTimeMillis() % 2 == 1) {
                logger.info("Throwing exception: "+Thread.currentThread().getName());
                throw new RuntimeException();
            } else {
                logger.info("Worker "+Thread.currentThread().getName()+" done with work");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return shiftDurationInSecs;
    }
}
