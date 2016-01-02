package be.ulb.dsa.externalmultiwaymerge;

import java.io.IOException;
import java.util.Random;

public class Utilities {

	private static int amount = 10000000;
	private static String outputFolder = "C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/data/";

	public static void GenerateInputData() {
		Output output = new Output(outputFolder + "sample.txt");
		Random generator = new Random();

		try {
			for (int i = 0; i < amount; i++) {
				int element = generator.nextInt(amount);
				output.write(element);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*Output output = new Output("C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/test/integerfile.txt");
		output.write(15);
		output.write(13);
		output.write(2);
		output.write(7);
		output.write(1);
		output.write(11);
		output.write(8);
		output.write(5);
		output.write(9);
		output.write(10);
		output.close();*/
	}
}
