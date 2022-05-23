package com.aselsis.springbootworkitemdemo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkItemUpdateRequest {

    private String title;

    private String description;
}
