/* Project done by Manitoulin Metal team 6865
*/

package org.usfirst.frc.team6865.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpu.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DiffrentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 

* This Program has some parts for the 2018 Program as it was used a basis but not the whole thing

* If you have any problems speak to Zachary Dallaire
*/



/** This VM is Configured to automatically run this class and to call the functions with it
*/

public class Robot extends TimedRobot {

		
		// This is if we need a certian one used instead of the ones that we required.
		private Spark feed = new Spark(2);
		private Spark bucket = new Spark (5);
		private Spark arm = new Spark (6);
		
		
		
		

	
	// This should always stay the same 
	private Spark feed = new Spark(2);
	// Drive Station controls 
	
	private DiffrentialDrive move = New DifferentialDrive(new Spark (0) new Spark (1));

	// These are the Joysticks
	
	private Joystick bigJ = new Joystick(1);
	private Joystick xBox = new Joystick(0);
	
	// Constraints can be changed if nessory
	private final double deadZone =0.6;
	
	// This is the timer
	private Timer time = new Timer();
	

}

