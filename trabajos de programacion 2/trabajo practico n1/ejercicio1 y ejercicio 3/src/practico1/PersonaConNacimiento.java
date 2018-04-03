package practico1;

import java.util.Scanner;

public class PersonaConNacimiento extends Persona{
	private String PersonaConNacimiento;
	public PersonaConNacimiento(String nombre, String apellido, long dni, String  PersonaConNacimiento) {
		super(nombre, apellido, dni);
		// TODO Auto-generated constructor stub
		PersonaConNacimiento ="";
	}
	public String getPersonaConNacimiento() {
		return PersonaConNacimiento;
	}
	public void setPersonaConNacimiento(String personaConNacimiento) {
		PersonaConNacimiento = personaConNacimiento;
	}
	@Override
	public String toString() {
		return "PersonaConNacimiento [PersonaConNacimiento=" + PersonaConNacimiento + ", getPersonaConNacimiento()="
				+ getPersonaConNacimiento() + ", toString()=" + super.toString() + ", getNombre()=" + getNombre()
				+ ", getApellido()=" + getApellido() + ", getDni()=" + getDni() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	public  void  input (String[] args) {     
        Scanner sc = new Scanner(System.in);  //crear un objeto Scanner
      
        System.out.print("Introduzca su fecha: ");  
        PersonaConNacimiento= sc.nextLine();  //leer un String
        System.out.print("Introduzca su nombre: ");       
        nombre = sc.nextLine();  //leer un String
        System.out.print("Introduzca su apellido: ");       
        apellido = sc.nextLine();  //leer un String
         System.out.print("Introduzca su dni: ");       
        dni = sc.nextInt();  //leer un String
	}
	public  void  show (String[] args) {    
		 System.out.println( PersonaConNacimiento + "!!!");
		 System.out.println( nombre + "!!!");
		 System.out.println( apellido + "!!!");
		  System.out.println( dni+ "!!!");
	}
	
}