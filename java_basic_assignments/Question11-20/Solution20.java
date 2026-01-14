/*
Question 20 : Write a program to accept two dates and print the count of week-end days (Consider
Saturdays and Sundays as week-ends)
*/

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solution20 
{

    private static LocalDate parseDate(String input) 
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
            try 
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

                if (pattern.contains("HH")) 
                {
                    return LocalDateTime.parse(input, formatter).toLocalDate();
                } else 
                {
                    return LocalDate.parse(input, formatter);
                }

            } catch (DateTimeParseException ignored) {}
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
            LocalDate date1 = parseDate(sDate1);
            LocalDate date2 = parseDate(sDate2);

            if (date1.isAfter(date2)) 
            {
                LocalDate temp = date1;
                date1 = date2;
                date2 = temp;
            }

            int weekendCount = 0;
            LocalDate current = date1;

            while (!current.isAfter(date2))
            {
                DayOfWeek day = current.getDayOfWeek();

                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) 
                {
                    weekendCount++;
                }
                current = current.plusDays(1);
            }
            System.out.println("Number of weekend days: " + weekendCount);
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}
