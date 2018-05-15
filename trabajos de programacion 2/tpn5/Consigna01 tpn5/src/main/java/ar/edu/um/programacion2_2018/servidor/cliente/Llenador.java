package ar.edu.um.programacion2_2018.servidor.cliente;

public class Llenador extends Thread {
	private ColaCaja cola;
	
	public Llenador() {
		
	}

	public Llenador(ColaCaja cola) {
		super();
		this.cola = cola;
	}
	
	public ColaCaja getCola() {
		return cola;
	}

	public void setCola(ColaCaja cola) {
		this.cola = cola;
	}

	@Override
	public void run() {
		while (true) {
			Producto prod1 = new Producto("Manteca",40);
			Producto prod2 = new Producto("Sopa",20);
			Cliente cl = new Cliente(12,"Pepe");
			cl.getProductos().add(prod1);
			cl.getProductos().add(prod2);

			this.cola.getClientes().add(cl);
		}
	}
}
