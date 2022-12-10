package searchengine.backEnd.PageHike;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import searchengine.backEnd.sitesListIndexing.UserAgentIndexing;
import searchengine.config.UserAgentList;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;


@Service
@ComponentScan
public class PageIndexingStream extends RecursiveAction {


    private UserAgentList agentList;

    private SiteParser node;

    private HttpURLConnection connect;

    private int connectTimeout = 1500;

    private String pageURL;

    private String contentSQL;

    private int codeErrorSQL;

    private String error = "Error";

    private Document page;

    public PageIndexingStream(SiteParser node) {
        this.node = node;
    }

    protected void compute() {

        try {
            pageURL = node.getUrl().replaceFirst("^https", "http");

            connection(pageURL,connectTimeout,"GET",false);

            page = Jsoup.connect(node.getUrl()).userAgent(UserAgentIndexing.getUserAgentName(agentList))
                    .referrer(UserAgentIndexing.getUserAgentReferrer(agentList))
                    .get();

            if (HttpURLConnection.HTTP_OK == connect.getResponseCode()) {
                sleep(500);
                Elements elements = page.select("body").select("a");
                for (Element a : elements) {
                    String childUrl = a.absUrl("href");

                    childUrl = stripParams(childUrl);

                    if (isCorrectUrl(childUrl)) {
                        childUrl = stripParams(childUrl);
                        node.addChild(new SiteParser(childUrl));
                        System.out.println(childUrl);
                    }
                }
            } else {

                codeErrorSQL = connect.getResponseCode();
                contentSQL = error;

                System.err.println(pageURL + " - " + connect.getResponseCode() + " - " + contentSQL);


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        for (SiteParser child : node.getChildren()) {
            PageIndexingStream task = new PageIndexingStream(child);
            task.compute();
            try {
                pageURL = child.getUrl().replaceFirst("^https", "http");
                connection(pageURL,connectTimeout,"GET",false);
                codeErrorSQL = connect.getResponseCode();
                contentSQL = "";

                page = Jsoup.connect(pageURL).userAgent(UserAgentIndexing.getUserAgentName(agentList))
                        .referrer(UserAgentIndexing.getUserAgentReferrer(agentList))
                        .get();

                if (HttpURLConnection.HTTP_OK == codeErrorSQL) {
                    sleep(500);
                    System.out.println(child.getUrl());

                } else {

                    contentSQL = error;
                    System.err.println(pageURL + " - " + connect.getResponseCode() + " - " + contentSQL);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private String stripParams(String url) {
        return url.replaceAll("\\?.+", "");
    }

    private boolean isCorrectUrl(String url) {
        Pattern patternRoot = Pattern.compile("^" + node.getUrl());
        Pattern patternNotFile = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)");
        Pattern patternNotAnchor = Pattern.compile("#([\\w\\-]+)?$");

        return patternRoot.matcher(url).lookingAt() &&
                !patternNotFile.matcher(url).find()
                && !patternNotAnchor.matcher(url).find();
    }

    private HttpURLConnection connection(String pageURL,
                                         Integer connectTimeout,
                                         String requestMethod,
                                         Boolean useCaches) throws IOException {
        HttpURLConnection urlConnection;
        urlConnection = (HttpURLConnection) new URL(pageURL).openConnection();
        urlConnection.setConnectTimeout(connectTimeout);
        urlConnection.setReadTimeout(connectTimeout);
        urlConnection.setRequestMethod(requestMethod);
        urlConnection.setUseCaches(useCaches);
        urlConnection.connect();

        return urlConnection;
    }
}
