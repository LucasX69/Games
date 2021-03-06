package Games.Hangman.Resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class WordWriter {

    private BufferedWriter writer;
    private ArrayList<String> words;

    public WordWriter(String filePath) throws IOException {
        WordsReader reader = new WordsReader(filePath);
        words = reader.read();
        File file = Paths.get(filePath).toFile();
        writer = new BufferedWriter(new FileWriter(file));
    }

    private void reWrite() throws IOException {
        for (String str : words) {
            writer.write(str + "\n");
        }
    }

    public void write(Scanner scanner) throws IOException {
        reWrite();
        String word;
        System.out.println("You can only add words that confirm to [a-zA-Z]+");
        System.out.println("This means, only letters, no spaces, nothing else");
        System.out.println("End the sequence by typing #quit.");
        while (!(word = scanner.next().toLowerCase()).equals("#quit")) {
            if (!words.contains(word)) {
                words.add(word);
                writer.write(word + "\n");
            } else {
                System.out.println("This word is already known.");
            }
        }
        writer.close();
    }
}
