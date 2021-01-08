/* This is my Connect4 project, a 2-player game where one player tries to get four tiles to have the same colour,
 * whether diagonal, vertical, or horizontal. The first player to get four colour in a row wins, and then the
 * game board freezes until a user decides to start a new game
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class Connect4Complete { 

    static JButton test = new JButton();
    static JButton [] buttonArray = new javax.swing.JButton[42];

    static boolean playerOne = true;
    static boolean playerTwo = false;
    static boolean win = false;

    static int [] stackArray = new int[6];
    static int column = 0;
    static int row = 0;
    static JMenuItem reset = new JMenuItem("Restart");
    static JMenuItem menuItemNewGame = new JMenuItem("New game");
    static JMenuItem help = new JMenuItem("Help");
    static JMenu gameMenu = new JMenu("Game");
    static JMenuBar menuBar = new JMenuBar();
    static ButtonHandler listener = new ButtonHandler();
    static JLabel [] labelArray = new javax.swing.JLabel[42];
    static int x = 0;
    static boolean rowChange = false;
    static JLabel label = new JLabel(); 
    static int clicks = 0;

    static int redWins = 0;
    static int blueWins = 0;

    static JFrame gameFrame = new JFrame ("Connect 4: Player 1's (red) turn                         P1: " + redWins + " / P2: " + blueWins);
    static ImageIcon frame = new ImageIcon("frame.png");
    public static void main (String [] args) { 
        win = false;
        gameFrame.setSize(700, 700);
        int width = 7;
        int height = 6;
        gameFrame.setLayout(new GridLayout (7, 6));

        int rowJumps = 0;
        gameFrame.setVisible(true); 
        //Add a MenuItem to a Menu

        gameMenu.add(menuItemNewGame);
        gameMenu.add(reset);
        gameMenu.add(help);

        //Add a Menu to the menubar
        menuBar.add(gameMenu);

        //For menus to work, you need to add a listener
        menuItemNewGame.addActionListener(listener);
        reset.addActionListener(listener);
        help.addActionListener(listener);

        // put the menubar on the frame
        gameFrame.setJMenuBar(menuBar);
        gameFrame.setResizable(false);

        for (int i = 0; i < 42; i++) {
            ImageIcon frame  = new ImageIcon("tile.jpg");
            Image img = frame.getImage() ;  
            Image newimg = img.getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  

            buttonArray[i] = new JButton ();
            frame = new ImageIcon(newimg);
            buttonArray[i].setIcon(frame);
            buttonArray[i].setBackground(Color.white);
            buttonArray[i].addActionListener(listener);
            buttonArray[i].setBorder(BorderFactory.createLineBorder(Color.orange)); // Simple Line Border
            ;

            gameFrame.add(buttonArray[i]);
            gameFrame.setSize(787, 868);
            gameFrame.setSize(700, 833);
        } 
    }

    private static class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //System.out.println(gameFrame.getSize());
            JButton [] [] columnArray = {{buttonArray[0] , buttonArray[6] , buttonArray[12] , buttonArray[18], buttonArray[24],buttonArray[30], buttonArray[36]},  // An array with seperates the buttons into their respective columns
                    {buttonArray[1] ,buttonArray[7],buttonArray[13],buttonArray[19],buttonArray[25],buttonArray[31],buttonArray[37]}, 
                    {buttonArray[2] ,buttonArray[8],buttonArray[14],buttonArray[20],buttonArray[26],buttonArray[32], buttonArray[38]}, 
                    {buttonArray[3],buttonArray[9],buttonArray[15],buttonArray[21],buttonArray[27],buttonArray[33],buttonArray[39]} ,
                    {buttonArray[4],buttonArray[10],buttonArray[16],buttonArray[22],buttonArray[28],buttonArray[34],buttonArray[40]},
                    {buttonArray[5] ,buttonArray[11],buttonArray[17],buttonArray[23],buttonArray[29],buttonArray[35],buttonArray[41]}};

            JButton [] [] rowArray = {{buttonArray[0] , buttonArray[1], buttonArray[2], buttonArray[3], buttonArray[4], buttonArray[5] },  // An array with seperates the buttons into their respective rows
                    {buttonArray[6], buttonArray[7], buttonArray[8], buttonArray[9], buttonArray[10], buttonArray[11] },
                    {buttonArray[12], buttonArray[13], buttonArray[14], buttonArray[15], buttonArray[16], buttonArray[17] },
                    {buttonArray[18], buttonArray[19], buttonArray[20], buttonArray[21], buttonArray[22], buttonArray[23] }, 
                    {buttonArray[24], buttonArray[25], buttonArray[26], buttonArray[27], buttonArray[28], buttonArray[29]},
                    {buttonArray[30], buttonArray[31], buttonArray[32], buttonArray[33], buttonArray[34], buttonArray[35]},
                    {buttonArray[36], buttonArray[37], buttonArray[38], buttonArray[39], buttonArray[40], buttonArray[41]}};

            JButton [] [] board = {{buttonArray[36], buttonArray[37], buttonArray[38], buttonArray[39], buttonArray[40], buttonArray[41]},  // An array that places the buttons onto the board (a cartesian plane)
                    {buttonArray[30], buttonArray[31], buttonArray[32], buttonArray[33], buttonArray[34], buttonArray[35]},
                    {buttonArray[24], buttonArray[25], buttonArray[26], buttonArray[27], buttonArray[28], buttonArray[29]},
                    {buttonArray[18], buttonArray[19], buttonArray[20], buttonArray[21], buttonArray[22], buttonArray[23] },
                    {buttonArray[12], buttonArray[13], buttonArray[14], buttonArray[15], buttonArray[16], buttonArray[17] },
                    {buttonArray[6], buttonArray[7], buttonArray[8], buttonArray[9], buttonArray[10], buttonArray[11] },
                    {buttonArray[0] , buttonArray[1], buttonArray[2], buttonArray[3], buttonArray[4], buttonArray[5] }};

            ImageIcon redFrame = new ImageIcon("redTile.jpg");
            Image redImg = redFrame.getImage();
            Image newRedImg = redImg.getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ; 
            redFrame = new ImageIcon(newRedImg);

            ImageIcon blueFrame = new ImageIcon("blueTile.jpg");
            Image blueImg = blueFrame.getImage();
            Image newBlueImg = blueImg.getScaledInstance(125, 125, java.awt.Image.SCALE_SMOOTH);
            blueFrame = new ImageIcon(newBlueImg);

            if (win != true) {
                //System.out.println(clicks + " clicks");
                if (e.getSource() == buttonArray[0] || e.getSource() == buttonArray[6] || e.getSource() == buttonArray[12] || e.getSource() == buttonArray[18] || e.getSource() == buttonArray[24]|| e.getSource() == buttonArray[30] || e.getSource() == buttonArray[36] ) {//The buttons that constitute column 1
                    //System.out.println("COLUMN ONE");
                    column = 0;

                    stackArray[column] = stackArray[column] +1;

                    if (stackArray[column] <= 7) {
                        if (playerOne == true) {
                            columnArray[column][7-stackArray[column]].setBackground(Color.red);  
                            columnArray[column][7-stackArray[column]].setIcon(redFrame); 
                        } else { 
                            columnArray[column][7-stackArray[column]].setBackground(Color.blue);
                            columnArray[column][7-stackArray[column]].setIcon(blueFrame); 

                        }
                    }

                    //System.out.println(stackArray[column]);
                }
                if (e.getSource() == buttonArray[1] || e.getSource() == buttonArray[7] || e.getSource() == buttonArray[13] || e.getSource() == buttonArray[19] || e.getSource() == buttonArray[25]|| e.getSource() == buttonArray[31] || e.getSource() == buttonArray[37] ) {//The buttons that constitute column 2
                    //System.out.println("COLUMN TWO");
                    column = 1;
                    stackArray[column] = stackArray[column] +1;

                    if (stackArray[column] <= 7) {
                        if (playerOne == true) {
                            columnArray[column][7-stackArray[column]].setBackground(Color.red);  
                            columnArray[column][7-stackArray[column]].setIcon(redFrame);
                        } else { 
                            columnArray[column][7-stackArray[column]].setBackground(Color.blue);
                            columnArray[column][7-stackArray[column]].setIcon(blueFrame); 
                        }
                    }

                    //System.out.println(stackArray[column]);
                }
                if (e.getSource() == buttonArray[2] || e.getSource() == buttonArray[8] || e.getSource() == buttonArray[14] || e.getSource() == buttonArray[20] || e.getSource() == buttonArray[26]|| e.getSource() == buttonArray[32] || e.getSource() == buttonArray[38] ) {//The buttons that constitute column 3
                    //System.out.println("COLUMN THREE");
                    column = 2;
                    stackArray[column] = stackArray[column] +1;

                    if (stackArray[column] <= 7) {
                        if (playerOne == true) {
                            columnArray[column][7-stackArray[column]].setBackground(Color.red);  
                            columnArray[column][7-stackArray[column]].setIcon(redFrame);
                        } else { 
                            columnArray[column][7-stackArray[column]].setBackground(Color.blue);
                            columnArray[column][7-stackArray[column]].setIcon(blueFrame); 
                        }
                    }
                    //System.out.println(stackArray[column]);
                }
                if (e.getSource() == buttonArray[3] || e.getSource() == buttonArray[9] || e.getSource() == buttonArray[15] || e.getSource() == buttonArray[21] || e.getSource() == buttonArray[27]|| e.getSource() == buttonArray[33] || e.getSource() == buttonArray[39] ) {//The buttons that constitute column 4
                    //System.out.println("COLUMN FOUR");
                    column = 3;
                    stackArray[column] = stackArray[column] +1;

                    if (stackArray[column] <= 7) {
                        if (playerOne == true) {
                            columnArray[column][7-stackArray[column]].setBackground(Color.red);  
                            columnArray[column][7-stackArray[column]].setIcon(redFrame);
                        } else { 
                            columnArray[column][7-stackArray[column]].setBackground(Color.blue);
                            columnArray[column][7-stackArray[column]].setIcon(blueFrame); 
                        }
                    }
                    //System.out.println(stackArray[column]);
                }
                if (e.getSource() == buttonArray[4] || e.getSource() == buttonArray[10] || e.getSource() == buttonArray[16] || e.getSource() == buttonArray[22] || e.getSource() == buttonArray[28]|| e.getSource() == buttonArray[34] || e.getSource() == buttonArray[40] ) {//The buttons that constitute column 5
                    //System.out.println("COLUMN FIVE");
                    column = 4;
                    stackArray[column] = stackArray[column] +1;

                    if (stackArray[column] <= 7) {
                        if (playerOne == true) {
                            columnArray[column][7-stackArray[column]].setBackground(Color.red);  
                            columnArray[column][7-stackArray[column]].setIcon(redFrame);
                        } else { 
                            columnArray[column][7-stackArray[column]].setBackground(Color.blue);
                            columnArray[column][7-stackArray[column]].setIcon(blueFrame); 
                        }
                    }

                    //System.out.println(stackArray[column]);
                }
                if (e.getSource() == buttonArray[5] || e.getSource() == buttonArray[11] || e.getSource() == buttonArray[17] || e.getSource() == buttonArray[23] || e.getSource() == buttonArray[29]|| e.getSource() == buttonArray[35] || e.getSource() == buttonArray[41] ) {//The buttons that constitute column 6
                    //System.out.println("COLUMN SIX");
                    column = 5;

                    stackArray[column] = stackArray[column] +1;
                    if (stackArray[column] <= 7) {
                        if (playerOne == true) {
                            columnArray[column][7-stackArray[column]].setBackground(Color.red);  
                            columnArray[column][7-stackArray[column]].setIcon(redFrame);
                        } else { 
                            columnArray[column][7-stackArray[column]].setBackground(Color.blue);
                            columnArray[column][7-stackArray[column]].setIcon(blueFrame); 
                        }
                    }

                    //System.out.println(stackArray[column]);
                }

                for (int i = 0; i < 42; i++) {
                    if (e.getSource() == buttonArray[i] && stackArray[column] <=7) {
                        clicks++;
                    }
                }

                if (stackArray[column] <=7 ){
                    if (playerOne == true) {
                        playerOne = false;
                        playerTwo = true;
                        gameFrame.setTitle("Connect4: Player 2's (blue) turn                        P1: " + redWins + " / P2: " + blueWins);
                    } else {
                        playerTwo = false;
                        playerOne = true;

                        gameFrame.setTitle("Connect4: Player 1's (red) turn                         P1: " + redWins + " / P2: " + blueWins);
                    }
                }
                if (clicks == 42) {
                    win = true;
                    gameFrame.setTitle("Game over!");

                }
                if (e.getSource() == buttonArray[0] || e.getSource() == buttonArray[1] || e.getSource() == buttonArray[2] || e.getSource() == buttonArray[3] || e.getSource() == buttonArray[4]|| e.getSource() == buttonArray[5]  ) {//The buttons that constitute row 1
                    //System.out.println("ROW ONE");
                    row = 0;

                    //System.out.println();
                }
                if (e.getSource() == buttonArray[6] || e.getSource() == buttonArray[7] || e.getSource() == buttonArray[8] || e.getSource() == buttonArray[9] || e.getSource() == buttonArray[10]|| e.getSource() == buttonArray[11]  ) {//The buttons that constitute row 2
                    //System.out.println("ROW TWO");
                    row = 1;

                    //System.out.println();
                }
                if (e.getSource() == buttonArray[12] || e.getSource() == buttonArray[13] || e.getSource() == buttonArray[14] || e.getSource() == buttonArray[15] || e.getSource() == buttonArray[16]|| e.getSource() == buttonArray[17]  ) {//The buttons that constitute row 3
                    //System.out.println("ROW THREE");
                    row = 2;

                    //System.out.println();
                }
                if (e.getSource() == buttonArray[18] || e.getSource() == buttonArray[19] || e.getSource() == buttonArray[20] || e.getSource() == buttonArray[21] || e.getSource() == buttonArray[22]|| e.getSource() == buttonArray[23]  ) {//The buttons that constitute row 4
                    //System.out.println("ROW FOUR");
                    row = 3;

                    //System.out.println();
                }
                if (e.getSource() == buttonArray[24] || e.getSource() == buttonArray[25] || e.getSource() == buttonArray[26] || e.getSource() == buttonArray[27] || e.getSource() == buttonArray[28]|| e.getSource() == buttonArray[29]  ) {//The buttons that constitute row 5
                    //System.out.println("ROW FIVE");
                    row = 4;

                    //System.out.println();
                }
                if (e.getSource() == buttonArray[30] || e.getSource() == buttonArray[31] || e.getSource() == buttonArray[32] || e.getSource() == buttonArray[33] || e.getSource() == buttonArray[34]|| e.getSource() == buttonArray[35]  ) { //The buttons that constitute row 6
                    //System.out.println("ROW SIX");
                    row = 5;

                    //System.out.println();
                }
                if (e.getSource() == buttonArray[36] || e.getSource() == buttonArray[37] || e.getSource() == buttonArray[38] || e.getSource() == buttonArray[39] || e.getSource() == buttonArray[40]|| e.getSource() == buttonArray[41]  ) { //The buttons that constitute row 7
                    //System.out.println("ROW SEVEN");
                    row = 6;

                    //System.out.println();
                }

                for (int i = 0; i < 7; i++) { //Checks horz win
                    for (int j = 0; j < 4; j++) {

                        if (rowArray[i][j].getBackground() == Color.red && rowArray[i][j+1].getBackground() == Color.red && rowArray[i][j+2].getBackground() == Color.red && rowArray[i][j+3].getBackground() == Color.red ){
                            redWins++;
                            gameFrame.setTitle("Player 1 wins!                      P1: " + redWins + " / P2: " + blueWins);
                            JOptionPane.showMessageDialog(null,"Player 1 wins!");
                            win = true;

                        }
                        if (rowArray[i][j].getBackground() == Color.blue && rowArray[i][j+1].getBackground() == Color.blue && rowArray[i][j+2].getBackground() == Color.blue && rowArray[i][j+3].getBackground() == Color.blue ){
                            blueWins++;
                            gameFrame.setTitle("Player 2 wins!                      P1: " + redWins + " / P2: " + blueWins);
                            JOptionPane.showMessageDialog(null,"Player 2 wins!");
                            win = true;         
                        }
                    }
                }

                for (int i = 0; i < 6; i++) { //Checks vertical win
                    for (int j = 0; j < 4; j++) {

                        if (columnArray[i][j].getBackground() == Color.red && columnArray[i][j+1].getBackground() == Color.red && columnArray[i][j+2].getBackground() == Color.red && columnArray[i][j+3].getBackground() == Color.red ){
                            redWins++;
                            gameFrame.setTitle("Player 1 wins!                      P1: " + redWins + " / P2: " + blueWins);
                            JOptionPane.showMessageDialog(null,"Player 1 wins!");
                            win = true;                            
                        }
                        if (columnArray[i][j].getBackground() == Color.blue && columnArray[i][j+1].getBackground() == Color.blue && columnArray[i][j+2].getBackground() == Color.blue && columnArray[i][j+3].getBackground() == Color.blue ){                            
                            win = true;
                            blueWins++;
                            gameFrame.setTitle("Player 2 wins!                      P1: " + redWins + " / P2: " + blueWins);
                            JOptionPane.showMessageDialog(null,"Player 2 wins!");
                        }
                    }
                }

                for (int i = 0; i < 4; i++) { //Diagonal right upward
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j].getBackground() == Color.red && board[i+1][j+1].getBackground() == Color.red && board[i+2][j+2].getBackground() == Color.red && board[i+3][j+3].getBackground() == Color.red) {                           
                            win = true;
                            redWins++;
                            gameFrame.setTitle("Player 1 wins!                      P1: " + redWins + " / P2 " + blueWins);
                            JOptionPane.showMessageDialog(null,"Player 1 wins!");
                        }
                        if (board[i][j].getBackground() == Color.blue && board[i+1][j+1].getBackground() == Color.blue && board[i+2][j+2].getBackground() == Color.blue && board[i+3][j+3].getBackground() == Color.blue) {                            
                            win = true;
                            blueWins++;
                            gameFrame.setTitle("Player 2 wins!                      P1: " + redWins + " / P2: " + blueWins);
                            JOptionPane.showMessageDialog(null,"Player 2 wins!");
                        }
                    }
                }

                for (int i = 0; i < 4; i++) { //Diagonal right upward
                    for (int j = 5; j > 2; j--) {
                        if (board[i][j].getBackground() == Color.red && board[i+1][j-1].getBackground() == Color.red && board[i+2][j-2].getBackground() == Color.red && board[i+3][j-3].getBackground() == Color.red) {
                            redWins++;
                            gameFrame.setTitle("Player 1 wins!                      P1: " + redWins + " / P2: " + blueWins);
                            JOptionPane.showMessageDialog(null,"Player 1 wins!");
                            win = true;                            
                        }
                        if (board[i][j].getBackground() == Color.blue && board[i+1][j-1].getBackground() == Color.blue && board[i+2][j-2].getBackground() == Color.blue && board[i+3][j-3].getBackground() == Color.blue) {
                            blueWins++;
                            gameFrame.setTitle("Player 2 wins!                      P1: " + redWins + " / P2: " + blueWins);
                            JOptionPane.showMessageDialog(null,"Player 2 wins!");
                            win = true;
                        }
                    }
                }

            }
            if (e.getSource() == reset) {
                win = false;
                clicks = 0;
                blueWins = 0;
                redWins = 0;

                gameFrame.dispose();
                gameFrame.setTitle("Connect 4: Player 2's (red) turn                         P1: " + redWins + " / P2: " + blueWins);
                for (int i = 0; i < 42; i++) { // Removes all the buttons from the board
                    gameFrame.remove(buttonArray[i]);

                }

                stackArray = new int[6]; //Reinitializes the stack array

                for (int i = 0; i < (42); i++ ) { // Places the buttons back onto the board

                    ImageIcon frame  = new ImageIcon("tile.jpg");
                    Image img = frame.getImage() ;  
                    Image newimg = img.getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  

                    buttonArray[i] = new JButton ();
                    frame = new ImageIcon(newimg);
                    buttonArray[i].setIcon(frame);
                    buttonArray[i].setBackground(Color.white);
                    buttonArray[i].addActionListener(listener);
                    buttonArray[i].setBorder(BorderFactory.createLineBorder(Color.orange)); // Simple Line Border
                    ;

                    gameFrame.add(buttonArray[i]);
                    gameFrame.setSize(787, 868);
                    gameFrame.setSize(700, 833);

                } 
                gameFrame.setVisible(true); //Makes the frame visible

            }
            if(e.getSource() == menuItemNewGame) //If the user presses new game
            {
                win = false;
                clicks = 0;

                gameFrame.dispose();
                gameFrame.setTitle("Connect 4: Player 2's (red) turn                         P1: " + redWins + " / P2: " + blueWins);
                for (int i = 0; i < 42; i++) { // Removes all the buttons from the board
                    gameFrame.remove(buttonArray[i]);

                }

                stackArray = new int[6]; //Reinitializes the stack array

                for (int i = 0; i < (42); i++ ) { // Places the buttons back onto the board
                    ImageIcon frame  = new ImageIcon("tile.jpg");
                    Image img = frame.getImage() ;  
                    Image newimg = img.getScaledInstance( 125, 125,  java.awt.Image.SCALE_SMOOTH ) ;  

                    buttonArray[i] = new JButton ();
                    frame = new ImageIcon(newimg);
                    buttonArray[i].setIcon(frame);
                    buttonArray[i].setBackground(Color.white);
                    buttonArray[i].addActionListener(listener);
                    buttonArray[i].setBorder(BorderFactory.createLineBorder(Color.orange)); // Simple Line Border
                    ;

                    gameFrame.add(buttonArray[i]);
                    gameFrame.setSize(787, 868);
                    gameFrame.setSize(700, 833);
                } 
                gameFrame.setVisible(true); //Makes the frame visible
            }
            if (e.getSource() == help) { // When the user clicks the help button
                JOptionPane.showMessageDialog(null,"Player 1: Red \nPlayer 2: Blue\nEach player must attempt to connect 4 tiles with the same colour. \nThis game functions horizontally, vertically, and diagonally.\nGood luck!");
            }
        }

    }

}

