package com.telefonica.challengeapi.service.impl;

import com.telefonica.challengeapi.service.BitmapMatrixService;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
public class BitmapMatrixServiceImpl implements BitmapMatrixService {

    @Override
    public List<String> getHowManyTimeArrayElementsAppearsInMatrix(BufferedImage image) {

        var elementsFound = new ArrayList<String>();

        int array[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        int matrix[][] = bitmapToMatrix(image);

        for(int i = 0; i < array.length; i++) {

            var times = 0;

            for (int c = 0; c < matrix.length; c++) {

                for (int d = 0; d < matrix[0].length; d++) {

                    if (array[i] == matrix[c][d]) {
                        times++;
                    }
                }
            }

            elementsFound.add("Element: " + array[i] + " Found " + times + " times");
        }

        return elementsFound;
    }

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
