import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradebookManager 
{
    private ArrayList<StudentRecord> students = new ArrayList<>();

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

            StudentRecord student = findStudent(id);

            if (student == null)
            {
                student = new StudentRecord(id, name);
                students.add(student);
            }

            student.addScore(category, score);
            line.close();
        }

        sc.close();
        System.out.println("Read file: " + filename);
    }

    private StudentRecord findStudent(String id)
    {
        for (StudentRecord s : students)
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

    public ArrayList<StudentRecord> getStudents()
    {
        return students;
    }
}
