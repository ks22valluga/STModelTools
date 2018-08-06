package com.ks22valluga.examples;

import java.util.ArrayList;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class Tank {

	private SimpleTEntity root;
	private ArrayList<SimpleTEntity> tankBodyElements;
	private ArrayList<SimpleTEntity> nonInvasiveSensorArray;
	private SimpleTEntity heater;
	private SimpleTEntity surfaceNIS;
	private SimpleTEntity ambientNIS;
	private SimpleTEntity sensorTankAnchorPoint;
	private boolean bottomHeating;
	private int percentFromTopHeater;
	private float heaterTemp;
	private boolean heaterState;
	private boolean drawWaterState;
	private int drawWaterVolume;

	public static void main(String[] args) {

		int noOfTankSegments = 1000;
		float initialTankSegmentTemps = 1900f;
		float initialSensorTemps = 1900f;
		int percentFromTopHeater = 25;

		// Bottom heating
		Tank zn = new Tank(noOfTankSegments, initialTankSegmentTemps,
				initialSensorTemps);

		// Variable level heating
		// int percentFromTop=25;
		// Tank zn = new
		// Tank(noOfTankSegments,initialTankSegmentTemps,initialSensorTemps,percentFromTop);

		// tank construction
		// body of tank 1 chain (chain1) [done]
		// gas heater/deep immersion element at final tank chain [done]
		// top up immersion at top quarter of tank [done]
		// chain comprising tank insulation,surface,insulation,ambient temp
		// [done]

		// tank lifetime

		// no draw, normal mass/temp/conductance causes de-layering slowly
		// [done, implicit in use of SimpleTEntity I think]

		// to draw water ,re-create tankElements with top elements removed
		// greater number per unit time for flow rate
		// replace removed elements at bottom of chain with cold water
		float coldWaterTemp = 1000f;
		int unitsPerTimePeriod = 20;
		zn.drawWaterConfigure(unitsPerTimePeriod, true);

		// heat, water mixing of all tank elements
		float heaterTemp = 5500f;
		zn.heatTankConfigure(heaterTemp, true);

		zn.operateCycle();

	}

	public void operateCycle() {
		// process draw
		if(drawWaterState){
			doDraw();
		}
		// process heating
		if(heaterState){
			doHeat();
		}
		// relocate heater
		relocateHeater();
		// relocate sensor
		relocateSensor();
		//update from root

	}

	private void relocateSensor() {
		// TODO Auto-generated method stub
		
	}

	private void doHeat() {
		// TODO Auto-generated method stub
		
	}

	private void doDraw() {
		// TODO Auto-generated method stub
		
	}

	public void heatTankConfigure(float heaterTemp, boolean state) {
		this.heaterTemp = heaterTemp;
		this.heaterState = state;
	}

	public void drawWaterConfigure(int unitsPerTimePeriod, boolean state) {
		this.drawWaterState = state;
		this.drawWaterVolume = unitsPerTimePeriod;
		// create new body array
		// 1st new body element = index unitsPerTimePeriod of old
		// after all old elements used add new elements as per coldWaterTemp
		// re attach heater and sensor

	}

	public Tank(int noOfTankElements, float initialTemp,
			float defaultTempsSensorArray) {

		// construct

		// TODO tank element defaults to be tuned for slow delayering
		tankBodyElements = new ArrayList<>(noOfTankElements);
		float defaultMass = 100f;
		float defaultConductance = 1f;
		SimpleTEntity root = new SimpleTEntity(null, defaultMass, initialTemp,
				defaultConductance, null);
		root.setIAmARoot(true);
		tankBodyElements.add(root);
		for (int i = 1; i < noOfTankElements - 1; i++) {
			SimpleTEntity tankElement = new SimpleTEntity(null, defaultMass,
					initialTemp, defaultConductance, null);
			tankElement.setParent(tankBodyElements.get(i - 1));
			tankElement.addChild(tankElement);
		}

		nonInvasiveSensorArray = getNonInvasiveSensorArray();
		heater = new SimpleTEntity(null, defaultMass, initialTemp,
				defaultConductance, "heater");
		relocateTankSensorAttachPointMid();
		relocateHeater();
		this.percentFromTopHeater = 0;
		this.bottomHeating = true;
	}

	public Tank(int noOfTankElements, float initialTemp,
			float defaultTempsSensorArray, int percentFromTopHeater) {
		this(noOfTankElements, initialTemp, defaultTempsSensorArray);

		this.percentFromTopHeater = percentFromTopHeater;
		this.bottomHeating = false;
	}

	private ArrayList<SimpleTEntity> getNonInvasiveSensorArray() {
		ArrayList<SimpleTEntity> nonInvasiveSensorArrayInit = new ArrayList<>();
		// chain comprising tank insulation,surface,more insulation,ambient temp

		// TODO: tune the array the figures entered are very much defaults

		SimpleTEntity tankInsulation, tankSurface, sensorInsulation, ambientTemperature;
		tankInsulation = new SimpleTEntity(null, 100, 1900f, 1,
				"TankInsulation"); // parent has high conductance
		nonInvasiveSensorArrayInit.add(tankInsulation);
		tankSurface = new SimpleTEntity(tankInsulation, 100, 1900f, 1,
				"TankSurface"); // parent has low conductance
		nonInvasiveSensorArrayInit.add(tankSurface);
		sensorInsulation = new SimpleTEntity(tankSurface, 100, 1900f, 1,
				"SensorInsulation"); // parent has high conductance
		nonInvasiveSensorArrayInit.add(sensorInsulation);
		ambientTemperature = new SimpleTEntity(sensorInsulation, 100, 1900f, 1,
				"AmbientTemperature"); // parent has low conductance
		nonInvasiveSensorArrayInit.add(ambientTemperature);
		return nonInvasiveSensorArrayInit;
	}

	private void relocateHeater() {
		SimpleTEntity parentOfHeater;
		if (bottomHeating) {
			int lastElement = tankBodyElements.size() - 1;
			parentOfHeater = tankBodyElements.get(lastElement);
			heater.setParent(parentOfHeater);
		} else {
			int positionElement = tankBodyElements.size()
					/ (100 / percentFromTopHeater);
			parentOfHeater = tankBodyElements.get(positionElement);
			heater.setParent(parentOfHeater);
		}

	}

	private void relocateTankSensorAttachPointMid() {
		setSensorTankAttachPoint(50);
	}

	private void setSensorTankAttachPoint(int percentFromTop) {
		int attachValue = tankBodyElements.size() / (100 / percentFromTop);
		this.sensorTankAnchorPoint = tankBodyElements.get(attachValue);

	}

	public SimpleTEntity getRoot() {
		return root;
	}

	public ArrayList<SimpleTEntity> getTankBodyElements() {
		return tankBodyElements;
	}

	public SimpleTEntity getHeater() {
		return heater;
	}

	public SimpleTEntity getSurfaceNIS() {
		return surfaceNIS;
	}

	public SimpleTEntity getAmbientNIS() {
		return ambientNIS;
	}

	public SimpleTEntity getSensorTankAnchorPoint() {
		return sensorTankAnchorPoint;
	}

	public SimpleTEntity getPocketSensor() {
		return sensorTankAnchorPoint;
	}

	public boolean isBottomHeating() {
		return bottomHeating;
	}

	public int getPercentFromTopHeater() {
		return percentFromTopHeater;
	}

}
