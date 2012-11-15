package jeopardy.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import jeopardy.domain.Board;

public class BoardReader {
    private BoardReader() {
        // I'm a utility class
    }

    private static boolean shouldSkip(String line) {
        return line == null || line.matches("^(\\s*|\\s*#+.*)$");
    }

    public static Board createBoard(Reader reader) throws IOException {
        BufferedReader buff = new BufferedReader(reader);

        Board b = new Board();
        try {
            String line = null;

            do {
                line = buff.readLine();
                if (shouldSkip(line)) {
                    continue;
                }
                String[] parts = line.split("~~~");
                b.add(parts[0].trim(), Integer.parseInt(parts[1].trim()), parts[2].trim(), parts[3]
                        .trim());
            } while (line != null);
        } finally {
            buff.close();
        }

        return b;
    }
}
