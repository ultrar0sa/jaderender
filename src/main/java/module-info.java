module edu.umn.d.cs1622.jaderender {
    requires javafx.controls;
    requires java.desktop;


    opens edu.umn.d.cs1622.jaderender to javafx.fxml;
    exports edu.umn.d.cs1622.jaderender;
}