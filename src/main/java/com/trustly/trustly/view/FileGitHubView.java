package com.trustly.trustly.view;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import com.trustly.trustly.model.ClassFileGitHub;

public class FileGitHubView {
    
    public static ClassFileGitHub getFile(String filename, String url) {
        ClassFileGitHub file = new ClassFileGitHub();
        file.setFileName(filename);
        file.setUrl(url);
        String array[] = null;
        Integer lines = 0;
        Float size = null;
        String unidade = "";
        String texto = null;
        try {
            // GET HTML CODE OF GITHUB FILE
            Document doc = Jsoup.connect(file.getUrl()).get();
            Elements divs = doc.select("div.text-mono.f6.flex-auto.pr-3.flex-order-2.flex-md-order-1.mt-2.mt-md-0");
            texto = divs.text();
            array = texto.split(" ");
            // IF THE FILE DOES NOT HAVE A LINE, THE ARRAY SPLIT WILL HAVE ONLY TWO POSITIONS
            //HERE FILE WITH LINES
            try {
                if (array[4] != null) {
                    lines = Integer.parseInt(array[0]);
                    size = Float.parseFloat(array[4]);
                    unidade = array[5].toString();
                }
            //HERE FILES WITHOUT LINES
            } catch (Exception e) {
                size = Float.parseFloat(array[0]);
                unidade = array[1].toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.setLines(lines);
        file.setSize(size);
        file.setUnidade(unidade);
        return file;
    }
}
