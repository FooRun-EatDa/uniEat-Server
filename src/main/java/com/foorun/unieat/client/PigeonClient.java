package com.foorun.unieat.client;

import com.foorun.unieat.client.payload.PigeonForm;
import com.foorun.unieat.client.payload.PigeonRequest;
import com.foorun.unieat.client.payload.PigeonResponse;
import com.foorun.unieat.exception.UniEatLogicalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class PigeonClient {
    private final RestTemplate pigeonRestTemplate;

    public <T> PigeonResponse<T> send(PigeonRequest<? extends PigeonForm> pigeonRequest) {
        ResponseEntity<PigeonResponse<T>> responseEntity = pigeonRestTemplate.exchange(
                String.format("/%s/%s",
                        pigeonRequest.getMethod().getLowerCase(),
                        pigeonRequest.getMode().getLowerCase()),
                HttpMethod.POST,
                new HttpEntity<>(pigeonRequest.getForm()),
                new ParameterizedTypeReference<PigeonResponse<T>>() {});

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            log.error("Pigeon Client 요청 실패");
            throw new UniEatLogicalException();
        }
        return responseEntity.getBody();
    }
}
