package kz.shop.auto_parts.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderNotFoundException extends NotFoundException {

    private String date;

    public OrderNotFoundException(String message, String code) {
        super(message, code);
        this.date = LocalDateTime.now().toString();
    }

}