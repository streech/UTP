package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// package-private, class is not allowed in zad1
class Futil {

    public static void processDir(String dirName, String resultFileName) {

        File resultFile = new File(resultFileName);

        try (BufferedWriter out = Files.newBufferedWriter(
                resultFile.toPath(), StandardCharsets.UTF_8)
        ) {
            Files.walk(Paths.get(dirName))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            Files.lines(path, Charset.forName("Cp1250"))
                                    .forEach(line -> {
                                        try {
                                            out.write(line);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}