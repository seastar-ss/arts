package com.shawn.ss.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 给你一个初始地址 startUrl 和一个 HTML 解析器接口 HtmlParser，请你实现一个 多线程的网页爬虫，用于获取与 startUrl 有 相同主机名 的所有链接。 
 * <p>
 * 以 任意 顺序返回爬虫获取的路径。
 * <p>
 * 爬虫应该遵循：
 * <p>
 * 从 startUrl 开始
 * 调用 HtmlParser.getUrls(url) 从指定网页路径获得的所有路径。
 * 不要抓取相同的链接两次。
 * 仅浏览与 startUrl 相同主机名 的链接。
 * <p>
 * <p>
 * 如上图所示，主机名是 example.org 。简单起见，你可以假设所有链接都采用 http 协议，并且没有指定 端口号。举个例子，链接 http://leetcode.com/problems 和链接 http://leetcode.com/contest 属于同一个 主机名， 而 http://example.org/test 与 http://example.com/abc 并不属于同一个 主机名。
 * <p>
 * HtmlParser 的接口定义如下：
 * <p>
 * interface HtmlParser {
 * // Return a list of all urls from a webpage of given url.
 * // This is a blocking call, that means it will do HTTP request and return when this request is finished.
 * public List<String> getUrls(String url);
 * }
 * 注意一点，getUrls(String url) 模拟执行一个HTTP的请求。 你可以将它当做一个阻塞式的方法，直到请求结束。 getUrls(String url) 保证会在 15ms 内返回所有的路径。 单线程的方案会超过时间限制，你能用多线程方案做的更好吗？
 * <p>
 * 对于问题所需的功能，下面提供了两个例子。为了方便自定义测试，你可以声明三个变量 urls，edges 和 startUrl。但要注意你只能在代码中访问 startUrl，并不能直接访问 urls 和 edges。
 * <p>
 *  
 * <p>
 * 拓展问题：
 * <p>
 * 假设我们要要抓取 10000 个节点和 10 亿个路径。并且在每个节点部署相同的的软件。软件可以发现所有的节点。我们必须尽可能减少机器之间的通讯，并确保每个节点负载均衡。你将如何设计这个网页爬虫？
 * 如果有一个节点发生故障不工作该怎么办？
 * 如何确认爬虫任务已经完成？
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：
 * urls = [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.google.com",
 *   "http://news.yahoo.com/us"
 * ]
 * edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
 * startUrl = "http://news.yahoo.com/news/topics/"
 * 输出：[
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.yahoo.com/us"
 * ]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：
 * urls = [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.google.com"
 * ]
 * edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
 * startUrl = "http://news.google.com"
 * 输出：["http://news.google.com"]
 * 解释：startUrl 链接与其他页面不共享一个主机名。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= urls.length <= 1000
 * 1 <= urls[i].length <= 300
 * startUrl 是 urls 中的一个。
 * 主机名的长度必须为 1 到 63 个字符（包括点 . 在内），只能包含从 “a” 到 “z” 的 ASCII 字母和 “0” 到 “9” 的数字，以及中划线 “-”。
 * 主机名开头和结尾不能是中划线 “-”。
 * 参考资料：https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames
 * 你可以假设路径都是不重复
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/web-crawler-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MultiThreadCrawler {

    static Map<String, List<String>> ALL_SEED = new HashMap();

    static final String[] URLS = new String[]{
            "http://news.yahoo.com",
            "http://news.yahoo.com/news",
            "http://news.yahoo.com/news/topics/",
            "http://news.google.com",
            "http://news.yahoo.com/us"
    };

    public static void main(String[] args) {
        testParser();
        //testMain();
    }

    private static void testParser() {
        for (String s : URLS)
            System.out.println(new MultiThreadCrawler().getHost(s));
    }

    private static void testMain() {
        List<String> node1 = new ArrayList<>();
        node1.add(URLS[0]);
        node1.add(URLS[1]);
        ALL_SEED.put(URLS[2], node1);
        List<String> node2 = new ArrayList<>();
        node2.add(URLS[2]);
        node2.add(URLS[1]);
        ALL_SEED.put(URLS[3], node2);
        List<String> node3 = new ArrayList<>();
        node3.add(URLS[4]);
        ALL_SEED.put(URLS[0], node3);

        List<String> crawl = new MultiThreadCrawler().crawl(URLS[2], new HtmplParserImpl());
        System.out.println(crawl);
    }

    static interface HtmlParser {
        public List<String> getUrls(String url);
    }

    static class HtmplParserImpl implements HtmlParser {

        @Override
        public List<String> getUrls(String url) {
            //            return ALL_SEED.get(url);
            return null;
        }
    }

    //    static final Pattern URL_PATTERN = Pattern.compile("http://([a-z.0-9\\-]+)(/.*)?");

    BlockingQueue<String> urlQueue = new LinkedBlockingDeque<>();
    BlockingQueue<String> statusQueue = new LinkedBlockingDeque<>();
    Map<String, Integer> urlStatus = new ConcurrentHashMap<>();
    volatile String mainHost;

    //    ExecutorService service=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        int n = Runtime.getRuntime().availableProcessors() * 2 + 1;
        urlQueue.add(startUrl);
        mainHost = getHost(startUrl);
        for (int i = 0; i < n; ++i) {
            new Thread(new Crawler(htmlParser)).start();
        }
        String msg = null;
        while (true) {
            try {
                msg = statusQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            if (msg != null) {
                if (msg.equals("--")) {
                    n--;
                    if (n == 0) {
                        break;
                    }
                } else {
                    urlStatus.put(msg, 1);
                }
            }
        }
        return new ArrayList<String>(urlStatus.keySet());
    }

    private String getHost(String startUrl) {
        if (startUrl.startsWith("http://")) {
            return startUrl.replace("http://", "").split("/")[0];
        }
        throw new IllegalArgumentException("url illegal");
    }

    private class Crawler implements Runnable {

        private final HtmlParser htmlParser;

        public Crawler(HtmlParser htmlParser) {
            this.htmlParser = htmlParser;
        }

        @Override
        public void run() {
            String url;
            int emptyTimes = 0;
            try {
                while (true) {
                    url = urlQueue.poll(17, TimeUnit.MILLISECONDS);
                    if (url == null) {
                        emptyTimes++;
                        if (emptyTimes > 2) {
                            statusQueue.offer("--");
                            break;
                        }
                    } else {
                        if (!urlStatus.containsKey(url)) {
                            List<String> urls = htmlParser.getUrls(url);
                            statusQueue.offer(url);
//                            boolean hasNew = false;
                            if (urls != null && urls.size() > 0) {
                                for (String u : urls) {
                                    if (!urlStatus.containsKey(u) && getHost(u).equals(mainHost)) {
//                                        hasNew = true;
                                        urlQueue.offer(u);
                                    }
                                }
                            }
//                            if (!hasNew && urlQueue.size() == 0) {
//                                statusQueue.offer("--");
//                                break;
//                            }
                        }
                        if (emptyTimes > 0) emptyTimes--;
                    }
                }
            } catch (InterruptedException ex) {
                statusQueue.offer("--");
                ex.printStackTrace();
            }
        }
    }
}
