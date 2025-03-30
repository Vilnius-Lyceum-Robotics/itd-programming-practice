package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

public class HighBucket extends ParallelCommandGroup {
    public HighBucket(OuttakeSubsystem outtakeSubsystem, LiftSubsystem liftSubsystem) {
        addCommands(
                new InstantCommand(outtakeSubsystem::toBucket),
                new InstantCommand(liftSubsystem::toHighBucket),
                new WaitUntilCommand(liftSubsystem::reachedTarget)
        );
        addRequirements(outtakeSubsystem, liftSubsystem);
    }
}
