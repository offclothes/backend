package com.app.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/usage")
class SwaggerRedirector {
    @GetMapping
    public String api() { return "redirect:/swagger-ui/index.html"; }
}