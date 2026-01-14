// ...existing code...
/*
Extend (27) to accept URL as a command line argument instead of a hardcoded URL within
the program. [Revision of previous concepts, 2 hours]
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.net.URISyntaxException;

public class Solution28
{
    public static void main(String[] args)
    {

        if (args.length != 1)
        {
            System.out.println("Usage: java Solution28 <URL>");
            return;
        }

        String urlString = args[0];

        Path htmlFile = Paths.get("homepage.html");
        Path zipFile = Paths.get("homepage.html.zip");

        try {
            downloadToFile(urlString, htmlFile);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Error downloading file: " + e.getMessage());
            return;
        }

        try {
            compressFile(htmlFile, zipFile);
        } catch (IOException e) {
            System.out.println("Error compressing file: " + e.getMessage());
            return;
        }

        try {
            long originalSize = Files.size(htmlFile);
            long compressedSize = Files.size(zipFile);

            System.out.println("HTML file size in bytes: " + originalSize);
            System.out.println("Compressed file size in bytes: " + compressedSize);
        } catch (IOException e) {
            System.out.println("Error reading file sizes: " + e.getMessage());
        }
    }

    private static void downloadToFile(String urlString, Path target)
    throws IOException, URISyntaxException {

        URL url = new URI(urlString).toURL();
        int maxRedirects = 5;

        for (int i = 0; i < maxRedirects; i++) {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            try {
                conn.setInstanceFollowRedirects(false);
                conn.setRequestProperty("User-Agent", "Java/" + System.getProperty("java.version"));

                int status = conn.getResponseCode();

                if (status == HttpURLConnection.HTTP_MOVED_PERM ||
                    status == HttpURLConnection.HTTP_MOVED_TEMP ||
                    status == HttpURLConnection.HTTP_SEE_OTHER ||
                    status == 307 || status == 308) {

                    String location = conn.getHeaderField("Location");
                    if (location == null) {
                        throw new IOException("Redirect without Location header");
                    }

                    URL newUrl = new URL(url, location);
                    // optional guard: if you don't want to follow HTTPS redirects, fail here
                    if ("https".equalsIgnoreCase(newUrl.getProtocol())) {
                        throw new IOException("Redirected to HTTPS. SSL not supported in this program.");
                    }
                    url = newUrl;
                    // disconnect and follow redirect
                    conn.disconnect();
                    continue;
                }

                try (InputStream in = new BufferedInputStream(conn.getInputStream());
                     OutputStream out = Files.newOutputStream(target)) {

                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = in.read(buffer)) != -1)
                    {
                        out.write(buffer, 0, len);
                    }
                }
                return;
            } finally {
                conn.disconnect();
            }
        }

        throw new IOException("Too many redirects");
    }

    private static void compressFile(Path input, Path output) throws IOException {
        try (InputStream in = new BufferedInputStream(Files.newInputStream(input));
             OutputStream fos = Files.newOutputStream(output);
             ZipOutputStream zos = new ZipOutputStream(fos))
        {

            ZipEntry entry = new ZipEntry(input.getFileName().toString());
            zos.putNextEntry(entry);

            byte[] buffer = new byte[4096];
            int len;
            while ((len = in.read(buffer)) != -1)
            {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        }
    }
}
