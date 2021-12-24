package the_first.demo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class TranslationController {

    /**
     * 通用翻译API HTTP地址
     */
    private final String apiHttp = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    /**
     * 通用翻译API HTTPS地址
     */
    private final String apiHttps = "https://fanyi-api.baidu.com/api/trans/vip/translate";

    /**
     * 通用翻译
     *
     * @param query 请求翻译query
     */
    @RequestMapping("/autoTranslation/{query}")
    public void autoTranslation(@PathVariable("query") String query) {
        // 请求参数Map
        Map<String, Object> paramMap = new LinkedHashMap<>();
        // appid
        String appId = "face";
        paramMap.put("appid", appId);
        // 请求翻译query
        paramMap.put("q", query);
        // 翻译源语言
        paramMap.put("from", "auto");
        // 译文语言（不可设置为 auto）
        paramMap.put("to", "en");
        // 随机数
        Integer random = RandomUtil.randomInt();
        paramMap.put("salt", random);
        // 签名（appid+q+salt+密钥的MD5值）
        paramMap.put("sign", SecureUtil.md5(appId + query + random + "xxxxxxxxxxx"));
        // 执行请求
        String body = HttpRequest.post(apiHttps)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .form(paramMap)
                .timeout(5000)
                .execute(true)
                .body();
        System.out.println(body);

    }
}
