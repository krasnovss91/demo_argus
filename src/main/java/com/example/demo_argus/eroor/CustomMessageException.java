package com.example.demo_argus.eroor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomMessageException extends RuntimeException{
    private final String message;
}
