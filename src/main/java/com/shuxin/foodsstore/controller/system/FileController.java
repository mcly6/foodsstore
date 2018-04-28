package com.shuxin.foodsstore.controller.system;


import com.shuxin.foodsstore.commons.enums.ResultEnum;
import com.shuxin.foodsstore.commons.exception.MyRuntimeException;
import com.shuxin.foodsstore.commons.utils.FileUtils;
import com.shuxin.foodsstore.commons.utils.ResultVOUtils;
import com.shuxin.foodsstore.entity.system.UploadFile;
import com.shuxin.foodsstore.service.system.UploadFileService;
import com.shuxin.foodsstore.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @ClassName: FileController
 * @Description: 文件控制.
 * @Author liu yang
 * @Date 2018/1/8 11:41
 */
@Controller
@RequestMapping("/sys/file")
@Slf4j
public class FileController {

    //TODO(图片存储路径, 后期配置到字典表中)
    public static final String BASEPATH = "D:\\temp\\Uploads\\";
//    public static final String BASEPATH = "D:\\temp\\Uploads\\productInfo";

    @Autowired
    private UploadFileService uploadFileService;


    /**
     * @Title: uploadFile 上传图片方法
     * @Param [file 文件base64
     * ,request
     * ,type 业务文件夹分类
     * ]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public ResultVO uploadFile(@RequestParam(value = "upFile") MultipartFile file,
                               @RequestParam(value = "type") String type,
                               HttpServletRequest request) {

        Integer code = 0;

        String msg = "";


        Map<String, Object> upFileMap = FileUtils.upLoadFile(file, request, BASEPATH + type);

        System.out.println(type);
        code = (Integer) upFileMap.get("code");

        if (200 == code) {
            log.info("上传文件{}", upFileMap.get("data"));
            String filePath = (String) upFileMap.get("data");

            UploadFile uploadFile = new UploadFile();

            /** TODO(字典表设置) */
            uploadFile.setId((String) upFileMap.get("id"));
            uploadFile.setFileModule(type);
            uploadFile.setFilePath(filePath);
            uploadFile.setFileType(FileUtils.getSuffix(file.getOriginalFilename()));

            UploadFile resultFile = uploadFileService.save(uploadFile);
            if (resultFile == null) {

                log.info("文件上传失败{}", file.getOriginalFilename());
                throw new MyRuntimeException(ResultEnum.FILE_UPLOAD_ERROR.getMessage());

            }

            return ResultVOUtils.success(upFileMap.get("id"));
        } else {
            return ResultVOUtils.error(400, (String) upFileMap.get("msg"));
        }

    }

    /**
     * @Title: downloadFile 图片下载
     * @Param [imgsrc]
     * @Return com.shuxin.foodsstore.vo.ResultVO
     */
    //@GetMapping("/imgShow")
    @RequestMapping("/imgShow")
    @ResponseBody
    public void downloadFile(@RequestParam(value = "src") String fileId,
                             HttpServletResponse response,
                             HttpServletRequest request) throws Exception {


        // response.setContentType("image/jpeg;charset=utf-8");

        UploadFile uploadFile = uploadFileService.findOne(fileId);

        String fileName = uploadFile.getId() + uploadFile.getFileType();  //imgSrc.substring(imgSrc.lastIndexOf(File.separator) + 1);

//        String userAgent = request.getHeader("user-agent").toLowerCase();

        fileName = URLEncoder.encode(fileName, "UTF-8");
       /* if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        }*/
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);

        FileUtils.downloadFile(fileName, uploadFile.getFilePath(), BASEPATH, request, response);

    }
}
