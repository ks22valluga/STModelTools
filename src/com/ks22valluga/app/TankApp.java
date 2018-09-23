package com.ks22valluga.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JSlider;
import javax.swing.JLabel;
import com.ks22valluga.visualisation.ToggleSwitch;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TankApp {

	private JFrame frmHotWaterTank;
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
					window.frmHotWaterTank.setVisible(true);
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
		frmHotWaterTank = new JFrame();
		frmHotWaterTank.setTitle("Hot Water Tank Simulation");
		frmHotWaterTank.setVisible(true);
		frmHotWaterTank.setBounds(10, 10, 705, 650);
		frmHotWaterTank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHotWaterTank.getContentPane().setLayout(null);
		
		
		int x=10;
		int w=55;
		int h=20;
		
		TankPanel tankPanel = new TankPanel();
		tankPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tankPanel.setBackground(new Color(204, 204, 204));
		tankPanel.setBounds(x+w+10, 40, 80, 500);
		frmHotWaterTank.getContentPane().add(tankPanel);
		
		int sensorPosSection = tankPanel.getHeight()/15;
		

		
		textField1 = new TextFieldCentigrade();
		textField1.setText("txt1");
		textField1.setBorder(new LineBorder(Color.GRAY));
		int textField1Ypos=tankPanel.getHeight()-(sensorPosSection*2)+tankPanel.getY();
		textField1.setBounds(x, textField1Ypos, w, h);
		frmHotWaterTank.getContentPane().add(textField1);
		
		textField2 = new TextFieldCentigrade();
		textField2.setBorder(new LineBorder(Color.GRAY));
		textField2.setText("txt2");
		int textField2Ypos=tankPanel.getHeight()-(sensorPosSection*4)+tankPanel.getY();
		textField2.setBounds(x, textField2Ypos, w, h);
		frmHotWaterTank.getContentPane().add(textField2);
		
		textField3 = new TextFieldCentigrade();
		textField3.setBorder(new LineBorder(Color.GRAY));
		textField3.setText("txt3");
		int textField3Ypos=tankPanel.getHeight()-(sensorPosSection*6)+tankPanel.getY();
		textField3.setBounds(x, textField3Ypos, w, h);
		frmHotWaterTank.getContentPane().add(textField3);
		
		textField4 = new TextFieldCentigrade();
		textField4.setText("txt4");
		textField4.setBorder(new LineBorder(Color.GRAY));
		int textField4Ypos=tankPanel.getHeight()-(sensorPosSection*8)+tankPanel.getY();
		textField4.setBounds(x, textField4Ypos, w, h);
		frmHotWaterTank.getContentPane().add(textField4);
		
		textField5 = new TextFieldCentigrade();
		textField5.setText("txt5");
		textField5.setBorder(new LineBorder(Color.GRAY));
		int textField5Ypos=tankPanel.getHeight()-(sensorPosSection*10)+tankPanel.getY();
		textField5.setBounds(x, textField5Ypos, w, h);
		frmHotWaterTank.getContentPane().add(textField5);
		
		textField6 = new TextFieldCentigrade();
		textField6.setText("txt6");
		textField6.setBorder(new LineBorder(Color.GRAY));
		int textField6Ypos=tankPanel.getHeight()-(sensorPosSection*12)+tankPanel.getY();
		textField6.setBounds(x, textField6Ypos, w, h);
		frmHotWaterTank.getContentPane().add(textField6);
		
		textField7 = new TextFieldCentigrade();
		textField7.setText("txt7");
		textField7.setBorder(new LineBorder(Color.GRAY));
		int textField7Ypos=tankPanel.getHeight()-(sensorPosSection*14)+tankPanel.getY();
		textField7.setBounds(x, textField7Ypos, w, h);
		frmHotWaterTank.getContentPane().add(textField7);
		

		HotWaterControlPanel hotWaterControlPanel = new HotWaterControlPanel("Hot water bottom coil mid tank direct sensor");
		hotWaterControlPanel.setLocation(390, 106);

		frmHotWaterTank.getContentPane().add(hotWaterControlPanel);
		
		
		JPanel scenarioSelectPanel = new JPanel();
		scenarioSelectPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Scenario Selector", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scenarioSelectPanel.setBounds(390, 40, 278, 55);
		frmHotWaterTank.getContentPane().add(scenarioSelectPanel);
		scenarioSelectPanel.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(comboBox.getSelectedItem());
				//frmHotWaterTank.getContentPane().remove(hotWaterControlPanel);
				//HotWaterControlPanel hotWaterControlPanel = new HotWaterControlPanel((String)comboBox.getSelectedItem());
				//hotWaterControlPanel.setLocation(390, 106);
                 hotWaterControlPanel.switchControls((String)comboBox.getSelectedItem());
                 hotWaterControlPanel.invalidate();
                 hotWaterControlPanel.setLocation(390, 106);
				//frmHotWaterTank.getContentPane().add(hotWaterControlPanel);
				//frmHotWaterTank.getContentPane().repaint();
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel<String>(ScenarioTypeEnum.getNames()));
		comboBox.setBounds(10, 22, 262, 20);
		scenarioSelectPanel.add(comboBox);
		
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
		switch(i) {
		case 1:{
			textField1.setValue(val);
		}break;
		case 2:{
			textField2.setValue(val);
		}break;
		case 3:{
			textField3.setValue(val);
		}break;
		case 4:{
			textField4.setValue(val);
		}break;
		case 5:{
			textField5.setValue(val);
		}break;
		case 6:{
			textField6.setValue(val);
		}break;
		case 7:{
			textField7.setValue(val);
		}break;
		}
		
	}
}
