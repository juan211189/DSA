package be.ulb.dsa.externalmultiwaymerge;

import java.io.IOException;

import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.renderer.SimpleTextRenderer;

public class Main {

	private static EtmMonitor monitor;

	public static void main(String[] args) throws IOException {
		//Utilities.GenerateInputData();
		// configure measurement framework
		setup();

		ExternalMultiwayMerge merge = new ExternalMultiwayMerge();
		merge.sort("C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/test/sample.txt", 10000, 50);
		
		// visualize results
	   monitor.render(new SimpleTextRenderer());
		
		// shutdown measurement framework
		tearDown();
	}

	private static void setup() {
		BasicEtmConfigurator.configure();
		monitor = EtmManager.getEtmMonitor();
		monitor.start();
	}

	private static void tearDown() {
		monitor.stop();
	}
}