package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Chassis {
    private Motor frontRight, frontLeft, rearRight, rearLeft;
    private Telemetry telemetry;
    private MecanumDrive drive;

    public Chassis (HardwareMap hardwareMap, Telemetry telemetry) {
        frontRight = new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_435);
        frontLeft = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        rearRight = new Motor(hardwareMap, "rearRight", Motor.GoBILDA.RPM_435);
        rearLeft = new Motor(hardwareMap, "rearLeft", Motor.GoBILDA.RPM_435);

        this.telemetry = telemetry;

        frontRight.setInverted(true);
        frontLeft.setInverted(true);
        rearRight.setInverted(true);
        rearLeft.setInverted(true);

        drive = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);

    }

    public void robotCentricDriving(double x, double y, double r) {
        drive.driveRobotCentric(x, y, r, false);
    }
}
