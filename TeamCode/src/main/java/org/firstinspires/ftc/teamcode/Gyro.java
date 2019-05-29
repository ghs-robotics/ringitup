
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class Gyro {

    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;
    double angle;

    public Gyro(HardwareMap hardwareMap){
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;
        angle = 0;
    }

    public void calibrate(){
        modernRoboticsI2cGyro.calibrate();
    }

    public void reset(){
        modernRoboticsI2cGyro.resetZAxisIntegrator();
    }


    public double measure(){

      int rawX = modernRoboticsI2cGyro.rawX();
      int rawY = modernRoboticsI2cGyro.rawY();
      int rawZ = modernRoboticsI2cGyro.rawZ();
      int heading = modernRoboticsI2cGyro.getHeading();
      int integratedZ = modernRoboticsI2cGyro.getIntegratedZValue();

      AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
      float zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

      int zAxisOffset = modernRoboticsI2cGyro.getZAxisOffset();
      int zAxisScalingCoefficient = modernRoboticsI2cGyro.getZAxisScalingCoefficient();

      double angle_offset = angle - rawX;
      angle = rawX;
      return rawX;
    }
}
