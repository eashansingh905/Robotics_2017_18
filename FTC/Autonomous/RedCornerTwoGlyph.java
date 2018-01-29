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
@Autonomous (name = "Red Corner(Two Glyphs)", group = "Red Alliance")



public class RedCornerTwoGlyph extends LinearOpMode {

    Camera camera = new Camera(telemetry);
    JewelMechanism jewelMechanism = new JewelMechanism();
    GlyphMechanism glyphMechanism = new GlyphMechanism();
    Drivetrain drivetrain = new Drivetrain();
    ElapsedTime time = new ElapsedTime();

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

        //make sure jewel arm is in the center
        jewelMechanism.rotateArm(0.45);
        //lower servo arm
        jewelMechanism.lowerArm();
        //turn on light
        jewelMechanism.ledOn();
        //read color
        jewelMechanism.colorRed(); //true = red, false = blue
        //turn to knock off jewel using servo arm
        if (jewelMechanism.colorRed() == true) {
            jewelMechanism.rotateArm(0.9);//0.8
            sleep(1000);
            //turn off light
            jewelMechanism.ledOff();
            jewelMechanism.rotateArm(0.45);
            //raise servo arm
            jewelMechanism.raiseArm();
            sleep(1000);
        } else {
            jewelMechanism.rotateArm(0.1);//0.2
            sleep(1000);
            //turn off light
            jewelMechanism.ledOff();
            jewelMechanism.rotateArm(0.45);
            //raise servo arm
            jewelMechanism.raiseArm();
            sleep(1000);
        }

        //right drivetrain.drive(1.25, 0.3);
        //center drivetrain.drive(1.66, 0.3);
        //left drivetrain.drive(1.95, 0.3);

        //drive straight x feet based on pictograph
        if (key.equals("RIGHT")) {
            drivetrain.drive(1.25, 0.3);
        } else if (key.equals("CENTER")) {
            drivetrain.drive(1.66, 0.3);
        } else if (key.equals("LEFT")) {
            drivetrain.drive(1.95, 0.3);
        } else if (key.equals("UNKNOWN")) {
            //"CENTER" by default
            drivetrain.drive(1.66, 0.3);
        }

        drivetrain.turnWithEncoder(-72, 0.5);
        //release glyph
        glyphMechanism.down();
        glyphMechanism.openTop();
        glyphMechanism.openBottom();
        //push glyph into cryptobox
        drivetrain.drive(.4, 1);
        drivetrain.drive(-.2, 1);

        drivetrain.turnWithEncoder(72, 0.5);
        glyphMechanism.up();

        if (key.equals("RIGHT")) {
            drivetrain.drive(1.3, 0.3); //1.25
        } else if (key.equals("CENTER")) {
            drivetrain.drive(1.11, 0.3); //1.66
        } else if (key.equals("LEFT")) {
            drivetrain.drive(0.83, 0.3); // 1.95
        } else if (key.equals("UNKNOWN")) {
            //"CENTER" by default
            drivetrain.drive(1.11, 0.3);
        }
        glyphMechanism.closeTop();
        glyphMechanism.closeBottom();
        sleep(1000);
        glyphMechanism.up();
        if (key.equals("RIGHT") || key.equals("LEFT")) {
            drivetrain.drive(-1.11, 0.3); //1.25
        }
        else {
            drivetrain.drive(-0.83, 0.3);
        }
        drivetrain.turnWithEncoder(-72, 0.5);
        glyphMechanism.down();
        glyphMechanism.down();
        glyphMechanism.openTop();
        glyphMechanism.openBottom();
        //push glyph into cryptobox
        drivetrain.drive(.2, 1);
        drivetrain.drive(-.25, 1);
    }
}


