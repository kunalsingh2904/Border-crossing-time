// CONTAINS THE MAIN CLASS

package assignment0;

import java.util.Random;
import java.time.LocalTime;
import java.time.Duration;
import java.io.FileWriter;
import java.io.IOException;

public class Mainclass {
	
	// Generate a random number between 1 and 1000.
	// Use it as the starting position for the Infiltrator.
	static int randomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
	
	
	// function to switch each sensor on or off after every 10 seconds based on probability
	static void sensor_on_of(Border matrix[][], float prob, int width){
		for(int i=1; i<=width; i++) {
			for(int j=0; j<1000; j++) {
				Border temp = matrix[i][j];
				int x = 10-(int)(prob*10);
				int y= randomNumberInRange(1,10);
				if(y>x) {
					temp.on_off.setSwitched(true);
				}
				else temp.on_off.setSwitched(false);
			}
		}
	}
	
	
	
	
	// Main program
	public static void main(String[] args) {
		
		
		double [] store = new double[918];  // array to store each combination of w & p with its 15 Time intervals.
		int count =0;
		int []widder = {1,2,3,4,8,16};  // width (w) of the Border
		double []prob = {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9};  // probability (p) of a sensor being on
		
		for(int xxx : widder) {      // each combination (w,p) is run with
			for(double ppp: prob) {  // the help of these two 'for loops'
				store[count] = xxx;
				count++;
				store[count] = ppp;
				count++;
				System.out.println("started for width " + xxx +" and p " + ppp);
				for(int z=1;z<=15;z++) {  // 'for loop' to run each combination for 15 times to obtain {T1,T2.....T15}
					int width = xxx;
					float probability = (float)ppp;
					Border [][] matrix = new Border[width+2][1000];  // defining area
					
					for(int i=0; i<1000; i++) {    // defining attacking country area
						
						Border temp = new Border("AC");
						temp.seton_off();
						matrix[0][i]= temp;
					}
					
					for(int i=1; i<=width; i++) {  // defining war-zone area
						for(int j=0;j<1000;j++) {
							Border temp = new Border("WZ");
							temp.seton_off();
							matrix[i][j]= temp;
						}	
					}
					
					for(int i=0; i<1000;i++) {        // defining defending country area
						Border temp = new Border("DC");
						temp.seton_off();
						matrix[width+1][i]=temp;
					}
					
					Infiltrator man = new Infiltrator();     // defining Infiltrator
					man.width = randomNumberInRange(200,800);
					
					LocalTime startTime = LocalTime.now();
					
					while(man.success != true  && man.caught != true) {
						
						if(man.type.getlocation()=="DC") { // End of a cycle
							man.success = true;
							break;
						}
						
						Clock clk = new Clock();
						sensor_on_of(matrix, probability, width);
						int x = man.len;
						int y = man.width;
						Border temp = matrix[x][y];
						
						if(temp.on_off.getSwitched() == false) {  // Making sure that the sensor at the
							Border temp2 = matrix[x+1][y+1];     // location of the infiltrator is off
							Border temp1 = matrix[x+1][y];
							Border temp3 = matrix[x+1][y-1];
							if(temp1.on_off.getSwitched() == false) { // Making sure that the sensor ahead 
								man.len = x+1;                       // of the infiltrator is off
								man.width = y;
								man.type.setlocation(temp1.getlocation());
							}
							else if(temp2.on_off.getSwitched() == false) { // Making sure that the sensor diagonally
								man.len = x+1;                            // to the right of the infiltrator is off
								man.width = y+1;
								man.type.setlocation(temp2.getlocation());
							}
							else if(temp3.on_off.getSwitched() == false) { // Making sure that the sensor diagonally
								man.width = y-1;                          // to the left of the infiltrator is off
								man.len = x+1;
								man.type.setlocation(temp3.getlocation());
							}	
						}
						
						
						int dur =0;
						while(dur<10) {         
							clk.setcurrent_time();   
							dur = clk.getduration();
						}
					
					}
					
					LocalTime endTime = LocalTime.now();
					
					int dur = (int)Duration.between(startTime, endTime).getSeconds();
					store[count] = dur;    // This is the time taken in one particular cycle
					count++;
					
				}
				System.out.println("Done for width " + xxx +" and p " + ppp); // After 15 cycles for each combination of w & p.
			}
		}
		
		
		// Writing into a file in matrix-form
		int var =1;
		try {
			FileWriter myWriter = new FileWriter("output.txt");
			for(double temp:store) {
				String temp2 = String.valueOf(temp);
				
				myWriter.write(temp2);
				myWriter.write("\t");
				if(var % 17 ==0) {
					myWriter.write('\n');
				}
				var++;
				
			}
			myWriter.close();
		}
		catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	        }

		
	}

	
}
