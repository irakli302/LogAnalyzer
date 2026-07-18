import java.io.File;
import java.io.IOException;

public class LogAnalyzer {
    public static void main(String[] args){
        String inputPath;
        String outputPath;

        if(args.length >= 2){
            inputPath = args[0];
            outputPath = args[1];
        }
        else {
            inputPath = "logs/server.log";
            outputPath = "reports/summary_report.txt";
            System.out.println("No arguments provided. Using default.");
        }

        File inputFile = new File(inputPath);
        File outFile = new File(outputPath);
        File outParentFile = outFile.getParentFile();


        if(outParentFile != null && !outParentFile.exists()){
            System.err.println("Parent folder doesn`t exists for report file!");
            System.out.println("Creating missing output directory: " + outParentFile.getPath());


            boolean parent = outParentFile.mkdirs();
            if(!parent) {
                System.err.println("Error: Could not create directory: " + outParentFile.getPath());
                System.exit(1);
            }
        }


        System.out.println("Checking system permissions...");
        if(!PermissionChecker.checkPermissions(inputFile, outFile))
            System.exit(1);


        System.out.println("✅ Security check passed.");
        System.out.println("Starting log analysis on: " + inputPath);


        try (ReportWriter reportWriter = new ReportWriter(outFile)){
            LogParser logParser = new LogParser();
            LogParser.LogAnalysisResult result = logParser.parseLog(inputFile, reportWriter);
            reportWriter.printSummary(result.totalLines, result.infoCount, result.warningCount, result.errorCount);
            System.out.println("Safe analysis complete! Hardware sync executed. Report saved to: " + outputPath);
        }catch (IOException e){
            System.err.println("Critical error during analysis: " + e.getMessage());
            System.exit(1);
        }
    }
}