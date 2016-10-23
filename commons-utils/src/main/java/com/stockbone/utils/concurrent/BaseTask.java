package com.stockbone.utils.concurrent;

import java.util.concurrent.Callable;

/**
 * 任务基类
 * 
 * @param <T>
 */
public abstract class BaseTask<T> implements Callable<T> {
	/**
	 * 任务id
	 */
	protected String taskId;
	/**
	 * 所属任务组
	 */
	protected TaskGroup taskGroup;

	public BaseTask(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public TaskGroup getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(TaskGroup taskGroup) {
		this.taskGroup = taskGroup;
	}

}
