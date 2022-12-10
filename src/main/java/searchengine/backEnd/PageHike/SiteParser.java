package searchengine.backEnd.PageHike;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;


@Service
@ComponentScan
public class SiteParser {


    private volatile SiteParser parent;

    private volatile int depth;

    private String url;

    private volatile CopyOnWriteArrayList<SiteParser> children;


    public SiteParser(String url) {
        depth = 0;
        this.url = url;
        parent = null;
        children = new CopyOnWriteArrayList<>();
    }

    private int calculateDepth() {
        int result = 0;
        if (parent == null) {
            return result;
        }
        result = 1 + parent.calculateDepth();
        return result;
    }

    public synchronized void addChild(SiteParser element) {
        SiteParser root = getRootElement();
        if (!root.contains(element.getUrl())) {
            element.setParent(this);
            children.add(element);
        }
    }

    private boolean contains(String url) {
        if (this.url.equals(url)) {
            return true;
        }
        for (SiteParser child : children) {
            if (child.contains(url))
                return true;
        }

        return false;
    }

    public String getUrl() {
        return url;
    }

    private void setParent(SiteParser sitemapNode) {
        synchronized (this) {
            this.parent = sitemapNode;
            this.depth = calculateDepth();
        }
    }

    public SiteParser getRootElement() {
        return parent == null ? this : parent.getRootElement();
    }

    public CopyOnWriteArrayList<SiteParser> getChildren() {
        return children;
    }
}
