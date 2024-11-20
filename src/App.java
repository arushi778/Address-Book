import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App{
    private AddressBook addressBook;

    public App() {
        addressBook = new AddressBook();
        addressBook.loadEntriesFromCSV("C:\\Users\\Arushi\\OneDrive\\Desktop\\Address-Book\\src\\test_data.csv");

        JFrame frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel searchPanel = createSearchPanel();
        tabbedPane.addTab("Search", searchPanel);


        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Search Options"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel exactSearchLabel = new JLabel("Exact Search by Name:");
        JTextField exactSearchField = new JTextField(20);
        JButton exactSearchButton = new JButton("Search");

        JLabel prefixSearchLabel = new JLabel("Prefix Search by Name:");
        JTextField prefixSearchField = new JTextField(20);
        JButton prefixSearchButton = new JButton("Search");

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(exactSearchLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(exactSearchField, gbc);
        gbc.gridx = 2;
        inputPanel.add(exactSearchButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(prefixSearchLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(prefixSearchField, gbc);
        gbc.gridx = 2;
        inputPanel.add(prefixSearchButton, gbc);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Search Results"));

        JTable resultTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Name", "Phone", "Address", "Email"});
        resultTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        JLabel statusLabel = new JLabel("Enter a name or prefix to begin your search.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(resultPanel, BorderLayout.CENTER);
        panel.add(statusLabel, BorderLayout.SOUTH);

        exactSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = exactSearchField.getText().toLowerCase();
                ArrayList<AddressEntry> results = Trie.exactSearch(name);
                updateTable(results, tableModel, statusLabel);
            }
        });

        prefixSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prefix = prefixSearchField.getText().toLowerCase();
                ArrayList<AddressEntry> results = Trie.prefixSearch(prefix);
                updateTable(results, tableModel, statusLabel);
            }
        });

        return panel;
    }

    private void updateTable(ArrayList<AddressEntry> results, DefaultTableModel tableModel, JLabel statusLabel) {
        tableModel.setRowCount(0); 
        if (!results.isEmpty()) {
            for (AddressEntry entry : results) {
                tableModel.addRow(new Object[]{entry.getName(), entry.getAddress(), entry.getPhoneNumber(), entry.getEmail()});
            }
            statusLabel.setText("Found " + results.size() + " result(s).");
        } else {
            statusLabel.setText("No results found.");
            JOptionPane.showMessageDialog(null, "No results found.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
