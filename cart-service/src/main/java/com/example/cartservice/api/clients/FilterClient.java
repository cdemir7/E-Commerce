package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ChangeQuantityRequest;
import com.example.commonpackage.utils.dto.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "flt", fallback = FilterClientFallback.class)
public interface FilterClient {
    @PutMapping("/api/v1/filters/change-quantity")
    @Retry(name = "flightSearch")
    ClientResponse changeQuantity(@RequestBody ChangeQuantityRequest request);

}
