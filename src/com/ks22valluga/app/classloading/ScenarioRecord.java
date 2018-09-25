package com.ks22valluga.app.classloading;

public class ScenarioRecord {
private String name;
public ScenarioRecord(String name, String className) {
	this.name = name;
	this.className = className;
}
public String getName() {
	return name;
}
public String getClassName() {
	return className;
}
private String className;
}
