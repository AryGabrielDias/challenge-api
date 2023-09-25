package com.telefonica.challengeapi.controller;

import com.telefonica.challengeapi.service.BitmapMatrixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    @Test
    public void should_returnHeroRaceResultDTO_when_getVectorInsideBitmapMatrixFromImageIsCalled() throws Exception {

        var file = new File("C:\\KDI\\Cognizant\\image.jpeg");
        var input = new FileInputStream(file);

        var image = new MockMultipartFile("image", input);

        when(bitmapMatrixService.getHowManyTimeArrayElementsAppearsInMatrix(any()))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/v0/challenge/api/bitmap")
                        .file(image)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());
    }
}
