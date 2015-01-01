package com.ks22valluga.test;

import static org.junit.Assert.*;

import java.io.File;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ks22valluga.util.ModelPersistence;

public class TestBasicFileReadWrite {
	
	private String inputText="one\ntwo\nthree\nfour{}[]:";
	private static String fileName="testFile.txt";
	private static String fileName2="testFile2.txt";
	private static JSONObject jo;
	
	@BeforeClass
	public final static void setup(){
		jo=new JSONObject();
		jo.put("one", "one");
		jo.put("two","two");
		jo.put("three","three");
		jo.put("four","four");
		JSONObject embedJo= new JSONObject();
		embedJo.put("1", "1");
		embedJo.put("2", "2");
		embedJo.put("3", "3");
		jo.put("embed", embedJo);
	}

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
	
	@Test
	public final void testModel(){
		ModelPersistence mp = new ModelPersistence();
		mp.createV1ModelFile(fileName2, jo);
		
		JSONObject testJo = mp.getV1ModelFromFile(fileName2);
		
		assertFalse(testJo.has("error"));
		assertEquals("one",testJo.getString("one"));
		assertEquals("two",testJo.getString("two"));
		assertEquals("three",testJo.getString("three"));
		assertEquals("four",testJo.getString("four"));
		assertTrue(testJo.has("embed"));
		JSONObject testJo2=testJo.getJSONObject("embed");
		assertEquals("1",testJo2.getString("1"));
		assertEquals("2",testJo2.getString("2"));
		assertEquals("3",testJo2.getString("3"));
		
	}
	
	
   @AfterClass
   public final static void tidy(){
		   new File(fileName).delete();
		   new File(fileName2).delete();
   }
}
