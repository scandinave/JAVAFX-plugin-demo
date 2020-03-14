# JAVAFX Demo loading image from fxml with custom classloader

This demo use a custom classloader to load a fmlx from a jar file. 
This fxml display a image with the Image component. 
This component failed to load the folder.png image because it use 
`Thread.currentThread().getContextClassLoader();` and not the one used by
the fxml loader.

## How to

``` mvn clean install ``` inside the plugin subproject

Then place the JavaFXDemo-plugin-1.0-SNAPSHOT.jar to the `plugin` directory.
By default it is in <USER_HOME>/demo/plugins. 

This can be change by updating the path inside the `FileSystemLoader` class `L 19` in `core` 
subproject.

Then ```clean compile package javafx:run``` inside the `core` subproject.
