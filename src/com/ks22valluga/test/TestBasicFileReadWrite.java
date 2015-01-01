package com.ks22valluga.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import com.ks22valluga.util.ModelPersistence;

public class TestBasicFileReadWrite {
	
	private String inputText="one\ntwo\nthree\nfour";
	private static String fileName="testFile.txt";

	@Test
	public final void testWrite() {
		ModelPersistence mp = new ModelPersistence();
		assertTrue(mp.write(fileName, inputText));
	}

	@Test
	public final void testRead() {
		ModelPersistence mp = new ModelPersistence();
		assertEquals(inputText, mp.read(fileName));
	}
	
	
   @AfterClass
   public final static void tidy(){
		   new File(fileName).delete();
   }
}
