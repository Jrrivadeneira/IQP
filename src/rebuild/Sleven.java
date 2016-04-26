package rebuild;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Sleven extends Application {

	/**
	 * Copyright
	 */

	private Stage window;
	private boolean enableSave = true;
	private boolean restrictUsage = false;
	private boolean alive = true;
	private Scene runNumberScene, previousRunsScene, actionScene;

	private Button previousRuns;
	private Button buttonSettings = new Button("Settings");

	// runNumberScene
	private Button runNumberEnter, runNumberBackspace, runNumberClear,
			runNumberHome;
	private Button[] numberButtons = { new Button("1"), new Button("4"),
			new Button("7"), new Button("2"), new Button("5"), new Button("8"),
			new Button("3"), new Button("6"), new Button("9"), new Button("0"), };

	// Action Screen
	private Button finishRun;
	private TextField runNumberField = new TextField();
	private String runNumber;
	private int timeAdjustment = 0;
	private String[] actionButtonLabels = { "Monitor", "12 lead", "Oxygen",
			"Patient in EA", "NTG", "CPR", "IV", "Intubation", "Asprin",
			"Oral Meds", "IV Meds", "Epinephrine", "Splinting", "Morphine",
			"Benadryl", "Vitals" };
	//
	private Button[] actionButtons = { new Button(actionButtonLabels[0]),
			new Button(actionButtonLabels[1]),
			new Button(actionButtonLabels[2]),
			new Button(actionButtonLabels[3]),
			new Button(actionButtonLabels[4]),
			new Button(actionButtonLabels[5]),
			new Button(actionButtonLabels[6]),
			new Button(actionButtonLabels[7]),
			new Button(actionButtonLabels[8]),
			new Button(actionButtonLabels[9]),
			new Button(actionButtonLabels[10]),
			new Button(actionButtonLabels[11]),
			new Button(actionButtonLabels[12]),
			new Button(actionButtonLabels[13]),
			new Button(actionButtonLabels[14]),
			new Button(actionButtonLabels[15]) };;
	private Button RunNumberButton;

	// Patient Data
	private Label weight;
	private int weightMeasurement = 0;

	private ListView<String> listOfPreviousRuns;
	private ListView<String> actionList;
	private final Font large = new Font(72);
	private final Font medium = new Font(36);

	private void correctButtonsState() {
		new Thread() {
			public void run() {
				for (Button b : actionButtons)
					b.setDisable(true);
				finishRun.setDisable(true);
				while ((runNumber == null || runNumber.equals("")) && alive)
					try {
						Thread.sleep(100);
					} catch (Exception ex) {
					}
				for (Button b : actionButtons)
					b.setDisable(false);
				finishRun.setDisable(false);
			}
		}.start();
	}

	private ChangeListener<String> dateFormatter = new ChangeListener<String>() {
		public void changed(ObservableValue<? extends String> arg0,
				String arg1, String arg2) {
			String text = runNumberField.getText();
			// Day
			for (String s : "456789".split("")) {
				if (s.equals(text)) {
					text = "0" + text;
					break;
				}
			}
			// Month
			if (text.length() < 5)
				for (String s : "23456789".split("")) {
					if (text.endsWith("/" + s)) {
						text = text.substring(0, 3) + "0" + s;
						break;
					}
				}

			if (text.length() == 5)
				runNumberField.setText(text + "/");
			if (text.length() == 2)
				runNumberField.setText(text + "/");
			// Full date validation
			if (text.length() > 9) {
				String[] dmy = text.split("/");
				int day = Integer.parseInt(dmy[0]);
				int month = Integer.parseInt(dmy[1]);
				int year = Integer.parseInt(dmy[2]);
				String newDate = ""; // the nearest valid date
				int daysInFebruary = year % 4 == 0 ? 29 : 28;
				String thirtyDayMonths = "9 4 6 11";
				if (month > 12)
					month = 12;
				if (day > 31)
					day = 31;
				if (month == 2)
					if (day > daysInFebruary)
						day = daysInFebruary;
				if (day > 30)
					if (thirtyDayMonths.contains("" + month))
						day = 30;
				String dbuffer = day > 9 ? "" : "0";
				String mbuffer = month > 9 ? "" : "0";
				newDate = dbuffer + day + "/" + mbuffer + month + "/" + year;
				runNumberField.setText(newDate);
			}
		}
	};

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Sets up the configuration for the action buttons loads the config file.
	 */
	private void setActionButtons() {
		this.loadConfigFile();
		int i = 0;
		for (Button b : this.actionButtons) {
			b.setText(actionButtonLabels[i]);
			i++;
		}
	}

	/**
	 * Makes the scene for RunNumber
	 */
	private void makeRunNumberScene() {
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setHgap(10);
		layout.setVgap(10);
		VBox v = new VBox();
		HBox h = new HBox();
		h.setPadding(new Insets(10, 10, 0, 10));
		h.setSpacing(10);
		runNumberField.setMaxWidth(Double.MAX_VALUE);
		runNumberField.setEditable(false);
		runNumberField.setFont(large);
		runNumberField.setPromptText("Enter Run Number");
		HBox.setHgrow(runNumberField, Priority.ALWAYS);
		runNumberBackspace = new Button();
		runNumberBackspace.setText("<");
		runNumberBackspace.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		runNumberBackspace.setFont(large);
		runNumberBackspace.setOnAction(e -> {
			String txt = runNumberField.getText();
			if (txt.length() == 0)
				return;
			if (txt.endsWith("/"))
				txt = txt.substring(0, txt.length() - 1);
			txt = txt.substring(0, txt.length() - 1);
			runNumberField.setText(txt);
		});
		HBox.setHgrow(runNumberBackspace, Priority.ALWAYS);
		runNumberClear = new Button();
		runNumberClear.setText("X");
		runNumberClear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		runNumberClear.setFont(large);
		runNumberClear.setOnAction(e -> {
			runNumberField.setText("");
		});
		HBox.setHgrow(runNumberClear, Priority.ALWAYS);

		h.getChildren().add(this.runNumberField);
		h.getChildren().add(this.runNumberBackspace);
		h.getChildren().add(this.runNumberClear);
		v.getChildren().add(h);

		for (Button b : numberButtons) {
			b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			GridPane.setHgrow(b, Priority.ALWAYS);
			GridPane.setVgrow(b, Priority.ALWAYS);
			b.setFont(large);
			b.setOnAction(e -> {
				String txt = b.getText();
				runNumberField.appendText(txt);
			});
		}

		layout.add(this.numberButtons[0], 0, 0);
		layout.add(this.numberButtons[1], 0, 1);
		layout.add(this.numberButtons[2], 0, 2);
		layout.add(this.numberButtons[3], 1, 0);
		layout.add(this.numberButtons[4], 1, 1);
		layout.add(this.numberButtons[5], 1, 2);
		layout.add(this.numberButtons[6], 2, 0);
		layout.add(this.numberButtons[7], 2, 1);
		layout.add(this.numberButtons[8], 2, 2);
		layout.add(this.numberButtons[9], 1, 3);
		runNumberHome = new Button();
		runNumberHome.setText("Home");
		runNumberHome.setOnAction(e -> this.toHomeScreen());
		runNumberHome.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		runNumberHome.setFont(large);
		runNumberEnter = new Button();
		runNumberEnter.setText("Enter");
		runNumberEnter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		runNumberEnter.setFont(large);
		runNumberEnter.setOnAction(e -> setRunNumber());

		GridPane.setHgrow(runNumberHome, Priority.ALWAYS);
		GridPane.setVgrow(runNumberHome, Priority.ALWAYS);

		GridPane.setHgrow(runNumberEnter, Priority.ALWAYS);
		GridPane.setVgrow(runNumberEnter, Priority.ALWAYS);

		VBox.setVgrow(layout, Priority.ALWAYS);

		layout.add(this.runNumberHome, 0, 3);
		layout.add(runNumberEnter, 2, 3);
		layout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		v.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		v.getChildren().add(layout);

		this.runNumberScene = new Scene(v);
	}

	/**
	 * Makes the scene for Previous Runs
	 */
	private void makePreviousRunsScene() {
		HBox layout = new HBox();
		VBox left = new VBox();
		left.setSpacing(10);
		VBox right = new VBox();

		layout.setSpacing(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		listOfPreviousRuns = new ListView<String>();

		getFiles();
		Button goHome = new Button("Home");
		goHome.setFont(large);

		goHome.setOnAction(e -> toHomeScreen());
		goHome.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(listOfPreviousRuns, Priority.ALWAYS);
		left.getChildren().add(listOfPreviousRuns);
		left.getChildren().add(goHome);
		TextArea ta = new TextArea();
		ta.setEditable(false);
		listOfPreviousRuns.setOnKeyReleased(e -> ta
				.setText(readFile(listOfPreviousRuns.getSelectionModel()
						.getSelectedItem())));
		listOfPreviousRuns.setOnMouseClicked(e -> ta
				.setText(readFile(listOfPreviousRuns.getSelectionModel()
						.getSelectedItem())));
		ta.setFont(medium);
		ta.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		VBox.setVgrow(ta, Priority.ALWAYS);
		right.getChildren().add(ta);
		right.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox.setHgrow(left, Priority.ALWAYS);
		HBox.setHgrow(right, Priority.ALWAYS);
		layout.getChildren().addAll(left, right);
		previousRunsScene = new Scene(layout);
	}

	/**
	 * Makes the scene for Action
	 */
	private void makeActionScene() {
		this.setActionButtons();
		HBox layout = new HBox();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setSpacing(10);
		VBox leftSide = new VBox();
		VBox rightSide = new VBox();
		GridPane actionLayout = new GridPane();
		actionLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		actionLayout.setHgap(10);
		actionLayout.setVgap(10);
		VBox dashboardLayout = new VBox();
		dashboardLayout.setSpacing(10);
		// dashboardLayout.setPadding(new Insets(10, 10, 10, 10));
		dashboardLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		VBox.setVgrow(dashboardLayout, Priority.ALWAYS);
		HBox SelectSex = new HBox();
		SelectSex.setSpacing(10);
		this.previousRuns = new Button();
		this.previousRuns.setText("View Previous Runs");
		this.previousRuns.setOnAction(e -> this.toPreviousRunsScreen());
		this.previousRuns.setFont(this.medium);
		// female.setSelected(true);
		weight = new Label();
		weight.setText("Weight: " + this.weightMeasurement);
		weight.setFont(medium);
		HBox nameAndDOB = new HBox();
		nameAndDOB.setSpacing(10);
		RunNumberButton = new Button();
		RunNumberButton.setText("Run Number");
		RunNumberButton.setFont(this.medium);
		RunNumberButton.setOnAction(e -> this.toRunNumberScreen());
		buttonSettings.setFont(medium);

		nameAndDOB.getChildren()
				.addAll(this.previousRuns, this.RunNumberButton);

		dashboardLayout.getChildren().add(nameAndDOB);
		leftSide.getChildren().add(dashboardLayout);
		int width = 4;
		int i = 0;
		for (Button b : actionButtons) {
			if (b.getText().endsWith("$")) {
				b.setText(b.getText().substring(0, b.getText().length() - 1)
						+ "\nON");
			}

			String text = b.getText();
			b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			GridPane.setHgrow(b, Priority.ALWAYS);
			GridPane.setVgrow(b, Priority.ALWAYS);
			actionLayout.add(b, i % width, i / width);
			b.setFont(medium);

			b.setOnAction(e -> {
				if (runNumber == null || runNumber.equals(""))
					return;
				long time = System.currentTimeMillis();
				int seconds = (int) ((time / 1000) % 60);
				int minutes = (int) ((time / 1000) / 60) % 60;
				int hours = (int) (((time) / 1000 / 3600) % 24)
						+ this.timeAdjustment;
				String tz = "_UTC";
				if (this.timeAdjustment >= 0)
					tz += "+" + this.timeAdjustment;
				else
					tz += this.timeAdjustment;
				String tim = hours + ":" + minutes + ":" + seconds + tz;
				ObservableList<String> ol = actionList.getItems();
				String text2 = text;
				if (text.contains("\n")) {
					int dex = text.indexOf("\n");
					text2 = text.substring(0, dex) + " "
							+ text.substring(dex + 1);
				}

				String fin = "";
				boolean isToggle = false;
				this.actionList.setItems(ol);
				if (b.getText().endsWith("\nON")) {
					isToggle = true;
					fin = b.getText().substring(0, b.getText().length() - 3)
							+ " ON";
					b.setText(b.getText()
							.substring(0, b.getText().length() - 3) + "\nOFF");
				} else if (b.getText().endsWith("\nOFF")) {
					isToggle = true;
					fin = b.getText().substring(0, b.getText().length() - 4)
							+ " OFF";
					b.setText(b.getText()
							.substring(0, b.getText().length() - 4) + "\nON");
				}

				if (isToggle)
					ol.add(fin + "_" + tim);
				else
					ol.add(text2 + "_" + tim);

			});
			i++;
		}
		VBox.setVgrow(actionLayout, Priority.ALWAYS);
		leftSide.getChildren().add(actionLayout);

		// right side
		actionList = new ListView<String>();
		// actionList.setCellFactory(ex -> {

		// return null;
		// });
		actionList.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		ContextMenu cm = new ContextMenu();
		MenuItem mistake = new MenuItem("Mistake");
		MenuItem wrongAction = new MenuItem("Wrong Intervention");
		MenuItem comment = new MenuItem("Add Comment");

		cm.getItems().addAll(mistake, wrongAction, comment);

		actionList.setContextMenu(cm);
		actionList
				.setOnTouchStationary(e -> {
					int index = actionList.getSelectionModel()
							.getSelectedIndex();
					mistake.setOnAction(ex -> {
						String s = actionList.getItems().get(index);
						s += " ***MISCLICK***";
						actionList.getItems().set(index, s);
					});
					wrongAction.setOnAction(ex -> {
						String s = actionList.getItems().get(index);
						s += " ***WRONG INTERVENTION***";
						actionList.getItems().set(index, s); 
					});
					comment.setOnAction(ex -> {
						String s = actionList.getItems().get(index);
						TextInputDialog tid = new TextInputDialog(
								"Enter your comment");
						tid.setTitle("Comment");
						Optional<String> result = tid.showAndWait();
						if (result.isPresent()) {
							s += "\tComment: " + result.get();
						}
						actionList.getItems().set(index, s);
					});
					actionList.getContextMenu().show(this.window);
				});
		actionList
				.setOnContextMenuRequested(e -> {
					int index = actionList.getSelectionModel()
							.getSelectedIndex();
					mistake.setOnAction(ex -> {
						String s = actionList.getItems().get(index);
						s += " ***MISTAKE***";
						actionList.getItems().set(index, s);
					});
					wrongAction.setOnAction(ex -> {
						String s = actionList.getItems().get(index);
						s += " ***WRONG INTERVENTION***";
						actionList.getItems().set(index, s);
					});
					comment.setOnAction(ex -> {
						String s = actionList.getItems().get(index);
						TextInputDialog tid = new TextInputDialog(
								"Enter your comment");
						tid.setTitle("Comment");
						Optional<String> result = tid.showAndWait();
						if (result.isPresent()) {
							s += "\tComment: " + result.get();
						}
						actionList.getItems().set(index, s);

					});
				});

		rightSide.getChildren().add(actionList);
		finishRun = new Button();
		finishRun.setText("End Run");
		finishRun.setFont(large);
		finishRun.setOnAction(e -> this.endRun());
		finishRun.setMaxWidth(Double.MAX_VALUE);
		rightSide.getChildren().add(finishRun);
		VBox.setVgrow(finishRun, Priority.ALWAYS);
		VBox.setVgrow(actionList, Priority.ALWAYS);
		HBox.setHgrow(leftSide, Priority.ALWAYS);
		HBox.setHgrow(rightSide, Priority.ALWAYS);
		layout.getChildren().add(leftSide);
		layout.getChildren().add(rightSide);
		this.actionScene = new Scene(layout);
		this.correctButtonsState();
	}

	/**
	 * sets the runNumber to the text in the runNumberField and then goes to the
	 * action screen
	 */
	private void setRunNumber() {
		this.runNumber = runNumberField.getText();
		if (this.runNumber.equals("") && restrictUsage)
			return;
		ObservableList<String> ol = actionList.getItems();
		ol.addAll(this.runNumber);
		this.actionList.setItems(ol);
		this.toActionScreen();
	}

	/**
	 * Shows the run number screen This screen is the same as the Date of birth
	 * screen but the onAction for the enter button has been changed to
	 * setRunNumber()
	 */
	private void toRunNumberScreen() {
		this.numberButtons[4].requestFocus();
		this.runNumberField.textProperty().removeListener(this.dateFormatter);
		this.runNumberField.setText("");
		this.runNumberField.setPromptText("Enter Run Number");
		this.runNumberEnter.setOnAction(e -> setRunNumber());
		this.runNumberHome.setText("Home");
		this.runNumberHome.setOnAction(e -> toHomeScreen());
		this.window.setScene(this.runNumberScene);
	}

	/**
	 * Shows the action screen
	 */
	private void toActionScreen() {
		// setRunNumber();
		this.window.setScene(this.actionScene);
	}

	/**
	 * Shows the previous runs screen
	 */
	private void toPreviousRunsScreen() {
		getFiles();
		window.setScene(previousRunsScene);
	}

	/**
	 * Shows the home screen
	 */
	private void toHomeScreen() {
		// this.name.setText("");
		this.runNumber = "";
		// window.setScene(this.homeScene);
		window.setScene(this.actionScene);
	}

	/**
	 * This is where the run number is passed to the program by the server.
	 * 
	 * @param newRunNumber
	 */
	protected void setRunNumber(String newRunNumber) {
		this.runNumber = newRunNumber;
	}

	/**
	 * getFiles gets a list of APIN Files for viewing
	 */
	private void getFiles() {
		try {
			ObservableList<String> listOfFiles = FXCollections
					.observableArrayList();
			listOfPreviousRuns.setItems(listOfFiles);
			int i = 0;
			for (File f : new File(".").listFiles()) {
				if (i > 100)
					break;
				String s = f.getName();
				if (s.toUpperCase().endsWith(".APIN")
						&& !s.toUpperCase().equals(".APIN"))
					listOfFiles.add(s);
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Saves the data entered by an EMT on the action screen to a file named
	 * after the run number
	 */
	private void saveToFile() {
		saveToFile(0);
	}

	/**
	 * Saves the data entered by an EMT on the action screen to a file named
	 * after the run number
	 */
	private void saveToFile(int same) {
		if (!enableSave)
			return;
		try {
			String fn = this.runNumber;
			if (same != 0) {
				fn += "(" + same + ")";
			}
			File fil = new File(fn + ".APIN");
			if (fil.exists()) {
				System.out.println("File already created!");
				saveToFile(same + 1);
				return;
			}
			System.out.println("Saving...");

			BufferedWriter bw = new BufferedWriter(new FileWriter(fn + ".APIN"));
			bw.write("Run: " + runNumber);
			bw.newLine();
			for (String s : this.actionList.getItems()) {
				bw.write(s);
				bw.newLine();
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Done.");
	}

	/**
	 * Saves the configuration file
	 */
	private void saveConfigFile() {
		String fn = "config";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fn));
			for (String line : this.actionButtonLabels) {
				bw.write(line);
				bw.newLine();
			}
			bw.write(this.timeAdjustment + "");
			bw.close();
		} catch (Exception ex) {
		}
	}

	/**
	 * Loads the configuration file
	 */
	private void loadConfigFile() {
		try {
			Scanner scan = new Scanner(new File("config"));
			this.actionButtonLabels = new String[16];

			int i = 0;
			while (i < actionButtons.length) {
				actionButtonLabels[i] = scan.nextLine();
				i++;
			}
			this.timeAdjustment = scan.nextInt();
			scan.close();
		} catch (Exception ex) {
			saveConfigFile();
		}
	}

	/**
	 * Reads the file at the given filepath and returns the entire file
	 * separated by newline chars ("\n")
	 * 
	 * @param filepath
	 * @return
	 */
	private String readFile(String filepath) {
		String eachLine = "";
		try {
			File f = new File(filepath);
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				eachLine += sc.nextLine() + "\n";
			}
			sc.close();
		} catch (Exception ex) {
		}
		return eachLine;
	}

	/**
	 * Called when the run is ended
	 */
	private void endRun() {
		saveToFile();
		ObservableList<String> clear = FXCollections.observableArrayList();
		this.actionList.setItems(clear);
		this.toHomeScreen();
		this.correctButtonsState();

	}

	/**
	 * Called when the window requests close.
	 */
	private void dispose() {
		alive = false;
		window.close();
	}

	/**
	 * This is where it makes all of the scenes and sets the window properties.
	 */
	public void start(Stage mainStage) throws Exception {
		window = mainStage;
		window.setTitle("Rebuild");
		window.setWidth(Double.MAX_VALUE);
		window.setHeight(Double.MAX_VALUE);
		this.makeRunNumberScene();
		this.makePreviousRunsScene();
		this.makeActionScene();
		window.setOnCloseRequest(e -> {
			e.consume();
			dispose();
		});
		window.setScene(this.actionScene);
		window.setMaximized(true);
		window.setResizable(false);
		window.show();
	}
}