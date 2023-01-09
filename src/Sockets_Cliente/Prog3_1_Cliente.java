package Sockets_Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Prog3_1_Cliente {
	
	
	//declaramos las variables puerto y host
	private static final String HOST = "localhost";
	private static final int PUERTO = 2000;
	
	
	
	//constructor
	public Prog3_1_Cliente() {
		
		try {
			
			// 1 Conectarse con el servidor
			Socket socketCliente = new Socket(HOST,PUERTO);
			
			//flujos de entrada y salida
			DataInputStream flujoEntrada = new DataInputStream (socketCliente.getInputStream());
			DataOutputStream flujoSalida = new DataOutputStream (socketCliente.getOutputStream());
		
			//coger IP y envio de informacion
			InetAddress ad = socketCliente.getInetAddress();
			String IP = ad.toString();
			flujoSalida.writeUTF(IP);
			
			//recepcion de informacion
			String mensaje = flujoEntrada.readUTF();
			System.out.println("El mensaje del servidor: " + mensaje);
			
			//se cierra el socket
			socketCliente.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	
	
	//clase main
	public static void main(String[] args) {
		Prog3_1_Cliente cliente = new Prog3_1_Cliente();

	}

}
