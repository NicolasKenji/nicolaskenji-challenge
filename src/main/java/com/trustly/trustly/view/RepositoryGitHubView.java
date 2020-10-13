package com.trustly.trustly.view;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.trustly.trustly.model.ClassFileGitHub;
import com.trustly.trustly.model.ClassFileGroup;

public class RepositoryGitHubView {
    static String baseUrl = "https://github.com";

    public static List<ClassFileGitHub> getFiles(String url) {
        List<ClassFileGitHub> files = new ArrayList<>();
        Document doc = null;
        final Integer attempts = 20;
        Integer current = 0;
        // GET HTML CODE OF GITHUB REPOSITORY
        do {
            try {
                doc = Jsoup.connect(url).get();
            } catch (HttpStatusException e) {
                current++;
                System.out.print(e);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e1) {
                    System.out.print(e1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (doc == null && current < attempts);

        // GET DIV CLASS ITEMS OF REPOSITORY
        try {
            Elements divs = doc
                    .select("div.Box-row.Box-row--focus-gray.py-2.d-flex.position-relative.js-navigation-item");
            for (Element div : divs) {
                Elements svgs = div.getElementsByTag("svg");
                String label = svgs.attr("aria-label");
                Elements divs2 = div.getElementsByTag("a");
                String href = divs2.attr("href");
                String name = divs2.attr("title");
                String url_sub = baseUrl + href;
                // IF IS DIRECTORY => RECURSIVE THIS FUNCTION AND RECEIVE LIST OF FILES
                if (label.equals("Directory")) {
                    for (ClassFileGitHub file : getFiles(url_sub)) {
                        files.add(file);
                    }
                    // ELSE IS A FILE => CREATE A FILE
                } else {
                    files.add(FileGitHubView.getFile(name, url_sub));
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return files;
    }

    public static List<ClassFileGroup> groupBy(List<ClassFileGitHub> lista) {
        Map<String, List<ClassFileGitHub>> mapFileByExtension;
        List<ClassFileGroup> filesGroups = new ArrayList<>();

        // GROUP OBJECTS IN JAVA 8
        // OBJECT FILE BY EXTENSION
        mapFileByExtension = lista.stream().collect(Collectors.groupingBy(ClassFileGitHub::getExtension));

        for (Map.Entry<String, List<ClassFileGitHub>> entrada : mapFileByExtension.entrySet()) {
            String extension = entrada.getKey();
            Integer totalNumberLines = 0;
            Double totalNumberbytes = 0.00;
            // ADDING LINES AND BYTES FOR EACH GROUPED FILE
            for (ClassFileGitHub file : entrada.getValue()) {
                totalNumberLines = totalNumberLines + file.getLines();

                if (file.getUnidade().equals("Bytes")) {
                    totalNumberbytes = totalNumberbytes + file.getSize();
                } else if (file.getUnidade().equals("KB")) {
                    totalNumberbytes = totalNumberbytes + (file.getSize() * 1000);
                }

            }
            ClassFileGroup filegroup = new ClassFileGroup(extension, totalNumberLines, totalNumberbytes);
            filesGroups.add(filegroup);
        }
        // RETURN LIST OF FILES GROUPEDS
        return filesGroups;
    }

    // TO JSON THE LIST OF FILES GROUPEDS
    public static String listClassFileToJson(List<ClassFileGroup> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
