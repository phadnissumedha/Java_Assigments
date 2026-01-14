/*
Question 19 : Write a program to accept two dates (any of the supported period) and print an output
whether date1 and date2 are equal, date1 is earlier than date2 or date1 is later than date2.
*/

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solution19 
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
            try 
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                if (pattern.contains("HH")) 
                {
                    return LocalDateTime.parse(input, formatter);
                } 
                else 
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

            if (date1.isEqual(date2))
            {
                System.out.println("Date 1 is equal to Date 2");
            } 
            else if (date1.isBefore(date2))
            {
                System.out.println("Date 1 is earlier than Date 2");
            } 
            else 
            {
                System.out.println("Date 1 is later than Date 2");
            }
        } 
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}
