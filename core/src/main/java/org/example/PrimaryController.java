package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {

   @FXML
   private VBox root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Get the custom classloader
        URLClassLoader pluginClassLoader = FileSystemLoader.getInstance().getPluginClassLoader();
        // Create a loader for the plugin.
        FXMLLoader pluginLoader = new FXMLLoader();
        // The the classloader to the plugin FXMLLoader
        pluginLoader.setClassLoader(pluginClassLoader);
        try {
            // Get the fxml reference
            InputStream fxml = pluginClassLoader.getResourceAsStream("org/example/plugin/primary.fxml");
            // Load the fxml and get the node
            // This failed because the image can't be load.
            Node node = pluginLoader.load(fxml);
            // Add the node to the parent template.
            root.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}