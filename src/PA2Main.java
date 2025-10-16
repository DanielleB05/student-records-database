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
        System.out.println("PA2Main start.");

        GradebookManager manager = new GradebookManager();

        String[] files = {
            "data/homework_1.csv",
            "data/homework_2.csv",
            "data/quizzes_1.csv",
            "data/quizzes_2.csv",
            "data/exams_1.csv",
            "data/exams_2.csv"
        };

        for (String filename : files)
        {
            System.out.println("Reading file: " + filename);
            manager.addFile(filename);
        }

        System.out.println("All files read successfully.");
        manager.writeOutputs("details.csv", "summary.csv");

        System.out.println("Details and summary files written successfully.");
    }
}
