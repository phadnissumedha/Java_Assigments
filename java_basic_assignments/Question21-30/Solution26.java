/*
Question 26 : Visit https://merce.co and save the homepage as an HTML file from a browser as
“merce-homepage.html”. Write a program to read the saved HTML file, compress it and storethe compressed file as “merce-homepage.html.zip”. Use ready classes for compression. At
the end, the program shall print HTML file size, Compressed file size.
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Solution26
{
    public static void main(String[] args) 
    {
        Path input = Paths.get("merce-homepage.html");
        Path output = Paths.get("merce-homepage.html.zip");

        if (!Files.exists(input)) 
        {
            System.out.println("Input file not found: " + input.toAbsolutePath());
            return;
        }

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
            zos.finish();
        } 
        catch (IOException e) 
        {
            System.out.println("I/O error: " + e.getMessage());
            return;
        }
        try 
        {
            long originalSize = Files.size(input);
            long compressedSize = Files.size(output);
            System.out.println("HTML file size (bytes): " + originalSize);
            System.out.println("Compressed file size (bytes): " + compressedSize);
        } 
        catch (IOException e) 
        {
            System.out.println("Unable to read file sizes: " + e.getMessage());
        }
    }
}
