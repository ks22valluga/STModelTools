package com.ks22valluga.examples;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class Simple2NodeLowConductance {

	public static void main(String[] args) {


SimpleTEntity root = new SimpleTEntity(null, 500, 10000, 0,"root");
root.addChild(500, 0, 35, "firstchild");

for(int i=0;i<360;i++){
	root.update();
	System.out.println("Min "+i+"\t"+root.getTemp()+ "\t"+root.getChildren()[0].getTemp()+ "\t");
}


	}
	

	
	

}
