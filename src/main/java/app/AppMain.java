package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AppMain {
    public static void main(String[] args) {
        Ecrans ecrans = new Ecrans();
    }
}

class Ecrans extends JFrame {
    private JPanel container = new JPanel();
    String[] tab_string = { "1",  "2",  "3",
                            "4",  "5",  "6",
                            "7",  "8",  "9",
                            "OK", "0", "DEL" };

    ButtonNum[] tab_button = new ButtonNum[tab_string.length];
    private JLabel ecran = new JLabel();

    JLabel title = new JLabel("EPISEN LOCKER - Entrez votre code."); // Affiche le titre.
    JPanel result; // AFfiche le resultat.


    public Ecrans(){
        this.setSize(800, 600);
        this.setTitle("EPISEN LOCKER - RETRAIT DE COMMANDE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initComposant();
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void initComposant(){
        title.setFont(new Font("Arial", Font.BOLD, 40));
        ecran = new JLabel("");
        ecran.setFont(new Font("Arial", Font.BOLD, 50));
        ecran.setHorizontalAlignment(JLabel.CENTER);
        ecran.setPreferredSize(new Dimension(300, 70));

        JPanel chiffre = new JPanel();
        chiffre.setLayout(new GridLayout(4, 3));

        JPanel panEcran = new JPanel();

        for(int i = 0; i < tab_string.length; i++){
            tab_button[i] = new ButtonNum(tab_string[i]);
            switch(i){
                case 9 :
                    tab_button[i].addActionListener(new ValiderListener());
                    chiffre.add(tab_button[i]);
                    break;
                case 11 :
                    tab_button[i].setForeground(Color.red);
                    tab_button[i].addActionListener(new ResetListener());
                    chiffre.add(tab_button[i]);
                    break;
                default :
                    chiffre.add(tab_button[i]);
                    tab_button[i].addActionListener(new ChiffreListener());
                    break;
            }
        }
        panEcran.add(ecran);
        panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
        container.add(title, BorderLayout.NORTH);
        container.add(panEcran, BorderLayout.WEST);
        container.add(chiffre, BorderLayout.EAST);

    }

    class ChiffreListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String str = ((JButton)e.getSource()).getText();
            if(!ecran.getText().equals("0"))
                str = ecran.getText() + str;
            ecran.setText(str);
        }
    }

    class ValiderListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            ResultatTrouve(Integer.parseInt(ecran.getText()));

        }
    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            ecran.setText("");
        }
    }

    class ResetAll implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            System.out.println("OKKKKK!!!!!");
            result.setVisible(false);
        }
    }

    public void ResultatTrouve(int code) {
        int nombreAleatoire = 1000 + (int)(Math.random() * ((9999 - 1000) + 1));
        boolean trouve = nombreAleatoire > code;
        result = new JPanel();
        if(trouve) {
            JLabel titreResult = new JLabel("Commande trouvée !");
            titreResult.setFont(new Font("Arial", Font.BOLD, 50));
            titreResult.setHorizontalAlignment(JLabel.CENTER);
            JLabel numCom = new JLabel("N° de commande : C123456");
            numCom.setFont(new Font("Arial", Font.PLAIN, 30));
            numCom.setHorizontalAlignment(JLabel.CENTER);
            result.setLayout(new GridLayout(3, 1));
            result.add(titreResult);
            result.add(numCom);

            JButton btnOk = new JButton("Ouvrir le compartiment");
            btnOk.addActionListener(new ResetAll());
            result.add(btnOk);
        } else {
            JLabel titreResult = new JLabel("Mauvais code !");
            titreResult.setFont(new Font("Arial", Font.BOLD, 50));
            titreResult.setHorizontalAlignment(JLabel.CENTER);

            JButton btnOk = new JButton("Recommencer");
            btnOk.addActionListener(new ResetAll());
            result.setLayout(new GridLayout(2, 1));
            result.add(titreResult);
            result.add(btnOk);
        }

        container.add(result, BorderLayout.SOUTH);
        result.setVisible(true);
        ecran.setText("");

    }
}

