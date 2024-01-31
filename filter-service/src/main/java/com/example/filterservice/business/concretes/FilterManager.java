package com.example.filterservice.business.concretes;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.filterservice.business.abstracts.FilterService;
import com.example.commonpackage.utils.dto.ChangeQuantityRequest;
import com.example.filterservice.business.dto.GetAllFiltersResponse;
import com.example.filterservice.business.dto.GetFilterResponse;
import com.example.filterservice.entities.Filter;
import com.example.filterservice.repository.FilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllFiltersResponse> getAll() {
        var filters = repository.findAll();
        var response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetFilterResponse getById(UUID id) {
        var filter = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(filter, GetFilterResponse.class);

        return response;
    }

    @Override
    public void add(Filter filter) {
        repository.save(filter);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllByCategory(UUID categoryId) {
        repository.deleteAllByCategoryId(categoryId);
    }

    @Override
    public void deleteByProductId(UUID productId) {
        repository.deleteByProductId(productId);
    }

    @Override
    public Filter getByProductId(UUID productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public ClientResponse changeQuantity(ChangeQuantityRequest request) {
        Filter filter = repository.findById(request.getId()).orElseThrow();
        filter.setQuantity(request.getQuantity());
        repository.save(filter);

        return new ClientResponse(true, "Success");
    }
}
