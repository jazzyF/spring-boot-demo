package com.fola.service;

import com.fola.model.Resource;

import java.util.List;

public interface ResourceService {
    List<Resource> findAll();

    List<Resource> saveAll(List<Resource> resources);
}
