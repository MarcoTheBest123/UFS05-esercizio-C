package org.example;


import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static String hostname = "127.0.0.1";
    static int port_number = 1234;
    static public Socket clientSocket = null;



    public static void main( String[] args ) {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(1234), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Database db = new Database();
        //server.createContext("/applications/myapp", new MyHandler());
        server.createContext("/", new ClientHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static private ServerSocket openToServer() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port_number);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return serverSocket;
    }
}
