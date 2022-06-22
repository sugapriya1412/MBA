/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vtop.CourseRegistration.service;

import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.CaptchaConstants;
import java.security.MessageDigest;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.*;


@Service
@Transactional(readOnly=true)
public class Sdc_common_functions 
{
	private static final Logger logger = LogManager.getLogger(Sdc_common_functions.class);
			
	public static final String FILE_TYPE = "jpeg";
	private static final Set<String> s = new HashSet<String>();

	static {
		s.add("f**k"); s.add("suck"); s.add("dick"); 	s.add("b**b");
		s.add("c*m"); s.add("bastar"); s.add("pish"); s.add("shit");
		s.add("damn"); s.add("bitch"); s.add("crap"); s.add("piss");
		s.add("darn"); s.add("pussy"); s.add("cock"); s.add("fag");
		s.add("asshol"); s.add("slut"); s.add("douche"); s.add("sexdad");
	}

	private static final Random r = new Random(System.currentTimeMillis());

	public String getWord() {
		int length = 6;
		String cand = getrandom(length);
		while (isSwearWord(cand)) {
			cand = getrandom(length);
		}
		return cand;
	}

	private boolean isSwearWord(final String w) {
		return s.contains(w.toLowerCase());
	}

	public String getRandomWord(final Integer length) {
		
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			final int rnd = r.nextInt(52);
			final char c = (char) (rnd < 26 ? 'a' + rnd : 'A' + (rnd - 26));
			if (c == 'O' || c == 'o' || c == '0')
				continue;
			sb.append(c);
		}
		return sb.toString();
	}

    
    public String getrandom(int keylen) 
	{
    	final String alphabet = "ABCDEFGHJKMNPRSTUVWXYZ23456789";
    	
    	StringBuilder stringBuilder = new StringBuilder();
		Random r = new Random();
				
		for (int i = 0; i < keylen; i++)
		{
			stringBuilder.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		
		return stringBuilder.toString();
	}
    
    public Integer getrandom1(int keylen) 
	{
		final List<Integer> alphabet = Arrays.asList(8,14,17,22,27,31,35,37,41,47,53,68,73,79,81,88,93,96,98,100,111,113,118,132,148,152,157,168,173,188,200,221,233,237,260);
		final int N = alphabet.size();
		int key = 0;
		Random r = new Random();
		for (int i = 0; i < keylen; i++) 
		{	
			key = key + alphabet.get(r.nextInt(N));
		}
		return key;
	}
    
    public String getrandom3(int keyLength) 
	{
    	final String alphabet = "0123456789abcdef";
		final int N = alphabet.length();
		String key = "";
		Random r = new Random();
		for (int i = 0; i < keyLength; i++) 
		{
			//logger.trace("\n"+ alphabet.charAt(r.nextInt(N)));
			key = key + alphabet.charAt(r.nextInt(N));
		}
		return key;
	}
	
	public static String convertByteToHex(byte data[])
	{
	    StringBuffer hexData = new StringBuffer();
	    for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
	        hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));
	     return hexData.toString();
	}
	
	public String Hash512(String hash_string)
	{
		String hashval=null;
		MessageDigest md;
		
		try
		{
			md = MessageDigest.getInstance("SHA-512");
			md.update(hash_string.getBytes("UTF_16LE"));			
		    byte[] digest = md.digest();    
		    hashval = convertByteToHex(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hashval;
	}
	
	public String Encrypt_String(String pass_string)
	{
		String mykey=null, encpstr = null;		
		mykey = getrandom(32);		
		pass_string = pass_string + mykey;
	    encpstr = mykey + Hash512(pass_string);
	    return encpstr;		
	}
	
	public String Encrypt_String3(String pass_string, int keyLength)
	{
		String mykey = null, encpstr = null;		
		mykey = getrandom3(keyLength);		
		pass_string = pass_string + mykey;
	    encpstr = mykey + Hash512(pass_string);
	    return encpstr;		
	}
		
	public int String_Validate(String pass_string, String pass_string2)
	{		
		int result;
		String salt=null, dbpwdhash=null, passwdhash=null;
		salt = pass_string2.substring(0,32);
		dbpwdhash = pass_string2.substring(32,pass_string2.length());
		passwdhash = Hash512(pass_string + salt);
		
		if (passwdhash.equals(dbpwdhash))
		{
			result = 1;
		}
		else
		{
			result = 0;
		}
		
		return result;
	}
	
	public int String_Validate3(String pass_string, String pass_string2, int keyLength)
	{		
		int result = 2;
		String salt = null, dbpwdhash = null, passwdhash = null;
		
		salt = pass_string2.substring(0, keyLength);
		dbpwdhash = pass_string2.substring(keyLength, pass_string2.length());
		passwdhash = Hash512(pass_string + salt);
		if (passwdhash.equals(dbpwdhash))
		{
			result = 1;
		}
		
		return result;
	}
	
	public void drawTextOnImage(Graphics2D graphics, String captchaText, HttpServletRequest request)
    {
		String displayCaptcha = "";
		
		displayCaptcha = getWord();
		captchaText = "";
		captchaText = displayCaptcha;
		Font font;
        TextLayout textLayout;
        double currentFontStatus = Math.random();

        // Generate random font status.
        if (currentFontStatus >= 0.5)
        {
            font = new Font("Arial", Font.PLAIN, 40);
        }
        else
        {
            font = new Font("Arial", Font.BOLD, 40);
        }

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        textLayout = new TextLayout(captchaText, font, graphics.getFontRenderContext());
        textLayout.draw(graphics, CaptchaConstants.TEXT_X_COORDINATE,CaptchaConstants.TEXT_Y_COORDINATE);
        HttpSession session = request.getSession(true);
        session.setAttribute("CAPTCHA", captchaText);  
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		Sdc_common_functions sdf = new Sdc_common_functions();
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        response.setContentType("image/jpeg");   	        
        String captchaText = "";        
        int bufferedImageWidth=245;
        int bufferedImageHeight=70;
        
        try 
        {
        	BufferedImage bufferedImage;
	        Graphics2D graphics;
	        bufferedImage = new BufferedImage(CaptchaConstants.DEFAULT_CAPTCHA_WIDTH,CaptchaConstants.DEFAULT_CAPTCHA_HEIGHT,BufferedImage.TYPE_BYTE_INDEXED);	        
	        graphics = bufferedImage.createGraphics();	        
	        applyCurrentGradientPaint(graphics, bufferedImage.getWidth(), bufferedImage.getHeight(), Color.PINK, Color.GRAY);
	        graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
	        graphics.setColor(Color.BLUE);
	        // Draw text on the CAPTCHA image.
	        sdf.drawTextOnImage(graphics, captchaText, request);
	        // Draw the image border.
	        drawBorders(graphics, bufferedImage.getWidth(), bufferedImage.getHeight());
	        drawBrokenLineOnImage(graphics);
	        applyShear(graphics, bufferedImageWidth, bufferedImageHeight,Color.BLACK, Color.BLACK);
	        graphics.drawString(captchaText,3,15);      
	        ByteArrayOutputStream out = new ByteArrayOutputStream();    	        
            ImageIO.write(bufferedImage, FILE_TYPE, out);              
            String res = DatatypeConverter.printBase64Binary(out.toByteArray());               
            HttpSession session = request.getSession(true);          
            session.setAttribute("ENCDATA", res);             
            out.flush();
            out.close();
        } 
        catch (Exception e) 
        {
        	logger.trace(e);
        }
    }
	
	public static void applyCurrentGradientPaint(Graphics2D graphics,int width, int height, Color startingColor, Color endingColor)
    {
        GradientPaint gradientPaint = new GradientPaint(0, 0, startingColor,width, height, endingColor);
        graphics.setPaint(gradientPaint);
    }
	
	public void drawBorders(Graphics2D graphics, int width, int height)
    {
        graphics.setColor(Color.WHITE);
        graphics.drawLine(0, 0, 0, width - 1);
        graphics.drawLine(0, 0, width - 1, 0);
        graphics.drawLine(0, height - 1, width, height - 1);
        graphics.drawLine(width - 1, height - 1, width - 1, 0);
    }
	
	public void drawThickLineOnImage(Graphics graphics, int x1, int y1,int x2, int y2)
    {
        int dX = x2 - x1;
        int dY = y2 - y1;
        int xPoints[] = new int[4];
        int yPoints[] = new int[4];
        int thickness = 2;

        double lineLength = Math.sqrt(dX * dX + dY * dY);
        double scale = (double) (thickness) / (2 * lineLength);
        double ddx = -scale * (double) dY;
        double ddy = scale * (double) dX;

        graphics.setColor(Color.WHITE);

        ddx += (ddx > 0) ? 0.5 : -0.5;
        ddy += (ddy > 0) ? 0.5 : -0.5;
        dX = (int) ddx;
        dY = (int) ddy;

        xPoints[0] = x1 + dX;
        yPoints[0] = y1 + dY;
        xPoints[1] = x1 - dX;
        yPoints[1] = y1 - dY;
        xPoints[2] = x2 - dX;
        yPoints[2] = y2 - dY;
        xPoints[3] = x2 + dX;
        yPoints[3] = y2 + dY;

        graphics.fillPolygon(xPoints, yPoints, 4);
    }

    public void drawBrokenLineOnImage(Graphics2D graphics)
    {

        int yPoint1;
        int yPoint2;
        int yPoint3;
        int yPoint4;
        int yPoint5;
        Random random = new Random();

        // Random Y Points.
        yPoint1 = random.nextInt(CaptchaConstants.DEFAULT_CAPTCHA_HEIGHT);
        yPoint2 = random.nextInt(CaptchaConstants.DEFAULT_CAPTCHA_HEIGHT);
        yPoint3 = CaptchaConstants.DEFAULT_CAPTCHA_HEIGHT / 2;
        yPoint4 = random.nextInt(CaptchaConstants.DEFAULT_CAPTCHA_HEIGHT);
        yPoint5 = random.nextInt(CaptchaConstants.DEFAULT_CAPTCHA_HEIGHT);

        // Draw the random broken line.
        drawThickLineOnImage(graphics, 0, yPoint1,CaptchaConstants.DEFAULT_CAPTCHA_WIDTH / 4, yPoint2);
        drawThickLineOnImage(graphics,CaptchaConstants.DEFAULT_CAPTCHA_WIDTH / 4, yPoint2,CaptchaConstants.DEFAULT_CAPTCHA_WIDTH / 2, yPoint3);
        drawThickLineOnImage(graphics,CaptchaConstants.DEFAULT_CAPTCHA_WIDTH / 2, yPoint3,3 * CaptchaConstants.DEFAULT_CAPTCHA_WIDTH / 4, yPoint4);
        drawThickLineOnImage(graphics,3 * CaptchaConstants.DEFAULT_CAPTCHA_WIDTH / 4, yPoint4,CaptchaConstants.DEFAULT_CAPTCHA_WIDTH, yPoint5);
    }
    
    public double getDelta(int period, double i, double phase, double frames)
    {
        return (double) (period / 2) * Math.sin(i / (double) period + (2 * CaptchaConstants.PI * phase) / frames);
    }


    public void applyShear(Graphics2D graphics, int bufferedImageWidth,int bufferedImageHeight, Color startingColor, Color endingColor)
    {

	    int periodValue = 15;
	    int numberOfFrames = 3;
	    int phaseNumber = 5;
	    double deltaX;
	    double deltaY;

	    applyCurrentGradientPaint(graphics, bufferedImageWidth,bufferedImageHeight, startingColor, endingColor);

	    for (int i = 0; i < bufferedImageWidth; ++i)
	    {
	        deltaX = getDelta(periodValue, i, phaseNumber, numberOfFrames);
	        graphics.copyArea(i, 0, 1, bufferedImageHeight, 0, (int) deltaX);
	        graphics.drawLine(i, (int) deltaX, i, 0);
	        graphics.drawLine(i, (int) deltaX + bufferedImageHeight, i,
	                bufferedImageHeight);
	    }

	    for (int i = 0; i < bufferedImageHeight; ++i)
	    {
	        deltaY = getDelta(periodValue, i, phaseNumber, numberOfFrames);
	        graphics.copyArea(0, i, bufferedImageWidth, 1, (int) deltaY, 0);
	        graphics.drawLine((int) deltaY, i, 0, i);
	        graphics.drawLine((int) deltaY + bufferedImageWidth, i,
	                bufferedImageWidth, i);
	    }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	Sdc_common_functions sdf2 = new Sdc_common_functions();	
    	sdf2.doPost(request, response);
    }        
}
