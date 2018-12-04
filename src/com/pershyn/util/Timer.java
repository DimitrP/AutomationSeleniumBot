package com.pershyn.util;

public class Timer {
	public static void pauseSeconds(int s){
		try{

			Thread.sleep(s*1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
