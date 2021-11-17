package com.company;// A Java program for a Client
//source: https://www.geeksforgeeks.org/socket-programming-in-java/
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Client
{
    private final Socket socket;
    private DataInputStream inputt;
    private Scanner scanner;
    private byte[] buffer = new byte[512];
    public Client(String serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }
    protected void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            sendMessage(input, out);
            System.out.println("Recibiendo");
            System.out.println(reader());
            buffer = new byte[512];

        }
    }

    private void sendMessage(String input, PrintWriter out) throws IOException {
        inputt = new DataInputStream(this.socket.getInputStream());
        System.out.println("Enviando: ");
        System.out.println(input);
        out.println(input);
        out.flush();
    }

    private String reader() throws IOException {
        String response = "";
        inputt.read(buffer);
        for(byte b:buffer){
            char c = (char)b;
            response += c;
        }
        return response;
    }

}

