package com.sofka.birthdayprojectback.birthdaytracker.mapper;

import com.sofka.birthdayprojectback.birthdaytracker.document.BirthdayEntity;
import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BirthdayMapper {
    private final ModelMapper mapper;

    public BirthdayMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public BirthdayDTO entityToDTO(BirthdayEntity entity) {
        return mapper.map(entity, BirthdayDTO.class);
    }

    public BirthdayEntity dtoToEntity(BirthdayDTO dto) {
        return mapper.map(dto, BirthdayEntity.class);
    }

}
