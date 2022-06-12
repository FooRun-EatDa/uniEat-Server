package com.foorun.unieat.service.address;

import com.foorun.unieat.client.KakaoApiClient;
import com.foorun.unieat.domain.code.region.dto.embed.Coordinate;
import com.foorun.unieat.domain.kakao.dto.KakaoLocalApiResponse;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;


@Service
@RequiredArgsConstructor
public class AddressCoordinateService {
    private final KakaoApiClient kakaoApiClient;

    public Coordinate coordinateFromAddress(String address) {
        List<KakaoLocalApiResponse.Document> documents = kakaoApiClient.searchAddress(address).block();
        if (isEmpty(documents)) {
            throw new UniEatNotFoundException();
        }
        KakaoLocalApiResponse.Document document = documents.get(0);
        return Coordinate.of(document.getY(), document.getX());
    }
}
