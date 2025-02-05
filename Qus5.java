package lib;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Qus5{
    public static void main(String[] args) {
        String inputFile = "employees.csv";  // Input CSV file
        String managerFile = "managers.csv";  // Output file for R&D Managers
        String singleNameFile = "single_name_employees.csv";  // Output file for single-name employees

        List<String> managers = new ArrayList<>();
        List<String> singleNameEmployees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean isFirstLine = true;  // To skip the header line

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;  // Skip header
                    continue;
                }

                // Split the line into Name, Role, and Division
                String[] parts = line.split(",");

                if (parts.length < 3) {
                    continue;
                }

                String name = parts[0].trim();
                String role = parts[1].trim();
                String division = parts[2].trim();

                // Condition 1: Check if the employee is a Manager in R&D
                if (role.equalsIgnoreCase("Manager") && division.equalsIgnoreCase("R&D")) {
                    managers.add(line);
                }

                // Condition 2: Check if the employee has only a first name
                if (name.contains(" ")) {  // No space means single name
                    singleNameEmployees.add(line);
                }
            }

            // Write the filtered records to respective files
            writeToFile(managerFile, managers);
            writeToFile(singleNameFile, singleNameEmployees);

            System.out.println("Processing complete. Output saved in managers.csv and single_name_employees.csv.");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to write a list of strings to a file
    private static void writeToFile(String fileName, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String ln : data) {
                writer.write(ln);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }
}

