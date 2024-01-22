package com.example.inventoryservice.business.concretes;

import com.example.commonpackage.events.inventory.CategoryDeletedEvent;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.inventoryservice.business.abstracts.CategoryService;
import com.example.inventoryservice.business.dto.requests.create.CreateCategoryRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateCategoryRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateCategoryResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllCategoriesResponse;
import com.example.inventoryservice.business.dto.responses.get.GetCategoryResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateCategoryResponse;
import com.example.inventoryservice.business.rules.CategoryBusinessRules;
import com.example.inventoryservice.entities.Category;
import com.example.inventoryservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository repository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;
    private final CategoryBusinessRules rules;

    @Override
    public List<GetAllCategoriesResponse> getAll() {
        var categories = repository.findAll();
        var response = categories
                .stream()
                .map(category -> mapper.forResponse().map(category, GetAllCategoriesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCategoryResponse getById(UUID id) {
        rules.checkIfCategoryExists(id);
        var category = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(category, GetCategoryResponse.class);

        return response;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        var category = mapper.forRequest().map(request, Category.class);
        category.setId(UUID.randomUUID());
        var createdCategory = repository.save(category);
        var response = mapper.forResponse().map(createdCategory, CreateCategoryResponse.class);

        return response;
    }

    @Override
    public UpdateCategoryResponse update(UUID id, UpdateCategoryRequest request) {
        rules.checkIfCategoryExists(id);
        var category = mapper.forRequest().map(request, Category.class);
        category.setId(id);
        var updatedCategory = repository.save(category);
        var response = mapper.forResponse().map(updatedCategory, UpdateCategoryResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCategoryExists(id);
        repository.deleteById(id);
        sendKafkaCategoryDeletedEvent(id);
    }


    private void sendKafkaCategoryDeletedEvent(UUID id) {
        producer.sendMessage(new CategoryDeletedEvent(id), "category-deleted");
    }
}
