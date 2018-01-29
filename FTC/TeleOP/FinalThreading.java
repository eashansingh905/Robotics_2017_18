package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by keshp on 12/18/2017.
 */
@TeleOp(name = "Final Teleop(With Threading)")
public class FinalThreading extends LinearOpMode {
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
    double SJX = 0.45;
    double SJY = 0.85;
    // relic rotation //
    double Rotation = 1;
    // relic claw fine-toning //
    double RFT = 0;
    double CFT = 0;
    // toggles //
    int topToggle = 0;
    int botToggle = 0;
    int jewelToggle = 1;
    int halfSpeedToggle = 0;
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    Thread glyph = new Thread(new Runnable() {
        @Override
        public void run() {
            // Glyph Coding //
            while(opModeIsActive()) {
                // close position bottom //
                if (gamepad2.right_bumper && botToggle == 0) {
                    glyphLeftBottom.setPosition(0.55);
                    glyphRightBottom.setPosition(0.35);
                    botToggle = 1;
                    sleep(500);
                }
                // open position bottom //
                if (gamepad2.right_bumper && botToggle == 1) {
                    glyphLeftBottom.setPosition(0);
                    glyphRightBottom.setPosition(0.85);
                    botToggle = 0;
                    sleep(500);
                }
                // close position top //
                if (gamepad2.left_bumper && topToggle == 0) {
                    glyphLeftTop.setPosition(0.5);
                    glyphRightTop.setPosition(0.3);
                    topToggle = 1;
                    sleep(500);
                }
                // open position top //
                if (gamepad2.left_bumper && topToggle == 1) {
                    glyphLeftTop.setPosition(0);
                    glyphRightTop.setPosition(0.8);
                    topToggle = 0;
                    sleep(500);
                }
                // aligment //
                if (gamepad2.y) {
                    glyphLeftBottom.setPosition(0.3);
                    glyphRightBottom.setPosition(0.6);
                    glyphLeftTop.setPosition(0.3);
                    glyphRightTop.setPosition(0.6);
                }
                // open all Servos //
                if (gamepad2.x) {
                    glyphLeftTop.setPosition(0.5);
                    glyphRightTop.setPosition(0.3);
                    glyphLeftBottom.setPosition(0.0);
                    glyphRightBottom.setPosition(0.8);
                    topToggle=0;
                    botToggle=0;
                }
                // close all Servos //
                if (gamepad2.b) {
                    glyphLeftTop.setPosition(0);
                    glyphRightTop.setPosition(0.8);
                    glyphLeftBottom.setPosition(0.55);
                    glyphRightBottom.setPosition(0.35);
                    topToggle=1;
                    botToggle=1;
                }
                // tight //
                if (gamepad2.a) {
                    glyphLeftTop.setPosition(0.15); //0.3
                    glyphRightTop.setPosition(0.65); //0.5
                    glyphLeftBottom.setPosition(0.35); //0.3
                    glyphRightBottom.setPosition(0.45); //0.5
                }
            }
        }
    });


    Thread halfSpeed = new Thread(new Runnable() {
        @Override
        public void run() {
            while (opModeIsActive()) {
                // Driving Coding //
                // toggle //
                if (gamepad1.x && halfSpeedToggle ==0) {
                    halfSpeedToggle = 1;
                    sleep(500);
                }
                if (gamepad1.x && halfSpeedToggle ==1) {
                    halfSpeedToggle = 0;
                    sleep(500);
                }
                // changing speed //
                if (halfSpeedToggle == 0) {
                    leftStickY = gamepad1.left_stick_y;
                    leftStickX = gamepad1.left_stick_x;
                    rightStickX = gamepad1.right_stick_x;
                }
                if (halfSpeedToggle == 1) {
                    leftStickY = gamepad1.left_stick_y/2;
                    leftStickX = gamepad1.left_stick_x/2;
                    rightStickX = gamepad1.right_stick_x/2;
                }
                // drive function //
                frontLeft.setPower((  leftStickY - leftStickX + rightStickX));
                frontRight.setPower((-leftStickY - leftStickX + rightStickX));
                backLeft.setPower((   leftStickY + leftStickX + rightStickX));
                backRight.setPower(( -leftStickY + leftStickX + rightStickX));
            }
        }
    });

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
        // multi-threading//
        glyph.start();
        halfSpeed.start();
        // Starting Position Of All Servos //
        // Jewels Starting Position //
        // Up Position //
        SJewelsX.setPosition(SJX);
        // Glyph Starting Position //
        // Open Position //
        glyphLeftTop.setPosition(0.5);
        glyphRightTop.setPosition(0.3);
        glyphLeftBottom.setPosition(0);
        glyphRightBottom.setPosition(0.85);
        // Relic Starting Position //
        // Close Position //
        SRelic.setPosition(.45);
        rotationRelic.setPosition(0);
        while(opModeIsActive()) {
////////////////////////////////////////////////////////////////////////////////////////////////////
            // Lift Coding //
            liftLeft.setPower(gamepad2.right_stick_y);
            liftRight.setPower(gamepad2.right_stick_y);
////////////////////////////////////////////////////////////////////////////////////////////////////
            // Horizontal Lift Coding //
            if( gamepad1.dpad_left)
                HL.setPower(0.75);
            else if (gamepad1.dpad_right)
                HL.setPower(-0.75);
            else
                HL.setPower(0);
////////////////////////////////////////////////////////////////////////////////////////////////////
            // Relic Mechanism Coding // (
            if (gamepad1.dpad_up)
                CFT+=0.01;
            if (gamepad1.dpad_down)
                CFT-=0.01;
            // Boundaries //
            if (CFT > 1)
                CFT = 1;
            if (CFT < 0)
                CFT=0;
            SRelic.setPosition(CFT);
            // Relic Rotation Coding //
            if (gamepad1.x)
                rotationRelic.setPosition(0);
            if (gamepad1.y)
                rotationRelic.setPosition(0.18);
            if (gamepad1.b)
                rotationRelic.setPosition(0.8);
////////////////////////////////////////////////////////////////////////////////////////////////////
            // jewel check //
            if (gamepad1.right_stick_button || gamepad1.left_bumper)
                SJewelsY.setPosition(SJY);
////////////////////////////////////////////////////////////////////////////////////////////////////
        }
    }
}
