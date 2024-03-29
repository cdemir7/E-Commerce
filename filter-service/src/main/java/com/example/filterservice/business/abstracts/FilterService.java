package com.example.filterservice.business.abstracts;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.dto.ChangeQuantityRequest;
import com.example.filterservice.business.dto.GetAllFiltersResponse;
import com.example.filterservice.business.dto.GetFilterResponse;
import com.example.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();
    GetFilterResponse getById(UUID id);
    void add(Filter filter);
    void delete(UUID id);
    void deleteAllByCategory(UUID categoryId);
    void deleteByProductId(UUID productId);
    Filter getByProductId(UUID productId);
    ClientResponse changeQuantity(ChangeQuantityRequest request);
}
