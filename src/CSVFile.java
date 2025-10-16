import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFile 
{
    public static final String[] EXPECTED_CATEGORIES = 
    {
        "HW1", "HW2", "HW3", "HW4", "HW5", "HW6", "HW7",
        "Q1", "Q2", "Q3", "Q4",
        "E1", "E2", "E3"
    };

    public static final double[] MAX_POINTS = 
    {
        100, 100, 100, 100, 100, 100, 100,
        100, 100, 100, 100,
        100, 100, 200
    };
    
    public static void writeDetails(String filename, ArrayList<StudentRecords> students) throws IOException 
    {
        FileWriter writer = new FileWriter(filename);

        writer.write("ID,Name");

        for (String cat : EXPECTED_CATEGORIES) 
        {
            writer.write("," + cat);
        }

        writer.write("\n");

        writer.write(",Overall");

        for (double max : MAX_POINTS) 
        {
            writer.write("," + max);
        }

        writer.write("\n");

        for (StudentRecords s : students) 
        {
            writer.write(s.getId() + ",\"" + s.getName() + "\"");

            ArrayList<String> studentCategories = s.getCategories();
            ArrayList<Double> studentScores = s.getScores();

            for (String cat : EXPECTED_CATEGORIES) 
            {
                int index = studentCategories.indexOf(cat);
                double score = (index >= 0) ? studentScores.get(index) : 0;
                writer.write("," + score);
            }

            writer.write("\n");
        }

        writer.close();
        System.out.println("Details written to " + filename);
    }

    public static void writeSummary(String filename, ArrayList<StudentRecords> students) throws IOException
    {
        FileWriter writer = new FileWriter(filename);

        writer.write("ID,Name,Overall");

        for (String cat : EXPECTED_CATEGORIES) 
        {
            writer.write("," + cat);
        }

        writer.write("\n");

        for (StudentRecords s : students) 
        {
            writer.write(s.getId() + ",\"" + s.getName() + "\"");

            double sum = 0;
            int count = 0;

            double[] percentages = new double[EXPECTED_CATEGORIES.length];

            for (int i = 0; i < EXPECTED_CATEGORIES.length; i++) 
            {
                String cat = EXPECTED_CATEGORIES[i];

                int index = s.getCategories().indexOf(cat);
                double percent = 0;

                if (index >= 0 && MAX_POINTS[i] != 0) 
                {
                    percent = (s.getScores().get(index) / MAX_POINTS[i]) * 100;
                }

                percentages[i] = percent;
                sum += percent;
                count++;
            }

            double overall = (count == 0) ? 0 : sum / count;

            writer.write("," + overall);

            for (double pct : percentages) 
            {
                writer.write("," + pct);
            }

            writer.write("\n");
        }

        writer.close();
        System.out.println("Summary written to " + filename);
    }
}