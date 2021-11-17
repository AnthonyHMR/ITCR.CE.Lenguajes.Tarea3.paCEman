package com.company;

public class Main
{
    public static void main(String[] args) throws Exception {
        Client client = new Client(
                "127.0.0.1",
                8888);
        client.start();

    }
}

