package cl.uach.info090.tarea;

import java.util.Date;

/**
 * Subclase de RentalItemButton 
 * sobreescribe el método returnMe
 * @author nicol
 *
 */

public class BikeButton extends RentalItemButton {
	
	/**
	 * Constructor de la clase
	 * 
	 * @param serie
	 * @param desc
	 * @param baseFee
	 * @param hourFee
	 */

	public BikeButton(String serie, String desc, double baseFee, double hourFee) {
		super(serie, desc, baseFee, hourFee);
	}
	
	/**
	 * este método descuenta el valor base de arriendo (baseFee)
	 *  si el arriendo duró más de 3 horas (180 minutos).
	 */
	
	@Override
	public Receipt returnMe(Date end) {
		Receipt b = super.returnMe(end);
		int tiempo = b.getTimeMin();
		if(tiempo >180) {
			b.setTotal(b.getTotal() - b.getBaseFee());
		}
		
		return (b);
	}
	
}
