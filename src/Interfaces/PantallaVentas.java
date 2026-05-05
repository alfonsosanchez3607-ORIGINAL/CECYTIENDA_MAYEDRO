package Interfaces;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Entidades.DetalleVentas;
import Entidades.Productos;
import Entidades.Ventas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.text.NumberFormat;
import javax.swing.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PantallaVentas {

	private JFrame frmSistemaCecyPos;
	PantallaProductos pp=new PantallaProductos();
	Cobro C = new Cobro();
	private JButton btnProductos;
	private JButton btnSalir;
	private JButton btnAgregarProducto;
	private JButton btnBuscarProducto;
	private JScrollPane scrollPane;
	private JLabel lblTotal;
	private JLabel lblMxn;
	private JButton btnCobrar;
	private JTable table;
    String codigoBarras="";
    double total = 0.0;
    ArrayList<DetalleVentas> detalleVenta = new ArrayList<DetalleVentas>();
    Productos p = new Productos();
	DefaultTableModel model = new DefaultTableModel();
	private JTextField txtCodigoBarras;
	private int id_producto;
	private JLabel lblNombreCliente;
	private JTextField txtNombreCliente;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaVentas window = new PantallaVentas();
					window.frmSistemaCecyPos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PantallaVentas() {
		initialize();
	}

	
	private void initialize() {
		frmSistemaCecyPos = new JFrame();
		frmSistemaCecyPos.getContentPane().setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		frmSistemaCecyPos.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaVentas.class.getResource("/Imagen/pibble.png")));
		frmSistemaCecyPos.setTitle("SISTEMA CECY POS 2026");
		frmSistemaCecyPos.setBounds(100, 100, 953, 598);
		frmSistemaCecyPos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaCecyPos.setLocationRelativeTo(null);
		frmSistemaCecyPos.getContentPane().setLayout(null);
		
		btnProductos = new JButton("Productos");
		btnProductos.setFont(new Font("Elephant", Font.ITALIC, 10));
		btnProductos.setIcon(redimensionar (50, 50, "/Imagen/productos1.png"));
		btnProductos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProductos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProductos.setVerticalAlignment (SwingConstants.CENTER);
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			pp.frmGestionProductos.setVisible(true)	;
				
			}
		});
		btnProductos.setBounds(683, 10, 118, 65);
		frmSistemaCecyPos.getContentPane().add(btnProductos);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Elephant", Font.ITALIC, 10));
		btnSalir.setIcon(redimensionar (50,50 , "/Imagen/SALIR.png"));
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setVerticalAlignment (SwingConstants.CENTER);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Estas seguro de salir","Salir",JOptionPane.YES_NO_OPTION)==0) {
					JOptionPane.showMessageDialog(null,"ADIOS");
					System.exit(0);
				}
				
			}
		});
		btnSalir.setBounds(811, 10, 118, 65);
		frmSistemaCecyPos.getContentPane().add(btnSalir);
		
		txtCodigoBarras = new JTextField();
		txtCodigoBarras.setBounds(125, 89, 416, 40);
		frmSistemaCecyPos.getContentPane().add(txtCodigoBarras);
		txtCodigoBarras.setColumns(10);
		
		JLabel lblCodigoBarras = new JLabel("Codigo de Barras");
		lblCodigoBarras.setFont(new Font("Elephant", Font.PLAIN, 10));
		lblCodigoBarras.setBounds(20, 88, 108, 40);
		frmSistemaCecyPos.getContentPane().add(lblCodigoBarras);
		
		btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}	    
			
		});
		btnAgregarProducto.setIcon(redimensionar (25, 25, "/Imagen/add1.png"));
		btnAgregarProducto.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAgregarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		btnAgregarProducto.setVerticalTextPosition(SwingConstants.CENTER);
		btnAgregarProducto.setVerticalAlignment(SwingConstants.CENTER);
		btnAgregarProducto.setFont(new Font("Elephant", Font.PLAIN, 10));
		btnAgregarProducto.setBounds(549, 88, 167, 40);
		frmSistemaCecyPos.getContentPane().add(btnAgregarProducto);
		
		btnBuscarProducto = new JButton("Buscar Producto");
		btnBuscarProducto.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				for (Productos p : pp.listaProductos) {
			        if (p.getIdProducto() == id_producto) {
			           
			        }
			    }
			    return ;
			}

			public void incrementaCantidad(int idProducto) {
			    int index = -1;
			    for (int i = 0; i < detalleVenta.size(); i++) {
			        if (idProducto == detalleVenta.get(i).getIdProducto()) {
			            index = i;
			        }
			    }
			    detalleVenta.get(index).setCantidad(
			        detalleVenta.get(index).getCantidad() + 1
			    );
			}

			public boolean siEsta(int idProducto) {
			    boolean si = false;
			    for (DetalleVentas d : detalleVenta) {
			        if (d.getIdProducto() == idProducto) {
			            return true;
			        }
			    }
			    return false;
			}

			public static String aMoneda(double cantidad) {
			    Locale localeMexico = new Locale("es", "MX");
			    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(localeMexico);
			    return formatoMoneda.format(cantidad);
			
			}
		});
		btnBuscarProducto.setIcon(redimensionar (25, 25, "/Imagen/BUSCAR.png"));
		btnBuscarProducto.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBuscarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		btnBuscarProducto.setVerticalTextPosition(SwingConstants.CENTER);
		btnBuscarProducto.setVerticalAlignment(SwingConstants.CENTER);
		btnBuscarProducto.setFont(new Font("Elephant", Font.PLAIN, 10));
		btnBuscarProducto.setBounds(726, 88, 167, 40);
		frmSistemaCecyPos.getContentPane().add(btnBuscarProducto);
		
		scrollPane = new JScrollPane();
		scrollPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		scrollPane.setBounds(30, 139, 887, 240);
		frmSistemaCecyPos.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int fila = table.getSelectedRow();
				if (fila != -1) {

				    if (e.getKeyCode() == KeyEvent.VK_PLUS ||  e.getKeyCode() == KeyEvent.VK_ADD) {
				    	cambiarCantidad(fila,1);

				        // Acción cuando presionas +
				        
				    } else if (e.getKeyCode() == KeyEvent.VK_MINUS ||  e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
				    	cambiarCantidad(fila,-1);

				        // Acción cuando presionas -

				    }
				}
			}
		});
		txtCodigoBarras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					agregarProducto();
				}
			}
		});


		
		model.addColumn("CODIGO DE BARRAS");
		model.addColumn("NOMBRE");
		model.addColumn("PRECIO");
		model.addColumn("STOCK");
		model.addColumn("TOTAL");
		table.setModel(model);
		table.setDefaultEditor(Object.class , null);
		scrollPane.setViewportView(table);
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Elephant", Font.PLAIN, 25));
		lblTotal.setBounds(657, 413, 108, 40);
		frmSistemaCecyPos.getContentPane().add(lblTotal);
		
		lblMxn = new JLabel("$0.0 MXN");
		lblMxn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMxn.setFont(new Font("Elephant", Font.PLAIN, 25));
		lblMxn.setBounds(753, 415, 154, 40);
		frmSistemaCecyPos.getContentPane().add(lblMxn);
		
		btnCobrar = new JButton("Cobrar");
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(total>0  && detalleVenta.size()>0) {
				C.setTotal(total);
				C.lblTotalPagar.setText(aMoneda(total));
				C.frmCobrar.setVisible(true)	;
				C.txtMonto.setRequestFocusEnabled(true);
				C.setVentas(PantallaVentas.this);
			}else {mensaje("NO HAS INGRESADO PRODUCTOS","ERROR");

			}
			}
		});
		btnCobrar.setIcon(redimensionar (32, 32, "/Imagen/cobrar.png"));
		btnCobrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCobrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCobrar.setVerticalAlignment (SwingConstants.CENTER);
		btnCobrar.setFont(new Font("Elephant", Font.PLAIN, 10));
		btnCobrar.setBounds(646, 474, 108, 56);
		frmSistemaCecyPos.getContentPane().add(btnCobrar);
		
		JLabel lblFechaHora = new JLabel("");
		lblFechaHora.setFont(new Font("Elephant", Font.PLAIN, 11));
		lblFechaHora.setBounds(444, 10, 229, 21);
		frmSistemaCecyPos.getContentPane().add(lblFechaHora);
		
		lblNombreCliente = new JLabel("Cliente");
		lblNombreCliente.setFont(new Font("Elephant", Font.PLAIN, 10));
		lblNombreCliente.setBounds(20, 35, 108, 40);
		frmSistemaCecyPos.getContentPane().add(lblNombreCliente);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setColumns(10);
		txtNombreCliente.setBounds(125, 41, 406, 21);
		frmSistemaCecyPos.getContentPane().add(txtNombreCliente);
		SimpleDateFormat formato = new SimpleDateFormat("EEEE dd/MM/yyyy HH:mm:ss", new Locale("es", "MX"));

		Timer timer = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Date ahora = new Date(); // obtiene fecha actual
		        lblFechaHora.setText(formato.format(ahora)); // la pone en el label
		    }
		});

		timer.start();
	}
	public void cambiarCantidad(int index, int incremento) {
	    if (incremento == -1) {
	        if (detalleVenta.get(index).getCantidad() == 1) {
	            int op = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar producto?");
	            if (op == 0) {
	                detalleVenta.remove(index);
	            }
	        } else {
	            detalleVenta.get(index).setCantidad(
	                detalleVenta.get(index).getCantidad() - 1
	            );
	        }
	    } else {
	        if ((detalleVenta.get(index).getCantidad() + 1) >
	            buscarProducto(detalleVenta.get(index).getIdProducto()).getStock()) {

	            mensaje("YA NO SE PUEDE AGREGAR MAS CANTIDAD", "ERROR");
	        } else {
	            detalleVenta.get(index).setCantidad(
	                detalleVenta.get(index).getCantidad() + 1
	            );
	        }
	    }

	    actualizarDetalleVentas();
	    table.setRowSelectionInterval(index,index); 
	    table.scrollRectToVisible(table.getCellRect(index, 0, true));
	    table.requestFocusInWindow();
	}
	public void actualizarDetalleVentas() {
	    // Limpia la tabla
	    while (model.getRowCount() > 0) {
	        model.removeRow(0);
	    }

	    total = 0;

	    // Recorre los productos en la venta
	    for (DetalleVentas d : detalleVenta) {
	        Productos p = buscarProducto(d.getIdProducto());

	        model.addRow(new Object[] {
	            p.getCodigoBarras(),
	            p.getNombre(),
	            p.getPrecioVenta(),
	            d.getCantidad(),
	            (p.getPrecioVenta() * d.getCantidad())
	        });

	        total += (p.getPrecioVenta() * d.getCantidad());
	    }

	    // Actualiza la tabla y el total
	    table.setModel(model);
	    lblMxn.setText("" + aMoneda(total));
	}
	public ImageIcon redimensionar(int w, int h, String ruta) {

		ImageIcon icono=new ImageIcon(PantallaProductos.class.getResource(ruta));

		Image imagenOriginal = icono.getImage();

		Image imagenEscalada = imagenOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		ImageIcon iconRedimensionado = new ImageIcon(imagenEscalada);
		return iconRedimensionado;
	}
	// ===============================
	// MÉTODO AGREGAR PRODUCTO
	// ===============================

	public void agregarProducto() {

	    // Obtener el texto escrito en el campo de código de barras
	    codigoBarras = txtCodigoBarras.getText();

	    // Verificar si el campo está vacío
	    if (codigoBarras.length() == 0) {

	        // Mostrar mensaje de error si no se ingresó código
	    	mensaje("NO HAS INGRESADO CODIGO DE BARRAS",
		            "ERROR");

	        // Detener el método
	        return;
	    }

	    // Variable para guardar el ID del producto encontrado
	    int id = -1;

	    // Recorrer la lista de productos
	    for (Productos p : pp.listaProductos) {

	        // Comparar si el código ingresado coincide
	        if (p.getCodigoBarras().equals(codigoBarras)) {

	            // Guardar el ID del producto encontrado
	            id = p.getIdProducto();
	        }
	    }

	    // Si no se encontró el producto
	    if (id == -1) {
	    	mensaje("NO EXISTE PRODUCTO", "ERROR");


	        // Detener el método
	        return;
	    }

	    // Verificar si el producto ya fue agregado
	    if (!siEsta(id)) {
	    	
           if(buscarProducto(id).getStock()> 0) {
	        // Si no existe, agregarlo con cantidad 1
	        detalleVenta.add(new DetalleVentas(id, 1));
	    } else {
	    	mensaje("PRODUCTO SIN STOCK", "ERROR");
	    }
	    }else {
	        incrementaCantidad(id);
	    }

	    // Limpiar todas las filas actuales de la tabla
	    while (model.getRowCount() > 0) {
	        model.removeRow(0);
	    }

	    // Reiniciar el total de la venta
	    total = 0;

	    // Recorrer todos los productos agregados
	    for (DetalleVentas d : detalleVenta) {

	       
	        Productos p = buscarProducto(d.getIdProducto());

	        // Agregar fila nueva a la tabla
	        model.addRow(new Object[]{   p.getCodigoBarras(),p.getNombre(), p.getPrecioVenta(), d.getCantidad(),
	        		(p.getPrecioVenta() * d.getCantidad())  });

	        // Sumar al total general
	        total += (p.getPrecioVenta() * d.getCantidad());
	    }

	    // Actualizar la tabla
	    table.setModel(model);

	    // Mostrar el total en moneda mexicana
	    lblMxn.setText("" + aMoneda(total));
	}

	
	// ===============================
	// MÉTODO BUSCAR PRODUCTO
	// ===============================

	public Productos buscarProducto(int idProducto) {

	    // Recorrer la lista de productos
	    for (Productos p : pp.listaProductos) {

	        // Si encuentra el ID correcto
	        if (p.getIdProducto() == idProducto) {

	            // Regresar el producto
	            return p;
	        }
	    }

	    // Si no encuentra nada
	    return null;
	}


	// ===============================
	// MÉTODO AUMENTAR CANTIDAD
	// ===============================

	public void incrementaCantidad(int idProducto) {

	    // Variable para guardar la posición
	    for (DetalleVentas d : detalleVenta) {
	    	if(idProducto == d.getIdProducto()){
	    		if((d.getCantidad()+1)>buscarProducto(idProducto).getStock()){
	    			mensaje("YA NO SE PUEDE AGREGAR MAS CANTIDAD"
	    					+ "", "ERROR");
	    		}else {
	    			
             d.setCantidad(d.getCantidad()+1);		
	    		}
		}	   
	}
	}
	public boolean siEsta(int idProducto) {

	    // Recorrer productos agregados
	    for (DetalleVentas d : detalleVenta) {

	        // Si encuentra el mismo ID
	        if (d.getIdProducto() == idProducto) {

	            // Regresar true
	            return true;
	        }
	    }

	    // Si no existe
	    return false;
	}


	// ===============================
	// MÉTODO FORMATO MONEDA
	// ===============================

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
	public void guardarVenta() {
		
		//INSERTAR REGISTROS EN BASE DE DATOS
		Ventas v= new Ventas(txtNombreCliente.getText(),"EFECTIVO", total);
		int idVenta = v.insertarVenta();
		System.out.println("ID VENTA: "+idVenta);
		for (DetalleVentas d : detalleVenta) {
			d.setIdVenta(idVenta);
			d.insertarDetalleVenta();
		}
		
		pp.cargarProductos();
		txtCodigoBarras.setText("");
		txtNombreCliente.setText("");
		lblMxn.setText(aMoneda(0));
		while (model.getRowCount()>0)
			model.removeRow(0);
		detalleVenta.clear();
		table.setModel(model);
		
	}
	
}