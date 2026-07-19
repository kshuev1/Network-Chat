package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "file.log";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {

        String time = LocalDateTime.now().format(FORMATTER);

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(LOG_FILE, true))) {

            writer.write("[" + time + "] " + message);

            writer.newLine();

        } catch (IOException e) {

            System.out.println("Ошибка записи лога");

        }

    }

}
