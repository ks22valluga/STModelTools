package com.ks22valluga.examples;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class Zone {

    private SimpleTEntity root;
    private SimpleTEntity child11, child21, child31, child41, child51, child61, child32;
    private ArrayList<SimpleTEntity> chain = new ArrayList<>();

    public static void main(String[] args) {

	Zone zn = new Zone();

	// set zone temp 19.5 = 1950
	for (SimpleTEntity ste : zn.chain) {
	    ste.setTemp(1950);
	}
	// set root to boiler temp 60deg
	zn.root.setFixedTemp(4000f);

	// set ext temp 2 deg
	zn.child61.setFixedTemp(200f);
	zn.child61.setMass(1900f);

	// set mass on adjacent node
	zn.child32.setMass(9000);

	StringBuilder sb = new StringBuilder();
	sb.append("Min,");
	for (SimpleTEntity ste : zn.chain) {
	    sb.append(ste.getFriendlyName() + ",");
	}
	System.out.println(sb.toString());

	for (int i = 0; i < 151; i++) {
	    zn.root.update();
	    if (i != 1500) {
		sb = new StringBuilder();

		sb.append(i + ",");
		for (SimpleTEntity ste : zn.chain) {
		    sb.append(ste.getTemp() + ",");
		}
		System.out.println(sb.toString());
	    }

	}

	// zn.root.setFixedTemp(false);
	zn.root.setFixedTemp(zn.child31.getTemp());

	for (int i = 0; i < 541; i++) {
	    zn.root.setTemp(zn.child11.getTemp());
	    zn.root.update();

	    if (i != 5400) {
		sb = new StringBuilder();

		sb.append(i + ",");
		for (SimpleTEntity ste : zn.chain) {
		    sb.append(ste.getTemp() + ",");
		}
		System.out.println(sb.toString());
	    }

	}
	
	//System.out.println(zn.root.buildConfig().toString(2));

    }

    public Zone() {
	root = new SimpleTEntity(null, 1, 0, 2000, "root");
	chain.add(root);
	float tempRoot = root.getTemp();

	root.addChild(500, 0, 10, "child11");
	child11 = root.getChildren()[0];
	chain.add(child11);
	float tempChild1 = child11.getTemp();

	child11.addChild(500, 0, 8, "child21");
	child21 = child11.getChildren()[0];
	chain.add(child21);
	float tempChild21 = child21.getTemp();

	child21.addChild(500, 0, 5, "child31");
	child31 = child21.getChildren()[0];
	chain.add(child31);
	float tempChild31 = child31.getTemp();
	child21.addChild(500, 0, 5, "child32");
	child32 = child21.getChildren()[1];
	chain.add(child32);
	float tempChild32 = child32.getTemp();

	child31.addChild(600, 0, 4, "child41");
	child41 = child31.getChildren()[0];
	chain.add(child41);
	float tempChild41 = child41.getTemp();

	child41.addChild(900, 0, 6, "child51");
	child51 = child41.getChildren()[0];
	chain.add(child51);
	float tempChild51 = child51.getTemp();

	child51.addChild(3000, 0, 100, "child61");
	child61 = child51.getChildren()[0];
	chain.add(child61);
	float tempChild61 = child61.getTemp();

    }

}
