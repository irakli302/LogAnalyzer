import java.io.File;

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

        File outFile = new File(outputPath);
        File outParentFile = outFile.getParentFile();
        if(outParentFile != null && !outParentFile.exists()){
            System.out.println("Parent folder doesn`t exists for report file!");
            System.out.println("Creating missing output directory: " + outParentFile.getPath());


            boolean parent = outParentFile.mkdirs();
            if(!parent) {
                System.out.println("Error: Could not create directory: " + outParentFile.getPath());
                System.exit(1);
            }
        }
    }
}