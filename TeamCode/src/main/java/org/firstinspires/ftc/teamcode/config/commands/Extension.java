package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalIntake;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;

public class Extension extends ParallelCommandGroup {
    public Extension(Linkage linkageSubsystem, HorizontalIntake horizontalIntakeSubsystem, Claw clawSubsystem){
        addCommands(
                new InstantCommand(linkageSubsystem::extend),
                new WaitCommand(1200),
                new InstantCommand(horizontalIntakeSubsystem::clear),
                new InstantCommand(clawSubsystem::open)
        );
        addRequirements(linkageSubsystem, horizontalIntakeSubsystem);
    }
}
