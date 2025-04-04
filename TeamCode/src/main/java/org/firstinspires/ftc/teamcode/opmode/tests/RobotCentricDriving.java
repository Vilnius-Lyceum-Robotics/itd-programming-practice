package org.firstinspires.ftc.teamcode.opmode.tests;

import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// CODE TAKEN FROM
// https://github.com/FTC-23511/SolversLib/blob/master/examples/src/main/java/org/firstinspires/ftc/teamcode/MecanumDrivingSample.java

@TeleOp(name="Simple TeleOp Driving")
public class RobotCentricDriving extends LinearOpMode {

    private Motor fL, fR, bL, bR;

    @Override
    public void runOpMode() throws InterruptedException {

        fL = new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_435);
        fR = new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_435);
        bL = new Motor(hardwareMap, "leftRear", Motor.GoBILDA.RPM_435);
        bR = new Motor(hardwareMap, "rightRear", Motor.GoBILDA.RPM_435);

        bL.setInverted(true);
        bR.setInverted(true);
        fL.setInverted(true);
        fR.setInverted(true);

        // constructor takes in frontLeft, frontRight, backLeft, backRight motors
        // IN THAT ORDER
        MecanumDrive drive = new MecanumDrive(
                fL, fR, bL, bR
        );

        // the extended gamepad object
        GamepadEx driverOp = new GamepadEx(gamepad1);

        waitForStart();

        while (!isStopRequested()) {

            // Driving the mecanum base takes 3 joystick parameters: leftX, leftY, rightX.
            // These are related to the left stick x value, left stick y value, and
            // right stick x value respectively. These values are passed in to represent the
            // strafing speed, the forward speed, and the turning speed of the robot frame
            // respectively from [-1, 1].

            // optional fourth parameter for squared inputs
            drive.driveRobotCentric(
                    driverOp.getLeftX(),
                    driverOp.getLeftY(),
                    driverOp.getRightX(),
                    false
            );

        }
    }

}