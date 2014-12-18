package com.ks22valluga.visualisation;


import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2139989720069504520L;



	private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("text", 50, 50);
    }


    
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		 doDrawing(g);
	}
    
    
}
