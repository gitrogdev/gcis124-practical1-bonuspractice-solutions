public class TrendAnalyzer extends SensorAnalyzer {

    /**
     * Analyzes trends in temperature changes, whether the temperatures are
     * rising or falling, or if there is no clear trend
     * @param timestamp The timestamp of the reading
     * @param readings A reading of temperatures recorded at the timestamp
     * @return Whether a clear trend was found at the timestamp
     */
    @Override
    public String analyze(String timestamp, int[] readings) {
        if (readings.length == 1) {
            return String.format("No clear trend at %s.", timestamp);
        }

        int i = 1;
        while (i < readings.length && readings[i] == readings[i - 1]) i++;

        if (i == readings.length) {
            return String.format("No clear trend at %s.", timestamp);
        }

        boolean increasing = readings[i] > readings[i - 1];
        for (; i < readings.length; i++) {
            if (
                (increasing && readings[i] < readings[i - 1])
                || (!increasing && readings[i] > readings[i - 1])
            ) {
                return String.format("No clear trend at %s.", timestamp);
            }
        }
        return String.format(
            "Temperature %s at %s.",
            increasing ? "rising" : "falling", timestamp
        );
    }
    
}
