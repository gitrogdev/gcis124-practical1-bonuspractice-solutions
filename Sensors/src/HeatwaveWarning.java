public class HeatwaveWarning extends SensorAnalyzer {
    /**
     * If all the entries in a temperature reading are above 30°C, returns a
     * heatwave warning
     * @param timestamp The timestamp of the reading
     * @param readings A reading of temperatures recorded at the timestamp
     * @return Whether a heatwave was detected at the timestamp
     */
    @Override
    public String analyze(String timestamp, int[] readings) {
        for (int temperature: readings) {
            if (temperature < 30) return String.format(
                "Normal temperatures at %s.",
                timestamp
            );
        }
        return String.format("Heatwave warning at %s!", timestamp);
    }
}
