package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by Minh on 11/11/2017.
 */

//@TeleOp(name = "Clockwork Mania TeleOp")
@Disabled
public class ClockworkCodeTeleOp extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    private DcMotor Conveyor;
    private DcMotor leftCascade;
    private DcMotor rightCascade;

    private Servo servoArm;
    private CRServo topLeft;
    private CRServo midLeft;
    private CRServo bottomLeft;
    private CRServo topRight;
    private CRServo midRight;
    private CRServo bottomRight;


    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status","Initialized");
        telemetry.update();

        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backRight = hardwareMap.get(DcMotor.class, "BR");

        Conveyor = hardwareMap.get(DcMotor.class, "C");
        leftCascade = hardwareMap.get(DcMotor.class, "LL");
        rightCascade = hardwareMap.get(DcMotor.class, "RL");


        servoArm = hardwareMap.get(Servo.class, "J");
        topLeft = hardwareMap.get(CRServo.class, "TL");
        midLeft = hardwareMap.get(CRServo.class, "ML");
        bottomLeft = hardwareMap.get(CRServo.class, "BL");
        topRight = hardwareMap.get(CRServo.class, "TR");
        midRight = hardwareMap.get(CRServo.class, "MR");
        bottomRight = hardwareMap.get(CRServo.class, "BR");


        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {

            frontLeft.setPower(-gamepad2.left_stick_x - gamepad2.right_stick_x + gamepad2.right_stick_y);
            backLeft.setPower(gamepad2.left_stick_x - gamepad2.right_stick_x + gamepad2.right_stick_y);
            frontRight.setPower(-gamepad2.left_stick_x - gamepad2.right_stick_x - gamepad2.right_stick_y);
            backRight.setPower(gamepad2.left_stick_x - gamepad2.right_stick_x - gamepad2.right_stick_y);
            // all positive == right turn //

            Conveyor.setPower(gamepad1.left_stick_y);
            leftCascade.setPower(gamepad1.right_stick_y);
            rightCascade.setPower(-gamepad1.right_stick_y);


            //in
            if (gamepad1.x) {
                topLeft.setPower(0.75);
                midLeft.setPower(0.75);
                bottomLeft.setPower(0.75);
                topRight.setPower(-0.75);
                midRight.setPower(-0.75);
                bottomRight.setPower(-0.75);
            }
            //out
            else if (gamepad1.y) {
                topLeft.setPower(-0.75);
                midLeft.setPower(-0.75);
                bottomLeft.setPower(-0.75);
                topRight.setPower(0.75);
                midRight.setPower(0.75);
                bottomRight.setPower(0.75);
            }
            else {
                topLeft.setPower(0);
                midLeft.setPower(0);
                bottomLeft.setPower(0);
                topRight.setPower(0);
                midRight.setPower(0);
                bottomRight.setPower(0);
            }

            if (gamepad1.right_bumper) {
                servoArm.setPosition(0);
            }
            if (gamepad1.left_bumper) {
                servoArm.setPosition(1);
            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Servo Position", servoArm.getPosition());
            telemetry.addData("Status","Running");
            telemetry.update();
        }
    }
}

