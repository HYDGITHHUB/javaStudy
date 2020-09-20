package com.pong.target;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简单的网络爬虫
 * @author hy
 *
 */
public class WebSpider {

    /**
     * 用来根据输入的 URL 获取其中的内容
     * @param URL
     * @param charaSet
     * @return
     */
    public String getURLContent(String URL,String charaSet) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(URL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(charaSet)));
            String str = "";
            while ((str = bufferedReader.readLine())!=null) {
                stringBuilder.append(str);
            }
        } catch (MalformedURLException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 根据输入的内容返回 URL 以及 标题（标题返回有误）
     * @param pattern
     * @param content
     * @return
     */
    public List<String> getMes(String pattern,String content) {
        List<String> list =  new ArrayList<>();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        while (m.find()) {
            list.add("URL: "+m.group(1));
            list.add("协议： "+m.group(2));
            list.add("标题： "+m.group(3));
        }
        return list;
    }

    public static void main(String[] args) {
        WebSpider webSpider = new WebSpider();
        List<String> list = new ArrayList<>();
        String str = webSpider.getURLContent("https://www.163.com/","gbk");
//        System.out.println(str);
        list = webSpider.getMes("href=\"((http[s]*)*[\\w:\\/.?=-]+)\">([^em<>][^span][\\s\\S]+?)<\\/a>",str);
        for (String string : list) {
            System.out.println(string);
        }
    }
}
