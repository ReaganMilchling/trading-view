package com.tradingview.search;

import com.tradingview.ticker.Ticker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Component
@RestController
@RequestMapping("/search")
public class SearchController {

    @GetMapping("{tokens}")
    public ResponseEntity<String> getAllAsTicker(
            @PathVariable("tokens") String tokens
    ) {
        return new ResponseEntity<>("Not Implemented Yet : " + tokens, HttpStatus.OK);
    }
}
