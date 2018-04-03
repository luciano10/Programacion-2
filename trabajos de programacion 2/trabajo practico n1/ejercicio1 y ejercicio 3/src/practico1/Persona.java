package practico1;

import java.util.Scanner;

public class Persona {
	 protected static String nombre;

	protected static int dni;
	private Scanner scanner;
	protected static String apellido;
	public Persona(String nombre, String apellido, long dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni =  (int) dni;
		
	}
	




	@Override
	public String toString() {
		return "Persona [scanner=" + scanner + ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido()
				+ ", getDni()=" + getDni() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni =  (int) dni;
	}
	
	
	public  void  input (String[] args) {     
        Scanner sc = new Scanner(System.in);  //crear un objeto Scanner
      
        System.out.print("Introduzca su nombre: ");       
        nombre = sc.nextLine();  //leer un String
        System.out.print("Introduzca su apellido: ");       
        apellido = sc.nextLine();  //leer un String
         System.out.print("Introduzca su dni: ");       
        dni = sc.nextInt();  //leer un String
       
      
     
        
  }
	
	public  void  show (String[] args) {    
		 System.out.println( nombre + "!!!");
		 System.out.println( apellido + "!!!");
		  System.out.println( dni+ "!!!");
		
	}
	
	
	}


