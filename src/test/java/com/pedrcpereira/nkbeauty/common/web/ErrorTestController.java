package com.pedrcpereira.nkbeauty.common.web;

import com.pedrcpereira.nkbeauty.common.exception.ConflictException;
import com.pedrcpereira.nkbeauty.common.exception.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test-errors")
public class ErrorTestController {

    record SampleRequest(
            @NotBlank String name,
            @NotBlank @Email String email) {
    }

    @PostMapping("/validation")
    void validation(@Valid @RequestBody SampleRequest request) {
    }

    @GetMapping("/type")
    void type(@RequestParam int number) {
    }

    @GetMapping("/not-found")
    void notFound() {
        throw new NotFoundException("Not found");
    }

    @GetMapping("/conflict")
    void conflict() {
        throw new ConflictException("Conflict", Map.of("field", "email"));
    }

    @GetMapping("/unexpected")
    void unexpected() {
        throw new RuntimeException("secret detail");
    }
}
