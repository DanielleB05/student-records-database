
public class PA2Main
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Usage: java PA2Main <file1.csv> <file2.csv> ...");
            return;
        }
        System.out.println("PA2Main start.");

        GradebookManager merger = new GradebookManager();

        for (String filename : args)
        {
            System.out.println("Reading file: " + filename);
        }
    }
}
