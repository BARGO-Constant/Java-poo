import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class WeddingPlannerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Application de Planification de Mariage");

        // Créer les éléments de l'interface
        Label welcomeLabel = new Label("Bienvenue dans l'application de planification de mariage !");
        Button manageWeddingsButton = new Button("Gérer les mariages");
        Button manageGuestsButton = new Button("Gérer les invités");
        Button manageVendorsButton = new Button("Gérer les fournisseurs");
        Button manageTasksButton = new Button("Gérer les tâches");

        // Configurer les actions des boutons
        manageWeddingsButton.setOnAction(e -> afficherGestionMariages());
        manageGuestsButton.setOnAction(e -> afficherGestionInvites());
        manageVendorsButton.setOnAction(e -> afficherGestionFournisseurs());
        manageTasksButton.setOnAction(e -> afficherGestionTaches());

        // Disposition verticale (VBox) pour organiser les éléments
        VBox layout = new VBox(15);
        layout.getChildren().addAll(welcomeLabel, manageWeddingsButton, manageGuestsButton, manageVendorsButton,
                manageTasksButton);

        // Style de la VBox
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #f0f0f0;");

        // Création de la scène principale
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode pour afficher la gestion des mariages
    private void afficherGestionMariages() {
        Stage stage = new Stage();
        stage.setTitle("Gestion des Mariages");
        Label label = new Label("Gérer les mariages ici (ajout, modification, suppression)");
        VBox layout = new VBox(10, label);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    // Méthode pour afficher la gestion des invités
    private void afficherGestionInvites() {
        Stage stage = new Stage();
        stage.setTitle("Gestion des Invités");
        Label label = new Label("Gérer les invités ici (ajout, modification, suppression)");
        VBox layout = new VBox(10, label);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    // Méthode pour afficher la gestion des fournisseurs
    private void afficherGestionFournisseurs() {
        Stage stage = new Stage();
        stage.setTitle("Gestion des Fournisseurs");
        Label label = new Label("Gérer les fournisseurs ici (ajout, modification, suppression)");
        VBox layout = new VBox(10, label);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    // Méthode pour afficher la gestion des tâches
    private void afficherGestionTaches() {
        Stage stage = new Stage();
        stage.setTitle("Gestion des Tâches");
        Label label = new Label("Gérer les tâches ici (ajout, modification, suppression)");
        VBox layout = new VBox(10, label);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
