package com.rentcar.controller;

import com.rentcar.domain.Car;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.repository.CarRepository;
import com.rentcar.service.aws.AmazonUploadingFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/aws/photo/car")
@RequiredArgsConstructor
public class AwsFileUploadingController {

    private final CarRepository carRepository;
    private final AmazonUploadingFileService amazonUploadingFileService;

    @PostMapping("/{id}")
    public ResponseEntity<Map<Object, Object>> uploadCarPhoto(@PathVariable Long id,
                                                               @RequestBody MultipartFile file) throws IOException {

        Car car  = carRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Car not found by id "));

        byte[] imageBytes = file.getBytes();
        String imageLink = amazonUploadingFileService.uploadFile(imageBytes, id);

        car.setLinkPhoto(imageLink);
        carRepository.save(car);

        return new ResponseEntity<>(Collections.singletonMap("imageLink", imageLink), HttpStatus.CREATED);
    }

}