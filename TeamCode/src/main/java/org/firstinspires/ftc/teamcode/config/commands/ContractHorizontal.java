package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.subsystems.HorizontalArm;
import org.firstinspires.ftc.teamcode.config.subsystems.Linkage;

//import org.firstinspires.ftc.teamcode.config.subsystems.ExtendSubsystem;
//import org.firstinspires.ftc.teamcode.config.subsystems.IntakeSubsystem;

public class ContractHorizontal extends ParallelCommandGroup {
    public ContractHorizontal(Linkage linkageSubsystem, HorizontalArm horizontalArmSubsystem){
        addCommands(
                new InstantCommand(linkageSubsystem::retract),
                new InstantCommand(horizontalArmSubsystem::clear),
                new WaitCommand(400) // To make sure linkage contracts
        );
        addRequirements(linkageSubsystem, horizontalArmSubsystem);
    }
}
