
package com.nio;

import java.io.File;
import java.nio.file.Path;

public class PathTest
{
    
    public static void main(String[] args) {
    
        Path path = Path.of("/sdfsdfsdf");
        System.out.println(path);
    
        System.out.println(path.getFileName());
        
        File file = new File("/asfdsafdsa");
    
        System.out.println(file);
        
        Path path2 = Path.of("/tmp/file_symlink");
        
        File file2 = new File("/tmp/file_symlink");
    
        System.out.println(file2);
        
    }
    
}
