
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AddressBook {
    private ArrayList<AddressEntry> entries;

    public AddressBook() {
        entries = new ArrayList<>();
    }

    public void addEntry(AddressEntry entry) {
        entries.add(entry);
        Trie.insert(entry.getName().toLowerCase(), entry);
    }

    public void loadEntriesFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    AddressEntry entry = new AddressEntry(data[1], data[2], data[3], data[4]);
                    
                    Trie.insert(data[1].toLowerCase(), entry);
                    //addEntry(entry); //not required uwu :3
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
            System.out.println("Entries loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
    }
}
}