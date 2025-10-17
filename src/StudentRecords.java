import java.util.ArrayList;

public class StudentRecords 
{
    // List of valid category names
    private static final String[] EXPECTED_CATEGORIES = {
        "HW1", "HW2", "HW3", "HW4", "HW5", "HW6", "HW7",
        "Q1", "Q2", "Q3", "Q4",
        "E1", "E2", "E3"
    };

    // ID, name, scores, and categories for each file
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
        // Return student's ID for output
        return id;
    }

    public String getName()
    {
        // Return student's name for output
        return name;
    }

    public ArrayList<Double> getScores()
    {
        // Return list of all scores for current student
        return scores;
    }

    public ArrayList<String> getCategories()
    {
        // Return list of categories for each score
        return categories;
    }

    public void addScore(String category, double score)
    {
        // Check if category is valid
        for (String expected : EXPECTED_CATEGORIES)
        {
            if (expected.equals(category))
            {
                // Add category and score to student's data
                categories.add(category);
                scores.add(score);

                return;
            }
        }
    }
}
