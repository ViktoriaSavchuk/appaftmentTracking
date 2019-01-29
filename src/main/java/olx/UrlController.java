package olx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.regex.Matcher;

@Component
public class UrlController extends UrlStarter {

    @Autowired
    private SMTPMailSender smtpMailSender;
    private Matcher m;
    private RegexLinks regexLinks = new RegexLinks();
    private boolean firstElem, newElem;
    private String newLink;
    private String[] tempLinkInf = new String[3];
    //private MainLinksReCreator mainLinksReCreator = new MainLinksReCreator();
    protected RegexLinks reWrittenBD;
    protected int id;


    @Scheduled(fixedRate = 70000, initialDelay = 1000)
    public void checking() throws MessagingException {
        System.out.println("start checking");

        firstElem = false;
        newElem = false;

        for (int i = 0; i < links.list.size(); i++) {

            stringHttp = data.creatingData(links.list.get(i)[2]);
            m = regexLinks.getMatcher(stringHttp);

            while (m.find() && !firstElem) {

                newLink = "https://www.olx.ua/obyavlenie/" + m.group(1);
                tempLinkInf[0] = links.list.get(i)[0];
                tempLinkInf[1] = links.list.get(i)[1];
                tempLinkInf[2] = newLink;
                //System.out.println(newLink);
                sending();
            }
        }
    }

    public void sending() throws MessagingException {

        newElem = false;

        try {
            int id = Integer.parseInt(tempLinkInf[0]);
            if (tempLinkInf[2].equals(dbList.get(id * 3)[2]) || newLink.equals(dbList.get(id * 3 + 1)[2]) || newLink.equals(dbList.get(id * 3 + 2)[2])) {
                firstElem = true;

            } else if (!newLink.equals(dbList.get(0)[2]) && !newLink.equals(dbList.get(1)[2])) {
                smtpMailSender.sendEmail("viktoria.svchk@icloud.com", "New apartment " + tempLinkInf[1] + " " + newLink);
                System.out.println(newLink + " was sent");
                newElem = true;
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            //mainLinksReCreator.reCreateStartDB(tempLinkInf);
            //reCreateStartDB(tempLinkInf);
            //newElem = false;
        }
        if (newElem) {
            //mainLinksReCreator.reCreateStartDB(tempLinkInf);
            reCreateStartDB(tempLinkInf);
            newElem = false;
        }

    }

    public void reCreateStartDB(String[] linkInf) {

        reWrittenBD = new RegexLinks();
        System.out.println("bd rewriting");

        id = Integer.parseInt(linkInf[0]);

        System.out.println(id);

        stringHttp = data.creatingData(links.list.get(id)[2]);
        reWrittenBD.linkRegExp(stringHttp);

        for (int i = 0; i < reWrittenBD.links.size(); i++) {
            urlInf = new String[3];
            urlInf[0] = links.list.get(id)[0];
            urlInf[1] = links.list.get(id)[1];
            urlInf[2] = reWrittenBD.links.get(i);
            dbList.set(id * 3 + i, urlInf);
        }
        System.out.println("updated bd");
        showMainBD();
    }

    public void showMainBD() {

        for (int i = 0; i < dbList.size(); i++) {
            System.out.println(dbList.get(i)[0] + " " + dbList.get(i)[1] + " " + dbList.get(i)[2]);
        }
    }

}
