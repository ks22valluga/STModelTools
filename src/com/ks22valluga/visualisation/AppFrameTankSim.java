package com.ks22valluga.visualisation;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import com.ks22valluga.thermal.SimpleTEntity;
import com.ks22valluga.util.STTimer;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AppFrameTankSim {

	private JFrame frmStmodelvis;
	private JTextField currentTemperature;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrameTankSim window = new AppFrameTankSim();
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
	public AppFrameTankSim() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStmodelvis = new JFrame();
		frmStmodelvis.setTitle("STModelVis");
		frmStmodelvis.setResizable(true);
		frmStmodelvis.setBounds(10, 10, 400,650);
		frmStmodelvis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStmodelvis.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 231, 443, 41);
		frmStmodelvis.getContentPane().add(panel_1);

		JLabel lblSelectedTemp = new JLabel("Selected Temperature");
		panel_1.add(lblSelectedTemp);

		currentTemperature = new JTextField();
		currentTemperature.setText("null");

		panel_1.add(currentTemperature);
		currentTemperature.setColumns(10);
		STTimer sttPanelUpdate = new STTimer(0, 100);
		STTimer sttModelUpdate = new STTimer(0, 100);

		// build simple chain
		int noOfCells = 20;
		ArrayList<TempColourPanel> altcp = new ArrayList<TempColourPanel>();
		for (int i = 0; i < noOfCells; i++) {
			TempColourPanel panel = new TempColourPanel();
			panel.setAssocatedEntity(new SimpleTEntity(null, 200, 0, 99, ""));
			altcp.add(panel);
			sttPanelUpdate.addTimerActivity(panel);
			panel.setJtxtTempVal(currentTemperature);
			panel.setBounds(20 + (i * 15), 20, 15, 15);
			panel.setMinTemp(5f);
			panel.setMaxTemp(50.0f);
			float factor = new Float(i);
			float temperature = 10.0f + (factor / 1);
			panel.setTemperature(temperature);

			Connector conn = new Connector();
			if (i == 0) {
				conn.addConnector(ConnectorEnum.right);
			} else if (i == (noOfCells - 1)) {
				conn.addConnector(ConnectorEnum.left);
				conn.addConnector(ConnectorEnum.top);
			} else {
				conn.addConnector(ConnectorEnum.right);
				conn.addConnector(ConnectorEnum.left);
			}

			panel.setConnector(conn);
			frmStmodelvis.getContentPane().add(panel);
		}

		// build simple chain
		SimpleTEntity rootNode = altcp.get(0).getAssocatedEntity();
		rootNode.setIAmARoot(true);
		rootNode.setFixedTemp(true);
		rootNode.setTemp(50);
		sttModelUpdate.addTimerActivity(rootNode);

		for (int i = 1; i < altcp.size(); i++) {
			SimpleTEntity currentSTE = altcp.get(i).getAssocatedEntity();
			SimpleTEntity parentSTE = altcp.get(i - 1).getAssocatedEntity();
			currentSTE.setFriendlyName("ch-link-" + i);
			currentSTE.setTemp(5.0f);
			currentSTE.setParent(parentSTE);
			parentSTE.addChild(currentSTE);
			// add a fixed temp node at end
			if (i == (altcp.size() - 1)) {
				currentSTE.setFixedTemp(true);
				currentSTE.setMass(100000000000f);
			}
		}
		// add a fixed temp node at end#

		sttPanelUpdate.start();
		sttModelUpdate.start();

	}
}
