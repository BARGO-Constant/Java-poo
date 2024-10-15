module com.example.projetjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projetjava to javafx.fxml;
    exports com.example.projetjava;
}