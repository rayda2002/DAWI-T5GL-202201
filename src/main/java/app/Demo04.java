package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	
	//obtener todo los Datos de Usuario segun codigo
	public static void main(String[] args) {
		
		//establecer  conexción
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
					
		//Proceso-> Consulta--->Select * from tb_xx Where xxx objeto Usuario
		Usuario u = em.find(Usuario.class, 10);
		
		if (u == null) {
		System.out.println("usuario no encontrado");
		}else {
		
			System.out.println("usuario encontrado");
			System.out.println(u);
		
		}
			em.close();
		
	}

}
