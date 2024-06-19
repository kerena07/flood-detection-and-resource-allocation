package application;

import java.util.*;

public class FloodPredictionAndResourceAllocation {
    static class WeatherForecast {
        int precipitation;
        int windSpeed;

        public WeatherForecast(int precipitation, int windSpeed) {
            this.precipitation = precipitation;
            this.windSpeed = windSpeed;
        }
    }

    static class Geography {
        int elevation;
        int riverProximity;

        public Geography(int elevation, int riverProximity) {
            this.elevation = elevation;
            this.riverProximity = riverProximity;
        }
    }

    static class Zone {
        int id;
        int predictedFloodLevel;
        int requiredResources;

        Zone(int id) {
            this.id = id;
        }
    }

    static class ResourceAllocation {
        int zoneId;
        int allocatedResources;

        ResourceAllocation(int zoneId, int allocatedResources) {
            this.zoneId = zoneId;
            this.allocatedResources = allocatedResources;
        }
    }

    public static int[][] predictFloodLevels(int[] historicalData, WeatherForecast[] forecasts, Geography[] geography) {
        int n = historicalData.length;
        int m = forecasts.length;
        int[][] floodLevels = new int[n][m];

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                floodLevels[i][j] = (int) (historicalData[i] * 0.6 + random.nextInt(100) * 0.3 + random.nextInt(100) * 0.1);
            }
        }
        return floodLevels;
    }

    public static List<ResourceAllocation> allocateResources(Zone[] zones, int totalResources) {
        int n = zones.length;
        int[][] dp = new int[n + 1][totalResources + 1];
        int[][] allocation = new int[n][totalResources + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= totalResources; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= zones[i - 1].requiredResources) {
                    int value = dp[i - 1][j - zones[i - 1].requiredResources] + zones[i - 1].predictedFloodLevel;
                    if (value > dp[i][j]) {
                        dp[i][j] = value;
                        allocation[i - 1][j] = zones[i - 1].requiredResources;
                    }
                }
            }
        }

        List<ResourceAllocation> result = new ArrayList<>();
        int remainingResources = totalResources;
        for (int i = n - 1; i >= 0; i--) {
            if (allocation[i][remainingResources] > 0) {
                result.add(new ResourceAllocation(zones[i].id, allocation[i][remainingResources]));
                remainingResources -= allocation[i][remainingResources];
            }
        }
        return result;
    }
}
