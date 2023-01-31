package com.orsys.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.orsys.business.Locataire;
import com.orsys.dto.LocataireDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocataireMapper {

	LocataireMapper INSTANCE = Mappers.getMapper(LocataireMapper.class);

	@Mapping(source = "password", target = "motDePasse")
	Locataire toEntity(LocataireDto locataireDto);

	LocataireDto toDto(Locataire locataire);

}
