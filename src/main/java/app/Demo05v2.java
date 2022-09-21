package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05v2 {

	//eliminar logicamente cambiar el estado
	
	public static void main(String[] args) {
	 //Conexion
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
			EntityManager em = fabrica.createEntityManager();

			// obj de usuario a eliminar
			Usuario u = em.find(Usuario.class, 10);
			
			if (u == null) {

			System.out.println("Usuario no encontrado");

			} else {
			// proceso --> eliminar por codigo
			u.setEstado(2);
			em.getTransaction().begin();
			em.merge(u); // busca --> solo lo elimina si encuentra el objeto!!
			System.out.println("usuario deshabilitado");
			em.getTransaction().commit();
			}

					em.close();
		}
}
