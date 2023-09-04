package kz.shop.auto_parts.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotFoundException extends RuntimeException{
    private String message;
    private String code;
    public NotFoundException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
