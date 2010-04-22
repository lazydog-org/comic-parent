package comic.manager.utility;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File utility.
 * 
 * @author  Ron Rickard
 */
public class FileUtility {

    /**
     * Delete the file.
     * 
     * @param  directoryPath  the directory path.
     * @param  fileName       the file name.
     * 
     * @return  true if the file is deleted, otherwise false.
     */
    public static boolean deleteFile(String directoryPath,
                                     String fileName) {
        
        // Declare.
        boolean fileDeleted;
        
        // Initialize.
        fileDeleted = false;
        
        // Check if the directory path and file name exist.
        if (directoryPath != null && fileName != null) {
            
            // Declare.
            File file;
            
            // Get the file.
            file = new File(directoryPath, fileName);

            // Delete the file.
            fileDeleted = file.delete();
        }
        
        return fileDeleted;
    }
    
    /**
     * Check if the file exists.
     * 
     * @param  directoryPath  the directory path.
     * @param  fileName       the file name.
     * 
     * @return  true if the file exists, otherwise false.
     */
    public static boolean fileExists(String directoryPath,
                                     String fileName) {
        
        // Declare.
        boolean fileExists;
        
        // Initialize.
        fileExists = false;
        
        // Check if the directory path and file name exist.
        if (directoryPath != null && fileName != null) {
            
            // Declare.
            File file;
            
            // Get the file.
            file = new File(directoryPath, fileName);

            // Test whether the file exists or not.
            fileExists = file.exists();
        }
        
        return fileExists;
    }
    
    /**
     * Get the file names.
     * 
     * @param  directoryPath  the directory path.
     * 
     * @return  the file names.
     */
    public static List<String> getFileNames(String directoryPath) {
        return getFileNames(directoryPath, "");
    }
    
    /**
     * Get the file names.
     * 
     * @param  directoryPath    the directory path.
     * @param  fileNamePattern  the file name pattern.
     * 
     * @return  the file names.
     */
    public static List<String> getFileNames(String directoryPath,
                                            String fileNamePattern) {
        
        // Declare.
        List<String> fileNames;
        
        // Initialize.
        fileNames = null;
        
        // Check if the directory path exists.
        if (directoryPath != null) {
            
            // Declare.
            File directory;
            
            // Get the directory.
            directory = new File(directoryPath);
            
            // Check if the directory exists and is a directory.
            if (directory.exists() && directory.isDirectory()) {
                
                // Declare.
                FileNameFilter fileNameFilter;
                String[] fileNamesAsArray;
                Filter jpegFilter;
                Filter matchFilter;

                // Create the JPEG filter.
                jpegFilter = new Filter();
                jpegFilter.setRule(Filter.Rule.ENDS_WITH);
                jpegFilter.setString(".jpg");
                
                // Create the match filter.
                matchFilter = new Filter();
                matchFilter.setRule(Filter.Rule.CONTAINS);
                matchFilter.setString(fileNamePattern);
              
                // Add the JPEG and match filters to the file name filter.
                fileNameFilter = new FileNameFilter();
                fileNameFilter.addFilter(jpegFilter);
                fileNameFilter.addFilter(matchFilter);
                
                // Get the file names as an array.
                fileNamesAsArray = directory.list(fileNameFilter);
                
                // Convert the array to an array list.
                fileNames = new ArrayList<String>(Arrays.asList(fileNamesAsArray));
            }
        }
        
        return fileNames;
    }
    
    /**
     * Move the file.
     * 
     * @param  srcDirectoryPath   the source directory path.
     * @param  srcFileName        the source file name.
     * @param  destDirectoryPath  the destination directory path.
     * @param  destFileName       the destination file name.
     * 
     * @return  true if the file is moved, otherwise false.
     */
    public static boolean moveFile(String srcDirectoryPath,
                                   String srcFileName,
                                   String destDirectoryPath,
                                   String destFileName) {
        
        // Declare.
        boolean fileMoved;
        
        // Initialize.
        fileMoved = false;
        
        // Check if the directory path and file name exist.
        if (srcDirectoryPath != null && srcFileName != null &&
            destDirectoryPath != null && destFileName != null) {
            
            // Declare.
            File destFile;
            File srcFile;
            
            // Get the source file.
            srcFile = new File(srcDirectoryPath, srcFileName);
            
            // Get the destination file.
            destFile = new File(destDirectoryPath, destFileName);
            
            // Check if the destination file does not already exist.
            if (!destFile.exists()) {

                // Move the file.
                fileMoved = srcFile.renameTo(destFile);
            }
        }
        
        return fileMoved;
    }
    
    /**
     * File Name Filter.
     * 
     * @author  Ron Rickard
     */
    private static class FileNameFilter
           implements FilenameFilter {
        
        private List<Filter> filters = new ArrayList<Filter>();
        
        /**
         * Method used to filter file names.
         * 
         * @param directory  the directory.
         * @param fileName   the file name.
         * 
         * @return  true if the file is accepted, otherwise false.
         */
        @Override
        public boolean accept(File directory,
                              String fileName) {

            // Declare.
            boolean accept;
            
            // Initialize.
            accept = true;
            
            // Loop through the filters.
            for (Filter filter : this.filters) {

                switch (filter.getRule()) {
                    
                    case CONTAINS:
                        accept = (accept) ? fileName.toLowerCase().indexOf(filter.getString().toLowerCase()) != -1 : accept;
                        break;
                    case ENDS_WITH:
                        accept = (accept) ? fileName.toLowerCase().endsWith(filter.getString().toLowerCase()) : accept;
                        break;
                    case STARTS_WITH:
                        accept = (accept) ? fileName.toLowerCase().startsWith(filter.getString().toLowerCase()) : accept;
                        break;  
                }
            }
            
            return accept;
        }
        
        /**
         * Add the filter.
         * 
         * @param  filter  the filter.
         */
        public void addFilter(Filter filter) {
            this.filters.add(filter);
        }
    }
    
    /**
     * Filter.
     * 
     * @author  Ron Rickard
     */
    private static class Filter {
        
        /**
         * Rule enumeration.
         */
        public static enum Rule {
        
            CONTAINS,
            ENDS_WITH,
            STARTS_WITH;
        };
        
        private String string;
        private Rule rule;
        
        /**
         * Get the string.
         * 
         * @return  the string.
         */
        public String getString() {
            return this.string;
        }
        
        /**
         * Get the rule.
         * 
         * @return  the rule.
         */
        public Rule getRule() {
            return this.rule;
        }
        
        /**
         * Set the string.
         * 
         * @param  string  the string.
         */
        public void setString(String string) {
            this.string = string;
        }
        
        /**
         * Set the rule.
         * 
         * @param  rule  the rule.
         */
        public void setRule(Rule rule) {
            this.rule = rule;
        }
    }
}
