package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
    private TalonFX shootLeft; 
    private TalonFX shootRight;
    private WPI_TalonSRX hoodMotor; 

    public ShooterSubsystem(){ 
        shootLeft = new TalonFX(ShooterConstants.leftShootPort);  
        shootRight = new TalonFX(ShooterConstants.rightShootPort); 
        hoodMotor = new WPI_TalonSRX(ShooterConstants.hoodPort);   
        
        configMotors();
        shootLeft.follow(shootRight);  

    }  
    
    public void configMotors(){ 
        TalonFX[] motors = {shootLeft,shootRight};  
        for (TalonFX motor : motors){ 
           motor.configFactoryDefault(); 
           motor.setNeutralMode(NeutralMode.Coast);
        } 
        shootRight.setInverted(true);  
        hoodMotor.setInverted(true); 
    }  

    public void shootNow(){ 
       shootRight.set(ControlMode.PercentOutput, 1); 
    } 
    public void haltHood(){ 
        hoodMotor.set(0); 

    } 
    public void retractHood(){ 
        hoodMotor.set(ControlMode.PercentOutput, -1); 
    } 
    public void stopHoodMotors(){ 
        shootRight.set(0); 
    }

    



}
