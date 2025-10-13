import java.io.FileNotFoundException;
import java.io.IOException;

public class PA2Main
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        if (args.length == 0)
        {
            System.out.println("Usage: java PA2Main <file1.csv> <file2.csv> ...");
            return;
        }

        System.out.println("PA2Main start.");

        GradebookManager manager = new GradebookManager();

        for (String filename : args)
        {
            System.out.println("Reading file: " + filename);
            manager.addFile(filename);
        }

        System.out.println("All files read successfully.");
        manager.writeOutputs("details.csv", "summary.csv");

        System.out.println("Details and summary files written successfully.");
    }
}
