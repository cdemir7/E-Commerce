package com.example.filterservice.api.controllers;

import com.example.filterservice.business.abstracts.FilterService;
import com.example.filterservice.business.dto.GetAllFiltersResponse;
import com.example.filterservice.business.dto.GetFilterResponse;
import com.example.filterservice.entities.Filter;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/filters")
public class FilterController {
    private final FilterService service;

    //@PostConstruct
    //public void creatDb(){
      //  var filter = new Filter();
        //filter.setCategoryName("Laptop");
        //filter.setProductName("Lenovo Legion Y530");
        //service.add(filter);
    //}

    @GetMapping
    public List<GetAllFiltersResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFilterResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }
}
