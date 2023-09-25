package com.telefonica.challengeapi.service;

import com.telefonica.challengeapi.service.impl.BitmapMatrixServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class BitmapMatrixServiceImplTest {

    @InjectMocks
    private BitmapMatrixServiceImpl bitmapMatrixService;

    @Test
    public void should_returnHeroRaceResultDTO_when_getRaceResultIsCalled() throws IOException {
        var bufferedImage = ImageIO.read(new File("C:\\KDI\\Cognizant\\image.jpeg"));
        var list = bitmapMatrixService.getHowManyTimeArrayElementsAppearsInMatrix(bufferedImage);
        Assertions.assertTrue(Objects.nonNull(list));
    }
}
