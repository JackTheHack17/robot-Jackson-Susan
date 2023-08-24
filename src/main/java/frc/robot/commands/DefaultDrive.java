package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;


public class DefaultDrive extends CommandBase {
    private DoubleSupplier speed; 
    private DoubleSupplier rotation; 
    private Drive drive; 

    public DefaultDrive(DoubleSupplier speed, DoubleSupplier rotation, Drive drive){ 
        this.speed = speed; 
        this.rotation = rotation; 
        this.drive = drive;  

        addRequirements(drive); 
    } 

    @Override 
    public void execute(){ 
        drive.arcadeDrive(speed.getAsDouble(), rotation.getAsDouble()); 
    }
}
