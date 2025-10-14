import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class CSVFile 
{
    public static void writeDetails(String filename, ArrayList<StudentRecords> students) throws IOException
    {
        FileWriter writer = new FileWriter(filename);
        writer.write("ID, Name, Category, Score\n");

        for (StudentRecords s : students)
        {
            ArrayList<String> categories = s.getCategories();
            ArrayList<Double> scores = s.getScores();

            for (int i = 0; i < categories.size(); i++)
            {
                writer.write(s.getId() + "," + s.getName() + "," + categories.get(i) + "," + scores.get(i) + "\n");
            }
        }

        writer.close();

        System.out.println("Details written to " + filename);
    }

    public static void writeSummary(String filename, ArrayList<StudentRecords> students) throws IOException
    {
        FileWriter writer = new FileWriter(filename);

        Hashtable<String, Boolean> categoryTable = new Hashtable<>();

        for (StudentRecords s : students)
        {
            for (String category : s.getCategories())
            {
                categoryTable.put(category, true);
            }
        }

        ArrayList<String> categories = new ArrayList<>(categoryTable.keySet());

        writer.write("ID, Name, Overall\n");

        for (String category : categories)
        {
            writer.write("," + category);
        }

        writer.write("\n");

        for (StudentRecords s : students)
        {
            writer.write(s.getId() + "," + s.getName() + "\n");

            ArrayList<Double> scores = s.getScores();

            double sum = 0;

            for (double score : scores)
            {
                sum += score;
            }

            double overall = scores.isEmpty() ? 0 : sum / scores.size();
            writer.write("," + overall);

            ArrayList<String> studentCategories = s.getCategories();
            ArrayList<Double> studentScores = s.getScores();

            for (String category : categories)
            {
                double score = 0;

                for (int i = 0; i < studentCategories.size(); i++)
                {
                    if (studentCategories.get(i).equals(category))
                    {
                        score = studentScores.get(i);
                    }
                }

                writer.write("," + score);
            }

            writer.write("\n");
        }

        writer.close();
        System.out.println("Summary written to " + filename);
    }
}
