package com.mvc.pet.project.demomvc.model;

import java.awt.*;

public class Cluster {
    int id;
    int pixelCount;
    int red;
    int green;
    int blue;
    int reds;
    int greens;
    int blues;

    public Cluster(int id, Color color) {
        red = color.getRed();
        green = color.getGreen();
        blue = color.getBlue();
        this.id = id;
        addPixel(color);
    }

    public void clear() {
        red = 0;
        green = 0;
        blue = 0;
        reds = 0;
        greens = 0;
        blues = 0;
        pixelCount = 0;
    }

    public int getId() {
        return id;
    }

    public Color getRGB() {
        int r = reds / pixelCount;
        int g = greens / pixelCount;
        int b = blues / pixelCount;
        return new Color(r, g, b);
    }

    public void addPixel(Color color) {
        reds += color.getRed();
        greens += color.getGreen();
        blues += color.getBlue();
        pixelCount++;
        red = reds / pixelCount;
        green = greens / pixelCount;
        blue = blues / pixelCount;
    }

    public void removePixel(Color color) {
        reds -= color.getRed();
        greens -= color.getGreen();
        blues -= color.getBlue();
        pixelCount--;
        red = reds / pixelCount;
        green = greens / pixelCount;
        blue = blues / pixelCount;
    }

    public int distance(Color color) {
        int rx = Math.abs(red - color.getRed());
        int gx = Math.abs(green - color.getGreen());
        int bx = Math.abs(blue - color.getBlue());
        return (rx + gx + bx) / 3;
    }
}
