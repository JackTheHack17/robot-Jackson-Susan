package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private DoubleSolenoid intake1;  
    private TalonSRX rollerMotor;   
    private DoubleSolenoid intake2; 
    private Compressor compressor;
    
    public Intake(){ 
        intake1 = new DoubleSolenoid(PneumaticsModuleType.REVPH,1,2); 
        rollerMotor = new TalonSRX(7); 
        intake2 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 3, 4); 
        compressor = new Compressor(PneumaticsModuleType.REVPH);  
        compressor.enableDigital();  
        
        
    } 

    public void startIntake(){ 
        rollerMotor.set(ControlMode.PercentOutput,1); 
    } 
    public void endIntake(){ 
        rollerMotor.set(ControlMode.PercentOutput,0); 
    } 
    public void getObject(){ 
        intake1.set(Value.kForward); 
        intake2.set(Value.kReverse); 
        startIntake(); 
    } 
    public void retreat(){ 
        intake1.set(Value.kReverse);  
        intake2.set(Value.kReverse);  
        endIntake(); 
    } 
    public void reset(){ 
        intake1.set(Value.kOff); 
        intake2.set(Value.kOff); 
    }
}
