package eu.unareil.dal.jdbc;

import eu.unareil.bo.Stylo;
import eu.unareil.dal.DALException;

import java.util.List;

public class Test {
    public static void main(String[] args) throws DALException {
        StyloJDBCImpl styloJDBC = new StyloJDBCImpl();
        Stylo styloBic = new Stylo("bic", "Oversize Ultimate", 150, 1.2f, "Beige", "Petite mine");
//        styloJDBC.insert(styloBic);

        List<Stylo> stylos = styloJDBC.selectAll();
        for (Stylo stylo : stylos
        ) {
            System.out.println(stylo);
        }

        System.out.println(styloJDBC.selectById(12));
//        styloJDBC.delete(stylos.get(0));

        Stylo stylo2 = styloJDBC.selectById(12);
        stylo2.setLibelle("SuperMan BIC Heros Geniaux");


        styloJDBC.update(stylo2);
    }
}

