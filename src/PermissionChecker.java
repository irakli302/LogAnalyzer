import java.io.File;

public class PermissionChecker {
    public static boolean checkPermissions(File inputFile, File outputFile){
        if(!inputFile.exists() || !inputFile.isFile()){
            System.err.println("Permission check failed: Input log not found: " + inputFile.getPath());
            return false;
        }

        if(!inputFile.canRead()){
            System.err.println("Permission check failed: input file isn`t readable : " + inputFile.getPath());
            return false;
        }

        File parentDirectory = outputFile.getParentFile();
        if(outputFile.exists()){
            if(!outputFile.canWrite()){
                System.err.println("Permission check failed: output file isn`t writable : " + outputFile.getPath());
                return false;
            }
        }
        else {
            if(parentDirectory != null && !parentDirectory.canWrite()){
                System.err.println("Permission check failed: output parent directory isn`t writable : " + outputFile.getPath());
                return false;
            }
        }

        return true;
    }
}
