package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    List<String> readFile(String filePath) {

        List<String> matchTimeInputList = new ArrayList<>();

        try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
            matchTimeInputList = fileStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchTimeInputList;
    }

}
