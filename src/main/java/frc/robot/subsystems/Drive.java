package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
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
    private ProfiledPIDController PIDcontrol;  
    

    private Encoder encoder = new Encoder(DriverConstants.encoderChannelA,DriverConstants.encoderChannelB);
    private final double circumferenceTicks = (DriverConstants.kWheelDiameter * Math.PI)/4096;
    
   
    
    
    public Drive(){  
        
        leftMaster = new WPI_TalonFX(DriverConstants.leftFront); 
        rightMaster = new WPI_TalonFX(DriverConstants.rightFront);  
        leftSlave = new WPI_TalonFX(DriverConstants.leftBack); 
        rightSlave = new WPI_TalonFX(DriverConstants.rightBack);  
        
        leftSlave.follow(leftMaster); 
        rightSlave.follow(rightMaster);   

        leftMaster.setInverted(true); 
        leftSlave.setInverted(true);  
        PIDcontrol = new ProfiledPIDController(DriverConstants.kP,DriverConstants.kI,DriverConstants.kD, null);

        

        drive = new DifferentialDrive(leftMaster, rightMaster);   
        encoder.setDistancePerPulse(circumferenceTicks);  
        

    }  

    @Override 
    public void periodic(){ 
        SmartDashboard.putNumber("Drive encoder position:",encoder.getDistance());
    } 
 
    

    public void arcadeDrive(double speed, double rotation){ 
        drive.arcadeDrive(speed,rotation);
    }
    public void resetEncoders(){ 
        encoder.reset(); 
    } 
    public void driveForward(double setpointFeet){  
        PIDcontrol.setTolerance(3); 
        double actualSet = encoder.getDistance() + setpointFeet;       
        double output = PIDcontrol.calculate(encoder.getDistance(), actualSet);   
        arcadeDrive(output,0); 

    }


}
