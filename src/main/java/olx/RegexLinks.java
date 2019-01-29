package olx;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLinks extends HttpData {

    ArrayList<String> links = new ArrayList<>();
    private Pattern p;
    private Matcher m;

    public Matcher getMatcher(String data) {

        try {
            data= data.split("<div class=\"hasPromoted section clr\">")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            // System.out.println("Can not divide ");
        }

        p = Pattern.compile("<a href=\"https://www\\.olx\\.ua/obyavlenie/(.*)\" c");
        m = p.matcher(data);

        return m;
    }

    public ArrayList<String> linkRegExp(String data) {
        m = getMatcher(data);
        int i = 0;

        while (m.find() && i < 3) {
            links.add("https://www.olx.ua/obyavlenie/" + m.group(1));
            i++;
        }
        return links;
    }

    public void showLinks() {
        for (String link : links) {
            System.out.println(link);
        }
    }
}

