package org.firstinspires.ftc.teamcode.Autonomous.Methods;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Ranade-HP on 11/2/2017.
 */

public class Camera {

    ElapsedTime time = new ElapsedTime();

    public static final String TAG = "Vuforia VuMark Sample";

    //OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;

    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;


    Telemetry telemetry;

    public Camera(Telemetry classTelemetry){
        telemetry = classTelemetry;

    }

    public void init(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AQfYPMv/////AAAAGQ0uI5dgnEKgtRaeHWYQwZx/vkfCCJMQyewhgHFUCUFdZwQhmcqDQQAYmc+Ah0kbdMxVLBf6urFcu9Ip8yH3+0Bcke1NrcAlGzDzjaVut0sVVMJziqVkds8eO1THIzmK5WEKKfkLr0ckzarHk+J0gHruovG4OY7m3waXnswjVe9nY7t6/Qw7jmOCrRdVgwBdapXxpc4truDXevxwyprMhGNf3O4+mk/p4mJBzR+OU8icJ8pJUsbNQD9mGxX61XC3pdTSiQrYH+Cx7K/f21FfrOk9u6SWFfpuyuZNfCkU39sJk1YHW33ioVE8OHmkK4qJYqkyxKE6nBEdxsiy2bMzxIVhc8RXi9SNZ2YUHX5PkOLF";


        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);


        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();

    }

    public String getKey() {
        String key;
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
        time.reset();
        while (vuMark == RelicRecoveryVuMark.UNKNOWN && time.seconds() <= 4) {
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
        }
        //Gets the pictograph Value
        if (vuMark == RelicRecoveryVuMark.LEFT) {
            key = "LEFT";
        }
        else if (vuMark == RelicRecoveryVuMark.RIGHT) {
            key = "RIGHT";
        }
        else if (vuMark == RelicRecoveryVuMark.CENTER) {
            key = "CENTER";
        }
        else {
            key = "UNKNOWN";
        }

        telemetry.addData("Key: ", key);
        telemetry.update();

        return key;
    }

    public void cameraOff() {
        relicTrackables.deactivate();
    }

    public void cameraOn() {
        relicTrackables.activate();
    }
}