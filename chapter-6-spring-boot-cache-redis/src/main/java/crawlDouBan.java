import jdk.nashorn.internal.ir.RuntimeNode;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.web.servlet.server.Jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class crawlDouBan {

    private static String url = "https://movie.douban.com/subject/30163509/comments?start=";
    private static OkHttpClient client = new OkHttpClient();

    private static ArrayList<String>  exportUrl(){
        ArrayList<String> urls = new ArrayList<>();
        for (int i=20; i<=500;){
            String newUrl = url+i;
            urls.add(newUrl);
            i=i+20;
        }
        return urls;
    }

    public static String get(String url) {
        String html = "";
        try{
        Request request = new Request.Builder().url(url).get().build();
        final Call call = client.newCall(request);
        Response response = call.execute();
        html = response.body().string();
//            System.out.println(html);
    }catch (Exception e){
            e.printStackTrace();
        }
        return html;
    }

    public static void parseHtml(String html){
//        Jsoup jsoup = new Jsoupoup();
        Document doc = Jsoup.parse(html);
        Elements comment = doc.select("#comments > div > div.comment > p > span");
        Elements autor = doc.select("#comments > div> div.comment > h3 > span.comment-info > a");
        for (int i=0; i< comment.size(); ++i){
            System.out.println("autor: "+autor.eachText().toArray()[i]+"\n"+"comment: "+comment.eachText().toArray()[i]);
        }
    }

    public static void main(String[] args){
//        System.out.println(get(url));
//        String html = get(url);
//        parseHtml(htl);
        ArrayList<String> urls = exportUrl();
        for (String url:urls
             ) {
            String html = get(url);
            parseHtml(html);
        }
    }
}

