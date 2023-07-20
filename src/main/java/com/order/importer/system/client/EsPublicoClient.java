package com.order.importer.system.client;

import static com.order.importer.system.constants.KataConstants.ESPUBLICO_CLIENT;
import static com.order.importer.system.constants.KataConstants.ESPUBLICO_URL_PLACEHOLDER;

import com.order.importer.system.model.OrdersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ESPUBLICO_CLIENT, url = ESPUBLICO_URL_PLACEHOLDER)
public interface EsPublicoClient {

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    OrdersResponse getOrders(@RequestParam("page") Integer page);

}
