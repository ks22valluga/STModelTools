package com.ks22valluga.visualisation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;

public class AppFrame {

	private JFrame frmStmodelvis;

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
		/*
		MyPanel canvas = new MyPanel();
		canvas.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		canvas.setFont(UIManager.getFont("FileChooser.listFont"));
		canvas.setBackground(Color.LIGHT_GRAY);
		canvas.setForeground(Color.WHITE);
		canvas.setBounds(0, 0, 443, 200);
		frmStmodelvis.getContentPane().add(canvas);
		*/
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.WHITE);
		textPane.setEditable(false);
		textPane.setBounds(0, 200, 443, 70);
		frmStmodelvis.getContentPane().add(textPane);
		
		int noOfCells=20;
		for(int i= 0;i<noOfCells;i++){
		TempColourPanel panel = new TempColourPanel();
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
