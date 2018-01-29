package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by keshp on 10/28/2017.
 */

@Disabled
public class MinhsCode extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;
    private Servo servoArm = null;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status","Initialized");
        telemetry.update();

        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backRight = hardwareMap.get(DcMotor.class, "BR");
        servoArm = hardwareMap.get(Servo.class, "servoArm");

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());

            frontLeft.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
            backLeft.setPower(gamepad1.left_stick_y+gamepad1.left_stick_x+gamepad1.right_stick_x);
            frontRight.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x+gamepad1.right_stick_x);
            backRight.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
            servoArm.setPosition(0.5);

        }




    }

}
