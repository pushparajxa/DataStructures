
package com.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    
    public static void main(String[] args) throws IOException {
    
        Path path = Paths.get("/tmp/file");
    
        List<String> list = Files.readAllLines(path);
        String read = String.join("\n", list);
        System.out.println(read);
    }
}
