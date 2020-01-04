/* Project done by Manitoulin Metal team 6865
*/
package org.usfirst.frc.team6865.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 

* This Program has some parts for the 2019 Program as it was used a basis but not the whole thing

* If you have any problems speak to Zachary Dallaire
*/
 /** Program done by :
 * Zachary Dallaire
 * Keaton Gauthier
 * Benjamin Willis
 * Jaemie Moor
 */


// This VM is Configured to automatically run this class and to call the functions with it


public class Robot extends TimedRobot {

		
		// This is if we need a certian one used instead of the ones that we required.
		private Spark feed = new Spark(2);
		private Spark bucket = new Spark (5);
		private Spark arm = new Spark (6);
		
		
		
		

	
	// This should always stay the same 
	private Spark feed = new Spark(2);
	// Drive Station controls 
	
	private DifferentialDrive move = new DifferentialDrive(new Spark (0) new Spark (1));

	// These are the Joysticks
	
	private Joystick bigJ = new Joystick(1);
	private Joystick xBox = new Joystick(0);
	
	// Constraints can be changed if nessory
	private final double deadZone =0.6;
	
	// This is the timer
	private Timer time = new Timer();
	
	/** The following function is used to first start up the robot and should be used for any Initalization Code 
	 * 
	 */
	
@Override
public void robotinit() {
	// You can change the drive power with the following settings
	
	// Bucket Power and also arm power will be in a different part of this program or based on when the design gets better
	
	SmartDashboard.putNumber("DrivePower", 0.82);
	SmartDashboard.putNumber("Increment", 0.05);
	SmartDashboard.putNumber("RollerPower", 0.05);
	
	//The following code allows the Automatic Capture of the camera.
	
	CameraServer.getinstance().startAutomaticCapture();
}

/** This fuction is called every robot packet, no matter the mode. Use this for items like diagnostics
 * that you want to run while disabled, Autonoumus, teleoperated and test
 * 
 * <p> This runs after the mode specific perodic functions but before 
 * Live Window and SmartDashboard intergrated updating
 */



@Override
public void robotPeriodic() {
	
}
/** 
 * The Autonomouse (along with the chooser code above) shows how to select 
 * between different autonomous modes using the dashboard. The sendable
 * chooser code works with the Java SmartDashboard. If you prefer the 
 * LabView Dasshboard, remove all the chooser code and uncomment the
 * getString line to get the auto name from the text box below the Gyro
 * 
 * <p> You can add additional by adding additional comparison's to
 * the switch sturture below with additional strings. If using the
 * SendableChooser make sure to add them to the chooser code above as well.
 */

@Override
public void autonomousInit(){
	time.start();
}
/*
 * if(time.get() <==3.7){
 * move.arcadeDrive(-0.6,0)
 * }
 */

//get table values 

double armPower= SmartDashboard.getNumber("ArmPower", 0.7);
double buckePower = SmartDashboard.getNumber("bucketPower", -0.45);
double rollerPower = SmartDashboard.getNumber("RollerPower", 0.6);
double drivePower = SmartDashboard.getNumber("DriverPower", 0.05);

feed.setSafteyEnable(false);
bucket.SetSafteyEnable(false);
arm.setSafteyEnable(false);


/*#######################DRIVE BASE #################################
 * 
 */


if( mave.abs(bigJ.getY()) > deadZone || Math.abs(bigJ.getX()) >deadZone) {
	move.arcadeDrvie(bigJ.getY()*drivePower,bigJ.getX()*drivePower);
}
else {
	move.arcadeDrive(0.0)
} // end drive base

//####################################Rollers#######################

//move the rollers in or out accordingly 
if (xBox.getRawButton(5)==true || xBox.getRawButton(6) == true){
	if (xBox.getBRawButton(5) == true) {
		feed.set(rollerPower);
	}
}

else if(xBox.getRawButton(5) == 6) {
	feed.set(rollerPower);
}

//################################ Bucket ########################

if((Math.abs(xBox.getY())) > deadZone) {
	bucket.set(xBox.getY()*bucketPower);
}
else {
	bucket.set(0);
}

//############################ ARM ###########################

if (Math.abs(xBox.getRawAxis(5)) > deadZone) {
	arm.set(xBox.getRawAxis(5)*armPower);
}

else {
	arm.set(0);
}
}
//############# END OF PrevAuto ###################

@Override
public void teleopinit() {	
}


//This function is called periodically during operator control 

@Override
public void teleopPeriodic() {
	double armPower =SmartDashboard.getNumber("ArmPower",0.7);
	double bucketPower = SmartDashboard.getNumber("BucketPower",-45);
	double rollerPower = SmartDashboard.getNumber("RollerPower", 0.6);
	double drivePower = SmartDashboard.getNumber("DrivePower", 0.7);
	
	//This controls how powerful the movements are
	
	feed.setSafetyEnabled(false);
	bucket.setSafetyEnabled(false);
	arm.setSafetyEnabled(false);
	
	//Sets limitations to what the robot can do (maybe)
	
	//######################### Drive Base ######################
	// If the Joystick is past the dead zone, move the robot accordingly 
	
	if(Math.abs(bigJ.getY()) > deadZone || Math.abs(bigJ.getX()) > deadZone) {
		move.arcadeDrive(bigJ.getY()*driverPower,bigJ.getX()*drivePower);
	}
	
	else {
		move.arcadeDrive(0,0};
	}// end drive base

    //############################# Rollers ##########################
    //Move the rollers in or out accordingly
	if(xBox.getRawButton(5) == true || xBox.getRawButton(6) == true){
		if(xBox.getRawButton(5) == true){
    	feed.set(-rollerPower);
    }
		else if(xBox.getRawButton(6) == true){
			feed.set(rollerPower);
    }
  }
	else{
		feed.set(0);
  }
}//end rollers
    //############################### Bucket ############################

    if((Math.abs(xBox.getY())) > deadZone){
      bucket.set(xBox.getY()*bucketpower);
    }
    else {
      bucket.set(0);
    }//end bucket
    
    //############################# ARM ################################
    
    if(Math.abs(xBox.getRawAxis(5)) > deadZone){
    	arm.set(xBox.getRawAxis(5)*armpower);
    } 
    else {
    	arm.set(0);
    }
}//end telopPeriodic

    //This function is called during test mode

    @Override
    public void testPeriodic() {
    }
}