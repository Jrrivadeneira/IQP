package rebuild;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Sleven extends Application implements EventHandler<ActionEvent> {

	/**
	 * Copyright
	 */
	Stage window;
	boolean enableSave = true;
	boolean restrictUsage = false;

	Scene homeScene, runNumberScene, previousRunsScene, ActionScreen;

	// startscene
	Button startButton, previousRuns, exit;

	// runNumberScene
	Button runNumberEnter, runNumberBackspace, runNumberClear, home;
	Button[] numberButtons = { new Button("1"), new Button("4"),
			new Button("7"), new Button("2"), new Button("5"), new Button("8"),
			new Button("3"), new Button("6"), new Button("9"), new Button("0"), };

	// Action Screen
	Button finishRun;
	TextField runNumberField = new TextField();
	String runNumber;

	// fentanyl?
	// intubate?
	// airway/breathing
	// patient NEA
	//
	Button[] actionButtons = { new Button("Monitor"), new Button("12 lead"),
			new Button("Oxygen"), new Button("Patient in EA"),
			new Button("NTG"), new Button("CPR"), new Button("IV"),
			new Button("Intubation"), new Button("Asprin"),
			new Button("Oral Meds"), new Button("IV Meds"),
			new Button("Epinephrine"), new Button("Splinting"),
			new Button("Morphine"), new Button("Benadryl"),
			new Button("Vitals") };
	Button DOB;
	Button RunNumberButton;

	// Patient Data
	TextField name;
	String dateOfBirth = "";
	RadioButton male, female;
	Label weight;
	int weightMeasurement = 0;

	ListView<String> listOfPreviousRuns;
	Font large = new Font(72);
	Font medium = new Font(36);
	Font small = new Font(18);
	ListView<String> actionList;

	ChangeListener<String> dateFormatter = new ChangeListener<String>() {
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
	 * Makes the scene for Home
	 */
	private void makeHomeScene() {
		if (!restrictUsage)
			return;
		startButton = new Button();
		startButton.setText("New Run");
		startButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		startButton.setOnAction(e -> this.newRun());
		startButton.setFont(large);

		previousRuns = new Button();
		previousRuns.setText("View \nPrevious \nRuns");
		previousRuns.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		previousRuns.setFont(large);
		previousRuns.setOnAction(e -> this.toPreviousRunsScreen());

		exit = new Button();
		exit.setText("Exit");
		exit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		exit.setOnAction(e -> this.dispose());
		exit.setFont(large);

		VBox v = new VBox();
		v.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		v.setSpacing(10);
		v.getChildren().add(startButton);

		VBox v2 = new VBox();
		v2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		v2.getChildren().add(previousRuns);
		v2.getChildren().add(exit);

		VBox.setVgrow(previousRuns, Priority.ALWAYS);
		VBox.setVgrow(exit, Priority.ALWAYS);
		VBox.setVgrow(startButton, Priority.ALWAYS);

		HBox h = new HBox();
		HBox.setHgrow(v, Priority.ALWAYS);
		HBox.setHgrow(v2, Priority.ALWAYS);

		h.setPadding(new Insets(10, 10, 10, 10));
		h.setSpacing(10);
		v2.setSpacing(10);
		h.getChildren().add(v);
		h.getChildren().add(v2);

		homeScene = new Scene(h);
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
		home = new Button();
		home.setText("Home");
		home.setOnAction(e -> this.toHomeScreen());
		home.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		home.setFont(large);
		runNumberEnter = new Button();
		runNumberEnter.setText("Enter");
		runNumberEnter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		runNumberEnter.setFont(large);
		runNumberEnter.setOnAction(e -> setRunNumber());

		GridPane.setHgrow(home, Priority.ALWAYS);
		GridPane.setVgrow(home, Priority.ALWAYS);

		GridPane.setHgrow(runNumberEnter, Priority.ALWAYS);
		GridPane.setVgrow(runNumberEnter, Priority.ALWAYS);

		VBox.setVgrow(layout, Priority.ALWAYS);

		layout.add(this.home, 0, 3);
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
		// dashboardLayout.setPadding(new Insets(10,10,10,10));

		dashboardLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		VBox.setVgrow(dashboardLayout, Priority.ALWAYS);
		name = new TextField();
		name.setFont(medium);
		name.setPromptText("Name");
		DOB = new Button();
		DOB.setText("Date of Birth");
		DOB.setFont(medium);
		DOB.setOnAction(e -> this.toDateOfBirthScreen());
		male = new RadioButton();
		HBox SelectSex = new HBox();
		SelectSex.setSpacing(10);
		male.setText("Male");
		male.setFont(medium);
		male.setOnAction(e -> {
			if (male.isSelected())
				e.consume();
			female.setSelected(false);
			male.setSelected(true);
		});
		female = new RadioButton();
		female.setText("Female");
		female.setFont(medium);
		female.setOnAction(e -> {
			if (female.isSelected())
				e.consume();
			male.setSelected(false);
			female.setSelected(true);
		});
		SelectSex.getChildren().add(male);
		SelectSex.getChildren().add(female);
		female.setSelected(true);
		weight = new Label();
		weight.setText("Weight: " + this.weightMeasurement);
		weight.setFont(medium);
		HBox nameAndDOB = new HBox();
		nameAndDOB.setSpacing(10);
		RunNumberButton = new Button();
		RunNumberButton.setText("Run Number");
		RunNumberButton.setFont(this.medium);
		RunNumberButton.setOnAction(e -> this.toRunNumberScreen());
		nameAndDOB.getChildren().addAll(name, DOB, this.RunNumberButton);
		dashboardLayout.getChildren().add(nameAndDOB);
		dashboardLayout.getChildren().add(SelectSex);
		dashboardLayout.getChildren().add(weight);
		leftSide.getChildren().add(dashboardLayout);
		int width = 4;
		int i = 0;
		for (Button b : actionButtons) {
			b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			GridPane.setHgrow(b, Priority.ALWAYS);
			GridPane.setVgrow(b, Priority.ALWAYS);
			actionLayout.add(b, i % width, i / width);
			b.setFont(medium);
			b.setOnAction(e -> {
				long time = System.currentTimeMillis();
				int seconds = (int) ((time / 1000) % 60);
				int minutes = (int) ((time / 1000) / 60) % 60;
				int hours = (int) ((time) / 1000 / 3600) % 24;
				String tim = hours + ":" + minutes + ":" + seconds + "_UTC";
				ObservableList<String> ol = actionList.getItems();
				String text = b.getText();
				if (text.contains("\n")) {
					int dex = text.indexOf("\n");
					text = text.substring(0, dex) + " "
							+ text.substring(dex + 1);

				}
				ol.add(text + "_" + tim);
				this.actionList.setItems(ol);
			});
			i++;
		}
		VBox.setVgrow(actionLayout, Priority.ALWAYS);
		leftSide.getChildren().add(actionLayout);

		// right side
		actionList = new ListView<String>();
		actionList.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
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
		this.ActionScreen = new Scene(layout);
	}

	/**
	 * sets the runNumber to the text in the runNumberField and then goes to the
	 * action screen
	 */
	private void setRunNumber() {

		this.runNumber = runNumberField.getText();
		if (this.runNumber.equals("") && restrictUsage)
			return;
		this.toActionScreen();
	}

	/**
	 * sets the date of birth to the text in the runNumberField and then returns
	 * to the action screen
	 */
	private void setDateOfBirth() {
		this.dateOfBirth = runNumberField.getText();
		this.toActionScreen();
	}

	/**
	 * Shows the date of birth screen This screen is the same as the run number
	 * screen but the onAction for the enter button has been changed to
	 * setDateOfBirth()
	 */
	private void toDateOfBirthScreen() {
		this.numberButtons[4].requestFocus();
		this.runNumberField.setText("");
		this.runNumberField.setPromptText("DDMMYYYY");
		this.runNumberField.textProperty().addListener(this.dateFormatter);
		this.runNumberEnter.setOnAction(e -> setDateOfBirth());
		this.home.setText("Back");
		this.home.setOnAction(e -> toActionScreen());
		this.window.setScene(this.runNumberScene);
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
		this.home.setText("Home");
		this.home.setOnAction(e -> toHomeScreen());
		this.window.setScene(this.runNumberScene);
	}

	/**
	 * Shows the action screen
	 */
	private void toActionScreen() {
		// setRunNumber();
		this.window.setScene(this.ActionScreen);
		DOB.requestFocus();

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
		this.name.setText("");
		this.runNumber = "";
		// window.setScene(this.homeScene);
		window.setScene(this.ActionScreen);
	}

	/**
	 * getFiles gets a list of APIN Files for viewing
	 */
	private void getFiles() {
		try {
			ObservableList<String> listOfFiles = FXCollections
					.observableArrayList();
			listOfPreviousRuns.setItems(listOfFiles);
			for (File f : new File(".").listFiles()) {
				String s = f.getName();
				if (s.toUpperCase().endsWith(".APIN")
						&& !s.toUpperCase().equals(".APIN"))
					listOfFiles.add(s);
			}
		} catch (Exception ex) {

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
			bw.write("Name: " + this.name.getText());
			bw.newLine();
			bw.write("Date Of Birth: " + this.dateOfBirth);
			bw.newLine();
			bw.write("Weight: " + this.weightMeasurement);
			bw.newLine();
			bw.write("Sex: " + (this.male.isSelected() ? "Male" : "Female"));
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
	}

	/**
	 * Called when the New Run button is hit
	 */
	private void newRun() {
		runNumber = "";
		this.runNumberField.setText("");
		this.toRunNumberScreen();
	}

	/**
	 * Called when the window requests close.
	 */
	private void dispose() {
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
		window.setScene(this.ActionScreen);
		window.show();
	}

	/**
	 * Handles action events passed to it.
	 */
	public void handle(ActionEvent event) {
		Object source = event.getSource();
		System.out.println("Hello Everyone!");
		source.toString();
	}

}