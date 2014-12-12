package com.ks22valluga.test;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class TestBuildMethod {

	public static void main(String[] args) {


SimpleTEntity root = new SimpleTEntity(null, 500000, 2500, 0,"root");
root.addChild(500, 0, 5, "one");
root.addChild(300, 0, 5, "two");
root.addChild(200, 0, 5,"three");
root.addChild(600, 0, 5,"four");
root.getChildren()[0].addChild(500, 000, 25,"one-one");
root.getChildren()[1].addChild(500, 000, 25,"two-one");
root.getChildren()[2].addChild(500, 000, 25,"three-one");
root.getChildren()[0].getChildren()[0].addChild(500, 0, 5,"one-one-one");

/*
for(int i=0;i<360;i++){
	root.update();
	System.out.println("Min "+i+"\t"+root.getTemp()+ "\t"+root.getChildren()[0].getTemp()+ "\t"+root.getChildren()[0].getChildren()[0].getTemp()+"\t"+root.getChildren()[0].getChildren()[0].getChildren()[0].getTemp()+"\t");
}
*/
JSONObject jo = new JSONObject();
jo=root.buildConfig();
System.out.println(jo.toString(2));


	}
	

	
	

}
