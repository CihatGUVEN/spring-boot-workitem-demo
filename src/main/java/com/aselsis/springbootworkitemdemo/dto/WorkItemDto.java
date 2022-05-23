package com.aselsis.springbootworkitemdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkItemDto {

    private Long id;

    private  boolean resolved;

    private Date date;

    private String title;

    private String description;
}
