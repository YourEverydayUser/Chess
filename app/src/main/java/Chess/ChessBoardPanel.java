package Chess;

import Observer.BoardObserver;
import Pieces.Board;
import Pieces.Field;
import Pieces.Figures.Figure;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class ChessBoardPanel extends JPanel implements BoardObserver {
    private final HashMap<Field, Figure> board;
    private final Image[] images = new Image[12];

    public ChessBoardPanel(HashMap<Field, Figure> board) throws IOException {
        this.board = board;

        BufferedImage all = ImageIO.read(new File("C:\\Users\\Fabian\\Desktop\\Chess\\app\\src\\main\\resources\\chess.png"));
        int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                images[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if((x+y) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.darkGray);
                }
                g.fillRect(x*64, y*64, 64, 64);
            }
        }

        String figureType;
        for(Field field : board.keySet()) {
            int ind=0;
            Figure figure = board.get(field);

            if(figure != null) {
                figureType = figure.getName();
                switch (figureType) {
                    case ("King") -> ind = figure.getColor() == Pieces.Figures.Color.BLACK ? 0 : 6;
                    case ("Queen") -> ind = figure.getColor() == Pieces.Figures.Color.BLACK ? 1 : 7;
                    case ("Bishop") -> ind = figure.getColor() == Pieces.Figures.Color.BLACK ? 2 : 8;
                    case ("Knight") -> ind = figure.getColor() == Pieces.Figures.Color.BLACK ? 3 : 9;
                    case ("Tower") -> ind = figure.getColor() == Pieces.Figures.Color.BLACK ? 4 : 10;
                    case ("Pawn") -> ind = figure.getColor() == Pieces.Figures.Color.BLACK ? 5 : 11;
                }
                g.drawImage(images[ind], field.getxCoordinate() * 64, field.getyCoordinate() * 64, this);
            }
        }
    }

    @Override
    public void update(Board board) {
            this.board.clear();
            this.board.putAll(board.getGameBoard());
            repaint();
    }

}

