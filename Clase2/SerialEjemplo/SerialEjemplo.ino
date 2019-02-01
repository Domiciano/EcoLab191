#include <SPI.h>
#include <Ethernet.h>
byte mac[] = {0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED};
IPAddress ip(192, 168, 0, 2);
EthernetServer server(80);

String mensaje = "NULL";

void setup() {
  Serial.begin(9600); 
  Ethernet.begin(mac, ip);
  server.begin();
}

void loop() {
  publishInfo();
}

//Recibir mensajes
void serialEvent() {
  /*
  if(Serial.available()>0){
    mensaje = Serial.readStringUntil('\n');  
  }
  */
}

void publishInfo(){
  EthernetClient client = server.available();
  if (client) {
    bool currentLineIsBlank = true;
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        if (c == '\n' && currentLineIsBlank) {
          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connection: close");  // the connection will be closed after completion of the response
          client.println("Refresh: 5");  // refresh the page automatically every 5 sec
          client.println();
          client.println("<!DOCTYPE HTML>");
          client.println("<html>");
          
          String sensorReading = mensaje;
          client.print("analog input ");
          client.print(" is ");
          client.print(sensorReading);
          client.println("<br />");
          
          client.println("</html>");
          break;
        }
        if (c == '\n') {
          // you're starting a new line
          currentLineIsBlank = true;
        } else if (c != '\r') {
          // you've gotten a character on the current line
          currentLineIsBlank = false;
        }
      }
    }
    delay(20);
    client.stop();
  }
}
