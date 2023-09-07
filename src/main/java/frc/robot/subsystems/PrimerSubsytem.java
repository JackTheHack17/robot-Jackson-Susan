package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PrimerConstants;

public class PrimerSubsytem extends SubsystemBase{
    private WPI_TalonSRX hopper;   
    private WPI_TalonSRX cargoIntake;  
    public PrimerSubsytem(){ 
       hopper = new WPI_TalonSRX(PrimerConstants.hopperPort); 
       cargoIntake = new WPI_TalonSRX(PrimerConstants.intakePort);   
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

    public void reset(){ 
        hopper.set(0); 
        cargoIntake.set(0); 
    } 
    public void runHopper(){    
          hopper.set(PrimerConstants.hopperSpeed); 
    } 
    public void runBackwardHopper(){ 
        hopper.set(-PrimerConstants.hopperSpeed);
    }
    public void stopHopper(){ 
        hopper.set(0); 
    }  
    public void reload(){ 
        cargoIntake.set(1); 
    }  
    public void drop(){ 
        cargoIntake.set(-1); 
    }
    public void stopCargIntake(){ 
        cargoIntake.set(0); 
    } 
    public void throwBall(){ 
        cargoIntake.set(-1); 
    } 

          
} 
    
    
