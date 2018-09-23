package com.ks22valluga.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JLabel;

public class TextFieldCentigrade extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5073059353640189538L;
    private static final String degreeSymb = "\u00b0";
    private BigDecimal bdValue;
    
    
	@Override
	public String getText() {
	//	String retString = super.getText().replace( degreeSymb+"C", "");
		return super.getText();
	}
	@Override
	public void setText(String text) {
		super.setText(text+degreeSymb+"C");
	}
    
    public void setValue(float value) {
		BigDecimal bigDecimal=new BigDecimal(value);
		bdValue=bigDecimal;
		setText(bigDecimal.setScale(2, RoundingMode.HALF_UP).toString());
    }
    
    public float getValue() {
    	if(bdValue!=null){
    		return bdValue.setScale(2, RoundingMode.HALF_UP).floatValue();
    	}else {
     		String valStr=super.getText().replace( degreeSymb+"C", "");
     		return Float.parseFloat(valStr);
    	}
    }
    
    

    
//	@Override
//	public void setText(String text) {
//		super.setText(text+degreeSymb+"C");
//	}
//
//	@Override
//	public synchronized String getText() {
//		String retString = super.getText().replace( degreeSymb+"C", "");
//		return retString;
//	}
	
	

}
