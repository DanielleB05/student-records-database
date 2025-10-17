import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The GradebookManager class handles reading student grade
 * data from multiple CSV files. It then stores the data in
 * StudentRecords and writes output using CSVFile.
 * @author Danielle Boisseranc
 */
public class GradebookManager 
{
    /**
     * Constructs a GradebookManager object.
     */
    public GradebookManager()
    {
        // Fixes JavaDoc warning
    }

    // Array list to store all student records from the CSV files
    /**
     * List to store all student records from the CSV files.
     */
    private ArrayList<StudentRecords> students = new ArrayList<>();

    /**
     * Searches for a student by ID in the given list.
     * @param id The student ID to search for.
     * @return The StudentRecords object if found, otherwise null.
     */
    private StudentRecords findStudent(String id) 
    {
        // Searches for student in the list using id
        for (StudentRecords s : students) 
        {
            if (s.getId().equals(id)) 
            {
                // Student found
                return s;
            }
        }

        // Student not found
        return null;
    }   

    /**
     * Writes both detailed and summary CSV output files
     * after all student data has been read and processed.
     * @param detailsFile The filename for the detailed grade report.
     * @param summaryFile The filename for the summary grade report.
     * @throws IOException If an error occurs while writing files.
     */
    public void writeOutputs(String detailsFile, String summaryFile) throws IOException 
    {
        // Write two output files after all student data is processed
        CSVFile.writeDetails(detailsFile, students);
        CSVFile.writeSummary(summaryFile, students);
    }

    /**
     * Reads a CSV file containing student scores, parses the data,
     * and adds scores to the given list.
     * @param filename The CSV file to read.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public void addFile(String filename) throws FileNotFoundException 
    {
        // Create scanner to read from file
        Scanner sc = new Scanner(new File(filename));

        // If the file is empty, stop reading
        if (!sc.hasNextLine()) 
        {
            sc.close();
            return;
        }

        // Read the header line of this CSV
        String headerLine = sc.nextLine();
        String[] headers = GradebookReader.parseCSVLine(headerLine);

        // Read remaining lines of the file
        while (sc.hasNextLine()) 
        {
            String line = sc.nextLine().trim();

            // Skip any empty lines
            if (line.isEmpty()) 
            {
                continue;
            }

            // Split current line into ID, name, and score categories
            String[] parts = GradebookReader.parseCSVLine(line);

            // Check if line doesn't have specific categories
            if (parts.length < 2) 
            {
                System.out.println("Skipping malformed line: " + line);
                continue;
            }

            String id = parts[0].trim();
            String name = parts[1].trim();

            // If statement to remove extra "overall" line
            if (id.equalsIgnoreCase("OVERALL") || name.equalsIgnoreCase("OVERALL")) 
            {
                continue;
            }

            // Find or create the student
            StudentRecords student = findStudent(id);

            if (student == null) 
            {
                student = new StudentRecords(id, name);
                students.add(student);
            }

            // Loop through each column in the CSV file for current student
            for (int i = 2; i < parts.length; i++) 
            {
                String scoreStr = parts[i].trim();

                // Skip blank score lines
                if (!scoreStr.isEmpty()) 
                {
                    try 
                    {
                        // Convert score from text to double
                        double score = Double.parseDouble(scoreStr);

                        // Get category name from header
                        String colCategory = headers[i];

                        // Only add score if it matches one of the EXPECTED_CATEGORIES
                        int index = -1;

                        for (int j = 0; j < CSVFile.EXPECTED_CATEGORIES.length; j++) 
                        {
                            if (CSVFile.EXPECTED_CATEGORIES[j].equals(colCategory)) 
                            {
                                index = j;
                                break;
                            }
                        }

                        // Only record the score if the category is valid
                        if (index >= 0) 
                        {
                            // Get the maximum possible points for the category
                            double max = CSVFile.MAX_POINTS[index];

                            // Set score to not exceed maximum amount
                            if (score > max) 
                            {
                                score = max;
                            }

                            // Add the score to the current student's record
                            student.addScore(colCategory, score);
                        }

                    } 
                    catch (NumberFormatException e) 
                    {
                        // Check for invalid score entries
                        System.out.println("Skipping invalid score: " + scoreStr + " for student " + id);
                    }
                }
            }
        }

        // Close file scanner
        sc.close();

        System.out.println("Read file: " + filename);
    }
}
