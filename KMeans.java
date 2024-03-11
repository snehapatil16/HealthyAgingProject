package testing_commit;

import java.sql.*;
import java.util.*;
import java.io.*;

public class KMeans {

	// number of clusters
	private static final int NUM_CLUSTERS = 3;
	// number of iterations
	private static final int NUM_ITERATIONS = 10;

	public static void main(String[] args) {


		// read data from CSV file and store it in a HashMap
	       String csvFilePath = "/Users/annika/Desktop/data.csv";
	        HashMap<Integer, double[]> data = new HashMap<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
	        	String[] fields = reader.readLine().split(",");
	        	 int personID = 0;
	        	 String line;
	            while ((line = reader.readLine()) != null) {
	            	String[] nums = line.split(",");
	                double[] tScores = new double[9];
	                for(int i = 0; i < tScores.length; i++) {
	                	tScores[i] = Double.parseDouble(nums[i]);
	                }
	                
	                data.put(personID, tScores);
	                personID++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return;
	        } 

		//String dbName = "healthy_aging.db";
		//String url = "jdbc:sqlite:" + dbName;
		//HashMap<Integer, double[]> data = new HashMap<>();
//		try {
//			Connection connection = DriverManager.getConnection(url);
//			Statement statement = connection.createStatement();
//			String query = "SELECT Physical_Function, Pain_Interference, Fatigue, Sleep, "
//					+ "Cognitive, Depression, Anxiety, Social, Self_Efficacy, " + "Manage_symptoms FROM healthy_aging";
//			ResultSet rs = statement.executeQuery(query);
//
//			int personID = 0;
//			while (rs.next()) {
//				int key = personID;
//				double[] tscores = { rs.getDouble("Physical_Function"), rs.getDouble("Pain_Interference"),
//						rs.getDouble("Fatigue"), rs.getDouble("Sleep"), rs.getDouble("Cognitive"),
//						rs.getDouble("Depression"), rs.getDouble("Anxiety"), rs.getDouble("Social"),
//						rs.getDouble("Self_Efficacy") };
//				data.put(key, tscores);
//				personID++;
//			}
//			rs.close();
//			statement.close();
			
			
			
			//initialize KMeansClusterer with data and settings
			double[][] initialCentroids = new double[NUM_CLUSTERS][9];
			for(int i = 0; i < NUM_CLUSTERS; i++) {
				initialCentroids[i] = data.get(i);
			}
			
			System.out.println(initialCentroids[1][1]);
			KMeansClusterer clusterer = new KMeansClusterer(NUM_CLUSTERS, NUM_ITERATIONS, initialCentroids, data);
			clusterer.cluster();
			Map<Integer, List<Integer>> clusters = new HashMap<>();
			for(int i = 0; i < NUM_CLUSTERS; i++) {
				clusters.put(i, new ArrayList<>());
			}
			for(Map.Entry<Integer, double[]> entry : data.entrySet()) {
				int clusterID = (int)entry.getValue()[8];
				clusters.get(clusterID).add(entry.getKey());
			}
			for(int i = 0; i < NUM_CLUSTERS; i++) {
				System.out.println("Cluster " + (i+1) + "; " + clusters.get(i));
			}
			
//			connection.close();
//		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		// perform K-Means clustering
//		int numDataPoints = 0;
//		int col = 0;
//		if(data.values().iterator().next() !=null) {
//			col = data.values().iterator().next().length;
//		}
//		double[][] dataPoints = new double[NUM_CLUSTERS][col];
//		for (Map.Entry<Integer, double[]> entry : data.entrySet()) {
//			double[] tScores = entry.getValue();
//			for (int i = 0; i < tScores.length; i++) {
//				dataPoints[numDataPoints % NUM_CLUSTERS][i] += tScores[i];
//			}
//			if (numDataPoints % NUM_CLUSTERS == NUM_CLUSTERS - 1) {
//				for (int i = 0; i < NUM_CLUSTERS; i++) {
//					for (int j = 0; j < dataPoints[i].length; j++) {
//						dataPoints[i][j] /= NUM_CLUSTERS;
//					}
//				}
//				KMeansClusterer clusterer = new KMeansClusterer(NUM_CLUSTERS, NUM_ITERATIONS, dataPoints, data);
//				clusterer.cluster();
//				dataPoints = new double[NUM_CLUSTERS][data.values().iterator().next().length];
//			}
//			numDataPoints++;
//		}
//		if (numDataPoints % NUM_CLUSTERS != 0) {
//			for (int i = 0; i < NUM_CLUSTERS; i++) {
//				for (int j = 0; j < dataPoints[i].length; j++) {
//					dataPoints[i][j] /= (numDataPoints % NUM_CLUSTERS);
//				}
//			}
//			KMeansClusterer clusterer = new KMeansClusterer(NUM_CLUSTERS, NUM_ITERATIONS, dataPoints, data);
//			clusterer.cluster();
//		}
//
//		// print out the clusters
//		for (int i = 0; i < NUM_CLUSTERS; i++) {
//			System.out.println("Cluster " + (i + 1) + ":");
//			for (Map.Entry<Integer, double[]> entry : data.entrySet()) {
//				double[] tScores = entry.getValue();
//				if (tScores[tScores.length - 1] == i) {
//					System.out.println(entry.getKey() + ": " + Arrays.toString(tScores));
//				}
//			}
//		}

	}

}