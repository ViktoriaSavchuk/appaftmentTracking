package olx;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBClass extends HttpData {


    ArrayList<String> links = new ArrayList<>();


    public ArrayList<String> linkRegExp(String data) {

        data = data.split("Обычные\\ объявления")[1];

        Matcher m = Pattern.compile("<a href=\"https:\\/\\/www\\.olx\\.ua\\/obyavlenie\\/(.*)\\\" c").matcher(data);
        int i = 0;

        while (m.find() && i < 3) {

           // if (!m.group(1).contains("promoted")) {
                links.add("https://www.olx.ua/obyavlenie/"+m.group(1));
                i++;
           // }

        }
        return links;
    }

    public void showLinks() {

        for (String link : links) {
            System.out.println(link);
        }


    }


}

