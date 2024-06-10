package org.funtushan.stockindex.api.resource;

import lombok.AllArgsConstructor;

import org.funtushan.stockindex.api.dto.StockIndexViewData;
import org.funtushan.stockindex.facade.StockIndexFacade;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@CrossOrigin("http://localhost:4200/")
@RestController
@AllArgsConstructor
@RequestMapping("/api/indexes")
public class StockIndexResource {

    private StockIndexFacade facade;

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StockIndexViewData> indexes() {
        return facade.find();
    }
}
