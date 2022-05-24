package com.aselsis.springbootworkitemdemo.controller;

import com.aselsis.springbootworkitemdemo.dto.SimpleWorkItemDto;
import com.aselsis.springbootworkitemdemo.dto.WorkItemDto;
import com.aselsis.springbootworkitemdemo.request.WorkItemCreateRequest;
import com.aselsis.springbootworkitemdemo.request.WorkItemUpdateRequest;
import com.aselsis.springbootworkitemdemo.response.WorkItemByIdResponse;
import com.aselsis.springbootworkitemdemo.response.WorkItemDetailsResponse;
import com.aselsis.springbootworkitemdemo.response.WorkItemGetAllResponse;
import com.aselsis.springbootworkitemdemo.service.WorkItemService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/workitem")
@RequiredArgsConstructor
public class WorkItemController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private final WorkItemService workItemService;

    @PostMapping
    public ResponseEntity<WorkItemDetailsResponse> createWorkItem(@RequestBody WorkItemCreateRequest workItemCreateRequest) {
        WorkItemDto workItemDto = workItemService.createWorkItem(dozerBeanMapper.map(workItemCreateRequest, WorkItemDto.class));

        return ResponseEntity.ok(dozerBeanMapper.map(workItemDto, WorkItemDetailsResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkItemDetailsResponse> updateWorkItem(@RequestBody WorkItemUpdateRequest workItemUpdateRequest, @PathVariable Long id) {
        WorkItemDto workItemDto = workItemService.updateWorkItem(dozerBeanMapper.map(workItemUpdateRequest, WorkItemDto.class), id);

        return ResponseEntity.ok(dozerBeanMapper.map(workItemDto, WorkItemDetailsResponse.class));
    }

    @PutMapping("/{id}/resolved")
    public WorkItemDto updateWorkItemStatue(@RequestBody Map<String, Boolean> update, @PathVariable Long id) {

        return workItemService.updateWorkItemStatue(update, id);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkItem(@PathVariable Long id) {
        workItemService.deleteWorkItem(id);
    }

    @GetMapping
    public ResponseEntity<List<WorkItemGetAllResponse>> getAllWorkItems(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        List<WorkItemDto> workItemDtoList = workItemService.getAllWorkItems(page, size);
        List<WorkItemGetAllResponse> workItemGetAllResponseList = workItemDtoList.stream()
                .map(workItemDto -> dozerBeanMapper.map(workItemDto, WorkItemGetAllResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(workItemGetAllResponseList);
    }

    @GetMapping("/details")
    public ResponseEntity<List<WorkItemDetailsResponse>> getAllWorkItemsByDetails(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        List<WorkItemDto> workItemDtoList = workItemService.getAllWorkItems(page, size);
        List<WorkItemDetailsResponse> workItemDetailsResponseList = workItemDtoList.stream()
                .map(workItemDto -> dozerBeanMapper.map(workItemDto, WorkItemDetailsResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(workItemDetailsResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkItemByIdResponse> getWorkItemById(@PathVariable Long id) {
        WorkItemDto workItemDto = workItemService.getWorkItemById(id);

        return ResponseEntity.ok(dozerBeanMapper.map(workItemDto, WorkItemByIdResponse.class));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<WorkItemDetailsResponse> getWorkItemDetailsById(@PathVariable Long id) {
        WorkItemDto workItemDto = workItemService.getWorkItemById(id);

        return ResponseEntity.ok(dozerBeanMapper.map(workItemDto, WorkItemDetailsResponse.class));
    }

}
