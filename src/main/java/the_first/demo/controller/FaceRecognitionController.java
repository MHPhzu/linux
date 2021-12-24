package the_first.demo.controller;

import com.baidu.aip.face.AipFace;
import com.rnkrsoft.bopomofo4j.Bopomofo4j;
import com.rnkrsoft.bopomofo4j.ToneType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;


@RestController
public class FaceRecognitionController {

    @Value("${baidu.humaface.app-id}")
    private String appId;
    @Value("${baidu.humaface.app-key}")
    private String appKey;
    @Value("${baidu.humaface.secret-key}")
    private String secretKey;


    @PostMapping("/registered")
    public String registered(String img, String name) {

        HashMap<String, String> options = new HashMap<>();
        options.put("user_info", name);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");
        String imageType = "BASE64";
        String groupId = "group1";
        JSONObject jsonObject = new AipFace(appId, appKey, secretKey).addUser(img, imageType, groupId, name, options);
        System.out.println(jsonObject.toString());
        return "SUCCESS".equals(jsonObject.get("error_msg")) ?
                "success" :
                "error";
    }

    /**
     * 人脸搜索
     *
     * @param img 图片Base64码
     * @return
     */
    @PostMapping("/verification")
    public String verification(String img, HttpSession session) {
        HashMap<String, String> options = new HashMap<>();
        options.put("max_face_num", "1");
        options.put("match_threshold", "80");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        String imageType = "BASE64";
        String groupIdList = "group1";

        JSONObject jsonObject = new AipFace(appId, appKey, secretKey).search(img, imageType, groupIdList, options);
        System.out.println(jsonObject);
        if ("SUCCESS".equals(jsonObject.get("error_msg"))) {
            JSONObject jsonArray = jsonObject.getJSONObject("result").getJSONArray("user_list").getJSONObject(0);
            session.setAttribute("stunum", "Y");
            return jsonArray.get("user_info").toString();
        }
        return "error";
    }

    /**
     * 人脸检测
     *
     * @param img 图片Base64码
     */
    @RequestMapping("/faceDetection")
    public String faceDetection(String img) {
        // 请求参数 Map
        HashMap<String, String> options = new HashMap<>();
        // 包括age,beauty,expression,face_shape,gender,glasses,landmark,landmark72，landmark150，race,quality,eye_status,emotion,face_type信息 逗号分隔. 默认只返回face_token、人脸框、概率和旋转角度
        options.put("face_field", "age,beauty,face_shape");
        // 最多处理人脸的数量，最大值10
        options.put("max_face_num", "1");
        // 	人脸的类型 LIVE表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等IDCARD表示身份证芯片照：二代身份证内置芯片中的人像照片 WATERMARK表示带水印证件照：一般为带水印的小图，如公安网小图 CERT表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片 默认LIVE
        options.put("face_type", "LIVE");
        // 活体检测控制 NONE: 不进行控制 LOW:较低的活体要求(高通过率 低攻击拒绝率) NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率) HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
        options.put("liveness_control", "LOW");

        // 图片类型 BASE64:图片的base64值，base64编码后的图片数据，编码后的图片大小不超过2M；URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；FACE_TOKEN: 人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
        String imageType = "BASE64";

        // 人脸检测
        JSONObject jsonObject = new AipFace(appId, appKey, secretKey).detect(img, imageType, options);
        System.out.println(jsonObject);
        return "success";
    }
}
