package be.ulb.dsa.externalmultiwaymerge;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import be.ulb.dsa.multiwaymerge.MultiwayMerge;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

public class ExternalMultiwayMerge {

	private static final EtmMonitor MONITOR = EtmManager.getEtmMonitor();
	public Queue<String> streamReferences = new LinkedList<String>();
	String outputFolder = "C:/Users/Juan/Documents/IT4BI/ULB/Database Systems Architecture/Project/test/";
	private int streamNo = 1;

	public void readAndSplit(String filePath, int M) {

		EtmPoint point = MONITOR.createPoint("ExternalMergeSort:readAndSplit");

		Input input = new Input(filePath);
		List<Integer> integers = new ArrayList<Integer>();

		try {
			input.open();
		} catch (FileNotFoundException e) {
			System.out.println("File cannot be found.");
		}

		try {
			while (!input.end_of_stream()) {
				// read the value
				int value = input.read_next();
				integers.add(value);
				// check whether main memory capacity has been reached
				if (integers.size() == M) {
					EtmPoint pointW = MONITOR.createPoint("xternalMergeSort:writeToDisk_initial_stream");
					writeToDisk(integers, "initial_stream");
					pointW.collect();
					integers.clear();
				}
			}

			if (!integers.isEmpty()) {
				EtmPoint pointW = MONITOR.createPoint("xternalMergeSort:writeToDisk_initial_stream");
				writeToDisk(integers, "initial_stream");
				pointW.collect();
				integers.clear();
			}
			
			streamNo = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		point.collect();
	}

	public void writeToDisk(List<Integer> integers, String type) {
		// set the file name
		String filePath = outputFolder + type + streamNo + ".txt";

		// sort the stream
		Collections.sort(integers);

		// write the data to a file
		Output output = new Output(filePath);
		try {
			for (int i = 0; i < integers.size(); i++) {
				output.write(integers.get(i));
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Store the reference to the stream in a queue
		streamReferences.add(filePath);
		streamNo++;
	}

	public void mergeStreams(int d) {

		// Check whether d is a valid number
		if (d > streamReferences.size()) {
			d = streamReferences.size();
		}
		List<List<Integer>> inputStreams = new ArrayList<List<Integer>>();

		// Load in memory the d first streams
		for (int i = 0; i < d; i++) {
			List<Integer> integers = loadStream(streamReferences.poll());
			inputStreams.add(integers);
		}

		// Merge the d streams
		MultiwayMerge multiwayMerge = new MultiwayMerge(25);
		List<Integer> mergedStream = multiwayMerge.sort(inputStreams);

		// UNCOMMENT to print the mergedstream
		/*
		 * System.out.println("OUTPUT"); for (int i = 0; i <
		 * mergedStream.size(); i++) { System.out.println(mergedStream.get(i));
		 * }
		 */

		// Write the result stream to disk
		EtmPoint pointW = MONITOR.createPoint("xternalMergeSort:writeToDisk_merged_stream");
		writeToDisk(mergedStream, "merged_stream");
		pointW.collect();

		// if necessary, merge the next d streams
		// stop condition
		if (streamReferences.size() != 1) {
			mergeStreams(d);
		} else {
			// UNCOMMENT to write the plain numbers to a file. This will affect execution time
			//writeNumbers(mergedStream);
			return;
		}
	}

	public List<Integer> loadStream(String filePath) {
		Input input = new Input(filePath);
		List<Integer> integers = new ArrayList<Integer>();

		try {
			input.open();

			while (!input.end_of_stream()) {
				// read the value
				int value = input.read_next();
				integers.add(value);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File cannot be found.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return integers;
	}

	public void sort(String filePath, int M, int d) {
		
		EtmPoint point = MONITOR.createPoint("ExxternalMergeSort:sort");
		
		readAndSplit(filePath, M);
		mergeStreams(d);
		
		 point.collect();
	}

	// Complementary method to write the numbers in plain text
	public void writeNumbers(List<Integer> integers) {
		String filePath = outputFolder + "SortedNumbers.txt";
		try {
			Writer writer = new FileWriter(filePath);
			for (int i = 0; i < integers.size(); i++) {
				writer.write(integers.get(i) + " ");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
