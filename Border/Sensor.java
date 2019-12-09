// CONTAINS THE SENSOR CLASS

package assignment0;

import java.util.Random;

public class Sensor {
	private boolean switched;
	
	Sensor(){ //Initialising a sensor being off
		switched = false;
	}

	public int randomNumberInRange(int min, int max) { // function to generate random numbers as required
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
	
	public boolean getSwitched() { // function to know if the sensor is on
		return switched;
	}
	
	public void setSwitched(boolean temp) {   //switching the sensor on or off based on result from below
		this.switched = temp;
	}
		
	public void setSwitch(float temp) {       // using probability to switch the sensor on or off
		int x = 10-(int)temp*10;
		int y= randomNumberInRange(1,10);
		setSwitched(y>x);
	}

}
