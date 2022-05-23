package com.aselsis.springbootworkitemdemo.service;

import com.aselsis.springbootworkitemdemo.dto.SimpleWorkItemDto;
import com.aselsis.springbootworkitemdemo.entity.WorkItem;
import com.aselsis.springbootworkitemdemo.repository.WorkItemRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

@Service
public class WorkItemService {

    private final WorkItemRepository workItemRepository;

    public WorkItemService(WorkItemRepository workItemRepository) {
        this.workItemRepository = workItemRepository;
    }

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public SimpleWorkItemDto createWorkItem(SimpleWorkItemDto simpleWorkItemDto){
        WorkItem workItem = workItemRepository.save(dozerBeanMapper.map(simpleWorkItemDto, WorkItem.class));

        return dozerBeanMapper.map(workItem, SimpleWorkItemDto.class);
    }
}
