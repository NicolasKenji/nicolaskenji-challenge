package com.trustly.trustly.controller;

import java.util.List;
import com.trustly.trustly.model.ClassFileGitHub;
import com.trustly.trustly.model.ClassFileGroup;
import com.trustly.trustly.view.RepositoryGitHubView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    static String baseUrl = "https://github.com";
    @GetMapping(value = "/")
    public String hello() {
        return "/repository/?profile=codeschool-projects&repository=HTMLPortfolioProject";
    }

    @GetMapping(value = "/repository")
    @ResponseBody
    public String repository(
        @RequestParam(required = true) String profile,
        @RequestParam(required = true) String repository) {
        List<ClassFileGitHub> lista = RepositoryGitHubView.getFiles(baseUrl+"/"+profile+"/"+repository);
        List<ClassFileGroup> filesGroups = RepositoryGitHubView.groupBy(lista);
        String json = RepositoryGitHubView.listClassFileToJson(filesGroups);
        return json;
    }
}