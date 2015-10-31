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

import com.ks22valluga.thermal.SimpleTEntity;

public class TempColourPanel extends JPanel implements MouseMotionListener{
	private float minTemp=-20f;
	private float maxTemp=100f;
	private Color currentColor=new Color(0, 0, 0);
	private float currentTemp;
	private int connVal;
	private JTextField jtxtTempVal;
	private SimpleTEntity assocatedEntity;

	public SimpleTEntity getAssocatedEntity() {
		return assocatedEntity;
	}

	public void setAssocatedEntity(SimpleTEntity assocatedEntity) {
		this.assocatedEntity = assocatedEntity;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1270267253612995056L;
	
	public TempColourPanel(){
		addMouseMotionListener(this);
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
		//take float temp and convert to rgb
		//range 1  r=0,g=0,b=0 black to r=0,g=0,b=255 blue
		//range 2  r=0,g=0,b=255 blue to r=255,g=0,b=255 purple
		//range 3  r=255,g=0,b=255 purple to r=255,g=0,b=0 red
		//range 4  r=255,g=0,b=0 red to r=255,g=255,b=255 white
		if(temperature<=minTemp){
			currentColor=new Color(0, 0, 0);
		}else if(temperature>=maxTemp){
			currentColor=new Color(255, 255, 255);
		}else{
			float inputRange=maxTemp-minTemp;
			float outputRange=4*256;
			float ratio=temperature/inputRange;
			float targetFloat=ratio*outputRange;
			int targetInt = Math.round(targetFloat);
			if(targetInt<256){
				//range 1
				currentColor= new Color(0,0,targetInt);
			}else if(targetInt>(1*256)-1 && targetInt <2*256){
				//range 2
				int offsetTargetint=targetInt-(1*256);
				currentColor= new Color(offsetTargetint, 0, 255);
			}else if(targetInt>(2*256)-1 && targetInt <3*256){
				//range 3
				try {
					int offsetTargetint=255-(targetInt-(2*256));
					currentColor= new Color(255, 0, offsetTargetint);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}else{
				//range 4
				int offsetTargetint=targetInt-(3*256);
				currentColor= new Color(255, offsetTargetint, offsetTargetint);
			}
			
		}
		
	}

	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
	}

	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
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


	
	

}
