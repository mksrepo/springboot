package com.ttt.ui.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

public class ProxyUtil {

	private String hostName = null;
	private int hostPort = 0;

	public ProxyUtil() throws URISyntaxException {
		System.setProperty("java.net.useSystemProxies", "true");
		List<Proxy> l = ProxySelector.getDefault().select(new URI("https://www.cognizant.com//"));
		for (Iterator<Proxy> iter = l.iterator(); iter.hasNext();) {
			Proxy proxy = iter.next();
			InetSocketAddress addr = (InetSocketAddress) proxy.address();
			if (addr == null) {
				System.out.println("No Proxy");
			} else {
				this.setHostName(addr.getHostName());
				this.setHostPort(addr.getPort());
			}
		}
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getHostPort() {
		return hostPort;
	}

	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}
}