package mkcloudadmin.service.membersmanage.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import mkcloudadmin.mapper.mkcloud.MKCloudMemberInfoMapper;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mkcloudadmin.mapper.mkcloud.FinanceUserBankCardInfoMapper;
import mkcloudadmin.mapper.mkcloud.FinanceUserInviteInfoMapper;
import mkcloudadmin.model.base.Page;
import mkcloudadmin.model.mkcloud.dto.MemberQueryDto;
import mkcloudadmin.model.mkcloud.vo.MKCloudMemberInfoVo;
import mkcloudadmin.service.membersmanage.MembersService;

@Component
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MKCloudMemberInfoMapper memberInfoMapper;

    @Override
    public List<MKCloudMemberInfoVo> queryMemberInfoList(Page<MKCloudMemberInfoVo> page, MemberQueryDto memberQueryDto) {

        String memberCode = memberQueryDto.getMemberCode();
        String memberName = memberQueryDto.getMemberName();
        String memberMobile = memberQueryDto.getMemberMobile();
        String businessCode = memberQueryDto.getBusinessCode();
        String businessName = memberQueryDto.getBusinessName();
        String businessMobile = memberQueryDto.getBusinessMobile();
        String businessStatus = memberQueryDto.getBusinessStatus();
        String registerBeginDate = null;
        if (memberQueryDto.getRegisterBeginDate() != null && !"".equals(memberQueryDto.getRegisterBeginDate())) {
            registerBeginDate = memberQueryDto.getRegisterBeginDate() + " 00:00:00";
        }
        String registerEndDate = null;
        if (memberQueryDto.getRegisterEndDate() != null && !"".equals(memberQueryDto.getRegisterEndDate())) {
            registerEndDate = memberQueryDto.getRegisterEndDate() + " 23:59:59";
        }


        List<MKCloudMemberInfoVo> members = memberInfoMapper.selectMembers(memberCode, memberName, memberMobile, businessCode,
                businessName, businessMobile, businessStatus, registerBeginDate, registerEndDate, page);
        Long count = memberInfoMapper.selectMemberCount(memberCode, memberName, memberMobile, businessCode,
                businessName, businessMobile, businessStatus, registerBeginDate, registerEndDate);
        page.setDataList(members);
        page.setTotalCount(count);

        if (members.isEmpty()) {
            return members;
        }
        AtomicInteger index = new AtomicInteger();
        for (MKCloudMemberInfoVo memberInfo : members) {
            memberInfo.setSeqNo(index.incrementAndGet());
            if ("0".equals(memberInfo.getBusinessType())) {
                memberInfo.setBusinessType("内部推广员");
            } else if ("1".equals(memberInfo.getBusinessType())) {
                memberInfo.setBusinessType("外部推广员");
            }
            if ("0".equals(memberInfo.getHasBusiness())) {
                memberInfo.setHasBusiness("无");
            } else {
                memberInfo.setHasBusiness("有");
            }
            if ("0".equals(memberInfo.getBusinessStatus())) {
                memberInfo.setBusinessStatus("失败");
            } else if ("1".equals(memberInfo.getBusinessStatus())) {
                memberInfo.setBusinessStatus("成功");
            }else if("2".equals(memberInfo.getBusinessStatus())){
                memberInfo.setBusinessStatus("审核中");
            }
        }
        return members;
    }
    @Override
    public Workbook createExcel(MemberQueryDto memberQueryDto) {

        String memberCode = memberQueryDto.getMemberCode();
        String memberName = memberQueryDto.getMemberName();
        String memberMobile = memberQueryDto.getMemberMobile();
        String businessCode = memberQueryDto.getBusinessCode();
        String businessName = memberQueryDto.getBusinessName();
        String businessMobile = memberQueryDto.getBusinessMobile();
        String businessStatus = memberQueryDto.getBusinessStatus();
        String registerBeginDate = memberQueryDto.getRegisterBeginDate();
        String registerEndDate = memberQueryDto.getRegisterEndDate();

        List<MKCloudMemberInfoVo> members = memberInfoMapper.selectFullMembers(memberCode, memberName, memberMobile, businessCode,
                businessName, businessMobile, businessStatus, registerBeginDate, registerEndDate);

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);            //设置字体的大小
        font.setFontName("微软雅黑");                        //设置字体的样式，如：宋体、微软雅黑等
        font.setItalic(false);
        font.setBoldweight((short) (170 * 200));//斜体true为斜体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    //对文中进行加粗
        font.setColor(HSSFColor.BLACK.index);            //设置字体的颜色
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Sheet sheet = workbook.createSheet();
        sheet.setDefaultColumnWidth(15);
        // 第一行文字说明
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("排序");

        cell = row.createCell(1, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("客户编号");

        cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("客户手机号");

        cell = row.createCell(3, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("客户姓名");

        cell = row.createCell(4, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("单位地址");

        cell = row.createCell(5, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("推广员编号");

        cell = row.createCell(6, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("推广员姓名");

        cell = row.createCell(7, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("推广员类型");

        cell = row.createCell(8, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("注册时间");

        cell = row.createCell(9, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("有无业务办理");

        cell = row.createCell(10, Cell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue("办理状态");

        AtomicInteger index = new AtomicInteger();
        if (members != null && members.size() != 0) {
            int length = members.size();
            // 下面是具体内容
            for (int i = 0; i < length; i++) {
                row = sheet.createRow(i + 1);
                // 排序
                cell = row.createCell(0, cell.CELL_TYPE_STRING);
                cell.setCellValue(index.incrementAndGet());
                // 客户编号
                cell = row.createCell(1, cell.CELL_TYPE_STRING);
                cell.setCellValue(members.get(i).getMemberCode());

                // 客户手机号
                cell = row.createCell(2, cell.CELL_TYPE_STRING);
                cell.setCellValue(members.get(i).getMobileNum());

                // 客户姓名
                cell = row.createCell(3, cell.CELL_TYPE_STRING);
                cell.setCellValue(members.get(i).getMemberName());

                // 单位地址
                cell = row.createCell(4, cell.CELL_TYPE_STRING);
                cell.setCellValue(members.get(i).getWorkAddress());

                // 推广员编号
                cell = row.createCell(5, cell.CELL_TYPE_STRING);
                cell.setCellValue(members.get(i).getBusinessCode());

                //推广员姓名
                cell = row.createCell(6, Cell.CELL_TYPE_STRING);
                cell.setCellValue(members.get(i).getBusinessName());

                //推广员类型
                cell = row.createCell(7, Cell.CELL_TYPE_STRING);
                if ("0".equals(members.get(i).getBusinessType())) {
                    cell.setCellValue("内部推广员");
                }else{
                    cell.setCellValue("");
                }
                // 注册时间
                cell = row.createCell(8, cell.CELL_TYPE_STRING);
                cell.setCellValue(members.get(i).getRegisterTime());

                //有无业务办理
                cell = row.createCell(9, Cell.CELL_TYPE_STRING);
                if ("0".equals(members.get(i).getHasBusiness())) {
                    cell.setCellValue("无");
                } else {
                    cell.setCellValue("有");
                }
                //办理状态
                cell = row.createCell(10, Cell.CELL_TYPE_STRING);
                if ("0".equals(members.get(i).getBusinessStatus())) {
                    cell.setCellValue("失败");
                } else if ("1".equals(members.get(i).getBusinessStatus())) {
                    cell.setCellValue("成功");
                } else  if("2".equals(members.get(i).getBusinessStatus())){
                    cell.setCellValue("审核中");
                }else{
                    cell.setCellValue("");
                }
            }
        }
        return workbook;
    }
}
