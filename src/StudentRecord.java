import java.util.ArrayList;

public class StudentRecord 
{
    private String id;
    private String name;
    private ArrayList<Double> scores;
    private ArrayList<String> categories;

    public StudentRecord(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.scores = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void addScore(String category, double score)
    {
        categories.add(category);
        scores.add(score);
    }

    public ArrayList<Double> getScores()
    {
        return scores;
    }

    public ArrayList<String> getCategories()
    {
        return categories;
    }
}
