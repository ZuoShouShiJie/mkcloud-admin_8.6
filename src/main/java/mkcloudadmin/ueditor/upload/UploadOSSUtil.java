package mkcloudadmin.ueditor.upload;

/**
 * 上传到阿里云：xhj
 *
 *
 */
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadOSSUtil {
    public UploadOSSUtil(){}

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;



    public String  uploadImgAliyun(InputStream inputStream ,String fileName) {

        OSSClient client = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        //此处"images/companyNewsImages/"+fileName,表示上传至阿里云中images文件夹下的companyNewsImages文件夹中，请修改为自己的路径即可
        client.putObject(bucketName, "images/companyNewsImages/"+fileName, inputStream);
        Date expiration = new Date(new Date().getTime() + 3600*1000*24*365*10);
        URL url = client.generatePresignedUrl(bucketName, "images/companyNewsImages/"+fileName, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

}
