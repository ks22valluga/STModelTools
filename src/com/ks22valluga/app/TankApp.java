package com.ks22valluga.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextField;

public class TankApp {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;

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
		

		
		textField1 = new JTextField();
		textField1.setBounds(x, tankPanel.getHeight()-(sensorPosSection*2)+tankPanel.getY(), w, h);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(x, tankPanel.getHeight()-(sensorPosSection*4)+tankPanel.getY(), w, h);
		frame.getContentPane().add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(x, tankPanel.getHeight()-(sensorPosSection*6)+tankPanel.getY(), w, h);
		frame.getContentPane().add(textField3);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(x, tankPanel.getHeight()-(sensorPosSection*8)+tankPanel.getY(), w, h);
		frame.getContentPane().add(textField4);
		
		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(x, tankPanel.getHeight()-(sensorPosSection*10)+tankPanel.getY(), w, h);
		frame.getContentPane().add(textField5);
		
		textField6 = new JTextField();
		textField6.setColumns(10);
		textField6.setBounds(x, tankPanel.getHeight()-(sensorPosSection*12)+tankPanel.getY(), w, h);
		frame.getContentPane().add(textField6);
		
		textField7 = new JTextField();
		textField7.setColumns(10);
		textField7.setBounds(x, tankPanel.getHeight()-(sensorPosSection*14)+tankPanel.getY(), w, h);
		frame.getContentPane().add(textField7);
	}
}
