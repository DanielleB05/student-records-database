import java.util.ArrayList;

/**
 * The StudentRecords class represents a single student's record.
 * It stores the student's ID, name, and list of scores
 * with corresponding categories. It uses helper methods to access
 * and update the student data.
 * @author Danielle Boisseranc
 */
public class StudentRecords 
{
    // List of valid category names
    /**
     * List of valid categories for scores.
     */
    private static final String[] EXPECTED_CATEGORIES = {
        "HW1", "HW2", "HW3", "HW4", "HW5", "HW6", "HW7",
        "Q1", "Q2", "Q3", "Q4",
        "E1", "E2", "E3"
    };

    // ID, name, scores, and categories for each file

    /**
     * The student's ID
     */
    private final String id;

    /**
     * The student's full name;
     */
    private final String name;

    /**
     * List of scores corresponding to categories.
     */
    private final ArrayList<Double> scores;

    /**
     * List of categories corresponding to each score.
     */
    private ArrayList<String> categories;

    /**
     * Constructs a StudentRecords object with the given ID and name.
     * It initializes empty lists for scores and categories.
     * @param id The student's ID.
     * @param name The student's name.
     */
    public StudentRecords(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.scores = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    /**
     * Returns the student's ID.
     * @return The student ID.
     */
    public String getId()
    {
        // Return student's ID for output
        return id;
    }

    /**
     * Returns the student's full name.
     * @return The student name.
     */
    public String getName()
    {
        // Return student's name for output
        return name;
    }

    /**
     * Returns the list of scores for the current student.
     * @return An ArrayList of double representing scores.
     */
    public ArrayList<Double> getScores()
    {
        // Return list of all scores for current student
        return scores;
    }

    /**
     * Returns the list of categories corresponding to the scores.
     * @return An ArrayList of String representing categories.
     */
    public ArrayList<String> getCategories()
    {
        // Return list of categories for each score
        return categories;
    }

    /**
     * Adds a score for the specified category if it is valid.
     * Otherwise the score is ignored.
     * @param category The category of the score.
     * @param score The score value to add.
     */
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
