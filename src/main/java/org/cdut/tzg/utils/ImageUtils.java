package org.cdut.tzg.utils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author anlan
 * @date 2019/6/27 8:56
 */
public class ImageUtils {

    public static final String UPLOAD_PATH = System.getProperties().getProperty("user.home")+"/tzg/upload/";
    public static final String URL_PATH = "/images/upload/";
    public static final String DEFAULT_IMAGE_URL = "/images/upload/default/001.jpg";

    /**
     * 上传图片并返回图片的url
     */
    public static String upload(MultipartFile file,String schoolNumber) {
        if(file==null)
            return DEFAULT_IMAGE_URL;
        Calendar cal = Calendar.getInstance();
        //获取当前年月以创建目录，如果没有该目录则创建
        String mediaPath = UPLOAD_PATH+schoolNumber+"/"+cal.get(Calendar.YEAR)+"/"+cal.get(Calendar.MONTH);
        File mediaDir = new File(mediaPath);
        if (!mediaDir.exists()) {
            mediaDir.mkdirs();
        }
        try {
            file.transferTo(new File(mediaDir.getAbsoluteFile(), file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fullUrlPath = URL_PATH+schoolNumber+"/"+cal.get(Calendar.YEAR)+"/"+cal.get(Calendar.MONTH)+"/"+file.getOriginalFilename();
        return fullUrlPath;
    }
}
