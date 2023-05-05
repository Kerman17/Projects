import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class InterfataUtilizator extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    static int mouseX,mouseY, nouMouseX, nouMouseY;
    static int dimPatrat=85;

    JButton bNewGame = new JButton("New Game");
    JButton bSaveGame = new JButton("Save Game");
    JButton bLoadGame = new JButton("Load Game");

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.gray.brighter());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.add(bNewGame); bNewGame.addActionListener(this); bNewGame.setActionCommand("New");
        this.add(bSaveGame);bSaveGame.addActionListener(this); bSaveGame.setActionCommand("Save");
        this.add(bLoadGame);bLoadGame.addActionListener(this); bLoadGame.setActionCommand("Load");

        bNewGame.setSize(100,30);
        bNewGame.setLocation(8*dimPatrat+2,1*dimPatrat-30);
        bNewGame.setBackground(Color.white);
        bNewGame.setForeground(Color.black);                  //

        bSaveGame.setSize(100,30);
        bSaveGame.setLocation(8*dimPatrat+2,2*dimPatrat-80);
        bSaveGame.setBackground(Color.white);
        bSaveGame.setForeground(Color.black);                 //

        bLoadGame.setSize(100,30);
        bLoadGame.setLocation(8*dimPatrat+2,3*dimPatrat-130);
        bLoadGame.setBackground(Color.white);
        bLoadGame.setForeground(Color.black);                 //


        for(int i=0;i<64;i+=2){
            g.setColor(new Color(238,238,210));
            g.fillRect((i%8+(i/8)%2)*dimPatrat,(i/8)*dimPatrat,dimPatrat,dimPatrat);
            g.setColor(new Color(118, 150, 86));
            g.fillRect(((i+1)%8-((i+1)/8)%2)*dimPatrat,((i+1)/8)*dimPatrat,dimPatrat,dimPatrat);
        }

        Image imaginePieseSah;
        imaginePieseSah = new ImageIcon("piese.png").getImage();

        for(int i=0;i<64;i++){
            int j=-1,k=-1;
            switch (SahMain.tablaSah[i / 8][i % 8]) {
                case "P" -> {
                    j = 5;
                    k = 0;
                }
                case "p" -> {
                    j = 5;
                    k = 1;
                }
                case "R" -> {
                    j = 2;
                    k = 0;
                }
                case "r" -> {
                    j = 2;
                    k = 1;
                }
                case "K" -> {
                    j = 4;
                    k = 0;
                }
                case "k" -> {
                    j = 4;
                    k = 1;
                }
                case "B" -> {
                    j = 3;
                    k = 0;
                }
                case "b" -> {
                    j = 3;
                    k = 1;
                }
                case "Q" -> {
                    j = 1;
                    k = 0;
                }
                case "q" -> {
                    j = 1;
                    k = 1;
                }
                case "A" -> {
                    j = 0;
                    k = 0;
                }
                case "a" -> {
                    j = 0;
                    k = 1;
                }
            }
            if(j!=-1 && k!=-1){
                g.drawImage(imaginePieseSah,(i%8)*dimPatrat,(i/8)*dimPatrat,(i%8+1)*dimPatrat,(i/8+1)*dimPatrat,j*64,k*64,(j+1)*64,(k+1)*64,this);
            }
        }
    } /// am colorat tabla
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getX()<8*dimPatrat && e.getY()<8*dimPatrat){
        mouseX=e.getX();
        mouseY=e.getY();
        SahMain.contor=1;
        repaint();}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getX()<8*dimPatrat && e.getY()<8*dimPatrat){
            String mutare;
            nouMouseX =e.getX();
            nouMouseY =e.getY();
            mutare=""+mouseY/dimPatrat + mouseX/dimPatrat + nouMouseY /dimPatrat + nouMouseX /dimPatrat;
            if(" ".equals(SahMain.tablaSah[nouMouseY /dimPatrat][nouMouseX /dimPatrat])){
                SahMain.executaMutarea(mutare);
            }else
            if(SahMain.ePiesaOponent(mutare)){
                SahMain.executaMutarea(mutare);
            }
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    @Override
    public void actionPerformed(ActionEvent ae) {

        String a = ae.getActionCommand();
        if(a.equals("New") && SahMain.contor==1) {
            int raspuns = JOptionPane.showConfirmDialog(null,"Resetati tabla de joc?",
                    "Resetare tabla joc", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(raspuns == JOptionPane.YES_OPTION){
                SahMain.tablaSah = new String[][]{
                        {"r", "k", "b", "q", "a", "b", "k", "r"},
                        {"p", "p", "p", "p", "p", "p", "p", "p"},
                        {" ", " ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " ", " "},
                        {"P", "P", "P", "P", "P", "P", "P", "P"},
                        {"R", "K", "B", "Q", "A", "B", "K", "R"}
                };
                repaint();
            }else if(raspuns == JOptionPane.NO_OPTION){
                showMessageDialog(null,"Ati ales nu");

          }else showMessageDialog(null,"Nicio varianta aleasa");
            SahMain.contor--;
        }

        if(a.equals("Save")) {
            scrieTablaSah("stocMatrix",SahMain.tablaSah);
            SahMain.contor=1;
        }
        if(a.equals("Load")) {
            citireTablaSah("stocMatrix.txt");
            SahMain.contor=1;
        }

    }

    void scrieTablaSah(String numefisier,String[][] tabla){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("stocMatrix.txt"));
            for(int i=0;i<SahMain.tablaSah.length;i++){
                for(int j=0;j<SahMain.tablaSah.length;j++){
                    if(SahMain.tablaSah[i][j]==" ")bw.write("x");
                    else bw.write( SahMain.tablaSah[i][j]);

                }
                bw.newLine();
            }
            bw.flush();
        }catch (IOException e){}
    }

    void citireTablaSah(String filename) {
        if(SahMain.contor==1) {
            File file = new File(filename);
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int j=0;
                while ((line = br.readLine()) != null && SahMain.contor == 1) {
                    SahMain.tablaSah[j][0] = String.valueOf(line.charAt(0));
                    SahMain.tablaSah[j][1] = String.valueOf(line.charAt(1));
                    SahMain.tablaSah[j][2] = String.valueOf(line.charAt(2));
                    SahMain.tablaSah[j][3] = String.valueOf(line.charAt(3));
                    SahMain.tablaSah[j][4] = String.valueOf(line.charAt(4));
                    SahMain.tablaSah[j][5] = String.valueOf(line.charAt(5));
                    SahMain.tablaSah[j][6] = String.valueOf(line.charAt(6));
                    SahMain.tablaSah[j][7] = String.valueOf(line.charAt(7));
                    j++;
                    repaint();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            SahMain.contor = 0;
        }
    }


}
