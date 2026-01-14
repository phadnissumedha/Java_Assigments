/*
Question 2 : Write a program to prompt the user to enter a name, and print “Hello <name>”. Replace
“<name>” with the text entered by a user running the program. 
*/
import java.util.Scanner;

public class Solution2 
{

    public static void main (String args [])
    {
        System.out.print("enter  name : ");
        String sName ;

        Scanner scanner = new Scanner(System.in);

        sName = scanner.next();

        System.out.println( "Hello" +" " +sName);

        scanner.close();
    }
}