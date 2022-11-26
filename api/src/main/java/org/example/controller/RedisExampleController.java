package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dao.SimpleKeyPair;
import org.example.util.RedisUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RedisExampleController {
    private final RedisUtil redisUtil;

    @GetMapping("/getValue")
    String getKeyPair(@RequestParam("key") String key) {
        return redisUtil.get(key).toString();
    }

    @PostMapping("/setValue")
    String setKeyPair(@RequestBody SimpleKeyPair simpleKeyPair) {
        return redisUtil.set(simpleKeyPair.getKey(),
                simpleKeyPair.getValue(),
                simpleKeyPair.getExpire()) ? "success" : "error";
    }
}
