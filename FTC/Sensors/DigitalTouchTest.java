package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

//@Autonomous(name = "Sensor: Digital touch(TOUCH)", group = "Sensor")
@Disabled
public class DigitalTouchTest extends LinearOpMode {


    DigitalChannel digitalTouch;

    @Override
    public void runOpMode() {

        digitalTouch = hardwareMap.get(DigitalChannel.class, "TS");

        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();
        while (opModeIsActive()) {
            if (digitalTouch.getState() == true) {
                telemetry.addData("Digital Touch", "Is Pressed");
            } else {
                telemetry.addData("Digital Touch", "Is Not Pressed");
            }
            telemetry.update();
        }
    }
}
