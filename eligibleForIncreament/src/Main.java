import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Data{
    String name;
    int id;
    double[] performanceScores;
    double sum = 0;


    public Data(String name, int id) {
        this.name = name;
        this.id = id;
        this.performanceScores = new double[6];
    }

    public Data(double sum) {
        this.sum = sum;
    }

    public double avg_score(){
        double avg = sum / 6;
        return avg;
    }



}
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Employees: ");
        int n = sc.nextInt();


        ArrayList<Data> employee_name = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            sc.nextLine();

            System.out.println("Enter Employee Names: ");
            String name = sc.nextLine();

            System.out.println("Enter Employee Id: ");
            int id = sc.nextInt();

            Data employee = new Data(name, id);
            System.out.println("Enter Performance Score over the last 6 months...");
            for (int j = 0; j < 6; j++) {
                employee.performanceScores[j] = sc.nextDouble();
                employee.sum += employee.performanceScores[j];
            }
            employee_name.add(employee);
        }
        //Creating FILE
        File employee_file;
        try {
            employee_file = new File("employee.txt");
            if (employee_file.createNewFile()) {
                System.out.println("File created successfully");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Ceating Increment File
        File increment = new File("increment.txt");
        try{
            if (increment.createNewFile()) {
                System.out.println("Increment File created successfully");
            } else {
                System.out.println("Increment File already exists");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Writing ON FILE
        try {
            BufferedWriter insert_info = new BufferedWriter(new FileWriter("employee.txt"));
            BufferedWriter write_info = new BufferedWriter(new FileWriter("increment.txt"));
            for (Data s : employee_name) {
                insert_info.write("Name: " + s.name);
                insert_info.newLine();
                insert_info.write("Id: " + s.id);
                insert_info.newLine();
                insert_info.write("Average Score: " + (int) s.avg_score());
                insert_info.newLine();
                insert_info.flush();
                if (s.avg_score() > 75){
                    write_info.write("Name: " + s.name);
                    write_info.newLine();
                    write_info.write("Id: " + s.id);
                    write_info.newLine();
                    write_info.write("Average Score: " + (int) s.avg_score());
                    write_info.newLine();
                    write_info.flush();
                }
            }
            System.out.println("Data Written");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Data s : employee_name) {
            System.out.println("Name: " + s.name + "\nID: " + s.id + "\nPerformance Score: " + s.avg_score());
        }
        // Works fine till here

        //Reading From file
//        File readFile = new File("employee.txt");
        ArrayList<Integer> Performance_Score = new ArrayList<Integer>();

        try (BufferedReader reading_file = new BufferedReader(new FileReader("employee.txt"))) {
            System.out.println("Reeeeeeading");
            String employee_info;
            while ((employee_info = reading_file.readLine()) != null) {
                try {
                    System.out.println("Data Inside file: " + employee_info);

                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("File Read Done");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}