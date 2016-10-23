package com.stockbone.utils.rmi;

import java.io.Serializable;

public class RMIObject implements Serializable {

	private static final long serialVersionUID = 1089842578935673069L;

	public RMIObject() {

	}

	public RMIObject(Object object) {
		this.object = object;
	}

	private Object object;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
