package client;

import common.Config;
import common.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Config config = new Config("settings.txt");

        try {

            Socket socket =
                    new Socket(
                            config.getHost(),
                            config.getPort());

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter writer =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите имя: ");

            String name = scanner.nextLine();

            writer.println(name);

            Logger.log("Подключился как " + name);

            MessageReceiver receiver =
                    new MessageReceiver(reader);

            new Thread(receiver).start();

            while (true) {

                String message =
                        scanner.nextLine();

                if (message.equals("/exit")) {

                    writer.println(message);

                    break;

                }

                writer.println(message);

                Logger.log("Отправлено: " + message);

            }

            socket.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}