/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6865.robot;

/*
 * DISCLAIMER
 * Please not that this code is still in beta and we don't know if this will actually work yet
 * waiting on robot
 * 
 * Testing should start sometime this week.
 * 
 * Made by Zachary Dallaire, and Ben
 * 
 * Original name of the robots wifi network was Robots in Disguise 
 */

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
public Talon shoot = new Talon (1);
public edu.wpi.first.wpilibj.Talon intake = new Talon (2);
public edu.wpi.first.wpilibj.Talon DriverPower = new Talon (3);
//public Talon Climb = new Talon (4);
public Talon Polocord = new Talon (5);698


  private int mode =1;// initialize default mode
@SuppressWarnings("rawtypes")
private SendableChooser autoCommand;

// Timer 
public Timer time = new Timer();


// Set Joy sticks 

private Joystick bigJ = new Joystick(1);
private Joystick xBox = new Joystick(0);




// Set Constants

private final double deadZone = 0.05;

// Talon motor stuff




// Drive Stuff
private  DifferentialDrive move = new DifferentialDrive(new Talon(0),new Talon(1));

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void robotInit() {
        // This is called once when the robot code initializes
    	SmartDashboard.putNumber("DriverPower",0.82);
    	SmartDashboard.putNumber("ShootPower,",0.9);
    	SmartDashboard.putNumber("IntakePower",0.9);
    	SmartDashboard.putNumber("Polycord",0.6);
    
    	
    	// Camera USB
    	CameraServer.getInstance().startAutomaticCapture();
    	// Code for new stuff
    	
    
    	
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
    	// This is for later when we program Autonomous 
    	/*
    	double DrivePower = SmartDashboard.getNumber("DriverPower", 0.7);
    	double shootPower = SmartDashboard.getNumber("ShootPower",0.9);
    	double intakePower = SmartDashboard.getNumber("IntakePower",0.9);
    	//double ClimbPower = SmartDashboard.getNumber('ClimbPower',0.9);
    	double Polocord = SmartDashboard.getNumber("Polocord",0.6);
    	*/
    	
    	/*int One;
    	One = false;
    	
    	if (mode == 1) {
    		case = 1;
    		
		}
    	/*
    	 * This is a template to what we could do with autonomous.
    	if (case 1 = true) {
    		move.arcadeDrive(1,1);
    		Time(5 seconds);
    		move.arcadeDrive(0,0);
    	}
    	if(case 2 = true {
    	move.arcadeDrive(-5,-5);
    	move.arcadeDrive(0,0);
    	}
    	*/
    	
    }

    @Override
    public void teleopInit() {
        // This is called once when the robot first enters tele operated mode
    	
    }

    @Override
    public void teleopPeriodic() {
        // This is called periodically while the robot is in tele operated mode
    	
 
    	double DrivePower = SmartDashboard.getNumber('DriverPower' 0.7);

    	double shootPower = SmartDashboard.getNumber('ShootPower', 0.9);
    	double intakePower = SmartDashboard.getNumber('IntakePower',0.9);
    	//double ClimbPower = SmartDashboard.getNumber('ClimbPower',0.9);
    	double Polocord = SmartDashboard.getNumber('Polocord',0.6);
    	
    	// Insert Safetys once we know what were doing
    	
    	feed.setSafetyEnabled(false);
    	DrivePower.setSafetyEnabled(false);
    	IntakePower.setSafetyEnabled(false);
    	ShootPower.setSafetyEnabled(false);
    	Polocord.setSafetyEnabled(false);
    	// driving
    	if(Math.abs(bigJ.getY()) > deadZone || Math.abs(bigJ.getX()) > deadZone) {
    		move.arcadeDrive(bigJ.getY()*DriverPower,bigJ.getX()*drivePower);
    	}
    	else {
    		move.arcadeDrive(0,0,);
    	
    		
    		// Shooter Controls are below this 
    		
    		if(Math.abs(xBox.getRawButton(5)) > true ) {
    		feed.set(ShootPower);
    	} 
    	else {
    		ShootPower.set(0);
    	}
    		
    		
    		// Intake controls
    		if (Math.abs(xBox.getRawButton4)) {
    			feed.set(IntakePower);
    		}else {
    			IntakePower.set(0);
    		}
    		
    		
    		// Climbing if we get the chance
    	/*if (Math.abs(xBox.get3)) {
    		feed.set(ClimbPower);
    	}else {
    		feed.set(0);
    	*/}
    	// This is for the Poly cord to bring the power cells into the shoot 
    	if (Math.abs(xBox.getRawButton0)) {
    		feed.set(Polocord);
    	}else {
    	PoloCord.set(0);
    	}
    	}// End of the drive base.
    	
    	
    

    @Override
    public void testInit() {
        // This is called once when the robot enters test mode

    }

    @Override
    public void testPeriodic() {
        // This is called periodically while the robot is in test mode
    }

}

// any Ideas that you think that should be in this please put it in the discord or in the github issues page.