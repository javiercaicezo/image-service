package edu.eci.dosw.imagenes.image_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.dosw.imagenes.image_service.model.document.ImagenDocument;

import java.util.List;

public interface ImagenRepository extends MongoRepository<ImagenDocument, String> {

    List<ImagenDocument> findByReferenciaExterna(String referenciaExterna);
}