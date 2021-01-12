import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mengwei
 * @Date: 2021-1-4 9:33
 */
public class excell {
    public static void main(String[] args) {
        Sheet sheet;
        Workbook book;
        Cell cell1, cell2;
        List<ZongCodeList> zongCodeLists = new ArrayList<>();
        try {
            //为要读取的excel文件名
            book = Workbook.getWorkbook(new File("F:\\myProject\\gitAndProgram\\myProject\\src\\main\\resources\\rongmu.xls"));

            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet = book.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                cell1 = sheet.getCell(0, i);//（列，行）
                cell2 = sheet.getCell(1, i);
                if ("".equals(cell1.getContents())) {//如果读取的数据为空
                    break;
                }
                ZongCodeList zongCodeList = new ZongCodeList();
                zongCodeList.setZoneCode(cell1.getContents());
                zongCodeList.setZoneName(cell2.getContents());
                zongCodeLists.add(zongCodeList);
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ZongCodeList zongCodeList : zongCodeLists) {
            List<String> methodCodes = new ArrayList<>();
            methodCodes.add("101001");
            methodCodes.add("101005");
            methodCodes.add("101003");
            methodCodes.add("101004");
            methodCodes.add("101006");
            List<String> codes = new ArrayList<>();
            codes.add("101001029");
            codes.add("101005011");
            codes.add("101003018");
            codes.add("101004012");
            codes.add("101006019");
            for (int i = 0; i < 5; i++) {
                String id = UUIDGenerator.generate();
                System.out.println("DELETE  FROM gpx_basic.basic_dict WHERE ID = '"+id+"';\n");
                System.out.println("INSERT INTO `gpx_basic`.`basic_dict`(`id`, `DICT_TYPE`, `DICT_CODE`, " +
                        "`DICT_NAME`, `DICT_VALUE`, `REMARK`, `STATUS`, `ZONE_CODE`, " +
                        "`ZONE_NAME`, `CREATE_USER_ID`, `CREATE_USER_NAME`, " +
                        "`CREATE_TIME`, `UPDATE_TIME`, `UPDATE_USER_ID`, `UPDATE_USER_NAME`) " +
                        "VALUES ('" + id + "', '" + methodCodes.get(i) + "', '" + codes.get(i) + "', " +
                        "'评审人员意见表', '59', NULL, 1, '" + zongCodeList.getZoneCode() + "', '" + zongCodeList.getZoneName() + "', " +
                        "'D4865F7DB771491B89E96CB48B67D600', '系统管理员', " +
                        "'2020-12-31 11:36:58', '2020-12-31 11:36:58'," +
                        " 'D4865F7DB771491B89E96CB48B67D600', '系统管理员');\n");
            }
        }
    }


}
