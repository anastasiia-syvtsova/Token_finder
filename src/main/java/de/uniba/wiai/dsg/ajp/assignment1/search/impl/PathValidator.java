package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * The PathValidator class is responsible for handling validation
 * of the folder and file paths and specifying Exceptions
 */
public class PathValidator {
    public PathValidator() {
    }

    /**
     * Checks that a root folder String that shall become a Path is not null
     * is not empty, exists, and is a directory
     *
     * @param rootFolderString String of path to be validated
     * @return Path object of the specified input String, if it is valid
     * @throws InvalidPathException with the description of the error occurred
     */
    Path validateFolderPathString(String rootFolderString) throws InvalidPathException {
        if (rootFolderString == null) {
            throw new NullPointerException("Root folder is null");
        }
        if (rootFolderString.length() == 0) {
            throw new InvalidPathException(rootFolderString, "Root folder name is empty");
        }

        Path rootFolderPath = Path.of(rootFolderString);
        if (Files.notExists(rootFolderPath)) {
            throw new InvalidPathException(rootFolderString, "Root folder does not exist");
        }
        if (!Files.isDirectory(rootFolderPath)) {
            throw new InvalidPathException(rootFolderString, "Specified root folder has to be a directory");
        }
        return rootFolderPath;
    }

    /**
     * Checks that a result file String that shall become a Path is not null,
     * is not empty and, references an existing directory, whether the result
     * file String specifies a parent directory other than the current or is not
     * a directory
     *
     * @param resultFileString String of path to be validated
     * @return Path object of the specified input String if it is valid
     * @throws InvalidPathException with the description of the error occurred
     */
    Path validateFilePathString(String resultFileString) throws InvalidPathException {
        if (resultFileString == null) {
            throw new NullPointerException("Result file is null");
        }
        if (resultFileString.length() == 0) {
            throw new InvalidPathException(resultFileString, "Result file name is empty");
        }

        Path resultFilePath = Path.of(resultFileString);
        if (resultFilePath.getParent() != null) {
            if (!Files.isDirectory(resultFilePath.getParent())) {
                throw new InvalidPathException(resultFileString,
                        "Specified parent directory of result file " + "does not exist");
            }
        }
        if (Files.isDirectory(resultFilePath)) {
            throw new InvalidPathException(resultFileString, "Result file cannot be a directory");
        }

        return resultFilePath;
    }
}