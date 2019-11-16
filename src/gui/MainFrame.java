package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Main window of the game
 */
public final class MainFrame extends JFrame
{
    private JLabel score, scoreLabel;
    private JPanel leftPanel = new JPanel();
    private JLayeredPane rightPanel = new JLayeredPane();
    public JPanel gameOverPanel;
    private GameBoardPanel gameBoardPanel;
    private JButton startButton = new JButton("START");
    private static final Insets insets = new Insets(0,0,0,0);

    /**
     * Initializes main window of the game
     * @param gameBoardPanel Game board to be added to the window
     */
    public MainFrame(GameBoardPanel gameBoardPanel)
    {
        super("Tetris by Fox");

        this.gameBoardPanel = gameBoardPanel;
        layoutComponents();

        setMinimumSize(new Dimension(600, 700));
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.CYAN);
        setVisible(true);
    }

    private void layoutComponents() {
        createScoreLabel();
        startButton.setFont(new Font("Candara", Font.BOLD, 20));

        setLayout(new GridBagLayout());
        leftPanel.setLayout(new GridBagLayout());
        rightPanel.setLayout(new GridBagLayout());
        addComponent(this, leftPanel, 0, 0, 1, GridBagConstraints.WEST, GridBagConstraints.CENTER, 30);
        addComponent(this, rightPanel, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 70);
        addComponent(rightPanel, gameBoardPanel, 0, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0);
        addComponent(leftPanel, scoreLabel, 0, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, 0);
        addComponent(leftPanel, score, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, 0);
        addComponent(leftPanel, startButton, 0, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 0);
        rightPanel.setLayer(gameBoardPanel, JLayeredPane.DEFAULT_LAYER);
        createGameOverPanel();
    }

    private void addComponent(Container container, Component component, int gridX, int gridY, int gridWidth, int anchor, int fill, int weightX)
    {
        GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, gridWidth, 1, 1.0, 1.0, anchor, fill, insets, 0, 0);
        gbc.weightx = weightX;
        container.add(component, gbc);
    }

    private void createScoreLabel(){
        scoreLabel = new JLabel("Score: ");
        scoreLabel.setFont(new Font("Candara", Font.BOLD, 28));
        score = new JLabel();
        score.setFont(new Font ("Candara", Font.BOLD, 30));
        score.setForeground(Color.BLUE);
    }

    private void createGameOverPanel(){
        gameOverPanel = new JPanel();
        JLabel text = new JLabel("GAME OVER");
        text.setFont(new Font("Candara", Font.BOLD, 28));
        text.setForeground(Color.WHITE);
        addComponent(rightPanel, gameOverPanel, 0, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0);
        addComponent(gameOverPanel, text, 0, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0);

        rightPanel.setLayer(gameOverPanel, 2);
        rightPanel.setLayer(text, JLayeredPane.POPUP_LAYER);

        gameOverPanel.setOpaque(false);
        gameOverPanel.setVisible(false);
    }

    /**
     * Sets new score in the score label
     * @param score New score
     */
    public void setScoreLabel(String score)
    {
        this.score.setText(score);
    }

    /**
     * Adds listener to the start button
     * @param start Action listener for the button
     */
    public void addStartListener(ActionListener start){
        startButton.addActionListener(start);
    }

    /**
     * Disables start button
     */
    public void disableStartButton(){
        startButton.setEnabled(false);
    }

    /**
     * Displays game over panel
     */
    public void gameOver(){
        gameOverPanel.setVisible(true);
        startButton.setEnabled(true);
        startButton.setText("PLAY AGAIN");
    }
}