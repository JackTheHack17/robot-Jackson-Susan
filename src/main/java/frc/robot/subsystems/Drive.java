package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    private  WPI_TalonFX leftMaster; 
    private  WPI_TalonFX rightMaster; 
    private  WPI_TalonFX leftSlave; 
    private  WPI_TalonFX rightSlave;   
    private DifferentialDrive drive; 

    public Drive(){ 
        leftMaster = new WPI_TalonFX(5); 
        rightMaster = new WPI_TalonFX(6);  
        leftSlave = new WPI_TalonFX(7); 
        rightSlave = new WPI_TalonFX(8);  

        leftMaster.setInverted(true); 
        leftSlave.setInverted(true); 

        leftSlave.follow(leftMaster); 
        rightSlave.follow(rightMaster);  

        drive = new DifferentialDrive(leftMaster, rightMaster); 

    } 

    public void arcadeDrive(double speed, double rotation){ 
        drive.arcadeDrive(-speed,rotation);
    }



}
