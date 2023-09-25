package com.telefonica.challengeapi.controller;

import com.telefonica.challengeapi.service.BitmapMatrixService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v0/challenge/api/bitmap")
public class BitmapMatrixController {

    private final BitmapMatrixService bitmapMatrixService;

    @Autowired
    public BitmapMatrixController(BitmapMatrixService bitmapMatrixService) {
        this.bitmapMatrixService = bitmapMatrixService;
    }

    @PostMapping
    @Operation(summary = "Get Vector Inside BitMap Matrix",
            description = "Get Vector Inside BitMap Matrix")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<String>> getVectorInsideBitmapMatrixFromImage(@RequestParam("image") MultipartFile image) throws IOException {
        return new ResponseEntity<>(bitmapMatrixService
                .getHowManyTimeArrayElementsAppearsInMatrix(ImageIO.read(image.getInputStream())), HttpStatus.OK);
    }
}
