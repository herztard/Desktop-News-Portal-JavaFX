module herztalkingnews.adilzhan_slyamgazy_220103151_project_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires json;
    requires hadoop.mapreduce.client.app;

    opens herztalkingnews.code to javafx.fxml;
    exports herztalkingnews.code;
}