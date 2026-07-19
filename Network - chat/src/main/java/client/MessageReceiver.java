package client;

import common.Logger;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageReceiver implements Runnable {

    private final BufferedReader reader;

    public MessageReceiver(BufferedReader reader) {

        this.reader = reader;

    }

    @Override
    public void run() {

        try {

            String message;

            while ((message = reader.readLine()) != null) {

                System.out.println(message);

                Logger.log("Получено: " + message);

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
