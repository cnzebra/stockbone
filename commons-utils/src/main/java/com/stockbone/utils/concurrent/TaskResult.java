package com.stockbone.utils.concurrent;

public class TaskResult<T> {
	
	public boolean isSuccess = false;
	public T result;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
