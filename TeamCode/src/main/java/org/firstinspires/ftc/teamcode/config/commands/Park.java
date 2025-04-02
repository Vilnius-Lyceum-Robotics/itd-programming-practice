package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

public class Park extends ParallelCommandGroup {
    public Park(OuttakeSubsystem outtakeSubsystem, LiftSubsystem liftSubsystem, IntakeSubsystem intakeSubsystem, ExtendSubsystem extendSubsystem) {
        addCommands(
                new InstantCommand(outtakeSubsystem::toPark),
                new InstantCommand(liftSubsystem::toZero),
                new InstantCommand(intakeSubsystem::toPark),
                new InstantCommand(extendSubsystem::toZero),

                new WaitUntilCommand(liftSubsystem::reachedTarget)
        );
        addRequirements(outtakeSubsystem, liftSubsystem);
    }
}
