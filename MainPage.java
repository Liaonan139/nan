package com.cx;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * MainPage类用于处理主页面的UI组件。
 */
public class MainPage {
    // 定义一个ImageView对象，用于显示图片
    @FXML
    private ImageView image;

    // 定义一个ComboBox对象，用于选择下拉框中的选项
    @FXML
    private ComboBox comboBox;

    /**
     * initialize方法用于初始化MainPage类的实例。
     * 在这个方法中，可以对UI组件进行一些设置和绑定操作。
     */
    public void initialize() {
        // 在这里添加初始化代码，例如设置默认值、绑定事件等
    }

    public void handlePlay(MouseEvent event) {
        String filePath = "src/com/cx/Music/忠诚卫士之歌.wav";
        Music music = new Music(filePath);
        music.addToPlaylist("src/com/cx/Music/忠诚卫士之歌.wav");
        music.addToPlaylist("src/com/cx/Music/就为打胜仗.wav");
        music.togglePlay(); // 调用togglePlay()方法
    }

}
