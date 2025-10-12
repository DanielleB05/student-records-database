import java.util.ArrayList;

public class GradebookMerger 
{
    private ArrayList<String> csvFiles;

    public GradebookMerger()
    {
        csvFiles = new ArrayList<>();
    }

    public void addFile(String filename)
    {
        csvFiles.add(filename);
        System.out.println("file added: " + filename);
    }

    public void writeOutputs(String detailsFile, String summaryFile)
    {
        System.out.println("Writing output files.");
        System.out.println("Details file: " + detailsFile);
        System.out.println("Summary file: " + summaryFile);
    }
}
