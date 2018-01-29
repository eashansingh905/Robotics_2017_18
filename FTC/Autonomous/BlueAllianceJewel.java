package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Methods.JewelMechanism;

/**
 * Created by keshp on 1/2/2018.
 */
@Autonomous(name = "Blue Alliance Jewel", group = "Blue Alliance")
public class BlueAllianceJewel extends LinearOpMode {

    JewelMechanism jewelMechanism = new JewelMechanism();

    @Override
    public void runOpMode() throws InterruptedException {

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
    }
}
