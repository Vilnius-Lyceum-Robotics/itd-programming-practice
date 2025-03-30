package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.LiftSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystems.OuttakeSubsystem;

public class PrepareWall extends ParallelCommandGroup {
    public PrepareWall(OuttakeSubsystem outtakeSubsystem, IntakeSubsystem intakeSubsystem, LiftSubsystem liftSubsystem){
        addCommands(
                new InstantCommand(outtakeSubsystem::toHuman),
                new InstantCommand(liftSubsystem::toHuman),
                new InstantCommand(intakeSubsystem::toPark),
                new InstantCommand(outtakeSubsystem::open),

                new WaitUntilCommand(liftSubsystem::reachedTarget),
                new WaitCommand(2000)
        );
        addRequirements(outtakeSubsystem, intakeSubsystem, liftSubsystem);
    }
}
