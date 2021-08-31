import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class RssFeeds {

    public static void main(String[] args) throws IOException, FeedException {
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed feed = syndFeedInput.build(new XmlReader(new URL(Const.URL)));
        StringBuilder blogPosts = new StringBuilder();

        for (int i = 0; i < feed.getEntries().size(); i++) {
            if (i >= Const.MAX_POST) {
                break;
            }
            SyndEntry entry = feed.getEntries().get(i);
            blogPosts.append(getBlogPost(entry));
        }

        String readmeText = Const.README + blogPosts.toString();

        File file = new File("/Users/juhyun/Desktop/study/README.md");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(readmeText);
        fileWriter.close();
    }

    /* ex) [`여름이 떠나가는 8월의 회고`](https://zzang9ha.tistory.com/379) <br/>  */
    private static String getBlogPost(final SyndEntry entry) {
        return "[`" + entry.getTitle() + "`](" + entry.getLink() + ") <br/> \n";
    }
}
