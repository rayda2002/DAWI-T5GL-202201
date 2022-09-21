package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	
	//Eliminar Usuario
	public static void main(String[] args) {
		
		//establecer  conexción
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
			
		//Proceso-> registrar, actualizar,eliminar---> Transacción
		em.getTransaction().begin();
		
		// objetos a Eliminar
		Usuario u = new Usuario();
		u.setCodigo(20);
		em.remove(u); // busca---> Solo elimina si encientra el objeto!!
		System.out.println("Usuario Eliminado");
		em.getTransaction().commit();
		em.close();
	}

}
