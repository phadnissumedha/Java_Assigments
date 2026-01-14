/*
Question 9 : Extend (8) to accept statistical operation instead of “proceed”. Valid values for “<operation>”
are count (to count number of valid numbers), mean value, minimum value (minimum of all
numbers input), maximum value (maximum of all numbers input).
*/

import java.util.Scanner;

public class Solution9
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);

        String sResponse;
        double sum = 0.0;
        int count = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        System.out.println("enter one number at time. "
        + " Type count/mean/min/max to get desired output");

        while(true)
        {
            System.out.print("enter a valid number :");
            sResponse=scanner.nextLine();

            switch(sResponse)
            {
                case "count"->
                {
                    System.out.println("Count of valid numbers =" + count);
                    scanner.close();
                    return;
                }
                case "mean"->
                {
                    if (count == 0) 
                    {
                        System.out.println("No valid numbers entered.");
                    } else 
                    {
                        System.out.println("Mean value = " + (sum / count));
                    }
                    scanner.close();
                    return;
                }
                case "min"->
                {
                    if (count == 0) 
                    {
                        System.out.println("No valid numbers entered.");
                    }
                    else
                    {
                        System.out.println("Minimum value =" + min);
                    }
                    scanner.close();
                    return;
                }
                case "max"->
                {
                    if (count == 0) 
                    {
                        System.out.println("No valid numbers entered.");
                    } else 
                    {
                        System.out.println("Maximum value =" + max);
                    }
                    scanner.close();
                    return;
                }
                default->
                {
                    try 
                    {
                        double number = Double.parseDouble(sResponse);
                        sum += number;
                        count++;

                        if (number < min) min = number;
                        if (number > max) max = number;

                    } 
                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid input! Enter a number or an operation.");
                    }
                }
            }
        }
    }
}