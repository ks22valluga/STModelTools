package com.ks22valluga.examples;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class Simple2Node {

	public static void main(String[] args) {


SimpleTEntity root = new SimpleTEntity(null, 500, 10000, 0,"root");
root.addChild(500, 0, 5, "firstchild");

for(int i=0;i<360;i++){
	root.update();
	System.out.println("Min "+i+"\t"+root.getTemp()+ "\t"+root.getChildren()[0].getTemp()+ "\t");
}


	}
	
}
