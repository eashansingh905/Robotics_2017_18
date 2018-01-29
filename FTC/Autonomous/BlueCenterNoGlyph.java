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
@Autonomous (name = "Blue Center(No Glyph)", group = "Blue Alliance")



public class BlueCenterNoGlyph extends LinearOpMode {

    JewelMechanism jewelMechanism = new JewelMechanism();
    Drivetrain drivetrain = new Drivetrain();
    ElapsedTime time = new ElapsedTime();

    @Override

    public void runOpMode() throws InterruptedException {

        drivetrain.init(hardwareMap);
        jewelMechanism.init(hardwareMap);

        waitForStart();

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
            jewelMechanism.rotateArm(0.1);//0.8
            sleep(1000);
            //turn off light
            jewelMechanism.ledOff();
            jewelMechanism.rotateArm(.45);
            //raise servo arm
            jewelMechanism.raiseArm();
            sleep(1000);
        }
        else {
            jewelMechanism.rotateArm(0.9);//0.2
            sleep(1000);
            //turn off light
            jewelMechanism.ledOff();
            jewelMechanism.rotateArm(0.45);
            //raise servo arm
            jewelMechanism.raiseArm();
            sleep(1000);
        }
        //drives to cryptobox
        drivetrain.drive(-1.23, 0.3);
        //strafe x feet based on pictograph column
        drivetrain.strafe(-0.43,0.3);

    }
}

