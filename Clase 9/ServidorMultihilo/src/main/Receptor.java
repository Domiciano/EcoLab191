package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Receptor extends Thread{

	private InputStream is;
	
	public Receptor(InputStream is) {
		this.is = is;
	}
	
	@Override
	public void run() {
		DataInputStream dis = new DataInputStream(is);
		while(true) {
			try {
				String recibido = dis.readUTF();
				System.out.println(recibido);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
