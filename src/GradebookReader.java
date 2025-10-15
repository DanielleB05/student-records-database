import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;

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

        String baseName = new File(filename).getName().replace(".csv", "");
        String category = "";

        if (baseName.toLowerCase().contains("homework"))
        {
            category = "HW";
        }
        else if (baseName.toLowerCase().contains("quiz")) 
        {
            category = "Q";
        }
        else if (baseName.toLowerCase().contains("exam")) 
        {
            category = "E";
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
                String[] parts = parseCSVLine(line);

                if (parts.length >= 3)
                {
                    String id = parts[0].trim();
                    String name = parts[1].trim().replaceAll("^\"|\"$", "");
                    String scoreVal = parts[2].trim();

                    double score = 0;

                    if (isNumeric(scoreVal))
                    {
                        score = Double.parseDouble(scoreVal);
                    }

                    StudentRecords student = table.get(id);

                    if (student == null)
                    {
                        student = new StudentRecords(id, name);
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

    public static String[] parseCSVLine(String line) 
    {
        ArrayList<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) 
        {
            char c = line.charAt(i);

            if (c == '"') 
            {
                inQuotes = !inQuotes;
            } 
            else if (c == ',' && !inQuotes) 
            {
                parts.add(current.toString().trim());
                current.setLength(0);
            } 
            else 
            {
                current.append(c);
            }
        }

        parts.add(current.toString().trim());

        return parts.toArray(new String[0]);
    }
}
