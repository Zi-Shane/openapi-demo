package org.example.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleKeyPair {
    String key;
    String value;
    long expire;
}
