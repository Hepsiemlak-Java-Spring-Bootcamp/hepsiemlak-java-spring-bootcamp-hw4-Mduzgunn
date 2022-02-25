package com.example.realestate.controller;

import com.example.realestate.dto.AdvertRequest;
import com.example.realestate.dto.UpdateAdvertRequest;
import com.example.realestate.dto.response.AdvertResponse;
import com.example.realestate.service.AdvertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdvertController {

    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping(value = "/adverts")
    public ResponseEntity<List<AdvertResponse>> getAllAdvert() {
        return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
    }

    @PostMapping(value = "/adverts")
    public ResponseEntity<AdvertResponse> createAdvert(@RequestBody AdvertRequest request) {
        return new ResponseEntity<>(advertService.saveAdvert(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/adverts/{advertNo}")
    public ResponseEntity<AdvertResponse> getAdvertByAdvertId(@PathVariable(required = false) int advertNo) {
        return new ResponseEntity<>(advertService.getAdvertByAdvertId(advertNo), HttpStatus.OK);
    }

    @PutMapping(value = "/adverts/{id}")
    public ResponseEntity<AdvertResponse> updateAdvert(@PathVariable int id, @Valid @RequestBody UpdateAdvertRequest updateAdvertRequest) {
        return ResponseEntity.ok(advertService.updateAdvert(id, updateAdvertRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdvertByID(@PathVariable int id){
        return ResponseEntity.ok(advertService.deleteAdvertById(id));
    }
}