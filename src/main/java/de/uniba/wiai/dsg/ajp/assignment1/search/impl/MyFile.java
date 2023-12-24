package de.uniba.wiai.dsg.ajp.assignment1.search.impl;

import java.util.List;

/**
 * The MyFile class object represents a file, it is responsible
 * for keeping track of all the lines and all occurrences of the token.
 * It includes file name, token searched for, count of the hits and
 * an list of Lines contained in the file
 */
class MyFile {
    private final String fileName;
    private final String token;
    private int counter;
    private List<Line> lineList;

    public MyFile(String fileName, String token, int counter, List<Line> lineList) {
        this.fileName = fileName;
        this.token = token;
        this.counter = counter;
        this.lineList = lineList;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public String getFileName() {
        return fileName;
    }

    public String getToken() {
        return token;
    }

    public List<Line> getLineList() {
        return lineList;
    }

    public void setLineList(Line ln) {
        this.lineList.add(ln);
    }

    public int getCounter() {
        return counter;
    }

    /**
     * prepares the output of a file as a String
     *
     * @return a String formatted accordingly to the assignment
     */
    public String toString() {
        var token = "**" + this.getToken() + "**";
        var lineList = this.getLineList();
        var counter = this.getCounter();
        StringBuilder str = new StringBuilder();

        for (Line elem :
                lineList) {

            var fileName = elem.getSourceFile() + ": ";
            var lineNumber = elem.getLineNumber() + ", ";
            var columnNumber = elem.getColumnNumber();
            var lineContent = elem.getLineContent();
            var lineContentModified = (" > " + lineContent.substring(0, columnNumber) + token +
                    lineContent.substring(columnNumber + this.getToken().length()) + "\n");

            str.append(fileName);
            str.append(lineNumber);
            str.append(columnNumber);
            str.append(lineContentModified);
        }
        var sum = (fileName + " includes " + token + " " + counter + " times." + "\n");
        str.append(sum);
        return str.toString();
    }
}
