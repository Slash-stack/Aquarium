package simulation.environnement;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.*;
import simulation.agents.*;
import java.io.*;
import java.util.*;

/**
 * The type Aquarium.
 */
public class Aquarium {

    PrintStream POPULATION;
    PrintStream ESPECE_POPULATION_1;
    PrintStream ESPECE_POPULATION_2;
    PrintStream ESPECE_POPULATION_3;
    PrintStream ESPECE_POPULATION_4;
    PrintStream ESPECE_POPULATION_5;
    PrintStream ESPECE_POPULATION_6;
    static final String[] KNOWN_SPECIES = {"Carpe", "Bar", "Sole", "Thon", "Mérou", "Poisson-clown"};

    private final HashSet<SeaWeed> seaWeedSet;
    private final HashSet<Fish> fishSet;
    private int round = 0;
    private final Random r;

    /**
     * Instantiates a new Aquarium.
     */
    public Aquarium(PrintStream FILE,
                    PrintStream FILE_1,
                    PrintStream FILE_2,
                    PrintStream FILE_3,
                    PrintStream FILE_4,
                    PrintStream FILE_5,
                    PrintStream FILE_6) {
        POPULATION = FILE;
        ESPECE_POPULATION_1 = FILE_1;
        ESPECE_POPULATION_2 = FILE_2;
        ESPECE_POPULATION_3 = FILE_3;
        ESPECE_POPULATION_4 = FILE_4;
        ESPECE_POPULATION_5 = FILE_5;
        ESPECE_POPULATION_6 = FILE_6;
        this.seaWeedSet = new HashSet<>();
        this.fishSet = new HashSet<>();
        r = new Random();
    }

    /**
     * Gets round.
     *
     * @return the round
     */
    public int getRound() {
        return round;
    }

    /**
     * Gets r.
     *
     * @return the r
     */
    public Random getR() {
        return r;
    }

    /**
     * Add sea weed.
     */
    public void addSeaWeed(){
        seaWeedSet.add(new SeaWeed());
    }

    /**
     * Add fish.
     *
     * @param species the species
     * @param sex     the sex
     */
    public void addFish(int species, int sex){
        fishSet.add(new Fish(species, sex == 1));
    }

    /**
     * Select random fish.
     *
     * @return the fish
     */
    public Fish selectRandomFish(){
        int indexFood = r.nextInt(fishSet.size());

        int i = 0;
        for(Fish fish : fishSet) {
            if (i == indexFood) {
                return fish;
            }
            i++;
        }
        return null;
    }

    /**
     * Select random fish from the array.
     *
     * @return the fish
     */
    public Fish selectRandomFish(ArrayList<Fish> love){
        int indexFood = r.nextInt(love.size());

        int i = 0;
        for(Fish fish : love) {
            if (i == indexFood) {
                return fish;
            }
            i++;
        }
        return null;
    }

    public void updateEdges(ArrayList<Fish> loveSet, Graph<Fish, DefaultEdge> g) {
        for (Fish fish : loveSet) {
            if (fish.isMale()) {
                // selecting a random fish
                int i = 0;
                Fish partner = selectRandomFish(loveSet);
                while (!fish.canMateWith(partner) && i != 10) {
                    partner = selectRandomFish(loveSet);
                    i++;
                }
                if (fish.canMateWith(partner)) {
                    g.addEdge(fish, partner);
                }
            }
        }
    }

    public void makeLove(ArrayList<Fish> loveSet, Graph<Fish, DefaultEdge> g, int counter) {
        for (Fish fish : loveSet) {
            Set<DefaultEdge> edges = g.incomingEdgesOf(fish);
            if (edges.size() >= 1) counter++;
            Fish partner = null;
            double bet = 0.0;
            for (DefaultEdge edge : edges) {
                Fish f = g.getEdgeSource(edge);
                double fishBet = f.getBet();
                if (bet < fishBet) {
                    bet = fishBet;
                    partner = f;
                }
            }
            if (partner != null)
                fishSet.add(new Fish(fish, partner));
        }
    }

    /**
     * Select random sea weed.
     *
     * @return the sea weed
     */
    public SeaWeed selectRandomSeaWeed(){
        int indexFood = r.nextInt(seaWeedSet.size());

        int i = 0;
        for(SeaWeed seaWeed : seaWeedSet) {
            if (i == indexFood) {
                return seaWeed;
            }
            i++;
        }
        return null;
    }

    /**
     * Spend time.
     */
    public void spendTime() throws IOException {

        //HashMap<Fish, ArrayList> loveFish = new HashMap<>();
        System.setOut(POPULATION);
        System.out.println(fishSet.size());

        int n = 0;
        System.setOut(ESPECE_POPULATION_1);
        for (Fish fish : fishSet) {
            if (fish.getSpecies().equals(KNOWN_SPECIES[0])) n++;
        }
        System.out.println(n);
        n = 0;
        System.setOut(ESPECE_POPULATION_2);
        for (Fish fish : fishSet) {
            if (fish.getSpecies().equals(KNOWN_SPECIES[1])) n++;
        }
        System.out.println(n);
        n = 0;
        System.setOut(ESPECE_POPULATION_3);
        for (Fish fish : fishSet) {
            if (fish.getSpecies().equals(KNOWN_SPECIES[2])) n++;
        }
        System.out.println(n);
        n = 0;
        System.setOut(ESPECE_POPULATION_4);
        for (Fish fish : fishSet) {
            if (fish.getSpecies().equals(KNOWN_SPECIES[3])) n++;
        }
        System.out.println(n);
        n = 0;
        System.setOut(ESPECE_POPULATION_5);
        for (Fish fish : fishSet) {
            if (fish.getSpecies().equals(KNOWN_SPECIES[4])) n++;
        }
        System.out.println(n);
        n = 0;
        System.setOut(ESPECE_POPULATION_6);
        for (Fish fish : fishSet) {
            if (fish.getSpecies().equals(KNOWN_SPECIES[5])) n++;
        }
        System.out.println(n);

        ArrayList<Fish> carpeLove = new ArrayList<>();
        ArrayList<Fish> barLove = new ArrayList<>();
        ArrayList<Fish> soleLove = new ArrayList<>();
        ArrayList<Fish> thonLove = new ArrayList<>();
        ArrayList<Fish> merouLove = new ArrayList<>();
        ArrayList<Fish> pcLove = new ArrayList<>();

        // eatenLivingCreatures is where we stock all the dead living creatures
        // to remove them from the aquarium at the end of the round
        ArrayList<SeaWeed> eatenSeaWeed = new ArrayList<>();
        ArrayList<Fish> eatenFish = new ArrayList<>();

        // newLivingCreatures is where we stock all the new living creatures
        // to add them to the aquarium at the end of the round
        ArrayList<Fish> newFish = new ArrayList<>();
        ArrayList<SeaWeed> newSeaWeed = new ArrayList<>();

        // update the aquarium
        round++;
        // update life points
        for (Fish f : fishSet) {
            f.spendTime();
            if (f.getAge() >= 20 || f.getLifePoints() <= 0) eatenFish.add(f);
        }
        for (SeaWeed s : seaWeedSet) {
            s.spendTime();
            if (s.getAge() >= 20) {
                eatenSeaWeed.add(s);
            }
            // SeaWeeds can split
            else if (s.getLifePoints() >= 10) {
                // we set the HP of the seaweed to half of its current value
                int newHP = s.split();
                // we add a new seaweed with half of the HP of the old one
                newSeaWeed.add(new SeaWeed(newHP));
            }
        }
        // we get rid of the dead seaWeeds and we add the new ones
        eatenSeaWeed.forEach(seaWeedSet::remove);
        seaWeedSet.addAll(newSeaWeed);
        eatenSeaWeed.clear();
        // we get rid of the dead fish
        eatenFish.forEach(fishSet::remove);
        eatenFish.clear();

        //
        for (Fish fish : fishSet) {
            // if the fish needs to eat
            if (fish.getLifePoints() <= 5) {
                LivingCreature food = fish.isCarnivorous() ? selectRandomFish() : selectRandomSeaWeed();
                if (!fish.equals(food) || !eatenFish.contains(food) || eatenSeaWeed.contains(food)) {
                    fish.eat(food);
                    if (food.getLifePoints() <= 0) {
                        if (food instanceof SeaWeed) eatenSeaWeed.add((SeaWeed) food);
                        if (food instanceof Fish) eatenFish.add((Fish) food);
                    }
                }
            }
            // else it can reproduce
            else {
                switch (fish.getSpecies()) {
                    case "Carpe" -> carpeLove.add(fish);
                    case "Bar" -> barLove.add(fish);
                    case "Sole" -> soleLove.add(fish);
                    case "Thon" -> thonLove.add(fish);
                    case "Mérou" -> merouLove.add(fish);
                    case "Poisson-clown" -> pcLove.add(fish);
                }
            }
        }

        Graph<Fish, DefaultEdge> g = new DefaultDirectedGraph<Fish, DefaultEdge>(DefaultEdge.class);
        // updating vertices
        for (Fish fish : carpeLove) g.addVertex(fish);
        for (Fish fish : barLove) g.addVertex(fish);
        for (Fish fish : soleLove) g.addVertex(fish);
        for (Fish fish : thonLove) g.addVertex(fish);
        for (Fish fish : merouLove) g.addVertex(fish);
        for (Fish fish : pcLove) g.addVertex(fish);

        // updating edges
        updateEdges(carpeLove, g);
        updateEdges(barLove, g);
        updateEdges(soleLove, g);
        updateEdges(thonLove, g);
        updateEdges(merouLove, g);
        updateEdges(pcLove, g);

        // TODO : les enchères pour la reproduction (les poisons concernés sont stockés dans loveFish)
        int counter = 0;
        makeLove(carpeLove, g, counter);
        makeLove(barLove, g, counter);
        makeLove(soleLove, g, counter);
        makeLove(thonLove, g, counter);
        makeLove(merouLove, g, counter);
        makeLove(pcLove, g, counter);
        //System.out.println(counter);

        // visualization of the graph
        File filename = new File("output/dotFiles/round" + getRound() + ".dot");
        FileWriter outputStream = new FileWriter(filename);
        DOTExporter<Fish, DefaultEdge> exporter =
                new DOTExporter<>();
        Writer writer = new StringWriter();
        ComponentNameProvider<Fish> vComponentNameProvider = v -> v.toString();
        exporter.setVertexIDProvider(vComponentNameProvider);
        exporter.exportGraph(g, writer);
        outputStream.write(writer.toString());
        //System.out.println(writer);
        outputStream.close();


        // updating the aquarium's population
        eatenFish.forEach(fishSet::remove);
        eatenSeaWeed.forEach(seaWeedSet::remove);
        fishSet.addAll(newFish);
    }
}
