package com.ks22valluga.app;

import javax.swing.JTextField;

public class TextFieldCentigrade extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5073059353640189538L;
    private static final String degreeSymb = "\u00b0";
	@Override
	public void setText(String text) {
		super.setText(text+degreeSymb+"C");
	}

	@Override
	public synchronized String getText() {
		String retString = super.getText().replace( degreeSymb+"C", "");
		return retString;
	}
	
	

}
