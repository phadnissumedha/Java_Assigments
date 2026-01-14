import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.*;

public class Solution28Test {

    private static final Path HTML_FILE = Paths.get("homepage.html");
    private static final Path ZIP_FILE = Paths.get("homepage.html.zip");

    @BeforeEach
    public void setUp() throws IOException {
        // Clean up any existing files before each test
        Files.deleteIfExists(HTML_FILE);
        Files.deleteIfExists(ZIP_FILE);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up any files created during the test
        Files.deleteIfExists(HTML_FILE);
        Files.deleteIfExists(ZIP_FILE);
    }

    @Test
    public void testDownloadToFile_ValidURL() {
        String testUrl = "http://example.com";

        assertDoesNotThrow(() -> Solution28.downloadToFile(testUrl, HTML_FILE));
        assertTrue(Files.exists(HTML_FILE), "HTML file should exist after download");
    }

    @Test
    public void testDownloadToFile_InvalidURL() {
        String invalidUrl = "http://invalid.url";

        Exception exception = assertThrows(IOException.class, () -> Solution28.downloadToFile(invalidUrl, HTML_FILE));
        assertTrue(exception.getMessage().contains("Error downloading file"), "Expected error message for invalid URL");
    }

    @Test
    public void testCompressFile_ValidFile() throws IOException {
        // Create a dummy HTML file
        Files.writeString(HTML_FILE, "<html><body>Test</body></html>");

        assertDoesNotThrow(() -> Solution28.compressFile(HTML_FILE, ZIP_FILE));
        assertTrue(Files.exists(ZIP_FILE), "ZIP file should exist after compression");
    }

    @Test
    public void testCompressFile_NonExistentFile() {
        Exception exception = assertThrows(IOException.class, () -> Solution28.compressFile(HTML_FILE, ZIP_FILE));
        assertTrue(exception.getMessage().contains("NoSuchFileException"), "Expected error message for non-existent file");
    }

    @Test
    public void testMain_ValidURL() {
        String testUrl = "http://example.com";

        // Simulate passing the URL as a command-line argument
        String[] args = {testUrl};
        Solution28.main(args);

        assertTrue(Files.exists(HTML_FILE), "HTML file should exist after main execution");
        assertTrue(Files.exists(ZIP_FILE), "ZIP file should exist after main execution");
    }

    @Test
    public void testMain_InvalidArguments() {
        String[] args = {}; // No arguments passed

        // Capture console output
        assertDoesNotThrow(() -> Solution28.main(args));
    }
}