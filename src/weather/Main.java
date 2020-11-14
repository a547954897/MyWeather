package weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    final static String KEY = "5e47b2143db54d4d8279c7c853b5f0b0";


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

        URL location = getClass().getResource("home.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("天气系统");
        primaryStage.setScene(new Scene(root,670,370));

        initHomePage(fxmlLoader.getController());

        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 初始化HomePage
     * @param controller
     */
    public void initHomePage(HomeController controller) {
        controller.updateCurrentDate();
        controller.initPage();
    }


}
