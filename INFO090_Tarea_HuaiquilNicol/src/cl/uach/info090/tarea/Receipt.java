package cl.uach.info090.tarea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Esta clase es la que se encarga de generar los
 * strings que forman el nombre de la boleta que se muestra
 * en el panel de boletas.
 * 
 * Tambien se encarga de generar las boletas como archivos de
 * texto al presionar el boton de "exportar boletas"
 * 
 * @author nicol
 *
 */

public class Receipt {
	private String clientName;
	private String serial;
	private Date start;
	private Date end;
	private int timeMin;
	private double baseFee;
	private double hourFee;
	private double total;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param clientName
	 * @param serial
	 * @param start
	 * @param end
	 * @param timeMin
	 * @param baseFee
	 * @param hourFee
	 * @param total
	 */
	
	public Receipt(String clientName, String serial, Date start, 
			Date end, int timeMin, double baseFee, double hourFee,
			double total) {
		super();
		this.clientName = clientName;
		this.serial = serial;
		this.start = start;
		this.end = end;
		this.timeMin = timeMin;
		this.baseFee = baseFee;
		this.hourFee = hourFee;
		this.total = total;
	}
	
	/**
	 * retorna un String conteniedo fecha y hora inicio, 
	 * cantidad de tiempo y precio total: 
	 * 
	 */
	@Override
	public String toString() {
		String bolName = "";
		String fecha = this.start.toString();
		String[] meses = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		String mes = "";


		for(int i=0; i<12; i++){
			if(fecha.contains(meses[i]) && i < 9) {
				mes = "0" + Integer.toString(i+1);
			}
			if(fecha.contains(meses[i]) && i >= 9){
				mes = Integer.toString(i+1);
			}
		}

		String dia = fecha.substring(8, 11) ;
		String year = fecha.substring(fecha.length()-5, fecha.length());
		String hora = fecha.substring(11,16);
		fecha = (dia + "/" + mes + "/" + year).replace(" ", "");
	
		int horas = this.timeMin/60;
		int minutos = this.timeMin - (horas *60);
	
		String m = "";
		if(minutos < 10) {
			m = "0" + Integer.toString(minutos);
		}
		else {
			m = Integer.toString(minutos);
		}
		
		
		String tiempo = Integer.toString(horas) + ":" + m;
		
		bolName = fecha + " " + hora + " " + tiempo + " $" +(int)this.total;
		return bolName;
	}
	
	/**
	 * Nombra el archivo usando start Date y clientName
	 * 
	 */
	public void toFile(){
		String fileName = "";
		String fechaI = this.start.toString();
		String fechaF = this.end.toString();
		String[] meses = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		String mes = "";		
		for(int i=0; i<12; i++){
			if(fechaI.contains(meses[i]) && i < 9) {
				mes = "0" + Integer.toString(i+1);
			}
			if(fechaI.contains(meses[i]) && i >= 9){
				mes = Integer.toString(i+1);
			}
		}
		String dia = fechaI.substring(8, 11) ;
		String year = fechaI.substring(fechaI.length()-5, fechaI.length());
		fechaI = (year + mes + dia).replace(" ", "");
		
		fileName = fechaI + "_" + this.clientName;
		
		for(int i=0; i<12; i++){
			if(fechaF.contains(meses[i]) && i < 9) {
				mes = "0" + Integer.toString(i+1);
			}
			if(fechaF.contains(meses[i]) && i >= 9){
				mes = Integer.toString(i+1);
			}
		}
		dia = fechaF.substring(8, 11) ;
		year = fechaF.substring(fechaF.length()-5, fechaF.length());
		String hora = fechaF.substring(11,16);
		fechaF = (dia + "/" + mes + "/" + year).replace(" ", "");
		
		String[] fI = toString().split(" ");
		
		
		try{
			FileWriter filewr = new FileWriter(fileName + ".txt");
			BufferedWriter out = new BufferedWriter(filewr);

			out.write("Client Name: \t" + this.clientName + 
					"\nSerial: \t" + this.serial +
					"\nRental Started: " + fI[0] + " " + fI[1] +
					"\nRental Ended: \t" + fechaF + " " + hora +
					"\nTotal Time: \t" + fI[2] +
					"\nBase Fee: \t" + this.baseFee +
					"\nHour Price: \t" + this.hourFee +
					"\n-------------------------------" +
					"\nTOTAL: \t\t" + fI[3]);
			
			out.close();
			filewr.close();
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}		
	}

	//GETTERS y SETTERS
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
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

	public int getTimeMin() {
		return timeMin;
	}

	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}


}
