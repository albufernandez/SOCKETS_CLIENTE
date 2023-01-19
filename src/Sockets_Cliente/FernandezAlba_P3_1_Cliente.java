package Sockets_Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//programa que envia un nombre de fichero y si el servidor tiene un fichero con este mismo nombre
//crea otro fichero y copia el contenido del fichero servidor en fichero cliente
public class FernandezAlba_P3_1_Cliente {
	
	//variables 
	private static final String HOST = "localhost"; 
	private static final int PUERTO=2000;
	private String fichero;
	private String existe;

	//constructor
	public FernandezAlba_P3_1_Cliente() {
		
		//nos cnoectamos con el servidor
		try {
			Socket socketCliente = new Socket(HOST,PUERTO);
			
			//envio y recepcion de datos
			DataInputStream leer = new DataInputStream(socketCliente.getInputStream());
			DataOutputStream escribir = new DataOutputStream (socketCliente.getOutputStream());
		
			//pedimos el nombre del fichero
			System.out.println("Dime el nombre del fichero a comprobar");
			Scanner teclado = new Scanner (System.in);
			fichero = teclado.nextLine() + ".txt";
			
			//mandamos el nombre del fichero para que el servidor compruebe
			escribir.writeUTF(fichero);
			
			//recibimos si el fichero existe o no
			existe = leer.readUTF();
			
			//si no existe el fichero avisa
			if (existe.equalsIgnoreCase("no")) {
			System.out.println("Lo siento pero el fichero no existe");	
			}
			
			//si el fichero si existe avisa al usuario y comienza a copiarlo
			else {
			System.out.println("El fichero si existe, vamos a guardarlo");	
				
			//buffered de escritura, crea el fichero
			BufferedWriter escribirFi = new BufferedWriter ( new FileWriter(fichero));
			String linea = leer.readUTF();
			
			//mientras que la linea que nos llegue no sea el aviso de fin de fichero
			while (!linea.equalsIgnoreCase("fin_archivo_fichero")) {
				
				//escribimos esa linea y el salto de linea
				escribirFi.write(linea);
				escribirFi.newLine();
				
				//leemos la sieguiente linea
				linea = leer.readUTF();
			}
			//cerramos el buffered de escritura
			escribirFi.close();
			
			//avisamos de que ya se ha copiado el archivo y lo imprimimos
			System.out.println("Fichero recibido y copiado correctamente, el contenido es:");
			
			//buffered de lectura de nuestro fichero
			BufferedReader leerFi = new BufferedReader (new FileReader(fichero));
			String lineaFi = leerFi.readLine();
			
			while(lineaFi!=null) {
				System.out.println(lineaFi);
				lineaFi=leerFi.readLine();
			}
			
			//cerramos buffered de lectura y socket
			leerFi.close();
			socketCliente.close();
				
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//main que ejecuta el programa
	public static void main(String[] args) {
		FernandezAlba_P3_1_Cliente cliente = new FernandezAlba_P3_1_Cliente();

	}

}
