import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(
                Files.lines(Paths.get(args[0]))
                        .mapToInt(Integer::parseInt)
                        .map(Main::massToFuel)
                        .sum()
        );
    }

    private static Integer massToFuel(Integer mass) {
        Integer fuel = (mass / 3) - 2;
        return fuel < 1 ? 0 : fuel + massToFuel(fuel);
    }

}
