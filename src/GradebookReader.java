import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;

public class GradebookReader 
{
    public static void readFile(String filename, Hashtable<String, StudentRecords> table) throws FileNotFoundException
    {
        // Create a file object and scanner to read from given filename
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // Skip first line if it contains headers
        if (scanner.hasNextLine())
        {
            scanner.nextLine();
        }

        // Get the base filename
        String baseName = new File(filename).getName().replace(".csv", "");
        String category = "";

        // Determine which category the current file represents
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

        // Process each remaining line of file
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine().trim();

            // If statement to only process filled lines
            if (line.length() > 0)
            {
                // Split line to handle commas and quotes
                String[] parts = parseCSVLine(line);

                // Check if line has ID, name, and score
                if (parts.length >= 3)
                {
                    // Get student ID, name, and score from the CSV file
                    String id = parts[0].trim();
                    String name = parts[1].trim().replaceAll("^\"|\"$", "");
                    String scoreVal = parts[2].trim();

                    double score = 0;

                    // Check if score value is numeric
                    if (isNumeric(scoreVal))
                    {
                        score = Double.parseDouble(scoreVal);
                    }

                    // Look up student in hash table
                    StudentRecords student = table.get(id);

                    // If student not found, create new record
                    if (student == null)
                    {
                        student = new StudentRecords(id, name);
                        table.put(id, student);
                    }

                    // Add score to current student's record
                    student.addScore(category, score);
                }
            }
        }

        // Close scanner
        scanner.close();

        System.out.println("read files: " + filename);
    }

    private static boolean isNumeric(String val)
    {
        // Check for empty or null strings
        if (val == null || val.isEmpty())
        {
            return false;
        }

        // Check each character for digit or decimal
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

        // Check each character in current line
        for (int i = 0; i < line.length(); i++) 
        {
            char c = line.charAt(i);

            if (c == '"') 
            {
                // Register quotations when found
                inQuotes = !inQuotes;
            } 
            else if (c == ',' && !inQuotes) 
            {
                // End current field if comma found outside of quotes
                parts.add(current.toString().trim());
                current.setLength(0);
            } 
            else 
            {
                // Add regular characters to current field
                current.append(c);
            }
        }

        // Add last field after loop ends
        parts.add(current.toString().trim());

        // Convert ArrayList to String array
        return parts.toArray(new String[0]);
    }
}
