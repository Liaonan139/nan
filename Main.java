package com.cx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        // 启动JavaFX应用程序
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载名为"Login.fxml"的FXML文件，并将其设置为场景的根节点
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Login.fxml"))));
        // 设置舞台样式为透明
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        // 显示舞台
        primaryStage.show();
    }
}
