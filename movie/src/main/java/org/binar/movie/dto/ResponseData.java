package org.binar.movie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {
    private String status;
    private String messagge;
    private Object data;
}