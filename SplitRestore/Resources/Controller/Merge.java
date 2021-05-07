package SplitRestore.Resources.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.IOException;
import java.io.*;

public class Merge {
    
    public VBox mergemainwindow;
    public TextField choosefilepath;


    public static int restore(String filename) throws IOException {
        int count=0;
        int numberofpieces=0;
        File check_file=new File(filename);
        if(!check_file.exists() || check_file.isDirectory()){
            return -1;
        }
        String raw_file=check_file.getName();
        File directory=new File(check_file.getParent());
        String[] list_of_file=directory.list();
        int indexoflast=raw_file.lastIndexOf(".");
        raw_file=raw_file.substring(0,indexoflast);
        boolean flag=true;
        while(flag){
            String to_check=(raw_file+"."+(count+1));
            flag=false;
            for(int i=0;i<list_of_file.length;i++){
                if(to_check.equals(list_of_file[i])){
                    count+=1;
                    flag=true;
                    break;
                }
            }
        }
        numberofpieces=count;
        count=0;
        raw_file=check_file.getParent()+"\\"+raw_file;
        FileOutputStream out=new FileOutputStream(raw_file);
        BufferedOutputStream output=new BufferedOutputStream(out);
        while(count<numberofpieces){
            count++;
            String part_file=raw_file+"."+count;
            FileInputStream in = new FileInputStream(part_file);
            BufferedInputStream input = new BufferedInputStream(in);
            File fp=new File(part_file);
            int size=(int) (fp.length());
            byte temp_file[]=new byte[size];
            input.read(temp_file,0,size);
            output.write(temp_file,0,size);
            input.close();
        }
        output.close();
        out.close();
        return numberofpieces;
    }

    public void merging(ActionEvent actionEvent) throws IOException {
        String path= choosefilepath.getText();

        int x=restore(path);
        if(x==-1){
            choosefilepath.setText("Error Occurred ! try Again");
        }else{
            choosefilepath.setText(x+" Parts Merged in Single File");
        }

    }

    public void backtomain(ActionEvent actionEvent) throws IOException {
        Stage mergewin=(Stage) mergemainwindow.getScene().getWindow();
        mergewin.close();
        Parent root = FXMLLoader.load(getClass().getResource("Fxml/mainwindow.fxml"));
        Stage Win=new Stage();
        Win.setTitle("Split and Merge Tool");
        Win.setScene(new Scene(root));
        Win.show();
    }
    public void choosefile(ActionEvent actionEvent) throws  IOException{
        FileChooser fp=new FileChooser();
        fp.setTitle("Select File to merge");
        File first_part=fp.showOpenDialog(null);
        if(first_part!=null){
            choosefilepath.setText(first_part.getAbsolutePath());
        }
    }

    public void spliting(ActionEvent actionEvent) {
    }
}
