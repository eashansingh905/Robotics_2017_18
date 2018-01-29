package org.firstinspires.ftc.teamcode.Autonomous.Methods;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

/**
 * Created by Ranade-HP on 11/2/2017.
 */

public class JewelMechanism
{

    Servo SJX;
    Servo SJY;
    ColorSensor sensorColor;
    //DistanceSensor sensorDistance;
    ElapsedTime time = new ElapsedTime(0);


    public void init(HardwareMap hardwareMap)
    {

        SJX = hardwareMap.servo.get("SJX");
        SJY = hardwareMap.servo.get("SJY");
        sensorColor = hardwareMap.get(ColorSensor.class, "CS");
        //sensorDistance = hardwareMap.get(DistanceSensor.class,"DS");

    }

    public void lowerArm () {
        SJY.setPosition(0);
    }//0.05

    public void rotateArm(double position) {
        SJX.setPosition(position);
    }

    public void ledOn() {
        sensorColor.enableLed(true);
    }

    public boolean colorRed () {
        boolean red = true;
        time.reset();
        while (time.seconds() < 1) {
            //true = red
            red = sensorColor.red() > sensorColor.blue();
            //false = blue
        }
        return red;
    }

    public void ledOff() {
        sensorColor.enableLed(false);
    }

    public void raiseArm() {
        SJY.setPosition(0.8);
    }
}