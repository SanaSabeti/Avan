//Time spent: 4 hours

import java.util.Scanner;

public class RosterAnalyzer {
    private static String[] students = new String[2];
    private static int[] scores = new int[2];
    private static int counter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many students do you have?:");
        int n = scanner.nextInt();

        while(counter<n){
            System.out.println("Student's name:");
            String name = scanner.next();
            System.out.println("Student's score:");
            int score = scanner.nextInt();
            if(score == -1){
                System.out.println("Enter the student again!");
                //continue;
            }
            else if (score >= 0 && score <= 100) {
                //update:
                checkSize();
                students[counter] = name;
                scores[counter] = score;
                counter ++;
                System.out.println("OK, Next!");
            }
            else {
                System.out.println("Warning: invalid score!");
                System.out.println("Enter the student again!");
                //continue;
            }
        }

        int response;
        do{
            System.out.println("What do you want to do?");
            System.out.println("Menu:\n1) Add student\n2) Show report\n3) Curve scores\n4)Exit");
            response = scanner.nextInt();
            switch (response){
                case 1:
                    char answer = 'y';
                    while(answer == 'y'){
                        while(true){
                            System.out.println("Student's name:");
                            String name = scanner.next();
                            System.out.println("Student's score:");
                            int score = scanner.nextInt();
                            if(score == -1){
                                System.out.println("Enter the student again!");
                                //continue;
                            }
                            else if(score >= 0 && score <= 100){
                                //update:
                                checkSize();
                                students[counter] = name;
                                scores[counter] = score;
                                counter++;
                                break;
                            }
                            else{
                                System.out.println("Warning: invalid score");
                                System.out.println("Enter the student again!");
                                //continue;
                            }
                        }
                        System.out.println("Add another student? (y/n)");
                        answer = scanner.next().charAt(0);
                    }
                    break;
                case 2:
                    showReport();
                    break;
                case 3:
                    System.out.println("How many points should be added?");
                    int points = scanner.nextInt();
                    curve(scores, points);
                    System.out.println("Scores after curve:");
                    for(int i=0;i<counter;i++){
                        System.out.println(students[i] + ": " + scores[i]);
                    }
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(response!=4);
    }

    //Adding this method:
    private static void checkSize() {
        if (counter == students.length) {
            String[] newStudents = new String[students.length*2];
            int[] newScores = new int[scores.length*2];

            for (int i = 0; i < counter; i++) {
                newStudents[i] = students[i];
                newScores[i] = scores[i];
            }
            students = newStudents;
            scores = newScores;
            //System.out.println("Array size incraesed!");
        }
    }
    private static double average(int[] scores){
        int sum = 0;
        for (int i = 0; i < counter; i++) {
            sum += scores[i];
        }
        return  (double)sum / counter;
    }

    private static int max(int[] scores){
        int maximum = scores[0];
        for (int i = 1; i < counter; i++) {
            if (scores[i] > maximum) {
                maximum = scores[i];
            }
        }
        return maximum;
    }

    private static int min(int[] scores){
        int minimum = scores[0];
        for (int i = 1; i < counter; i++) {
            if (scores[i] < minimum) {
                minimum = scores[i];
            }
        }
        return minimum;
    }

    private static void curve(int[] scores, int points){
        for (int i = 0; i < counter; i++) {
            scores[i] += points;
            if (scores[i] > 100)
                scores[i] = 100;
        }
    }

    private static String convertScore(int score) {
        switch (score / 10) {
            case 10:
                return "A";
            case 9:
                return "A";
            case 8:
                return "B";
            case 7:
                return "C";
            case 6:
                return "D";
            default:
                return "F";
        }
    }

    private static void showReport() {

        for (int i = 0; i < counter; i++) {
            String status = scores[i] >= 60 ? "Pass" : "Fail";
            String report = String.format("%s - Score: %d - Grade: %s - %s", students[i], scores[i],convertScore(scores[i]),status);
            System.out.println(report);
        }
        System.out.println("Average: " + average(scores));
        System.out.println("Maximum: " + max(scores));
        System.out.println("Minimum: " + min(scores));
    }



}

