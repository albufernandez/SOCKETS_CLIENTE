package Sockets_Cliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//programa que crea cliente que se conecta con un servidor y recoge el numero de cliente que es
public class Prog3_3_Cliente {

	//variables
	private static int PUERTO_ENVIAR = 2001;
	private static int PUERTO_RECIBIR = 2000;
	private static final String HOST = "localhost";
	
	//constructor
	public Prog3_3_Cliente() {
		
		try {
			
			//manejadores de envio y recepcion de info
			DatagramSocket enviar = new DatagramSocket();
			DatagramSocket recibir = new DatagramSocket(PUERTO_RECIBIR);
			
			//creamos el paquete para recibir la info
			byte[] mensaje_recibir = new byte[1000];
			DatagramPacket paquete_recibir = new DatagramPacket (mensaje_recibir, mensaje_recibir.length);
			
			//creamos el paquete para enviar la info
			//enviamos la IP
			String textoEnviar = InetAddress.getByName(HOST).toString();
			byte[] mensaje_enviar = textoEnviar.getBytes();
			DatagramPacket paquete_enviar = new DatagramPacket(mensaje_enviar, mensaje_enviar.length, InetAddress.getByName(HOST), PUERTO_ENVIAR);
			enviar.send(paquete_enviar);
			
			//recibirmos la info (IP + num cliente)
			recibir.receive(paquete_recibir);
			String datos = new String(paquete_recibir.getData(),0,paquete_recibir.getLength());
			System.out.println("Mensaje recibido: " + datos);
			
			//cerramos el manejador
			recibir.close();
			enviar.close();
			
		} catch (Exception e) {		
			System.out.println(e.getMessage());
		}	
		
	}
	
	public static void main(String[] args) {		
		Prog3_3_Cliente cliente = new Prog3_3_Cliente();
	}

}
