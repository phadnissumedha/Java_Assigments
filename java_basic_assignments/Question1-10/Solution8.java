/*
Question 8 : Write a program to prompt for one number at a time, till the user enters “proceed”. Upon
receiving “proceed”, the program shall calculate the sum of all numbers and produce an
output. Ensure that only valid numbers are considered as an input.
*/

import java.util.Scanner;

public class Solution8
{
    public static void main( String args[])
    {
        Scanner scanner = new Scanner(System.in);

        String sResponse;
        double sum=0.0;

        System.out.println("enter one number at a time enter proceed to calculate the sum of numbers");

        while(true)
        {
            System.out.print("Enter a number :");
             sResponse = scanner.nextLine();

             if(sResponse.equalsIgnoreCase("proceed"))
             {
                break;
             }
             try {
                double number = Double.parseDouble(sResponse);
                sum+=number;
             }
             catch (NumberFormatException e)
             {
                    System.out.println("Invalid Input!");
             }
        }
        System.out.println("sum of entered numbers :" +sum);
        scanner.close();
    }
}