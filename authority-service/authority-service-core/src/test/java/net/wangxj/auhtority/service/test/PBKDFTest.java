package net.wangxj.auhtority.service.test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import net.wangxj.util.encry.PBKDF2SHA1;

public class PBKDFTest{
	
	@Test
	public void testPBKDF() throws NoSuchAlgorithmException, InvalidKeySpecException    
	{
	    String  originalPassword = "123";
	    Long begin = System.currentTimeMillis();
	    String generatedSecuredPasswordHash = PBKDF2SHA1.generateStorngPasswordHash(originalPassword);
	    System.out.println(generatedSecuredPasswordHash + "--"+(System.currentTimeMillis() - begin));
	    Long begin2 = System.currentTimeMillis();
	    boolean matched = PBKDF2SHA1.validatePassword("123", generatedSecuredPasswordHash);
        System.out.println(matched +"----" + (System.currentTimeMillis() - begin2));
        
        matched = PBKDF2SHA1.validatePassword("password1", generatedSecuredPasswordHash);
        System.out.println(matched);
	}
	
	
}
