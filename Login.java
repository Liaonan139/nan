package com.cx;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    AnchorPane anchorPane;

    // 初始化方法，用于设置登录界面的布局和元素
    public void initialize() {
        StackPane root=new StackPane();

        // 创建一个路径对象，并添加一些线段和闭合路径
        Path path = new Path();
        path.getElements().add(new MoveTo(150, 50));
        path.getElements().add(new LineTo(150, 50));
        path.getElements().add(new LineTo(300, 50));
        path.getElements().add(new LineTo(300, 80));
        path.getElements().add(new LineTo(170, 80));
        path.getElements().add(new LineTo(170, 60));
        path.getElements().add(new LineTo(150, 50));
        path.getElements().add(new ClosePath());
        path.setFill(Color.rgb(180,182,182));
        path.setStroke(path.getFill());

        // 设置布局位置
        root.setLayoutX(80);
        root.setLayoutY(30);
        anchorPane.getChildren().add(root);
    }

    // 登录方法，当用户点击登录按钮时触发
    public void Login(Event event) throws IOException {
        // 获取事件源（即登录按钮）
        Button button= (Button) event.getSource();

        // 获取按钮所在的场景（即窗口）
        Stage stage= (Stage) button.getScene().getWindow();

        // 设置窗口的位置和场景
        stage.setX(100);
        stage.setY(20);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainPage.fxml"))));
    }
}
