package the_first.demo.controller;

import cn.hutool.core.io.FileUtil;
import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SpeechRecognitionController {

    @Value("${baidu.speech.app-id}")
    private String appId;
    @Value("${baidu.speech.app-key}")
    private String appKey;
    @Value("${baidu.speech.secret-key}")
    private String secretKey;

    /**
     * 语音转文字
     */
    @RequestMapping("/speechRecognition")
    public void speechRecognition() {
        // 语音文件转成二进制
        byte[] path = FileUtil.readBytes("D:\\浏览器下载\\16k.pcm");
        // 语音识别：语音文件二进制、语音文件的格式、采样率、多参数
        JSONObject jsonObject = new AipSpeech(appId, appKey, secretKey).asr(path, "pcm", 16000, null);
        System.out.println(jsonObject);
    }


}
