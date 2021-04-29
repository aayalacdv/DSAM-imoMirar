package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Covid19ManagerTest {

    private static Covid19ManagerImpl manager;

    @BeforeAll
    public static void setUp(){
       manager = Covid19ManagerImpl.getInstance();
       manager.setUpResources();
    }

    @Test
    public void testAplicarVacuna(){
        Usuario u = new Usuario("Rico");
        try{
            manager.aplicarVacuna("cubana",u.getNombre(),"2020/6/7");
            manager.aplicarVacuna("añklsdf",u.getId(),"2020/8/8");
        }catch (Exception e){

        }
        //se ha añadido la vacuna
        assertEquals(4,manager.getVacunasList().size());
        //se ha añadido el usuario porque no estaba
        assertEquals(4,manager.getUsuarios().size());

    }

    @Test
    public void testHacerSeguimiento(){
        List<String> l = new ArrayList<>();
        for( String s : manager.getUsuarios().keySet()){
            l.add(s);
        }

        String fecha = "2020/6/7";
        String comentario = "Comer mucha verdura, está malo de los pulmones";
        manager.hacerSeguimiento(l.get(0),fecha,comentario);

        Usuario u = manager.getUsuarioById(l.get(0));
        assertEquals(1,u.getSeguimientos().size());
    }

    @AfterAll
    public static void tearDown(){
        manager.tearDownResources();
    }

}
