package com.ks22valluga.test;

import org.json.JSONObject;

import com.ks22valluga.thermal.SimpleTEntity;

public class AdhocCalcs {

	public static void main(String[] args) {

double mass1,mass2,temp1,temp2,energy1,energy2;

double kelvin=273.15d;
mass1=1000d;
temp1=10.5d;
energy1=mass1*(temp1+kelvin);
mass2=500d;
temp2=20.8d;
energy2=mass2*(temp2+kelvin);


//so looking for a simplistic algo that 
//allows for equilibrium i.e 2 bodies become same temp over time
//allows 'energy' transfer to be proportional to temp diff
//allows large 'mass' to raise and lower temp slower than small mass
// i.e if we have large mass and small mass eventual temp will not be 
// ((t2-t1)/2)+t1 (halfway between t1 and t2)

//Q = m•C•ΔT heat = mass*specific heat*temp change (but this is timeless)
// lets assume:
// specific heat of all =1
// a standard heat transfer rate per deg C diff per unit of contact surface area 


/*
System.out.println("Start values\n");
System.out.println("temp1 "+temp1);
System.out.println("mass1 "+mass1);
System.out.println("energy1 "+energy1);
System.out.println("\ntemp2 "+temp2);
System.out.println("mass2 "+mass2);
System.out.println("energy2 "+energy2);
*/
SimpleTEntity root = new SimpleTEntity(null, 500000, 2500, 0,"root");
root.addChild(500, 0, 5, "one");
root.addChild(300, 0, 5, "two");
root.addChild(200, 0, 5,"three");
root.addChild(600, 0, 5,"four");
root.getChildren()[0].addChild(500, 000, 25,"one-one");
root.getChildren()[0].getChildren()[0].addChild(500, 0, 5,"one-one-one");

for(int i=0;i<360;i++){
	root.update();
	System.out.println("Min "+i+"\t"+root.getTemp()+ "\t"+root.getChildren()[0].getTemp()+ "\t"+root.getChildren()[0].getChildren()[0].getTemp()+"\t"+root.getChildren()[0].getChildren()[0].getChildren()[0].getTemp()+"\t");
}
JSONObject jo = new JSONObject();
jo=root.buildConfig();
System.out.println(jo.toString(2));
int a =2;
int b=a+2;

	}
	
	private static void update(SimpleTEntity root){
		// cycle through each parent - child
		SimpleTEntity[] steArr =root.getChildren();
		for(SimpleTEntity ste :steArr ){
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
			tempArr[0].setTemp(temp1);
			tempArr[1].setTemp(temp2);
		}
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
	
	
	

}
