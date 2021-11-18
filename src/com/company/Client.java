package com.company;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Client
{
    private final Socket socket;
    private DataInputStream inputt;
    private Scanner scanner;
    private boolean flag = true;
    private DataOutputStream out;
    private byte[] buffer = new byte[512];
    public Client(String serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
        out = new DataOutputStream(this.socket.getOutputStream());
        inputt = new DataInputStream(this.socket.getInputStream());
    }
    public String recibido;
    public void start() throws IOException {
        while (flag) {

            System.out.println("Recibiendo: ");
            recibido = reader();
            processMessage(recibido); //metodo para saber que hacer con la info recibida
            //borrando buffer
            buffer = new byte[512];

        }
        socket.close();
        inputt.close();
        out.close();
    }

    /**
     * Retorna el ultimo mensaje recibido desde el servidor
     * @return
     */
    public String getRecibido(){
        return recibido;
    }

    /**
     * La funcion lee los mensajes y envía una respuesta al servidor
     * @param message es el mesaje enviado por el servidor
     * @throws IOException
     */
    private void processMessage(String message) throws IOException {
        String keyword = "Login";
        String keyword2 = "Puntaje";
        System.out.println(message);
        if (message.equals(keyword)){
            System.out.println("Entiendo datos del server");
            sendMessage("Login");
        }
        else if(message.equals(keyword2)){
            System.out.println("Asignar puntaje");
            sendMessage("waiting...");
        }
        //else if (message.equals("Adios")){
        //    flag = false;
        //}

    }

    /**
     * Envia un mensaje al servidor
     * @param input mensaje a enviar
     * @throws IOException
     */
    public void sendMessage(String input) throws IOException {

        System.out.println("Enviando: ");
        System.out.println(input);
        out.write(writer(input));
        out.flush();

    }

    private byte[] writer(String message){
        byte [] bytes;
        bytes = message.getBytes();
        return bytes;
    }
    private String reader() throws IOException {
        String response = "";
        inputt.read(buffer);
        for(byte b:buffer){
            char c = (char)b;
            if(c != '\0'){
                if (c != '\n'){
                    response += c;
                }
            }
            else break;
        }

        return response;
    }


}

