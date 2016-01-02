package be.ulb.dsa.externalmultiwaymerge;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Output output = new Output("C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/test/integerfile.txt");
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
		output.close();
		
		ExternalMultiwayMerge merge = new ExternalMultiwayMerge();
		merge.sort("C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/test/integerfile.txt", 4, 2);
	}
	
}
