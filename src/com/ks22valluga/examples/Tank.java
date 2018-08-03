package com.ks22valluga.examples;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class Tank {

    private SimpleTEntity root;
    private ArrayList<SimpleTEntity> tankBodyElements;
    private ArrayList<SimpleTEntity> nonInvasiveSensorArray = new ArrayList<>();
    private int noOfNISArraElements=5;
    private SimpleTEntity heater;
    private SimpleTEntity surfaceNIS;
    private SimpleTEntity ambientNIS;
    private SimpleTEntity sensorTankAnchorPoint;
    private boolean bottomHeating;

    public static void main(String[] args) {

	boolean bottomHeating=true;
	int noOfTankSegments=1000;
	float initialTankSegmentTemps=1900f;
	float initialSensorTemps=1900f;
	Tank zn = new Tank(noOfTankSegments,initialTankSegmentTemps,initialSensorTemps,bottomHeating);
	// tank construction
	//body of tank 1 chain (chain1)
	//gas heater/deep immersion element at final tank chain
	//top up immersion at top quarter of tank 
	// chain comprising tank insulation,surface,insulation,ambient temp 
	
	//tank lifetime 
	
	//no draw, normal mass/temp/conductance causes de-layering slowly
	
	//to draw water ,re-create tankElements with top elements removed greater number per unit time for flow rate
	//replace removed elements at bottom of chain with cold water
	
	
	//heat, water mixing of all tank elements



    }

    public Tank(int noOfTankElements,float initialTemp,float defaultTempsSensorArray,boolean bottomHeating) {
	
	//construct 
	tankBodyElements = new ArrayList<>(noOfTankElements);
	float defaultMass=100f;
	float defaultConductance=1f;
	SimpleTEntity root = new SimpleTEntity(null, defaultMass, initialTemp, defaultConductance, null);
	root.setIAmARoot(true);
	tankBodyElements.add(root);
	for(int i=1;i<noOfTankElements-1;i++ ){
	    SimpleTEntity  tankElement = new SimpleTEntity(null, defaultMass, initialTemp, defaultConductance, null);
	    tankElement.setParent(tankBodyElements.get(i-1));
	    tankElement.addChild(tankElement);
	}
	
	nonInvasiveSensorArray = new ArrayList<>(noOfNISArraElements);
	relocateTankSensorAttachPoint();
	relocateHeater();
	
	
	
	
	

    }

    private void relocateHeater() {
	// TODO Auto-generated method stub
	
    }

    private void relocateTankSensorAttachPoint() {
	setSensorTankAttachPoint();
	
	
    }

    private void setSensorTankAttachPoint() {
	int midPointValue=tankBodyElements.size()/2;
	this.sensorTankAnchorPoint=tankBodyElements.get(midPointValue);
	
    }

}
