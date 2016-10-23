package com.stockbone.utils.rmi;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {

	RMIObject call(Class clazz, String method, RMIObject[] params) throws RemoteException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}
