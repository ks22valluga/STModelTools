package com.ks22valluga.examples;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class InterZoneConnector {

    private SimpleTEntity root;
    private SimpleTEntity endA,endB;
    private SimpleTEntity child11, child21, child31, child41, child51, child61;
    ArrayList<SimpleTEntity> chain;
 

    public static void main(String[] args) {

	InterZoneConnector zn = new InterZoneConnector();

	// set zone temp 19.5 = 1950
	for (SimpleTEntity ste : zn.getChain()) {
	    ste.setTemp(0);
	}
	// set root to boiler temp 60deg
	zn.getRoot().setFixedTemp(4000f);

	// set ext temp 2 deg
	zn.getChild61().setFixedTemp(0f);
	zn.getChild61().setMass(1900f);

	// set mass on adjacent node


	StringBuilder sb = new StringBuilder();
	sb.append("Min,");
	for (SimpleTEntity ste : zn.getChain()) {
	    sb.append(ste.getFriendlyName() + ",");
	}
	System.out.println(sb.toString());

	for (int i = 0; i < 10000; i++) {
	    zn.getRoot().update();
	    if (i != 1500) {
		sb = new StringBuilder();

		sb.append(i + ",");
		for (SimpleTEntity ste : zn.getChain()) {
		    sb.append(ste.getTemp() + ",");
		}
		System.out.println(sb.toString());
	    }

	}

	// zn.root.setFixedTemp(false);
	//zn.root.setFixedTemp(zn.child31.getTemp());

//	for (int i = 0; i < 541; i++) {
//	  //  zn.root.setTemp(zn.child11.getTemp());
//	    zn.root.update();
//
//	    if (i != 5400) {
//		sb = new StringBuilder();
//
//		sb.append(i + ",");
//		for (SimpleTEntity ste : zn.chain) {
//		    sb.append(ste.getTemp() + ",");
//		}
//		System.out.println(sb.toString());
//	    }

	//}
	
	//System.out.println(zn.root.buildConfig().toString(2));

    }

    public InterZoneConnector() {
    	
    chain = new ArrayList<>();	
	setRoot(new SimpleTEntity(null, 500, 0, 5, "root"));
	chain.add(getRoot());
	float tempRoot = getRoot().getTemp();

	getRoot().addChild(500, 0, 5, "child11");
	child11 = getRoot().getChildren()[0];
	chain.add(child11);
	float tempChild1 = child11.getTemp();

	child11.addChild(500, 0, 5, "child21");
	child21 = child11.getChildren()[0];
	chain.add(child21);
	float tempChild21 = child21.getTemp();

	child21.addChild(50000, 0, 1, "child31");
	child31 = child21.getChildren()[0];
	chain.add(child31);


	child31.addChild(500, 0, 5, "child41");
	child41 = child31.getChildren()[0];
	chain.add(child41);
	float tempChild41 = child41.getTemp();

	child41.addChild(500, 0, 5, "child51");
	child51 = child41.getChildren()[0];
	chain.add(child51);
	float tempChild51 = child51.getTemp();

	child51.addChild(500, 0, 5, "child61");
	setChild61(child51.getChildren()[0]);
	chain.add(getChild61());
	float tempChild61 = getChild61().getTemp();

    }

	public ArrayList<SimpleTEntity> getChain() {
		return chain;
	}


	public SimpleTEntity getRoot() {
		return root;
	}

	public void setRoot(SimpleTEntity root) {
		this.root = root;
	}

	public SimpleTEntity getChild61() {
		return child61;
	}

	public void setChild61(SimpleTEntity child61) {
		this.child61 = child61;
	}

}
