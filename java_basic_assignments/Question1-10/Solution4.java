/*
Question 4 : Modify the above program to take two command-line parameters. The first is the string for
the message template, like “Hello <name>” or any other template. The second is the actual
name to print in the message by replacing “<name>” with the given name. 

compile : javac question4.java
run : java question4 "Hello <name> " sumedha
*/

public class Solution4
{
    public static void main(String args[]) 
    {
        if (args.length != 2) 
        {
            System.out.println("Usage: <template> <name>");
            return;
        }
        String sTemplate = args[0];
        String sName = args[1];

        String sMessage = sTemplate.replace("<name>", sName);

        System.out.println(sMessage);
    }
}
