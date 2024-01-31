package com.example.filterservice.api.controllers;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.filterservice.business.abstracts.FilterService;
import com.example.commonpackage.utils.dto.ChangeQuantityRequest;
import com.example.filterservice.business.dto.GetAllFiltersResponse;
import com.example.filterservice.business.dto.GetFilterResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/filters")
public class FilterController {
    private final FilterService service;

    /*@PostConstruct
    public void creatDb(){
        var filter = new Filter();
        filter.setCategoryName("Laptop");
        filter.setProductName("Lenovo Legion Y530");
        service.add(filter);
    }*/

    @GetMapping
    public List<GetAllFiltersResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFilterResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PutMapping("/change-quantity")
    public ClientResponse changeQuantity(@RequestBody ChangeQuantityRequest request){
        return service.changeQuantity(request);
    }
}
