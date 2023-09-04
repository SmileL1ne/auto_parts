package kz.shop.auto_parts.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InvalidInputTypeException extends RuntimeException {

    private String message;
    private String code;
    private String date;

    public InvalidInputTypeException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
        this.date = LocalDateTime.now().toString();
    }

}