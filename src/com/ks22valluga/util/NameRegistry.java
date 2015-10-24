package com.ks22valluga.util;

import java.util.ArrayList;

public class NameRegistry {




	private static ArrayList<String> names=new ArrayList<String>() ;
	
	
	
	public static String getUniqueName() throws UniqueCodeGenException{
		
		boolean test = false;
		int count=0;
		int retries=10;
		String retStr=null;
		while(!test){
		retStr=GenerateCodes.generateRandomCode(20, "01234567890ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz");
		if(names.contains(retStr)){
		count++;
		if(count>retries){
				throw new UniqueCodeGenException("Exceeded unique code generation retries");
		}
		}else{
			names.add(retStr);
			break;
		}
		}
		return retStr;
	}
}
