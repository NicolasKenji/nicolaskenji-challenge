package com.trustly.trustly.services;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import com.trustly.trustly.model.File;

public class FileService {
    
    public static File getFile(String filename, String url) {
        File file = new File();
        file.setFileName(filename);
        file.setUrl(url);
        String array[] = null;
        Integer lines = 0;
        Float size = null;
        String unidade = "";
        String texto = null;
        Document doc = null;
        final Integer attempts = 20;
        Integer current = 0;
        // GET HTML CODE OF GITHUB FILE
        do {
            try {
                doc = Jsoup.connect(file.getUrl()).get();
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

        Elements divs = doc.select("div.text-mono.f6.flex-auto.pr-3.flex-order-2.flex-md-order-1.mt-2.mt-md-0");
        texto = divs.text();
        array = texto.split(" ");
        // IF THE FILE DOES NOT HAVE A LINE, THE ARRAY SPLIT WILL HAVE ONLY TWO
        // POSITIONS
        // HERE FILE WITH LINES
        try {
            if (array[4] != null) {
                lines = Integer.parseInt(array[0]);
                size = Float.parseFloat(array[4]);
                unidade = array[5].toString();
            }
            // HERE FILES WITHOUT LINES
        } catch (Exception e) {
            size = Float.parseFloat(array[0]);
            unidade = array[1].toString();
        }
        file.setLines(lines);
        file.setSize(size);
        file.setUnidade(unidade);
        return file;
    }
}
