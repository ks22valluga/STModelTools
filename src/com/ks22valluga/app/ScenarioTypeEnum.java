package com.ks22valluga.app;

import java.util.ArrayList;

public enum ScenarioTypeEnum {

	bottomCoilMidInvSensor("Hot water bottom coil mid tank direct sensor"),
	topImmersionMidInvSensor("ImmersionElement top, mid tank direct sensor"),
	bottomCoilMidNonInvSensor("Hot water bottom coil mid tank, non invasive sensor"),
	topImmersionMidNonInvSensor("ImmersionElement top, mid tank non invasive sensor"),
	bottomCoilTriInvSensor("Hot water bottom coil TMB tank direct sensors"),
	topImmersionTriInvSensor("ImmersionElement top, TMB tank direct sensor"),
	bottomCoilTriNonInvSensor("Hot water bottom coil TMB tank, non invasive sensor"),
	topImmersionTriNonInvSensor("ImmersionElement top, TMB tank non invasive sensor");

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