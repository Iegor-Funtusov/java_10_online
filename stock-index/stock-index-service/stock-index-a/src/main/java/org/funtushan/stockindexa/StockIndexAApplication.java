package org.funtushan.stockindexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/index")
@SpringBootApplication
public class StockIndexAApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockIndexAApplication.class, args);
    }

    @GetMapping
    public DeferredResult<ResponseEntity<Double>> get() {
        DeferredResult<ResponseEntity<Double>> deferredResult = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            try {
                Thread.sleep(3000);
                double random = ThreadLocalRandom.current().nextDouble(100.00, 200.00);
                deferredResult.setResult(ResponseEntity.ok(random));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return deferredResult;
    }
}
