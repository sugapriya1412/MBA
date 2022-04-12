package org.vtop.CourseRegistration.controller;

import java.util.Random;
import java.security.MessageDigest;

public class SDCAuthentication {
    
    public String getrandom(int keylen) 
	{
		final String alphabet = "ABCDEFGHJKMNPRSTUVWXYZ23456789abcdefghjkmnprstuvwxyz";
		final int N = alphabet.length();
		String key = "";
		Random r = new Random();
		for (int i = 0; i < keylen; i++) 
		{
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
}
