import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Rover {
    private int x;
    private int y;
    private char direction;

    public Rover(int x, int y, char direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void processInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'L') {
                turnLeft();
            } else if (instruction == 'R') {
                turnRight();
            } else if (instruction == 'M') {
                move();
            }
        }
    }

    private void turnLeft() {
        switch (direction) {
            case 'N':
                direction = 'W';
                break;
            case 'E':
                direction = 'N';
                break;
            case 'S':
                direction = 'E';
                break;
            case 'W':
                direction = 'S';
                break;
        }
    }

    private void turnRight() {
        switch (direction) {
            case 'N':
                direction = 'E';
                break;
            case 'E':
                direction = 'S';
                break;
            case 'S':
                direction = 'W';
                break;
            case 'W':
                direction = 'N';
                break;
        }
    }

    private void move() {
        switch (direction) {
            case 'N':
                y++;
                break;
            case 'E':
                x++;
                break;
            case 'S':
                y--;
                break;
            case 'W':
                x--;
                break;
        }
    }

    public String getPosition() {
        return x + " " + y + " " + direction;
    }
}

public class MarsRover {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the input file path as an argument.");
            return;
        }

        String inputFile = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            String[] plateauCoords = line.split(" ");
            int plateauX = Integer.parseInt(plateauCoords[0]);
            int plateauY = Integer.parseInt(plateauCoords[1]);

            while ((line = reader.readLine()) != null) {
                int roverX = Integer.parseInt(line.split(" ")[0]);
                int roverY = Integer.parseInt(line.split(" ")[1]);
                char roverDirection = line.split(" ")[2].charAt(0);
                String instructions = reader.readLine();

                Rover rover = new Rover(roverX, roverY, roverDirection);
                rover.processInstructions(instructions);

                System.out.println(rover.getPosition());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the input file: " + e.getMessage());
        }
    }
}
