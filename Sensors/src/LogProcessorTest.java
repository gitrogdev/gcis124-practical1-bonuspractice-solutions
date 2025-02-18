import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class LogProcessorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        // Set the default locale to ensure consistent floating-point formatting
        Locale.setDefault(Locale.US);
    }

    @Test
    void testHeatwaveWarning() {
        LogProcessor.processLog("data/sensor_log.txt", new HeatwaveWarning());
        String output = outContent.toString().trim();
        String[] lines = output.split(System.lineSeparator());

        assertEquals("Normal temperatures at 2025-02-18 14:30:00.", lines[0]);
        assertEquals("Normal temperatures at 2025-02-18 14:45:00.", lines[1]);
        assertEquals("Normal temperatures at 2025-02-18 15:00:00.", lines[2]);
        assertEquals("Normal temperatures at 2025-02-18 15:15:00.", lines[3]);
        assertEquals("Heatwave warning at 2025-02-18 15:30:00!", lines[4]);
        assertEquals("Normal temperatures at 2025-02-18 15:45:00.", lines[5]);
        assertEquals("Normal temperatures at 2025-02-18 16:00:00.", lines[6]);
        assertEquals("Normal temperatures at 2025-02-18 16:15:00.", lines[7]);
    }

    @Test
    void testSuddenDropAlert() {
        LogProcessor.processLog("data/sensor_log.txt", new SuddenDropAlert());
        String output = outContent.toString().trim();
        String[] lines = output.split(System.lineSeparator());

        assertEquals("Stable temperatures at 2025-02-18 14:30:00.", lines[0]);
        assertEquals("Stable temperatures at 2025-02-18 14:45:00.", lines[1]);
        assertEquals("Stable temperatures at 2025-02-18 15:00:00.", lines[2]);
        assertEquals("Sudden temperature drop at 2025-02-18 15:15:00!", lines[3]);
        assertEquals("Stable temperatures at 2025-02-18 15:30:00.", lines[4]);
        assertEquals("Sudden temperature drop at 2025-02-18 15:45:00!", lines[5]);
        assertEquals("Stable temperatures at 2025-02-18 16:00:00.", lines[6]);
        assertEquals("Stable temperatures at 2025-02-18 16:15:00.", lines[7]);
    }

    @Test
    void testTrendAnalyzer() {
        LogProcessor.processLog("data/sensor_log.txt", new TrendAnalyzer());
        String output = outContent.toString().trim();
        String[] lines = output.split(System.lineSeparator());

        assertEquals("No clear trend at 2025-02-18 14:30:00.", lines[0]);
        assertEquals("No clear trend at 2025-02-18 14:45:00.", lines[1]);
        assertEquals("No clear trend at 2025-02-18 15:00:00.", lines[2]);
        assertEquals("No clear trend at 2025-02-18 15:15:00.", lines[3]);
        assertEquals("Temperature rising at 2025-02-18 15:30:00.", lines[4]);
        assertEquals("Temperature falling at 2025-02-18 15:45:00.", lines[5]);
        assertEquals("No clear trend at 2025-02-18 16:00:00.", lines[6]);
        assertEquals("Temperature rising at 2025-02-18 16:15:00.", lines[7]);
    }

    @Test
    void testOutlierDetection() {
        LogProcessor.processLog("data/sensor_log.txt", new OutlierDetection());
        String output = outContent.toString().trim();
        String[] lines = output.split(System.lineSeparator());

        assertEquals("No outliers at 2025-02-18 14:30:00.", lines[0]);
        assertEquals("No outliers at 2025-02-18 14:45:00.", lines[1]);
        assertEquals("No outliers at 2025-02-18 15:00:00.", lines[2]);
        assertEquals("No outliers at 2025-02-18 15:15:00.", lines[3]);
        assertEquals("No outliers at 2025-02-18 15:30:00.", lines[4]);
        assertEquals("No outliers at 2025-02-18 15:45:00.", lines[5]);
        assertEquals("No outliers at 2025-02-18 16:00:00.", lines[6]);
        assertEquals("Outliers detected at 2025-02-18 16:15:00.", lines[7]);
    }

    @BeforeEach
    void setUpStreams() {
        System.setErr(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    void testNonExistentFile() {
        LogProcessor.processLog("non_existent_file.txt", new HeatwaveWarning());
        String errorOutput = outContent.toString();
        assertFalse(errorOutput.isEmpty(), "Expected error output for non-existent file");
        assertTrue(errorOutput.contains("FileNotFoundException"), 
                   "Expected FileNotFoundException in stack trace, but got: " + errorOutput);
    }
}