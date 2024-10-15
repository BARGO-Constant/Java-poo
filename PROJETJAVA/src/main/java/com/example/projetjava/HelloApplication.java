package com.example.projetjava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private static final String SECTION_BACKGROUND_COLOR = "#ffff";
    private List<Mariage> mariages = new ArrayList<>();
    private List<Invite> invites = new ArrayList<>();
    private List<Fournisseur> fournisseurs = new ArrayList<>();
    private List<Tache> taches = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Application de Planification de Mariage");

        Label welcomeLabel = new Label("Bienvenue dans l'application de planification de mariage !");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        welcomeLabel.setTextFill(Color.DARKBLUE);

        Button manageWeddingsButton = createStyledButton("Gérer les mariages");
        Button manageGuestsButton = createStyledButton("Gérer les invités");
        Button manageVendorsButton = createStyledButton("Gérer les fournisseurs");
        Button manageTasksButton = createStyledButton("Gérer les tâches");

        manageWeddingsButton.setOnAction(e -> afficherGestionMariages());
        manageGuestsButton.setOnAction(e -> afficherGestionInvites());
        manageVendorsButton.setOnAction(e -> afficherGestionFournisseurs());
        manageTasksButton.setOnAction(e -> afficherGestionTaches());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(welcomeLabel, manageWeddingsButton, manageGuestsButton, manageVendorsButton, manageTasksButton);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center; -fx-background-color: linear-gradient(to bottom right, #f0f8ff, #e6e6fa);");

        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;"));
        return button;
    }

    private void afficherGestionTaches() {
        Stage stage = new Stage();
        stage.setTitle("Gestion des Tâches");


        ListView<Tache> tacheListView = new ListView<>();
        tacheListView.getItems().addAll(taches);
        tacheListView.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #ddd; -fx-border-width: 1px;");

        Button ajouterButton = createStyledButton("Ajouter une Tâche");
        Button modifierButton = createStyledButton("Modifier la Tâche");
        Button supprimerButton = createStyledButton("Supprimer la Tâche");

        ajouterButton.setOnAction(e -> afficherFormulaireAjoutTache(tacheListView));
        modifierButton.setOnAction(e -> {
            Tache selectedTache = tacheListView.getSelectionModel().getSelectedItem();
            if (selectedTache != null) {
                afficherFormulaireModificationTache(selectedTache, tacheListView);
            } else {
                showAlert("Aucune tâche sélectionnée", "Veuillez sélectionner une tâche à modifier.");
            }
        });
        supprimerButton.setOnAction(e -> {
            Tache selectedTache = tacheListView.getSelectionModel().getSelectedItem();
            if (selectedTache != null) {
                taches.remove(selectedTache);
                tacheListView.getItems().remove(selectedTache);
            } else {
                showAlert("Aucune tâche sélectionnée", "Veuillez sélectionner une tâche à supprimer.");
            }
        });

        HBox buttonBox = new HBox(10, ajouterButton, modifierButton, supprimerButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15);
        layout.getChildren().addAll( tacheListView, buttonBox);
        layout.setStyle("-fx-padding: 20; -fx-background-color: " + SECTION_BACKGROUND_COLOR + ";");
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void afficherFormulaireAjoutTache(ListView<Tache> tacheListView) {
        Stage stage = new Stage();
        stage.setTitle("Ajouter une Tâche");

        TextField nomField = new TextField();
        nomField.setPromptText("Nom de la tâche");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description de la tâche");
        DatePicker dateLimite = new DatePicker();
        dateLimite.setPromptText("Date limite");

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            Tache nouvelleTache = new Tache(nomField.getText(), descriptionField.getText(), dateLimite.getValue());
            taches.add(nouvelleTache);
            tacheListView.getItems().add(nouvelleTache);
            stage.close();
        });

        VBox layout = new VBox(10, nomField, descriptionField, dateLimite, saveButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void afficherFormulaireModificationTache(Tache tache, ListView<Tache> tacheListView) {
        Stage stage = new Stage();
        stage.setTitle("Modifier une Tâche");

        TextField nomField = new TextField(tache.getNom());
        TextField descriptionField = new TextField(tache.getDescription());
        DatePicker dateLimite = new DatePicker(tache.getDateLimite());

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            tache.setNom(nomField.getText());
            tache.setDescription(descriptionField.getText());
            tache.setDateLimite(dateLimite.getValue());
            tacheListView.refresh();
            stage.close();
        });

        VBox layout = new VBox(10, nomField, descriptionField, dateLimite, saveButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void afficherGestionFournisseurs() {
        Stage stage = new Stage();
        stage.setTitle("Gestion des Fournisseurs");

        ListView<Fournisseur> fournisseurListView = new ListView<>();
        fournisseurListView.getItems().addAll(fournisseurs);
        fournisseurListView.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #ddd; -fx-border-width: 1px;");

        Button ajouterButton = createStyledButton("Ajouter un Fournisseur");
        Button modifierButton = createStyledButton("Modifier le Fournisseur");
        Button supprimerButton = createStyledButton("Supprimer le Fournisseur");


        ajouterButton.setOnAction(e -> afficherFormulaireAjoutFournisseur(fournisseurListView));
        modifierButton.setOnAction(e -> {
            Fournisseur selectedFournisseur = fournisseurListView.getSelectionModel().getSelectedItem();
            if (selectedFournisseur != null) {
                afficherFormulaireModificationFournisseur(selectedFournisseur, fournisseurListView);
            } else {
                showAlert("Aucun fournisseur sélectionné", "Veuillez sélectionner un fournisseur à modifier.");
            }
        });
        supprimerButton.setOnAction(e -> {
            Fournisseur selectedFournisseur = fournisseurListView.getSelectionModel().getSelectedItem();
            if (selectedFournisseur != null) {
                fournisseurs.remove(selectedFournisseur);
                fournisseurListView.getItems().remove(selectedFournisseur);
            } else {
                showAlert("Aucun fournisseur sélectionné", "Veuillez sélectionner un fournisseur à supprimer.");
            }
        });

        HBox buttonBox = new HBox(10, ajouterButton, modifierButton, supprimerButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15);
        layout.getChildren().addAll( fournisseurListView, buttonBox);
        layout.setStyle("-fx-padding: 20; -fx-background-color: " + SECTION_BACKGROUND_COLOR + ";");
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void afficherFormulaireAjoutFournisseur(ListView<Fournisseur> fournisseurListView) {
        Stage stage = new Stage();
        stage.setTitle("Ajouter un Fournisseur");

        TextField nomField = new TextField();
        nomField.setPromptText("Nom du fournisseur");
        TextField serviceField = new TextField();
        serviceField.setPromptText("Service fourni");
        TextField contactField = new TextField();
        contactField.setPromptText("Contact");

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            Fournisseur nouveauFournisseur = new Fournisseur(nomField.getText(), serviceField.getText(), contactField.getText());
            fournisseurs.add(nouveauFournisseur);
            fournisseurListView.getItems().add(nouveauFournisseur);
            stage.close();
        });

        VBox layout = new VBox(10, nomField, serviceField, contactField, saveButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void afficherFormulaireModificationFournisseur(Fournisseur fournisseur, ListView<Fournisseur> fournisseurListView) {
        Stage stage = new Stage();
        stage.setTitle("Modifier un Fournisseur");

        TextField nomField = new TextField(fournisseur.getNom());
        TextField serviceField = new TextField(fournisseur.getService());
        TextField contactField = new TextField(fournisseur.getContact());

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            fournisseur.setNom(nomField.getText());
            fournisseur.setService(serviceField.getText());
            fournisseur.setContact(contactField.getText());
            fournisseurListView.refresh();
            stage.close();
        });

        VBox layout = new VBox(10, nomField, serviceField, contactField, saveButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void afficherGestionInvites() {
        Stage stage = new Stage();
        stage.setTitle("Gestion des Invités");

        ListView<Invite> inviteListView = new ListView<>();
        inviteListView.getItems().addAll(invites);
        inviteListView.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #ddd; -fx-border-width: 1px;");

        Button ajouterButton = createStyledButton("Ajouter un Invité");
        Button modifierButton = createStyledButton("Modifier l'Invité");
        Button envoyerInvitationButton = createStyledButton("Envoyer une Invitation");
        Button supprimerButton = createStyledButton("Supprimer l'Invité");


        ajouterButton.setOnAction(e -> afficherFormulaireAjoutInvite(inviteListView));
        modifierButton.setOnAction(e -> {
            Invite selectedInvite = inviteListView.getSelectionModel().getSelectedItem();
            if (selectedInvite != null) {
                afficherFormulaireModificationInvite(selectedInvite, inviteListView);
            } else {
                showAlert("Aucun invité sélectionné", "Veuillez sélectionner un invité à modifier.");
            }
        });
        envoyerInvitationButton.setOnAction(e -> {
            Invite selectedInvite = inviteListView.getSelectionModel().getSelectedItem();
            if (selectedInvite != null) {
                envoyerLienInvitation(selectedInvite);
            } else {
                showAlert("Aucun invité sélectionné", "Veuillez sélectionner un invité pour envoyer une invitation.");
            }
        });
        supprimerButton.setOnAction(e -> {
            Invite selectedInvite = inviteListView.getSelectionModel().getSelectedItem();
            if (selectedInvite != null) {
                invites.remove(selectedInvite);
                inviteListView.getItems().remove(selectedInvite);
            } else {
                showAlert("Aucun invité sélectionné", "Veuillez sélectionner un invité à supprimer.");
            }
        });

        HBox buttonBox = new HBox(10, ajouterButton, modifierButton, envoyerInvitationButton, supprimerButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15);
        layout.getChildren().addAll( inviteListView, buttonBox);
        layout.setStyle("-fx-padding: 20; -fx-background-color: " + SECTION_BACKGROUND_COLOR + ";");
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 700, 500);  // Légèrement plus large pour accommoder les 4 boutons
        stage.setScene(scene);
        stage.show();
    }

    private void afficherFormulaireAjoutInvite(ListView<Invite> inviteListView) {
        Stage stage = new Stage();
        stage.setTitle("Ajouter un Invité");

        TextField nomField = new TextField();
        nomField.setPromptText("Nom de l'invité");
        TextField emailField = new TextField();
        emailField.setPromptText("Email de l'invité");

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            Invite nouvelInvite = new Invite(nomField.getText(), emailField.getText());
            invites.add(nouvelInvite);
            inviteListView.getItems().add(nouvelInvite);
            stage.close();
        });

        VBox layout = new VBox(10, nomField, emailField, saveButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void afficherFormulaireModificationInvite(Invite invite, ListView<Invite> inviteListView) {
        Stage stage = new Stage();
        stage.setTitle("Modifier un Invité");

        TextField nomField = new TextField(invite.getNom());
        TextField emailField = new TextField(invite.getEmail());

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            invite.setNom(nomField.getText());
            invite.setEmail(emailField.getText());
            inviteListView.refresh();
            stage.close();
        });

        VBox layout = new VBox(10, nomField, emailField, saveButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void envoyerLienInvitation(Invite invite) {
        String emailInvite = invite.getEmail();
        if (emailInvite != null && !emailInvite.isEmpty()) {
            String lienInvitation = "https://weddingplanner.com/invite?id=" + genererIdentifiantUnique(invite.getNom());
            String message = "Bonjour " + invite.getNom() + ",\n\n" +
                    "Vous êtes invité à notre mariage ! Veuillez confirmer votre présence et mettre à jour vos informations en cliquant sur le lien suivant : " +
                    lienInvitation;
            EmailService.sendEmail(emailInvite, "Invitation à notre mariage", message);
            showAlert("Invitation envoyée", "L'invitation a été envoyée à " + invite.getNom() + " avec succès.");
        } else {
            showAlert("Erreur", "L'email de l'invité n'est pas valide.");
        }
    }

    private String genererIdentifiantUnique(String nomInvite) {
        long timestamp = System.currentTimeMillis();
        return nomInvite.toLowerCase().replace(" ", "_") + "_" + timestamp;
    }

    private void afficherGestionMariages() {
        Stage stage = new Stage();
        stage.setTitle("Gestion des Mariages");

        ListView<Mariage> mariageListView = new ListView<>();
        mariageListView.getItems().addAll(mariages);
        mariageListView.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #ddd; -fx-border-width: 1px;");


        Button ajouterButton = createStyledButton("Ajouter un Mariage");
        Button modifierButton = createStyledButton("Modifier le Mariage");
        Button supprimerButton = createStyledButton("Supprimer le Mariage");

        ajouterButton.setOnAction(e -> afficherFormulaireAjout(mariageListView));
        modifierButton.setOnAction(e -> {
            Mariage selectedMarriage = mariageListView.getSelectionModel().getSelectedItem();
            if (selectedMarriage != null) {
                afficherFormulaireModification(selectedMarriage, mariageListView);
            } else {
                showAlert("Aucun mariage sélectionné", "Veuillez sélectionner un mariage à modifier.");
            }
        });
        supprimerButton.setOnAction(e -> {
            Mariage selectedMarriage = mariageListView.getSelectionModel().getSelectedItem();
            if (selectedMarriage != null) {
                mariages.remove(selectedMarriage);
                mariageListView.getItems().remove(selectedMarriage);
            } else {
                showAlert("Aucun mariage sélectionné", "Veuillez sélectionner un mariage à supprimer.");
            }
        });

        HBox buttonBox = new HBox(10, ajouterButton, modifierButton, supprimerButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15);
        layout.getChildren().addAll( mariageListView, buttonBox);
        layout.setStyle("-fx-padding: 20; -fx-background-color: " + SECTION_BACKGROUND_COLOR + ";");
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void afficherFormulaireAjout(ListView<Mariage> mariageListView) {
        Stage stage = new Stage();
        stage.setTitle("Ajouter un Mariage");

        TextField nomMarie1Field = new TextField();
        nomMarie1Field.setPromptText("Nom du premier marié");
        TextField nomMarie2Field = new TextField();
        nomMarie2Field.setPromptText("Nom du second marié");
        DatePicker datePicker = new DatePicker();
        TextField lieuField = new TextField();
        lieuField.setPromptText("Lieu du mariage");
        TextField budgetField = new TextField();
        budgetField.setPromptText("Budget du mariage");

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            try {
                LocalDate date = datePicker.getValue();
                String nomMarie1 = nomMarie1Field.getText();
                String nomMarie2 = nomMarie2Field.getText();
                String lieu = lieuField.getText();
                double budget = Double.parseDouble(budgetField.getText());
                Mariage mariage = new Mariage(nomMarie1, nomMarie2, date, lieu, budget);
                mariages.add(mariage);
                mariageListView.getItems().add(mariage);
                stage.close();
            } catch (NumberFormatException ex) {
                showAlert("Erreur", "Le budget doit être un nombre valide.");
            }
        });

        VBox layout = new VBox(10, nomMarie1Field, nomMarie2Field, datePicker, lieuField, budgetField, saveButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 300));
        stage.show();
    }

    private void afficherFormulaireModification(Mariage mariage, ListView<Mariage> mariageListView) {
        Stage stage = new Stage();
        stage.setTitle("Modifier un Mariage");

        TextField nomMarie1Field = new TextField(mariage.getNomMarie1());
        TextField nomMarie2Field = new TextField(mariage.getNomMarie2());
        DatePicker datePicker = new DatePicker(mariage.getDate());
        TextField lieuField = new TextField(mariage.getLieu());
        TextField budgetField = new TextField(String.valueOf(mariage.getBudget()));

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            try {
                mariage.setNomMarie1(nomMarie1Field.getText());
                mariage.setNomMarie2(nomMarie2Field.getText());
                mariage.setDate(datePicker.getValue());
                mariage.setLieu(lieuField.getText());
                mariage.setBudget(Double.parseDouble(budgetField.getText()));
                mariageListView.refresh();
                stage.close();
            } catch (NumberFormatException ex) {
                showAlert("Erreur", "Le budget doit être un nombre valide.");
            }
        });

        VBox layout = new VBox(10, nomMarie1Field, nomMarie2Field, datePicker, lieuField, budgetField, saveButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 300, 300));
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}

class Mariage {
    private String nomMarie1;
    private String nomMarie2;
    private LocalDate date;
    private String lieu;
    private double budget;

    public Mariage(String nomMarie1, String nomMarie2, LocalDate date, String lieu, double budget) {
        this.nomMarie1 = nomMarie1;
        this.nomMarie2 = nomMarie2;
        this.date = date;
        this.lieu = lieu;
        this.budget = budget;
    }

    // Getters et setters
    public String getNomMarie1() { return nomMarie1; }
    public void setNomMarie1(String nomMarie1) { this.nomMarie1 = nomMarie1; }
    public String getNomMarie2() { return nomMarie2; }
    public void setNomMarie2(String nomMarie2) { this.nomMarie2 = nomMarie2; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    @Override
    public String toString() {
        return "Mariage de " + nomMarie1 + " & " + nomMarie2 + " à " + lieu + " le " + date;
    }
}

class Invite {
    private String nom;
    private String email;

    public Invite(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return nom + " (" + email + ")";
    }
}

class Fournisseur {
    private String nom;
    private String service;
    private String contact;

    public Fournisseur(String nom, String service, String contact) {
        this.nom = nom;
        this.service = service;
        this.contact = contact;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    @Override
    public String toString() {
        return nom + " - " + service + " (" + contact + ")";
    }
}

class Tache {
    private String nom;
    private String description;
    private LocalDate dateLimite;

    public Tache(String nom, String description, LocalDate dateLimite) {
        this.nom = nom;
        this.description = description;
        this.dateLimite = dateLimite;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDateLimite() { return dateLimite; }
    public void setDateLimite(LocalDate dateLimite) { this.dateLimite = dateLimite; }

    @Override
    public String toString() {
        return nom + " (Date limite : " + dateLimite + ")";
    }
}

class EmailService {
    public static void sendEmail(String recipient, String subject, String message) {
        // Logique d'envoi d'email (à implémenter)
        System.out.println("Email envoyé à " + recipient + " avec le sujet '" + subject + "' et le message : " + message);
    }
}