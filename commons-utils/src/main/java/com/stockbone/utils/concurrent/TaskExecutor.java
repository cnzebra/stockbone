package com.stockbone.utils.concurrent;

import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务执行器
 * 
 */
public class TaskExecutor {

	private static Logger logger = LoggerFactory.getLogger(TaskExecutor.class);
	private ExecutorService executor;

	public TaskExecutor(int poolSize) {
		if (poolSize <= 0) {
			throw new IllegalArgumentException();
		}
		executor = Executors.newFixedThreadPool(poolSize);
	}

	/**
	 * 执行线程，并返回线程执行结果。
	 * 
	 * @param <T>
	 * @param task
	 * @return
	 */
	public <T> Future<T> submit(BaseTask<T> task) {
		if (task == null) {
			logger.warn("Task is null!");
			throw new IllegalArgumentException("Task must not be null!");
		}
		return executor.submit(task);
	}

	/**
	 * 执行线程。不返回结果
	 * 
	 * @param thread
	 */
	public void execute(Thread thread) {
		if (thread == null) {
			logger.warn("Thread is null!");
			throw new IllegalArgumentException("Thread must not be null!");
		}
		executor.execute(thread);
	}

	/**
	 * 执行线程组
	 * 
	 * @param taskGroup
	 * @return
	 */
	public TaskGroup submit(TaskGroup taskGroup) {
		if (taskGroup == null) {
			logger.warn("TaskGroup is null!");
			throw new IllegalArgumentException("TaskGroup must not be null!");
		}
		if (taskGroup.getTasks() != null) {
			Set<Entry<String, BaseTask>> set = taskGroup.getTasks().entrySet();
			for (Entry<String, BaseTask> entry : set) {
				String taskId = entry.getKey();
				BaseTask task = entry.getValue();
				Future future = executor.submit(task);
				taskGroup.addTaskFutures(taskId, future);
			}
		}
		for (TaskGroup subGroup : taskGroup.getSubGroups()) {
			this.submit(subGroup);
		}
		return taskGroup;
	}

	/**
	 * 当所有线程执行完毕后退出。 executor.shutdown();不会立即退出。只是不再接收任务。
	 */
	public void shutdown() {
		executor.shutdown();
	}

	/**
	 * 所有线程立即退出。
	 */
	public void shutdownNow() {
		executor.shutdownNow();
	}

	/**
	 * 获取可用空闲的线程总数
	 * 
	 * @return
	 */
	public int getFreeTaskCount() {
		ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executor;
		return poolExecutor.getMaximumPoolSize() - poolExecutor.getActiveCount();
	}

}
