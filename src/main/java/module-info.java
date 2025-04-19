module edu.umn.d.cs1622.jaderender {
    requires javafx.controls;
    requires java.desktop;


    opens edu.umn.d.cs1622.jaderender to javafx.fxml;
    exports edu.umn.d.cs1622.jaderender;
    exports edu.umn.d.cs1622.jaderender.triangles;
    opens edu.umn.d.cs1622.jaderender.triangles to javafx.fxml;
    exports edu.umn.d.cs1622.jaderender.lines;
    opens edu.umn.d.cs1622.jaderender.lines to javafx.fxml;
    exports edu.umn.d.cs1622.jaderender.vectors;
    opens edu.umn.d.cs1622.jaderender.vectors to javafx.fxml;
}