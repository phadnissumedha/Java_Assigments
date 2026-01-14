/*
Question 14 : Write a program to print current date/time in following formats (one line per format) in UTC
time e.g. “16 Mar 2022” “Mar 16, 2022” “2022-03-16” “2022-03-16T15:52:00Z” “Tuesday, 16
March 2022” [Date manipulation, 3 hours]
*/

import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Solution14 
{
    public static void main(String[] args) 
    {
     
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneOffset.UTC);

        // 16 Mar 2022
        System.out.println(utcNow.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy")));

        // Mar 16, 2022
        System.out.println(utcNow.format(
                DateTimeFormatter.ofPattern("MMM dd, yyyy")));

        // 2022-03-16
        System.out.println(utcNow.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 2022-03-16T15:52:00Z
        System.out.println(utcNow.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));

        // Tuesday, 16 March 2022
        System.out.println(utcNow.format(
                DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")));
    }
}
