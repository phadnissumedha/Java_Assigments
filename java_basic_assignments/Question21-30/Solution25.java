/*
Question 25 : Write a program to take the names of candidates as input. Prompt user to keep entering new
names till user enters “done”. Once a list of names are accepted, prompt the user for a
search pattern (regex). Output shall be a list of all names where the search pattern exists.
[Array & Loop & Regex, 8-12 hours]
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution25 
{
   public static void main(String[] args) 
   {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> candidateNames = new ArrayList<>() ;
    while (true)
        {
            System.out.print("Enter candidate name (or 'done' to finish): ");
            String name = scanner.nextLine().trim();

            if (name.equalsIgnoreCase("done")) 
            {
                break;
            }        
            if (!name.isEmpty()) 
            {
                candidateNames.add(name);
            }
        }

    System.out.print("Enter regex search pattern: ");
    String regex = scanner.nextLine();
    Pattern pattern;

    try
    {
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    } 
    catch (Exception e) 
    {
        System.out.println("Invalid regex: " + e.getMessage());
        scanner.close();
        return;
    }

    boolean found = false;
    for (String candidate : candidateNames) 
    {
        Matcher m = pattern.matcher(candidate);
        if (m.find()) 
        {
            if (!found) 
            {
                System.out.println("Matching names:");
                found = true;
            }
            System.out.println(candidate);
        }
    }
    if (!found) 
    {
        System.out.println("No matches found.");
    }
scanner.close();
}
}

    



