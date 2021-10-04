package com.mvc.pet.project.demomvc.service.sport.impl;

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
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class PictureServiceImpl implements PictureService {

    static final Logger LOGGER = LoggerFactory.getLogger(PictureServiceImpl.class);

    @Override
    public PictureData processImage(MultipartFile file) throws IOException {
        InputStream is = new ByteArrayInputStream(file.getBytes());
        BufferedImage image = ImageIO.read(is);
        LinkedHashMap<String, Integer> dominantColors = getColors(image);
        return new PictureData(new String(java.util.Base64.getEncoder().encode(file.getBytes()), StandardCharsets.UTF_8), dominantColors);
    }

    @Override
    public LinkedHashMap<String, Integer> getColors(BufferedImage image) {
        LinkedHashMap<String, Integer> colors = new LinkedHashMap<>();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                String color = "#" + Integer.toHexString((new Color(image.getRGB(i, j)).getRGB())).substring(2);
                if (colors.containsKey(color)) {
                    Integer value = colors.get(color);
                    colors.put(color, value + 1);
                } else {
                    colors.put(color, 1);
                }
            }

        }
        return sortColors(colors);
    }

    @Override
    public LinkedHashMap<String, Integer> sortColors(LinkedHashMap<String, Integer> colors) {
        return colors.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
