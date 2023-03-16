package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ConnectFour extends JFrame {
    static boolean isX = true;
    static boolean isWon = false;
    static HashMap<String, Integer> buttonMap = new HashMap<>() {{
        put("A", 35);
        put("B", 36);
        put("C", 37);
        put("D", 38);
        put("E", 39);
        put("F", 40);
        put("G", 41);
    }};

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 350);
        setVisible(true);
        setLayout(new GridLayout(7, 7));
        setTitle("Connect Four");
        for (int i = 6; i > 0; i--) {
            for (int j = 65; j <= 71; j++) {
                String name = String.valueOf((char) j) + i;
                JButton jButton = new JButton(" ");
                jButton.setFocusPainted(false);
                jButton.setName("Button" + name);
                jButton.setBackground(Color.gray);
                add(jButton);
            }
        }

        Component[] components = getContentPane().getComponents();
        JButton buttonReset = new JButton("Reset");
        buttonReset.setName("ButtonReset");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset(components);
            }
        });
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(buttonReset);



        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!isWon) {
                            String letter = ((JButton) e.getSource()).getName().substring(6, 7);
                            JButton button = (JButton) components[buttonMap.get(letter)];
                            button.setText(isX ? "X" : "O");
                            isX = !isX;
                            buttonMap.put(letter, buttonMap.get(letter) - 7);
                            checkWinner(components);
                        }
                    }
                });
            }
        }

    }

    private static boolean checkWinner(Component[] components) {
        // verify rows
        HashMap<Integer, String> xRowMap = new HashMap<>();
        HashMap<Integer, String> oRowMap = new HashMap<>();
        int xRowCount = 0;
        int oRowCount = 0;
        for (int i = 0; i < 41; i+=7) {
            for (int j = i; j < i + 7; j++) {
                String text = ((JButton) components[j]).getText();
                if (text.equals("X") && oRowCount != 4) {
                    xRowMap.put(j, "X");
                    oRowMap = new HashMap<>();
                    oRowCount = 0;
                    xRowCount++;
                } else if (text.equals("O") && xRowCount != 4) {
                    oRowMap.put(j, "O");
                    xRowMap = new HashMap<>();
                    oRowCount++;
                    xRowCount = 0;
                } else if (text.equals(" ") && (xRowCount != 4 && oRowCount != 4)){
                    xRowMap = new HashMap<>();
                    oRowMap = new HashMap<>();
                    xRowCount = 0;
                    oRowCount = 0;
                }

            }
            if (xRowCount == 4) {
                System.out.println("X wins");
                fillCell(xRowMap, components);
                isWon = true;
                return false;
            } else if (oRowCount == 4) {
                System.out.println("O wins");
                fillCell(oRowMap, components);
                isWon = true;
                return false;
            }
            xRowMap = new HashMap<>();
            oRowMap = new HashMap<>();
            xRowCount = 0;
            oRowCount = 0;
        }


        HashMap<Integer, String> colMap = new HashMap<>();
        int xColCount = 0;
        int oColCount = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 41; j+=7) {
                String text = ((JButton) components[j]).getText();
                if (text.equals("X") && oColCount != 4) {
                    colMap.put(j, "X");
                    xColCount++;
                    oColCount = 0;
                } else if (text.equals("O") && xColCount != 4) {
                    colMap.put(j, "O");
                    oColCount++;
                    xColCount = 0;
                } else if (text.equals(" ")) {
                    colMap = new HashMap<>();
                    xColCount = 0;
                    oColCount = 0;
                }

                if (xColCount == 4 || oColCount == 4) {
                    break;
                }
            }
            if (xColCount == 4) {
                System.out.println("X wins");
                fillCell(colMap, components);
                isWon = true;
                return false;
            } else if (oColCount == 4) {
                System.out.println("O wins");
                fillCell(colMap, components);
                isWon = true;
                return false;
            }
            colMap = new HashMap<>();
            xColCount = 0;
            oColCount = 0;
        }


        HashMap<Integer, String> xDiaDownMap = new HashMap<>();
        HashMap<Integer, String> oDiaDownMap = new HashMap<>();
        int xDownDiagCount = 0;
        int oDownDiagCount = 0;
        int buttonIndexDown = 0;
        for (int k = 0; k < 15; k+=7) {
            buttonIndexDown = k;
            for (int i = 0; i < 4; i++, buttonIndexDown=k+i) {
                for (int j = 0; j < 4; j++) {
                    String text = ((JButton) components[buttonIndexDown]).getText();
                    if (text.equals("X")) {
                        xDiaDownMap.put(buttonIndexDown, "X");
                        xDownDiagCount++;
                    }
                    if (text.equals("O")) {
                        oDiaDownMap.put(buttonIndexDown, "O");
                        oDownDiagCount++;
                    }
                    if (text.equals(" ")) {
                        xDiaDownMap = new HashMap<>();
                        oDiaDownMap = new HashMap<>();
                    }

                    buttonIndexDown+=8;
                }

                if (xDownDiagCount == 4) {
                    System.out.println("X wins");
                    fillCell(xDiaDownMap, components);
                    isWon = true;
                    return false;
                } else if (oDownDiagCount == 4) {
                    System.out.println("O wins");
                    fillCell(oDiaDownMap, components);
                    isWon = true;
                    return false;
                }


                xDiaDownMap = new HashMap<>();
                oDiaDownMap = new HashMap<>();
                xDownDiagCount = 0;
                oDownDiagCount = 0;
            }
        }

        HashMap<Integer, String> xDiaUpMap = new HashMap<>();
        HashMap<Integer, String> oDiaUpMap = new HashMap<>();
        int xUpDiagCount = 0;
        int oUpDiagCount = 0;
        int buttonIndexUp = 0;
        for (int k = 35; k > 20; k-=7) {
            buttonIndexUp = k;
            for (int i = 0; i < 4; i++, buttonIndexUp=k+i) {
                for (int j = 0; j < 4; j++) {
                    String text = ((JButton) components[buttonIndexUp]).getText();
                    if (text.equals("X")) {
                        xDiaUpMap.put(buttonIndexUp, "X");
                        xUpDiagCount++;
                    }
                    if (text.equals("O")) {
                        oDiaUpMap.put(buttonIndexUp, "O");
                        oUpDiagCount++;
                    }
                    if (text.equals(" ")) {
                        xDiaUpMap = new HashMap<>();
                        oDiaUpMap = new HashMap<>();
                    }
                    buttonIndexUp-=6;
                }
                if (xUpDiagCount == 4) {
                    System.out.println("X wins");
                    fillCell(xDiaUpMap, components);
                    isWon = true;
                    return false;
                } else if (oUpDiagCount == 4) {
                    System.out.println("O wins");
                    fillCell(oDiaUpMap, components);
                    isWon = true;
                    return false;
                }
                xDiaUpMap = new HashMap<>();
                oDiaUpMap = new HashMap<>();
                xUpDiagCount = 0;
                oUpDiagCount = 0;
            }
        }
        return true;
    }

    private static void reset(Component[] components) {
        for (int i = 0; i < 42; i++) {
            ((JButton) components[i]).setText(" ");
            ((JButton) components[i]).setBackground(Color.gray);
        }
        buttonMap = new HashMap<>() {{
            put("A", 35);
            put("B", 36);
            put("C", 37);
            put("D", 38);
            put("E", 39);
            put("F", 40);
            put("G", 41);
        }};

        isX = true;
        isWon = false;
    }

    private static void fillCell(HashMap<Integer, String> map, Component[] components) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            ((JButton) components[entry.getKey()]).setBackground(Color.pink);
        }


    }
}