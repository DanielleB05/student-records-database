import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFile 
{
    public static void writeDetails(String filename, ArrayList<StudentRecord> students) throws IOException
    {
        FileWriter writer = new FileWriter(filename);
        writer.write("ID, Name, Category, Score");

        for (StudentRecord s : students)
        {
            ArrayList<String> categories = s.getCategories();
            ArrayList<Double> scores = s.getScores();

            for (int i = 0; i < categories.size(); i++)
            {
                writer.write(s.getId() + "," + s.getName() + "," + categories.get(i) + "," + scores.get(i));
            }
        }

        writer.close();

        System.out.println("Details written to " + filename);
    }
}
