import java.util.Calendar;

public class Main3 {

	public static void main(String[] args) {
		/**
		 * Realizando Operaciones
		 */
		 
		System.out.println("\n[ Operando con Fechas ]\n");
		 
		//Instancia a calendar
		Calendar fecha = Calendar.getInstance();
		 
		//Sumando dias
		System.out.println(String.format("Fecha antes de la suma de dias : %1$tY-%1$tm-%1$td",
		      fecha.getTime()));
		fecha.add(Calendar.DATE, 35);
		System.out.println(String.format("He sumado 35 dias y ahora tengo : %1$tY-%1$tm-%1$td",
		      fecha.getTime()));
		 
		//Restando meses
		System.out.println(String.format("Fecha antes de la resta de anios : %1$tY-%1$tm-%1$td",
		      fecha.getTime()));
		fecha.add(Calendar.YEAR, -5);
		System.out.println(String.format("He restado 5 anios y ahora tengo : %1$tY-%1$tm-%1$td",
		      fecha.getTime()));
		 
		//Sumando horas
		System.out.println(String.format("Fecha antes de la suuma de horas : %1$tY-%1$tm-%1$td",
		      fecha.getTime()));
		fecha.add(Calendar.HOUR, 1200);
		System.out.println(String.format("He sumado 1200 horas y ahora tengo : %1$tY-%1$tm-%1$td",
		      fecha.getTime()));

	}

}
