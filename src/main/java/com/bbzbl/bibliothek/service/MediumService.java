package com.bbzbl.bibliothek.service;

import com.bbzbl.bibliothek.dto.MediumDto;
import com.bbzbl.bibliothek.entity.MediumEntity;
import com.bbzbl.bibliothek.repository.MediumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediumService {

    private final MediumRepository mediumRepository;

    public MediumDto getMediumById(Long id) {
        MediumEntity entity = mediumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medium not found"));
        return mapToDto(entity);
    }

    public MediumDto getMediumByIsbn(String isbn) {
        MediumEntity entity = mediumRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medium not found"));
        return mapToDto(entity);
    }

    public List<MediumDto> getAllMedia() {
        return mediumRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public MediumDto createMedium(MediumDto dto) {
        MediumEntity entity = new MediumEntity();
        updateEntityFields(entity, dto);
        MediumEntity savedEntity = mediumRepository.save(entity);
        return mapToDto(savedEntity);
    }

    public MediumDto updateMedium(Long id, MediumDto dto) {
        MediumEntity entity = mediumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medium not found"));

        updateEntityFields(entity, dto);
        MediumEntity updatedEntity = mediumRepository.save(entity);
        return mapToDto(updatedEntity);
    }

    public void deleteMedium(Long id) {
        if (!mediumRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medium not found");
        }
        mediumRepository.deleteById(id);
    }

    private MediumDto mapToDto(MediumEntity entity) {
        return new MediumDto(
                entity.getId(), entity.getType(), entity.getTitle(),
                entity.getAuthor(), entity.getIsbn()
        );
    }

    private void updateEntityFields(MediumEntity entity, MediumDto dto) {
        entity.setType(dto.type());
        entity.setTitle(dto.title());
        entity.setAuthor(dto.author());
        entity.setIsbn(dto.isbn());
    }
}
