package com.ks22valluga.visualisation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AppFrame {

	private JFrame frmStmodelvis;
	private JTextField currentTemperature;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame window = new AppFrame();
					window.frmStmodelvis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStmodelvis = new JFrame();
		frmStmodelvis.setTitle("STModelVis");
		frmStmodelvis.setResizable(false);
		frmStmodelvis.setBounds(100, 100, 450, 300);
		frmStmodelvis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStmodelvis.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 231, 443, 41);
		frmStmodelvis.getContentPane().add(panel_1);
		
		JLabel lblSelectedTemp = new JLabel("Selected Temp");
		panel_1.add(lblSelectedTemp);
		
		currentTemperature = new JTextField();
		currentTemperature.setText("null");
		
		panel_1.add(currentTemperature);
		currentTemperature.setColumns(10);
		
		int noOfCells=20;
		for(int i= 0;i<noOfCells;i++){
		TempColourPanel panel = new TempColourPanel();
		panel.setJtxtTempVal(currentTemperature);
		panel.setBounds(20+(i*15), 20, 15, 15);
		panel.setMinTemp(-5f);
		panel.setMaxTemp(30.0f);
		float factor = new Float(i);
		float temperature=10.0f+(factor/1);
		panel.setTemperature(temperature);
		
		Connector conn = new Connector();
		if(i==0){	
			conn.addConnector(ConnectorEnum.right);
		} else if(i==(noOfCells-1)){
			conn.addConnector(ConnectorEnum.left);			
		} else{
			conn.addConnector(ConnectorEnum.right);
			conn.addConnector(ConnectorEnum.left);
		}
		
		panel.setConnector(conn);
		frmStmodelvis.getContentPane().add(panel);
        }
		
	}
}
