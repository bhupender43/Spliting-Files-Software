package SplitRestore.Resources.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import  javafx.stage.Stage;

import java.io.IOException;

public class MainWindow{
    public GridPane mainwindow;

    public void merger(ActionEvent actionEvent) throws IOException {
        Stage mainwin=(Stage) mainwindow.getScene().getWindow();
        mainwin.close();
        Parent root = FXMLLoader.load(getClass().getResource("Fxml/Merge.fxml"));
        Stage mergeWin=new Stage();
        /*Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        mergeWin.setX(bounds.getMinX());
        mergeWin.setY(bounds.getMinY());
        mergeWin.setWidth(bounds.getWidth());
        mergeWin.setHeight(bounds.getHeight());

         */
        mergeWin.setTitle("Merge");
        mergeWin.setScene(new Scene(root));
        mergeWin.show();

    }

    public void splitter(ActionEvent actionEvent) throws IOException {
        Stage mainwin=(Stage) mainwindow.getScene().getWindow();
        mainwin.close();
        Parent root = FXMLLoader.load(getClass().getResource("Fxml/Split.fxml"));
        Stage splitWin=new Stage();
        /*Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        splitWin.setX(bounds.getMinX());
        splitWin.setY(bounds.getMinY());
        splitWin.setWidth(bounds.getWidth());
        splitWin.setHeight(bounds.getHeight());

         */
        splitWin.setTitle("Split");
        splitWin.setScene(new Scene(root));
        splitWin.show();
    }
}
