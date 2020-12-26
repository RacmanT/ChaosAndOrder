package it.units.sdm.project.gui.controllers;

import it.units.sdm.project.entities.Board;
import it.units.sdm.project.entities.Move;
import it.units.sdm.project.entities.Symbol;
import it.units.sdm.project.exceptions.TakenCellException;
import it.units.sdm.project.gui.imageviews.CircleImageView;
import it.units.sdm.project.gui.imageviews.CrossImageView;
import it.units.sdm.project.gui.windows.EndGameWindow;
import it.units.sdm.project.utils.WinnerChecker;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardController implements Initializable {

    @FXML
    GridPane board;

    RootController rootController;
    Set<StackPane> cells = new HashSet<>(36);
    MediaPlayer mediaPlayer;
    ScaleTransition animation;

    {
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/sounds/pop.mp3").toString()));

        animation = new ScaleTransition(Duration.seconds(0.3));
        animation.setFromX(0);
        animation.setFromY(0);
        animation.setToX(1);
        animation.setToY(1);
    }

    public GridPane getBoard() {
        return board;
    }

    public void injectRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        board.getStyleClass().add("grid");

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                StackPane cell = new StackPane();
                GridPane.setRowIndex(cell, i);
                GridPane.setColumnIndex(cell, j);
                cell.setPrefHeight(80);
                cell.setPrefWidth(80);
                cell.getStyleClass().add("cell");
                cell.setOnMouseClicked(event -> {
                    try {
                        makeMove(cell, rootController.getSymbol());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
                board.add(cell, i, j);
                cells.add(cell);
            }
        }

    }

    private void makeMove(StackPane cell, Symbol symbol) throws TakenCellException {

        ImageView imageView = (symbol == Symbol.CIRCLE) ? new CircleImageView() : new CrossImageView();

        animation.setNode(imageView);
        mediaPlayer.seek(Duration.ZERO);

        mediaPlayer.play();
        animation.play();

        cell.getChildren().add(imageView);
        cell.setDisable(true);
        rootController.logicBoard.insert(new Move(board.getRowIndex(cell), board.getColumnIndex(cell), symbol));


        if (WinnerChecker.getWinnerRole(rootController.logicBoard) != null) {
            EndGameWindow endGameWindow = new EndGameWindow(WinnerChecker.getWinnerRole(rootController.logicBoard));
            endGameWindow.show();
        }

        rootController.changePlayer();
    }

    public void toEmptyBoard() {

        cells = cells.stream().peek(cell -> {
            cell.getChildren().removeAll(cell.getChildren());
        }).collect(Collectors.toSet());



        System.out.println("[BoardController]: fired");
    }

}
