    package com.shuxin.foodsstore.commons.utils;

    import lombok.extern.slf4j.Slf4j;
    import org.springframework.web.multipart.MultipartFile;

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.*;
    import java.text.SimpleDateFormat;
    import java.util.*;
    /**
     * @ClassName: FileUtils
     * @Description: 上传下载工具类.
     * @Author liu yang
     * @Date 2018/1/9 16:16
     */
    @Slf4j
    public class FileUtils {


        public static Map<String, Object> upLoadFile(MultipartFile file,
                                                     HttpServletRequest request,
                                                     String basePath) {

            Map<String, Object> result = new HashMap<>();

            /**TODO("分类文件名 后期保存都字典表中")*/
            final String uploadPath = "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());//上传的组合路径

            final String targetName = file.getOriginalFilename(); //上传的文件名

            File dirPath = new File(basePath + uploadPath);

            if (!dirPath.exists()) dirPath.mkdirs();//目录不存在，递归创建

            String signID = UUID.randomUUID().toString();// 文件名用uuid生成，规避中文文件名称下载乱码问题


            //TODO(后期保存到字典表中)
            if (Arrays.asList(".jpg", ".png", ".gif", ".bmp", ".jpeg").contains(getSuffix(targetName)) == false) {//上传文件名检查（白名单机制）true:通过，false：没有通过
                result.put("code", 500);
                result.put("msg", "上传文件格式不支持");
            } else {
                try {

                    File saveFile = new File(dirPath + "\\" + signID + getSuffix(targetName));
                    file.transferTo(saveFile);//保存文件
                    result.put("code", 200);
                    result.put("msg", "上传成功!!!");
                    result.put("data", saveFile.getPath()/*new HashMap<String, Object>() {
                        {
                            // put("src", uploadPath + "\\" + signID + getSuffix(targetName));
                            put("src", saveFile.getPath());

                        }
                    }*/);

                    result.put("id", signID);
                } catch (Exception e) {
                    result.put("code", 400);
                    result.put("msg", "上传失败!!!");

                    e.printStackTrace();

                }
            }

            return result;

        }

        public static void downloadFile(String fileName,
                                        String filePath,
                                        String basePath,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException {


            InputStream inputStream = null;
            OutputStream outputStream = null;

            String imgPath = PathUtil.getPath(filePath);
            try {

                inputStream = new BufferedInputStream(new FileInputStream(imgPath));
                outputStream = response.getOutputStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
                response.flushBuffer();
            } catch (Exception e) {
                log.info("--通过流的方式获取文件异常--" + e.getMessage());
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        }

        /**
         * 文件后缀
         *
         * @return
         */
        public static String getSuffix(String name) {
            String suffix = name;
            if (suffix.contains("."))
                suffix = suffix.substring(suffix.lastIndexOf("."));
            else
                suffix = "";
            return suffix;
        }

    }
