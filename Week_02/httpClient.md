相关依赖

```xml
<!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
<dependency>
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpclient</artifactId>
  <version>4.5.12</version>
</dependency>
<dependency>
  <groupId>commons-logging</groupId>
  <artifactId>commons-logging</artifactId>
  <version>1.2</version>
</dependency>
```

```java
package top.tangtian.week2.visit8801;


import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author tangtian
 * @version 1.0
 * @className HttpclientUtils
 * @description
 * @date 2020/10/26 6:54 AM
 **/
public class HttpclientUtils {
  private static final String URL = "http://localhost:8801";
  private static RequestConfig requestConfig = RequestConfig.custom()
      .setSocketTimeout(30000)
      .setConnectTimeout(30000)
      .setConnectionRequestTimeout(30000)
      .build();
  public static String getServe(){
    HttpGet httpGet = new HttpGet(URL);// 创建get请求
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    HttpEntity entity = null;
    String responseContent = null;
    try {
      // 创建默认的httpClient实例.
      httpClient = HttpClients.createDefault();
      httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
      httpGet.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.3 Safari/605.1.15");
      httpGet.setHeader("Connection","keep-alive");
      httpGet.setConfig(requestConfig);
      // 执行请求
      response = httpClient.execute(httpGet);
      entity = response.getEntity();
      responseContent = EntityUtils.toString(entity, "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        // 关闭连接,释放资源
        if (response != null) {
          response.close();
        }
        if (httpClient != null) {
          httpClient.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return responseContent;
  }
  public static void main (String[] args) throws InterruptedException {
       HttpclientUtils.getServe();
  }
}

```