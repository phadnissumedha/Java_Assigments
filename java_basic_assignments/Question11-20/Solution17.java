/* 
Question 17 : Write a program to accept two dates (any of the formats supported in the earlier problem) 
and print a difference in human readable format
e.g. “1 year 2 day 32 minutes”. [Date manipulation, 3 hours] 
*/


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solution17
 {
    private static LocalDateTime parseDate(String input) 
    {
        String[] patterns = 
        {
            "dd-MM-yyyy HH:mm",
            "dd/MM/yyyy HH:mm",
            "yyyy-MM-dd HH:mm",
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd"
        };

        for (String pattern : patterns) 
        {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                if (pattern.contains("HH")) 
                {
                    return LocalDateTime.parse(input, formatter);
                } else 
                {
                    return LocalDate.parse(input, formatter).atStartOfDay();
                }
            } 
            catch (DateTimeParseException ignored) {}
        }

        throw new IllegalArgumentException("Unsupported date format!");
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter date 1: ");
        String sDate1 = scanner.nextLine();

        System.out.print("Enter date 2: ");
        String sDate2 = scanner.nextLine();

        try 
        {
            LocalDateTime date1 = parseDate(sDate1);
            LocalDateTime date2 = parseDate(sDate2);

            if (date2.isBefore(date1)) 
            {
                LocalDateTime temp = date1;
                date1 = date2;
                date2 = temp;
            }

            Period period = Period.between(date1.toLocalDate(), date2.toLocalDate());
            LocalDateTime tempDate = date1.plus(period);

            Duration duration = Duration.between(tempDate, date2);

            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;

            System.out.println("\nDifference:");
            System.out.println(
                period.getYears() + " years " +
                period.getMonths() + " months " +
                period.getDays() + " days " +
                hours + " hours " +
                minutes + " minutes"
            );

        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
