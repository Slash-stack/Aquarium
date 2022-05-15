import simulation.environnement.Aquarium;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class testAquarium {
    public static void main(String[] args) throws IOException {

        PrintStream POPULATION = new PrintStream(new FileOutputStream("output/population.txt"));
        PrintStream ESPECE_POPULATION_1 = new PrintStream(new FileOutputStream("output/population1.txt"));
        PrintStream ESPECE_POPULATION_2 = new PrintStream(new FileOutputStream("output/population2.txt"));
        PrintStream ESPECE_POPULATION_3 = new PrintStream(new FileOutputStream("output/population3.txt"));
        PrintStream ESPECE_POPULATION_4 = new PrintStream(new FileOutputStream("output/population4.txt"));
        PrintStream ESPECE_POPULATION_5 = new PrintStream(new FileOutputStream("output/population5.txt"));
        PrintStream ESPECE_POPULATION_6 = new PrintStream(new FileOutputStream("output/population6.txt"));

        Aquarium aquaTest = new Aquarium(POPULATION,
                ESPECE_POPULATION_1,
                ESPECE_POPULATION_2,
                ESPECE_POPULATION_3,
                ESPECE_POPULATION_4,
                ESPECE_POPULATION_5,
                ESPECE_POPULATION_6
                );
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
