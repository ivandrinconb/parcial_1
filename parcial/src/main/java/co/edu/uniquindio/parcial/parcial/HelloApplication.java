package co.edu.uniquindio.parcial.parcial;

import co.edu.uniquindio.parcial.parcial.model.Empresa;
import co.edu.uniquindio.parcial.parcial.patrones.singleton.SistemaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Cargar los datos de la empresa en el Singleton al arrancar
        Empresa empresa = new Empresa("RentCar", "900123456-7", "Calle 10 #5-20",
                "6067400000", "contacto@rentcar.com", "www.rentcar.com");
        SistemaController.getInstance().setEmpresa(empresa);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("RentCar");
        stage.setScene(scene);
        stage.show();
    }
}