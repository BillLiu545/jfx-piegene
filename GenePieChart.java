import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.TableColumn.*;
import javafx.util.converter.*;
import javafx.scene.chart.*;
import javafx.application.*;

public class GenePieChart extends Application
{
    // Observable list required for pie chart
    PieGene gene = new PieGene();
    private final ObservableList<PieChart.Data> dataList = gene.getObsList();
    public void start(Stage mainStage)
    {
        // Set the main layout and scene
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 600,600);
        mainStage.setScene(mainScene);
        VBox mainLayout = new VBox();
        root.setCenter(mainLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainStage.setTitle("Genetic Composition Pie Chart");
        
        // Pie chart that displays percentage data of each chemical base (A, T, C, G)
        PieChart chart = new PieChart();
        mainLayout.getChildren().add(chart);
        
        // link list to chart
        chart.setData(dataList);
        
        // chart formatting
        chart.setTitle("Genetic Composition");
        chart.setLabelsVisible(true);
        chart.setLabelLineLength(50);
        chart.setLegendSide(Side.LEFT);
        
        // Button row for adding or removing chemicals
        HBox buttonRow = new HBox();
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(10);
        buttonRow.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
        
        // Buttons to be added for adding/removing chemicals
        // Adding
        Button addButton = new Button("Add Chemical Base");
        addButton.setOnAction((event)->
        {
            // User enters in a chemical base
            TextInputDialog baseDialog = new TextInputDialog();
            baseDialog.setTitle("Enter Base");
            baseDialog.setHeaderText("Enter Base");
            baseDialog.setContentText("Enter a valid chemical base");
            Optional<String> baseOpt = baseDialog.showAndWait();
            String baseName = baseOpt.get();
            String added = null;
            try {
                if (baseName.equals("C") || baseName.equals("G") || baseName.equals("T"))
                {
                    added = baseName;
                }
                else
                {
                    added = "A";
                }
            }
            catch (Exception ex)
            {
                added = "A";
            }
            // User enters in a number to add for that particular chemical base
            TextInputDialog numDialog = new TextInputDialog();
            numDialog.setTitle("Enter Number");
            numDialog.setHeaderText("Enter Number");
            numDialog.setContentText("Enter an amount of the chemical base to add");
            Optional<String> numOpt = numDialog.showAndWait();
            String num_str = numOpt.get();
            Integer amt = 0;
            try
            {
                amt = Integer.parseInt(num_str);
            }
            catch (Exception ex)
            {
                amt = 0;
            }
            // Modify gene bases
            gene.addChem(added, amt);
        });
        buttonRow.getChildren().add(addButton);
        // Removing
        Button removeButton = new Button("Remove Chemical Base");
        removeButton.setOnAction((event)->
        {
            TextInputDialog baseDialog = new TextInputDialog();
            baseDialog.setTitle("Enter Base");
            baseDialog.setHeaderText("Enter Base");
            baseDialog.setContentText("Enter a valid chemical base");
            Optional<String> baseOpt = baseDialog.showAndWait();
            String baseName = baseOpt.get();
            String removed = null;
            try {
                if (baseName.equals("C") || baseName.equals("G") || baseName.equals("T"))
                {
                    removed = baseName;
                }
                else
                {
                    removed = "A";
                }
            }
            catch (Exception ex)
            {
                removed = "A";
            }
            // User enters in a number to remove for that particular chemical base
            TextInputDialog numDialog = new TextInputDialog();
            numDialog.setTitle("Enter Number");
            numDialog.setHeaderText("Enter Number");
            numDialog.setContentText("Enter an amount of the chemical base to remove");
            Optional<String> numOpt = numDialog.showAndWait();
            String num_str = numOpt.get();
            Integer amt = 0;
            try
            {
                amt = Integer.parseInt(num_str);
            }
            catch (Exception ex)
            {
                amt = 0;
            }
            // Modify gene bases
            gene.removeChem(removed, amt);
        });
        buttonRow.getChildren().add(removeButton);
        mainLayout.getChildren().add(buttonRow);
        
        // Top menu for functions
        MenuBar topMenu = new MenuBar();
        topMenu.setStyle("-fx-font-size: 15px;");
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        // Menu Item - Check Stress Condition
        MenuItem condItem = new MenuItem("Check Genetic Composition in %");
        condItem.setOnAction((e->{
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("Current Chemical Compositions");
            info.setHeaderText("Current Chemical Compositions");
            info.setContentText(gene.getCompositions());
            info.showAndWait();
        }));
        // Menu Item - Reset
        MenuItem resetItem = new MenuItem("Reset");
        resetItem.setOnAction((e->{
            dataList.clear();
        }));
        // Close item
        MenuItem closeItem = new MenuItem("Exit");
        closeItem.setOnAction((e->{
            mainStage.close();
            System.exit(0);
        }));
        // Add all menu items in
        fileMenu.getItems().addAll(condItem, resetItem, closeItem);
        
        // Show all elements once added
        mainStage.show();
    }
}
