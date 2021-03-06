package com.ks22valluga.thermal;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ks22valluga.util.NameRegistry;
import com.ks22valluga.util.TimerActivity;
import com.ks22valluga.util.UniqueCodeGenException;

public class SimpleTEntity implements TimerActivity {
	private float mass;


	private float temp;
	private SimpleTEntity parent;
	private float parentConductance;
	private ArrayList<SimpleTEntity> children;
	private String nodeId;
	private String friendlyName;
	private boolean fixedTemp;
	private boolean iAmARoot;
	
	
	public SimpleTEntity(SimpleTEntity parent, float mass, float temp, float parentConductance, String name){
		this.friendlyName=name;
		this.mass=mass;
		this.temp=temp;
		this.parent=parent;
		this.parentConductance=parentConductance;
		this.children = new ArrayList<SimpleTEntity>();
		try {
			this.nodeId=NameRegistry.getUniqueName();
		} catch (UniqueCodeGenException e) {
			e.printStackTrace();
		}
		this.fixedTemp=false;
		setIAmARoot(false);
		
	}
	
	public SimpleTEntity(SimpleTEntity parent, float mass, float temp, float parentConductance, String name, boolean fixedTemp){
		this(parent, mass, temp, parentConductance, name);
		this.fixedTemp=fixedTemp;
	}
	
	public void addChild(float mass, float temp, float conductance, String name ){

		SimpleTEntity locSTE = new SimpleTEntity(this, mass, temp, conductance, name);
		children.add(locSTE);
	}
	
	public void addChild(SimpleTEntity ste){

		children.add(ste);
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public float getMass() {
		return mass;
	}

	public SimpleTEntity getParent() {
		return parent;
	}
	
	
	public void setParent(SimpleTEntity ste) {
		this.parent=ste;
	}
	

	public float getParentConductance() {
		return parentConductance;
	}

	public SimpleTEntity[] getChildren() {
		SimpleTEntity[] steArr = new SimpleTEntity[children.size()];
		int count=0;
		for(SimpleTEntity ste : children ){
			steArr[count++]=ste;
		}
		return steArr;
	}
	
	public void update(){
		SimpleTEntity root=this;
		float keepTemp=temp;

		if(root.hasChildren()){
		SimpleTEntity[] steArr =root.getChildren();
		for(SimpleTEntity ste :steArr ){

			ste.update();
			//find highest temp
			float conductance = ste.getParentConductance();
			SimpleTEntity[] tempArr=new SimpleTEntity[2];
			tempArr[0]=root;
			tempArr[1]=ste;
			tempArr = sortTempPair(tempArr);
			float tempDiff=tempArr[0].getTemp()-tempArr[1].getTemp();
			float heatTrans = tempDiff/(conductance/100);
			float heat1=tempArr[0].getMass()*tempArr[0].getTemp();
			float heat2=tempArr[1].getMass()*tempArr[1].getTemp();
			heat1=heat1-heatTrans;
			heat2=heat2+heatTrans;
			float temp1 =heat1/tempArr[0].getMass();
			float temp2 =heat2/tempArr[1].getMass();
			    if(!tempArr[0].isFixedTemp()){
				tempArr[0].setTemp(temp1);
			    }
			    if(!tempArr[1].isFixedTemp()){
				tempArr[1].setTemp(temp2);
			    }


			
		}
		}
		if(fixedTemp){
			this.temp=keepTemp;
		}
	}
	
    private boolean hasChildren() {
		return children.size()>0;
	}

	private static SimpleTEntity[] sortTempPair(SimpleTEntity[] steArrPair){
    	SimpleTEntity[] retArr = new SimpleTEntity[2];
    	float t1 = steArrPair[0].getTemp();
    	float t2 = steArrPair[1].getTemp();
    	if (t1>=t2){
    		//leave alone
    		retArr=steArrPair;
    	}else{
    		//swap array round
    		retArr[0]=steArrPair[1];
    		retArr[1]=steArrPair[0];
    	}
    	return retArr;
    }
	
	public JSONObject buildConfig(){
	JSONObject jo = new JSONObject();
		SimpleTEntity currentEntity=this;
		JSONArray ja=null;
		if(currentEntity.hasChildren()){
		SimpleTEntity[] steArr =currentEntity.getChildren();
		
		 ja= new JSONArray();
		for(SimpleTEntity ste :steArr ){
			 
			JSONObject tempJo = new JSONObject();
			tempJo =ste.buildConfig();
			ja.put(tempJo);
			//define current node and add children (jo)
		}
		}

		jo.put("name", this.friendlyName);
		jo.put("mass", this.mass);
		jo.put("temp", this.temp);
		jo.put("pConductance", this.parentConductance);
		jo.put("nodeId",this.nodeId);
		jo.put("childrenArray", ja);
		
		
		return jo;
	}

	public String getNodeId() {
		return nodeId;
	}
	
	public void setMass(float mass) {
		this.mass = mass;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public boolean isFixedTemp() {
		return fixedTemp;
	}

	public void setFixedTemp(boolean fixedTemp) {
		this.fixedTemp = fixedTemp;
	}

	@Override
	public void fireTimerEvent() {
		if(isIAmARoot()){
		//    System.out.println('.');
			update();
		}
		
	}

	public boolean isIAmARoot() {
		return iAmARoot;
	}

	public void setIAmARoot(boolean iAmARoot) {
		this.iAmARoot = iAmARoot;
	}

	public void setFixedTemp(float f) {
	   this.fixedTemp=true;
	   this.temp=f;
	    
	}
	

	


}
