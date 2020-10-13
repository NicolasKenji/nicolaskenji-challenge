package com.trustly.trustly.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        try {
            Document doc = Jsoup.connect(url).get();
            Elements divs = doc
                    .select("div.Box-row.Box-row--focus-gray.py-2.d-flex.position-relative.js-navigation-item");
            for (Element div : divs) {
                Elements svgs = div.getElementsByTag("svg");
                String label = svgs.attr("aria-label");
                Elements divs2 = div.getElementsByTag("a");
                String href = divs2.attr("href");
                String name = divs2.attr("title");
                String url_sub = baseUrl + href;
                if (label.equals("Directory")) {
                    for (ClassFileGitHub file : getFiles(url_sub)) {
                        files.add(file);
                    }
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
        mapFileByExtension = lista.stream().collect(Collectors.groupingBy(ClassFileGitHub::getExtension));
        for (Map.Entry<String, List<ClassFileGitHub>> entrada : mapFileByExtension.entrySet()) {
            String extension = entrada.getKey();
            Integer totalNumberLines = 0;
            Double totalNumberbytes = 0.00;
            for (ClassFileGitHub file : entrada.getValue()) {
                totalNumberLines = totalNumberLines + file.getLines();
                if(file.getUnidade().equals("Bytes")){
                    totalNumberbytes = totalNumberbytes + file.getSize();
                }
                else if(file.getUnidade().equals("KB")){
                    totalNumberbytes = totalNumberbytes + (file.getSize() * 1000);
                }
                
            }
            ClassFileGroup filegroup = new ClassFileGroup(extension, totalNumberLines, totalNumberbytes);
            filesGroups.add(filegroup);
        }
        return filesGroups;
    }

    public static String listClassFileToJson(List<ClassFileGroup> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
