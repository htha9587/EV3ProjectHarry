package robot.controller;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

import robot.model.EV3Bot;

/**
 *  
 * @author htha9587
 *Version 0.x Dec 14, 2015
 */

public class BotController 
{
	private String message;
	
	private int xPosition;
	private int yPosition;
	
	private long waitTime;
	
	private EV3Bot harryBot;
	
	public BotController()
	{
		this.message = "Programming robits in Java!";
		this.xPosition = 10;
		this.yPosition = 10;
		this.waitTime = 4000;
		
		harryBot = new EV3Bot();
	}
	
	public void start()
	{
		LCD.drawString(message, xPosition, yPosition);
		Delay.msDelay(waitTime);
		
		harryBot.driveRoom();
	}
	
	
	
}
