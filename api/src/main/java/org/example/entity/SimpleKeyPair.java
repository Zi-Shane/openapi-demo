package org.example.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleKeyPair {
    String key;
    String value;
    long expire;
}
