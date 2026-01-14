/* 
Question 11 : Extend (9) to support “countodd” and “counteven” operations to respectively print number of
times odd numbers and number of even numbers found within the list. [Expressions, 2
hours]
*/

import java.util.Scanner;

public class Solution11
{
    public static void main (String args[])
    {
        Scanner scanner = new Scanner(System.in);

        String sResponse;
        int iCountOdd =0;
        int iCountEven=0;

        System.out.println("enter one number at time. "
        + " Type 'countodd' or 'counteven' to get desired output");

        while(true)
        {
            System.out.print("enter a valid number:");
            sResponse=scanner.nextLine();

            switch(sResponse)
            {
                case "countodd"->
                {
                    System.out.println("count of odd numbers :"+iCountOdd);
                    scanner.close();
                    return;
                }

                case "counteven"->
                {
                    System.out.println("count of even numbers :"+iCountEven);
                    scanner.close();
                    return;
                }

                default->
                {
                    double number= Double.parseDouble(sResponse);
                    try{
                        if(number % 2 == 0.0)
                        {
                            iCountEven++;
                        }
                        else if (number % 2 !=0.0)
                        {
                            iCountOdd++;
                        }
                        else{}
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Invalid input!");
                    }
                }
            }
        }
    }
}