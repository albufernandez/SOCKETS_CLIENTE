package Sockets_Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Prog3_2_Cliente {
	
	//variables
	private static final String HOST = "localhost"; 
	private static final int PUERTO=2000;
	private int numero;
	private boolean encontrado = false;
	private String comprobacion;
	
	//Constructor
	public Prog3_2_Cliente() {
	
		try {
			
			//conectarse con el servidor
			Socket socketCliente = new Socket(HOST,PUERTO);
			
			//envio y recepcion de datos
			DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
			DataOutputStream flujoSalida = new DataOutputStream (socketCliente.getOutputStream());
			
			//pedimos el primer numero
			System.out.println("Dime un numero entre 1 y 100");
			Scanner teclado = new Scanner (System.in);
			numero = teclado.nextInt();
			
			//mandamos el numero
			flujoSalida.writeInt(numero);
			
			//se comunica con el server hasta que se encuentre el numero
			while (encontrado != true) {
				
				//cogemos lo que nos dice el servidor (si es el mismo m ayor o inferior)
				comprobacion = flujoEntrada.readUTF();
				
				if (comprobacion.equals("=")) {
					System.out.println("Enhorabuena! has encontrado el numero");
					encontrado = true;
				}
				
				if (comprobacion.equals("+")) {
					System.out.println("El numero que buscas es mayor que " + numero);
					System.out.println("Dime otro numero para seguir probando");
					numero = teclado.nextInt();
					flujoSalida.writeInt(numero);
				}
				if (comprobacion.equals("-")) {
					System.out.println("El numero que buscas es menor que " + numero);
					System.out.println("Dime otro numero para seguir probando");
					numero = teclado.nextInt();
					flujoSalida.writeInt(numero);
				}
				
			}
			
			//cerramos el socket 
			socketCliente.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	

	public static void main(String[] args) {
		Prog3_2_Cliente cliente = new Prog3_2_Cliente();

	}

}
