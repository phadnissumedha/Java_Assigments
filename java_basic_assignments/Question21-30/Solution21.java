/*
Question 21 : Write a program to accept a list of holidays (date in any of the supported formats). Store this
list in an internal array. After the user confirms entering of the holiday list, accept a date from
the user, and confirm whether it's a working day or not. (All Saturdays and Sundays are
implicitly considered as holidays). [Arrays & Date Manipulation, 4 hours]
*/

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Solution21 
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

        
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) 
            {
                break;
            }
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
        
        System.out.println("Enter a date to check: ");
        String checkInput = scanner.nextLine();

        try {
            LocalDate checkDate = parseDate(checkInput);
            DayOfWeek day = checkDate.getDayOfWeek();

            boolean isHoliday = false;
        
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
            {
                isHoliday = true;
            } 
            else 
            {           
                for (int i = 0; i < count; i++) 
                {
                    if (holidays[i].equals(checkDate)) 
                    {
                        isHoliday = true;
                        break;
                    }
                }
            }

            if (isHoliday) 
            {
                System.out.println("It is a working day.");
            } else
            {
                System.out.println("It is a Holiday.");
            }

        } catch (IllegalArgumentException e) 
        {
            System.out.println("Invalid date format.");
        }
        scanner.close();
    }
}
