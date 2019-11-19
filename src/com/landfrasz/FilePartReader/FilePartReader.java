package com.landfrasz.FilePartReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilePartReader {
    private String filePath;
    private int fromLine;
    private int toLine;

    public FilePartReader() {
        this.filePath = "/";
        this.fromLine = 0;
        this.toLine = 0;
    }

    public void setup(String filePath, int fromLine, int toLine) {
        if (fromLine < 1 || toLine < fromLine) {
            throw new IllegalArgumentException();
        }
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws IOException {
        return Files.readString(Path.of(this.filePath));
    }

    public String readLines() throws IOException {
        StringBuilder sb = new StringBuilder();
        String[] fileLines = this.read().strip().split("\n");
        for (int i = fromLine - 1; i < toLine; i++) {
            sb.append(fileLines[i]).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Files.createFile(Path.of("src/test.txt"));
    }
}
