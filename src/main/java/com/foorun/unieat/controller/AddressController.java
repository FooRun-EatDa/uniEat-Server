package com.foorun.unieat.controller;

import com.foorun.unieat.constant.SwaggerApiInfo;
import com.foorun.unieat.domain.code.region.dto.embed.Coordinate;
import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.service.address.AddressCoordinateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.foorun.unieat.constant.JwtConstant.HEADER_NAME;
import static com.foorun.unieat.constant.JwtConstant.HEADER_NAME_REFRESH_TOKEN;
import static com.foorun.unieat.constant.SwaggerApiInfo.GET_COORDINATE;
import static com.foorun.unieat.domain.common.api.ApiResponse.valueOf;

@RestController
@RequestMapping("/v1/address")
@RequiredArgsConstructor
@Api(tags = SwaggerApiInfo.ADDRESS)
@CrossOrigin(allowCredentials = "true", originPatterns = "*", exposedHeaders = {HEADER_NAME, HEADER_NAME_REFRESH_TOKEN})
public class AddressController {
    private final AddressCoordinateService addressCoordinateService;

    @ApiImplicitParam(name = "address", required = true, value = "주소", example = "서울특별시 중구 세종대로 110", dataTypeClass = String.class)
    @ApiOperation(value = GET_COORDINATE)
    @GetMapping(path = "/coordinate")
    public ResponseEntity<ApiResponse<Coordinate>> coordinate(
            @RequestParam("address") String address) {
        return ResponseEntity.ok(
                valueOf(addressCoordinateService.coordinateFromAddress(address)));
    }
}
