package cl.uach.info090.tarea;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Esta clase representa el programa (tiene un main) y además la ventana principal
 * 
 * @author nicol
 */

public class RentalApp extends JFrame implements ActionListener{
	
	private JFrame ventana;
	
	private JPanel panelBotones; 
	private JPanel panelInfo;
	private JPanel info;
	private JPanel botones;
	private JTextField tex[];
	private JLabel texto;
	private JLabel estado;
	private JButton botonVerificar;
	private JButton sal;
	private JButton expBol;
	
	private JList<Receipt> listReceipts;
	private DefaultListModel<Receipt> listModel;
	
	public final Color verde = new Color(100,150,90);
	public final Color rojo = new Color(221,81,69);
	
	private RentalItemButton currentItem;
	
	/**
	 * Constructor de la clase
	 */
	
	public RentalApp() {
		currentItem = null;
		ventana = new JFrame("RentalApp");	//Nombre de mi ventana
		ventana.setBounds(305, 180, 762, 428);  				//(x, y, ancho, alto) -> con x e y en el 4to cuadrante 
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setLayout(new GridLayout(1,2));	//para que los elementos dentro de la ventana tengan posiciones especificas
		
		panelBotones = new JPanel();
		panelInfo = new JPanel();
		
		panelBotones.setLayout(null);
		panelInfo.setLayout(null);
		
		botones = new JPanel();
		botones.setLayout(new GridLayout(4,4,5,5));
		loadRentalItems("data/rental_items.csv");
	
		info = new JPanel();
		info.setLayout(new GridLayout(7,2));
		String palabras[] = {"Serie:","Desc:","Valor base:","Valor hora:","Estado:","Cliente:","Inicio:"};	
		tex = new JTextField[6];
		
		int j = 0;
		int k = 0;
		for(int i = 0; i < 14; i++) {
			if(i%2 == 0) {
				texto = new JLabel(palabras[j]);
				j++;
				info.add(texto);
			}
			else {
				if(i != 9) {
					tex[k] = new JTextField();
					tex[k].setSize(1,100);
					tex[k].setEditable(false);
					info.add(tex[k]);
					k++;
				}
				else {
					texto = new JLabel("");
					info.add(texto);
				}
			}
		}
				
		estado = new JLabel();
		estado.setBounds(170,160,100,20);
		panelInfo.add(estado);
		
		botonVerificar = new JButton();
		botonVerificar.setBounds(250,160,85,20);
		botonVerificar.addActionListener(this);
		botonVerificar.setVisible(false);
		
		
		JLabel ultBol = new JLabel("Ultimas Boletas:");
		ultBol.setBounds(10,270,100,20);
		panelBotones.add(ultBol);
		
		expBol = new JButton("Exportar Boletas");
		expBol.setBounds(50,323,130,20);
		expBol.addActionListener(this);
		panelInfo.add(expBol);
		

		sal = new JButton("Salir");
		sal.setBounds(190,323,100,20);
		sal.addActionListener(this);
		panelInfo.add(sal);
		
		info.setBounds(5, 10, 330, 250);
		botones.setBounds(10, 10, 350, 250);
		
		panelBotones.add(botones);
		panelInfo.add(botonVerificar);
		panelInfo.add(info);
		
		listModel = new DefaultListModel<Receipt>();
		listReceipts = new JList<Receipt>(listModel);
		JScrollPane listScrollPane = new JScrollPane(listReceipts);
		listScrollPane.getViewport().setBackground(Color.white);
		listScrollPane.setBounds(10, 290, 350, 90);
		
		panelBotones.add(listScrollPane);
		
		ventana.add(panelBotones);
		ventana.add(panelInfo);
		ventana.setVisible(true);
	}
		
	/**
	 * Crea un objeto apropiado de entre las subclases
	 * de RentalItemButton y se agrega al panel de botones en la ventana.
	 * 
	 * @param filePath
	 */
	
	public void loadRentalItems(String filePath) {
		ArrayList<String> texto = new ArrayList<String>();
		File archivo = new File(filePath);
		try{
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea = ""; 
			while((linea = br.readLine()) != null){
				texto.add(linea);
			}
			fr.close();
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		String serial, desc;
		double baseFee, hourFee;
		
		for(String linea : texto) {
			String[] elem = linea.split(",");
			serial = elem[0];
			desc = elem[1];
			baseFee = Double.parseDouble(elem[2]);
			hourFee = Double.parseDouble(elem[3]);
			RentalItemButton bks = null;
			
			if(serial.contains("B")){
				bks = new BikeButton (serial, desc, baseFee, hourFee);
			}
			
			if(serial.contains("K")){
				bks = new KayakButton (serial, desc, baseFee, hourFee);
			}
			
			if(serial.contains("S")){
				bks = new SegwayButton (serial, desc, baseFee, hourFee);
			}
			bks.setText(serial);
			bks.addActionListener(this);
			bks.paintComponent();
			botones.add(bks);
		}
	}
	
	/**
	 * El método toma el objeto r de tipo RentalItemButton y se despliegan todos los componentes 
	 * de la ventana en el lado derecho, los valores de los atributos 
	 * del objeto r (r.getSerial(), r.getDesc(), r.getHourFee(), etc). 
	 * 
	 * @param r
	 */
	
	public void showDetails(RentalItemButton r) {
		if(r!=null) {
			tex[0].setText(r.getSerie());
			tex[1].setText(r.getDesc());
			tex[2].setText(Double.toString(r.getBaseFee()));
			tex[3].setText(Double.toString(r.getHourFee()));
			
			if(r.isRented()){
				tex[4].setText(r.getClientName());
				
				String fecha = r.getStart().toString();
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
				
				tex[5].setText(fecha + " " + hora);
			}
			else {
				tex[4].setText(null);
				tex[5].setText(null);
			}

		}
		else {
			tex[0].setText("");
			tex[1].setText("");
			tex[2].setText("");
			tex[3].setText("");
			tex[4].setText("");
			tex[5].setText("");
		}
		currentItem = r;
	}
	
	/**
	 * Este metodo debe reconocer el botón que lo invocó 
	 * y verificar si el botón apretado representa un item arrendado 
	 * o disponible.
	 * 
	 * Luego carga la información del item de arriendo en el
	 * panel del lado derecho usando el método showDetails.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof RentalItemButton) {
			RentalItemButton a = (RentalItemButton)e.getSource();
			showDetails(a);
			if(!currentItem.isRented()){
				estado.setText("Disponible");
				estado.setForeground(verde);
				botonVerificar.setText("Arrendar");
				botonVerificar.setForeground(Color.WHITE);
				botonVerificar.setBackground(verde);
				botonVerificar.setContentAreaFilled(false);
				botonVerificar.setOpaque(true);
				
				botonVerificar.setVisible(true);
			}
			else {
				estado.setText("Arrendado");
				estado.setForeground(rojo);
				botonVerificar.setVisible(true);
				botonVerificar.setText("Finalizar");
				botonVerificar.setForeground(Color.WHITE);
				botonVerificar.setBackground(rojo);
				botonVerificar.setContentAreaFilled(false);
				botonVerificar.setOpaque(true);
			}
		}
		else if(e.getSource() == botonVerificar) {
			JButton a = (JButton)e.getSource();
			String pal = a.getText();
			
			if(pal.equals("Arrendar")){
				String cName = JOptionPane.showInputDialog("Arrendar item " + currentItem.getSerie() + "?\n\nCliente:");
				if ((cName != null) && (cName.length() > 0)) {
					currentItem.rentMe(cName, new Date());
					showDetails(currentItem);
					
					botonVerificar.setText("Finalizar");
					botonVerificar.setForeground(Color.WHITE);
					botonVerificar.setBackground(rojo);
					botonVerificar.setContentAreaFilled(false);
					botonVerificar.setOpaque(true);
					
					estado.setText("Arrendado");
					estado.setForeground(rojo);
					currentItem.paintComponent();
				}
			}
			else if(pal.equals("Finalizar")) {
				Receipt r = currentItem.returnMe(new Date());
				showDetails(currentItem);
				botonVerificar.setText("Arrendar");
				botonVerificar.setForeground(Color.WHITE);
				botonVerificar.setBackground(verde);
				botonVerificar.setContentAreaFilled(false);
				botonVerificar.setOpaque(true);
				
				estado.setText("Disponible");
				estado.setForeground(verde);
				currentItem.paintComponent();
				listModel.addElement(r);			
			}
		}
		
		else if(e.getSource() == sal){
			int result = JOptionPane.showConfirmDialog(null,"Está seguro/a que desea terminar el programa?", "Salir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(result == JOptionPane.OK_OPTION){
				System.exit(0);
			}
		}
		else if(e.getSource() == expBol){
			Object lI[] = listModel.toArray();
			Receipt a[] = new Receipt[lI.length];
			for(int i = 0; i < a.length; i++) {
				
			a[i] = (Receipt) lI[i];
			a[i].toFile();
			}
		}
	}
	
	/**
	 * Main de la clase
	 * @param args
	 */

	public static void main(String[] args) {
		RentalApp programa = new RentalApp();
	}
}
