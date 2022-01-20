package viewcontroller;

import additions.*;
import model.Othello;
import model.OthelloBoard;
import undoredo.CommandInvoker;
import undoredo.UndoRedoEventHandler;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put
	// --module-path "/usr/share/openjfx/lib" --add-modules
	// javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws Exception {
		// Create and hook up the Model, View and the controller

		// MODEL
		Othello othello = new Othello();
		Othello othelloCopy = othello.copy();

		// CONTROLLER
		// CONTROLLER->MODEL hookup
		CommandInvoker invoker = new CommandInvoker();
		OthelloTimer ot = new OthelloTimer(OthelloBoard.P1);
		OthelloTimer ot2 = new OthelloTimer(OthelloBoard.P2);
		GameStatus status = new GameStatus(othello, ot, ot2);
		status.setPrefWidth(100);
		status.setPrefHeight(70);
		othello.attach(status);
		OthelloTimerEventHandler otehp1 = new OthelloTimerEventHandler(othello, ot, status);
		OthelloTimerEventHandler otehp2 = new OthelloTimerEventHandler(othello, ot2, status);
		OthelloChooseTime octp1 = new OthelloChooseTime();
		OthelloChooseTime octp2 = new OthelloChooseTime();
		OthelloFeedbackLabel ofl = new OthelloFeedbackLabel();
		OthelloDefaultTimeButton odtb = new OthelloDefaultTimeButton();
		OthelloDefaultTimeButtonEventHandler odtbeh = new OthelloDefaultTimeButtonEventHandler(odtb, octp1, octp2,
				otehp1, otehp2, ofl);
		OthelloChooseTimeEventHandler octehp1 = new OthelloChooseTimeEventHandler(octp1, octp2, ot, otehp1, otehp2, ofl, odtb);
		OthelloChooseTimeEventHandler octehp2 = new OthelloChooseTimeEventHandler(octp2, octp1, ot2, otehp2, otehp1, ofl, odtb);
		OthelloButtonEventHandler obeh = new OthelloButtonEventHandler(othello, invoker, octp1, octp2, otehp1, otehp2,
				ofl, ot, ot2);
		OthelloRestartButtonEventHandler obreh = new OthelloRestartButtonEventHandler(othello, ot, ot2, otehp1, otehp2, invoker, octp1, octp2, odtb);
		UndoRedoEventHandler ureh = new UndoRedoEventHandler(othello, ot, ot2, otehp1, otehp2, invoker);
		// VIEW
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup

		OthelloButton[][] gridPaneNodes = new OthelloButton[8][8];
		GridPane grid = new GridPane();
		for (int row = 0; row <= 7; row++) {
			for (int col = 0; col <= 7; col++) {
				OthelloButton button = new OthelloButton("  ", row, col);
				char move = othello.getWhosTurn();
				// if((row == col) && (row == 3 || row == 4))button.setText("X");
				if ((row == col) && (row == 3 || row == 4)) {
					button.setPreviousToken('X');
					button.setStyle(
							"-fx-background-color: #000000;" + "-fx-background-radius: 25em; " + "-fx-min-width: 25px; "
									+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
				} else if ((row == 3 && col == 4) || (row == 4 && col == 3)) {
					button.setPreviousToken('O');
					button.setStyle("-fx-border-color: black;" + "-fx-border: 12px;" + "-fx-faint-focus-color: black;"
							+ "-fx-focus-color: black;" + "-fx-background-color: #ffffff;" + "-fx-border-radius: 25em; "
							+ "-fx-min-width: 25px; " + "-fx-min-height: 25px; " + "-fx-max-width: 25px; "
							+ "-fx-max-height: 25px;");
				}

				else if (othelloCopy.move(row, col)) {
					button.setStyle(
							"-fx-border-color: #808080;" + "-fx-background-color: #ffcccb;" + "-fx-min-width: 25px; "
									+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
					othelloCopy = othello.copy();
				}

				gridPaneNodes[col][row] = button;
				button.setOnAction(obeh);
				// MODEL->VIEW hookup
				othello.attach(button);
				grid.add(button, col, row);
			}

		}

		grid.setHgap(1);
		grid.setVgap(1);

		// Player type TextField
		OthelloPlayerTypeTextField optt = new OthelloPlayerTypeTextField();
		optt.setPrefWidth(100);
		optt.setPrefHeight(70);
		othello.attach(optt);

		// Hint Checkboxes
		final ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Greedy Hint");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("Random Hint");
		rb2.setToggleGroup(group);
		RadioButton rb3 = new RadioButton("Advanced Hint");
		rb3.setToggleGroup(group);
		RadioButton none = new RadioButton("No Hint");
		none.setToggleGroup(group);

		// Hint event handler
        HintHandler hints = new HintHandler(othello, gridPaneNodes);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

				if (group.getSelectedToggle() != null) {

					if (rb1.isSelected()) {
					    hints.hintType = "Greedy Hint";
                    }
					else if (rb2.isSelected()) {
                        hints.hintType = "Random Hint";
                    }
					else if (rb3.isSelected()) {
						hints.hintType = "Advanced Hint";
					}
					else {
                        hints.hintType = "No Hint";
                    }
                    hints.showHint();
				}

			}
		});

		VBox hintPanel = new VBox();
		hintPanel.getChildren().add(rb1);
		hintPanel.getChildren().add(rb2);
		hintPanel.getChildren().add(rb3);
		hintPanel.getChildren().add(none);

		HBox legend = new HBox();

		VBox legendButtons = new VBox();
		legendButtons.setPadding(new Insets(4));
		Button b1 = new Button();
		b1.setStyle("-fx-border-color: #ffcccb;" + "-fx-background-color: #ffcccb;" + "-fx-min-width: 25px; "
						+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
		Button b2 = new Button();
		b2.setStyle("-fx-border-color: #00ff00;" + "-fx-background-color: #00ff00;" + "-fx-min-width: 25px; "
				+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
		Button b3 = new Button();
		b3.setStyle("-fx-border-color: #00ccff;" + "-fx-background-color: #00ccff;" + "-fx-min-width: 25px; "
				+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
		Button b4 = new Button();
		b4.setStyle("-fx-border-color: #ff00ff;" + "-fx-background-color: #ff00ff;" + "-fx-min-width: 25px; "
				+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");

		legendButtons.getChildren().addAll(b1, b2, b3, b4);

		VBox legendDetails = new VBox();
		legendDetails.setSpacing(9);
		Label detail1 = new Label("Valid moves");
		Label detail2 = new Label("Greedy hint");
		Label detail3 = new Label("Random hint");
		Label detail4 = new Label("Advanced hint");

		legendDetails.getChildren().addAll(detail1, detail2, detail3, detail4);

		legend.getChildren().addAll(legendButtons, legendDetails);

		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.getChildren().addAll(status, optt, legend);
		// Mode buttons
		OthelloModeButton ohvab = new OthelloModeButton("Advanced");
		OthelloModeButton ohvhb = new OthelloModeButton("Human");
		OthelloModeButton ohvgb = new OthelloModeButton("Greedy");
		OthelloModeButton ohvrb = new OthelloModeButton("Random");

		// Mode buttons event handlers
		OthelloModeButtonEventHandler ohvheh = new OthelloModeButtonEventHandler(othello, optt);
		OthelloModeButtonEventHandler ohvgeh = new OthelloModeButtonEventHandler(othello, optt);
		OthelloModeButtonEventHandler ohvreh = new OthelloModeButtonEventHandler(othello, optt);
		OthelloModeButtonEventHandler ohvaeh = new OthelloModeButtonEventHandler(othello, optt);

		// Mode buttons HBox
		HBox hbox2 = new HBox();
		hbox.setPadding(new Insets(4));
		hbox2.getChildren().add(ohvhb);
		hbox2.getChildren().add(ohvrb);
		hbox2.getChildren().add(ohvgb);
		hbox2.getChildren().add(ohvab);

		ohvhb.setOnAction(ohvheh);
		ohvgb.setOnAction(ohvgeh);
		ohvrb.setOnAction(ohvreh);
		ohvab.setOnAction(ohvaeh);

		// Restart/Undo
		VBox vbox3 = new VBox();
		vbox3.setPadding(new Insets(3));
		Button restart = new Button("Restart");
		restart.setOnAction(obreh);
		Button undo = new Button("Undo");
		undo.setOnAction(ureh);
		Button redo = new Button("Redo");
		redo.setOnAction(ureh);
		
		vbox3.getChildren().add(restart);
		vbox3.getChildren().add(undo);
		vbox3.getChildren().add(redo);

		grid.getChildren().add(ot);
		grid.getChildren().add(ot2);

		octp1.setOnKeyPressed(octehp1);
		octp2.setOnKeyPressed(octehp2);
		
		odtb.setOnAction(odtbeh);

		VBox vbox4 = new VBox();
		vbox4.setPadding(new Insets(3));
		vbox4.getChildren().add(ot);
		vbox4.getChildren().add(ot2);
		vbox4.getChildren().add(octp1);
		vbox4.getChildren().add(octp2);
		vbox4.getChildren().add(odtb);
		vbox4.getChildren().add(ofl);

		// Pane Setup
		BorderPane border = new BorderPane();

		border.setCenter(grid);
		border.setBottom(hbox2);

		BorderPane vborder = new BorderPane();
		vborder.setTop(hintPanel);
		vborder.setBottom(vbox3);

		border.setLeft(vbox4);
		border.setRight(vborder);
		border.setTop(hbox);

		// SCENE
		Scene scene = new Scene(border);
		stage.setTitle("Othello");
		stage.setScene(scene);

		// LAUNCH THE GUI
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}