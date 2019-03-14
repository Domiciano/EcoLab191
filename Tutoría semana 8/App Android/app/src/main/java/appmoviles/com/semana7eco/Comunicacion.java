package appmoviles.com.semana7eco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable {

    private Socket s;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private boolean conectado;

    private static Comunicacion ref;

    private Comunicacion(){
        conectado =  false;
        System.out.println("CREANDO CONEXION");
    }

    public static Comunicacion getRef() { // Comunicacion.getRef()
        if(ref == null) {
            ref = new Comunicacion();
            Thread t = new Thread(ref);
            t.start();
        }
        return ref;
    }

    @Override
    public void run() {
        while(true){
            try {
            if(!conectado){
                s = new Socket(InetAddress.getByName("10.0.2.2"),5000);
                salida = new DataOutputStream(s.getOutputStream());
                entrada = new DataInputStream(s.getInputStream());
                conectado = true;
            }else{
               recibir();
            }
            Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void recibir() throws IOException {
        String mensaje = entrada.readUTF();
        setChanged();
        notifyObservers(mensaje);
        clearChanged();
    }

    public void enviar(final String msg) {
        if (s != null & s.isConnected() && salida != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        salida.writeUTF(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
