package com.proyect.tinder.services.impl;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.Photo;
import com.proyect.tinder.repository.PhotoRepository;
import com.proyect.tinder.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository){
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo savePhoto(MultipartFile file) throws SpringException {
        if(file != null){
            try{
                Photo photo = new Photo();
                photo.setMime(file.getContentType());
                photo.setName(file.getName());
                photo.setContent(file.getBytes());

                return photoRepository.save(photo);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Photo updatePhoto(Integer idPhoto, MultipartFile file) throws SpringException {
        if(file != null){
            try{
                Photo photo = new Photo();
                if(idPhoto != null){
                    Optional<Photo> response = photoRepository.findById(idPhoto);
                    if(response.isEmpty()){
                        photo = response.get();
                    }
                }
                photo.setMime(file.getContentType());
                photo.setName(file.getName());
                photo.setContent(file.getBytes());

                return photoRepository.save(photo);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
