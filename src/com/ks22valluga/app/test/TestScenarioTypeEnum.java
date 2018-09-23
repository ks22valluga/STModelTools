package com.ks22valluga.app.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ks22valluga.app.HotWaterControlPanel;

public class TestScenarioTypeEnum {
	
	
	 @Rule
	  public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testException() {
		
	    exception.expect(IllegalArgumentException.class);
		@SuppressWarnings("unused")
		HotWaterControlPanel hcp = new HotWaterControlPanel("dummy");
		


		
	//	bottomCoilMidInvSensor("Hot water bottom coil mid tank direct sensor"),
	//	HotWaterControlPanel hcp = new HotWaterControlPanel("Hot water bottom coil mid tank direct sensor");
	//	bottomCoilTrvInSensor("Hot water bottom coil TMB tank direct sensors");
	}
	
	@Test
	public void testPositive() {
		
	//	bottomCoilMidInvSensor("Hot water bottom coil mid tank direct sensor"),
		HotWaterControlPanel hcp = new HotWaterControlPanel("Hot water bottom coil mid tank direct sensor");
		String checkText =hcp.getSte().name();
		assertEquals("Check for correct enum name ","bottomCoilMidInvSensor", checkText);
	//	bottomCoilTrvInSensor("Hot water bottom coil TMB tank direct sensors");
		hcp = new HotWaterControlPanel("Hot water bottom coil TMB tank direct sensors");
		checkText =hcp.getSte().name();
		assertEquals("Check for correct enum name ","bottomCoilTriInvSensor", checkText);
		
		//bottomCoilTriNonInvSensor("Hot water bottom coil TMB tank, mid tank non invasive sensor"),
		hcp = new HotWaterControlPanel("Hot water bottom coil TMB tank, mid tank non invasive sensor");
		checkText =hcp.getSte().name();
		assertEquals("Check for correct enum name ","bottomCoilTriNonInvSensor", checkText);
	}

}
