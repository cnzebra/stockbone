package com.stockbone.utils.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 任务组
 * 
 */
public class TaskGroup {
	/**
	 * 组id
	 */
	private String groupId;
	/**
	 * 任务(Map<taskId, task>)
	 */
	private Map<String, BaseTask> tasks = new HashMap<String, BaseTask>();
	/**
	 * 任务运行结果
	 */
	private Map<String, Future> taskFutures = new HashMap<String, Future>();
	/**
	 * 父任务组
	 */
	private TaskGroup parentGroup;
	/**
	 * 子任务组
	 */
	private List<TaskGroup> subGroups;

	public TaskGroup(String groupId) {
		this(groupId, null);
	}

	public TaskGroup(String groupId, TaskGroup parentGroup) {
		this.groupId = groupId;
		this.parentGroup = parentGroup;
		this.taskFutures = new HashMap<String, Future>();
		this.subGroups = new ArrayList<TaskGroup>();
	}

	/**
	 * 获取父任务组
	 * 
	 * @return
	 */
	public TaskGroup getParentGroup() {
		return parentGroup;
	}

	/**
	 * 获取当前任务组id
	 * 
	 * @return
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 获取所有任务
	 * 
	 * @return
	 */
	public Map<String, BaseTask> getTasks() {
		return tasks;
	}

	/**
	 * 添加任务到当前组
	 * 
	 * @param task
	 */
	public void addTask(BaseTask task) {
		if (task != null) {
			task.setTaskGroup(this);
			tasks.put(task.getTaskId(), task);
		}
	}

	/**
	 * 获取所有子线程组
	 * 
	 * @return
	 */
	public List<TaskGroup> getSubGroups() {
		return subGroups;
	}

	/**
	 * 添加子线程组
	 * 
	 * @param subGroup
	 */
	public void addSubGroup(TaskGroup subGroup) {
		if (subGroup != null) {
			subGroups.add(subGroup);
		}
	}

	/**
	 * 添加任务结果
	 * 
	 * @param taskId
	 * @param future
	 */
	public void addTaskFutures(String taskId, Future future) {
		if (taskId != null && future != null) {
			taskFutures.put(taskId, future);
		}
	}

	/**
	 * 获取任务运行结果集
	 * 
	 * @return
	 */
	public Map<String, Future> getTaskFutures() {
		return taskFutures;
	}

	/**
	 * 中断线程组中所有任务
	 * <p>
	 * mayInterruptIfRunning 是否立即中断。若为false，等待线程执行完
	 * </p>
	 * 
	 * @param mayInterruptIfRunning
	 * @return
	 */
	public boolean cancel(boolean mayInterruptIfRunning) {
		Set<Entry<String, Future>> set = this.getTaskFutures().entrySet();
		for (Entry<String, Future> entry : set) {
			Future future = entry.getValue();
			future.cancel(mayInterruptIfRunning);
		}
		for (TaskGroup subGroup : this.getSubGroups()) {
			subGroup.cancel(mayInterruptIfRunning);
		}
		return true;
	}

	/**
	 * 线程组任务是否全部完成
	 * 
	 * @return
	 */
	public boolean isDone() {
		Set<Entry<String, Future>> set = this.getTaskFutures().entrySet();
		for (Entry<String, Future> entry : set) {
			Future future = entry.getValue();
			if (!future.isDone()) {
				return false;
			}
		}
		for (TaskGroup subGroup : this.getSubGroups()) {
			if (!subGroup.isDone()) {
				return false;
			}
		}
		return true;
	}

}
