package cc.moecraft.scripts;

import cc.moecraft.irc.osubot.Main;
import cc.moecraft.irc.osubot.minigames.fingers.FingersPlayerType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 此类由 Hykilpikonna 在 2018/05/24 创建!
 * Created by Hykilpikonna on 2018/05/24!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class FingersStatisticXlsxGenerator
{
    public static void main(String[] args) throws IOException
    {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("FingersStatistic");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        CellStyle won = workbook.createCellStyle();
        won.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        won.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle lose = workbook.createCellStyle();
        lose.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        lose.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle draw = workbook.createCellStyle();
        draw.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        draw.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 上面的 1-9
        Row headerRow = sheet.createRow(0);

        for (int i = 1; i < 10; i++)
        {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(i);
            cell.setCellStyle(headerCellStyle);
        }

        // 右边的 1-9
        for (int i = 1; i < 10; i++)
        {
            Row row = sheet.createRow(i);

            Cell cell = row.createCell(0);
            cell.setCellValue(i);
            cell.setCellStyle(headerCellStyle);
        }

        // 记录数据
        for (int i = 1; i < 10; i++)
        {
            Row thisRow = sheet.getRow(i);

            for (int j = 1; j < 10; j++)
            {
                Cell thisCell = thisRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                FingersPlayerType result = get1v1Result(i, j, FingersPlayerType.Player, 0);

                if (result == null)
                {
                    thisCell.setCellValue("Draw");
                    thisCell.setCellStyle(draw);
                }
                else if (result == FingersPlayerType.Player)
                {
                    thisCell.setCellValue("Won");
                    thisCell.setCellStyle(won);
                }
                else if (result == FingersPlayerType.Bot)
                {
                    thisCell.setCellValue("Lost");
                    thisCell.setCellStyle(lose);
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(Main.PATH + File.separator + "deprecated/experimental" + File.separator + "result2.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    /**
     * 判断1v1胜败结果 ( 无死循环规律 )
     * 注意: 这个是判定执行次数来判断死循环的
     * @param playerHand 玩家手上的数
     * @param botHand 机器人手上的数
     * @param whosTurn 该谁走
     * @return 对于数组第一个数表示的玩家的胜败
     */
    public static FingersPlayerType get1v1Result(int playerHand, int botHand, FingersPlayerType whosTurn, int count)
    {
        count ++;
        if (count > 500) return null;

        if (botHand == 0) return FingersPlayerType.Bot;
        if (playerHand == 0) return FingersPlayerType.Player;

        if (whosTurn == FingersPlayerType.Player) playerHand = (playerHand + botHand) % 10;
        else botHand = (playerHand + botHand) % 10;

        return get1v1Result(playerHand, botHand, whosTurn.getTheOther(), count);
    }
}
