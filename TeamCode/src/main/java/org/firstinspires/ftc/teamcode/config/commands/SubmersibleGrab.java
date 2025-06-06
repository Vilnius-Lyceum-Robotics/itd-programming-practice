package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalIntake;

//import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;

public class SubmersibleGrab extends SequentialCommandGroup {


    public SubmersibleGrab(Claw clawSubsystem, HorizontalIntake horizontalIntakeSubsystem){
        addCommands(
                new InstantCommand(clawSubsystem::open),
                new WaitCommand(100),
                new InstantCommand(horizontalIntakeSubsystem::ground),
                new WaitCommand(500),
                new InstantCommand(clawSubsystem::close),
                new WaitCommand(300),
                new InstantCommand(horizontalIntakeSubsystem::clear)
        );
        addRequirements(clawSubsystem, horizontalIntakeSubsystem);
    }

}
