module org.example.employermanfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.employermanfx to javafx.fxml;
    exports org.example.employermanfx;
}