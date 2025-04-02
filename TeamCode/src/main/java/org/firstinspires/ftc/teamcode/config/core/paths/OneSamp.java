package org.firstinspires.ftc.teamcode.config.core.paths;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
public class OneSamp {

    public static Pose start = new Pose(89, 88, Math.toRadians(180));
    public static Pose score = new Pose(18, 125, Math.toRadians(135));
    public static Pose park = new Pose(62, 98, Math.toRadians(90));

    public static PathChain score1() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                start, score
                        )
                )
                .setLinearHeadingInterpolation(start.getHeading(), score.getHeading())
                .build();

    }

    public static PathChain park() {
        return new PathBuilder()
                .addPath(
                        new BezierCurve(
                                score,
                                new Pose(28, 108),
                                new Pose(62, 120),
                                park
                        )
                )
                .setLinearHeadingInterpolation(score.getHeading(), park.getHeading())
                .build();
    }
}
