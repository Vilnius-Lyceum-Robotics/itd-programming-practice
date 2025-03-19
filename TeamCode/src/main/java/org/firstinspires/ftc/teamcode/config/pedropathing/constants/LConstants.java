package org.firstinspires.ftc.teamcode.config.pedropathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class LConstants {
    static {
        //TODO: Tune everything

        PinpointConstants.forwardY = 134; // From CAD
        PinpointConstants.strafeX = 80; // From CAD
        PinpointConstants.distanceUnit = DistanceUnit.MM;
        PinpointConstants.hardwareMapName = "pinpoint";
        PinpointConstants.useYawScalar = false;
        PinpointConstants.yawScalar = 1.0;
        PinpointConstants.useCustomEncoderResolution = false;
        PinpointConstants.encoderResolution = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD;
        PinpointConstants.customEncoderResolution = 13.26291192; //Not going to be used
        PinpointConstants.forwardEncoderDirection = GoBildaPinpointDriver.EncoderDirection.REVERSED;
        PinpointConstants.strafeEncoderDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;



//        Old config meant for use without Pinpoint
//        TwoWheelConstants.forwardTicksToInches = .001989436789;
//        TwoWheelConstants.strafeTicksToInches = .001989436789;
//        TwoWheelConstants.forwardY = 1;
//        TwoWheelConstants.strafeX = -2.5;
//        TwoWheelConstants.forwardEncoder_HardwareMapName = "leftFront";
//        TwoWheelConstants.strafeEncoder_HardwareMapName = "rightRear";
//        TwoWheelConstants.forwardEncoderDirection = Encoder.REVERSE;
//        TwoWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
//        TwoWheelConstants.IMU_HardwareMapName = "imu";
//        TwoWheelConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT);
    }
}




