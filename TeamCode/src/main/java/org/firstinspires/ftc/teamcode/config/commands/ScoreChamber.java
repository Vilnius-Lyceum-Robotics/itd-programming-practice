package org.firstinspires.ftc.teamcode.config.commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;



public class ScoreChamber extends SequentialCommandGroup {
//    public ScoreChamber(Outtake outtake){
//        addCommands(
//                new InstantCommand(() -> outtake.setElbowState(Outtake.ElbowState.SCORE)),
//                new WaitCommand(500),
//                new InstantCommand(outtake::toScoreChamber),
//                new WaitCommand(400),
//                new InstantCommand(outtake::open),
//                new WaitUntilCommand(outtake::reachedTarget),
//                new PrepareWall(outtake)
//        );
//        addRequirements(outtake);
//    }
}
