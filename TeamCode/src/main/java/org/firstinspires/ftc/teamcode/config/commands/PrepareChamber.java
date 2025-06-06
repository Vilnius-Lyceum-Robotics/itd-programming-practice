package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.Outtake;

public class PrepareChamber extends SequentialCommandGroup {
    public PrepareChamber(Outtake outtake){
        addCommands(
                new InstantCommand(outtake::close),
                new WaitCommand(300),
                new InstantCommand(outtake::toPrepChamber),
                new WaitCommand(100),
                new InstantCommand(outtake::midOpen),
                new WaitUntilCommand(outtake::reachedTarget)
        );
        addRequirements(outtake);
    }
}
