package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Config {

    private String host;
    private int port;

    public Config(String path) {
        load(path);
    }

    private void load(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts[0].equals("host")) {
                    host = parts[1];
                }

                if (parts[0].equals("port")) {
                    port = Integer.parseInt(parts[1]);
                }

            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения настроек");
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
