package com.trustly.trustly.controller;

import com.trustly.trustly.services.RepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
public class MainController {
    static String baseUrl = "https://github.com";
    @GetMapping(value = "/")
    public String hello() {
        return "/repository/?profile=codeschool-projects&repository=HTMLPortfolioProject";
    }

    @ApiOperation(value="/repository/{profile}/{repository}")
    @GetMapping(value = "/repository")
    @ResponseBody
    public ResponseEntity<String> repository(
        @RequestParam(required = true) String profile,
        @RequestParam(required = true) String repository) {
        String json = RepositoryService.getFileGroupJson(profile, repository);
        return ResponseEntity.ok().body(json);
    }
}