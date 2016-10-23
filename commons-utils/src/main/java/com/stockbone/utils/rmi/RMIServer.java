package com.stockbone.utils.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.rmi.PortableRemoteObject;

public class RMIServer {

	private RMIService service;
	private String ip;
	private int bindPort;
	private String serviceName;
	private String url;

	public RMIServer() {

	}

	public RMIServer(String ip, int bindPort, String serviceName) {
		this.ip = ip;
		this.bindPort = bindPort;
		this.serviceName = serviceName;
	}

	public void startup() throws RemoteException, MalformedURLException {
		if (service != null) {
			shutdown();
		}
		service = new RMIServiceImpl();
		LocateRegistry.createRegistry(bindPort);
		url = "rmi://" + ip + ":" + bindPort + serviceName;
		Naming.rebind(url, service);
	}

	public void shutdown() throws NoSuchObjectException {
		PortableRemoteObject.unexportObject(service);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setBindPort(int bindPort) {
		this.bindPort = bindPort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getBindPort() {
		return bindPort;
	}

}
