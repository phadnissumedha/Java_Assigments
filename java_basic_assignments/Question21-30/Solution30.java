/*
Question 30 :Extend the above program to ignore common words (e.g. “the”, “a”, “an”, …) and single letter
words (e.g. “I”). [Revision of previous concepts, 3 hours] 
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution30
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Usage: java Solution29 <filename>");
            return;
        }

        String fileName = args[0];
        HashMap<String, Integer> wordCountMap = new HashMap<>();

        Set<String> stopWords = new HashSet<>();
        stopWords.add("the");
        stopWords.add("a");
        stopWords.add("an");
        stopWords.add("is");
        stopWords.add("are");
        stopWords.add("to");
        stopWords.add("in");
        stopWords.add("on");
        stopWords.add("and");
        stopWords.add("of");
        stopWords.add("for");
        stopWords.add("with");
        stopWords.add("that");
        stopWords.add("this");
        stopWords.add("it");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                line = line.toLowerCase();

                String[] words = line.split("\\W+");

                for (String word : words)
                {
                    if (!word.isEmpty() && word.length() > 1 && !stopWords.contains(word))
                    {
                        wordCountMap.put(word,
                                wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
            System.out.println("Word occurrences (excluding common & single-letter words):");
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
