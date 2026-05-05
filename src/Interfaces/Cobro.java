package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cobro {
	
	double total = 0.0;
	double monto=0.0;
	boolean validar=false;

	public JFrame frmCobrar;
	public JTextField txtMonto;
	private JLabel lblTitulo;
	public JLabel lblTotalPagar;
	PantallaVentas Ventas = null;
	private JLabel lblCambio;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cobro window = new Cobro();
					window.frmCobrar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public JLabel getLblTitulo() {
		return lblTitulo;
	}



	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}



	public JLabel getLblTotalPagar() {
		return lblTotalPagar;
	}



	public void setLblTotalPagar(JLabel lblTotalPagar) {
		this.lblTotalPagar = lblTotalPagar;
	}



	public PantallaVentas getVentas() {
		return Ventas;
	}



	public void setVentas(PantallaVentas ventas) {
		Ventas = ventas;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public Cobro() {
		initialize();
	}

	private void initialize() {
		frmCobrar = new JFrame();
		frmCobrar.setBounds(100, 100, 326, 365);
		frmCobrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCobrar.getContentPane().setLayout(null);
		
		JToggleButton tglEfectivo = new JToggleButton("Efectivo");
		tglEfectivo.setIcon(redimensionar (32, 32, "/Imagen/efec.png"));
		tglEfectivo.setVerticalTextPosition(SwingConstants.BOTTOM);
		tglEfectivo.setHorizontalTextPosition(SwingConstants.CENTER);
		tglEfectivo.setVerticalAlignment (SwingConstants.CENTER);
		tglEfectivo.setBounds(10, 10, 91, 92);
		tglEfectivo.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmCobrar.getContentPane().add(tglEfectivo);
		
		JToggleButton tglTargeta = new JToggleButton("Targeta");
		tglTargeta.setIcon(redimensionar (32, 32, "/Imagen/traje.png"));
		tglTargeta.setVerticalTextPosition(SwingConstants.BOTTOM);
		tglTargeta.setHorizontalTextPosition(SwingConstants.CENTER);
		tglTargeta.setVerticalAlignment (SwingConstants.CENTER);
		tglTargeta.setBounds(111, 10, 91, 92);
		tglTargeta.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmCobrar.getContentPane().add(tglTargeta);
		
		JToggleButton tglTransferencia = new JToggleButton("Transferencia");
		tglTransferencia.setIcon(redimensionar (32, 32, "/Imagen/trans.png"));
		tglTransferencia.setVerticalTextPosition(SwingConstants.BOTTOM);
		tglTransferencia.setHorizontalTextPosition(SwingConstants.CENTER);
		tglTransferencia.setVerticalAlignment (SwingConstants.CENTER);
		tglTransferencia.setBounds(212, 10, 91, 92);
		tglTransferencia.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmCobrar.getContentPane().add(tglTransferencia);
		
		lblTitulo = new JLabel("TOTAL A PAGAR");
		lblTitulo.setBounds(0, 112, 312, 52);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		frmCobrar.getContentPane().add(lblTitulo);
		
		tglEfectivo.setSelected(true);
		
		lblTotalPagar = new JLabel("$0.0");
		lblTotalPagar.setBounds(111, 155, 91, 29);
		lblTotalPagar.setForeground(Color.RED);
		lblTotalPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPagar.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmCobrar.getContentPane().add(lblTotalPagar);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(0, 194, 76, 19);
		lblMonto.setHorizontalAlignment(SwingConstants.RIGHT);
		frmCobrar.getContentPane().add(lblMonto);
		
		txtMonto = new JTextField();
		txtMonto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			try {
				if(txtMonto.getText().length()>0) {
				    monto=Double.parseDouble(txtMonto.getText());
				if(total<=monto) {
					lblCambio.setText(aMoneda(monto -total));
					validar=true;
					
				}
			}else {
				validar=false;
			}
				
		} catch (Exception e2) {
		}	
			}
		});
		txtMonto.setBounds(86, 194, 192, 19);
		frmCobrar.getContentPane().add(txtMonto);
		txtMonto.setColumns(10);
		
		lblCambio = new JLabel("$0.0");
		lblCambio.setBounds(111, 252, 91, 29);
		lblCambio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambio.setForeground(Color.BLACK);
		lblCambio.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmCobrar.getContentPane().add(lblCambio);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(validar) {
						Ventas.guardarVenta();
						total=0;
						txtMonto.setText("");
						frmCobrar.setVisible(false);
						mensaje("PAGO EXITOSO","EXITO");
					}else {
						mensaje("MONTO A PAGAR INVALIDO","ERROR");
					}
				} catch (Exception e2) {
					
				
			}
			}
		});
		btnPagar.setBounds(111, 290, 84, 29);
		btnPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmCobrar.getContentPane().add(btnPagar);
		
		JLabel lblCambioTitulo = new JLabel("Cambio");
		lblCambioTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambioTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCambioTitulo.setBounds(96, 223, 117, 19);
		frmCobrar.getContentPane().add(lblCambioTitulo);
	}
	public ImageIcon redimensionar(int w, int h, String ruta) {

		ImageIcon icono=new ImageIcon(PantallaProductos.class.getResource(ruta));

		Image imagenOriginal = icono.getImage();

		Image imagenEscalada = imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
		return iconRedimensionado;
	}
	public static String aMoneda(double cantidad) {

	    // Configurar formato para México
	    Locale localeMexico = new Locale("es", "MX");

	    // Crear formato de moneda
	    NumberFormat formatoMoneda =
	            NumberFormat.getCurrencyInstance(localeMexico);

	    // Regresar cantidad en formato moneda
	    return formatoMoneda.format(cantidad);
}
	public void mensaje(String msj, String titulo) {
		 JOptionPane.showMessageDialog( null,msj,titulo,JOptionPane.QUESTION_MESSAGE,
			        redimensionar(32, 32, "/Imagen/PIBBLEE.png")
			        );
	}
}