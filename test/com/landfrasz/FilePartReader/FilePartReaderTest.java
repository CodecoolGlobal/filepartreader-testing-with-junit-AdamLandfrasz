package com.landfrasz.FilePartReader;

import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilePartReaderTest {
    @Test
    public void testSetupThrowsExceptionFromLineZero() {
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("/home/adam_landfrasz/codecool/java/test.txt", 0, 1));
    }

    @Test
    public void testSetupThrowsExceptionToLineSmaller() {
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("/home/adam_landfrasz/codecool/java/test.txt", 2, 1));
    }

    @Test
    public void testReaderSuccess() throws IOException {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("test_file.txt", 1, 1);
        String expectedOutput =
                "apples\n" +
                "are really\n" +
                "cool\n" +
                "so lets get this\n" +
                "party started baby\n" +
                "yeah lets\n" +
                "do\n" +
                "this";
        assertEquals(expectedOutput, filePartReader.read());
    }

    @Test
    public void testReaderThrowsIOException() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("testFile.txt", 1, 1);
        assertThrows(IOException.class, filePartReader::read);
    }

    @Test
    public void testReadLinesFirstLineOnly() throws IOException {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("test_file.txt", 1, 1);
        String expectedOutput = "apples\n";
        assertEquals(expectedOutput, filePartReader.readLines());
    }

    @Test
    public void testReadLinesRandomLines() throws IOException {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("test_file.txt", 3, 5);
        String expectedOutput = "cool\nso lets get this\nparty started baby\n";
        assertEquals(expectedOutput, filePartReader.readLines());
    }
}
