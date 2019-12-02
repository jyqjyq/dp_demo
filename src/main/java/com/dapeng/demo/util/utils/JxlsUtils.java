package com.dapeng.demo.util.utils;

public class JxlsUtils {

//    private static final String CONTENT_TYPE = "application/vnd.ms-excel";
//
//
//    /**
//     * 导出Excel
//     * @param templatePath 模板路径
//     * @param exportFileName 导出的文件名
//     * @param data 数据集合
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    public static void exportExcel(String templatePath, String exportFileName, Map<String, Object> data,
//                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
//        exportFileName = URLEncoder.encode(exportFileName, "UTF-8");
//        Context context = new Context(data);
//        response.setContentType(CONTENT_TYPE);
//        response.setHeader("content-disposition", "attachment;filename=" + exportFileName + ".xls");
//        ServletOutputStream os = response.getOutputStream();
//        InputStream is = JxlsUtils.class.getClassLoader().getResourceAsStream(templatePath);
//        JxlsHelper.getInstance().processTemplate(is, os, context);
//        is.close();
//    }

}
