package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	// Actualizar Usuario
		public static void main(String[] args) {
			
		//establecer  conexción
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
			
		//Proceso-> registrar, actualizar,eliminar---> Transacción
		em.getTransaction().begin();
		
		// objetos a actualizar
		Usuario u = new Usuario(20,"Angelica","zamora","Ag","2002","2002/05/27",2,1, null);
		em.merge(u);
		System.out.println("Usuario Actualizado");
		em.getTransaction().commit();
		em.close();
		
		}
}
