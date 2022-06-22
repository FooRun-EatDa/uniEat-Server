package com.foorun.unieat.client;

import com.foorun.unieat.domain.kakao.dto.KakaoLocalApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class KakaoApiClient {
    private final WebClient client;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public KakaoApiClient(WebClient.Builder builder) {
        this.client = builder
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK 7162aeb8809b14b1c52d258bba3053fc")
                .build();
    }

    public Mono<List<KakaoLocalApiResponse.Document>> searchAddress(String address) {
        return this.client
                .get()
                .uri(String.format("/v2/local/search/address.json?query=%s", address))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(KakaoLocalApiResponse.class)
                .map(KakaoLocalApiResponse::getDocuments);
    }
}