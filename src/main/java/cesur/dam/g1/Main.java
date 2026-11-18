package cesur.dam.g1;

import cesur.dam.g1.models.Equipo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        var cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();

        sf.inSession( (session -> {
            session.createQuery("From Equipo", Equipo.class)
                    .getResultList()
                    .forEach(System.out::println);
        }));

        try(Session session = sf.openSession()) {
            session.createQuery("From Equipo e where id>4 and id<8", Equipo.class)
                    .getResultList()
                    .forEach(System.out::println);
        }

        System.out.println("-------------------");

        sf.inSession( (session -> {
            Equipo e5 = session.find(Equipo.class, 5L);
            Equipo e6 = session.find(Equipo.class, 6L);
            Equipo e7 = session.find(Equipo.class, 7L);

            System.out.println(e5);
            System.out.println(e6);
            System.out.println(e7);
        }));

        System.out.println("-".repeat(80));


        sf.inTransaction((session) -> {
            Equipo e8 = session.find(Equipo.class, 8L);
            e8.setNombre("---"+e8.getNombre().toUpperCase()+"---");
        });

        System.out.println("-".repeat(80));

        Equipo e = new Equipo();
        e.setNombre("pepote");
        e.setCategoria("popi");
        e.setDisponible(true);

        sf.inTransaction((session) -> {
            Equipo e2 = session.find(Equipo.class, 2L);
            session.remove(e2);
        });


    }
}
