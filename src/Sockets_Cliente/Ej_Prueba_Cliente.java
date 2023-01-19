package Sockets_Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Ej_Prueba_Cliente {
	
	//declaramos las variables puerto y host
	private static final String HOST = "localhost";
	private static final int PUERTO = 2000;
	
	public Ej_Prueba_Cliente() {
		
		try {
			
			// 1 COnectarse con el servidor
			Socket socketCliente = new Socket(HOST,PUERTO);
			
			// 2 Envio + recepcion de datos
			// 2A Establecer flujos de salida y entrada
			DataOutputStream flujoSalida = new DataOutputStream (socketCliente.getOutputStream());
			DataInputStream flujoEntrada = new DataInputStream (socketCliente.getInputStream());
			
			// 2B Enviar informacion
			flujoSalida.writeUTF("Cliente ");
			
			// 2C Recibir informacion
			String mensaje = flujoEntrada.readUTF();
			System.out.println("Mensaje servidor: " + mensaje );
			
			// 3 Cerrar el socket
			socketCliente.close();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	

	public static void main(String[] args) {
		Ej_Prueba_Cliente cliente = new Ej_Prueba_Cliente();

	}

}
