package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);

		llenaCombo();
	}

	void llenaCombo() {
		
			//Conexion
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
			EntityManager em = fabrica.createEntityManager();
			
		// Combo1.obtener un listado de las categorias
	
			List<Categoria> lstCategorias = em.createQuery("select c from Categoria c", Categoria.class).getResultList();
	
			cboCategorias.addItem("Seleccione...");
			for ( Categoria c : lstCategorias) {
				cboCategorias.addItem(c.getIdcategoria() + "-" + c.getDescripcion());
				
			}
			
		//Pasar el istado al cnCategorias
		
		// Combo2.obtener un listado de los Proveedores
			List<Proveedor> lstProveedores = em.createQuery("select p from Proveedor p", Proveedor.class).getResultList();
		
		//Pasar el istado al cbProveedor
			cboProveedores.addItem("Seleccione...");
			for ( Proveedor p : lstProveedores) {
				cboProveedores.addItem(p.getIdprovedor() + "-" + p.getNombre_rs());
				
			}
			em.close();
	}

	void registrar() {
		//leer los campos 
		String codigo = leerCodigo();  //  Ojo !! completar con metodo
		String nombre = txtDescripcion.getText();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Integer.parseInt(txtPrecio.getText());
		int idcategoria = cboCategorias.getSelectedIndex();
		int idprovedor = cboProveedores.getSelectedIndex();
		int estado = 1;
		
		//Validaciones
		if (codigo == null) {
			return;
			
		}
		
		
		// objeto  de nuevo producto
		Producto p = new Producto();
		p.setId_prod(codigo);
		p.setDes_prod(nombre);
		p.setStk_prod(stock);
		p.setPre_prod(precio);
		p.setIdcategoria(idcategoria);
		p.setIdprovedor(idprovedor);
		p.setEst_prod(estado);
		
	//Guardar--------> registrar en la tabla
		//Conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		aviso( "Registro Ok", "Mensaje",JOptionPane.INFORMATION_MESSAGE);
		}catch (Exception e) {
			aviso( "Error al Registrar", "Mensaje",JOptionPane.ERROR_MESSAGE);
		}
		em.close();
	}
	
	 String leerCodigo() {
		if(!txtCodigo.getText().matches("[Pp][0-9]{4}")) {
		return null;
		}
		return txtCodigo.getText();
	}

	void aviso(String msg, String tit, int icono) {
		JOptionPane.showMessageDialog(this,msg, tit, icono);
	}

	void listado() {
		//Conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//generar un listado
		List<Producto> lstProductos = em.createQuery("select p from Producto p", Producto.class).getResultList();
		
		//mostrar el listado en el txt Area
		for (Producto p : lstProductos) {
			imprimir("Id Producto...;" + p.getId_prod());
			imprimir("Nombre...;" + p.getDes_prod());
			imprimir("Categoria...;" +
			p.getIdcategoria() + "-" + p.getCategoria().getDescripcion());
			imprimir("Proveedor...;" +
			p.getIdprovedor() + "-" + p.getProveedor().getNombre_rs());
			imprimir("-----------------------------------------------");
			}
		
			em.close();
		}
		
		
		
	void imprimir(String s) {
		txtSalida.append(s + "\n");}



	
	
	void buscar() {
		// leer el Código
		String codigo = leerCodigo();
		
		//buscar un Producto en el codigo indicado
		//Conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		Producto p = em.find(Producto.class,codigo);
		
		//si existe, muestra en los campos
		if (p !=null) {
			txtDescripcion.setText(p.getDes_prod());
			txtStock.setText(p.getStk_prod() + "");
			//Completar......
		}else {
			/// si no existe muestrs mensaje de error
			aviso("Código de producto no existe ","Aviso de sistema", JOptionPane.ERROR_MESSAGE);
			
		}
		em.close();
		//si no existe 
		
	}
}
