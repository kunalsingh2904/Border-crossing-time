// CONTAINS THE CLOCK CLASS

package assignment0;

import java.time.LocalTime;
import java.time.Duration;

public class Clock {
	private   LocalTime initial_time;
	private  LocalTime current_time ;
	static int round =0;
	
	Clock(){
		initial_time = LocalTime.now(); // before starting a particular round
		round++;
	}
	
	public LocalTime getinitial_time() {
		return initial_time;
	}
	
	public LocalTime getcurrent_time() {
		return current_time;
	}
	
	public void setcurrent_time() { // setting the time before start
		this.current_time = LocalTime.now();
	}
	
	public int getduration() { //function to know the time elapsed
		Duration duration = Duration.between(initial_time, current_time);
		return (int)duration.getSeconds();
	}
}
