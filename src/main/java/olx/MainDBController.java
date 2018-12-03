package olx;


import org.springframework.context.annotation.Bean;

public class MainDBController {

    protected DBClass mainDB;
    protected HttpData data = new HttpData();
    protected StringBuilder stringHttp = new StringBuilder(" ");
    protected String url = "https://www.olx.ua/nedvizhimost/kvartiry-komnaty/arenda-kvartir-komnat/kvartira/kiev/?search%5Bfilter_float_price%3Ato%5D=8000&search%5Bfilter_float_price%3Afrom%5D=5000&search%5Bfilter_float_number_of_rooms%3Afrom%5D=1&search%5Bfilter_float_number_of_rooms%3Ato%5D=1&search%5Bphotos%5D=1&search%5Bdistrict_id%5D=17";


    @Bean
    public DBClass creatingMainDataBase() {

        System.out.println("start");

        mainDB = new DBClass();


        try {
            stringHttp.replace(0, stringHttp.length(), data.creatingData(url));

            mainDB.linkRegExp(stringHttp.toString());

            mainDB.showLinks();

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("There is no matches. Not real to create 3 main links");

        }


        return mainDB;


    }

}
