package com.aselsis.springbootworkitemdemo.service;

import com.aselsis.springbootworkitemdemo.dto.SimpleWorkItemDto;
import com.aselsis.springbootworkitemdemo.dto.WorkItemDto;
import com.aselsis.springbootworkitemdemo.entity.WorkItem;
import com.aselsis.springbootworkitemdemo.repository.WorkItemRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkItemService {

    private final WorkItemRepository workItemRepository;
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public WorkItemDto createWorkItem(WorkItemDto workItemDto){
        workItemDto.setCreatedDate(new Date());
        WorkItem workItem = workItemRepository.save(dozerBeanMapper.map(workItemDto, WorkItem.class));

        return dozerBeanMapper.map(workItem, WorkItemDto.class);
    }

    public WorkItemDto updateWorkItem(WorkItemDto workItemDto, Long id) {

        WorkItem workItem = dozerBeanMapper.map(workItemDto, WorkItem.class);

        WorkItem updatedWorkItem = workItemRepository.findById(id).map(workItem1 -> {
            workItem1.setTitle(workItem.getTitle());
            workItem1.setDescription(workItem.getDescription());
            return workItemRepository.save(workItem1);
        }).orElseThrow(() -> new RuntimeException("---WORKITEM NOT FOUND---"));

        return dozerBeanMapper.map(updatedWorkItem, WorkItemDto.class);
    }

    public WorkItemDto updateWorkItemStatue(Map<String, Boolean> update, Long id) {

        WorkItem workItem = workItemRepository.findById(id)
                .map(workItem1 -> {
                    Boolean resolved = update.get("resolved");
                    if(resolved != null){
                        workItem1.setResolved(resolved);
                        return workItemRepository.save(workItem1);
                    }else{
                        throw new RuntimeException("resolved is not found");}
                }).orElseThrow(() -> new RuntimeException("workItem is not found"));

                    return dozerBeanMapper.map(workItem, WorkItemDto.class);

    }

    public void deleteWorkItem(Long id) {
           workItemRepository.deleteById(id);
    }

    public List<WorkItemDto> getAllWorkItems(Integer page, Integer size) {

        Pageable paging = PageRequest.of(page, size);

        Page<WorkItem> pageWorkItem = workItemRepository.findAll(paging);

        List<WorkItemDto> workItemDtoList = pageWorkItem.getContent().stream()
                .map(workItem -> dozerBeanMapper.
                        map(workItem, WorkItemDto.class)).collect(Collectors.toList());

        return workItemDtoList;
    }

    public WorkItemDto getWorkItemById(Long id) {
        WorkItem workItem = workItemRepository.findById(id).orElseThrow(() -> new RuntimeException("workItem is not found"));

        return dozerBeanMapper.map(workItem, WorkItemDto.class);
    }

}
