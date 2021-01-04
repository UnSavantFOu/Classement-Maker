package fr.onhenriquanne.Utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> Ranking;
    public static ArrayList<String> Inscrits;
    public static ArrayList<String> FinalResult;
    public static ArrayList<String> LastCompetitionRank;



    public static int getCompetitionForce(){
        int force = 0;

        for (int i=0;i<32;i++){
            int classement = Integer.parseInt(Inscrits.get(i).split(",")[0]);
            if (classement < 1000){
                if (classement <= 32){
                    force+=1000;
                }else if (Query(Inscrits.get(i).split(",")[1], Inscrits.get(i).split(",")[2]) <= 8){
                    force+=1000;
                }else{
                    force+=500;
                }
            }else{
                if (classement <= 1008){
                    force+=1000;
                }else if (classement <= 1016){
                    force+=500;
                }else{
                    force+=100;
                }
            }
        }

        return force;
    }


    public static int Query(String name, String surname){
        int rank = 1000;

        for (String tireur : Ranking){
            String[] data = tireur.split(",");
            if (data[1].toUpperCase().equals(name.toUpperCase()) & data[2].toUpperCase().equals(surname.toUpperCase())){
                return Integer.parseInt(data[0]);
            }
        }

        return rank;
    }




    public static ArrayList<String> EngardeParser(String url, int pas, int name, int surname, int ranking){
        ArrayList<String> var = new ArrayList<>();

        Document doc = null;
        try{
            doc = Jsoup.connect(url).get();
        }catch (IOException e){
            e.printStackTrace();
        }

        Elements elements = doc.select("td");
        for (int i=0;i<elements.size();i+=pas){
            String classement;
            if (elements.get(i+ranking).text().replace("\u00a0", "").length()==0){
                 classement = "10000";
            }else{
                classement = elements.get(i+ranking).text().replace("\u00a0", "");
            }
            var.add(classement+","+elements.get(i+name).text().replace("\u00a0", "")+","+elements.get(i+surname).text().replace("\u00a0", ""));
        }

        return var;
    }



    public static ArrayList<String> getActualRank(String url, int total, int name, int surname, int rank, int points){
        Ranking = new ArrayList<>();

        Document doc = null;
        try{
            doc = Jsoup.connect(url).get();
        }catch (IOException e){
            e.printStackTrace();
        }

        Elements elements = doc.select("td");
        for (int i=0;i<elements.size();i+=total){
            Ranking.add(elements.get(i+rank).text()+","+elements.get(i+name).text()+","+elements.get(i+surname).text()+","+elements.get(i+points).text());
        }

        return Ranking;
    }


    public static ArrayList<String> getLastCompetitionRankPoint(String url, int total, int rank, int name, int surname, int points){
        LastCompetitionRank = new ArrayList<>();

        Document doc = null;
        try{
            doc = Jsoup.connect(url).get();
        }catch (IOException e){
            e.printStackTrace();
        }

        Elements elements = doc.select("td");
        for (int i=0;i<elements.size();i+=total){
            LastCompetitionRank.add(elements.get(i+rank).text()+","+elements.get(i+name).text()+","+elements.get(i+surname).text()+","+elements.get(i+points).text());
        }

        return  LastCompetitionRank;
    }

}
