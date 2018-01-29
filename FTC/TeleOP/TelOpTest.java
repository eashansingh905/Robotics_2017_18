package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by keshp on 11/9/2017.
 **/

//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "test Teleop")
@Disabled
public class TelOpTest extends LinearOpMode {
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // motors Identifying //
    // Drive Train //
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    // Lift //
    private DcMotor liftRight;
    private DcMotor liftLeft;
    // Horizontal Lift //
    private DcMotor HL;
    // servo Identifying //
    // Jewels Mechanism //
    private Servo SJewelsX;
    private Servo SJewelsY;
    // Glyph Mechanism //
    private Servo glyphLeftTop;
    private Servo glyphLeftBottom;
    private Servo glyphRightTop;
    private Servo glyphRightBottom;
    // relic  mechanism //
    private Servo SRelic;
    private Servo rotationRelic;
    // Variables //
    // Motor Variables //
    // DriveTrain Controls //
    double leftStickY;
    double leftStickX;
    double rightStickX;
    // Servo Variable //
    // Jewels  fine-toning //
    double SJX = 0;
    double SJY = 1;
    // relic rotation //
    double Rotation = 0.5;
    // relic claw fine-toning //
    double RFT = 0;
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void runOpMode() throws InterruptedException {
        // motors Connecting //
        // Drive Train //
        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backLeft = hardwareMap.dcMotor.get("BL");
        backRight = hardwareMap.dcMotor.get("BR");
        // Lift //
        liftLeft = hardwareMap.dcMotor.get("LL");
        liftRight = hardwareMap.dcMotor.get("LR");
        // Horizontal Lift //
        HL = hardwareMap.dcMotor.get("HL");
        // Servo Connecting //
        // Jewels Mechanism //
        SJewelsX = hardwareMap.servo.get("SJX");
        SJewelsY = hardwareMap.servo.get("SJY");
        // Glyph Mechanism //
        glyphLeftTop = hardwareMap.servo.get("GTL");
        glyphLeftBottom = hardwareMap.servo.get("GBL");
        glyphRightTop = hardwareMap.servo.get("GTR");
        glyphRightBottom = hardwareMap.servo.get("GBR");
        // Relic Mechanism //
        SRelic = hardwareMap.servo.get("SR");
        rotationRelic = hardwareMap.servo.get("RR");
////////////////////////////////////////////////////////////////////////////////////////////////////
        waitForStart();
        // Starting Position Of All Servos //
        // Jewels Starting Position //
        // Up Position //
        SJewelsX.setPosition(0.45);
        SJewelsY.setPosition(SJY);
        // Glyph Starting Position //
        // Open Position //
        glyphLeftTop.setPosition(0.85);
        glyphLeftBottom.setPosition(0);
        glyphRightTop.setPosition(0.1);
        glyphRightBottom.setPosition(0.85);
        // Relic Starting Position //
        // Close Position //
        SRelic.setPosition(.45);
        rotationRelic.setPosition(0);
////////////////////////////////////////////////////////////////////////////////////////////////////
        while (opModeIsActive()) {
            // Driving Coding //
            leftStickY  = gamepad1.left_stick_y;
            leftStickX  = gamepad1.left_stick_x;
            rightStickX = gamepad1.right_stick_x;

            frontLeft.setPower(leftStickY - leftStickX + rightStickX);
            frontRight.setPower(-leftStickY - leftStickX + rightStickX);
            backLeft.setPower(leftStickY + leftStickX + rightStickX);
            backRight.setPower(-leftStickY + leftStickX + rightStickX);
////////////////////////////////////////////////////////////////////////////////////////////////////
            // Jewels Coding //
            // y-axis //
            if(gamepad1.dpad_up)
                SJY+=.0025;
            if(gamepad1.dpad_down)
                SJY-=.0025;
            // resetting Positions //
            SJewelsY.setPosition(SJY);
            SJewelsX.setPosition(0.5);
////////////////////////////////////////////////////////////////////////////////////////////////////
            // Glyph Coding //
            // close position bottom //
            if (gamepad2.b) {
                glyphLeftBottom.setPosition(0.55);//
                glyphRightBottom.setPosition(0.35);//
            }
            // open position bottom //
            if (gamepad2.a) {
                glyphLeftBottom.setPosition(0.05);//
                glyphRightBottom.setPosition(0.8);//
            }
            // aligment //
            if (gamepad2.right_stick_button) {
                glyphLeftBottom.setPosition(0.4);//
                glyphRightBottom.setPosition(0.5);//
                glyphLeftTop.setPosition(0.5);
                glyphRightTop.setPosition(0.5);
            }
            // open position top //
            if (gamepad2.x) {
                glyphLeftTop.setPosition(0.85);
                glyphRightTop.setPosition(0.1);
            }
            // close position top //
            if (gamepad2.y) {
                glyphLeftTop.setPosition(0.4);
                glyphRightTop.setPosition(0.6);
            }
////////////////////////////////////////////////////////////////////////////////////////////////////
            // Lift Coding //
            liftLeft.setPower(gamepad2.left_stick_y);
            liftRight.setPower(gamepad2.left_stick_y);
////////////////////////////////////////////////////////////////////////////////////////////////////
            // Horizontal Lift Coding //
            HL.setPower(gamepad2.right_stick_x);
////////////////////////////////////////////////////////////////////////////////////////////////////
            // Relic Mechanism Coding //
            // Close Position //
            if (gamepad2.right_bumper)
                SRelic.setPosition(.45);
            // Open Position //
            if (gamepad2.left_bumper)
                SRelic.setPosition(.6);
            // Relic Rotation Coding //
            RFT += gamepad2.right_stick_y/15;
            if(RFT > 1)
                RFT = 1;
            if(RFT < 0)
                RFT = 0;
            rotationRelic.setPosition(RFT);
////////////////////////////////////////////////////////////////////////////////////////////////////
        }
    }
}