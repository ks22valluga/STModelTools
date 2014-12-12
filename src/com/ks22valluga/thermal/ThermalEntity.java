package com.ks22valluga.thermal;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.ks22valluga.util.NameRegistry;
import com.ks22valluga.util.UniqueCodeGenException;

/**
 * The operation of each entity is as follows.<p>
 * Starting from a root node (which would generally be a heat or cooling source) the current node assesses 
 * calculated heat gain or loss (or no change input heat), Compares this values with its current state and with respect to its direct dependants.
 * The entity then loads heat gain/loss data into its dependants.
 * The thread running the calculation recursively repeats for all nested entities.<p>
 * 
 * An entity can be used in multiple overlapping trees to allow simulation of the effect of multiple heat source/loss.<p>
 *  
 * <ul>
 * <li>A heating root entity will have low insulation, high capacity and temp range 30000 to 90000</li>
 * <li>A cooling root entity will have low insulation, high capacity and temp range -40000 to 10000</li>
 * <li>An external world entity will have mid to high (variable by scenario) insulation, infinite capacity and wide temp range. -20000 to +45000</li> 
 * </ul>
 * @author kevins
 *
 */
public class ThermalEntity {
private int currentTemp;  //* 1000 , 12.5degc = 12500 i.e. allows +/- 0.001 degC resolution
private int contactEfficiency; // (to parent) 1-1000, 0 is perfect insulation (no point in being used) 100 is perfect conductor
private int capacity; // 0-100 defines rate of temperature increase/decrease for given temperature differencial.  
private String displayName;
private ThermalEntity parentEntity;
private boolean isHeatCoolSource;
private GregorianCalendar lastUpdate;





public GregorianCalendar getLastUpdate() {
	return lastUpdate;
}


public void setLastUpdate(GregorianCalendar lastUpdate) {
	this.lastUpdate = lastUpdate;
}


private String uniqueName;
private ArrayList<ThermalEntity> dependantEntities;



public ThermalEntity(int currentTemp, int contactEfficiency, int capacity,
		String displayName, ThermalEntity parentEntity) {

	this.currentTemp = currentTemp;
	this.contactEfficiency = contactEfficiency;
	this.capacity = capacity;
	this.displayName = displayName;
	this.parentEntity = parentEntity;
	this.isHeatCoolSource = (parentEntity==null);
	this.lastUpdate = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
	this.setUniqueName();
	
}


public String getUniqueName() {
	return uniqueName;
}


public void setUniqueName() {
	try {
		this.uniqueName = NameRegistry.getUniqueName();
	} catch (UniqueCodeGenException e) {
		
		e.printStackTrace();
	}
}




public boolean isHeatCoolSource() {
	return isHeatCoolSource;
}


public void setHeatCoolSource(boolean isHeatCoolSource) {
	this.isHeatCoolSource = isHeatCoolSource;
}


public ThermalEntity(ThermalEntity parentEntity, int initialContactTemp, int initial ){
	if (parentEntity==null){
		this.setHeatCoolSource(true);
	}
	
}


public String getDisplayName() {
	return displayName;
}


public void setDisplayName(String displayName) {
	this.displayName = displayName;
}


public int getCurrentTemp() {
	return currentTemp;
}
public void setCurrentTemp(int currentTemp) {
	this.currentTemp = currentTemp;
}
public int getContactEfficiency() {
	return contactEfficiency;
}
public void setContactEfficiency(int contactEfficiency) {
	this.contactEfficiency = contactEfficiency;
}
public int getCapacity() {
	return capacity;
}
public void setCapacity(int capacity) {
	this.capacity = capacity;
}

public ThermalEntity getParentEntity() {
	return parentEntity;
}
public void setParentEntity(ThermalEntity parentEntity) {
	this.parentEntity = parentEntity;
}


public void addDependantEntity(ThermalEntity depEntity){
	if(!this.dependantEntities.contains(depEntity)){
	this.dependantEntities.add(depEntity);
}
}

public void removeDependantEntity(ThermalEntity depEntity){
	if(this.dependantEntities.contains(depEntity)){
		this.dependantEntities.remove(depEntity);
	}
}

public void addHeat(int heat, GregorianCalendar updateTime){
	long timeDiff=0;
	if(updateTime.after(lastUpdate)){
	timeDiff=(updateTime.getTimeInMillis()-lastUpdate.getTimeInMillis());
	timeDiff/=(100*60);
	int cycles=(int)timeDiff;
	for (int i=0;i<cycles;i++){
		
	}
	}
	
}
	
	
public int heatResult(int heat1, int heat2){

	return 0;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((uniqueName == null) ? 0 : uniqueName.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ThermalEntity other = (ThermalEntity) obj;
	if (uniqueName == null) {
		if (other.uniqueName != null)
			return false;
	} else if (!uniqueName.equals(other.uniqueName))
		return false;
	return true;
}





}
