package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
		PApplet.main("main.Main");

	}

	DataOutputStream salida;
	
	@Override
	public void settings() {
		size(500,500);
	}
	
	@Override
	public void setup() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ServerSocket server = new ServerSocket(5000);
					System.out.println("Esperando un cliente");
					Socket socket = server.accept();
					System.out.println("Cliente conectado!");
					salida = new DataOutputStream(socket.getOutputStream());
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					while(true) {
						String recibido =  dis.readUTF();
						System.out.println(recibido);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	
	@Override
	public void draw() {
		
	}
	
	
	@Override
	public void mousePressed() {
		try {
			salida.writeUTF("Hola desde Eclipse");
			salida.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
