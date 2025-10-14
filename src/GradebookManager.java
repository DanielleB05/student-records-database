import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        }

        if (sc.hasNextLine())
        {
            sc.nextLine();
        }

        String category = filename.contains("_") ? filename.split("_")[0] : filename;
        
        while (sc.hasNextLine())
        {
            Scanner line = new Scanner(sc.nextLine());
            String id = line.next();
            String name = line.next();
            double score = line.nextDouble();

            StudentRecords student = findStudent(id);

            if (student == null)
            {
                student = new StudentRecords(id, name);
                students.add(student);
            }

            student.addScore(category, score);
            line.close();
        }

        sc.close();
        System.out.println("Read file: " + filename);
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

    public void writeOutputs(String detailsFile, String summaryFile) throws IOException
    {
        CSVFile.writeDetails(detailsFile, students);
        CSVFile.writeSummary(summaryFile, students);
    }

    public ArrayList<StudentRecords> getStudents()
    {
        return students;
    }
}
