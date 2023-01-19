package Sockets_Cliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Prog3_4_Cliente {
	
	//variables
	private static int PUERTO_ENVIAR = 2001;
	private static int PUERTO_RECIBIR = 2000;
	private static final String HOST = "localhost";
	private boolean encontrado = false;
	
	//constructor 
	public Prog3_4_Cliente() {
		Scanner teclado = new Scanner(System.in);
		
		try {
			//manejadores de entrada y salida
			DatagramSocket enviar = new DatagramSocket();
			DatagramSocket recibir = new DatagramSocket(PUERTO_RECIBIR);
			
			//creamos el paquete para recibir la info
			byte[] mensaje_recibir = new byte[1000];
			DatagramPacket paquete_recibir = new DatagramPacket (mensaje_recibir, mensaje_recibir.length);
			
			//creamos el paquete para enviar la info
			//enviamos nuestro numero
			System.out.println("Dime un numero");
			String numero= teclado.nextLine();
			byte[] mensaje_enviar = numero.getBytes();
			DatagramPacket paquete_enviar = new DatagramPacket(mensaje_enviar, mensaje_enviar.length, InetAddress.getByName(HOST), PUERTO_ENVIAR);
			enviar.send(paquete_enviar);
			
			while (encontrado != true) {
			
			//recibirmos la info (si es mayor o menos acertado)
			recibir.receive(paquete_recibir);
			String datos = new String(paquete_recibir.getData(),0,paquete_recibir.getLength());
			System.out.println("El numero que buscamos es " + datos + " que el que se ha enviado");
			
			//Comprobamos con los datos recibimos si es el que buscamos y si no pedimos otro
			if (datos.equals("igual")) encontrado = true;
			
			else {
				System.out.println("Dime otro numero para seguir probando");
				numero = teclado.nextLine();
				//enviamos el nuevo numero
				mensaje_enviar = numero.getBytes();
				paquete_enviar = new DatagramPacket(mensaje_enviar, mensaje_enviar.length, InetAddress.getByName(HOST), PUERTO_ENVIAR);
				enviar.send(paquete_enviar);
			}
	
			}
			
			//se avisa de que finaliza el programa
			System.out.println("Enhorabuena! Al encontrar el numero se finaliza el programa");
			//cerramos el manejador
			recibir.close();
			enviar.close();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	

	public static void main(String[] args) {
		Prog3_4_Cliente cliente = new Prog3_4_Cliente();

	}

}
