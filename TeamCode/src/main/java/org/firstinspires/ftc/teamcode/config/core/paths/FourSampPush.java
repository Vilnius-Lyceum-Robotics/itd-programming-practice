package org.firstinspires.ftc.teamcode.config.core.paths;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;

public class FourSampPush {

    public static Pose start = new Pose(9, 88, Math.toRadians(0));
    public static Pose score1 = new Pose(18, 125, Math.toRadians(-45));
    public static Pose prepare2 = new Pose(62, 121, Math.toRadians(0));
    public static Pose push2 = new Pose(9, 124, Math.toRadians(0));
    public static Pose prepare3 = new Pose(62, 132, Math.toRadians(0));
    public static Pose push3 = new Pose(18, 132, Math.toRadians(0));
    public static Pose prepare4 = new Pose(62, 137, Math.toRadians(0));
    public static Pose push4 = new Pose(29, 137, Math.toRadians(0));
    public static Pose park = new Pose(63, 97, Math.toRadians(-90));

    public static PathChain scorePreload() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                start, score1
                        )
                )
                .setLinearHeadingInterpolation(start.getHeading(), score1.getHeading())
                .build();
    }

    public static PathChain pushSamples() {
        return new PathBuilder()
                //prepare 2
                .addPath(
                        new BezierCurve(
                                score1,
                                new Pose(54, 95),
                                prepare2
                        )
                )
                .setLinearHeadingInterpolation(score1.getHeading(), prepare2.getHeading())

                //push 2
                .addPath(new BezierLine(prepare2, push2))

                //get back to prepare 2
                .addPath(new BezierLine(push2, prepare2))

                //prepare 3
                .addPath(new BezierLine(prepare2, prepare3))

                //push 3
                .addPath(new BezierLine(prepare3, push3))

                //get back to prepare 3
                .addPath(new BezierLine(push3, prepare3))

                //prepare 4
                .addPath(new BezierLine(prepare3, prepare4))

                //push 4
                .addPath(new BezierLine(prepare4, push4))
                .build();
    }

    public static PathChain park() {
        return new PathBuilder()
                .addPath(
                        new BezierLine(
                                push4, park
                        )
                )
                .setLinearHeadingInterpolation(push4.getHeading(), park.getHeading())
                .build();

    }

}
