package cl.uach.info090.tarea;

import java.awt.Color;
import java.util.Date;

import javax.swing.JButton;

/**
 * Esta clase es la que se encarga de establecer los metodos que se ejecutan
 * al momento de hacer o finalizar un arriendo.
 * 
 * @author nicol
 */

public class RentalItemButton extends JButton implements Rentable{
	private String serie;
	private String desc;
	private double baseFee;
	private double hourFee;
	private String clientName;
	private Date start;
	private Date end;
	public final Color COLOR_AVAILABLE = new Color(116,189,100);
	public final Color COLOR_RENTED = new Color(221,81,69);
	
	/**
	 * Constructor de la clase
	 * 
	 * @param serie
	 * @param desc
	 * @param baseFee
	 * @param hourFee
	 */
	
	public RentalItemButton(String serie, String desc, double baseFee, double hourFee) {
		super();
		this.serie = serie;
		this.desc = desc;
		this.baseFee = baseFee;
		this.hourFee = hourFee;
		this.clientName = null;
		this.start = null;
		this.end = null;
	}
	
	/**
	 * Devuelve true si clientName y start no son nulos (null)
	 */

	public boolean isRented() {
		if((clientName != null) && (start !=null)) return true;
		else return false;
	}
	
	/**
	 * establece los atributos clienteName y start con los parámetros homónimos
	 */
	public void rentMe(String clientName, Date start) {
		this.clientName = clientName;
		this.start = start;		
	}
	
	/**
	 *  establece el atributo fecha end con el parámetro homónimo, 
	 *  calcula la diferencia entre end y start, hace los cálculos 
	 *  de valor a cobrar de arriendo, genera la boleta (Receipt), 
	 *  establece atributos clientName, start y end en null (vuelve a estar disponible) 
	 *  y retorna la boleta generada
	 */
	
	public Receipt returnMe(Date end) {	
		this.end = end;
		long tInicial = start.getTime();
        long tFinal = end.getTime();
        tFinal = (tFinal - tInicial);
        double tiempoRentado = (double)tFinal/3600000;
        double minTot = (double)tFinal/60000;
     
        int horas = (int)tiempoRentado;

		
		double total = horas*this.hourFee + this.baseFee;
		Receipt boleta = new Receipt(this.clientName, this.serie, this.start, this.end, (int)minTot ,this.baseFee, this.hourFee, total);
		
		this.clientName = null;
		this.start = null;

		return boleta;
	}
	
	/**
	 * establece color de fondo del botón dependiendo del método isRented()
	 */
	
	public void paintComponent(){
		if(this.isRented()) {
			this.setBackground(COLOR_RENTED);
		}
		else {
			this.setBackground(COLOR_AVAILABLE);
		}
		
	}
	
	//	GETTERS Y SETTERS
	
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public double getBaseFee() {
		return baseFee;
	}
	public void setBaseFee(double baseFee) {
		this.baseFee = baseFee;
	}
	
	public double getHourFee() {
		return hourFee;
	}
	public void setHourFee(double hourFee) {
		this.hourFee = hourFee;
	}
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}


}

