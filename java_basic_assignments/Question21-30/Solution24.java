/*
Question 24 : Write a program to take the names of candidates as input. Prompt user to keep entering new
names till user enters “done”. Once a list of names are accepted, prompt the user for a
name. Output shall be “<name> exists” or “<name> does not exist”. A name exists if the
name exactly matches one of the names provided earlier. Use case insensitive match for
comparison. [Hash data structure & String operations, 6 hours]
*/

import java.util.HashSet;
import java.util.Scanner;

public class Solution24 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> candidateNames = new HashSet<>();

        while (true)
        {
            System.out.print("Enter candidate name (or 'done' to finish): ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("done")) 
            {
                break;
            }        
            candidateNames.add(name.toLowerCase());
        }       
        System.out.print("Enter a name to check: ");
        String checkName = scanner.nextLine();
    
        if (candidateNames.contains(checkName.toLowerCase())) 
        {
            System.out.println(checkName + " exists");
        } 
        else 
        {
            System.out.println(checkName + " does not exist");
        }
        scanner.close();
    }
}
