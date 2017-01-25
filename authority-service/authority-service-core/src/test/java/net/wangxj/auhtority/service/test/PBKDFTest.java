package net.wangxj.auhtority.service.test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import net.wangxj.util.encry.PBKDF2SHA1;

public class PBKDFTest{
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException    
	{
	    String  originalPassword = "123";
	    String generatedSecuredPasswordHash = PBKDF2SHA1.generateStorngPasswordHash(originalPassword);
	    String[] split = generatedSecuredPasswordHash.split(":");
	    System.out.println(generatedSecuredPasswordHash+"----"+generatedSecuredPasswordHash.length());
	    
	    boolean matched = PBKDF2SHA1.validatePassword("123", generatedSecuredPasswordHash);
        System.out.println(matched);
        
        matched = PBKDF2SHA1.validatePassword("password1", generatedSecuredPasswordHash);
        System.out.println(matched);
	}
	
	
}
