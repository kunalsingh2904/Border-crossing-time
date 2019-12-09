// CONTAINS THE INFILTRATOR CLASS

package assignment0;

public class Infiltrator {
	int len; // to determine the horizontal position of infiltrator
	int width; // to determine the vertical position of infiltrator
	Border type;
	boolean success; // to determine if the infiltrator has reached the defending country
	boolean caught;
	
	Infiltrator(){ // Initialising an infiltrator
		success = false;
		caught = false;
		len = 0;
		type = new Border("AC");
	}
}
