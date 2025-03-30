package org.firstinspires.ftc.teamcode.config.pedropathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class LConstants {
    static {
        //TODO: Tune everything

        PinpointConstants.forwardY = 13.4; // From CAD
        PinpointConstants.strafeX = 8; // From CAD
        PinpointConstants.distanceUnit = DistanceUnit.CM;
        PinpointConstants.hardwareMapName = "pinpoint";
        PinpointConstants.useYawScalar = false;
        PinpointConstants.yawScalar = 1.0;
        PinpointConstants.useCustomEncoderResolution = false;
        PinpointConstants.encoderResolution = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD;
        PinpointConstants.customEncoderResolution = 13.26291192; //Not going to be used
        PinpointConstants.forwardEncoderDirection = GoBildaPinpointDriver.EncoderDirection.REVERSED;
        PinpointConstants.strafeEncoderDirection = GoBildaPinpointDriver.EncoderDirection.REVERSED;
    }
}




