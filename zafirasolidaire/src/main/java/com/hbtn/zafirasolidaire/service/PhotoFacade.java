package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.dto.PhotoDto;
import com.hbtn.zafirasolidaire.dto.RequestPhotoDto;
import com.hbtn.zafirasolidaire.mapper.PhotoMapper;
import com.hbtn.zafirasolidaire.model.Photo;
import com.hbtn.zafirasolidaire.repository.PhotoRepository;

@Service
public class PhotoFacade {
    private final PhotoMapper photoMapper;
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoFacade(PhotoMapper photoMapper, PhotoRepository photoRepository) {
        this.photoMapper = photoMapper;
        this.photoRepository = photoRepository;
    }

    //---------- Repository Services ----------//
    public void createPhoto(RequestPhotoDto requestPhotoDto) {
        if (requestPhotoDto == null) {
            throw new IllegalArgumentException("Photo cannot be null.");
        }

        Photo photo = photoMapper.requestDtoToPhoto(requestPhotoDto);

        photoRepository.save(photo);
    }

    public void createAllPhotos(Iterable<RequestPhotoDto> requestPhotoDtos) {
        if (requestPhotoDtos == null || !requestPhotoDtos.iterator().hasNext()) {
            throw new IllegalArgumentException("Photo list cannot be null or empty.");
        }

        List<Photo> photos = new ArrayList<>();

        for (RequestPhotoDto requestPhotoDto : requestPhotoDtos) {
            Photo photo = photoMapper.requestDtoToPhoto(requestPhotoDto);
            photos.add(photo);
        }
        photoRepository.saveAll(photos);
    }

    public PhotoDto getPhotoById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Photo ID cannot be null.");
        }

        Photo foundPhoto = photoRepository.findByIdWithAssociations(id).orElseThrow(() -> new IllegalArgumentException("Photo not found"));

        return photoMapper.photoToDto(foundPhoto);
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Photo ID cannot be null.");
        }
        return photoRepository.existsById(id);
    }

    public Iterable<PhotoDto> getAllPhotos() {
        return mapToDto(photoRepository.findAll());
    }

    public Iterable<PhotoDto> getAllPhotosById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("ID list cannot be null or empty.");
        }

        return mapToDto(photoRepository.findAllById(ids));
    }

    public void deletePhotoById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Photo ID cannot be null.");
        }
        photoRepository.deleteById(id);
    }

    public void deletePhoto(Photo photo) {
        if (photo == null) {
            throw new IllegalArgumentException("Photo cannot be null.");
        }
        photoRepository.delete(photo);
    }

    public void deleteAllPhotos() {
        photoRepository.deleteAll();
    }

    public void deleteAllPhotos(Iterable<Photo> photos) {
        if (photos == null || !photos.iterator().hasNext()) {
            throw new IllegalArgumentException("Photo list cannot be null or empty.");
        }
        photoRepository.deleteAll(photos);
    }

    public long countPhotos() {
        return photoRepository.count();
    }

    //--------- Helper methods ---------//

    private Iterable<PhotoDto> mapToDto(Iterable<Photo> photos) {
        return StreamSupport.stream(photos.spliterator(), false)
            .map(photoMapper::photoToDto)
            .collect(Collectors.toList());
    }

    public Photo mapDtoToPhoto(RequestPhotoDto dto) {
    return photoMapper.requestDtoToPhoto(dto);
    }

    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }
}
