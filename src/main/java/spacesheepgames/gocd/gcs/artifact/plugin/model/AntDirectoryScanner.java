package spacesheepgames.gocd.gcs.artifact.plugin.model;

import org.apache.tools.ant.DirectoryScanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AntDirectoryScanner {

    public static List<File> addDirectoryFiles(File baseDir, String directory) {
        List<File> allFiles = new ArrayList<>();
        File[] files = new File(baseDir, directory).listFiles();
        if(files != null) {
            for(File f : files) {
                if (f.isDirectory()){
                    for (File file : addDirectoryFiles(baseDir, directory + "/" + f.getName()))
                    {
                        allFiles.add(file);
                    }
                } else {
                    allFiles.add(new File(directory, f.getName()));
                }
            }
        }     
        return allFiles;
    }

    public List<File> getFilesMatchingPattern(File baseDir, String pattern) {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(baseDir);
        scanner.setIncludes(pattern.trim().split(" *, *"));
        scanner.scan();

        String[] allPaths = scanner.getIncludedFiles();
        List<File> allFiles = new ArrayList<>();
        String[] directories = scanner.getIncludedDirectories();
        for (String directory : directories) {
            allFiles.addAll(addDirectoryFiles(baseDir, directory));
        }

        for (int i = 0; i < allPaths.length; i++) {
            File file = new File(allPaths[i]);
            if (!allFiles.contains(file)) {
                allFiles.add(new File(allPaths[i]));
            }
        }
        return allFiles;
    }
}
