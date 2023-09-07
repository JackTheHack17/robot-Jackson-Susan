// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.GlobalVars.shootConfig;
//import frc.robot.commands.Autos;
import frc.robot.commands.DefaultDrive;
//import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PrimerSubsytem;
import frc.robot.subsystems.ShooterSubsystem;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger; 


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem(); 
  private Drive drivebase = new Drive(); 
  private Intake m_intake = new Intake(); 
  private PrimerSubsytem m_primer = new PrimerSubsytem(); 
  private ShooterSubsystem m_shooter = new ShooterSubsystem();  
  private boolean runHopper; 
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private Trigger xButton = m_driverController.x(); 
  private Trigger bButton = m_driverController.b(); 
  private Trigger aButton = m_driverController.a(); 
  private Trigger rBump = m_driverController.rightBumper(); 
  private Trigger lBump = m_driverController.leftBumper(); 
  private Trigger rTrigger = m_driverController.rightTrigger(); 
  private Trigger lTrigger = m_driverController.leftTrigger(); 
  private Trigger povLeft = m_driverController.povLeft(); 
  private Trigger povRight = m_driverController.povRight(); 
  private Trigger povUp = m_driverController.povUp(); 
  private Trigger povDown = m_driverController.povDown(); 
  // Replace with CommandPS4Controller or CommandJoystick if needed
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings 
    m_intake.reset();  
    m_shooter.reset();  
    m_primer.reset();   
    runHopper = true; 
    drivebase.resetEncoders(); 
    drivebase.setDefaultCommand(new DefaultDrive( 
      () -> m_driverController.getLeftY(), 
      () -> m_driverController.getRightX(), 
      drivebase 
      
    )); 
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`  
    //Starts the cargo intake while the x button is pressed
    xButton.whileTrue(new InstantCommand(() -> m_primer.reload())); 
    xButton.onFalse(new InstantCommand(() -> m_primer.stopCargIntake()));
    // Drops the ball while the b button is pressed 
    bButton.whileTrue(new InstantCommand(() -> m_primer.drop())); 
    bButton.onFalse(new InstantCommand(() -> m_primer.stopCargIntake()));
    // Toggles auto shoot 
    aButton.whileTrue(new InstantCommand( 
      () -> { 
        if (runHopper){ 
          runHopper = false; 
          m_shooter.shootNow(); 
          m_primer.runHopper();  
        } else { 
          m_shooter.stopshootMotors(); 
          runHopper = true; 
          m_primer.stopHopper(); 
        } 
      }
    )); 
    /// Extends intake to retrieve balls
    rBump.whileTrue(new InstantCommand( 
      () -> {  
        m_intake.getObject(); 
        m_primer.reload();  
      }
    ));  

    lBump.whileTrue(new InstantCommand(  
      () -> { 
        m_intake.retreat(); 
        m_primer.stopCargIntake(); 
      }
    )); 

    lTrigger.whileTrue(new InstantCommand( 
      () -> {  
         if (runHopper){
          m_primer.runBackwardHopper(); 
         }
      }
    )); 

    rTrigger.whileTrue(new InstantCommand( 
      () -> { 
         if (runHopper){ 
          m_primer.runHopper();
         }

      }
    )); 
    povRight.onTrue(new InstantCommand(() -> changeShootSpeed(1)));
    povLeft.onTrue(new InstantCommand(() -> changeShootSpeed(0.5)));
    povUp.whileTrue(new InstantCommand(() -> m_shooter.extendHood())).onFalse(new InstantCommand(() -> m_shooter.stopHood())); 
    povDown.whileTrue(new InstantCommand(() -> m_shooter.retractHood())).onFalse(new InstantCommand(() -> m_shooter.stopHood()));
  }   

  private void changeShootSpeed(double speed){ 
     shootConfig.shootSpeed = speed;  

     if (!runHopper){m_shooter.shootNow();}; 
  }
    

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
     return null; 
} 
}
