package com.haekyulog.haekyulog.requesst;

import com.haekyulog.haekyulog.exception.InvalidRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
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

    //빌더의 장정
    // - 가독성에 좋다.
    // - 값 생성에 대한 유연함
    // - 필요한 값만 받을 수 있다.
    // - 객체의 불변성
    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void validate() {
        if (title.contains("바보")) {
            throw new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
        }
    }
}
