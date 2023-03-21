package cl.uach.info090.tarea;

import java.util.Date;

/**
 * Interfaz que inicializa los metodos para ser 
 * usados en la clase RentalItemButton y sus tres subclases
 * @author nicol
 *
 */

public interface Rentable {
	public boolean isRented();
	public void rentMe(String clientName, Date start);
	public Receipt returnMe(Date end);
}
