package com.orsys.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.orsys.business.Location;
import com.orsys.dto.LocationDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationMapper {
	LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

	@Mapping(source = "locataire.id", target = "idLocataire")
	@Mapping(source = "concessionnaire.id", target = "idConcessionnaire")
	@Mapping(source = "statut.id", target = "idStatut")
	LocationDto toDto(Location location);

	Location toEntity(LocationDto locationDto);
}
