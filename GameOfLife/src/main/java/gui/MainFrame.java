package gui;

import gui.components.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private DrawingPanel drawingPanel;
    private JLabel generationLabel;
    private JButton nextGenBtn;
    private JButton startBtn;
    private JButton clearBtn;
    private JSlider timeSlider;
    private JLabel timeLabel;

    private Timer timer;
    private int timerSpeed;
    private int generation;

    public static void main(String[] args) {
        new MainFrame();
    }

    public MainFrame() {
        setTitle(" Conway's Game Of Life ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(1000, 600));

        this.timerSpeed = 250;
        this.generation = 0;

        setMainPanel();
        setOptionsPanel();
        setDrawingPanel();

        setOnClicks();

        setOnCloseListener();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        this.setContentPane(mainPanel);
    }

    private void    setDrawingPanel() {
        drawingPanel = new DrawingPanel(660, 60);
        mainPanel.add(drawingPanel);
    }

    private void setOptionsPanel()
    {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridBagLayout());
        optionsPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        generationLabel = new JLabel("Generation: 0");
        optionsPanel.add(generationLabel, gbc);

        optionsPanel.add(Box.createVerticalStrut(20), gbc);

        nextGenBtn = new JButton("Next Generation");
        optionsPanel.add(nextGenBtn, gbc);

        optionsPanel.add(Box.createVerticalStrut(20), gbc);

        startBtn = new JButton("Start");
        optionsPanel.add(startBtn, gbc);

        optionsPanel.add(Box.createVerticalStrut(20), gbc);


        clearBtn = new JButton("Clear ");
        optionsPanel.add(clearBtn,gbc);

        optionsPanel.add(Box.createVerticalStrut(20),gbc);



        timeSlider = new JSlider(30, 2000);
        timeSlider.setValue(250);
        optionsPanel.add(timeSlider, gbc);
        timeLabel = new JLabel("Duration: " + timeSlider.getValue() + " ms");
        optionsPanel.add(timeLabel, gbc);

        mainPanel.add(optionsPanel);

    }

    private void setOnClicks() {

        nextGenBtn.addActionListener(e -> {
            drawingPanel.getCells().nextGeneration();
            drawingPanel.repaint();

            generation++;
            updateGenerationLabel();
        });

        startBtn.addActionListener(e -> {
            if (startBtn.getText().equals("Start")) {
                startTimer();
            } else if (startBtn.getText().equals("Stop")) {
                stopTimer();
            }
        });

        clearBtn.addActionListener(e -> {

            drawingPanel.getCells().resetCells();
            generation = 0;
            updateGenerationLabel();
            stopTimer();
            drawingPanel.repaint();


        });

        timeSlider.addChangeListener(e -> {
            int value = timeSlider.getValue();
            timerSpeed = value;
            timeLabel.setText("Duration: " + value + " ms");
            if (timer != null) {
                stopTimer();
                startTimer();
            }
        });
    }

    private void startTimer() {
        startBtn.setText("Stop");

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drawingPanel.getCells().nextGeneration();
                drawingPanel.repaint();
                generation++;
                updateGenerationLabel();
            }
        }, 500, timerSpeed);
    }

    private void stopTimer() {
        startBtn.setText("Start");

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void updateGenerationLabel()
    {
        generationLabel.setText("Generation: " + generation);
    }

    private void setOnCloseListener()
    {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }
        });
    }

}
