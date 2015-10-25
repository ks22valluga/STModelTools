package com.ks22valluga.visualisation;

import java.util.ArrayList;

public class Connector {
private int connectorsCode;
private ArrayList<Enum> setConnectors;

public Connector(){
	setConnectors=new ArrayList<Enum>();
}

public void addConnector(ConnectorEnum ce){
	if(!setConnectors.contains(ce)){
		setConnectors.add(ce);
	}
}


public int getConnectorBitMapValue(){
	int retVal=0;
	if(setConnectors.contains(ConnectorEnum.top)){
		retVal=retVal+1;
	}
	if(setConnectors.contains(ConnectorEnum.right)){
		retVal=retVal+2;
	}
	if(setConnectors.contains(ConnectorEnum.bottom)){
		retVal=retVal+4;
	}
	if(setConnectors.contains(ConnectorEnum.left)){
		retVal=retVal+8;
	}
	
	
	
	return retVal;
}

}
