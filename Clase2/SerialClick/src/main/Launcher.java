package main;

import processing.core.PApplet;
import processing.serial.Serial;

public class Launcher extends PApplet{

	Serial puerto;
	int clicks = 0;
	
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
		
	}
	
	@Override
	public void mousePressed() {
		
	}
	
}
