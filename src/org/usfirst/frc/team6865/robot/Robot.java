/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6865.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Talon;


public class Robot extends TimedRobot {
// Please change the number below once we know which one we are using.
public Spark shoot = new Spark (1);
public Spark Intake = new Spark (2);
public Talon DrivePower = new Talon (3);
public Talon Climb = new Talon (4);
private int mode =1;// initialize default mode
private SendableChooser autoCommand;




// Set Joy sticks 

private Joystick bigJ = new Joystick(1);
private Joystick xBox = new Joystick(0);


// Set Constants

private final double deadZone = 0.05;

// talon motor stuff



    @Override
    public void robotInit() {
        // This is called once when the robot code initializes
    	SmartDashboard.putNumber("DrivePower",0.82);
    	
    	// Camera USB
    	CameraServer.getInstance().startAutomaticCapture();
    	autoCommand = new SendableChooser();
    	autoCommand.addDefault("Command 1", 1);
    	autoCommand.addObject("Command 2", 2);
    	SmartDashboard.putData("Autonomos Selector", autoCommand);
    }

    @Override
    public void robotPeriodic() {
        // This is called every period regardless of mode
    	
    	
    }

    @Override
    public void autonomousInit() {
        // This is called once when the robot first enters autonomous mode
    	mode = (int) autoCommand.getSelected();
    	
    }

    @Override
    public void autonomousPeriodic() {
        // This is called periodically while the robot is in autonomous mode
    	switch(mode){
    	case 1;
    	
    	}
    	/*
    	 * This is a template to what we could do with autonoumous.
    	if (case 1 = TRUE) {
    		move.arcadeDrive(1,1);
    		Time(5 seconds);
    		move.arcadeDrive(0,0);
    	}
    	*/
    	
    }

    @Override
    public void teleopInit() {
        // This is called once when the robot first enters teleoperated mode
    	
    }

    @Override
    public void teleopPeriodic() {
        // This is called periodically while the robot is in teleopreated mode
    	
    	double DrivePower = SmartDashboard.getNumber('DriverPower',0.7);
    	double shootPower = SmartDashboard.getNumber('ShootPower', 0.9);
    	double intakePower = SmartDashboard.getNumber('IntakePower',0.9);
    	double ClimbPower = SmartDashboard.getNumber('ClimbPower',0.9);
    	
    	// Insert Saftys once we know what were doing
    	
    	feed.setSafetyEnabled(false);
    	
    	if(Math.abs(bigJ.getY()) > deadZone || Math.abs(bigJ.getX()) > deadZone) {
    		move.arcadeDrive(bigJ.getY()*drivePower,bigJ.getX()*drivePower);
    	}
    	else {
    		move.arcadeDrive(0,0,);
    	// Shooter Controls are below this 
    		
    		if(Math.abs(xBox.getRawButton(5)) > true ) {
    		feed.set(ShootPower);
    	} 
    	else {
    		feed.set(0);
    	}
    		// Intake controls
    		if (Math.abs(xBox.get4)) {
    			feed.set(IntakePower);
    		}else {
    			feed.set(0);
    		}
    		// Climbing if we get the chance
    	if (Math.abs(xBox.get3)) {
    		feed.set(ClimbPower);
    	}else {
    		feed.set(0);
    	}
    	
    	}// End of the drive base.
    	
    	
    }

    @Override
    public void testInit() {
        // This is called once when the robot enters test mode
    }

    @Override
    public void testPeriodic() {
        // This is called periodically while the robot is in test mode
    }

}

// any Ideas that you think that should be in this please put it in the discord.