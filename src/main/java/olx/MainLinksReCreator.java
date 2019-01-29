/*
package olx;

public class MainLinksReCreator extends UrlController {

    protected RegexLinks reWrittenBD;
    protected int id;
    protected HttpData data = new HttpData();
    protected Links links = new Links();


    public void reCreateStartDB(String[] linkInf) {

        reWrittenBD = new RegexLinks();
        System.out.println("bd rewriting");

        id = Integer.parseInt(linkInf[0]);

        stringHttp = data.creatingData(links.list.get(id)[2]);
        reWrittenBD.linkRegExp(stringHttp);

        System.out.println(reWrittenBD.links.size());
        for (int i = 0; i < reWrittenBD.links.size(); i++) {
            urlInf = new String[3];
            urlInf[0] = links.list.get(id)[0];
            urlInf[1] = links.list.get(id)[1];
            urlInf[2] = reWrittenBD.links.get(i);
            reSetMainDB(id * 3 + i, urlInf);
        }
        System.out.println("updated bd");
        showMainBD();
    }
}
*/
