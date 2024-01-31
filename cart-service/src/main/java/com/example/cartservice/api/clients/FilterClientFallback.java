package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ChangeQuantityRequest;
import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.commonpackage.utils.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "filterClient")
public class FilterClientFallback implements FilterClient{
    @Override
    public ClientResponse changeQuantity(ChangeQuantityRequest request) {
        log.info("FILTER SERVICE IS DOWN!");
        throw new BusinessException("FILTER SERVICE IS DOWN!");
    }
}
