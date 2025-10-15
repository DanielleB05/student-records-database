import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradebookManager 
{
    private ArrayList<StudentRecords> students = new ArrayList<>();

    public void addFile(String filename) throws FileNotFoundException 
    {
        Scanner sc = new Scanner(new File(filename));

        if (sc.hasNextLine()) 
        {
            sc.nextLine();
        

            String category = filename.contains("_") ? filename.split("_")[0] : filename;

            while (sc.hasNextLine()) 
            {
                String line = sc.nextLine().trim();

                if (line.isEmpty()) 
                {
                    continue;
                }

                String[] parts = GradebookReader.parseCSVLine(line);

                if (parts.length < 2) 
                {
                    System.out.println("Skipping malformed line: " + line);
                    continue; 
                }

                String id = parts[0].trim();
                String name = parts[1].trim();

                StudentRecords student = findStudent(id);

                if (student == null) 
                {
                    student = new StudentRecords(id, name);
                    students.add(student);
                }

                for (int i = 2; i < parts.length; i++) 
                {
                    String scoreStr = parts[i].trim();

                    if (!scoreStr.isEmpty()) 
                    {
                        try 
                        {
                            double score = Double.parseDouble(scoreStr);
                            student.addScore(category, score);
                        } 
                        catch (NumberFormatException e) 
                        {
                            System.out.println("Skipping invalid score: " + scoreStr + " for student " + id);
                        }
                    }
                }
            }

            sc.close();
            System.out.println("Read file: " + filename);
        }
    }

    private StudentRecords findStudent(String id)
    {
        for (StudentRecords s : students)
        {
            if (s.getId().equals(id))
            {
                return s;
            }
        }

        return null;
    }

    public void writeOutputs(String detailsFile, String summaryFile)
    {
        System.out.println("Would write outputs to: " + detailsFile + ", " + summaryFile);
    }

    public ArrayList<StudentRecords> getStudents()
    {
        return students;
    }
}
