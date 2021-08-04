package com.shawn.ss.tools;

import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

public class EncodeHtml {
    public static void main(String[] args) throws IOException {
        String content = readFileToString("/Users/shishengb18701shishengcorp.netease.com/Documents/project/otherProject/arts/src/main/resources/templates/signature_template.html");
        final String encodeHtml = URLEncoder.encode(content, "UTF-8");
        System.out.println(encodeHtml);
        System.out.println();
        System.out.println(URLDecoder.decode(encodeHtml,"UTF-8"));
        System.out.println();
        System.out.println();
        content = readFileToString("/Users/shishengb18701shishengcorp.netease.com/Documents/project/otherProject/arts/src/main/resources/templates/signature_template_without_html_tag.html");
        final String encodeDiv = URLEncoder.encode(content, "UTF-8");
        System.out.println(encodeDiv);
        System.out.println();
        System.out.println(URLDecoder.decode(encodeDiv,"UTF-8"));
    }

    private static String readFileToString(String s) throws IOException {
        StringBuilder ret = new StringBuilder();
        final FileReader fileReader = new FileReader(s);
        char tmp[] = new char[10240];
        int read = 0;
        while ((read = fileReader.read(tmp)) > 0) {
            ret.append(tmp, 0, read);
        }
        fileReader.close();
        return ret.toString();
    }
}
