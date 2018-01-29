package org.firstinspires.ftc.teamcode.Autonomous.Methods;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
/**
 * Created by Ranade-HP on 11/8/2017.
 */

public class GlyphMechanism {

    private Servo gTopLeft;
    private Servo gTopRight;
    private Servo gBottomLeft;
    private Servo gBottomRight;
    private DcMotor liftRight;
    private DcMotor liftLeft;
    ElapsedTime time = new ElapsedTime();

    public void init (HardwareMap hardwareMap) {
        gTopLeft = hardwareMap.servo.get("GTL");
        gTopRight = hardwareMap.servo.get ("GTR");
        gBottomLeft = hardwareMap.servo.get("GBL");
        gBottomRight = hardwareMap.servo.get("GBR");
        liftLeft = hardwareMap.dcMotor.get("LL");
        liftRight = hardwareMap.dcMotor.get("LR");
    }

    public void closeTop () {
        gTopLeft.setPosition(0.4);// original 0.2
        gTopRight.setPosition(0.6); // original 0.53
    }

    public void openTop () {
        gTopLeft.setPosition(0.85);
        gTopRight.setPosition(0.1);
    }

    public void wedge(){
        gBottomLeft.setPosition(0.35);
        gBottomRight.setPosition(0.45);

    }

    public void closeBottom() {
        gBottomLeft.setPosition(.55);// original 0.2
        gBottomRight.setPosition(0.35); // original 0.53
    }

    public void openBottom() {
        gBottomLeft.setPosition(0.05);
        gBottomRight.setPosition(0.8);
    }

    public void up () {
        time.reset();
        while (time.milliseconds() < 700) {
            liftLeft.setPower(1);
            liftRight.setPower(1);
        }
        liftRight.setPower(0);
        liftLeft.setPower(0);
    }

    public void down () {
        time.reset();
        while (time.milliseconds() < 700) {
            liftLeft.setPower(-1);
            liftRight.setPower(-1);
        }
        liftLeft.setPower(0);
        liftRight.setPower(0);
    }

}