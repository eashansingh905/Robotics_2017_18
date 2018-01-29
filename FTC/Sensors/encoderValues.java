package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by keshp on 1/7/2018.
 */
@TeleOp(name = "EnCoDeR vAlUeS",group = "Sensors")
public class encoderValues extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backLeft = hardwareMap.dcMotor.get("BL");
        backRight = hardwareMap.dcMotor.get("BR");

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Status", "Initialized");
            telemetry.update();
            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            // run until the end of the match (driver presses STOP.
                // Show the elapsed game time and wheel power.
                telemetry.addData("Status", "Front Left Encoder: " + frontLeft.getCurrentPosition());
                telemetry.addData("Status", "Front Right Encoder: " + frontRight.getCurrentPosition());
                telemetry.addData("Status", "Back Left Encoder: " + backLeft.getCurrentPosition());
                telemetry.addData("Status", "Back Right Encoder: " + backRight.getCurrentPosition());
                telemetry.update();
        }
    }
}
