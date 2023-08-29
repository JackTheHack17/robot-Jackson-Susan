package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriverConstants;

public class Drive extends SubsystemBase {
    private  WPI_TalonFX leftMaster; 
    private  WPI_TalonFX rightMaster; 
    private  WPI_TalonFX leftSlave; 
    private  WPI_TalonFX rightSlave;   
    private DifferentialDrive drive;  
    private Encoder encoder = new Encoder(DriverConstants.encoderChannelA,DriverConstants.encoderChannelB);
    private final double kDriveTicksToFeet = 1.0 / 128 * DriverConstants.kWheelDiameter * Math.PI / 12;  
    public final double getFeetPos(){ 
        return encoder.get() * kDriveTicksToFeet; 
    }
    public Drive(){ 
        leftMaster = new WPI_TalonFX(DriverConstants.leftFront); 
        rightMaster = new WPI_TalonFX(DriverConstants.rightFront);  
        leftSlave = new WPI_TalonFX(DriverConstants.leftBack); 
        rightSlave = new WPI_TalonFX(DriverConstants.rightBack);  
        
        leftSlave.follow(leftMaster); 
        rightSlave.follow(rightMaster);   

        leftMaster.setInverted(true); 
        leftSlave.setInverted(true); 

        

        drive = new DifferentialDrive(leftMaster, rightMaster);  
        

    }  

    @Override 
    public void periodic(){ 
        SmartDashboard.putNumber("Drive encoder position:",getFeetPos());
    } 
 
    

    public void arcadeDrive(double speed, double rotation){ 
        drive.arcadeDrive(speed,rotation);
    }



}
