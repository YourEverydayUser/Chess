/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Chess;

import GameSession.GameSession;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        GameSession gameSession = GameSession.getInstance();
        JFrame frame = new JFrame();
        JPanel panel = new ChessBoardPanel(gameSession.accessBoard().getGameBoard());
        frame.setBounds(100, 100, 512, 512);
        frame.setUndecorated(true);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / 64;
                int y = Math.abs(e.getY() - (8 * 64)) / 64;
                System.out.println("Clicked on field: (" + x + ", " + y + ")");
            }
        });
    }
}
