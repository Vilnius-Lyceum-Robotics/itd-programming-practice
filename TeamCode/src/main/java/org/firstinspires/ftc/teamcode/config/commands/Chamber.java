package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

public class Chamber extends SequentialCommandGroup {
    public Chamber(OuttakeSubsystem outtakeSubsystem){
        addCommands(
                new InstantCommand(outtakeSubsystem::toChamber),
                new WaitUntilCommand(outtakeSubsystem::reachedTarget)
        );
        addRequirements(outtakeSubsystem);
    }
}
