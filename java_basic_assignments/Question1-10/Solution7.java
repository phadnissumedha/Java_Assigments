/*
Question 7 : Extend program (5) to accept 3 inputs: “num1”, “num2” and “operation” where operation
could be “+”, “-”, “*” or “/” to represent sum, difference, multiplication or division. The output
will be output of “num1” <operation> “num2”. The output shall be “num1=<num1>
num2=<num2> <operation>=<result>” where “<operation>” will be replaced by the
operation name. Use “sum”, “difference”, “multiply” and “divide” as an operation name
*/

import java.util.Scanner;

public class Solution7
{
  public static void main(String args[])
{
    Scanner scanner = new Scanner(System.in);
    System.out.print("enter number 1 :");
    int num1 = scanner.nextInt();

    System.out.print("enter number 2 :");
    int num2 = scanner.nextInt();

    System.out.print("enter operator :");
    char op = scanner.next().charAt(0);

    int result;
    switch(op)
    {
        case '+'->{
        result = num1 + num2 ;
        System.out.println("sum : " +result);
        }
        
        case '-'->{
        result = num1 - num2 ;
        System.out.println("difference : " +result);
        }
        case '*'->
        {
        result = num1 * num2 ;
        System.out.println("Product : " +result);
        }
        case '/'->
        {
        if (num2 !=0)
        {
            result = num1 / num2 ;
            System.out.println("Ratio : " +result);   
        }
        else
        System.out.println(" Denominator cant be zero ");
    }
    default -> System.out.println("Invalid operator");
    }
    scanner.close();
    }
}