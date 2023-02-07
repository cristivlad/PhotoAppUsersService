package com.example.photoappusersservice.client;

import com.example.photoappusersservice.model.AlbumResponseModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-service")
public interface AlbumServiceClient {

    @GetMapping("/users/{id}/albums")
    @CircuitBreaker(name = "albums-service", fallbackMethod = "getAlbumsFallback")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);

    default List<AlbumResponseModel> getAlbumsFallback(String id, Throwable exc) {
        return new ArrayList<>();
    }
}
