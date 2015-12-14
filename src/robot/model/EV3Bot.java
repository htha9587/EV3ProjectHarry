package robot.model;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.hardware.motor.BaseRegulatedMotor;


/**
 * 
 * @author htha9587
 * Version 0.x Dec 14, 2015
 */

public class EV3Bot 
{
	private String botMessage;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	
	public EV3Bot()
	{
		this.botMessage = "Harry bots the bot";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4000;
	}
	
	public void driveRoom()
	{
		//call private helper method.
	}
	
	
private void displayMessage()
{
	//overload displayMessage with one string parameter.
	LCD.drawString(botMessage, xPosition, yPosition);
	Delay.msDelay(waitTime);
	
	//write first drive method here
	//call the displayMessage("describe action as a string") in the helper method.
	
	extends Device
	implements RegulatedMotor
	
		Motor.A.setSpeed(720); // 2 RPM
		Motor.C.setSpeed(720);
		Motor.A.forward(500);
		Motor.C.forward(500);
		Delay.msDelay(1000);
		Motor.A.stop(20);
		Motor.C.stop(20);
		Motor.A.rotateTo( 360);
		Motor.A.rotate(-720,true);
		while(Motor.a.isMoving()Thread.yield();
		int angle = Motor.A.getTachoCount(); // should be -360
		LCD.drawInt(angle,0,0);
		
		BaseRegulatedMotor.driveRoom();
	
}

}
