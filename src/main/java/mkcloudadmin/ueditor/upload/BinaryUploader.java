package mkcloudadmin.ueditor.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mkcloudadmin.MKCloudAdminApplication;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import mkcloudadmin.ueditor.PathFormat;
import mkcloudadmin.ueditor.define.AppInfo;
import mkcloudadmin.ueditor.define.BaseState;
import mkcloudadmin.ueditor.define.FileType;
import mkcloudadmin.ueditor.define.State;
import mkcloudadmin.util.LogUtil;

public class BinaryUploader {
	private final static Logger logger = LoggerFactory.getLogger(BinaryUploader.class);
	
    public static final State save(HttpServletRequest request,
                                   Map<String, Object> conf) {
//		FileItemStream fileStream = null;
//		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

//		ServletFileUpload upload = new ServletFileUpload(
//				new DiskFileItemFactory());
//
//        if ( isAjaxUpload ) {
//            upload.setHeaderEncoding( "UTF-8" );
//        }

        try {
//			FileItemIterator iterator = upload.getItemIterator(request);
//
//			while (iterator.hasNext()) {
//				fileStream = iterator.next();
//
//				if (!fileStream.isFormField())
//					break;
//				fileStream = null;
//			}
//
//			if (fileStream == null) {
//				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
//			}

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());
            if (multipartFile == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String savePath = (String) conf.get("savePath");
//            String originFileName = fileStream.getName();
            String originFileName = multipartFile.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0,
                    originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            savePath = PathFormat.parse(savePath, originFileName);

            String physicalPath = (String) conf.get("rootPath") + savePath;

//            String basePath=(String) conf.get("basePath");
//            String physicalPath = basePath + savePath;

//            InputStream is = fileStream.openStream();
            InputStream is = multipartFile.getInputStream();

            /**
             * 上传到阿里云：xhj添加
             */
            //*******************开始***********************
            String fileName = new StringBuffer().append(new Date().getTime()).append(multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf("."))).toString();
            State storageState = null;
            try {
                String url = MKCloudAdminApplication.appContect.getBean(UploadOSSUtil.class).uploadImgAliyun(is,fileName);
//                new UploadOSSUtil();
//                String url = UploadOSSUtil.uploadImgAliyun(is,fileName);
                storageState = StorageManager.saveFileByInputStream(is,
                        physicalPath, maxSize);
                storageState.putInfo("state", "SUCCESS");
                //url:返回前端的访问路径，请根据自己实际情况填写
                storageState.putInfo("url",url);
                storageState.putInfo("title", fileName);
                storageState.putInfo("original", fileName);
            } catch (Exception e) {
                storageState.putInfo("state", "文件上传失败!");
                storageState.putInfo("url","");
                storageState.putInfo("title", "");
                storageState.putInfo("original", "");
                logger.warn(LogUtil.getFormatLog(storageState, "文件上传失败"),e);
            }



            //********************结束**********************


           /*修改云储存*/
//            State storageState = StorageManager.saveFileByInputStream(is,
//                    physicalPath, maxSize);
            /*修改云储存*/

            is.close();

            if (storageState.isSuccess()) {
                storageState.putInfo("url", PathFormat.format(savePath));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
//        } catch (FileUploadException e) {
//            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
        } catch (IOException e) {
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
