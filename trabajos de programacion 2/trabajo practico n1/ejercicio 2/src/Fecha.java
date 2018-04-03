import java.util.Scanner;

public class Fecha {
private String dia;
private String mes;
private String año;


public Fecha(String dia, String mes, String año) {
	super();
	this.dia = dia;
	this.mes = mes;
	this.año = año;
}
@Override
public String toString() {
	return "Fecha [dia=" + dia + ", mes=" + mes + ", año=" + año + "]";
}
public String getDia() {
	return dia;
}
public void setDia(String dia) {
	this.dia = dia;
}
public String getMes() {
	return mes;
}
public void setMes(String mes) {
	this.mes = mes;
}
public String getAño() {
	return año;
}
public void setAño(String año) {
	this.año = año;
}
public  void  input (String[] args) {     
    Scanner sc = new Scanner(System.in);  //crear un objeto Scanner
  
    System.out.print("Introduzca su dia: ");       
    dia = sc.nextLine();  //leer un String
    System.out.print("Introduzca su mes: ");       
    mes = sc.nextLine();  //leer un String
     System.out.print("Introduzca su año: ");       
    año  = sc.nextLine();  //leer un String
   

	

}
public  void  show (String[] args) {    
	 System.out.println( dia + "!!!");
	 System.out.println( mes  + "!!!");
	  System.out.println( año + "!!!");
}
public static int size() {
	// TODO Auto-generated method stub
	return 0;
}
public static String[] toArray(String[] fechaa1) {
	// TODO Auto-generated method stub
	return null;
}
public int compareTo(Fecha fech2) {
	// TODO Auto-generated method stub
	return 0;
}
}