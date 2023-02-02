package com.example.photoappusersservice.client;

import com.example.photoappusersservice.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "albums-service")
public interface AlbumServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);
}
