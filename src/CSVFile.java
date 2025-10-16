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

    public static final double[] MAX_POINTS_2 =
    {
        700, 400, 400
    };
    
    public static void writeDetails(String filename, ArrayList<StudentRecords> students) throws IOException 
    {
        FileWriter writer = new FileWriter(filename);

        writer.write("ID, Name");

        for (String cat : EXPECTED_CATEGORIES) 
        {
            writer.write(", " + cat);
        }

        writer.write("\n");

        writer.write(", Overall");

        for (double max : MAX_POINTS) 
        {
            writer.write(", " + max);
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
                writer.write(", " + score);
            }

            writer.write("\n");
        }

        writer.close();
        System.out.println("Details written to " + filename);
    }

    public static void writeSummary(String filename, ArrayList<StudentRecords> students) throws IOException
    {
        FileWriter writer = new FileWriter(filename);

        writer.write("ID, Name, Final Grade, Homework, Quizzes, Exams\n");
        writer.write(", Overall, ");

        for (double max : MAX_POINTS_2) 
        {
            writer.write(", " + max);
        }

        writer.write("\n");
        
        for (StudentRecords s : students)
        {
            ArrayList<String> categories = s.getCategories();
            ArrayList<Double> scores = s.getScores();

            double homeworkTotal = 0;
            double quizzesTotal = 0;
            double examsTotal = 0;

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

            double[] totals = {homeworkTotal / 700.0, quizzesTotal / 400.0, examsTotal / 400.0};
            double[] weights = {0.35, 0.35, 0.30};

            double finalGrade = 0;

            for (int i = 0; i < totals.length; i++)
            {
                finalGrade += totals[i] * weights[i];
            }

            finalGrade *= 100;

            finalGrade = Math.round(finalGrade * 10000.0) / 10000.0;

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