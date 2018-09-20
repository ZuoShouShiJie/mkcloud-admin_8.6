package mkcloudadmin.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author yaolei
 * @Title: ParseExcel
 * @ProjectName mkcloud-admin
 * @Description:
 * @date 2018/7/11下午5:43
 */
public class ParseExcelUtil {

    public static Workbook readExcel(MultipartFile file) throws IOException {

        Workbook wb = null;

        if(file==null){
            return null;
        }
        String name  = file.getOriginalFilename();
        String extString = name.substring(name.lastIndexOf("."));
        InputStream is = file.getInputStream();

        try {
            if(".xls".equals(extString)){
                 wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                 wb = new XSSFWorkbook(is);
            }else{
                 wb = null;
            }
            is = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wb;
    }

    public static Object getCellFormatValue(Cell cell){

        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        //用于转化为日期格式
                        Date d = cell.getDateCellValue();

                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        cellValue = formater.format(d);
                    }else {
                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                       // 这时就相当于XSSFCell.CELL_TYPE_STRING:
                        cellValue= cell.getRichStringCellValue().getString();
                       // cellValue = String.valueOf(cell.getNumericCellValue());
                    }

                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

}
