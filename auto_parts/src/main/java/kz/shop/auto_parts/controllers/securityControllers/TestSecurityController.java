package kz.shop.auto_parts.controllers.securityControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @GetMapping("/admin/get")
    public String getAdmin() {
        return "Hi Admin";
    }

    @GetMapping("/user-test/get")
    public String getUser() {
        return "Hi User";
    }
}
