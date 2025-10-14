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

    }
}
