import java.util.ArrayList;

public class StudentRecords 
{
    private String id;
    private String name;
    private ArrayList<Double> scores;
    private ArrayList<String> categories;

    public StudentRecords(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.scores = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

}
