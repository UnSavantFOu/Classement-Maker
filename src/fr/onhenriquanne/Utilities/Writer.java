package fr.onhenriquanne.Utilities;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Label;
import jxl.write.Number;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Writer {

    private static final String EXCEL_FILE_LOCATION = "./Outpout.xls";

    public Writer(ArrayList<String> text, ArrayList<String> text1){

        if (new File(EXCEL_FILE_LOCATION).exists()){
            new File(EXCEL_FILE_LOCATION).delete();
        }

        if (true){
            WritableWorkbook myFirstWbook = null;
            try {

                myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

                WritableSheet excelSheet = myFirstWbook.createSheet("Competition", 0);


                WritableCellFormat cellFormat = new WritableCellFormat();
                cellFormat.setAlignment(Alignment.CENTRE);



                Label label = new Label(0, 0, "Name", cellFormat);
                excelSheet.addCell(label);
                label = new Label(1, 0, "Surname", cellFormat);
                excelSheet.addCell(label);
                label = new Label(2, 0, "Competition Points", cellFormat);
                excelSheet.addCell(label);
                label = new Label(3, 0, "Last Year Point", cellFormat);
                excelSheet.addCell(label);
                label = new Label(4, 0, "Delta Point", cellFormat);
                excelSheet.addCell(label);
                label = new Label(5, 0, "Actual Points", cellFormat);
                excelSheet.addCell(label);

                label = new Label(6, 0, "New Points", cellFormat);
                excelSheet.addCell(label);
                Number number = new Number(0,0,1);


                for (int i=0;i<text.size();i++){
                    String[] data = text.get(i).split(",");

                    label = new Label(0, i+1, data[1], cellFormat);
                    excelSheet.addCell(label);

                    label = new Label(1, i+1, data[2], cellFormat);
                    excelSheet.addCell(label);

                    number  = new Number(2, i+1, Double.parseDouble(data[3]), cellFormat);
                    excelSheet.addCell(number);

                    number  = new Number(3, i+1, Double.parseDouble(data[4]), cellFormat);
                    excelSheet.addCell(number);

                    NumberFormat nf = new DecimalFormat("#.##");


                    number  = new Number(4, i+1, Double.parseDouble(String.format(String.valueOf(Double.parseDouble(data[3]) - Double.parseDouble(data[4])), nf)), cellFormat);
                    excelSheet.addCell(number);



                    number  = new Number(5, i+1, Double.parseDouble(data[5]), cellFormat);
                    excelSheet.addCell(number);

                    number  = new Number(6, i+1, Double.parseDouble(String.format(String.valueOf(Double.parseDouble(data[5]) + Double.parseDouble(data[3]) - Double.parseDouble(data[4])), nf)), cellFormat);
                    excelSheet.addCell(number);
                }
                for (int c = 0; c < 10; c++) {
                    CellView cell = excelSheet.getColumnView(c);

                    cell.setSize(18*256);
                    excelSheet.setColumnView(c, cell);
                }

                excelSheet = myFirstWbook.createSheet("Ranking", 0);


                label = new Label(0, 0, "Rank", cellFormat);
                excelSheet.addCell(label);

                label = new Label(1, 0, "Name", cellFormat);
                excelSheet.addCell(label);

                label = new Label(2, 0, "Surname", cellFormat);
                excelSheet.addCell(label);

                label = new Label(3, 0, "Points", cellFormat);
                excelSheet.addCell(label);
                number = new Number(0,0,1);


                for (int i=0;i<text1.size();i++){
                    String[] data = text1.get(i).split(",");
                    number = new Number(0,i+1,i+1);
                    excelSheet.addCell(number);
                    label = new Label(1,i+1,data[1]);
                    excelSheet.addCell(label);
                    label = new Label(2,i+1,data[2]);
                    excelSheet.addCell(label);
                    number = new Number(3,i+1,Double.parseDouble(data[data.length-1]));
                    excelSheet.addCell(number);
                }
                for (int c = 0; c < 10; c++) {
                    CellView cell = excelSheet.getColumnView(c);
                    cell.setSize(18*256);
                    excelSheet.setColumnView(c, cell);
                }




                myFirstWbook.write();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            } finally {

                if (myFirstWbook != null) {
                    try {
                        myFirstWbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }
                }


            }


        }



    }

}
