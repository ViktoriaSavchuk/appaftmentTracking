package olx;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Links {

    public List<String[]> list = new ArrayList<>();

    public Links() {
        this.list.add(new String[]{"0", "Solomenka", "https://www.olx.ua/nedvizhimost/kvartiry-komnaty/arenda-kvartir-komnat/kvartira/kiev/?search%5Bfilter_float_price%3Afrom%5D=5000&search%5Bfilter_float_price%3Ato%5D=10000&search%5Bfilter_float_number_of_rooms%3Afrom%5D=1&search%5Bfilter_float_number_of_rooms%3Ato%5D=1&search%5Bphotos%5D=1&search%5Bdistrict_id%5D=17"});
        this.list.add(new String[]{"1", "Goloseevo", "https://www.olx.ua/nedvizhimost/kvartiry-komnaty/arenda-kvartir-komnat/kvartira/kiev/?search%5Bfilter_float_price%3Afrom%5D=5000&search%5Bfilter_float_price%3Ato%5D=10000&search%5Bfilter_float_number_of_rooms%3Afrom%5D=1&search%5Bfilter_float_number_of_rooms%3Ato%5D=1&search%5Bphotos%5D=1&search%5Bdistrict_id%5D=1"});
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public List<String[]> getList() {
        return list;
    }
}
