package olx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class UrlStarter {

    @Autowired
    protected Links links;

    protected List<String[]> dbList = new ArrayList<>();
    ;
    protected String[] urlInf = new String[3];

    //@Autowired
    protected RegexLinks mainDB = new RegexLinks();

    //@Autowired
    protected HttpData data = new HttpData();

    protected String stringHttp;

    @Bean
    public List<String[]> createStartDB() {

        System.out.println("start");

        int k = 0;
        for (int i = 0; i < links.list.size(); i++) {

            stringHttp = data.creatingData(links.list.get(i)[2]);
            mainDB.linkRegExp(stringHttp);

            while (k < mainDB.links.size()) {
                urlInf = new String[3];
                urlInf[0] = links.list.get(i)[0];
                urlInf[1] = links.list.get(i)[1];
                urlInf[2] = mainDB.links.get(k);
                //System.out.println(urlInf[0] + " " + urlInf[1] + " " + urlInf[2]);
                this.dbList.add(urlInf);
                k++;
            }
        }

        for (int i = 0; i < this.dbList.size(); i++) {
            for (int j = 0; j < this.dbList.get(i).length; j++) {
                System.out.print(this.dbList.get(i)[j] + ' ');
            }
            System.out.println();
        }

        return this.dbList;
    }



    /*public void setDbList() {
        this.dbList = createStartDB();
    }

    public List<String[]> getDbList() {
        return dbList;
    }*/
}
