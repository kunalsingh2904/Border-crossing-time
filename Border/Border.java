// CONTAINS THE BORDER CLASS

package assignment0;

public class Border {
	private String location;
	Sensor on_off ;
	
	//constructor for the class 'Border'
	Border(String temp){
		location = temp;
	}
	
	public void seton_off() { // Initialising a cell and sensor
		on_off = new Sensor();
	}
	
	public void setlocation(String temp) { // getting its co-ordinates
		this.location = temp;
	}
	
	public String getlocation() { // giving the position coordinates
		return location;
	}

	
}
