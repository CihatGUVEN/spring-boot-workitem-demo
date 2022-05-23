package com.aselsis.springbootworkitemdemo.controller;

import com.aselsis.springbootworkitemdemo.dto.SimpleWorkItemDto;
import com.aselsis.springbootworkitemdemo.request.WorkItemCreateRequest;
import com.aselsis.springbootworkitemdemo.response.WorkItemResponse;
import com.aselsis.springbootworkitemdemo.service.WorkItemService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workitem")
@RequiredArgsConstructor
public class WorkItemController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final WorkItemService workItemService;

    @PostMapping
    public ResponseEntity<WorkItemResponse> createWorkItem(@RequestBody WorkItemCreateRequest workItemCreateRequest){
        SimpleWorkItemDto simpleWorkItemDto = workItemService.createWorkItem(dozerBeanMapper.map(workItemCreateRequest, SimpleWorkItemDto.class));

        return ResponseEntity.ok(dozerBeanMapper.map(simpleWorkItemDto, WorkItemResponse.class));
    }
}
