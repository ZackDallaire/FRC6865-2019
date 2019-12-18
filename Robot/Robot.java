/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
//Set Motors - spark(port)
  private Spark feed = new Spark(2);
  private Spark bucket = new Spark(5);
  private Spark arm = new Spark(6);

//Set Drive Type
  private DifferentialDrive move = new DifferentialDrive(new Spark(0), new Spark(1));

//Set Joysticks
  private Joystick bigJ = new Joystick(1);
  private Joystick xBox = new Joystick(0);

//Set Constants
  private final double deadZone = 0.06;

//Set Timer
  public Timer time = new Timer();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

  // Table prefferences
    SmartDashboard.putNumber("ArmPower", 0.7);
    SmartDashboard.putNumber("BucketPower", -0.45);
    SmartDashboard.putNumber("RollerPower", 0.6);
    SmartDashboard.putNumber("Increment", 0.05);
    SmartDashboard.putNumber("DrivePower", 0.82);

    //Camera USB
    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    time.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
/*
    if(time.get() <= 3.7){
    move.arcadeDrive(-0.6,0);
    }
  */
    
      //get table values
      double armPower = SmartDashboard.getNumber("ArmPower", 0.7);
      double bucketPower = SmartDashboard.getNumber("BucketPower", -0.45);
      double rollerPower = SmartDashboard.getNumber("RollerPower", 0.6);
      double drivePower = SmartDashboard.getNumber("DrivePower", 0.05);

      feed.setSafetyEnabled(false);
      bucket.setSafetyEnabled(false);
      arm.setSafetyEnabled(false);
  
      //############################ Drive Base #######################
      //If the joystick is past the dead zone, move the robot accordingly
      if(Math.abs(bigJ.getY()) > deadZone || Math.abs(bigJ.getX()) > deadZone){
        move.arcadeDrive(bigJ.getY()*drivePower, bigJ.getX()*drivePower);
      } 
      else {
        move.arcadeDrive(0,0);
      } // end drive base
  
      //############################# Rollers ##########################
      //Move the rollers in or out accoringly
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
  
      //############################### Bucket ############################
      //Move bucket accordingly
      if((Math.abs(xBox.getY())) > deadZone){
        bucket.set(xBox.getY()*bucketPower);
      } 
      else {
        bucket.set(0);
      }

      //############################### ARM ###############################
      
      if(Math.abs(xBox.getRawAxis(5)) > deadZone){
        arm.set(xBox.getRawAxis(5)*armPower);
      } 
      else {
        arm.set(0);
      }
      //######## END OF PrevAuto #####
    
  }

  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //get table values
    double armPower = SmartDashboard.getNumber("ArmPower", 0.7);
    double bucketPower = SmartDashboard.getNumber("BucketPower", -0.45);
    double rollerPower = SmartDashboard.getNumber("RollerPower", 0.6);
    double drivePower = SmartDashboard.getNumber("DrivePower", 0.7);

    feed.setSafetyEnabled(false);
    bucket.setSafetyEnabled(false);
    arm.setSafetyEnabled(false);

    //############################ Drive Base #######################
    //If the joystick is past the dead zone, move the robot accordingly
    if(Math.abs(bigJ.getY()) > deadZone || Math.abs(bigJ.getX()) > deadZone){
      move.arcadeDrive(bigJ.getY()*drivePower, bigJ.getX()*drivePower);
    } 
    else {
      move.arcadeDrive(0,0);
    } // end drive base

    //############################# Rollers ##########################
    //Move the rollers in or out accoringly
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
/*
    //############################### Bucket ############################

    */
    if((Math.abs(xBox.getY())) > deadZone){
      bucket.set(xBox.getY()*bucketPower);
    } 
    else {
      bucket.set(0);
    }
    
    //############################### ARM ###############################
    
    if(Math.abs(xBox.getRawAxis(5)) > deadZone){
      arm.set(xBox.getRawAxis(5)*armPower);
    } 
    else {
      arm.set(0);
    }
    
 
    	
} // end teleopPeriodic

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
