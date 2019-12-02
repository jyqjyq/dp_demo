package com.dapeng.demo.util.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.text.DecimalFormat;

public class FileUtils {
    private static Logger log = LoggerFactory.getLogger(FileUtils.class);
    private static DecimalFormat df = new DecimalFormat("#.00");

    /**
     * base64转图片
     * @param base64Str
     * @param filePath 文件存放目录，后面带上 “/”
     * @param fileName 文件名
     * @return 返回文件绝对路径
     */
    public static String base64ToFile(String base64Str, String filePath, String fileName) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            File pathFile = new File(filePath);
            //不存在则创建目录
            if (!pathFile.exists()) {
                boolean flag = pathFile.mkdirs();
                log.info("mkdirs path:{}, result:{}", filePath, flag);
            }
            OutputStream out = new FileOutputStream(new File(filePath + fileName));
            out.write(b);
            out.flush();
            out.close();
            return filePath + fileName;
        } catch (Exception e) {
            log.error("base64Str转文件异常", e);
            return null;
        }
    }

    /**
     * 获取文件大小
     * @param filePath 文件绝对路径
     * @return
     */
    public static String getFileSize(String filePath) {
        long size = new File(filePath).length();
        if (size <= 0)
            return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    /**
     * 输入流转字节数组
     * @param is
     * @return
     */
    public static byte[] toByteArray(InputStream is) {
        byte[] byteArr = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            byteArr = baos.toByteArray();
            is.close();
            baos.close();
        } catch (IOException e) {
            log.error("读取byte数组异常", e);
        }
        return byteArr;
    }

    /**
     * 删除文件或文件夹
     * @param filePath
     */
    public static void delete(String filePath) {
        File file = new File(filePath);
        if(!file.exists())
            return;
        if(file.isFile()) {
            file.delete();
        }else {
            File[] files = file.listFiles();
            if (files != null)
                for(File f : files) {
                    delete(f.getAbsolutePath());
                }
            file.delete();
        }
    }

    /**
     * 格式化文件大小，带单位输出
     * @param size
     * @return
     */
    public static String formatSize(Long size) {
        if (size == null)
            return null;
        String sizeStr;
        if (size < 1024) {
            sizeStr = df.format((double) size) + " B";
        } else if (size < 1048576) {
            sizeStr = df.format((double) size / 1024) + " K";
        } else if (size < 1073741824) {
            sizeStr = df.format((double) size / 1048576) + " M";
        } else {
            sizeStr = df.format((double) size / 1073741824) + " G";
        }
        return sizeStr;
    }
}
