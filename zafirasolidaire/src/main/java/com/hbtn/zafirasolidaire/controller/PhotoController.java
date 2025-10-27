package com.hbtn.zafirasolidaire.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbtn.zafirasolidaire.dto.PhotoDto;
import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.service.PhotoFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/photos")
public class PhotoController {
    private final PhotoFacade photoFacade;

    @Autowired
    public PhotoController(PhotoFacade photoFacade) {
        this.photoFacade = photoFacade;
    }

    // ---------- POST ----------//

    // Save a single photo
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> savePhoto(@RequestBody @Valid RequestPhotoDto photoRequest) {
        photoFacade.createPhoto(photoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<String> test(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return ResponseEntity.ok("UUID is valid: " + uuid);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID");
        }
    }


    // // Save multiple photos
    // @PostMapping("/batch")
    // public ResponseEntity<Void> saveAllPhotos(@RequestBody @Valid List<PhotoDto> photoRequests) {
    //     photoFacade.createAllPhotos(photoRequests);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

    // ---------- GET ----------//
    // Get photo by ID
    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> getPhotoById(@PathVariable UUID id) {
        System.out.println("RECEIVED PHOTO ID : " + id);
        PhotoDto photoDto = photoFacade.getPhotoById(id);
        return ResponseEntity.ok(photoDto);
    }

    // Check if photo exists by
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        boolean exists = photoFacade.existsById(id);
        return ResponseEntity.ok(exists);
    }

    // Get all photos
    @GetMapping
    public ResponseEntity<Iterable<PhotoDto>> getAllPhotos() {
        Iterable<PhotoDto> photos = photoFacade.getAllPhotos();
        return ResponseEntity.ok(photos);
    }

    // Count photos
    @GetMapping("/count")
    public ResponseEntity<Long> countPhotos() {
        long count = photoFacade.countPhotos();
        return ResponseEntity.ok(count);
    }

    // ---------- DELETE ----------//

    // Delete photo by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotoById(@PathVariable UUID id) {
        photoFacade.deletePhotoById(id);
        return ResponseEntity.noContent().build();
    }

    // // Delete a single photo (full object)
    // @DeleteMapping
    // public ResponseEntity<Void> deletePhoto(@RequestBody Photo photo) {
    //     photoFacade.deletePhoto(photo);
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete all photos
    // @DeleteMapping("/all")
    // public ResponseEntity<Void> deleteAllPhotos() {
    //     photoFacade.deleteAllPhotos();
    //     return ResponseEntity.noContent().build();
    // }

    // // Delete specific photos
    // @DeleteMapping("/batch")
    // public ResponseEntity<Void> deleteAllPhotosFromList(@RequestBody List<Photo> photos) {
    //     photoFacade.deleteAllPhotos(photos);
    //     return ResponseEntity.noContent().build();
    // }


}
