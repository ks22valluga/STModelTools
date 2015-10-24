package com.ks22valluga.visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class TempColourPanel extends JPanel {
	private float minTemp=-20f;
	private float maxTemp=100f;
	private Color currentColor=new Color(0, 0, 0);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1270267253612995056L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Dimension dimen=this.getSize();
		int height =(int) dimen.getHeight();
		int width=(int) dimen.getWidth();
		g2d.setColor(currentColor);
		g2d.fillRect(0, 0, width, height);
	}
	
	public void setTemperature(float temperature){
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

}
