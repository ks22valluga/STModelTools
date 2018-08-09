package com.ks22valluga.visualisation;

import java.awt.Color;

public class ColorTemp {

	private float min;
	private float max;

	public ColorTemp(float min, float max) {
		this.min = min;
		this.max = max;
	}

	public Color getColorForTemp(float temp) {
		Color retColor = null;
		// take float temp and convert to rgb
		// range 1 r=0,g=0,b=0 black to r=0,g=0,b=255 blue
		// range 2 r=0,g=0,b=255 blue to r=255,g=0,b=255 purple
		// range 3 r=255,g=0,b=255 purple to r=255,g=0,b=0 red
		// range 4 r=255,g=0,b=0 red to r=255,g=255,b=255 white
		if (temp <= min) {
			retColor = new Color(0, 0, 0);
		} else if (temp >= max) {
			retColor = new Color(255, 255, 255);
		} else {
			float inputRange = max - min;
			float outputRange = 4 * 256;
			float ratio = temp / inputRange;
			float targetFloat = ratio * outputRange;
			int targetInt = Math.round(targetFloat);
			if (targetInt < 256) {
				// range 1
				retColor = new Color(0, 0, targetInt);
			} else if (targetInt > (1 * 256) - 1 && targetInt < 2 * 256) {
				// range 2
				int offsetTargetint = targetInt - (1 * 256);
				retColor = new Color(offsetTargetint, 0, 255);
			} else if (targetInt > (2 * 256) - 1 && targetInt < 3 * 256) {
				// range 3
				try {
					int offsetTargetint = 255 - (targetInt - (2 * 256));
					retColor = new Color(255, 0, offsetTargetint);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			} else {
				// range 4
				int offsetTargetint = targetInt - (3 * 256);
				// TODO: fix hack by understanding issue
				if (offsetTargetint > 255) {
					offsetTargetint = 255;
				}
				try {
					retColor = new Color(255, offsetTargetint, offsetTargetint);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("offsetTarget " + offsetTargetint);
				}
			}

		}
		return retColor;
	}

}
