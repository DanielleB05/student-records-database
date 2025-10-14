import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class GradebookReader 
{
    public static void readFile(String filename, Hashtable<String, StudentRecords> table) throws FileNotFoundException
    {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        if (scanner.hasNextLine())
        {
            scanner.nextLine();
        }

        String category = "";

        if (filename.toLowerCase().contains("homework"))
        {
            category = "Homework";
        }
        else if (filename.toLowerCase().contains("quiz"))
        {
            category = "Quiz";
        }
        else if (filename.toLowerCase().contains("exam"))
        {
            category = "Exam";
        }
        else 
        {
            category = "Other";
        }
    }
}
