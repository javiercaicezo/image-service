package edu.eci.dosw.imagenes.image_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.eci.dosw.imagenes.image_service.model.document.ImagenDocument;
import edu.eci.dosw.imagenes.image_service.repository.ImagenRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public ImagenDocument guardar(MultipartFile archivo, String referenciaExterna) throws IOException {
        ImagenDocument imagen = new ImagenDocument(
                archivo.getOriginalFilename(),
                archivo.getContentType(),
                archivo.getSize(),
                archivo.getBytes(),
                LocalDateTime.now(),
                referenciaExterna
        );

        return imagenRepository.save(imagen);
    }

    public ImagenDocument buscarPorId(String id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
    }

    public List<ImagenDocument> listar() {
        return imagenRepository.findAll();
    }

    public List<ImagenDocument> listarPorReferencia(String referenciaExterna) {
        return imagenRepository.findByReferenciaExterna(referenciaExterna);
    }

    public void eliminar(String id) {
        imagenRepository.deleteById(id);
    }
}