package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

public class PrepareChamber extends SequentialCommandGroup {
    public PrepareChamber(OuttakeSubsystem outtakeSubsystem){
        addCommands(
                new InstantCommand(outtakeSubsystem::toPrepChamber),
                new WaitCommand(100),
                new InstantCommand(outtakeSubsystem::midOpen),
                new WaitUntilCommand(outtakeSubsystem::reachedTarget)
        );
        addRequirements(outtakeSubsystem);
    }
}
