import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;

public class Main {
    private static HashMap<String, Integer> registeredUsers = new HashMap<>();
    private static JFrame frame;
    private static final String FILE_NAME = "registeredUsers.dat";

    public static void main(String[] args) {
        loadRegisteredUsers();
        setupShutdownHook();
        showFusionCascadeScreen();
    }

    private static void loadRegisteredUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            registeredUsers = (HashMap<String, Integer>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing user data found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveRegisteredUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(registeredUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Saving data before exiting...");
            saveRegisteredUsers();
        }));
    }

    private static void showFusionCascadeScreen() {
        frame = new JFrame("Fusion Cascade");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to full screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.getContentPane().setBackground(new Color(0xCEA2FD));

        JLabel titleLabel = new JLabel("Fusion Cascade", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Edwardian Script ITC", Font.BOLD, 120));
        titleLabel.setForeground(new Color(0xFFB7CE)); // Set text color
        frame.add(titleLabel, BorderLayout.CENTER);

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Serif", Font.BOLD, 16));
        continueButton.setForeground(new Color(0xFFB7CE)); // Set text color

        // Directly proceed to sign-up or login screen
        continueButton.addActionListener(e -> showSignUpOrLoginScreen());
        frame.add(continueButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static void showSignUpOrLoginScreen() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setBackground(new Color(0xCEA2FD)); // Set window background color


        JLabel fusionCascadeLabel = new JLabel("Welcome", JLabel.CENTER);
        fusionCascadeLabel.setFont(new Font("Forte", Font.ITALIC, 60));
        fusionCascadeLabel.setForeground(Color.WHITE); // Set text color to white


        JLabel welcomeLabel = new JLabel("Fusion Cascade", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Elephant", Font.ITALIC, 65));
        welcomeLabel.setForeground(new Color(0xFFB7CE));


        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Login");


        signUpButton.setFont(new Font("Serif", Font.BOLD,30));
        loginButton.setFont(new Font("Serif", Font.BOLD, 35));

        signUpButton.setForeground(new Color(0xFFB7CE));
        loginButton.setForeground(new Color(0xFFB7CE));


        signUpButton.addActionListener(e -> showSignUpScreen());
        loginButton.addActionListener(e -> showLoginScreen());


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(0xCEA2FD)); // Set the background color of the content panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        contentPanel.add(fusionCascadeLabel, gbc);

        gbc.gridy = 1;
        contentPanel.add(welcomeLabel, gbc);

        gbc.gridy = 2;
        contentPanel.add(signUpButton, gbc);

        gbc.gridy = 3;
        contentPanel.add(loginButton, gbc);


        contentPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0xCEA2FD));
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        // Add the main panel to the frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }



    private static void showSignUpScreen() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setBackground(new Color(0xCEA2FD)); // Set window background color


        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(20);


        nameField.setPreferredSize(new Dimension(400, 20)); // Width increased, height reduced
        ageField.setPreferredSize(new Dimension(400, 20)); // Width increased, height reduced

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        // Increase font size for submit and back buttons
        submitButton.setFont(new Font("Arial", Font.BOLD, 24));
        backButton.setFont(new Font("Arial", Font.BOLD, 24));

        submitButton.setForeground(new Color(0xFFB7CE));
        backButton.setForeground(new Color(0xFFB7CE));

        // Add action listeners
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String ageText = ageField.getText();

            if (name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                if (registeredUsers.containsKey(name)) {
                    JOptionPane.showMessageDialog(frame, "Username already taken!");
                } else {
                    registeredUsers.put(name, age);
                    saveRegisteredUsers();
                    JOptionPane.showMessageDialog(frame, "Signed up successfully!");
                    showSignUpOrLoginScreen();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid age! Please enter a number.");
            }
        });

        backButton.addActionListener(e -> showSignUpOrLoginScreen());

        // Create panel for layout
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(new Color(0xCEA2FD)); // Set panel background color
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));

        // Center the components within the signUpPanel
        signUpPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel headingLabel = new JLabel("Sign Up", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 50));
        headingLabel.setForeground(new Color(0xFFB7CE));


        signUpPanel.add(Box.createVerticalStrut(10));
        signUpPanel.add(headingLabel);


        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(0xCEA2FD));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        signUpPanel.add(Box.createVerticalStrut(10));
        signUpPanel.add(namePanel);


        JPanel agePanel = new JPanel();
        agePanel.setBackground(new Color(0xCEA2FD));
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        agePanel.add(ageLabel);
        agePanel.add(ageField);
        signUpPanel.add(Box.createVerticalStrut(10));
        signUpPanel.add(agePanel);

        // Add buttons panel at the bottom with spacing between buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xCEA2FD));
        buttonPanel.add(submitButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(backButton);
        signUpPanel.add(Box.createVerticalStrut(20));
        signUpPanel.add(buttonPanel);

        // Create a container panel for centering the signUpPanel within the frame
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridBagLayout());
        containerPanel.setBackground(new Color(0xCEA2FD));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        containerPanel.add(signUpPanel, gbc);


        frame.add(containerPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }


    private static void showLoginScreen() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setBackground(new Color(0xCEA2FD));

        // Create components
        JTextField nameField = new JTextField(25);
        nameField.setFont(new Font("Serif", Font.PLAIN, 18));
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");
        JLabel headingLabel = new JLabel("Login");

        // Style components
        headingLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 55));
        headingLabel.setForeground(new Color(0xFFB7CE));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        loginButton.setFont(new Font("Serif", Font.BOLD, 20));
        backButton.setFont(new Font("Serif", Font.BOLD, 20));

        loginButton.setForeground(new Color(0xFFB7CE));
        backButton.setForeground(new Color(0xFFB7CE));

        // Add action listeners
        loginButton.addActionListener(e -> {
            String name = nameField.getText();
            if (registeredUsers.containsKey(name)) {
                JOptionPane.showMessageDialog(frame, "Welcome, " + name + "!");
            } else {
                JOptionPane.showMessageDialog(frame, "User not found! Please sign up first.");
            }
        });

        backButton.addActionListener(e -> showSignUpOrLoginScreen());


        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(0xCEA2FD));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        loginPanel.add(Box.createVerticalStrut(20));

        // Create a panel for the heading and center it using FlowLayout
        JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headingPanel.setBackground(new Color(0xCEA2FD));
        headingPanel.add(headingLabel);
        loginPanel.add(headingPanel);

        loginPanel.add(Box.createVerticalStrut(10));

        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(0xCEA2FD));
        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameField);
        loginPanel.add(namePanel);


        loginPanel.add(Box.createVerticalStrut(10));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xCEA2FD));
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(backButton);
        loginPanel.add(buttonPanel);

        // Center the loginPanel inside the frame using GridBagLayout
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridBagLayout());
        containerPanel.setBackground(new Color(0xCEA2FD));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        containerPanel.add(loginPanel, gbc);

        // Add the container panel to the frame
        frame.add(containerPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}