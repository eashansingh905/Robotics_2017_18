package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.Methods.Camera;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.Drivetrain;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.GlyphMechanism;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.JewelMechanism;


/**
 * Created by easha on 1/24/+2018.
 */

@Autonomous (name = " Left Diagonal", group = "Red Alliance")

public class diagonal extends LinearOpMode {

    Camera camera = new Camera(telemetry);
    JewelMechanism jewelMechanism = new JewelMechanism();
    GlyphMechanism glyphMechanism = new GlyphMechanism();
    Drivetrain drivetrain = new Drivetrain();
    ElapsedTime time = new ElapsedTime();

    int[][] encoderValues = new int[6][2];


    @Override

    public void runOpMode() throws InterruptedException {

        drivetrain.init(hardwareMap);
        glyphMechanism.init(hardwareMap);
        jewelMechanism.init(hardwareMap);
        camera.init(hardwareMap);

        waitForStart();

        drivetrain.diagonalLeft(1,0.5);
        }

    }


