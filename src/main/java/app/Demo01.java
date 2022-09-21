package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
	
	// registrar los datos de un nuevo usuario
	public static void main(String[] args) {
		
		//establecer  vonexion con la unidad de persistencia 
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//cea el manejo de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso-> registrar, actualizar,eliminar---> Transacción
		em.getTransaction().begin();
		
		// objetos a grabar
		Usuario u = new Usuario(10,"Rayda","Pillaca","Angel","1234","2002/06/27",1,1, null);
		em.persist(u);
		
		System.out.println("Gabación Ok");
		
		//Confirmar Transancción 
		em.getTransaction().commit();
		
		//Cerrar la conexion
		em.close();
		}
	

}
