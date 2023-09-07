package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PrimerConstants;

public class PrimerSubsytem extends SubsystemBase{
    private WPI_TalonSRX hopper;   
    private WPI_TalonSRX cargoIntake; 
    public boolean shouldRun; 
    public PrimerSubsytem(){ 
       hopper = new WPI_TalonSRX(PrimerConstants.hopperPort); 
       cargoIntake = new WPI_TalonSRX(PrimerConstants.intakePort); 
       shouldRun = false;  
       configMotors(); 
       cargoIntake.setInverted(true);
    } 

    public void configMotors(){ 
        WPI_TalonSRX [] motors = {hopper, cargoIntake};  
        for (WPI_TalonSRX motor : motors){ 
            motor.configFactoryDefault(); 
            motor.setNeutralMode(NeutralMode.Coast); 
        } 

    } 

    public void stopAll(){ 
        hopper.set(0); 
        cargoIntake.set(0); 
    } 
    public void runHopper(boolean reversed){  
        if (reversed == true){  
          hopper.set(PrimerConstants.hopperSpeed); 
        } else { 
            hopper.set(-PrimerConstants.hopperSpeed); 
        }   
    }
    public void stopHopper(){ 
        hopper.set(0); 
    }  
    public void startCargIntake(){ 
        cargoIntake.set(1); 
    } 
    public void stopCargIntake(){ 
        cargoIntake.set(0); 
    } 
    public void throw (){ 
        cargoIntake.set(-1); 
    } 

          
} 
    
    
