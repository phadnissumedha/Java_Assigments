/*
Question 12 : Write a program to prompt for two whole positive numbers -- “num1” and “num2”. Print
multiplication table for the number e.g. for num1=3 and num2=20, output will be “3 * 1 = 3\n3
* 2 = 6\n … \n3 * 20 = 60”.
*/

import java.util.Scanner;

public class Solution12
{
    public static void main (String args[])
    {
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter positive whole number1 :");
        int iNum1= scanner.nextInt();

        System.out.print("Enter positive whole number2 :");
        int iNum2= scanner.nextInt();

        for (int i =1 ; i <= iNum2 ; i++ )
        {
            System.out.println(iNum1 +"*" + i +"=" +(iNum1*i));
            
        }
        scanner.close();
    }
}