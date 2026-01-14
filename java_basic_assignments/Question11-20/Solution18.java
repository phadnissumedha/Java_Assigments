/*
Question 18 : Write a program to accept a date and print whether the date falls into a leap year. Accept a
date in any format supported in one of the previous problems. [Date manipulation, 2 hours]
*/

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solution18 
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
        throw new IllegalArgumentException("Unsupported date format");
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a date: ");
        String inputDate = scanner.nextLine();

        try 
        {
            LocalDate date = parseDate(inputDate);
            int year = date.getYear();

            if (date.isLeapYear()) 
            {
                System.out.println(year + " is a leap year.");
            } 
            else
            {
                System.out.println(year + " is not a leap year.");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}
