package MedTechBackend.Backend.security.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @GetMapping
    public String get() {
        return "GET:: doctor controller";
    }
    @PostMapping
    public String post() {
        return "POST:: doctor controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: doctor controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: doctor controller";
    }

}
