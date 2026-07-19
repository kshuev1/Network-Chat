package server;

import common.Logger;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String name;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer = new PrintWriter(socket.getOutputStream(), true);

            // Первое сообщение - имя пользователя
            if (name == null || name.isBlank()) {
                name = "Гость";
            }
            name = reader.readLine();

            Logger.log(name + " подключился");

            Server.broadcast("*** " + name + " вошел в чат ***");

            String message;

            while ((message = reader.readLine()) != null) {

                if (message.equals("/exit")) {
                    break;
                }

                Server.broadcast(name + ": " + message);

            }

        } catch (IOException e) {

            System.out.println("Ошибка соединения с клиентом");

        } finally {

            disconnect();

        }

    }

    public void sendMessage(String message) {

        if (writer != null) {
            writer.println(message);
        }

    }

    private void disconnect() {

        try {

            Server.removeClient(this);

            if (name != null) {
                Server.broadcast("*** " + name + " покинул чат ***");
                Logger.log(name + " отключился");
            }

            if (reader != null) {
                reader.close();
            }

            if (writer != null) {
                writer.close();
            }

            socket.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
