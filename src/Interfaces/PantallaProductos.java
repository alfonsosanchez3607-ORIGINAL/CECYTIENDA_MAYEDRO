package Interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import Data.DataProductos;
import Entidades.Productos;


import javax.swing.event.ChangeEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaProductos {
    JFrame frmGestionProductos;
    private JTextField txtCodigoBarras;
    private JTextField txtNombre;
    private JLabel lblCodigoBarras;
    private JSlider sldPrecioVenta;
    private JSlider sldStock;
    private JLabel lblValorPrecio;
    private JLabel lblValorStock;
    private JTable tblProductos;
    
    DefaultTableModel model = new DefaultTableModel();
    public ArrayList<Productos> listaProductos = new ArrayList<Productos>();
    DataProductos dp= new DataProductos();
    Productos p=new Productos();
    int fila=-1;
    private JButton btnEliminar;
    private JButton btnActualizar;

	
	public static void main(String[] args) {
		PantallaProductos window = new PantallaProductos();
		window.frmGestionProductos.setVisible(true);
				
	}

	public PantallaProductos() {
		frmGestionProductos = new JFrame();
		frmGestionProductos.setTitle("GESTION PRODUCTOS");
		frmGestionProductos.setBounds(100, 100, 871, 594);
		frmGestionProductos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestionProductos.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION PRODUCTOS");
		lblNewLabel.setBounds(345, 10, 175, 12);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
		frmGestionProductos.getContentPane().add(lblNewLabel);
		
		lblCodigoBarras = new JLabel("Codigo Barras");
		lblCodigoBarras.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblCodigoBarras.setBounds(33, 47, 189, 31);
		frmGestionProductos.getContentPane().add(lblCodigoBarras);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblNombre.setBounds(33, 87, 189, 31);
		frmGestionProductos.getContentPane().add(lblNombre);
		
		txtCodigoBarras = new JTextField();
		txtCodigoBarras.setBounds(199, 48, 213, 31);
		frmGestionProductos.getContentPane().add(txtCodigoBarras);
		txtCodigoBarras.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(199, 88, 213, 31);
		txtNombre.setColumns(10);
		frmGestionProductos.getContentPane().add(txtNombre);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblPrecioVenta.setBounds(33, 122, 189, 31);
		frmGestionProductos.getContentPane().add(lblPrecioVenta);

		lblValorPrecio = new JLabel("0.0");
		lblValorPrecio.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblValorPrecio.setBounds(422, 122, 189, 31);
		frmGestionProductos.getContentPane().add(lblValorPrecio);
		
		
		sldPrecioVenta = new JSlider();
		sldPrecioVenta.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblValorPrecio.setText("$"+sldPrecioVenta.getValue());
				
			}
		});
		sldPrecioVenta.setPaintLabels(true);
		sldPrecioVenta.setValue(1);
		sldPrecioVenta.setPaintTicks(true);
		sldPrecioVenta.setMinimum(1);
		sldPrecioVenta.setBounds(199, 128, 213, 25);
		frmGestionProductos.getContentPane().add(sldPrecioVenta);
		
		
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblStock.setBounds(33, 157, 189, 31);
		frmGestionProductos.getContentPane().add(lblStock);
		
		lblValorStock = new JLabel("0.0");
		lblValorStock.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		lblValorStock.setBounds(422, 157, 189, 31);
		frmGestionProductos.getContentPane().add(lblValorStock);
		
		sldStock = new JSlider();
		sldStock.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblValorStock.setText(""+sldStock.getValue());
			}
		});
		sldStock.setPaintLabels(true);
		sldStock.setValue(1);
		sldStock.setPaintTicks(true);
		sldStock.setMinimum(1);
		sldStock.setBounds(199, 163, 213, 25);
		frmGestionProductos.getContentPane().add(sldStock);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(redimensionar (32, 32, "/Imagen/add1.png"));
		btnAgregar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAgregar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregar.setVerticalAlignment (SwingConstants.CENTER);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtCodigoBarras.getText().length()==0||txtNombre.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS","ERROR", JOptionPane.ERROR_MESSAGE);				
						return;
					}
					Productos p = new Productos(
							0,
						txtCodigoBarras.getText(),
						txtNombre.getText(),
						sldPrecioVenta.getValue(),
						sldStock.getValue()
					);

					if (p.insertarProducto()) {
						JOptionPane.showMessageDialog(null, "PRODUCTO AGREGADO CORRECTAMENTE","EXITOOO!!!!",
								JOptionPane.QUESTION_MESSAGE, redimensionar(32,32, "/imagen/pibble.png") );
						
						fila=-1;
						cargarProductos();
						txtCodigoBarras.setText("");
						txtNombre.setText("");
						sldPrecioVenta.setValue(1);
						sldStock.setValue(1);
					} else {
						
						JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR PRODUCTO","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR PRODUCTO");
				}
		           
			}	
		});
		btnAgregar.setBounds(33, 213, 131, 60);
		frmGestionProductos.getContentPane().add(btnAgregar);
		
		JScrollPane sclProductos = new JScrollPane();
		sclProductos.setBounds(58, 307, 577, 198);
		frmGestionProductos.getContentPane().add(sclProductos);
		
		tblProductos = new JTable();
		tblProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblProductos.getSelectedRow();
				p=listaProductos.get(fila);
				txtCodigoBarras.setText(p.getCodigoBarras());
				txtNombre.setText(p.getNombre());
				sldPrecioVenta.setValue((int)p.getPrecioVenta());
				sldStock.setValue(p.getStock());
				
			}
		});
		model.addColumn("CODIGO DE BARRAS");
		model.addColumn("NOMBRE");
		model.addColumn("PRECIO");
		model.addColumn("STOCK");
		sclProductos.setViewportView(tblProductos);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(redimensionar (40, 40, "/Imagen/delete1.png"));
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setVerticalAlignment (SwingConstants.CENTER);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(fila!=-1) {
						int opcion=JOptionPane.showConfirmDialog(null, "estas seguro de eliminar producto??"
								,"ELIMINAR PRODUCTO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
								redimensionar(32,32, "/imagen/PIBBLEE.png") );
						if(opcion==0) {
							if(p.eliminarProducto()) {
								fila=-1;
								cargarProductos();
								txtCodigoBarras.setText("");
								txtNombre.setText("");
								sldPrecioVenta.setValue(1);
								sldStock.setValue(1);

								JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE","EXITO!!!!", 
										JOptionPane.QUESTION_MESSAGE, redimensionar(32,32, "/imagen/PIBBLEE.png") );
							}else {
								JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR PRODUCTO","ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "FALTA SELECIONAR PRODUCTO","ERROR", 
								JOptionPane.QUESTION_MESSAGE, redimensionar(32,32, "/imagen/PIBBLEE.png") );
						
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR PRODUCTO","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setVerticalAlignment(SwingConstants.CENTER);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setBounds(199, 213, 131, 60);
		frmGestionProductos.getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(redimensionar (32, 32, "/Imagen/edit1.png"));
		btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnActualizar.setVerticalAlignment (SwingConstants.CENTER);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
				if(fila!=-1) {
					if(txtCodigoBarras.getText().length()==0||txtNombre.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS","ERROR", JOptionPane.ERROR_MESSAGE);				
						return;
					}
				p.setCodigoBarras(txtCodigoBarras.getText());
				p.setNombre(txtNombre.getText());
				p.setPrecioVenta(sldPrecioVenta.getValue());
				p.setStock(sldStock.getValue());

					if (p.actualizarProductos()) {
						JOptionPane.showMessageDialog(null, "PRODUCTO ACTUALIZADO CORRECTAMENTE","EXITOOO!!!!",
								JOptionPane.QUESTION_MESSAGE, redimensionar(32,32, "/imagen/PIBBLEE.png") );
						
						txtCodigoBarras.setText("");
						txtNombre.setText("");
						sldPrecioVenta.setValue(1);
						sldStock.setValue(1);
						cargarProductos();
						fila=-1;
					} else {
						
						JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PRODUCTO","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "FALTA SELECIONAR PRODUCTO","ERROR", 
							JOptionPane.QUESTION_MESSAGE, redimensionar(32,32, "/imagen/PIBBLEE.png") );
				}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PRODUCTO");
				}
		           
			}	
				
				
			
		});
		btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnActualizar.setVerticalAlignment(SwingConstants.CENTER);
		btnActualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnActualizar.setBounds(365, 213, 131, 60);
		frmGestionProductos.getContentPane().add(btnActualizar);
		cargarProductos();
	}
	
	public void cargarProductos() {
		while(model.getRowCount()>0)model.removeRow(0);
		listaProductos=dp.cargarProductos();
		for (Productos p : listaProductos) {
		    model.addRow(new Object[] {
			p.getCodigoBarras(),
		    p.getNombre(),
		    p.getPrecioVenta(),
		    p.getStock() });
				
		}
		tblProductos.setModel(model);
	}
	public ImageIcon redimensionar(int w, int h, String ruta) {

		ImageIcon icono=new ImageIcon(PantallaProductos.class.getResource(ruta));

		Image imagenOriginal = icono.getImage();

		Image imagenEscalada = imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
		return iconRedimensionado;
	}
}
