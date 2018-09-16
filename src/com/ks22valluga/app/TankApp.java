package com.ks22valluga.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextField;

public class TankApp {

	private JFrame frame;
	private TextFieldCentigrade textField1;
	private TextFieldCentigrade textField2;
	private TextFieldCentigrade textField3;
	private TextFieldCentigrade textField4;
	private TextFieldCentigrade textField5;
	private TextFieldCentigrade textField6;
	private TextFieldCentigrade textField7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TankApp window = new TankApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TankApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(10, 10, 705, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		int x=10;
		int w=55;
		int h=20;
		
		TankPanel tankPanel = new TankPanel();
		tankPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tankPanel.setBackground(new Color(204, 204, 204));
		tankPanel.setBounds(x+w+10, 40, 80, 500);
		frame.getContentPane().add(tankPanel);
		
		int sensorPosSection = tankPanel.getHeight()/15;
		

		
		textField1 = new TextFieldCentigrade();
		int textField1Ypos=tankPanel.getHeight()-(sensorPosSection*2)+tankPanel.getY();
		textField1.setBounds(x, textField1Ypos, w, h);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new TextFieldCentigrade();
		textField2.setColumns(10);
		int textField2Ypos=tankPanel.getHeight()-(sensorPosSection*4)+tankPanel.getY();
		textField2.setBounds(x, textField2Ypos, w, h);
		frame.getContentPane().add(textField2);
		
		textField3 = new TextFieldCentigrade();
		textField3.setColumns(10);
		int textField3Ypos=tankPanel.getHeight()-(sensorPosSection*6)+tankPanel.getY();
		textField3.setBounds(x, textField3Ypos, w, h);
		frame.getContentPane().add(textField3);
		
		textField4 = new TextFieldCentigrade();
		textField4.setColumns(10);
		int textField4Ypos=tankPanel.getHeight()-(sensorPosSection*8)+tankPanel.getY();
		textField4.setBounds(x, textField4Ypos, w, h);
		frame.getContentPane().add(textField4);
		
		textField5 = new TextFieldCentigrade();
		textField5.setColumns(10);
		int textField5Ypos=tankPanel.getHeight()-(sensorPosSection*10)+tankPanel.getY();
		textField5.setBounds(x, textField5Ypos, w, h);
		frame.getContentPane().add(textField5);
		
		textField6 = new TextFieldCentigrade();
		textField6.setColumns(10);
		int textField6Ypos=tankPanel.getHeight()-(sensorPosSection*12)+tankPanel.getY();
		textField6.setBounds(x, textField6Ypos, w, h);
		frame.getContentPane().add(textField6);
		
		textField7 = new TextFieldCentigrade();
		textField7.setColumns(10);
		int textField7Ypos=tankPanel.getHeight()-(sensorPosSection*14)+tankPanel.getY();
		textField7.setBounds(x, textField7Ypos, w, h);
		frame.getContentPane().add(textField7);
		
		ArrayList<Integer> positions = new ArrayList<>();
		positions.add(textField1Ypos*2);
		positions.add(textField2Ypos*2);
		positions.add(textField3Ypos*2);
		positions.add(textField4Ypos*2);
		positions.add(textField5Ypos*2);
		positions.add(textField6Ypos*2);
		positions.add(textField7Ypos*2);
		
		ArrayList<Float> temps = tankPanel.getSensorValues(positions);
//		temps.add(new Float("5.34"));
//		temps.add(new Float("6.34"));
//		temps.add(new Float("7.34"));
//		temps.add(new Float("8.34"));
//		temps.add(new Float("9.34"));
//		temps.add(new Float("10.34"));
//		temps.add(new Float("11.34"));
		
		updateTemperatureReadings(temps);
	}
	
	public void updateTemperatureReadings(ArrayList<Float> temps ) {
		for(int i=1;i<8;i++) {
			setTemp(i,temps.get(i-1));
		}
	}

	private void setTemp(int i, float val) {
		BigDecimal bigDecimal=new BigDecimal(val);
		String value = bigDecimal.setScale(2, RoundingMode.HALF_UP).toString();
		switch(i) {
		case 1:{
			textField1.setText(value);
		}break;
		case 2:{
			textField2.setText(value);
		}break;
		case 3:{
			textField3.setText(value);
		}break;
		case 4:{
			textField4.setText(value);
		}break;
		case 5:{
			textField5.setText(value);
		}break;
		case 6:{
			textField6.setText(value);
		}break;
		case 7:{
			textField7.setText(value);
		}break;
		}
		
	}
}
