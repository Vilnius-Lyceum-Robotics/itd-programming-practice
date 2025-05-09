package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

public class ScoreChamber extends SequentialCommandGroup {
    public ScoreChamber(OuttakeSubsystem outtakeSubsystem){
        addCommands(
                new InstantCommand(() -> outtakeSubsystem.setElbowState(OuttakeSubsystem.ElbowState.SCORE)),
                new WaitCommand(500),
                new InstantCommand(outtakeSubsystem::toScoreChamber),
                new WaitCommand(500),
                new InstantCommand(outtakeSubsystem::open),
                new WaitUntilCommand(outtakeSubsystem::reachedTarget),
                new PrepareWall(outtakeSubsystem)
        );
        addRequirements(outtakeSubsystem);
    }
}
