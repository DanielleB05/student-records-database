/*
 * Name: Danielle Boisseranc
 * Assignment: PA2 - Streams and Hash Table
 * Resources: None
 */

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main entry point for the gradebook program.
 * It reads multiple CSV files containing student grades, 
 * processes them using GradebookManager, and generates 
 * detail and summary output files.
 * @author Danielle Boisseranc
 */
public class PA2Main
{
    /**
     * Constructs a PA2Main object.
     */
    private PA2Main()
    {
        // Fixes JavaDoc warning
    }

    /**
     * The main method to run the gradebook program.
     * It reads input CSV files, processes student scores,
     * and writes output CSV files for detal and summary reports.
     * @param args Command line arguments
     * @throws FileNotFoundException If any input file cannot be found.
     * @throws IOException If an error occurs while writing output files.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        // Print message to indicate program has started
        System.out.println("PA2Main start:");
        System.out.println("------------------------------");

        // Create instance of GradebookManager to handle reading
        // files and generating output.
        GradebookManager manager = new GradebookManager();

        // Loop through each file in the array
        for (String filename : args)
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
