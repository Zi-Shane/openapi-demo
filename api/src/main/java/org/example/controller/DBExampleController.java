package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dao.TradeHistoryMapper;
import org.example.dto.TradeRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DBExampleController {
    private final TradeHistoryMapper tradeHistoryMapper;

    @GetMapping("trade")
    List<TradeRecord> getOrder(@RequestParam String username) {
        return tradeHistoryMapper.getTradeHistory(username);
    }
}
