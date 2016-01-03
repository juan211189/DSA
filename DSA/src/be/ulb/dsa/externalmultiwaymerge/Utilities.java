package be.ulb.dsa.externalmultiwaymerge;

import java.io.IOException;
import java.util.Random;

public class Utilities {

	private static final int AMOUNT = 1000000;
	private static final String FILENAME = "sample1.txt";
	private static final String OUTPUTFOLDER = "C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/data/";
	private static final int BUFFERSIZE = 4; 
	
	public static void GenerateInputData() {
		Output output = new Output(OUTPUTFOLDER + FILENAME, BUFFERSIZE);
		Random generator = new Random();

		try {
			for (int i = 0; i < AMOUNT; i++) {
				int element = generator.nextInt(AMOUNT);
				output.write(element);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
