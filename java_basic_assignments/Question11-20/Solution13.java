/*
Question 13 : Write a program to prompt for three inputs: character to be used for display, num1 to
represent number of rows and num2 to represent number of columns. The output will be a
rectangular matrix where each cell will print a character input as a first input value. [Loops, 4
hours]
*/

import java.util.Scanner;

public class Solution13
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter character to display :");
        char character = scanner.next().charAt(0);

        System.out.print("Enter number of rows :");
        int rows = scanner.nextInt();   

        System.out.print("Enter number of columns :");
        int columns = scanner.nextInt();   

        for (int i= 0 ; i< rows ; i++)
        {
            for(int j= 0 ; j< columns ; j++)
            {
                System.out.print(character + " ");
            }
           System.out.println();
        }
        scanner.close();
    }
}