package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.Methods.Camera;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.Drivetrain;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.GlyphMechanism;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.JewelMechanism;

/**
 * Created by easha on 1/13/2018.
 */



@Autonomous (name = "WIGGLE", group = "Red Alliance")

public class autoTest extends LinearOpMode {

    Camera camera = new Camera(telemetry);
    JewelMechanism jewelMechanism = new JewelMechanism();
    GlyphMechanism glyphMechanism = new GlyphMechanism();
    Drivetrain drivetrain = new Drivetrain();
    ElapsedTime time = new ElapsedTime();

    @Override

    public void runOpMode() throws InterruptedException {

        //int counter = 0;
        drivetrain.init(hardwareMap);
        jewelMechanism.init(hardwareMap);
        glyphMechanism.init(hardwareMap);
        camera.init(hardwareMap);

        waitForStart();

        glyphMechanism.wedge();



        for (int counter = 0; counter < 30; counter++) {
            drivetrain.turnWithEncoder(-5, 1);
            drivetrain.turnWithEncoder(10, 1);
            drivetrain.turnWithEncoder(-5, 1);
            drivetrain.drive(0.2, 1);
        }
    }
}