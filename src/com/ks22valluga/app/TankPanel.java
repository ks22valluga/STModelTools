package com.ks22valluga.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.ks22valluga.thermal.SimpleTEntity;
import com.ks22valluga.visualisation.ColorTemp;

public class TankPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2599715167305025815L;
	private ArrayList<SimpleTEntity> entities;
	private float minTemp=0f;
	private float maxTemp=60f;
	private Color currentColor=new Color(0, 0, 0);
	private ColorTemp ct;

	/**
	 * Create the panel.
	 */
	public TankPanel() {
		ct=new ColorTemp(minTemp, maxTemp);
		this.entities =loadTestEntities();
	}
	
	 @Override
     public void paintComponent(Graphics g) {
        super.paintComponent(g);     // paint parent's background
        setBackground(new Color(204,204,204));  // set background color for this JPanel

        // Your custom painting codes. For example,
        // Drawing primitive shapes
  
        for(int i=0;i<this.getHeight();i++){
        g.setColor(getColorForLine(i));    // set the drawing color
        g.drawLine(0,i, this.getWidth(),i);
        }
        
//        g.drawOval(150, 180, 10, 10);
//        g.drawRect(200, 210, 20, 30);
//        g.setColor(Color.RED);       // change the drawing color
//        g.fillOval(300, 310, 30, 50);
//        g.fillRect(400, 350, 60, 50);
//        // Printing texts
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
//        g.drawString("Testing custom drawing ...", 10, 20);
     }
	 
	 
	 public Color getColorForLine(int line){
		 // hack assumes tank =1000 entities and 500 pixels
		 int startLine=line*2;
		 int secondLine=startLine+1;
		 float startLineFlt=this.entities.get(startLine).getTemp();
		 float secondLineFlt=this.entities.get(secondLine).getTemp();
		 return ct.getColorForTemp((startLineFlt+secondLineFlt)/2);
	 }
	 
	 public void loadTemperatures(ArrayList<SimpleTEntity> entities){
		 this.entities=entities;
	 }
	 
	 private ArrayList<SimpleTEntity> loadTestEntities(){
		 ArrayList<SimpleTEntity> testList=new ArrayList<SimpleTEntity>();
		 // 10-65 in steps 1000 steps
		 //((1000/56)*i)+10
		 for(int i=999;i!=-1;i--){
			 float temp = (60f/1000f*i);
			// System.out.println(i+":"+temp);
			 SimpleTEntity ste = new SimpleTEntity(null, 1000, temp, 1, "dummy"+i);
			 testList.add(ste);
		 }
		 return testList;
	 }
	

}
