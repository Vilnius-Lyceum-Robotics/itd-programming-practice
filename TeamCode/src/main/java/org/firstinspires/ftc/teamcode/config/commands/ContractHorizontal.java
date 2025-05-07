package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;

public class ContractHorizontal extends ParallelCommandGroup {
    public ContractHorizontal(ExtendSubsystem extendSubsystem, IntakeSubsystem intakeSubsystem){
        addCommands(
                new InstantCommand(extendSubsystem::toZero),
                new InstantCommand(intakeSubsystem::toHover),
                new WaitUntilCommand(extendSubsystem::reachedTarget)
        );
        addRequirements(extendSubsystem, intakeSubsystem);
    }
}
