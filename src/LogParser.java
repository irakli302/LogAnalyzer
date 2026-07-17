import javax.imageio.IIOException;
import java.io.*;

public class LogParser {
    public static class LogAnalysisResult {
        public final long totalLines;
        public final long infoCount;
        public final long warningCount;
        public final long errorCount;
        public final long unclassifiedCount;

        public LogAnalysisResult(long totalLines, long infoCount, long warningCount, long errorCount, long unclassifiedCount){
            this.totalLines = totalLines;
            this.infoCount = infoCount;
            this.warningCount = warningCount;
            this.errorCount = errorCount;
            this.unclassifiedCount = unclassifiedCount;
        }
    }

    public LogAnalysisResult parseLog(File inputFile){
        long totalLines = 0;
        long infoCount = 0;
        long warningCount = 0;
        long errorCount = 0;
        long unclassifiedCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null){
                totalLines++;

                if(line.contains("INFO:"))
                    infoCount++;

                else if (line.contains("WARNING:"))
                    warningCount++;

                else if (line.contains("ERROR:"))
                    errorCount++;

                else
                    unclassifiedCount++;
            }
        }catch (IOException e){
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

        return new LogAnalysisResult(totalLines,infoCount,warningCount,errorCount,unclassifiedCount);
    }
}
