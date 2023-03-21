package cl.uach.info090.tarea;

import java.util.Date;

/**
 * Subclase de RentalItemButton 
 * @author nicol
 *
 */

public class SegwayButton extends RentalItemButton{
	
	/**
	 * Constructor de la clase
	 * 
	 * @param serie
	 * @param desc
	 * @param baseFee
	 * @param hourFee
	 */

	public SegwayButton(String serie, String desc, double baseFee, double hourFee) {
		super(serie, desc, baseFee, hourFee);
	}
	

}
