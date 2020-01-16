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


// Set Joysticks 

private Joystick bigJ = new Joystick(1);
private Joystick xBox = new Joystick(0);


// Set Constants

private final double deadZone = 0.05;

    @Override
    public void robotInit() {
        // This is called once when the robot code initializes
    	SmartDashboard.putNumber("DrivePower",0.82);
    	
    	// Camera USB
    	CameraServer.get.instance().startAutomaticCapture();
    	
    }

    @Override
    public void robotPeriodic() {
        // This is called every period regardless of mode
    	
    	
    }

    @Override
    public void autonomousInit() {
        // This is called once when the robot first enters autonomous mode
    }

    @Override
    public void autonomousPeriodic() {
        // This is called periodically while the robot is in autonomous mode
    	
    }

    @Override
    public void teleopInit() {
        // This is called once when the robot first enters teleoperated mode
    	
    }

    @Override
    public void teleopPeriodic() {
        // This is called periodically while the robot is in teleopreated mode
    	
    	double drivePower = SmartDashboard.getNumber('DrivePower',0.7);
    	double shootPower = SmartDashboard.getNumber('ShootPower', 0.9);
    	double intakePower = SmartDashboard.getNumber('IntakeNumber',0.9);
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
    		// Intake 
    		if (Math.abs(xBox.get4)) {
    			feed.set(intakePower);
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