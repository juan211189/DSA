package be.ulb.dsa.externalmultiwaymerge;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		
		//Utilities.GenerateInputData();
		
		ExternalMultiwayMerge merge = new ExternalMultiwayMerge();
		merge.sort("C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/test/sample.txt", 100000, 1000);
	}
	
}
