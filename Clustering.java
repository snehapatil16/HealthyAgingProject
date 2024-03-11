package testing_commit;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Clustering {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

<<<<<<< HEAD
=======
		//creating a HashMap for each cluster
		HashMap<Person, TScore> low = new HashMap<>();
		HashMap<Person, TScore> middle = new HashMap<>();
		HashMap<Person, TScore> high = new HashMap<>();

>>>>>>> 26e4ef4 (Output to text file and comments)
		String dbName = "healthy_aging.db";
		String url = "jdbc:sqlite:" + dbName;
		HashMap<Person, Double> data = new HashMap<>();
		try {
			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			String query = "SELECT Lifelong_Learning_Activities, Practitioner_Based_Integrative_Medicine, "
					+ "Body_Therapy, Meditation, Physically_Active, Physical_Activity, Do_You_Eat_A_Healthy_Diet, "
					+ "Diet, Average FROM healthy_aging";

			ResultSet rs = statement.executeQuery(query);
			rs.next();
			int personID = 0;

			while (rs.next()) {
				Person p = new Person();
				p.ID = personID;

				// lifelong learning activities
				if ((rs.getString("Lifelong_Learning_Activities")) != null) {
					String box = rs.getString("Lifelong_Learning_Activities");
					String[] a = box.split(",");
					for (int i = 0; i < a.length; i++) {
						if (!a[i].contains("Other") && !a[i].contains("other") && !a[i].contains("None") && !a[i].equals("")) {
							p.activities.add(a[i]);
//						} else if (a[i].equals("Other") || !a[i].contains("other")) {
//							String other = "Other Lifelong Learning Activity";
//							p.activities.add(other);
						}
					}
				}

				// practicioner based integrative medicine
				if ((rs.getString("Practitioner_Based_Integrative_Medicine")) != null) {
					String box = rs.getString("Practitioner_Based_Integrative_Medicine");
					String[] a = box.split(",");
					for (int i = 0; i < a.length; i++) {
						if (!a[i].contains("Other") && !a[i].contains("other") && !a[i].contains("None") && !a[i].equals("")) {
							p.activities.add(a[i]);
//						} else if (a[i].contains("Other") || !a[i].contains("other")) {
//							String other = "Other Practitioner Based Integrative Medicine";
//							p.activities.add(other);
						}
					}
				}

				// body therapy
				if ((rs.getString("Body_Therapy")) != null) {
					String box = rs.getString("Body_Therapy");
					String[] a = box.split(",");
					for (int i = 0; i < a.length; i++) {
						if (!a[i].contains("Other") && !a[i].contains("other") && !a[i].contains("None") && !a[i].equals("")) {
							p.activities.add(a[i]);
//						} else if (a[i].contains("Other") || !a[i].contains("other")) {
//							String other = "Other Body Therapy";
//							p.activities.add(other);
						}
					}
				}

				// meditation
				if ((rs.getString("Meditation")) != null) {
					String box = rs.getString("Meditation");
					String[] a = box.split(",");
					for (int i = 0; i < a.length; i++) {
						if (!a[i].contains("Other") && !a[i].contains("other") && !a[i].contains("None") && !a[i].equals("")) {
							p.activities.add(a[i]);
//						} else if (a[i].contains("Other") || !a[i].contains("other")) {
//							String other = "Other Meditation";
//							p.activities.add(other);
						}
					}
				}

				// physical activity
				if ((rs.getString("Physically_Active") != null) && (rs.getString("Physical_Activity")) != null) {
					if(!rs.getString("Physically_Active").contains("I AM NOT")) {
						String box = rs.getString("Physical_Activity");
						String[] a = box.split(",");
						for (int i = 0; i < a.length; i++) {
							if (!a[i].contains("What type?") && !a[i].contains("None")
									&& !a[i].contains("Other type of exercise?") && !a[i].equals("")) {
								p.activities.add(a[i]);
//							} else if (a[i].contains("Other") || !a[i].contains("other")) {
//								String other = "Other Physical Activity";
//								p.activities.add(other);
							}
						}
					}
				}

				// diet
				if ((rs.getString("Do_You_Eat_A_Healthy_Diet") != null) && (rs.getString("Diet")) != null) {
					if((rs.getString("Do_You_Eat_A_Healthy_Diet").contains("YES, I DO"))) {
						String box = rs.getString("Diet");
						String[] a = box.split(",");
						for (int i = 0; i < a.length; i++) {
							if (!a[i].contains("Other") && !a[i].contains("other") && !a[i].contains("None") && !a[i].equals("")) {
								p.activities.add(a[i]);
//							} else if (a[i].contains("Other") || !a[i].contains("other")) {
//								String other = "Other Diet";
//								p.activities.add(other);
							}
						}
					}
				}
<<<<<<< HEAD

				//this is the code if there is more than one tscore
//				double[] tscores = { rs.getDouble("Physical_Function"), rs.getDouble("Pain"), rs.getDouble("Fatigue"),
//						rs.getDouble("Sleep"), rs.getDouble("Cognitive"), rs.getDouble("Depression"),
//						rs.getDouble("Anxiety"), rs.getDouble("Social"), rs.getDouble("Self_Efficacy"),
//						rs.getDouble("Manage_Symptoms") };
				double tscore = rs.getDouble("Average");
				data.put(p, tscore);
=======
				
				//get t-scores and store in data HashMap
				double[] tscores = { rs.getDouble("Physical_Function"), rs.getDouble("Pain"), rs.getDouble("Fatigue"),
						rs.getDouble("Sleep"), rs.getDouble("Cognitive"), rs.getDouble("Depression"),
						rs.getDouble("Anxiety"), rs.getDouble("Social"), rs.getDouble("Self_Efficacy"),
						rs.getDouble("Manage_Symptoms") };
				data.put(p, tscores);
>>>>>>> 26e4ef4 (Output to text file and comments)
				personID++;
			}
			rs.close();
			statement.close();
<<<<<<< HEAD

			// Create the clusters
=======
			
			//create the three clusters based on t-score values
			for (Map.Entry<Person, double[]> entry : data.entrySet()) {
				for (int i = 0; i < 10; i++) {
					if (entry.getValue()[i] != 0.0 && entry.getValue()[i] <= 35.0) {
						low.put(entry.getKey(), new TScore(entry.getValue()[i], i));
					} else if (entry.getValue()[i] != 0.0 && entry.getValue()[i] > 35.0
							&& entry.getValue()[i] <= 65.0) {
						middle.put(entry.getKey(), new TScore(entry.getValue()[i], i));
					} else if (entry.getValue()[i] != 0.0 && entry.getValue()[i] > 65.0) {
						high.put(entry.getKey(), new TScore(entry.getValue()[i], i));
					}
				}
			}

			//printing out the clusters which includes Person ID, T-Score, Domain
			System.out.println("Unhealthy Cluster:");
			for (Map.Entry<Person, TScore> entry : low.entrySet()) {
				System.out.println("Person ID: " + entry.getKey().ID + "\tT-Score: " + entry.getValue().tscore
						+ "\tDomain: " + entry.getValue().activityName);
			}

			System.out.println("\nHealthy Cluster:");
			for (Map.Entry<Person, TScore> entry : middle.entrySet()) {
				System.out.println("Person ID: " + entry.getKey().ID + "\tT-Score: " + entry.getValue().tscore
						+ "\tDomain: " + entry.getValue().activityName);
			}

			System.out.println("\nSuper Healthy Cluster:");
			for (Map.Entry<Person, TScore> entry : high.entrySet()) {
				System.out.println("Person ID: " + entry.getKey().ID + "\tT-Score: " + entry.getValue().tscore
						+ "\tDomain: " + entry.getValue().activityName);
			}
			
			//output results to a text file
			//saves file to directory
			//String outputPath = "output.txt";
			//saves file to Desktop
			String outputPath = System.getProperty("user.home") + "/Desktop/output.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

                writer.write("Unhealthy Cluster:\n");
                for (Map.Entry<Person, TScore> entry : low.entrySet()) {
                    Person person = entry.getKey();
                    TScore tScore = entry.getValue();
                    writer.write("Person ID: " + person.ID + "\tT-Score: " + tScore.tscore +
                            "\tDomain: " + tScore.activityName + "\n");
                }

                writer.write("\nNeutral Cluster:\n");
                for (Map.Entry<Person, TScore> entry : middle.entrySet()) {
                    Person person = entry.getKey();
                    TScore tScore = entry.getValue();
                    writer.write("Person ID: " + person.ID + "\tT-Score: " + tScore.tscore +
                            "\tDomain: " + tScore.activityName + "\n");
                }

                writer.write("\nHealthy Cluster:\n");
                for (Map.Entry<Person, TScore> entry : high.entrySet()) {
                    Person person = entry.getKey();
                    TScore tScore = entry.getValue();
                    writer.write("Person ID: " + person.ID + "\tT-Score: " + tScore.tscore +
                            "\tDomain: " + tScore.activityName + "\n");
                }

                System.out.println("Results written to text file successfully!");
                
            } catch (IOException e) {
                System.out.println("Error writing to text file.");
            	e.printStackTrace();
            }
>>>>>>> 26e4ef4 (Output to text file and comments)

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
<<<<<<< HEAD

		Clusterer clusters = new Clusterer(data);
		clusters.cluster();

		//clusters.printClusters();
		// calling the statistical analysis function
		LinkedHashMap<String, Integer> unhealthy = statisticalAnalysis(clusters.unhealthy);
		LinkedHashMap<String, Integer> healthy = statisticalAnalysis(clusters.healthy);
		LinkedHashMap<String, Integer> superHealthy = statisticalAnalysis(clusters.superhealthy);

		int count = Integer.parseInt(args[0]);
		writeToFile(unhealthy, healthy, superHealthy, count);
	}

	public static LinkedHashMap<String, Integer> statisticalAnalysis(HashMap<Person, TScore> cluster) {

		// output hashmap of activities with their frequency
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		// System.out.println("output made");
		// loops through the cluster
		for (Map.Entry<Person, TScore> entry : cluster.entrySet()) {
			// loop through each person's arraylist of activities
			for (int i = 0; i < entry.getKey().activities.size(); i++) {
				String activityTemp = entry.getKey().activities.get(i);
				// System.out.println("activity temp is " + activityTemp);

				// check if this activity already exists in the output hashmap
				if (output.containsKey(activityTemp)) {
					// System.out.println("in contains");
					// if the hashmap has the activity already, increase the frequency by 1
					int tempFreq = output.get(activityTemp).intValue();
					output.put(activityTemp, tempFreq + 1);
				} else {
					// System.out.println("new");
					output.put(activityTemp, 1);
				}
			}

		}

		// Sort the HashMap by values in descending order
		List<Map.Entry<String, Integer>> entries = new ArrayList<>(output.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
				return entry2.getValue().compareTo(entry1.getValue());
			}
		});

		// Create a new LinkedHashMap to preserve the insertion order of sorted entries
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

//		// Print the sorted HashMap
//		for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}

		return sortedMap;
	}

	public static void writeToFile(LinkedHashMap<String, Integer> unhealthy, LinkedHashMap<String, Integer> healthy,
			LinkedHashMap<String, Integer> superHealthy, int count) {
		// output results to a text file
		// saves file to directory
		// String outputPath = "output.txt";
		// saves file to Desktop
		String outputPath = System.getProperty("user.home") + "/Desktop/output.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

			writer.write("\nSuper-Healthy Cluster:\n");
			int index = 0;
			for (Entry<String, Integer> entry : superHealthy.entrySet()) {
				if (index < count) {
					String activity = entry.getKey();
					Integer freq = entry.getValue();
					writer.write("Activity: " + activity + "\tFrequency: " + freq + "\n");
					index++;
				}
			}

			writer.write("\nHealthy Cluster:\n");
			index = 0;
			for (Entry<String, Integer> entry : healthy.entrySet()) {
				if (index < count) {
					String activity = entry.getKey();
					Integer freq = entry.getValue();
					writer.write("Activity: " + activity + "\tFrequency: " + freq + "\n");
					index++;
				}
			}

			writer.write("\nUnhealthy Cluster:\n");
			index = 0;
			for (Entry<String, Integer> entry : unhealthy.entrySet()) {
				if (index < count) {
					String activity = entry.getKey();
					Integer freq = entry.getValue();
					writer.write("Activity: " + activity + "\tFrequency: " + freq + "\n");
					index++;
				}
			}

			System.out.println("Results written to text file successfully!");
		} catch (IOException e) {
			System.out.println("Error writing to text file.");
			e.printStackTrace();
		}
=======
		
>>>>>>> 26e4ef4 (Output to text file and comments)
	}
}
