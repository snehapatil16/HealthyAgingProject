package testing_commit;

import java.util.*;

public class KMeansClusterer {

    private int numClusters;
    private int maxIterations;
    private double[][] centroids;
    private Map<Integer, double[]> data;

    public KMeansClusterer(int numClusters, int maxIterations, double[][] initialCentroids, HashMap<Integer, double[]> data) {
        this.numClusters = numClusters;
        this.maxIterations = maxIterations;
        this.centroids = initialCentroids;
        this.data = data;
    }

    public void cluster() {
        int numDimensions = centroids[0].length;
        Map<Integer, List<double[]>> clusters = new HashMap<>();
        for (int i = 0; i < numClusters; i++) {
            clusters.put(i, new ArrayList<>());
        }
        for (int iter = 0; iter < maxIterations; iter++) {
            // assign each data point to the nearest centroid
            for (int clusterId : clusters.keySet()) {
                clusters.get(clusterId).clear();
            }
            for (Map.Entry<Integer, double[]> entry : data.entrySet()) {
                double[] tScores = entry.getValue();
                int closestCentroidId = -1;
                double minDistance = Double.MAX_VALUE;
                for (int centroidId = 0; centroidId < numClusters; centroidId++) {
                    double distance = euclideanDistance(tScores, centroids[centroidId]);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestCentroidId = centroidId;
                    }
                }
                clusters.get(closestCentroidId).add(tScores);
                tScores[tScores.length-1] = closestCentroidId;
            }
            // update centroids
            for (int centroidId = 0; centroidId < numClusters; centroidId++) {
                double[] newCentroid = new double[numDimensions];
                for (double[] tScores : clusters.get(centroidId)) {
                    for (int i = 0; i < numDimensions; i++) {
                        newCentroid[i] += tScores[i];
                    }
                }
                if (!clusters.get(centroidId).isEmpty()) {
                    for (int i = 0; i < numDimensions; i++) {
                        newCentroid[i] /= clusters.get(centroidId).size();
                    }
                }
                centroids[centroidId] = newCentroid;
            }
        }
    }

    private double euclideanDistance(double[] v1, double[] v2) {
        double sumSquares = 0.0;
        for (int i = 0; i < v1.length; i++) {
            sumSquares += Math.pow(v1[i] - v2[i], 2);
        }
        return Math.sqrt(sumSquares);
    }

}
