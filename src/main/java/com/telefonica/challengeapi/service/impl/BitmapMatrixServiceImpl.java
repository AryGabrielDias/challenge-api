package com.telefonica.challengeapi.service.impl;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class BitmapMatrixServiceImpl {


    private static int[][] bitmapToMatrix(BufferedImage image) {
        int iw = image.getWidth();
        int ih = image.getHeight();
        int[][] ret = new int[ih][iw];

        // note that image is processed row by row top to bottom
        for (int y = 0; y < ih; y++) {
            for (int x = 0; x < iw; x++) {

                // returns a packed pixel where each byte is a color channel
                // order is the default ARGB color model
                int pixel = image.getRGB(x, y);

                // Get pixels
                // int alpha = (pixel >> 24) & 0xFF;
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;

                int average = (int) (((double) blue + (double) green + red) / 3.0);
                ret[y][x] = average;
            }
        }
        return ret;
    }

}
