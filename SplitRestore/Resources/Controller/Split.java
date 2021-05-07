package SplitRestore.Resources.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.io.IOException;

public class Split {


    public TextField choosefilepath;
    public VBox splitmainwindow;
    public TextField sizeid;
    public void backtomain(ActionEvent actionEvent) throws IOException {
        Stage mergewin=(Stage) splitmainwindow.getScene().getWindow();
        mergewin.close();
        Parent root = FXMLLoader.load(getClass().getResource("Fxml/mainwindow.fxml"));
        Stage Win=new Stage();
        Win.setTitle("Split and Merge Tool");
        Win.setScene(new Scene(root));
        Win.show();
    }
    public void choosefile(ActionEvent actionEvent) throws  IOException{
        FileChooser fp=new FileChooser();
        fp.setTitle("Select File to Split");
        File first_part=fp.showOpenDialog(null);
        if(first_part!=null){
            choosefilepath.setText(first_part.getAbsolutePath());
        }
    }

    public static int backup(String filename,double partsize) throws IOException {
        File check_file=new File(filename);
        if(!check_file.exists() || check_file.isDirectory()){
            return -1;
        }
        int size=(int) (1024*1024*partsize);
        byte temp_file[]=new byte[size];
        FileInputStream in = new FileInputStream(filename);
        BufferedInputStream input = new BufferedInputStream(in);
        int count=0;
        File fp=new File(filename);
        int remainingbyte= (int) (fp.length() % size);
        int partion= (int) (fp.length() / size);
        String rawfilename=fp.getAbsolutePath();
        while(count<partion){
            input.read(temp_file,0,size);
            count++;
            String file_Partion_name=rawfilename+"."+count;
            FileOutputStream out=new FileOutputStream(file_Partion_name);
            BufferedOutputStream output=new BufferedOutputStream(out);
            output.write(temp_file);
            output.close();
            out.close();
        }
        if(remainingbyte!=0){
            count++;
            String file_Partion_name=rawfilename+"."+count;
            input.read(temp_file,0,remainingbyte);
            FileOutputStream out=new FileOutputStream(file_Partion_name);
            BufferedOutputStream output=new BufferedOutputStream(out);
            output.write(temp_file,0,remainingbyte);
            output.close();
            out.close();
        }
        input.close();
        in.close();
        return count;
    }

    public void spliting(ActionEvent actionEvent) throws IOException {
        String path= choosefilepath.getText();
        String size=sizeid.getText();
        Double sizeMB;
        try {
            sizeMB = Double.parseDouble(size);
        }
        catch(Exception e){
            sizeid.setText("Please Enter Size in Integer");
            return ;
        }
        int x=backup(path,sizeMB);
        if(x==-1){
            choosefilepath.setText("Error Occurred ! try Again");
        }else{
            choosefilepath.setText("Single File Splitted into "+x+" Files.");
        }
    }
}
