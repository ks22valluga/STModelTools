package com.ks22valluga.app;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.ks22valluga.visualisation.ToggleSwitch;

import java.awt.Color;
import java.awt.Font;
import java.lang.IllegalArgumentException;;

public class HotWaterControlPanel extends JPanel {

	private static final long serialVersionUID = 7361278598887146934L;

	private ScenarioTypeEnum ste;
	private final String defaultSTE="Hot water bottom coil mid tank direct sensor";

	private ToggleSwitch toggleSwitch;
	private JPanel onOff1;
	private JLabel onOffLabel; 

	public HotWaterControlPanel(String typeString) {

		constructUI(typeString);
	}

	public ScenarioTypeEnum getSte() {
		return ste;
	}

	private void constructUI(String typeString) {
		this.invalidate();
		if(typeString==null||typeString.equals("")) {
			typeString=defaultSTE;
		}
		String[] names = ScenarioTypeEnum.getNames();
		boolean matched = false;
		int index = 0;
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(typeString)) {
				index = i;
				matched = true;
				break;
			}
		}
		if (!matched) {
			throw new IllegalArgumentException(typeString + " does not match an entry in ScenarioTypeEnum");
		}

		ste = (ScenarioTypeEnum.values()[index]);
		
		
		switch (ste) {
		case bottomCoilMidInvSensor: {
			// Setup control panel general
			setupPanelGeneral();
			
			
			//add on/off switch ,draw controls flow rate and turbulence, hot water coil, coil temp
			addOnOffControl();
			
			addDrawControls();
			
	

		}
			break;
		
		case bottomCoilTriInvSensor: {

		}
			break;
		
		
		case topImmersionMidInvSensor: {

		}
			break;
		
		case topImmersionTriInvSensor: {

		}
			break;
			default: {
			throw new IllegalArgumentException(
					"There is not a case match for ScenarioTypeEnum " + ste.name() + "," + ste.getTypeName());
		}
		}
	}



	private void addDrawControls() {
		
		Font font = new Font("Tahoma", Font.PLAIN, 11);
		
		JPanel drawPanel = new JPanel();
		drawPanel.setBounds(10, 92, 263, 80);
		drawPanel.setBorder(new LineBorder(Color.GRAY));
		drawPanel.setLayout(null);
		
		add(drawPanel);
		
		JSlider drawSlider = new JSlider();
		drawSlider.setSnapToTicks(true);
		drawSlider.setMinorTickSpacing(5);
		drawSlider.setMajorTickSpacing(10);
		drawSlider.setPaintLabels(true);
		drawSlider.setPaintTicks(true);
		drawSlider.setBounds(10, 25, 245, 44);
		drawSlider.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		drawSlider.setValue(0);
		
		drawPanel.add(drawSlider);
		
		JLabel drawLabel = new JLabel("Draw Hot Water Rate");
		drawLabel.setFont(font);
		drawLabel.setBounds(10, 11, 208, 14);
		
		drawPanel.add(drawLabel);
		
		
		
		JPanel drawTurbPanel = new JPanel();
		drawTurbPanel.setBounds(10, 186, 263, 80);
		drawTurbPanel.setLayout(null);
		drawTurbPanel.setBorder(new LineBorder(Color.GRAY));
		
		add(drawTurbPanel);
		
		JSlider drawTurbSlider = new JSlider();
		drawTurbSlider.setValue(0);
		drawTurbSlider.setSnapToTicks(true);
		drawTurbSlider.setPaintTicks(true);
		drawTurbSlider.setPaintLabels(true);
		drawTurbSlider.setMinorTickSpacing(5);
		drawTurbSlider.setMajorTickSpacing(10);
		drawTurbSlider.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		drawTurbSlider.setBounds(10, 25, 245, 44);
		
		drawTurbPanel.add(drawTurbSlider);
		
		JLabel drawTurbLabel = new JLabel("Draw turbulance");
		drawTurbLabel.setFont(font);
		drawTurbLabel.setBounds(10, 11, 117, 14);
		
		drawTurbPanel.add(drawTurbLabel);
		
	}

	private void setupPanelGeneral() {
		// Setup control panel general
		Border lineBorder = new LineBorder(Color.DARK_GRAY);
//		hotWaterControlPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hot Water Control", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		setBorder(new TitledBorder(lineBorder, "Hot Water Control", TitledBorder.LEADING, TitledBorder.TOP, null,
				Color.BLACK));
		setBounds(390, 190, 289, 280);
		setLayout(null);

	}
	
	private void addOnOffControl() {
		this.onOff1 = new JPanel();
		onOff1.setBounds(10, 23, 70, 58);
		onOff1.setBorder(new LineBorder(Color.GRAY));
		onOff1.setLayout(null);
		
		add(onOff1);
		
		this.onOffLabel = new JLabel("On/Off");
		onOffLabel.setBounds(10, 11, 46, 14);
		
		onOff1.add(onOffLabel);
		
		this.toggleSwitch = new ToggleSwitch();
		toggleSwitch.setBounds(10, 25, 46, 29);
		toggleSwitch.setBackground(Color.BLACK);
		toggleSwitch.setButtonColor(Color.WHITE);
		toggleSwitch.setActiveSwitch(Color.RED);
		toggleSwitch.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toggleSwitch.setLayout(null);
		
		onOff1.add(toggleSwitch);
	}

public void switchControls(String scenario) {

	constructUI(scenario);
	validate();
}

}
