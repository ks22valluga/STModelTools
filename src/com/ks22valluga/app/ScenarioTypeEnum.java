package com.ks22valluga.app;

import java.util.ArrayList;

public enum ScenarioTypeEnum {

	bottomCoilMidInvSensor("Hot water bottom coil mid tank direct sensor"),
	topImmersionMidInvSensor("ImmersionElement top, mid tank direct sensor"),
	bottomCoilTriInvSensor("Hot water bottom coil TMB tank direct sensors"),
	topImmersionTriInvSensor("ImmersionElement top, TMB tank direct sensor");
	
	private String typeName;

	private ScenarioTypeEnum(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public static String[] getNames() {
		ArrayList<String> nameValues = new ArrayList<>();
		for (ScenarioTypeEnum ste : ScenarioTypeEnum.values()) {
			nameValues.add(ste.typeName);
		}
		return nameValues.toArray(new String[nameValues.size()]);
	}
	

}