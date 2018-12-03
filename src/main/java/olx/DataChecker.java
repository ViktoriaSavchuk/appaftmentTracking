package olx;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class DataChecker extends MainDBController {


    @Autowired
    private SMTPMailSender smtpMailSender;
    private Pattern p;
    private Matcher m;
    private StringBuilder newLink = new StringBuilder();
    private boolean firstElem, newElem;

    private StringBuilder stringHttp = new StringBuilder(" ");



    @Scheduled(fixedRate = 60000, initialDelay = 1000)
    public void dataChecking() throws MessagingException {


        stringHttp.replace(0, stringHttp.length(),data.creatingData(url));

       try {


        stringHttp.replace(0, stringHttp.length(), stringHttp.toString().split("<div class=\"hasPromoted section clr\">")[1]);}
        catch (ArrayIndexOutOfBoundsException e){

            System.out.println("Can not divide ");
        }



        p = Pattern.compile("<a href=\"https://www\\.olx\\.ua/obyavlenie/(.*)\" c");
        m = p.matcher(stringHttp);


        firstElem = false;
        newElem = false;


        while (m.find() && !firstElem) {

            newLink.replace(0, newLink.length(), "https://www.olx.ua/obyavlenie/" + m.group(1));

            try {


            if (newLink.toString().equals(mainDB.links.get(0)) || newLink.toString().equals(mainDB.links.get(1)) || newLink.toString().equals(mainDB.links.get(2))) {
                firstElem = true;

            } else if (!newLink.toString().equals(mainDB.links.get(0)) && !newLink.toString().equals(mainDB.links.get(1))) {
                smtpMailSender.sendEmail("viktoria.svchk@icloud.com", "New apartment appears: " + newLink);
                System.out.println(newLink + " was sent");
                newElem = true;
            }}catch (IndexOutOfBoundsException e){
                creatingMainDataBase();
            }
        }


        if (newElem) {
            creatingMainDataBase();
           
        }


    }


}
