package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import de.uniba.wiai.dsg.ajp.assignment1.search.TokenFinderException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The InputHandling class is responsible for input operations and file/path
 * handling such as reading, writing and validation of paths.
 */
public class IOHandling {
    private final PathValidator pathValidator = new PathValidator();
    ArrayList<String> ignoredFolders = new ArrayList<>();
    List<Path> allFilesList = new ArrayList<>();
    List<MyFile> fileList = new ArrayList<>();

    /**
     * Validates the root folder Path and reads all Paths. If Path contains directory,
     * checks whether it should be ignored. If Path is a file with required extension
     * writes it ot the ArrayList of all file Paths.
     *
     * @param rootFolder    directory to be searched for files
     * @param fileExtension file extension to be searched for
     * @param ignoreFile    file containing directories to be ignored
     * @return ArrayList of Paths of all required files
     * @throws TokenFinderException with the description of the error occurred
     */
    public List<Path> findAllFiles(String rootFolder, String fileExtension, String ignoreFile) throws TokenFinderException {
        Path rootFolderPath;
        Path filePath;

        try {
            rootFolderPath = pathValidator.validateFolderPathString(rootFolder);
        } catch (InvalidPathException | NullPointerException exception) {
            throw new TokenFinderException("Invalid root folder path", exception);
        }

        try {
            filePath = pathValidator.validateFilePathString(ignoreFile);
        } catch (InvalidPathException | NullPointerException exception) {
            throw new TokenFinderException("Invalid ignore file path", exception);
        }

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(rootFolderPath)) {
            for (Path entry : ds) {
                if (Files.isDirectory(entry)) {
                    if (!containsIgnoreFolder(entry, filePath)) {
                        findAllFiles(entry.toString(), fileExtension, ignoreFile);
                    }
                } else if (entry.toString().endsWith(fileExtension)) {
                    allFilesList.add(entry);
                }
            }
        } catch (IOException exception) {
            throw new TokenFinderException("Could not read the file tree", exception);
        }
        return allFilesList;
    }


    /**
     * Takes the Path of a file and reads the content of the file. From each
     * line of the file, a Line object is created with the respective line number,
     * source file and content. All Lines are collected in an ArrayList, which then
     * represents the file.
     *
     * @param filePaths Paths of files to be converted into an ArrayList of Lines
     * @param token     to search for
     * @return ArrayList of all Lines objects from the file provided
     * @throws TokenFinderException with the description of the error occurred
     */
    public List<MyFile> createListOfFindings(List<Path> filePaths, String token) throws TokenFinderException {
        for (Path path : filePaths) {
            List<String> fileContent = readFileToList(path);
            List<Line> lineList = new ArrayList<>();
            MyFile file = new MyFile(path.toString(), token, 0, lineList);
            fileList.add(file);
            int lineCounter = 0;
            for (String lineContent : fileContent) {
                var columnCounter = lineContent.indexOf(token);
                lineCounter++;
                while (columnCounter >= 0) {
                    Line newLine = new Line(path, columnCounter, lineCounter, lineContent);
                    file.setLineList(newLine);
                    file.incrementCounter();
                    columnCounter = lineContent.indexOf(token, columnCounter + 1);
                }
            }
        }
        return fileList;
    }

    /**
     * Compares path with the provided List of Strings to be ignored
     *
     * @param targetFolderPath to be checked against the String list
     * @param ignoreFilePath   Strings to be ignored
     * @return Result of the comparison
     * @throws TokenFinderException with the description of the error occurred
     */

    private boolean containsIgnoreFolder(Path targetFolderPath, Path ignoreFilePath) throws TokenFinderException {
        var res = false;

        List<String> ignoreList = readFileToList(ignoreFilePath);
        for (String s : ignoreList) {
            if (targetFolderPath.toString().endsWith(s)) {
                res = true;
                ignoredFolders.add(targetFolderPath.toString());
            }
        }
        return res;
    }

    /**
     * Writes the content of the file to an ArrayList of Strings
     *
     * @param path the Path to the file to read
     * @return Content of the file as Arraylist of Strings
     * @throws TokenFinderException with the description of the error occurred
     */
    private List<String> readFileToList(Path path) throws TokenFinderException {
        List<String> strings = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            var lineContent = "";
            while ((lineContent = reader.readLine()) != null) {
                strings.add(lineContent);
            }
        } catch (IOException exception) {
            throw new TokenFinderException("Could not read file" + path, exception);
        }

        return strings;
    }

    /**
     * Prints out the result on the console an into output file
     *
     * @param files          List of MyFiles found
     * @param outputPath     path to the output file
     * @param token          token to search for
     * @param ignoredFolders folders been ignored
     */
    public void generateResult(List<MyFile> files, String outputPath, String token, ArrayList<String> ignoredFolders) {

        StringBuilder result = new StringBuilder();
        var counter = 0;

        for (String s : ignoredFolders) {
            result.append(s).append(" directory  was  ignored.").append("\n");
        }

        result.append("\n");


        for (MyFile mf : files) {
            result.append(mf.toString());
            counter += mf.getCounter();
        }

        result.append("\n" + "The  project  includes  **").append(token).append("** ").append(counter).append(" times.");
        System.out.println(result);

        //Output to resultFile
        FileWriter writer;
        File file = new File(outputPath);

        try {
            writer = new FileWriter(file, false);
            writer.write(result.toString());

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
