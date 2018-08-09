package com.ks22valluga.visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ks22valluga.util.GenerateCodes;
import com.ks22valluga.util.TimerActivity;
import com.ks22valluga.thermal.SimpleTEntity;

public class TempColourPanel extends JPanel implements MouseMotionListener, TimerActivity{
	private float minTemp=-20f;
	private float maxTemp=100f;
	private Color currentColor=new Color(0, 0, 0);
	private ColorTemp ct;
	private float currentTemp;
	private int connVal;
	private JTextField jtxtTempVal;
	private SimpleTEntity assocatedEntity;

	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1270267253612995056L;
	
	public TempColourPanel(){
		addMouseMotionListener(this);
		assocatedEntity = new SimpleTEntity(null, 500, 0, 5,"root");
	}
	
	public TempColourPanel(SimpleTEntity ste){
		this();
		setAssocatedEntity(ste);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Dimension dimen=this.getSize();
		int height =(int) dimen.getHeight();
		int width=(int) dimen.getWidth();
		g2d.setColor(currentColor);
		g2d.fillRect(1, 1, width-2, height-2);
		g2d.setColor(Color.black);

	    //this.setBorder(BorderFactory.createLineBorder(Color.black));
		//top=1 
		//right=2
		//bottom=4
		//left=8
		if((this.connVal&1)==1){
			//set top connector
			g2d.fillRect((width/2)+1, 0, 1, 1);
			//g2d.fillRect(width, (height/2)+1, 1, 1);
		}
		if((this.connVal&2)==2){
			//set right connector
			g2d.fillRect(width-1, (height/2)+1, 1, 1);

		}
		if((this.connVal&4)==4){
			//set bottom connector
			g2d.fillRect((width/2)+1, height-1, 1, 1);
		}
		if((this.connVal&8)==8){
			//set left connector
			g2d.fillRect(0, (height/2)+1, 1, 1);
		}
	}
	
	public void setTemperature(float temperature){
		this.currentTemp=temperature;
		this.currentColor=ct.getColorForTemp(temperature);
	}
	
	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
		 ct=new ColorTemp(minTemp, maxTemp);
	}

	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
		 ct=new ColorTemp(minTemp, maxTemp);
	}
	public void setConnector(Connector conn){
		this.connVal=conn.getConnectorBitMapValue();
	
	}

	public void setJtxtTempVal(JTextField jtxtTempVal) {
		this.jtxtTempVal = jtxtTempVal;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		this.jtxtTempVal.setText(""+this.currentTemp+"degC");
		
	}

	@Override
	public void fireTimerEvent() {
		// TODO Auto-generated method stub
		//setTemperature(new Float(GenerateCodes.generateRandomInt(0, 30)).floatValue());
		setTemperature(assocatedEntity.getTemp());
		this.repaint();
		
	}


	
	public SimpleTEntity getAssocatedEntity() {
		return assocatedEntity;
	}

	public void setAssocatedEntity(SimpleTEntity assocatedEntity) {
		this.assocatedEntity = assocatedEntity;
	}


}
