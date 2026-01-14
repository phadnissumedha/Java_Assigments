/*
Question 29 : Write a program to accept a filename from command line argument, read a file and print the
number of times each word occurs in a file. Perform case insensitive match while counting
the occurrence of each word. [Hashmap & File operation & String operation, 6 hours]
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution29 
{
    public static void main(String[] args) 
    {

        if (args.length == 0)
        {
            System.out.println("Usage: java WordCount <filename>");
            return;
        }

        String fileName = args[0];
        HashMap<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {

            String line;

            while ((line = br.readLine()) != null)
            {
                
                line = line.toLowerCase();

                String[] words = line.split("\\W+");

                for (String word : words) 
                {
                    if (!word.isEmpty())
                    {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
            System.out.println("Word occurrences:");
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet())
            {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        } 
        catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
