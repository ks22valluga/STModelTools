package com.ks22valluga.examples;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class MoreComplexExample {

	public static void main(String[] args) {

		SimpleTEntity root = new SimpleTEntity(null, 500000, 2500, 0, "root");
		root.addChild(500, 0, 5, "one");
		root.getChildren()[0].addChild(500, 000, 25, "one-one");
		root.getChildren()[0].getChildren()[0].addChild(500, 0, 5,
				"one-one-one");

		for (int i = 0; i < 360; i++) {
			root.update();
			System.out.println("Min "
					+ i
					+ "\t"
					+ root.getTemp()
					+ "\t"
					+ root.getChildren()[0].getTemp()
					+ "\t"
					+ root.getChildren()[0].getChildren()[0].getTemp()
					+ "\t"
					+ root.getChildren()[0].getChildren()[0].getChildren()[0]
							.getTemp() + "\t");
		}

	}

}
