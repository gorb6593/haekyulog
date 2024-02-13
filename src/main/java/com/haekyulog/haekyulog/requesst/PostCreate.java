package com.haekyulog.haekyulog.requesst;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCreate {

    @NotBlank //컨트롤러 까지도 안 넘어오고 검증함..
    private String title;

    @NotBlank
    private String content;
}
