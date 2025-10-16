/*
 * Name: Danielle Boisseranc
 * Assignment: PA2 - Streams and Hash Table
 * Resources: None
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class PA2Main
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        // Print message to indicate program has started
        System.out.println("PA2Main start:");
        System.out.println("------------------------------");

        // Create instance of GradebookManager to handle reading
        // files and generating output.
        GradebookManager manager = new GradebookManager();

        // Array of input CSV files containing categories for output
        String[] files = {
            "data/homework_1.csv", "data/homework_2.csv",
            "data/quizzes_1.csv", "data/quizzes_2.csv",
            "data/exams_1.csv", "data/exams_2.csv"
        };

        // Loop through each file in the array
        for (String filename : files)
        {
            // Read the current file using GradebookManager
            System.out.println("Reading file: " + filename);
            manager.addFile(filename);
        }

        System.out.println("------------------------------");
        System.out.println("All files read successfully.\n");

        // After reading all input files, generate output files accordingly
        // Details.csv contains individual category scores
        // Summary.csv contains total scores and final grade percentages
        manager.writeOutputs("details.csv", "summary.csv");

        System.out.println("\nDetails and summary files written successfully.");
    }
}
