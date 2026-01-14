/*
Question 15 : Extend (14) to print time in IST timezone.
*/

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Solution15 
{
    public static void main(String args[])
    {
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Current time in IST: " + istTime.format(formatter));
    }

}