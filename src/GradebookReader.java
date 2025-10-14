import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class GradebookReader 
{
    public static void readFile(String filename, Hashtable<String, StudentRecord> table) throws FileNotFoundException
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

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine().trim();

            if (line.length() > 0)
            {
                String[] parts = line.split(",");

                if (parts.length >= 3)
                {
                    String id = parts[0];
                    String name = parts[1];
                    String scoreVal = parts[2];

                    double score = 0;

                    if (isNumeric(scoreVal))
                    {
                        score = Double.parseDouble(scoreVal);
                    }

                    StudentRecord student = table.get(id);

                    if (student == null)
                    {
                        student = new StudentRecord(id, name);
                        table.put(id, student);
                    }

                    student.addScore(category, score);
                }
            }
        }

        scanner.close();
        System.out.println("read files: " + filename);
    }

    private static boolean isNumeric(String val)
    {
        if (val == null || val.isEmpty())
        {
            return false;
        }

        for (int i = 0; i < val.length(); i++)
        {
            char c = val.charAt(i);

            if ((c < '0' || c > '9') && c != '.')
            {
                return false;
            }
        }

        return true;
    }
}
