package com.ks22valluga.util;

import java.util.ArrayList;
import java.util.Timer;

public class STTimer extends Thread{

	private boolean letDie;
	private int iteration;
	private boolean loop;
	private long pauseTime;
	private ArrayList<TimerActivity> timerActivities;
	
	public STTimer(int iteration,long pauseTime){
		this.iteration=iteration;
		if(iteration==0){
			this.loop=true;
		}
		this.pauseTime=pauseTime;
		timerActivities= new ArrayList<>();
	}

	@Override
	public void run() {
		while(!letDie){
			try {
				Thread.sleep(this.pauseTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(TimerActivity ti:timerActivities){
				ti.fireTimerEvent();
			}			
			if(loop){
				//do nothing , permanent loop 
			}else{
				iteration--;
				if(iteration<0){
					this.letDie=true;
				}
			}
		}
	}
	
	public void letDie(){
		this.letDie=true;
	}
	
	public void addTimerActivity(TimerActivity ti){
		timerActivities.add(ti);
	}
	
	
	
	
}