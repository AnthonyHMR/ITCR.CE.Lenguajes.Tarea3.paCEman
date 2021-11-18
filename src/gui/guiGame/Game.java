package gui.guiGame;

import com.company.Client;
import java.io.IOException;

/**
 * Esta clase se encarga de crear un hilo para llamar el cliente
 * @author Anthony Montero
 */
public class Game extends Thread{
	Client client;
	public void run() {
		try {
			client = new Client(
					"127.0.0.1",
					8888);
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendMessage(String message){
		try {
			client.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getMessage(){
		String message = client.getRecibido();
		return message;
	}
}