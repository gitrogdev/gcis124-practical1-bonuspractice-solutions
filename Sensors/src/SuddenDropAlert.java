public class SuddenDropAlert extends SensorAnalyzer {

    /**
     * If the first reading is at least 10°C higher than the last reading,
     * returns a sudden temperature drop warning
     * @param timestamp The timestamp of the reading
     * @param readings A reading of temperatures recorded at the timestamp
     * @return Whether a sudden temperature drop was detected at the timestamp
     */
    @Override
    public String analyze(String timestamp, int[] readings) {
        if (readings[0] - readings[readings.length - 1] >= 10) {
            return String.format("Sudden temperature drop at %s!", timestamp);
        }
        return String.format("Stable temperatures at %s.", timestamp);
    }
    
}
