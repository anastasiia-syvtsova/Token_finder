package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.nio.file.Path;

/**
 * A Line object represents one line of a file. It includes the source file,
 * from which the line was taken, its line number in the respective file
 * and the content of the line.
 */

public class Line {
    private final Path sourceFile;
    private final int columnNumber;
    private final int lineNumber;
    private final String lineContent;

    public Line(Path sourceFile, int columnNumber, int lineNumber, String lineContent) {
        this.sourceFile = sourceFile;
        this.columnNumber = columnNumber;
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumnNumber() { return columnNumber; }

    public String getLineContent() {
        return lineContent;
    }

    public Path getSourceFile() { return sourceFile; }
}
