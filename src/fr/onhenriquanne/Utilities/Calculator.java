package fr.onhenriquanne.Utilities;

import sun.security.provider.Sun;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Calculator {
    public static void main(String[] args){

        File file = new File("./Outpout.xls");
        if (file.exists()){
            file.delete();
        }
        if (args.length==27){
            new Calculator(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5], Integer.parseInt(args[6]), Integer.parseInt(args[7]), Integer.parseInt(args[8]), Integer.parseInt(args[9]), Integer.parseInt(args[10]), args[11], Integer.parseInt(args[12]), Integer.parseInt(args[13]), Integer.parseInt(args[14]), Integer.parseInt(args[15]), args[16], Integer.parseInt(args[17]), Integer.parseInt(args[18]), Integer.parseInt(args[19]), Integer.parseInt(args[20]), args[21], Integer.parseInt(args[22]), Integer.parseInt(args[23]), Integer.parseInt(args[24]), Integer.parseInt(args[25]), Integer.parseInt(args[26]));
        }else{
            System.out.println("There is nothing");
        }

        //new Calculator("http://www.engarde-service.com/files/stadeclermontoisescrime/cnm172020/fhs/index.php?page=tireurs.htm", "http://www.engarde-service.com/files/stadeclermontoisescrime/cnm172020/fhs/index.php?page=clasfinal.htm", "http://www.engarde-service.com/files/stadeclermontoisescrime/cnm172020/fhd/index.php?page=clasfinal.htm", "http://www.escrime-ffe.fr/competition/7961");
    }

    public static ArrayList<String> ActualRanking;
    public static ArrayList<String> Inscription;
    public static ArrayList<String> Elite;
    public static ArrayList<String> Result;
    public static ArrayList<String> FinalResult;
    public static ArrayList<String> AnneePrecedente;
    public Calculator(String inscription, int totalInscription, int nameInscription, int surnameInscription, int rankingInscription,String actualRanking, int totalRanking, int nameRanking, int surnameRanking, int rankingRanking, int pointRanking, String Saturday, int totalSaturday, int nameSaturday, int surnameSaturday, int rankingSaturday, String Sunday, int totalSunday, int nameSunday, int surnameSunday, int rankingSunday, String Last, int totalLast, int nameLast, int surnameLast, int pointLast, int rankingLast){

        ActualRanking = Parser.getActualRank(actualRanking, totalRanking, nameRanking, surnameRanking, rankingRanking, pointRanking);
        Inscription = Parser.EngardeParser(inscription, totalInscription, nameInscription, surnameInscription, rankingInscription);
        Parser.Inscrits = Inscription;
        Result = Parser.EngardeParser(Saturday, totalSaturday, nameSaturday, surnameSaturday, rankingSaturday);
        Elite = Parser.EngardeParser(Sunday, totalSunday, nameSunday, surnameSunday, rankingSunday);
        AnneePrecedente = Parser.getLastCompetitionRankPoint(Last, totalLast, nameLast, surnameLast, rankingLast, rankingLast);
        for (int i=0;i<Elite.size();i++){
            Result.set(i, Elite.get(i));
        }
        for (int i=Result.size()-1;i>1;i--){
            if (Result.get(i).startsWith("10000")){
                Result.remove(i);
            }else{
                break;
            }
        }
        FinalResult = Result;
        Parser.FinalResult = FinalResult;

        //-----------------------------------------------------
        int nbTireurs = FinalResult.size();
        int CoefForce = Parser.getCompetitionForce();
        double CoefCompetition = 1.5;//CAR CN

        for (int i=0;i<FinalResult.size();i++){
            int classement = Integer.parseInt(FinalResult.get(i).split(",")[0]);

            double points = CoefCompetition*CoefForce*(1.01-(Math.log(classement)/Math.log(nbTireurs)));
            NumberFormat nf = new DecimalFormat("0.##");
            String s = nf.format(points).replace(",",".");
            FinalResult.set(i,FinalResult.get(i)+","+s);
        }

        for (int i=0;i<FinalResult.size();i++){
            for (int a=0;a<AnneePrecedente.size();a++){
                if (AnneePrecedente.get(a).split(",")[1].toUpperCase().equals(FinalResult.get(i).split(",")[1].toUpperCase()) && AnneePrecedente.get(a).split(",")[2].toUpperCase().equals(FinalResult.get(i).split(",")[2].toUpperCase())){
                    String[] data = AnneePrecedente.get(a).split(",");
                    if (data.length==5){
                        FinalResult.set(i, FinalResult.get(i)+","+data[3]+data[4]);
                    }else{
                        FinalResult.set(i, FinalResult.get(i)+","+data[3]);
                    }
                    break;
                }
            }
            if (FinalResult.get(i).split(",").length < 5){
                FinalResult.set(i, FinalResult.get(i)+",0");
            }
        }


        for (int i=0;i<FinalResult.size();i++){
            for (int a=0;a<ActualRanking.size();a++){
                if (ActualRanking.get(a).split(",")[1].toUpperCase().equals(FinalResult.get(i).split(",")[1].toUpperCase()) && ActualRanking.get(a).split(",")[2].toUpperCase().equals(FinalResult.get(i).split(",")[2].toUpperCase())){
                    String[] data = ActualRanking.get(a).split(",");
                    if (data.length==5){
                        FinalResult.set(i, FinalResult.get(i)+","+data[3]+data[4]);
                    }else{
                        FinalResult.set(i, FinalResult.get(i)+","+data[3]);
                    }
                    break;
                }
            }
            if (FinalResult.get(i).split(",").length < 6){
                FinalResult.set(i, FinalResult.get(i)+",0");
            }
        }


        for (int i=0;i<ActualRanking.size();i++){
            if(ActualRanking.get(i).split(",").length == 5){
                ActualRanking.set(i, ActualRanking.get(i).split(",")[0]+","+ActualRanking.get(i).split(",")[1]+","+ActualRanking.get(i).split(",")[2]+","+ActualRanking.get(i).split(",")[3]+ActualRanking.get(i).split(",")[4]);
            }
        }

        for (int i=0;i<FinalResult.size();i++){
            for (int a=0;a<ActualRanking.size();a++){
                if (FinalResult.get(i).split(",")[1].toUpperCase().equals(ActualRanking.get(a).split(",")[1].toUpperCase()) && FinalResult.get(i).split(",")[2].toUpperCase().equals(ActualRanking.get(a).split(",")[2].toUpperCase())){
                    String[] actual = ActualRanking.get(a).split(",");
                    ActualRanking.set(a,"");
                    for (int b=0;b<actual.length-1;b++){
                        ActualRanking.set(a,ActualRanking.get(a)+actual[b]+",");
                    }
                    ActualRanking.set(a,ActualRanking.get(a)+String.valueOf(Double.parseDouble(FinalResult.get(i).split(",")[5])-Double.parseDouble(FinalResult.get(i).split(",")[4])+Double.parseDouble(FinalResult.get(i).split(",")[3])));
                }
            }
        }

        while (!isOrdered(ActualRanking)){
            for (int i=0;i<ActualRanking.size()-1;i++){
                if (Double.parseDouble(ActualRanking.get(i).split(",")[3]) > Double.parseDouble(ActualRanking.get(i+1).split(",")[3])){
                    String var1 = ActualRanking.get(i);
                    String var2 = ActualRanking.get(i+1);
                    ActualRanking.set(i,var2);
                    ActualRanking.set(i+1,var1);
                }
            }
        }

        Collections.reverse(ActualRanking);


        new Writer(FinalResult, ActualRanking);



    }

    public static boolean isOrdered(ArrayList<String> list){
        for (int i=0;i<list.size()-1;i++){
            String[] data = list.get(i).split(",");
            String[] data2 = list.get(i+1).split(",");
            if (Double.parseDouble(data[3]) > Double.parseDouble(data2[3])){
                return false;
            }
        }
        return true;
    }


}
