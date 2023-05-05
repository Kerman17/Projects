import javax.swing.*;

public class SahMain  {



    static String tablaSah[][]={
            {"r","k","b","q","a","b","k","r"},
            {"p","p","p","p","p","p","p","p"},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {"P","P","P","P","P","P","P","P"},
            {"R","K","B","Q","A","B","K","R"}
    };
    static int contor=1;
    public static void main(String[] args) {

        JFrame f = new JFrame("Joc de Sah");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InterfataUtilizator ui = new InterfataUtilizator();
        f.add(ui);
        f.setSize(798,715);
        f.setResizable(false);
        f.setVisible(true);
    }

    public static void executaMutarea(String mutare){
        tablaSah[Character.getNumericValue(mutare.charAt(2))][Character.getNumericValue(mutare.charAt(3))]=tablaSah[Character.getNumericValue(mutare.charAt(0))][Character.getNumericValue(mutare.charAt(1))];
        tablaSah[Character.getNumericValue(mutare.charAt(0))][Character.getNumericValue(mutare.charAt(1))] = " ";
    }
    public static boolean ePiesaOponent(String piesa){
        int a=Character.getNumericValue(piesa.charAt(0));
        int b=Character.getNumericValue(piesa.charAt(1));
        int c=Character.getNumericValue(piesa.charAt(2));
        int d=Character.getNumericValue(piesa.charAt(3));
        String prima=tablaSah[a][b];
        String doua=tablaSah[c][d];

        if(prima=="P" || prima=="K" || prima=="B" || prima=="Q" || prima=="A" || prima=="R")
            if(doua=="p" || doua=="k" || doua=="b" || doua=="q" || doua=="a" || doua=="r")return true;

        if(doua=="P" || doua=="K" || doua=="B" || doua=="Q" || doua=="A" || doua=="R")
            if(prima=="p" || prima=="k" || prima=="b" || prima=="q" || prima=="a" || prima=="r")return true;

        return false;
    }
}
