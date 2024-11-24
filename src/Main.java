import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainMenuSwing {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Main Menu");

        // Set the frame size slightly smaller than the full screen to keep the taskbar visible
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight() - 50; // Leave space for the taskbar
        frame.setBounds(0, 0, width, height);
        frame.setLayout(new BorderLayout());

        // Set window background color
        frame.getContentPane().setBackground(Color.decode("#CEA2FD"));

        // Title label
        JLabel titleLabel = new JLabel("Fusion Cascade", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Heydex", Font.PLAIN, 36)); // Changed to "Heydex" (plain)
        titleLabel.setForeground(Color.decode("#FFB7CE")); // Changed text color to #FFB7CE
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Make the panel transparent

        // GridBagConstraints for button positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Spacing between buttons

        // Create a custom button style
        Font buttonFont = new Font("Arial", Font.PLAIN, 24);

        // Play Button
        JButton playButton = new JButton("Play");
        styleButton(playButton, buttonFont);
        gbc.gridy = 0;
        buttonPanel.add(playButton, gbc);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Starting the game...");
                // Add code to launch the game
            }
        });

        // Instructions Button
        JButton instructionsButton = new JButton("Instructions");
        styleButton(instructionsButton, buttonFont);
        gbc.gridy = 1;
        buttonPanel.add(instructionsButton, gbc);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInstructionsWindow();
            }
        });

        // Scoreboard Button
        JButton scoreboardButton = new JButton("Scoreboard");
        styleButton(scoreboardButton, buttonFont);
        gbc.gridy = 2;
        buttonPanel.add(scoreboardButton, gbc);
        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openScoreboardWindow(); // Open the new Scoreboard window
            }
        });

        // Exit Button
        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, buttonFont);
        gbc.gridy = 3;
        buttonPanel.add(exitButton, gbc);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });

        // Add button panel to frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Prevent resizing
        frame.setResizable(false);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the frame
        frame.setVisible(true);
    }

    // Helper method to style buttons
    private static void styleButton(JButton button, Font font) {
        button.setFont(font);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50)); // Set preferred button size
    }

    private static void openInstructionsWindow() {
        // Create a new frame
        JFrame instructionsFrame = new JFrame("Instructions");

        // Set the frame size slightly smaller than the full screen to keep the taskbar visible
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight() - 50; // Leave space for the taskbar
        instructionsFrame.setBounds(0, 0, width, height);

        // Set layout manager to GridBagLayout for centering
        instructionsFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add space around components

        // Set background color to #CEA2FD
        instructionsFrame.getContentPane().setBackground(Color.decode("#CEA2FD"));

        // Center panel for everything
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Vertical layout
        centerPanel.setOpaque(false); // Make the panel transparent

        // Add heading (How to Play 2048) in a large font
        JLabel headingLabel = new JLabel("How to Play Fusion Cascade", JLabel.CENTER);
        headingLabel.setFont(new Font("Heydex", Font.PLAIN, 40)); // Changed font to "Heydex" (plain)
        headingLabel.setForeground(Color.decode("#FFB7CE")); // Changed text color to #FFB7CE
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center it horizontally
        centerPanel.add(headingLabel);

        // Add space between heading and instructions
        centerPanel.add(Box.createVerticalStrut(20));

        // Add each instruction as a separate JLabel (no wrapping)
        JLabel instruction1 = new JLabel("1. Swipe or use arrow keys to move the fruits on the board.");
        instruction1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20)); // Changed font to "Monotype Corsiva"
        instruction1.setForeground(Color.WHITE); // Changed text color to white
        instruction1.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction1);

        JLabel instruction2 = new JLabel("2. Combine matching fruits (e.g., two apples) to create bigger fruits (e.g., an orange).");
        instruction2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20)); // Changed font to "Monotype Corsiva"
        instruction2.setForeground(Color.WHITE); // Changed text color to white
        instruction2.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction2);

        JLabel instruction3 = new JLabel("3. The goal is to reach the ultimate fruit tile for the ultimate fruit salad..");
        instruction3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20)); // Changed font to "Monotype Corsiva"
        instruction3.setForeground(Color.WHITE); // Changed text color to white
        instruction3.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction3);

        JLabel instruction4 = new JLabel("4. Strategize to avoid filling the board completely with fruits..");
        instruction4.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20)); // Changed font to "Monotype Corsiva"
        instruction4.setForeground(Color.WHITE); // Changed text color to white
        instruction4.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction4);

        JLabel instruction5 = new JLabel("Keep merging fruits to earn points and keep playing for a higher score!");
        instruction5.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20)); // Changed font to "Monotype Corsiva"
        instruction5.setForeground(Color.WHITE); // Changed text color to white
        instruction5.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(instruction5);

        // Add the centerPanel to the frame and center it using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Center the content
        instructionsFrame.add(centerPanel, gbc);

        // Add a close button at the bottom, also centered
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        closeButton.addActionListener(e -> instructionsFrame.dispose());

        gbc.gridy = 1; // Move close button to the next row
        instructionsFrame.add(closeButton, gbc);

        // Prevent resizing
        instructionsFrame.setResizable(false);

        // Display the instructions window
        instructionsFrame.setVisible(true);
    }

    // Method to open the Scoreboard window
    private static void openScoreboardWindow() {
        // Create a new frame
        JFrame scoreboardFrame = new JFrame("Scoreboard");

        // Set the frame size slightly smaller than the full screen to keep the taskbar visible
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight() - 50; // Leave space for the taskbar
        scoreboardFrame.setBounds(0, 0, width, height);

        // Set background color to #CEA2FD
        scoreboardFrame.getContentPane().setBackground(Color.decode("#CEA2FD"));

        // Create a panel to center everything
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Vertical layout
        centerPanel.setOpaque(false); // Make the panel transparent

        // Add space at the top (to ensure title is not at the very top)
        centerPanel.add(Box.createVerticalStrut(100)); // Adjust space as needed

        // Add a title label for the scoreboard
        JLabel titleLabel = new JLabel("High Scores", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Heydex", Font.PLAIN, 36)); // Changed font to "Heydex" (plain)
        titleLabel.setForeground(Color.decode("#FFB7CE")); // Changed text color to #FFB7CE
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the title
        centerPanel.add(titleLabel);

        // Add vertical space between title and the scores
        centerPanel.add(Box.createVerticalStrut(40)); // Adjust the value for the desired space

        // Create a JPanel to hold each score as a JLabel
        JPanel scoreboardListPanel = new JPanel();
        scoreboardListPanel.setLayout(new BoxLayout(scoreboardListPanel, BoxLayout.Y_AXIS)); // Vertical layout
        scoreboardListPanel.setOpaque(false); // Make the panel transparent

        // Add the scores as labels (to keep them in one line each)
        String[] scores = {
                "1. Player 1 - 2048",
                "2. Player 2 - 1024",
                "3. Player 3 - 512",
                "4. Player 4 - 256",
                "5. Player 5 - 128"
        };

        for (String score : scores) {
            JLabel scoreLabel = new JLabel(score, SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20)); // Changed font to "Monotype Corsiva"
            scoreLabel.setForeground(Color.WHITE); // Changed text color to white
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center each score
            scoreboardListPanel.add(scoreLabel);
        }

        // Add the scoreboard panel to the center panel
        centerPanel.add(scoreboardListPanel);

        // Add space before the close button
        centerPanel.add(Box.createVerticalStrut(20));

        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        closeButton.addActionListener(e -> scoreboardFrame.dispose());
        centerPanel.add(closeButton);

        // Add a filler component to vertically center the panel inside the frame
        centerPanel.add(Box.createVerticalGlue());

        // Add the centered content to the frame
        scoreboardFrame.add(centerPanel, BorderLayout.CENTER);

        // Prevent resizing
        scoreboardFrame.setResizable(false);

        // Display the scoreboard window
        scoreboardFrame.setVisible(true);
    }
}
