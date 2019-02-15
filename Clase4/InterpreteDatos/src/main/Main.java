package main;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import processing.core.PApplet;

public class Main extends PApplet{
	
	int alturaA = 400;
	int alturaB = 400;
	int alturaC = 400;

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	@Override
	public void settings() {
		size(500,500);
	}
	
	@Override
	public void setup() {
		
	}
	
	@Override
	public void draw() {
		background(255);
		fill(255,0,0);
		rect(50, 500-alturaA, 100, alturaA);
		fill(255,255,0);
		rect(200, 500-alturaB, 100, alturaB);
		fill(255,0,255);
		rect(350, 500-alturaC, 100, alturaC);
		
		actualizarAlturas();
	}
	
	public void actualizarAlturas() {
		if(frameCount % 60 == 0) {
			try {
				URL url = new URL("http://169.254.9.100");
				HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
				InputStream is = conexion.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				int c = 0;
				String mensaje = "";
				while( (c = bis.read()) != -1 ) {
					char alfa = (char) c;
					mensaje += alfa;
					
				}
				bis.close();
				String[] data = mensaje.split("::");
				alturaA = Integer.parseInt(data[0])/2;
				alturaB = Integer.parseInt(data[1])/2;
				alturaC = Integer.parseInt(data[2])/2;
				
			}catch(IOException ex) {
				System.out.println(ex);
			}
		}
	}

}
