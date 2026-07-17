public class LogAnalyzer {
    public static void main(String[] args){
        String inputPath;
        String outputPath;

        if(args.length > 2){
            inputPath = args[0];
            outputPath = args[1];
        }
        else {
            inputPath = "logs/server.log";
            outputPath = "reports/summary_report.txt";
            System.out.println("No arguments provided. Using default.");
        }
    }
}