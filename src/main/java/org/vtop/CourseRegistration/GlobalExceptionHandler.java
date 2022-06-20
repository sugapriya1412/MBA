package org.vtop.CourseRegistration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXParseException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@ControllerAdvice
public class GlobalExceptionHandler //implements ErrorController 
{	
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	private static final String PATH = "/error";
	
	@ExceptionHandler({NullPointerException.class})
	public ModelAndView appNullPointErrorHandling(NullPointerException exception,HttpServletRequest request) 
	{		 
		HttpSession session = request.getSession();	
		logger.trace(exception);
		try 
		{
			session.setAttribute("baseURL", NetAssist.getBaseURL(request));
		} 
		catch (ServletException e) 
		{		
			logger.trace(e);			
		}
		
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception);
	    mav.setViewName("CustomErrorPage");
	    mav.addObject("message","Session Expired");
	    mav.addObject("error","Try Logout and Log-in");
	    mav.addObject("errno",3);
	    return mav;
	}
	  
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ModelAndView appURLExceptionClass(Exception exception,HttpServletRequest request) 
	{
		HttpSession session = request.getSession();		
		
		try 
		{
			session.setAttribute("baseURL", NetAssist.getBaseURL(request));
		} 
		catch (ServletException e) 
		{			
			logger.trace(e);
		}   
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception);
	    mav.setViewName("CustomErrorPage");
	    mav.addObject("message","Session Expired");
	    mav.addObject("error","Try Logout and Log-in");
	    mav.addObject("errno",3);
	    return mav;
  	}
	
	@ExceptionHandler({AppExceptionHandler.class,ServletException.class})
	public ModelAndView appLoginExceptionClass(Exception exception,HttpServletRequest request) 
	{
		HttpSession session = request.getSession();	
		logger.trace(exception);
		try 
		{
			session.setAttribute("baseURL", NetAssist.getBaseURL(request));
		} 
		catch (ServletException e) 
		{
			logger.trace(e);
		}		
	
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception);
	    mav.setViewName("CustomErrorPage");
	    mav.addObject("message","Session Expired");
	    mav.addObject("error","Try Logout and Log-in");
	    mav.addObject("errno",3);
	    return mav;	    
	}
	
    @ExceptionHandler(value = {Exception.class,SAXParseException.class})
    public ModelAndView unknownException(Exception ex,HttpServletRequest request) 
    {
		HttpSession session = request.getSession();
		logger.trace(ex);
		try 
		{
			session.setAttribute("baseURL", NetAssist.getBaseURL(request));
		} 
		catch (ServletException e) 
		{
			logger.trace(e);		
		}
		
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", ex);
	    mav.setViewName("CustomErrorPage");
	    mav.addObject("message","Session Expired");
	    mav.addObject("error","Try Logout and Log-in");
	    mav.addObject("errno",3);
	    return mav;
    }
    
	@RequestMapping(value = PATH)
    public String error(Model model,HttpSession session,HttpServletRequest request) throws ServletException 
	{	
		try 
		{
			session.setAttribute("baseURL", NetAssist.getBaseURL(request));
		} 
		catch (ServletException e) 
		{			
			logger.trace(e);
		}

		String page="CustomErrorPage";
		model.addAttribute("message","Throwing Error from CustomErrorMapping for white lable error /error" );
		model.addAttribute("error","Please look logs for more details.." );
		model.addAttribute("errno",1);		
		return page;
    }
    
	//@Override
	public String getErrorPath() 
	{
		return PATH;
	}
}
