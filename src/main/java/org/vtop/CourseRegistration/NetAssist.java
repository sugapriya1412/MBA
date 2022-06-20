package org.vtop.CourseRegistration;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class NetAssist
{	
	public static String getBaseURL(HttpServletRequest request) throws ServletException
	{
		StringBuffer sb=null;
		String path=null;
		
		URL url=getURL(request);
		
		sb=new StringBuffer();
		sb.append(url.getProtocol());
		sb.append("://");
		sb.append(url.getHost());
		int port=url.getPort();
		if(port !=-1) {
			sb.append(":");
			sb.append(port);
		}
		sb.append(request.getContextPath());
		sb.append("/");
		path=sb.toString();
		
		return path;
	}

	public static URL getURL(HttpServletRequest request) throws ServletException
	{	
		URL url = null;
		String path=getURLPath(request);
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			throw new ServletException(e.getMessage());
		}

		return url;
	}
	
	public static int getURLPort(HttpServletRequest request) throws ServletException
	{
		URL url=getURL(request);
		return url.getPort();
	}

	public static String getURLHost(HttpServletRequest request) throws ServletException
	{
		URL url=getURL(request);
		return url.getHost();
	}

	public static String getURLProtocol(HttpServletRequest request) throws ServletException
	{
		URL url=getURL(request);
		return url.getProtocol();
	}
	
	public static String getURLPath(HttpServletRequest request)
	{
		String path= request.getRequestURL().toString();
		return path;
	}
}
