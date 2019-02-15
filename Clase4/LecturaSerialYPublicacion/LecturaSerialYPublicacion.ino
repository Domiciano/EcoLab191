#include <SPI.h>
#include <Ethernet.h>

byte mac[] = {0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED};
IPAddress ip(192, 168, 0, 100);
EthernetServer server(80);
String valorA="0", valorB="0", valorC="0";

void setup() {
  Serial.begin(9600);  
  Ethernet.begin(mac, ip);
  server.begin();
}

void loop() {
  EthernetClient client = server.available();
  if (client) {
    Serial.println("new client");
    // an http request ends with a blank line
    bool currentLineIsBlank = true;
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        Serial.write(c);
        // if you've gotten to the end of the line (received a newline
        // character) and the line is blank, the http request has ended,
        // so you can send a reply
        if (c == '\n' && currentLineIsBlank) {
          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connection: close");  // the connection will be closed after completion of the response
          //client.println("Refresh: 5");  // refresh the page automatically every 5 sec
          client.println();

          //AQUI ES DONDE IMPRIMO LO QUE NECESITO PUBLICAR
          client.print(valorA);
          client.print("::");
          client.print(valorB);
          client.print("::");
          client.print(valorC);
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
    // give the web browser time to receive the data
    delay(1);
    // close the connection:
    client.stop();
    Serial.println("client disconnected");
  }
}

  void serialEvent(){
    if( Serial.available()>0 ){
      String mensaje = Serial.readStringUntil('\n');
      String canal = mensaje.substring(0,1);
      if(canal == "A"){
        valorA = mensaje.substring(1);
      }
      if(canal == "B"){
        valorB = mensaje.substring(1);
      }
      if(canal == "C"){
        valorC = mensaje.substring(1);
      }
    }
  }
  
