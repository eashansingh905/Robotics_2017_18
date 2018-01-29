package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by keshp on 10/21/2017.
 */

@Disabled
public class Encoder  extends OpMode{

   private DcMotor frontLeft;
    int targetPosition = 90;
    int currentPosition = 0;
    double Kp = 1.0/90.0;
    double motorPower = 0;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("FL");
}

    @Override
    public void loop() {
        currentPosition = frontLeft.getCurrentPosition();
        motorPower = Kp * (targetPosition - currentPosition);
        frontLeft.setPower(motorPower);

    }
}
