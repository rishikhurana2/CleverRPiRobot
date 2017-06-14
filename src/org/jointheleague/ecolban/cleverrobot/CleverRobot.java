package org.jointheleague.ecolban.cleverrobot;

/*********************************************************************************************
 * Vic's ultrasonic sensor running with Erik's Clever Robot for Pi
 * version 0.9, 170227
 **********************************************************************************************/
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class CleverRobot extends IRobotAdapter {
	Sonar sonar = new Sonar();
	private boolean tailLight;
	Camera cam;
	public CleverRobot(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		CleverRobot rob = new CleverRobot(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();

	}

	private void setup() throws Exception {
		//driveDirect(100, 100);
		cam = new Camera(100,100);
		//for(int i : cam.pixels){
		//	System.out.println(i);
		//}
	}

	private boolean loop() throws Exception {
		//System.out.println("LEFT SONAR: " + sonar.readSonar("left"));
		Thread.sleep(1000);
		//setTailLight(tailLight = !tailLight);
		//System.out.println("RIGHT SONAR: " + sonar.readSonar("right"));
		//System.out.println("CENTER SONAR: " + sonar.readSonar("center"));
		cam.takeRGBPicture();
		System.out.println(cam.getRedPercentage(15));
		System.out.println(cam.getBluePercentage(15));
		System.out.println(cam.getGreenPercentage(15));
		return true;
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
