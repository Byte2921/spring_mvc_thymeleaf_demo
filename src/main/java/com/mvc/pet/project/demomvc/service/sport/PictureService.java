package com.mvc.pet.project.demomvc.service.sport;

import com.mvc.pet.project.demomvc.model.PictureData;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;

public interface PictureService {
    PictureData processImage(MultipartFile file) throws IOException;
    LinkedHashMap<String, Integer> getColors(BufferedImage image);
    LinkedHashMap<String, Integer> sortColors(LinkedHashMap<String, Integer> colors);
}
