package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.*;
import java.util.List;

// package-private, class is not allowed in zad2
class Futil {

    protected static void processDir(String dirName, String resultFileName) {

        File resultFile = new File(resultFileName);

        try (BufferedWriter out = Files.newBufferedWriter(
                resultFile.toPath(), StandardCharsets.UTF_8)
        ) {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {

                    List<String> allLines = Files.readAllLines(
                            file, Charset.forName("Cp1250"));

                    for (String line : allLines) {
                        out.write(line);
                    }

                    return java.nio.file.FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}