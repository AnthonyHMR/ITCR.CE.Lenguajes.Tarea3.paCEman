package com.company;
import gui.guiGame.Game;
import gui.guiGame.GameFrame;
//import org.json.simple.parser.*;
//import org.json.simple.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws Exception {
        GameFrame myFrame = new GameFrame();

        /*
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/resources/ejemplo.json"));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("JSON LEIDO: " + jsonObject);
            JSONArray jsonArray = (JSONArray) jsonObject.get("partida");
            System.out.println("");
            for(int i=0; i<jsonArray.size(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                System.out.println("DATOS DEL USUARIO: " + i);
                System.out.println("ghosts: " + jsonObject1.get("ghosts"));
                System.out.println("fruits: " + jsonObject1.get("fruits"));

                System.out.println("");
            }
        }catch (FileNotFoundException e) { }
        catch (IOException e) { }
        */
    }
}

