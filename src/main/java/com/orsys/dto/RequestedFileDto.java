package com.orsys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestedFileDto {
	private String name;
	private Long selectedFile;
}