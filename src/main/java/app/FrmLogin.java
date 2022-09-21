package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(41, 33, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clave");
		lblNewLabel_1.setBounds(41, 68, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(119, 30, 96, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(119, 65, 147, 20);
		contentPane.add(txtClave);
		
		JButton btnIngresar = new JButton("Ingrersar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresar();
			}

		});
		
		btnIngresar.setBounds(320, 44, 89, 23);
		contentPane.add(btnIngresar);
		
		
	}
		private void ingresar() {
	    // leer los campos
			String usuario = leerUsuario();
			String clave = leerClave();
			
		//Validacion
			
			
			
		// Optener un Usuario segun campos Usuario y clave
			//Conexion
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
			EntityManager em = fabrica.createEntityManager();
			Usuario u;
			try {
				TypedQuery<Usuario> consulta = em.createQuery("select u from Usuario u where u.usuario = :xusr and u.clave = :xpas", Usuario.class);
				consulta.setParameter("xusr", usuario);
				consulta.setParameter("xpas", clave);
				u = consulta.getSingleResult(); //Encaso de no encontrar el resultado lanza la excepcion
				aviso( "Bienvenido", "Mensaje del sistema",JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				u = null;
				aviso( "Usuario no Existe", "Mensaje del sistema",JOptionPane.ERROR_MESSAGE);
			}
			
}
		
		void aviso(String msg, String tit, int icono) {
			JOptionPane.showMessageDialog(this,msg, tit, icono);
		}

		private String leerClave() {
			// TODO Auto-generated method stub
			return String.valueOf(txtClave.getPassword());
		}

		private String leerUsuario() {
			// TODO Auto-generated method stub
			return txtUsuario.getText();
		}
}
