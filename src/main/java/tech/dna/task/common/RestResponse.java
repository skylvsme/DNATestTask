package tech.dna.task.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestResponse<T> {

    /* Server response data */
    private T body;

    /* The request was successful */
    private boolean success;

    /* Error message */
    private String message;

    /* Status code */
    @Builder.Default
    private int code = 0;

    public static <T> RestResponse<T> ok() {
        return RestResponse.<T>builder()
                .success(true)
                .build();
    }

    public static <T> RestResponse<T> ok(T body) {
        return RestResponse.<T>builder()
                .success(true)
                .body(body)
                .build();
    }

    public static <T> RestResponse<T> ok(T body, int code) {
        return RestResponse.<T>builder()
                .success(true)
                .body(body)
                .code(code)
                .build();
    }

    public static <T> RestResponse<T> fail() {
        return RestResponse.<T>builder()
                .success(false)
                .build();
    }

    public static <T> RestResponse<T> fail(String message) {
        return RestResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

    public static <T> RestResponse<T> fail(String message, int code) {
        return RestResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

}
