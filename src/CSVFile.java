import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The CSVFile class handles writing student grade data
 * to CSV files, incluing detailed and summary reports.
 * Each report includes header, maximum point values,
 * and calculated scores.
 * @author Danielle Boisseranc
 */
public class CSVFile 
{
    /**
     * Constructs a CSVFile object.
     * 
     */
    private CSVFile()
    {
        // Fixes JavaDoc warning
    }

    // List of all categories in detailed grade report
    /**
     * List of all categories in the detailed grade report.
     */
    public static final String[] EXPECTED_CATEGORIES = 
    {
        "HW1", "HW2", "HW3", "HW4", "HW5", "HW6", "HW7",
        "Q1", "Q2", "Q3", "Q4",
        "E1", "E2", "E3"
    };

    // Maximum possible points for detail.csv categories
    /**
     * Maximum possible point values for each category in the details.csv file.
     */
    public static final double[] MAX_POINTS = 
    {
        100, 100, 100, 100, 100, 100, 100,
        100, 100, 100, 100,
        100, 100, 200
    };

    // Maximum possible points for summary.csv categories
    /**
     * Maximum possible point values for each category in the summary.csv file.
     */
    public static final double[] MAX_POINTS_2 =
    {
        700, 400, 400
    };
    
    /**
     * Writes the detailed grade report to the details.csv file.
     * @param filename The name of the output CSV file.
     * @param students A list of StudentRecords containing student IDs, names, and scores.
     * @throws IOException If an error occurs while writing the file.
     */
    public static void writeDetails(String filename, ArrayList<StudentRecords> students) throws IOException 
    {
        FileWriter writer = new FileWriter(filename);

        // Header row
        writer.write("ID, Name");

        // Add categories to output
        for (String cat : EXPECTED_CATEGORIES) 
        {
            writer.write(", " + cat);
        }

        writer.write("\n");

        // Add overall line to output
        writer.write(", Overall");

        // Add maximum possible points to output
        for (double max : MAX_POINTS) 
        {
            writer.write(", " + max);
        }

        writer.write("\n");

        // Write each student's score in same category order
        for (StudentRecords s : students) 
        {
            // Write student's ID and name with quotes to handle commas
            writer.write(s.getId() + ",\"" + s.getName() + "\"");

            ArrayList<String> studentCategories = s.getCategories();
            ArrayList<Double> studentScores = s.getScores();

            // Loop through all expected categories
            for (String cat : EXPECTED_CATEGORIES) 
            {
                // Find index of current category in student's list
                int index = studentCategories.indexOf(cat);

                // Assign '0' as score if category not found
                double score = (index >= 0) ? studentScores.get(index) : 0;
                writer.write(", " + score);
            }

            writer.write("\n");
        }

        writer.close();
        System.out.println("Details written to " + filename);
    }

    /**
     * Writes the summary report to the summary.csv file.
     * @param filename The name of the output CSV file.
     * @param students A list of StudentRecords containing all scores per student.
     * @throws IOException If an error occurs while writing the file.
     */
    public static void writeSummary(String filename, ArrayList<StudentRecords> students) throws IOException
    {
        FileWriter writer = new FileWriter(filename);

        // Write header row
        writer.write("ID, Name, Final Grade, Homework, Quizzes, Exams\n");

        // Write maximum points row
        writer.write(", Overall, ");

        for (double max : MAX_POINTS_2) 
        {
            writer.write(", " + max);
        }

        writer.write("\n");
        
        // Process each student's record
        for (StudentRecords s : students)
        {
            ArrayList<String> categories = s.getCategories();
            ArrayList<Double> scores = s.getScores();

            double homeworkTotal = 0;
            double quizzesTotal = 0;
            double examsTotal = 0;

            // Add scores by category type
            for (int i = 0; i < categories.size(); i++)
            {
                String cat = categories.get(i);
                double score = scores.get(i);

                if (cat.startsWith("HW"))
                {
                    homeworkTotal += score;
                }
                else if (cat.startsWith("Q"))
                {
                    quizzesTotal += score;
                }
                else if (cat.startsWith("E"))
                {
                    examsTotal += score;
                }
            }

            // Normalize totals by maximum points
            double[] totals = {homeworkTotal / 700.0, quizzesTotal / 400.0, examsTotal / 400.0};

            // Set weights for each category
            double[] weights = {0.35, 0.35, 0.30};

            double finalGrade = 0;

            // Calulcate final grade category for each student
            for (int i = 0; i < totals.length; i++)
            {
                finalGrade += totals[i] * weights[i];
            }

            // Convert to percentage
            finalGrade *= 100;

            // Round to 4 decimal places
            finalGrade = Math.round(finalGrade * 10000.0) / 10000.0;

            // Write each student's results for every category
            writer.write(s.getId() + ",\"" + s.getName() + "\", ");
            writer.write(finalGrade + ", ");
            writer.write(homeworkTotal + ", ");
            writer.write(quizzesTotal + ", ");
            writer.write(examsTotal + "\n");
        }

        writer.close();
        System.out.println("Summary written to " + filename);
    }
}