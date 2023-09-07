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
        intake1 = new DoubleSolenoid(1,PneumaticsModuleType.CTREPCM,1,3); 
        rollerMotor = new TalonSRX(16); 
        intake2 = new DoubleSolenoid(1,PneumaticsModuleType.CTREPCM, 3, 4); 
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);  
        compressor.enableDigital();  
        
        
    } 
   
    public void startIntake(){   
        rollerMotor.set(ControlMode.PercentOutput,1); 
    } 
    public void endIntake(){  
        rollerMotor.set(ControlMode.PercentOutput,0); 
    } 
    public void getObject(){  
        System.out.println("Intake out!");
        intake1.set(Value.kForward); 
        intake2.set(Value.kForward); 
        startIntake(); 
    } 
    public void retreat(){  
        System.out.println("Intake in!");
        intake1.set(Value.kReverse);  
        intake2.set(Value.kReverse);  
        endIntake(); 
    } 
    public void reset(){ 
        intake1.set(Value.kOff); 
        intake2.set(Value.kOff); 
    }
}
