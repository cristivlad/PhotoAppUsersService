package com.example.photoappusersservice.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400:
                return new BadRequestException();
            case 404:
                if (s.contains("getAlbums")) {
                return new ResponseStatusException(HttpStatus.NOT_FOUND, response.reason());
                }
                break;
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
