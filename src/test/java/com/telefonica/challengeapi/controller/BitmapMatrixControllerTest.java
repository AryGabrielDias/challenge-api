package com.telefonica.challengeapi.controller;

import com.telefonica.challengeapi.service.BitmapMatrixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BitmapMatrixController.class)
public class BitmapMatrixControllerTest {

    @MockBean
    private BitmapMatrixService bitmapMatrixService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new BitmapMatrixController(bitmapMatrixService)).build();
    }

    @Disabled
    @Test
    public void should_returnHeroRaceResultDTO_when_getVectorInsideBitmapMatrixFromImageIsCalled() throws Exception {

        var bufferedImage = ImageIO.read(new File("C:\\KDI\\Cognizant\\image.jpeg"));

        when(bitmapMatrixService.getHowManyTimeArrayElementsAppearsInMatrix(any()))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(post("/v0/api/loan")
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .requestAttr("image", bufferedImage))
                .andExpect(status().isOk());
    }
}
