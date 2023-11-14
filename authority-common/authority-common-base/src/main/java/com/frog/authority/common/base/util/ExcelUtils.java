package com.frog.authority.common.base.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.converters.longconverter.LongStringConverter;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * excel工具类
 *
 * @author liuhuan
 */
public class ExcelUtils {

    /**
     * 生成excel文件, 并写入当前响应中
     *
     * @param sheetName sheet名称
     * @param clazz 导出对象class
     * @param list 导出数据
     * @throws IOException
     */
    public static void write(String sheetName, Class<?> clazz, List<?> list) throws IOException {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                if (!clazz.isInstance(item)) {
                    throw new ClassCastException("导出数据必须和类型(" + clazz.getName() + ")一致!");
                }
            });
            HttpServletResponse response = RequestUtils.getResponse();
            write(response.getOutputStream(), sheetName, clazz, list);
        }
    }

    public static void write(OutputStream outputStream, String sheetName, Class<?> clazz, List<?> list) throws IOException {
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, sheetName)
                .head(clazz)
                .registerWriteHandler(ExcelStyle.defaultStyle())
                .registerWriteHandler(new DynamicCellWriteHandler())
                .registerConverter(new LongStringConverter())
                .build();
        excelWriter.write(list, writeSheet);
        excelWriter.finish();
    }

    static class ExcelStyle {

        private static HorizontalCellStyleStrategy CELL_STYLE_STRATEGY;

        public static HorizontalCellStyleStrategy defaultStyle() {
            if (CELL_STYLE_STRATEGY == null) {
                WriteCellStyle headerCellStyle = defaultHeaderCellStyle();
                // 表身单元格样式
                WriteCellStyle bodyCellStyle = defaultBodyCellStyle();
                CELL_STYLE_STRATEGY = new HorizontalCellStyleStrategy(headerCellStyle, bodyCellStyle);
            }
            return CELL_STYLE_STRATEGY;
        }

        /**
         * 表头单元格默认格式
         *
         * @return 表头单元格默认格式
         */
        private static WriteCellStyle defaultHeaderCellStyle() {
            WriteCellStyle writeCellStyle = defaultCellStyle();
            // 设置前景色
            writeCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
            // 设置字体
            WriteFont headerFont = defaultHeaderFont();
            writeCellStyle.setWriteFont(headerFont);
            return writeCellStyle;
        }

        /**
         * 表身单元格默认格式
         *
         * @return 表身单元格默认格式
         */
        public static WriteCellStyle defaultBodyCellStyle() {
            WriteCellStyle cellStyle = defaultCellStyle();
            // 设置前景色
            cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            // 设置四周边框
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            WriteFont writeFont = defaultBodyFont();
            cellStyle.setWriteFont(writeFont);
            return cellStyle;
        }

        /**
         * 默认单元格格式
         *
         * @return 垂直居中、左右居中单元格
         */
        private static WriteCellStyle defaultCellStyle() {
            WriteCellStyle cellStyle = new WriteCellStyle();
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            return cellStyle;
        }


        private static final Short DEFAULT_FONT_SIZE = 12;

        private static final String DEFAULT_FONT_NAME = "宋体";

        /**
         * 默认表头字体
         *
         * @return 默认表头字体
         */
        private static WriteFont defaultHeaderFont() {
            return creatFont(true, DEFAULT_FONT_NAME, DEFAULT_FONT_SIZE);
        }

        /**
         * 默认表身字体
         *
         * @return 默认字体
         */
        private static WriteFont defaultBodyFont() {
            return creatFont(false, DEFAULT_FONT_NAME, DEFAULT_FONT_SIZE);
        }

        private static WriteFont creatFont(Boolean isBold, String fontName, Short size) {
            return creatFont(isBold, fontName, size, null);
        }

        /**
         * 创建字体
         *
         * @param isBold 是否为粗体
         * @param fontName 字体名称
         * @param size 字体大小
         * @param color 字体颜色
         * @return 返回创建之后的字体
         */
        private static WriteFont creatFont(Boolean isBold, String fontName, Short size, Short color) {
            WriteFont headerFont = new WriteFont();
            if (isBold != null) {
                headerFont.setBold(isBold);
            }
            if (!StringUtils.isBlank(fontName)) {
                headerFont.setFontName(fontName);
            }
            if (size != null) {
                headerFont.setFontHeightInPoints(size);
            }
            if (color != null) {
                headerFont.setColor(color);
            }
            return headerFont;
        }
    }

    static class DynamicCellWriteHandler implements CellWriteHandler {

        /**
         * 默认行高
         */
        private static final Short DEFAULT_HEIGHT = 400;

        @Override
        public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                     Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
            // 设置单元格行高
            row.setHeight(DEFAULT_HEIGHT);
        }

        @Override
        public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell,
                                     Head head, Integer relativeRowIndex, Boolean isHead) {
            int columnIndex = cell.getColumnIndex();
            // 获取标题单元格和普通单元格的最大宽度
            int cellWidth = getCellWidth(cell);
            int headWidth = getHeaderWidth(head);
            int maxColumnWidth = Math.max(headWidth, cellWidth);
            // 设置当前单元格为同一列单元格最大宽度
            Sheet sheet = writeSheetHolder.getSheet();
            if (sheet.getColumnWidth(columnIndex) < maxColumnWidth) {
                sheet.setColumnWidth(columnIndex, maxColumnWidth);
            }
        }

        private static final int CHARACTER_WIDTH_UNIT = 300;

        /**
         * 根据单元格内容动态计算单元格宽度
         *
         * @param cell excel单元格
         * @return 单元格宽度
         */
        private int getCellWidth(Cell cell) {
            return getLength(cell) * CHARACTER_WIDTH_UNIT;
        }

        /**
         * 计算单元格内容长度
         *
         * @param cell excel单元格
         * @return 单元格内容长度
         */
        private int getLength(Cell cell) {
            CellType cellType = cell.getCellType();
            String value;
            if (CellType.STRING.equals(cellType)) {
                value = cell.getStringCellValue();
            } else {
                value = String.valueOf(cell.getNumericCellValue());
            }
            int length = 0;
            if (StringUtils.isNotEmpty(value)) {
                for (int i = 0; i < value.length(); i++) {
                    if (value.charAt(i) > 255) {
                        length += 2;
                    } else {
                        length++;
                    }
                }
            }
            return length;
        }

        /**
         * 获取标题单元格宽度
         *
         * @param head 标题单元格
         * @return 标题单元格宽度
         */
        private int getHeaderWidth(Head head) {
            String title = StringPool.EMPTY;
            List<String> headNameList = head.getHeadNameList();
            if (!CollectionUtils.isEmpty(headNameList)) {
                title = headNameList.get(0);
            }
            return title.length() * 2 * CHARACTER_WIDTH_UNIT;
        }
    }
}
