package eu.unareil.dal.jdbc;

import eu.unareil.bo.*;
import eu.unareil.dal.DALException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestJDBCImpl {
    public static void main(String[] args) throws DALException {
        System.out.println("\n===================================================== \n");
        System.out.println("TEST STYLO");
        StyloJDBCImpl styloJDBC = new StyloJDBCImpl();

        System.out.println("TEST ==>  INSERT Stylo ");
        Stylo styloBic = new Stylo("bic", "Oversize Ultimate", 150, 1.2f, "Beige", "Petite mine");
        styloJDBC.insert(styloBic);

        System.out.println("TEST ==> SELECT ALL Stylo ");
        List<Stylo> stylos = styloJDBC.selectAll();
        for (Stylo stylo : stylos) {System.out.println(stylo);}

        System.out.println("TEST ==> SELECT BY ID Stylo ");
        Stylo stylo2 = styloJDBC.selectById(27);
        System.out.println(styloJDBC.selectById(27));

        System.out.println("TEST ==> UPDATE Stylo ");
        stylo2.setLibelle("SuperMan BIC Heros Geniaux");
        styloJDBC.update(stylo2);

        System.out.println("TEST ==> DELETE Stylo ");
        Stylo stylo3 = styloJDBC.selectById(21);
//        styloJDBC.delete(stylo3);

        System.out.println("\n===================================================== \n");
        System.out.println("TEST GLACE");
        GlaceJDBCImpl glaceJDBC = new GlaceJDBCImpl();

        System.out.println("TEST ==>  INSERT Glace ");

        Glace glaceMiko = new Glace(LocalDate.now().plusYears(1),"miko","glace pousse",-15, "chocolat",120,0.20f);
        glaceJDBC.insert(glaceMiko);

        System.out.println("TEST ==> SELECT ALL Glace ");
        List<Glace> glaces = glaceJDBC.selectAll();
        for (Glace glace : glaces) {System.out.println(glace);}

        System.out.println("TEST ==> SELECT BY ID Glace ");
        Glace glace2 = glaceJDBC.selectById(40);
        System.out.println(glaceJDBC.selectById(40));

        System.out.println("TEST ==> UPDATE Glace ");
        glace2.setLibelle("Super Glace ");
        glaceJDBC.update(glace2);

        System.out.println("TEST ==> DELETE Glace ");
        Glace glace3 = glaceJDBC.selectById(40);
//        glaceJDBC.delete(glace3);

        System.out.println("\n===================================================== \n");
        System.out.println("TEST PAIN");
        PainJDBCImpl painJDBC = new PainJDBCImpl();

        System.out.println("TEST ==>  INSERT Pain ");

        Pain painTrad = new Pain("Mie Caline","Tradition",250,20,1.2f);
        painJDBC.insert(painTrad);

        System.out.println("TEST ==> SELECT ALL Pain ");
        List<Pain> pains = painJDBC.selectAll();
        for (Pain pain : pains) {System.out.println(pain);}

        System.out.println("TEST ==> SELECT BY ID Pain ");
        Pain pain2 = painJDBC.selectById(33);
        System.out.println(painJDBC.selectById(33));

        System.out.println("TEST ==> UPDATE Pain ");
        pain2.setLibelle("SuperMan BIC Heros Geniaux");
        painJDBC.update(pain2);

        System.out.println("TEST ==> DELETE Pain ");
        Pain pain3 = painJDBC.selectById(21);
//        painJDBC.delete(pain3);





    }
}

