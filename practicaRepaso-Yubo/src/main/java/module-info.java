module org.example.practicarepasoyubo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.practicarepasoyubo to javafx.fxml;
    exports org.example.practicarepasoyubo;
    exports org.example.practicarepasoyubo.Controller;
    opens org.example.practicarepasoyubo.Controller to javafx.fxml;
}