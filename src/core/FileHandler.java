/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raiven
 */
public class FileHandler {

    final static Charset ENCODING = StandardCharsets.UTF_8;

    public ArrayList readLargerTextFileAlternate(String aFileName) throws IOException {
        try {
            ArrayList tempArray = new ArrayList();
            Path path = Paths.get(getClass().getResource(aFileName).toURI());
            try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    //process each line in some way
                    tempArray.add(line);
                }
            }
            return tempArray;
        }   catch (URISyntaxException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
