package org.firstinspires.ftc.teamcode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOp.*;

/**
 * Created by keshp on 12/17/2017.
 */
//@TeleOp(name = "Thread Test")
@Disabled
public class threadtest extends LinearOpMode{
    Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime runtime = new ElapsedTime();

            telemetry.addData("Status", "Initialized");
            telemetry.update();
            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {

                // Show the elapsed game time and wheel power.
                telemetry.addData("Status(Thread one)", "Run Time: " + runtime.toString());
                telemetry.update();
            }

        }
    });

    Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime runtime = new ElapsedTime();

            telemetry.addData("Status", "Initialized");
            telemetry.update();
            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {

                // Show the elapsed game time and wheel power.
                telemetry.addData("Status(Thread two)", "Run Time: " + runtime.toString());
                telemetry.update();
            }

        }

    });

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        thread2.start();
        thread1.start();

        while(opModeIsActive()) {

        }

    }
}

