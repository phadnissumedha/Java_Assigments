/*
Question 16 : Extend (14) to print supported timezones, and accept a valid timezone as input and print
time as per the time zone selected by an end user.
*/

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Solution16 
{

    public static void main(String[] args) 
    {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Time Zone:");
        System.out.println("1. IST (Asia/Kolkata)");
        System.out.println("2. UTC");
        System.out.println("3. PST (America/Los_Angeles)");
        System.out.println("4. EST (America/New_York)");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        ZoneId zoneId;

        switch (choice)
        {
            case 1:
                zoneId = ZoneId.of("Asia/Kolkata");
                break;
            case 2:
                zoneId = ZoneId.of("UTC");
                break;
            case 3:
                zoneId = ZoneId.of("America/Los_Angeles");
                break;
            case 4:
                zoneId = ZoneId.of("America/New_York");
                break;
            default:
                System.out.println("Invalid choice!");
                scanner.close();
                return;
        }

        ZonedDateTime time = ZonedDateTime.now(zoneId);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("\nCurrent Date & Time:");
        System.out.println(time.format(formatter));

        scanner.close();
    }
}
