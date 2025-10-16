import java.util.ArrayList;

public class StudentRecords 
{
    private static final String[] EXPECTED_CATEGORIES = {
        "HW1", "HW2", "HW3", "HW4", "HW5", "HW6", "HW7",
        "Q1", "Q2", "Q3", "Q4",
        "E1", "E2", "E3"
    };

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

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Double> getScores()
    {
        return scores;
    }

    public ArrayList<String> getCategories()
    {
        return categories;
    }

    public void addScore(String category, double score)
    {
        for (String expected : EXPECTED_CATEGORIES)
        {
            if (expected.equals(category))
            {
                categories.add(category);
                scores.add(score);

                return;
            }
        }
    }
}
