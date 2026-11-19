package cesur.dam.g1.ui;

import cesur.dam.g1.models.Equipo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EquipoApp extends Application {

    private SessionFactory sessionFactory;

    @Override
    public void start(Stage stage) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        TableView<Equipo> table = new TableView<>();

        TableColumn<Equipo, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Equipo, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Equipo, String> colCategoria = new TableColumn<>("Categoría");
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        TableColumn<Equipo, Boolean> colDisponible = new TableColumn<>("Disponible");
        colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        table.getColumns().add(colId);
        table.getColumns().add(colNombre);
        table.getColumns().add(colCategoria);
        table.getColumns().add(colDisponible);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        sessionFactory.inSession(session -> table.setItems(FXCollections.observableArrayList(
                session.createQuery("from Equipo", Equipo.class).getResultList())));

        stage.setTitle("Equipos - Hibernate + JavaFX");
        stage.setScene(new Scene(new StackPane(table), 700, 400));
        stage.show();
    }

    @Override
    public void stop() {
        if (sessionFactory != null) sessionFactory.close();
    }
}
