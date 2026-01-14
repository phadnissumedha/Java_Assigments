/*
Question 5 : Write a program to prompt the user for 2 inputs: num1 and num2 and generate a sum of two
numbers as output. The program must accept only whole numbers (positive or negative) or
throw an error. The output shall be “num1=<num1> num2=<num2> sum=<result>” where
“<num1>”, “<num2>” and “<result>” will be replaced with actual value. 
*/

import java.util.Scanner;

public class Solution5 
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter number 1: ");
            int num1 = scanner.nextInt(); 

            System.out.print("Enter number 2: ");
            int num2 = scanner.nextInt(); 

            int sum = num1 + num2;
            System.out.println("num1 = " + num1 + " num2 = " + num2 + " sum = " + sum);

        } 
        catch (Exception e) 
        {
            System.out.println("Error: Only whole numbers are accepted!");
        } 
        finally 
        {
            scanner.close();
        }
    }
}
