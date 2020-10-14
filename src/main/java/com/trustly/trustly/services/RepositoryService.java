package com.trustly.trustly.services;

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
import com.trustly.trustly.model.File;
import com.trustly.trustly.model.FileGroup;

public class RepositoryService {
    static String baseUrl = "https://github.com";

    public static String getFileGroupJson(String profile, String repository){
        List<File> lista = getFiles(baseUrl+"/"+profile+"/"+repository);
        List<FileGroup> filesGroups = groupBy(lista);
        return listClassFileToJson(filesGroups);
    }
    public static List<File> getFiles(String url) {
        List<File> files = new ArrayList<>();
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
                    Thread.sleep(200);
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
                    for (File file : getFiles(url_sub)) {
                        files.add(file);
                    }
                    // ELSE IS A FILE => CREATE A FILE
                } else {
                    files.add(FileService.getFile(name, url_sub));
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return files;
    }

    public static List<FileGroup> groupBy(List<File> lista) {
        Map<String, List<File>> mapFileByExtension;
        List<FileGroup> filesGroups = new ArrayList<>();

        // GROUP OBJECTS IN JAVA 8
        // OBJECT FILE BY EXTENSION
        mapFileByExtension = lista.stream().collect(Collectors.groupingBy(File::getExtension));

        for (Map.Entry<String, List<File>> entrada : mapFileByExtension.entrySet()) {
            String extension = entrada.getKey();
            Integer totalNumberLines = 0;
            Double totalNumberbytes = 0.00;
            // ADDING LINES AND BYTES FOR EACH GROUPED FILE
            for (File file : entrada.getValue()) {
                totalNumberLines = totalNumberLines + file.getLines();

                if (file.getUnidade().equals("Bytes")) {
                    totalNumberbytes = totalNumberbytes + file.getSize();
                } else if (file.getUnidade().equals("KB")) {
                    totalNumberbytes = totalNumberbytes + (file.getSize() * 1000);
                }

            }
            FileGroup filegroup = new FileGroup(extension, totalNumberLines, totalNumberbytes);
            filesGroups.add(filegroup);
        }
        // RETURN LIST OF FILES GROUPEDS
        return filesGroups;
    }

    // TO JSON THE LIST OF FILES GROUPEDS
    public static String listClassFileToJson(List<FileGroup> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
