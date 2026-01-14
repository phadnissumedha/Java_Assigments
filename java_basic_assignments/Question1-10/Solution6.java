/*
Question 6 : Write a program same as (4) above, but accept floating point numbers. 
*/

import java.util.Scanner;

public class Solution6 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        try 
        {
            
            System.out.print("Enter number 1 (floating-point only): ");
            String input1 = scanner.next();
           
            if (!input1.contains(".")) 
            {
                throw new Exception("Not a floating-point number!");
            }
            double num1 = Double.parseDouble(input1);

            
            System.out.print("Enter number 2 (floating-point only): ");
            String input2 = scanner.next();
           
            if (!input2.contains(".")) 
            {
                throw new Exception("Not a floating-point number!");
            }
            double num2 = Double.parseDouble(input2);
                        
            double sum = num1 + num2;

            System.out.println("num1 = " + num1 + " num2 = " + num2 + " sum = " + sum);
        } 
        catch (Exception e) 
        {
            System.out.println("Error: Only floating-point numbers with a decimal are allowed!");
        } 
        finally
        {
            scanner.close();
        }
    }
}
