package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.Claw;
import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalArm;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;

public class Extension extends ParallelCommandGroup {
    public Extension(Linkage linkageSubsystem, HorizontalArm horizontalArmSubsystem, Claw clawSubsystem){
        addCommands(
                new InstantCommand(linkageSubsystem::extend),
                new WaitCommand(1200),
                new InstantCommand(horizontalArmSubsystem::clear),
                new InstantCommand(clawSubsystem::open)
        );
        addRequirements(linkageSubsystem, horizontalArmSubsystem);
    }
}
