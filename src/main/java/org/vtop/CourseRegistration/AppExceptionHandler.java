package org.vtop.CourseRegistration;

import javax.servlet.ServletException;

public class AppExceptionHandler extends ServletException
{
	private static final long serialVersionUID = 1L;

	public AppExceptionHandler(String message) {
		super(message);
	}
}
