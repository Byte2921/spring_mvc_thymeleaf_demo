package com.mvc.pet.project.demomvc.service.sport.impl;

import com.mvc.pet.project.demomvc.model.Cluster;
import com.mvc.pet.project.demomvc.model.PictureData;
import com.mvc.pet.project.demomvc.service.sport.PictureService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class PictureServiceImpl implements PictureService {

    static final Logger LOGGER = LoggerFactory.getLogger(PictureServiceImpl.class);
    public static final int MODE_CONTINUOUS = 1;
    public static final int MODE_ITERATIVE = 2;
    Cluster[] clusters;

    @Override
    public PictureData processImage(MultipartFile file) throws IOException {
        InputStream is = new ByteArrayInputStream(file.getBytes());
        BufferedImage image = ImageIO.read(is);
        LinkedHashMap<String, Long> dominantColors = getColors(image, 1);
        return new PictureData(new String(java.util.Base64.getEncoder().encode(file.getBytes()), StandardCharsets.UTF_8), dominantColors);
    }

    @Override
    public LinkedHashMap<String, Long> getColors(BufferedImage image, int mode) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        clusters = createClusters(image);
        Integer[] normalisedPixels = new Integer[imageWidth * imageHeight];
        Arrays.fill(normalisedPixels, -1);
        boolean pixelChangedCluster = true;

        while (pixelChangedCluster) {
            pixelChangedCluster = false;
            for (int y = 0; y < imageHeight; y++) {
                for (int x = 0; x < imageWidth; x++) {
                    Color pixel = new Color(image.getRGB(x, y));
                    Cluster cluster = findMinimalCluster(pixel);
                    if (normalisedPixels[imageWidth * y + x] != cluster.getId()) {
                        if (mode == MODE_CONTINUOUS) {
                            if (normalisedPixels[imageWidth * y + x] != -1) {
                                clusters[normalisedPixels[imageWidth * y + x]].removePixel(pixel);
                            }
                            cluster.addPixel(pixel);
                        }
                        pixelChangedCluster = true;

                        normalisedPixels[imageWidth * y + x] = cluster.getId();
                    }
                }
            }
            if (mode == MODE_ITERATIVE) {
                for (Cluster cluster : clusters) {
                    cluster.clear();
                }
                for (int y = 0; y < imageHeight; y++) {
                    for (int x = 0; x < imageWidth; x++) {
                        int clusterId = normalisedPixels[imageWidth * y + x];
                        clusters[clusterId].addPixel(
                                new Color(image.getRGB(x, y)));
                    }
                }
            }
        }
        return assembleColorData(normalisedPixels);
    }

    private LinkedHashMap<String, Long> assembleColorData(Integer[] normalisedPixels) {
        Map<Integer, Long> pixelOccurrences = getNormalisedPixelsOccurences(normalisedPixels);
        return pixelOccurrences.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap((entry) -> "#" + Integer.toHexString(clusters[entry.getKey()].getRGB().getRGB()).substring(2), Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }

    private Map<Integer, Long> getNormalisedPixelsOccurences(Integer[] normalisedPixels) {
        return Arrays.stream(normalisedPixels)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Cluster[] createClusters(BufferedImage image) {
        int k = 6;
        Cluster[] result = new Cluster[k];
        int x = 0;
        int y = 0;
        int dx = image.getWidth() / k;
        int dy = image.getHeight() / k;
        for (int i = 0; i < k; i++) {
            result[i] = new Cluster(i, new Color(image.getRGB(x, y)));
            x += dx;
            y += dy;
        }
        return result;
    }

    public Cluster findMinimalCluster(Color color) {
        Cluster minimalCluster = null;
        int min = Integer.MAX_VALUE;
        for (Cluster cluster : clusters) {
            int distance = cluster.distance(color);
            if (distance < min) {
                min = distance;
                minimalCluster = cluster;
            }
        }
        return minimalCluster;
    }
}
