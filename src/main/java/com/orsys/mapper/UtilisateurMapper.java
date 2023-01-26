package com.orsys.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.orsys.business.Utilisateur;
import com.orsys.dto.UtilisateurDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UtilisateurMapper {
	LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

	UtilisateurDto toDto(Utilisateur utilisateur);

}
