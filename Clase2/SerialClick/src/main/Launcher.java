package main;

import processing.core.PApplet;
import processing.serial.Serial;

public class Launcher extends PApplet{

	Serial puerto;
	int clicks = 0;
	int Y1 = 200;
	int Y2 = 300;
	int Y3 = 100;
	
	public static void main(String... args) {
		PApplet.main("main.Launcher");
	}
	
	@Override
	public void settings() {
		size(500,500);
	}
	
	@Override
	public void setup() {
		printArray(Serial.list());
		puerto = new Serial(this, Serial.list()[0],9600);
		puerto.bufferUntil('\n');
	}
	
	@Override
	public void draw() {
		background(255);
		rect(95, 0, 10, 500);
		rect(245, 0, 10, 500);
		rect(395, 0, 10, 500);
		fill(255,0,0);
		ellipse(100, 500-Y1, 50, 50);
		ellipse(250, 500-Y2, 50, 50);
		ellipse(400, 500-Y3, 50, 50);
		fill(255);
	}
	
	@Override
	public void mouseDragged() {
		if(dist(pmouseX, pmouseY, 125, 500-Y1) < 50) {
			Y1 = 500-mouseY;
			puerto.write("A"+2*Y1+"\n");
		}
		if(dist(pmouseX, pmouseY, 275, 500-Y2) < 50) {
			Y2 = 500-mouseY;
			puerto.write("B"+2*Y2+"\n");
		}
		if(dist(pmouseX, pmouseY, 425, 500-Y3) < 50) {
			Y3 = 500-mouseY;
			puerto.write("C"+2*Y3+"\n");
		}
	}
	
	
}
