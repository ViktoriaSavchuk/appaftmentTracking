package olx;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class HttpData {

    String data;

    @Bean
    public String creatingData(String url) {
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(new HttpGet(url))
        ) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                this.data = IOUtils.toString(entity.getContent());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.data;
    }
}
