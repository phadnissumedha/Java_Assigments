/*
Question 3 : Write a program to accept “<name>” as command line argument, and print “Hello <name>”.
Replace “<name>” with the text entered by a user running the program.

compile : javac question3.java
Run : java question3 "name"
*/

public class Solution3

{
    public static void main(String args[]) 
    {    
        if (args.length == 0) 
        {
            System.out.println("Please provide a name as a command-line argument.");
            return;
        }

        String name = args[0];
        System.out.println("Hello " + name);
    }
}
