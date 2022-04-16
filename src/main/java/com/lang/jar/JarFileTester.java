
package com.lang.jar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.*;

public class JarFileTester {
    static int count = 0;
    static ArrayList<String> jarsWithNoPomProperties = new ArrayList<>();
    public static void main (String args[]) throws IOException
    {
        for(File file: new File("/Users/pushparaj/Desktop/code/camel-activemq-kafka"
        + "-connector/camel-activemq-kafka-connector/").listFiles())
        {
            if (!file.getName().endsWith(".jar"))
                continue;
            JarFile jarFile = new JarFile(file);
            Enumeration enu = jarFile.entries();
            boolean hasPomProperties = false;
            while (enu.hasMoreElements())
                if(process(jarFile, enu.nextElement()))
                    hasPomProperties = true;
            if(!hasPomProperties)
                jarsWithNoPomProperties.add(file.getName());
        }
        System.out.println(
            "Total count=" + count +"\n"
        );
        System.out.println(jarsWithNoPomProperties);
        System.out.println(jarsWithNoPomProperties.size());
    }
    
    private static boolean process(JarFile jarFile, Object obj) throws IOException
    {
        JarEntry entry = (JarEntry)obj;
        String name = entry.getName();
        if(name.endsWith("pom.properties")) {
            count++;
            InputStream is = jarFile.getInputStream(entry);
            Properties p = new Properties();
            p.load(is);
            String g = p.getProperty("groupId");
            String a = p.getProperty("artifactId");
            String v = p.getProperty("version");
/*            System.out.println(
                "<dependency>\n"
                    + "<gropuId>" + g + "</groupId>\n"
                    + "<artifactId>" + a + "</artifactId>\n"
                    + "<version>" +  v + "</version>\n"
                + "</dependency>\n");*/
            return true;
        }
        return false;
    }
}
