package herztalkingnews.code;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DesktopNewsWebSite extends Application {
    private static final String title = ".herztalking";
    static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        DataBaseCommands.connect();
        DataBaseCommands.createTables();

        if (Controllers.file.exists()){

            FXMLLoader fxmlLoader = new FXMLLoader(DesktopNewsWebSite.class.getResource("/herztalkingnews/views/welcome-logged-view.fxml"));
            scene = new Scene(fxmlLoader.load(), 1366, 768);
            scene.getStylesheets().add(getClass().getResource("/css/stylesheets.css").toExternalForm());
            //Image icon = new Image("file:herztalkingnews/code/herztalkingnews_logo.png");
            stage.getIcons().add(new Image(DesktopNewsWebSite.class.getResourceAsStream("/img/herztalkingnews_logo.png")));
            stage.setTitle(".herztalkingnews");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(DesktopNewsWebSite.class.getResource("/herztalkingnews/views/welcome-view.fxml"));
            scene = new Scene(fxmlLoader.load(), 1366, 768);
            scene.getStylesheets().add(getClass().getResource("/css/stylesheets.css").toExternalForm());
            //Image icon = new Image("file:herztalkingnews/code/herztalkingnews_logo.png");
            stage.getIcons().add(new Image(DesktopNewsWebSite.class.getResourceAsStream("/img/herztalkingnews_logo.png")));
            stage.setTitle(".herztalkingnews");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
;
    }



    public static void main(String[] args) {
        launch();
    }

    @FXML
    public void telegramLink() {
        this.getHostServices().showDocument("https://t.me/herztard");
    }

    @FXML
    public void instagramLink(){
        this.getHostServices().showDocument("https://www.instagram.com/herztard/");
    }

    @FXML
    public void githubLink(){
        this.getHostServices().showDocument("https://github.com/herztard");
    }

}