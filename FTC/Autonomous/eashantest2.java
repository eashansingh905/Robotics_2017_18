package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.Methods.Camera;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.Drivetrain;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.GlyphMechanism;
import org.firstinspires.ftc.teamcode.Autonomous.Methods.JewelMechanism;


/**
 *
 * Created by Eashan on 11/30/2017.
 */
@Autonomous (name = " Eashan Test Left Turn", group = "Red Alliance")

public class eashantest2 extends LinearOpMode {

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

        //read pictograph

        String key = camera.getKey();
        camera.cameraOff();

        //hold onto glyph
        glyphMechanism.closeTop();
        glyphMechanism.closeBottom();
        sleep(1000); //so that it can finish closing before it goes up
        glyphMechanism.up();

        jewelMechanism.rotateArm(0.45);
        //lower servo arm
        //make sure jewel arm is in the center
        jewelMechanism.lowerArm();
        sleep(100);
        //turn on light
        jewelMechanism.ledOn();
        //read color
        jewelMechanism.colorRed(); //true = red, false = blue
        //turn to knock off jewel using servo arm
        if (jewelMechanism.colorRed() == true) {
            jewelMechanism.rotateArm(0.9);
            sleep(1000);
            //turn off light
            jewelMechanism.ledOff();
            jewelMechanism.rotateArm(0.45);
           //raise servo arm
            jewelMechanism.raiseArm();
            sleep(1000);
        } else {
            jewelMechanism.rotateArm(0.1);
            sleep(1000);
            //turn off light
            jewelMechanism.ledOff();
            jewelMechanism.rotateArm(0.45);
            //raise servo arm
            jewelMechanism.raiseArm();
            sleep(1000);
        }
        //drives to cryptobox


        //strafe x feet based on pictograph column
        if (key == "RIGHT") {
            drivetrain.drive(1, 0.3, encoderValues[0]);
            drivetrain.diagonalLeft(1, 0.5);
            glyphMechanism.wedge();
            drivetrain.drive(-0.25, 1);
            glyphMechanism.openBottom();
            glyphMechanism.openTop();
            //drivetrain.drive(0.5, 0.7);
            //drivetrain.drive(-0.3, 1);
        }
        else if (key == "CENTER") {
            drivetrain.drive(1, 0.3, encoderValues[0]);
            drivetrain.strafe(-.45, 1); // -0.4
            drivetrain.diagonalLeft(1, 0.5);
            glyphMechanism.wedge();
            drivetrain.drive(-0.25, 1);
            glyphMechanism.openBottom();
            glyphMechanism.openTop();

        }
        else if (key == "LEFT") {
            drivetrain.drive(1, 0.3, encoderValues[0]);
            drivetrain.strafe(-.75, 1);
            drivetrain.diagonalLeft(1, 0.5);
            glyphMechanism.wedge();
            drivetrain.drive(-0.3, 1);
            glyphMechanism.openBottom();
            glyphMechanism.openTop();
            //glyphMechanism.openBottom();
            //drivetrain.drive(0.5, 0.7);
            //drivetrain.drive(-0.3, 1);

        }
        else {
            drivetrain.drive(1, 0.3, encoderValues[0]);
            drivetrain.strafe(-.45, 1); // -0.4
            drivetrain.diagonalLeft(1, 0.5);
            glyphMechanism.wedge();
            drivetrain.drive(-0.25, 1);
            glyphMechanism.openBottom();
            glyphMechanism.openTop();
            //10drivetrain.drive(0.5, 0.7);
            //drivetrain.drive(-0.3, 1);

        /*

        //release glyph
        drivetrain.turnWithEncoder(36,0.5, encoderValues[2]);
        //glyphMechanism.openBottom();
        glyphMechanism.down();
        glyphMechanism.openBottom();
        drivetrain.turnWithEncoder(-36,0.5, encoderValues[3]);
        drivetrain.strafe(-0.1,0.2);
        drivetrain.drive(0.6,1, encoderValues[4]);
        drivetrain.drive(-0.3,1, encoderValues[5]);
        glyphMechanism.down();
        // glyphMechanism.openTop();
        //push glyph into cryptobox
        //drivetrain.drive(.5, 1);
        //drivetrain.drive(-.2, 1);
        while (true) {
            telemetry.update();
            telemetry.addData("hi", 5);
            for (int a = 0; a < 6; a++) {
                for (int b = 0; b < 2; b++) {
                    telemetry.addData(""+a + " " + b, encoderValues[a][b]);
                }
            }
            */
        }

    }
}
