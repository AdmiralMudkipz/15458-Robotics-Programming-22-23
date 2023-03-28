package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.ArrayList;
import org.openftc.apriltag.AprilTagDetection;

@Autonomous(name = "*****RIGHT******")
public class RightAprilTag extends LinearOpMode {
    private ElapsedTime eTime = new ElapsedTime();   
    private DcMotor flMotor = null;
    private DcMotor frMotor = null;
    private DcMotor brMotor = null;
    private DcMotor blMotor = null;
    private DcMotor arm = null;
    private Servo leftServo = null;
    private Servo rightServo = null;
    public static boolean umkehren = false;

        //making methods so I don't have to retype raw inputs a bunch of times
    
    public void doAuto(int a){
      int timeLimit = 15;
      eTime.reset();
      boolean exit = true;
      while (opModeIsActive() && exit) {
        if (a == 1) {
          telemetry.addData("1", "1 is successfully returned");
          sleep(2);
          //direction, power, time, 0 = forward, 1 = back, 2 = left, 3 = right
          fahrrichtung(3,0.5,1);//right, pushes into corner .5p, 1s
          fahrrichtung(2, 0.5, 2.2);//left .5p, 2.2s
          fahrrichtung(0, 0.5, 1.8);//forward .5p, 1.8s
          fahrrichtung(1,0.5,0.05);//brake .5p, .05s
          endlich(true);
          exit = false;
        } else if (a == 2) {
          telemetry.addData("2", "2 is successfully returned");
          //direction, power, time, 0 = forward, 1 = back, 2 = left, 3 = right
          fahrrichtung(3,0.5,1);//right, pushes into corner .5p, 1s
          fahrrichtung(2, 0.5, 2.2);//left .5p, 2.2s
          fahrrichtung(0, 0.5, 1.8);//forward .5p, 1.8s
          fahrrichtung(1,0.5,0.05);//brake .5p, .05s
          fahrrichtung(3, 0.5, 1.2);//right og 1.2 seconds
           endlich(true);
           exit = false;
        } else{//12.97V
          telemetry.addData("3", "3 is successfully returned");
          //direction, power, time, 0 = forward, 1 = back, 2 = left, 3 = right
          fahrrichtung(3,0.5,1);//right, pushes into corner .5p, 1s
          fahrrichtung(2, 0.5, 2.2);//left .5p, 2.2s
          fahrrichtung(0, 0.5, 1.85);//forward .5p, 1.8s
          fahrrichtung(1,0.5,0.05);//brake .5p, .05s
          fahrrichtung(3, 0.5, 2.5);//right og 2 seconds
           endlich(true);
           exit = false;}
        if(!(eTime.seconds()<timeLimit)){
             a = 1;}}
      
    }
    public void fahrrichtung(int i, double power, double zeit){
        if(umkehren && i == 2){
          i = 3;
        }else if(umkehren && i == 3){
          i = 2;
        }
        if(i == 0){
          eTime.reset();
          while(opModeIsActive() && eTime.seconds() < zeit){//forward
            flMotor.setPower(-power);
            frMotor.setPower(-power);
            blMotor.setPower(-power);
            brMotor.setPower(-power);}
        }else if(i == 1){
          eTime.reset();
          while(opModeIsActive() && eTime.seconds() < zeit){//back
            flMotor.setPower(power);
            frMotor.setPower(power);
            blMotor.setPower(power);
            brMotor.setPower(power);}
        }else if(i == 2){
          eTime.reset();
          while(opModeIsActive() && eTime.seconds() < zeit){//left
            flMotor.setPower(power);
            frMotor.setPower(power);
            blMotor.setPower(-power);
            brMotor.setPower(-power);}
        }else if(i == 3){
          eTime.reset();
          while(opModeIsActive() && eTime.seconds() < zeit){//right
            flMotor.setPower(-power);
            frMotor.setPower(-power);
            blMotor.setPower(power);
            brMotor.setPower(power);}
        }else{
          while(opModeIsActive() && eTime.seconds() < zeit){
            flMotor.setPower(0);
            frMotor.setPower(0);
            blMotor.setPower(0);
            brMotor.setPower(0);
          }}
        }
      public void aufhalten(double zeit){
        while(opModeIsActive() && eTime.seconds() < zeit){
            flMotor.setPower(0);
            frMotor.setPower(0);
            blMotor.setPower(0);
            brMotor.setPower(0);}}
      public void offen(){          
        leftServo.setPosition(0.07);//open
        rightServo.setPosition(0.65);}
      public void schliessen(){//close
        leftServo.setPosition(.2);
        rightServo.setPosition(.45);}
      public void armKontrolle(boolean up, double power, double zeit){
        eTime.reset();
        while(opModeIsActive() && eTime.seconds() < zeit){
          if(up){
            arm.setPower(power);
          }else{
            arm.setPower(-power);
          }}
      arm.setPower(0.01);}
      public void endlich(boolean all){
        if(all){
          arm.setPower(0);}
        flMotor.setPower(0);
        frMotor.setPower(0);
        blMotor.setPower(0);
        brMotor.setPower(0);}
        // public void doAuto(){
        //     boolean hold = true;
        //     while(hold){
        //       schliessen();
        //       aufhalten(1.5);
        //       eTime.reset();
        //       while(eTime.seconds()< 1){ 
        //       arm.setPower(1);}
        //       //direction, power, time, 0 = forward, 1 = back, 2 = left, 3 = right
        //       fahrrichtung(3, 1.0, 1.0);// left
        //       fahrrichtung(0, 1.0, 0.8);//forward
        //       endlich(true);
        //       done = true;
              
        //       hold = false;
        //     }
        //     offen();*/
        //   }
        // }
  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */

  @Override
  public void runOpMode() {
    flMotor = hardwareMap.get(DcMotor.class, "flMotor ");
    frMotor = hardwareMap.get(DcMotor.class, "frMotor");
    blMotor = hardwareMap.get(DcMotor.class, "blMotor");
    brMotor = hardwareMap.get(DcMotor.class, "brMotor");
    arm = hardwareMap.get(DcMotor.class, "arm");
    leftServo = hardwareMap.get(Servo.class, "leftServo");
    rightServo = hardwareMap.get(Servo.class,"rightServo");
    flMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    frMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    blMotor.setDirection(DcMotor.Direction.FORWARD);
    brMotor.setDirection(DcMotor.Direction.REVERSE);
    telemetry.addData("Status", "Waiting_For_Start" );
    telemetry.update();

    waitForStart();
    eTime.reset();

    int numberOfDetections = 0;
    double detectedID;
    AprilTagIdCode.BlocksContext myDetector;
    ArrayList allDetections;
    int timeLimit;
    ElapsedTime loopTimer;
    AprilTagDetection singleDetection;

    detectedID = -1;
    // Create a pipeline/webcam object for AprilTag ID code detection. Edit configured webcam name as needed.  Use this myBlock in INIT section of OpMode, before startAprilTagDetector.
    myDetector = AprilTagIdCode.createAprilTagDetector(hardwareMap, "cam");
    // Begin operating camera/stream/pipeline for AprilTag
    // detection. Must specify a resolution supported by the camera;
    // edit these default values as desired. Use this myBlock
    // in INIT section of OpMode, after createAprilTagDetector.
    AprilTagIdCode.startAprilTagDetector(myDetector, 640, 480);
    waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive() && (numberOfDetections == 0 || numberOfDetections > 2)) {
        // Provide the data from any and all detetected AprilTags. Use this myBlock anywhere in the OpMode, after startAprilTagDetector.
        allDetections = AprilTagIdCode.getAllDetections(myDetector);
        // Provide the number of detections in the current batch. Use this myBlock anywhere in the OpMode, after getAllDetections.
        numberOfDetections = AprilTagIdCode.getHowManyDetections(allDetections);
        if (numberOfDetections == 0) {
          telemetry.addData("No detections", "Keep Looking");
        } else if (numberOfDetections >= 2) {
          telemetry.addData("Multiple detections", "Wait for only one");
        } else {
          // Provide the data from only the first detection in the current batch.  This myBlock is helpful when you know there's only one AprilTag detected.  Use it anywhere, after getAllDetections. This will crash if the input detections list is empty.
          singleDetection = AprilTagIdCode.getOneDetection(allDetections, 0);
          // Provide the AprilTag ID code from the designated detection. Use this myBlock anywhere, after getOneDetection.
          detectedID = AprilTagIdCode.getID(singleDetection);
          telemetry.addData("Detected April Tag ID code", singleDetection);
          telemetry.addData("other", detectedID);
          int b = (int)detectedID;
          doAuto(b);
        }
        telemetry.update();
      }
      
      
      // Close/disable the designated AprilTag pipeline, to free up CPU
      // resources. Use this after saving all needed AprilTag info.
      AprilTagIdCode.closeAprilTagDetector(myDetector);
    }
  }
}
