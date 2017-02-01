package main.java.utils;

import freemarker.template.*;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N on 28.01.2017.
 */
public class PageGen {
    private static String HTML_DIR = "pages";
    private final Configuration cfg;

    private static PageGen ourInstance = new PageGen();
    public static PageGen getInstance() {
        return ourInstance;
    }
    private PageGen() { cfg = new Configuration();}

    public String getPage(String filename, Map<String, Object> data){
        Writer stream = new StringWriter();

        try {
            cfg.setClassForTemplateLoading(this.getClass(), "/");
            Template template = cfg.getTemplate
                    (HTML_DIR + File.separator + filename, "UTF-8");
            System.out.println("Page LOADING * [ " + filename + " ]");
            template.process(data, stream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        String result = stream.toString();
        return result;
    }

    public String getPage(String filename){
        Writer stream = new StringWriter();
        return getPage(filename, new HashMap<String, Object>());
    }

}
