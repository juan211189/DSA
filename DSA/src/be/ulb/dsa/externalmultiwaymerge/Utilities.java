package be.ulb.dsa.externalmultiwaymerge;

import java.io.IOException;
import java.util.Random;

public class Utilities {

	private static final int AMOUNT = 1000000;
	private static String FILENAME = "sample1.txt";
	private static String OUTPUTFOLDER = "C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/data/";

	public static void GenerateInputData() {
		Output output = new Output(OUTPUTFOLDER + FILENAME);
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
