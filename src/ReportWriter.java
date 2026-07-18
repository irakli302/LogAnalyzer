import java.io.*;

public class ReportWriter implements AutoCloseable{
    public FileOutputStream fos;
    public BufferedWriter writer;

    public ReportWriter(File outputFile) throws FileNotFoundException {
        this.fos = new FileOutputStream(outputFile);
        this.writer = new BufferedWriter(new OutputStreamWriter(this.fos));
    }

    public void writeErrorLine(String line) {
        try {
            this.writer.write("[CRITICAL ERROR] -> " + line);
            this.writer.newLine();

            this.writer.flush();

            FileDescriptor fd = fos.getFD();
            fd.sync();
        } catch (SyncFailedException e){
            System.err.println("Hardware disk sync failed: " + e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void printSummary(long totalLines, long infoCount, long warningCount, long errorCount){
        try {
            this.writer.write("===============================");
            this.writer.newLine();
            this.writer.write("       LOG ANALYSIS SUMMARY");
            this.writer.newLine();
            this.writer.write("===============================");
            this.writer.newLine();
            this.writer.write("Total Log Lines Processed: " + totalLines);
            this.writer.newLine();
            this.writer.write("INFO Messages: " + infoCount);
            this.writer.newLine();
            this.writer.write("WARNING Messages: " + warningCount);
            this.writer.newLine();
            this.writer.write("ERROR Messages: " + errorCount);
            this.writer.newLine();
            this.writer.write("===============================");
            this.writer.flush();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        if (this.writer != null){
            this.writer.close();
        }
    }
}
