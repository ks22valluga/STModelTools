package com.ks22valluga.util;


import java.util.Random;

public class GenerateCodes {
//	private static final int expCodeSize=15;
//	private static final String validCharactersStr=("0123456789ABCDEFGHJKLMNPQRSTUVWXYZ");	
	
	public static String generateRandomCode(int expCodeSize, String validCharactersStr ){
        
		Random rand = new Random();
		String code="";
		for (int i =0; i< expCodeSize;i++){
			
			int nextInt =rand.nextInt(validCharactersStr.length()); //ascii char offset
			code=code+(validCharactersStr.charAt(nextInt));
		}
		
		
		
		
		return code;
	}

}
