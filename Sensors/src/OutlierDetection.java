public class OutlierDetection extends SensorAnalyzer {

    /**
     * Determines the average of the temperature readings, and reports an
     * outlier if a reading differs by more than 10°C
     * @param timestamp The timestamp of the reading
     * @param readings A reading of temperatures recorded at the timestamp
     * @return Whether outliers were detected at the timestamp
     */
    @Override
    public String analyze(String timestamp, int[] readings) {
        double sum = 0;
        for (int reading: readings) sum += reading;
        double avg = sum / readings.length;

        for (int reading: readings) if (Math.abs(avg - reading) > 10) {
            return String.format("Outliers detected at %s.", timestamp);
        }
        return String.format("No outliers at %s.", timestamp);
    }
}
