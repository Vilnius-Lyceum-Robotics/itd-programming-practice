package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.core.RobotConstants;
import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalArm;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;

//import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;

public class SubmersibleGrab extends SequentialCommandGroup {


    public SubmersibleGrab(Claw clawSubsystem, HorizontalArm horizontalArmSubsystem){
        addCommands(
                new InstantCommand(horizontalArmSubsystem::ground),
                new WaitCommand(500),
                new InstantCommand(clawSubsystem::close),
                new WaitCommand(300),
                new InstantCommand(horizontalArmSubsystem::hover)
        );
        addRequirements(clawSubsystem, horizontalArmSubsystem);
    }

}
