package com.ks22valluga.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ks22valluga.examples.InterZoneConnector;
import com.ks22valluga.thermal.SimpleTEntity;

public class TestInterZoneConnector {
 //   private SimpleTEntity root;
 //   private SimpleTEntity endA,endB;
 //   private SimpleTEntity child11, child21, child31, child41, child51, child61;
    private  ArrayList<SimpleTEntity> chain = new ArrayList<>();
    private InterZoneConnector zn;
	
	
	@Before
	public void setup() {
	zn = new InterZoneConnector();

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



		// zn.root.setFixedTemp(false);
		//zn.root.setFixedTemp(zn.child31.getTemp());

//		for (int i = 0; i < 541; i++) {
//		  //  zn.root.setTemp(zn.child11.getTemp());
//		    zn.root.update();
	//
//		    if (i != 5400) {
//			sb = new StringBuilder();
	//
//			sb.append(i + ",");
//			for (SimpleTEntity ste : zn.chain) {
//			    sb.append(ste.getTemp() + ",");
//			}
//			System.out.println(sb.toString());
//		    }

		//}
		
		//System.out.println(zn.root.buildConfig().toString(2));

	}

	@Test
	public void test() {
		
		

		StringBuilder sb = new StringBuilder();
		sb.append("Min,");
		for (SimpleTEntity ste : zn.getChain()) {
		    sb.append(ste.getFriendlyName() + ",");
		}
		System.out.println(sb.toString());
int loopCount=10000;
		for (int i = 0; i < loopCount; i++) {
			//set chain end temps
			zn.getRoot().setFixedTemp(getStartChainTemp(4000.0f, 0.0f, loopCount, i+1));
			zn.getChild61().setFixedTemp(getEndChainTemp(4000.0f, 0.0f, loopCount, i+1));
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
		fail("Not yet implemented");
	}
	
	public float getStartChainTemp(float maxTemp, float minTemp,int loopCount,int currentLoop) {
		float range= maxTemp-minTemp;
		float pos = (float)currentLoop/(float)loopCount;
		return (range*pos)+minTemp;
	}
	
	public float getEndChainTemp(float maxTemp, float minTemp,int loopCount,int currentLoop) {
		float range= maxTemp-minTemp;
		float pos = (float)currentLoop/(float)loopCount;
		return maxTemp-(range*pos);
	}
	

}
