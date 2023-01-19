package Sockets_Cliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ej_Prueba_UDP_Cliente {

	//declaramos las variables
	private static final String HOST = "localhost";
	private static final int PUERTO = 1500;
	
	//constructor
	//el cliente va a enviar tres mensajes al servidor
	public Ej_Prueba_UDP_Cliente() {
		
		try {
			//Creamos el manejador que va a enviar info
			DatagramSocket enviar = new DatagramSocket();
			
			//creamos el paquete para enviar la info
			String textoEnviar = "Mensaje del cliente";
			byte[] mensaje = new byte[1000];
			mensaje = textoEnviar.getBytes();
			DatagramPacket paquete = new DatagramPacket(mensaje, mensaje.length, InetAddress.getByName(HOST), PUERTO);
			
			//enviamos el paquete
			enviar.send(paquete);
			
			//cerramos el manejador
			enviar.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		Ej_Prueba_UDP_Cliente cliente = new Ej_Prueba_UDP_Cliente();
	}

}
