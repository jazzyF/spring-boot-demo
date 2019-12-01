package com.fola.service;

import com.fola.aop.DurationTrackable;
import com.fola.aop.Loggable;
import com.fola.data.ResourceRepository;
import com.fola.model.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @PostConstruct
    public void initResources() {
        resourceRepository.saveAll(Stream.of(
                new Resource(1, "SJFN4253O2", "Elephants and Castle", "jDoe", "anneMiller", LocalDateTime.now(), LocalDateTime.now()),
                new Resource(2, "SJFN4253O2", "Equitable Building", "jDoe", "johnDoe", LocalDateTime.now(), LocalDateTime.now()),
                new Resource(3, "SJFN4253O2", "Herman Ehlers Haus", "jDoe", "janeJohnson", LocalDateTime.now(), LocalDateTime.now())
        ).collect(Collectors.toList()));
    }

    @Override
    @Loggable
    @DurationTrackable
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    @Override
    @Loggable
    @DurationTrackable
    public List<Resource> saveAll(List<Resource> resources) {
        return resourceRepository.saveAll(resources);
    }
}
