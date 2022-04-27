import simulation.environnement.Aquarium;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class testAquarium {
    public static void main(String[] args) throws IOException {

        System.setOut(new PrintStream(new FileOutputStream("output/population.txt")));

        Aquarium aquaTest = new Aquarium();
        for (int i = 0; i < 12; i++) {
            aquaTest.addFish(i%6, (i < 6)? i%2 : (i+1)%2);
            aquaTest.addSeaWeed();
        }
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
        aquaTest.spendTime();
    }
}
