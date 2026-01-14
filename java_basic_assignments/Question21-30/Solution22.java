/*
Question 22 : Same as (21) to accept a list of holidays, and then prompt a user for two dates
(in the supported format) and print the number of working days between two dates.
Consider both dates during the calculation.
*/

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solution22
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
                }
                else 
                {
                    return LocalDate.parse(input, formatter);
                }
            }
            catch (DateTimeParseException ignored) {}
        }
        throw new IllegalArgumentException("Unsupported date format!");
    }
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        LocalDate[] holidays = new LocalDate[50];
        int count = 0;

        System.out.println("Enter holiday dates (type 'done' to finish):");
        while (true) 
        {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) 
                break;
            try 
            {
                holidays[count++] = parseDate(input);
                System.out.println("Holiday added.");
            } 
            catch (IllegalArgumentException e)
            {
                System.out.println("Invalid date format. Try again.");
            }
        }

        LocalDate startDate = null, endDate = null;

        while (startDate == null) 
        {
            System.out.println("Enter start date:");
            String input = scanner.nextLine();
            try
            {
                startDate = parseDate(input);
            }
            catch (IllegalArgumentException e) 
            {
                System.out.println("Invalid date format. Try again.");
            }
        }
        while (endDate == null) 
        {
            System.out.println("Enter end date:");
            String input = scanner.nextLine();
            try 
            {
                endDate = parseDate(input);
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("Invalid date format. Try again.");
            }
        }        
        if (startDate.isAfter(endDate)) 
        {
            LocalDate temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        int workingDays = 0;
        LocalDate current = startDate;

        while (!current.isAfter(endDate))
        {
            DayOfWeek day = current.getDayOfWeek();
            boolean isHoliday = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);

            for (int i = 0; i < count; i++) 
            {
                if (holidays[i].equals(current)) 
                {
                    isHoliday = true;
                    break;
                }
            }

            if (!isHoliday) workingDays++;
            current = current.plusDays(1);
        }
        System.out.println("Number of working days between " + startDate + " and " + endDate + ": " + workingDays);
        scanner.close();
    }
}

