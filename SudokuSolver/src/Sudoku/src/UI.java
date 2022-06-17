/**
    Realizat de Raul Detesan in 17.06.2022
    Realised by Raul Detesan on 17.07.2022

 **/


package Sudoku.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UI extends JFrame{

    private static final int MAXD  = 9;
    int sepI=5,sepJ=5,contorI=0,unu=0;
    int[][] tablaInt = new int[MAXD][MAXD];
    String capsule;

    Font font1 = new Font("Verdana", Font.BOLD, 20);
    Font font2 = new Font("Arial", Font.BOLD,20);

    JTextField[][] fields = new JTextField[MAXD][MAXD];
    JButton bRez,bReset,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    JLabel note;
    JTextArea feedback;


    public static final int SIZE = 9;

    UI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,750);
        init();
        setTitle("SudokuSolver");
        setVisible(true);
        setResizable(false);

    }

    public void init(){

        setLayout(null);
        getContentPane().setBackground(new Color(163, 90, 5, 255));


        // matrix of fields

        for(int i=0;i<MAXD;i++){
            if(i==3 || i==6)contorI++;
            for(int j=0;j<MAXD;j++){
                JTextField field = new JTextField();
                fields[i][j] = field;

                if(j>2 && j<=5)sepJ=5;
                else if(j>5)sepJ=10;
                else sepJ=0;

                field.setBounds(106*i+12+(sepI*contorI),55*(j+1)+sepJ,106,55);

                field.setFont(font1);
                field.setHorizontalAlignment(0);
                field.setBackground(new Color(176, 169, 169));
                add(field);
            }
        }


        // buttons
        bRez = new JButton("Rezolvare");
        bRez.setBounds(60,575,120,60);
        bRez.addActionListener(new AscultareRezolvare());

        bReset = new JButton("Reset");
        bReset.setBounds(200,575,120,60);
        bReset.addActionListener(new AscultareReset());

        b1 = new JButton("1");
        b1.setBounds(20,650,50,50);
        b1.addActionListener(new AscultareUnu());

        b2 = new JButton("2");
        b2.setBounds(80,650,50,50);
        b2.addActionListener(new AscultareDoi());

        b3 = new JButton("3");
        b3.setBounds(140,650,50,50);
        b3.addActionListener(new AscultareTrei());

        b4 = new JButton("4");
        b4.setBounds(200,650,50,50);
        b4.addActionListener(new AscultarePatru());

        b5 = new JButton("5");
        b5.setBounds(260,650,50,50);
        b5.addActionListener(new AscultareCinci());

        b6 = new JButton("6");
        b6.setBounds(320,650,50,50);
        b6.addActionListener(new AscultareSase());

        b7 = new JButton("7");
        b7.setBounds(380,650,50,50);
        b7.addActionListener(new AscultareSapte());

        b8 = new JButton("8");
        b8.setBounds(440,650,50,50);
        b8.addActionListener(new AscultareOpt());

        b9 = new JButton("9");
        b9.setBounds(500,650,50,50);
        b9.addActionListener(new AscultareNoua());

        // label

        note = new JLabel("Note: Numbers entered must be between 1 and 9 !");
        note.setBounds(680,595,300,60);

        // text area

        feedback = new JTextArea();
        feedback.setBounds(340,585,290,40);
        feedback.setFont(font2);
        feedback.setEditable(false);

        add(bRez);add(bReset);add(note);add(feedback);add(b1);add(b2);
        add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);


    }
        public void resetareTabla(JTextField[][] fields){
        for(int i=0;i<MAXD;i++)
            for(int j=0;j<MAXD;j++){
                fields[i][j].setText("");
                fields[j][i].setBackground(new Color(176, 169, 169));
            }
        }


        // ActionListeners -----------------------------------------------------------------------------------------

        class AscultareRezolvare implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                tablaToInt(fields,tablaInt);
                solveBoard(tablaInt);
                tablaToJField(tablaInt,fields);
            }
        }

        class AscultareReset implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {

                resetareTabla(fields);
                feedback.setText("Tabla resetata cu succes!");
            }
        }

        class AscultareUnu implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unu==0){
                    for(int i=0;i<MAXD;i++)
                        for(int j=0;j<MAXD;j++)
                        {capsule = fields[j][i].getText();
                            if(Objects.equals(capsule, "1"))fields[j][i].setBackground(Color.ORANGE);}
                    unu=1;
                }
                else {
                    unu = 0;
                    for(int i=0;i<MAXD;i++)
                        for(int j=0;j<MAXD;j++)
                        {capsule = fields[j][i].getText();
                            if(Objects.equals(capsule, "1"))fields[j][i].setBackground(new Color(176, 169, 169));}
                }
            }
        }

        class AscultareDoi implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unu==0){
                    for(int i=0;i<MAXD;i++)
                        for(int j=0;j<MAXD;j++)
                        {capsule = fields[j][i].getText();
                            if(Objects.equals(capsule, "2"))fields[j][i].setBackground(new Color(13, 169, 169));}
                    unu=1;
                }
                else {
                    unu = 0;
                    for(int i=0;i<MAXD;i++)
                        for(int j=0;j<MAXD;j++)
                        {capsule = fields[j][i].getText();
                            if(Objects.equals(capsule, "2"))fields[j][i].setBackground(new Color(176, 169, 169));}
                }
            }
        }
    class AscultareTrei implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(unu==0){
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "3"))fields[j][i].setBackground(new Color(176, 69, 69));}
                unu=1;
            }
            else {
                unu = 0;
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "3"))fields[j][i].setBackground(new Color(176, 169, 169));}
            }
        }
    }
    class AscultarePatru implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(unu==0){
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "4"))fields[j][i].setBackground(new Color(176, 16, 19));}
                unu=1;
            }
            else {
                unu = 0;
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "4"))fields[j][i].setBackground(new Color(176, 169, 169));}
            }
        }
    }
    class AscultareCinci implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(unu==0){
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "5"))fields[j][i].setBackground(new Color(196, 119, 129));}
                unu=1;
            }
            else {
                unu = 0;
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "5"))fields[j][i].setBackground(new Color(176, 169, 169));}
            }
        }
    }
    class AscultareSase implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(unu==0){
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "6"))fields[j][i].setBackground(new Color(96, 39, 169));}
                unu=1;
            }
            else {
                unu = 0;
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "6"))fields[j][i].setBackground(new Color(176, 169, 169));}
            }
        }
    }
    class AscultareSapte implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(unu==0){
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "7"))fields[j][i].setBackground(new Color(46, 69, 169));}
                unu=1;
            }
            else {
                unu = 0;
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "7"))fields[j][i].setBackground(new Color(176, 169, 169));}
            }
        }
    }
    class AscultareOpt implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(unu==0){
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "8"))fields[j][i].setBackground(new Color(128, 49, 232));}
                unu=1;
            }
            else {
                unu = 0;
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "8"))fields[j][i].setBackground(new Color(176, 169, 169));}
            }
        }
    }
    class AscultareNoua implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(unu==0){
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "9"))fields[j][i].setBackground(new Color(245, 186, 175));}
                unu=1;
            }
            else {
                unu = 0;
                for(int i=0;i<MAXD;i++)
                    for(int j=0;j<MAXD;j++)
                    {capsule = fields[j][i].getText();
                        if(Objects.equals(capsule, "9"))fields[j][i].setBackground(new Color(176, 169, 169));}
            }
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------

    // Methods

    public boolean checkLine(int[][] fields, int lin,int nr){      /// check if the nr is on the specific lin ( line )
        for(int j=0;j<MAXD;j++){
           if(fields[lin][j] == nr)return false;
        }
        return true;
    }

    public boolean checkColumn(int[][] fields, int col, int nr){    /// check is the nr is on the specific col (column)
        for(int i=0;i<MAXD;i++){
            if(fields[i][col] == nr)return false;
        }
        return true;
    }

    public boolean checkSquare(int[][] fields,int lin,int col,int nr){
        int x,y,nrPatrat;
        x = lin/3 + 1;
        y = col/3 + 1;
        nrPatrat = 2*x+y - (3-x); // number of the square the number is located in 1,2,3 / 4,5,6 / 7,8,9

        switch (nrPatrat){
            case 1:
                for(int i=0;i<=2;i++)
                    for(int j=0;j<=2;j++){
                        if(fields[i][j]==nr)return false;
                    }
                break;
            case 2:
                for(int i=0;i<=2;i++)
                    for(int j=3;j<=5;j++){
                        if(fields[i][j]==nr)return false;
                    }
                break;
            case 3:
                for(int i=0;i<=2;i++)
                    for(int j=6;j<=8;j++){
                        if(fields[i][j]==nr)return false;        // ROW 1 of squares
                    }
                break;
            case 4:
                for(int i=3;i<=5;i++)
                    for(int j=0;j<=2;j++){
                        if(fields[i][j]==nr)return false;
                    }
                break;
            case 5:
                for(int i=3;i<=5;i++)
                    for(int j=3;j<=5;j++){
                        if(fields[i][j]==nr)return false;
                    }
                break;
            case 6:
                for(int i=3;i<=5;i++)
                    for(int j=6;j<=8;j++){
                        if(fields[i][j]==nr)return false;       // ROW 2 of squares
                    }
                break;
            case 7:
                for(int i=6;i<=8;i++)
                    for(int j=0;j<=2;j++){
                        if(fields[i][j]==nr)return false;
                    }
                break;
            case 8:
                for(int i=6;i<=8;i++)
                    for(int j=3;j<=5;j++){
                        if(fields[i][j]==nr)return false;
                    }
                break;
            case 9:
                for(int i=6;i<=8;i++)
                    for(int j=6;j<=8;j++){
                        if(fields[i][j]==nr)return false;        // ROW 3 of squares
                    }
                break;
            default:
                System.out.println("Value outside the table");
        }

        return true;
    }


    public void tablaToInt(JTextField[][] tabla,int[][] tablaInt){  // JTextField matrix to int matrix
        for(int i=0;i<MAXD;i++){
            for(int j=0;j<MAXD;j++){
                String capsule = tabla[j][i].getText();
                if(Objects.equals(capsule, ""))tablaInt[i][j] = 0;
                else tablaInt[i][j] = Integer.parseInt(capsule);
            }
        }
    }

    public void tablaToJField(int[][] tablaInt,JTextField[][] tabla){   // int matrix to JTextField matrix
        for(int i=0;i<MAXD;i++){
            for(int j=0;j<MAXD;j++){
                String capsule = String.valueOf(tablaInt[i][j]);
                tabla[j][i].setText(capsule);
            }
        }
    }

    public boolean solveBoard(int[][] board){           // solve board with backtracking
        for(int row=0; row < MAXD;row++){
            for(int column=0; column < MAXD;column++){
                if(board[row][column] == 0){
                    for(int numberToTry = 1;numberToTry <=MAXD;numberToTry++) {
                        if (checkSquare(board, row, column, numberToTry) && checkColumn(board, column, numberToTry) && checkLine(board, row, numberToTry)){
                            board[row][column] = numberToTry;

                            if(solveBoard(board)){
                                return true;
                            }
                            else{
                                board[row][column] = 0;
                            }
                        }
                    }
                       return false;
                }
            }
        }
        return true;
    }

}

