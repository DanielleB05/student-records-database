import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFile 
{
    public static void writeDetails(String filename, ArrayList<StudentRecords> students) throws IOException
    {
        FileWriter writer = new FileWriter(filename);

        ArrayList<String> allCategories = new ArrayList<>();

        for (StudentRecords s : students) 
        {
            for (String cat : s.getCategories()) 
            {
                if (!allCategories.contains(cat)) 
                {
                    allCategories.add(cat);
                }
            }
        }

        allCategories.sort((a, b) -> 
        {
            char typeA = a.charAt(0); 
            char typeB = b.charAt(0);
            
            if (typeA != typeB) 
            {
                return Character.compare(typeA, typeB);
            }

            try
            {
                int numA = Integer.parseInt(a.substring(2));
                int numB = Integer.parseInt(b.substring(2));

                return Integer.compare(numA, numB);
            }
            catch (NumberFormatException e)
            {
                return a.compareTo(b);
            }
        });

        writer.write("ID,Name");

        for (String cat : allCategories) 
        {
            writer.write("," + cat);
        }

        writer.write("\n");

        for (StudentRecords s : students) 
        {
            writer.write(s.getId() + ",\"" + s.getName() + "\"");

            for (String cat : allCategories) 
            {
                int index = s.getCategories().indexOf(cat);

                if (index >= 0) {
                    writer.write("," + s.getScores().get(index));
                } 
                else 
                {
                    writer.write(",");
                }
            }

            writer.write("\n");
        }

        writer.close();
        System.out.println("Details written to " + filename);
    }

    public static void writeSummary(String filename, ArrayList<StudentRecords> students) throws IOException
    {
        FileWriter writer = new FileWriter(filename);

        ArrayList<String> categories = new ArrayList<>();

        for (StudentRecords s : students)
        {
            for (String cat : s.getCategories())
            {
                if (!categories.contains(cat))
                {
                    categories.add(cat);
                }
            }
        }

        categories.sort((a, b) -> 
        {
            char typeA = a.charAt(0); 
            char typeB = b.charAt(0);

            if (typeA != typeB) 
            {
                return Character.compare(typeA, typeB);
            }

            try
            {
                int numA = Integer.parseInt(a.substring(2));
                int numB = Integer.parseInt(b.substring(2));

                return Integer.compare(numA, numB);
            }
            catch (NumberFormatException e)
            {
                return a.compareTo(b);
            }
        });

        writer.write("ID,Name,Overall");

        for (String category : categories)
        {
            writer.write("," + category);
        }

        writer.write("\n");

        for (StudentRecords s : students)
        {
            writer.write(s.getId() + ",\"" + s.getName() + "\"");

            ArrayList<String> studentCategories = s.getCategories();
            ArrayList<Double> studentScores = s.getScores();

            double sum = 0;

            for (double score : studentScores)
            {
                sum += score;
            }

            double overall = studentScores.isEmpty() ? 0 : sum / studentScores.size();
            writer.write("," + overall);

            for (String category : categories)
            {
                double score = 0;

                for (int i = 0; i < studentCategories.size(); i++)
                {
                    if (studentCategories.get(i).equals(category))
                    {
                            score = studentScores.get(i);
                        break;
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
