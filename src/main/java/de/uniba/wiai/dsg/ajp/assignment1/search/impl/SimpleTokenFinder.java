package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import de.uniba.wiai.dsg.ajp.assignment1.search.SearchTask;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinder;
import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;

import java.nio.file.Path;
import java.util.List;

public class SimpleTokenFinder implements TokenFinder {

    public SimpleTokenFinder() {
        /*
         * DO NOT REMOVE
         *
         * REQUIRED FOR GRADING
         */
    }

    /**
     * Handles the search task and bundles the logic
     *
     * @param task specifies the search parameter
     * @throws TokenFinderException prepends the description of the error occurred
     */
    public void search(final SearchTask task) throws TokenFinderException {

        var inOutPutHandling = new IOHandling();
        var fileExtension = task.getFileExtension();
        var rootFolder = task.getRootFolder();
        var token = task.getToken();
        var ignoreFile = task.getIgnoreFile();
        var outputPath = task.getResultFile();

        try {
            List<Path> allFiles = inOutPutHandling.findAllFiles(rootFolder, fileExtension, ignoreFile);
            List<MyFile> allFindings = inOutPutHandling.createListOfFindings(allFiles, token);
            inOutPutHandling.generateResult(allFindings, outputPath, token, inOutPutHandling.ignoredFolders);
        } catch (Exception e) {
            throw new TokenFinderException("Something went wrong: ", e);
        }
    }


}
