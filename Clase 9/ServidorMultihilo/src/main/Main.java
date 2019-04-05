package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet{

	ArrayList<Socket> sockets;
	ArrayList<Receptor> receptores;
	
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	@Override
	public void settings() {
		size(500,500);
	}
	
	@Override
	public void setup() {
		sockets = new ArrayList<>();
		receptores = new ArrayList<>();
		new Thread(() -> {
		
			try {
				ServerSocket server = new ServerSocket(5000);
				while(true) {
					System.out.println("Esperando cliente...");
					Socket socket = server.accept();
					sockets.add(socket);
					System.out.println("Clientes: "+sockets.size());
					
					Receptor receptor = new Receptor( socket.getInputStream() );
					receptor.start();
					receptores.add(receptor);
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}) .start();
	
		
	}
	
	@Override
	public void draw() {
		background(255);
	}
	
}
