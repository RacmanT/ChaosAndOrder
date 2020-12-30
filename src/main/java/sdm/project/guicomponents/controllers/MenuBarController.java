package sdm.project.guicomponents.controllers;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import sdm.project.guicomponents.windows.InstructionsWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {

    @FXML
    MenuBar menuBar;

    @FXML
    Menu fileMenu;
    @FXML
    MenuItem newGame;
    @FXML
    MenuItem exitGame;

    @FXML
    Menu helpMenu;
    @FXML
    MenuItem readInstructions;
    @FXML
    MenuItem about;

    @FXML
    Menu themeMenu;
    @FXML
    RadioMenuItem lightTheme;
    @FXML
    RadioMenuItem darkTheme;

    RootController rootController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        newGame.setOnAction(event -> rootController.newGame());
        exitGame.setOnAction(event -> Platform.exit());
//        menuBar.getStyleClass().add("menubar");

        lightTheme.setOnAction(event -> {
//            menuBar.getScene().getStylesheets().remove(getClass().getResource("/css/dark-theme.css").toExternalForm());
            System.out.println(menuBar.getScene().getStylesheets().remove(getClass().getResource("")));
            menuBar.getScene().getStylesheets().add("/css/light-theme.css");
//        scene.getStylesheets().add("/css/light-theme.css");

        });

        darkTheme.setOnAction(event -> {
            System.out.println(menuBar.getScene().getStylesheets().remove(menuBar.getScene().getStylesheets()));
//            menuBar.getScene().getStylesheets().remove(getClass().getResource("/css/light-theme.css").toExternalForm());
            menuBar.getScene().getStylesheets().add("/css/dark-theme.css");
        });

        readInstructions.setOnAction(event -> {
            InstructionsWindow instructionsWindow = new InstructionsWindow();
            instructionsWindow.show();
        });

        about.setOnAction(event -> {

            Parent root;
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/About.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                scene.getStylesheets().add("/css/light-theme.css");
                root.getScene().setOnMouseClicked(event1 -> stage.close());
            } catch (IOException e) {
                e.printStackTrace();
            }

            stage.show();
        });

    }

    public void injectRootController(RootController rootController) {
        this.rootController = rootController;
    }

}
