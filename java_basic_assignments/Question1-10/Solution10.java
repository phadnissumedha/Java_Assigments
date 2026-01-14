/*
Question 10 : Extend (8) to support “sort” operation. Use an in-built function call for sorting numbers.

question 8 : Write a program to prompt for one number at a time, till the user enters “proceed”. Upon
receiving “proceed”, the program shall calculate the sum of all numbers and produce an
output. Ensure that only valid numbers are considered as an input.
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Solution10
{
    public static void main( String args [])
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Valid Numbers . Type sort to get numbers in sorted manner");

        ArrayList<Double> numberList = new ArrayList<>();

        while(true)
        {
         System.out.print("enter number :");
         String number = scanner.next();

         if(number.equalsIgnoreCase("sort"))
         {
            if(numberList.isEmpty())
            {
                System.out.println("No valid numbers entered.");
            }
            else
            {
                Collections.sort(numberList);
                System.out.println("Sorted Numbers "+numberList);   
            }
            break;
        }    
        try
        {
            double num = Double.parseDouble(number);
            numberList.add(num);
        }
         catch(NumberFormatException e)
        {
            System.out.println("invalid input!");
        }
        }
        scanner.close();
    }
}