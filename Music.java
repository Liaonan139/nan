package com.cx;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Music {
    private Clip clip; // 用于播放音乐的剪辑对象
    private AudioInputStream audioInputStream; // 用于读取音频文件的输入流对象
    private String filePath; // 音乐文件的路径
    private List<String> playlist; // 音乐播放列表
    private int currentTrackIndex; // 当前播放的音乐索引

    public Music(String filePath) {
        this.filePath = filePath; // 初始化音乐文件路径
        this.playlist = new ArrayList<>();
        this.currentTrackIndex = 0;
    }

    // 添加音乐到播放列表
    public void addToPlaylist(String filePath) {
        playlist.add(filePath);
    }

    // 播放音乐的方法
    public void play() {
        try {
            if (playlist.isEmpty()) {
                return;
            }
            audioInputStream = AudioSystem.getAudioInputStream(new File(playlist.get(currentTrackIndex)).getAbsoluteFile()); // 获取音频文件的输入流
            clip = AudioSystem.getClip(); // 获取剪辑对象
            clip.open(audioInputStream); // 打开音频文件的输入流
            clip.start(); // 开始播放音乐
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace(); // 打印异常信息
        }
    }

    // 暂停播放音乐的方法
    public void pause() {
        if (clip != null && clip.isRunning()) { // 如果剪辑对象不为空且正在播放音乐
            clip.stop(); // 停止播放音乐
        }
    }

    // 暂停和播放音乐的方法
    public void togglePlay() {
        if (clip != null && clip.isRunning()) { // 如果剪辑对象不为空且正在播放音乐
            clip.stop(); // 停止播放音乐
        } else {
            play(); // 否则开始播放音乐
        }
    }

    // 停止播放音乐的方法
    public void stop() {
        if (clip != null) { // 如果剪辑对象不为空
            clip.stop(); // 停止播放音乐
            clip.setFramePosition(0); // 将剪辑对象的帧位置设置为0，即从头开始播放
        }
    }

    // 设置音量的方法
    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // 获取主音量控制对象
        float range = gainControl.getMaximum() - gainControl.getMinimum(); // 计算音量范围
        float gain = (range * volume) + gainControl.getMinimum(); // 根据音量值计算实际音量
        gainControl.setValue(gain); // 设置主音量控制对象的值为实际音量
    }

    // 上一曲的方法
    public void previous() {
        if (!playlist.isEmpty()) {
            currentTrackIndex = (currentTrackIndex - 1 + playlist.size()) % playlist.size(); // 计算上一曲的索引
            stop(); // 停止当前音乐
            play(); // 播放上一曲
        }
    }

    // 下一曲的方法
    public void next() {
        if (!playlist.isEmpty()) {
            currentTrackIndex = (currentTrackIndex + 1) % playlist.size(); // 计算下一曲的索引
            stop(); // 停止当前音乐
            play(); // 播放下一曲
        }
    }

    public static void main(String[] args) {
        String filePath = "src/com/cx/Music/忠诚卫士之歌.wav";
        Music music = new Music(filePath);
        music.addToPlaylist("src/com/cx/Music/忠诚卫士之歌.wav");
        music.addToPlaylist("src/com/cx/Music/就为打胜仗.wav");

        // 创建窗口
        JFrame frame = new JFrame("音乐播放器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // 将窗口显示在屏幕中间
        frame.setLocationRelativeTo(null);

        // 创建面板
        JPanel panel = new JPanel();
        frame.add(panel);

        // 创建按钮
        JButton playPauseButton = new JButton("播放/暂停");
        JButton stopButton = new JButton("停止");
        JButton previousButton = new JButton("上一曲");
        JButton nextButton = new JButton("下一曲");
        JSlider volumeSlider = new JSlider(0, 100, 50);

        // 添加按钮到面板
        panel.add(playPauseButton);
        panel.add(stopButton);
        panel.add(previousButton);
        panel.add(nextButton);
        panel.add(volumeSlider);

        // 添加事件监听器
        playPauseButton.addActionListener(e -> {
            music.togglePlay();
        });

        stopButton.addActionListener(e -> {
            music.stop();
        });

        previousButton.addActionListener(e -> {
            music.previous();
        });

        nextButton.addActionListener(e -> {
            music.next();
        });

        volumeSlider.addChangeListener(e -> {
            int value = volumeSlider.getValue();
            music.setVolume(value / 100f);
        });

        // 显示窗口
        frame.setVisible(true);
    }
}
