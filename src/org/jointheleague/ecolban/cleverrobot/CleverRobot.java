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
		// driveDirect(100, 100);
		// cam = new Camera(100, 100);
		// for(int i : cam.pixels){
		// System.out.println(i);
		// }
	}

	private boolean loop() throws Exception {
		int[] bumps = getLightBumps();
		readSensors(100);
		driveDirect(300, 300);
		for (int i = 0; i < bumps.length; i++) {
			System.out.print(bumps[i] + " ");
		}
		System.out.print(bumps + "\n");
		// do something if both the sensors are bumped
		if (isBumpLeft() && isBumpRight()) {

			// Back up
			driveDirect(-100, -100);
			Thread.sleep(1000);

			// Turn Left
			driveDirect(200, 0);
			Thread.sleep(1000);

		} else {
			if (isBumpLeft()) {
				// Make roomba back up
				driveDirect(-100, -100);
				Thread.sleep(1000);

				// rotate right
				driveDirect(0, 100);
				Thread.sleep(1000);

				// Drive forward
				driveDirect(400, 400);
				Thread.sleep(1000);
			}
			if (isBumpRight()) {
				// Make roomba back up
				driveDirect(-100, -100);
				Thread.sleep(1000);

				// rotate left
				driveDirect(0, 100);
				Thread.sleep(1000);

				// Drive Forward
				driveDirect(100, 100);
				Thread.sleep(1000);
			}

			if (bumps[5] > 1000) {
				driveDirect(-100, -100);
				Thread.sleep(1000);
				driveDirect(0, 100);
			}
			if (bumps[0] > 1000) {
				driveDirect(-100, -100);
				Thread.sleep(1000);
				driveDirect(100, 0);

			}

		}

		return true;
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
