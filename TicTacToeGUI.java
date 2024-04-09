import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private static final int SIZE = 3;
    private JButton[][] buttons;
    private char currentPlayer = 'X';
    
    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(SIZE, SIZE));
        
        buttons = new JButton[SIZE][SIZE];
        initializeBoard();
        
        setVisible(true);
    }
    
    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.addActionListener(this);
                buttons[i][j] = button;
                add(button);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if (!button.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid move. Try again.");
            return;
        }
        button.setText(String.valueOf(currentPlayer));
        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }
    
    private boolean checkWin() {
        String[][] board = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].isEmpty()) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].isEmpty()) {
                return true;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].isEmpty()) {
            return true;
        }
        return board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].isEmpty();
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }
    
    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
