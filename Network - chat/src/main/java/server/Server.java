package server;

import common.Config;
import common.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

    private static final List<ClientHandler> clients =
            Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {

        Config config = new Config("settings.txt");

        try (ServerSocket serverSocket =
                     new ServerSocket(config.getPort())) {

            Logger.log("Сервер запущен");

            System.out.println("Сервер запущен");

            while (true) {

                Socket socket = serverSocket.accept();

                ClientHandler handler =
                        new ClientHandler(socket);

                clients.add(handler);

                new Thread(handler).start();

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public static void broadcast(String message) {

        synchronized (clients) {

            for (ClientHandler client : clients) {

                client.sendMessage(message);

            }

        }

        Logger.log(message);

    }

    public static void removeClient(ClientHandler client) {

        clients.remove(client);

    }

}