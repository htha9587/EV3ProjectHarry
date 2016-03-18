package robot.model;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;

/**
 * 
 * @author htha9587
 * Version 0.2x 3-18-16
 */

public class EV3Bot 
{
	private String botMessage;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	private Boolean isPositive;
	private MovePilot botPilot;
	private EV3UltrasonicSensor distanceSensor;
	private EV3TouchSensor backTouch;
	private double [] roomDistance1;
	private float []ultrasonicsSamples;
	private float [] touchSamples;
	
	public EV3Bot()
	{
		this.botMessage = "Harry bots the bot";
		this.xPosition = 0;
		this.yPosition = 1;
		this.waitTime = 4000;
		
		distanceSensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
		backTouch = new EV3TouchSensor(LocalEV3.get().getPort("S2"));
		roomDistance1 = new double[4];
		setupPilot();
		displayMessage();
		setUpDistanceArray();
	}
	
	public void driveRoom()
	{
		ultrasonicsSamples = new float [distanceSensor.sampleSize()];
		distanceSensor.fetchSample(ultrasonicsSamples, 0);
		//call private helper method.
		if(ultrasonicsSamples[0] < 2.5) //2.5 isn't a real whole number!
		{
			displayMessage();
			driveShort();
		}
	
		else
		{
			displayMessage("Long Drive");
			driveLong();
		}
		displayMessage("At the other door!");

	}
	
	public void driveShort()
	{
		for(int spot = 0; spot < roomDistance1.length; spot++)
		{
			displayMessage("Driving: " + roomDistance1[spot] + " units");
			botPilot.travel(roomDistance1[spot]);
			if (spot % 2 == 0)
			{
				botPilot.rotate(-90);
			}
			else
			{
				botPilot.rotate(90);
			}
		}
	}
	
	public void driveLong()
	{
		for(int spot = roomDistance1.length-1; spot >= 0; spot--)
		{
			displayMessage("Driving: " + roomDistance1[spot] + " units");
			botPilot.travel(roomDistance1[spot]);
			if (spot % 2 == 0)
			{
				botPilot.rotate(-90);
			}
			else
			{
				botPilot.rotate(90);
			}
			botPilot.stop();
		}
	}
	
	public void driveAround()
	{
		while(LocalEV3.get().getKeys().waitForAnyPress() != LocalEV3.get().getKeys().ID_ESCAPE);
		{
			double distance = (Math.random() * 100) % 23;
			double angle = (Math.random() * 360);
			boolean isPositive = ((int) (Math.random() * 2)% 2 == 0);
			distanceSensor.fetchSample(ultrasonicsSamples, 0);
			if(ultrasonicsSamples[0] < 17)
			{
				botPilot.travel(-distance);
				botPilot.rotate(angle);
			}
			else
			{
				botPilot.rotate(-angle);
				botPilot.travel(distance);
			}
		}
	}
	
	
	
	
	private void setupPilot()
	{
		Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 43.2).offset(-72);
	    Wheel rightWheel= WheeledChassis.modelWheel(Motor.B, 43.2).offset(72);
	    Chassis baseChassis = new WheeledChassis(new Wheel []{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		botPilot = new MovePilot(baseChassis);
		botPilot.travel(254.00);
		 //MovePilot.setRobotSpeed(30);  // cm per second
		 //MovePilot.travel(50);         // cm
		 //MovePilot.rotate(90);        // degree clockwise
		 //MovePilot.travel(50);  //  move forward.
		 //MovePilot.rotate(270);
		 //Movepilot.travel(50);
		 //MovePilot.rotate(90);
		 //MovePilot.travel(50);
		 //MovePilot.stop();
	}
	
	private void setUpDistanceArray()
	{
		roomDistance1[0] =(12 * 2.54 * 3);
		roomDistance1[1] =(12 * 2.54 * 10.5);
		roomDistance1[2] =(12 * 2.54 * 17);
		roomDistance1[3] =(12 * 2.54 * 19);
	}
	
	
	
private void displayMessage()
{
	
	LCD.drawString(botMessage, xPosition, yPosition);
	Delay.msDelay(waitTime);
	LCD.clear();
	//overload displayMessage with one string parameter.
	//write first drive method here
	//call the displayMessage("describe action as a string") in the helper method.
}
	private void displayMessage(String message)
	{
		LCD.drawString(message, xPosition, yPosition);
		Delay.msDelay(waitTime);
		LCD.clear();
	}
	
	
}


