/*
Question 23 : Same as (22) to accept a list of holidays, and then prompt a user for two inputs: input date
as a first argument and a number of business days as a second argument. Number of
business days will be a positive or negative whole number. The output shall be the date
relative to an input date +/- the number of business days. Holidays must be excluded while
calculating the output date.
*/

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solution23 
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

    private static boolean isHoliday(LocalDate date, LocalDate[] holidays, int count)
    {
        for (int i = 0; i < count; i++) 
        {
            if (date.equals(holidays[i])) 
                return true;
        }
        return false;
    }

    private static boolean isBusinessDay(LocalDate date, LocalDate[] holidays, int count) 
    {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.SATURDAY &&
               day != DayOfWeek.SUNDAY &&
               !isHoliday(date, holidays, count);
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of holidays: ");
        int numHolidays = scanner.nextInt();
        scanner.nextLine();

        LocalDate[] holidays = new LocalDate[numHolidays];

        System.out.println("Enter holidays :");
        
        for (int i = 0; i < numHolidays; i++) 
        {
            String input = scanner.nextLine();
            try
            {
                holidays[i] = parseDate(input);
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("Invalid date format. Try again.");
                i--;
            }
        }        
        System.out.print("\nEnter input date: ");
        LocalDate startDate = parseDate(scanner.nextLine());

        System.out.print("Enter number of business days (positive or negative): ");
        int businessDays = scanner.nextInt();

        LocalDate resultDate = startDate;
        int remainingDays = Math.abs(businessDays);
        int direction = businessDays >= 0 ? 1 : -1;

        while (remainingDays > 0)
        {
            resultDate = resultDate.plusDays(direction);
            if (isBusinessDay(resultDate, holidays, numHolidays)) 
            {
                remainingDays--;
            }
        }

        System.out.println("\nResulting date: " + resultDate);
        scanner.close();
    }
}
