package GUI.Window.Components.Time;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Empty extends Application {
    private ObservableList<String> buttonLabels = FXCollections.observableArrayList(
            "Button 1", "Button 2", "Button 3", "Button 4",
            "Button 5", "Button 6", "Button 7", "Button 8"
            // Add more button labels as needed
    );

    @Override
    public void start(Stage primaryStage) {
        TableView<String> tableView = new TableView<>();
        TableColumn<String, String> buttonColumn = new TableColumn<>("Buttons");

        buttonColumn.setCellFactory(col -> {
            TableCell<String, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Button button = new Button(item);
                        button.setOnAction(event -> {
                            // Handle button click event here
                            System.out.println("Button clicked: " + item);
                        });
                        setGraphic(button);
                    }
                }
            };
            return cell;
        });

        tableView.setItems(buttonLabels);
        tableView.getColumns().add(buttonColumn);

        Scene scene = new Scene(tableView, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Button Grid Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

