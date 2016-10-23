package com.stockbone.utils.rmi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.commons.lang3.StringUtils;

public class RMIServiceImpl extends UnicastRemoteObject implements RMIService {

	private static final long serialVersionUID = -1265554704691810173L;

	public RMIServiceImpl() throws RemoteException {

	}

	@Override
	public RMIObject call(Class clazz, String methodName, RMIObject[] params) throws RemoteException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (clazz == null || StringUtils.isEmpty(methodName)) {
			throw new IllegalArgumentException();
		}
		Method targetMethod = null;
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				targetMethod = method;
			}
		}
		if (targetMethod == null) {
			throw new IllegalArgumentException(clazz.getName() + " method " + methodName + " do not exist!");
		}
		Class[] parameterTypes = targetMethod.getParameterTypes();
		if ((parameterTypes.length != 0 && params == null) || (params != null && parameterTypes.length != params.length)) {
			throw new IllegalArgumentException(clazz.getName() + "." + methodName + " params length do not match.");
		}
		Object[] realParams = null;
		if (params != null) {
			realParams = new Object[params.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				Object param = params[i].getObject();
				if (!parameterTypes[i].getName().equals(param.getClass().getName())) {
					throw new IllegalArgumentException(clazz.getName() + "." + methodName + " param[" + i + "] do not match.");
				}
				realParams[i] = param;
			}
		}
		Object instance = clazz.newInstance();
		Object result = targetMethod.invoke(instance, realParams);
		return new RMIObject(result);
	}

}
