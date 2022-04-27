package simulation.agents;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Fish.
 */
public class Fish extends LivingCreature{

    /**
     * The Known species.
     */
    static final String[] KNOWN_SPECIES = {"Carpe", "Bar", "Sole", "Thon", "Mérou", "Poisson-clown"};
    /**
     * The Species regimes.
     */
    static final boolean[] SPECIES_REGIMES = {true, false, false, true, true, false};
    /**
     * The Male names.
     */
    static final String[] MALE_NAMES = {
            "Timothée",
            "Iven",
            "Yves",
            "Didier",
            "Christophe",
            "Neville",
            "Felicien",
            "Charles",
            "Xavier",
            "Arthur",
            "Alain",
            "Jérôme",
            "Gaspard",
            "Guillaume",
            "Nathan",
            "Marcelin",
            "Marco",
            "Thiery",
            "Aleron",
            "Jules",
            "Ray",
            "Laurent",
            "Stéphane",
            "Richard",
            "Tristan",
            "Leopold",
            "David",
            "Olivier",
            "Benjamin",
            "Nemo",
            "Bellamy",
            "Raymond",
            "Gabriel",
            "Léo",
            "Raphaël",
            "Louis",
            "Lucas",
            "Adam",
            "Hugo",
            "Noah",
            "Paul",
            "Ethan",
            "Sacha",
            "Gabin",
            "Aaron"
    };
    /**
     * The Female names.
     */
    static final String[] FEMALE_NAMES = {
            "Emma",
            "Jade",
            "Louise",
            "Alice",
            "Lina",
            "Chloë",
            "Rose",
            "Léa",
            "Mila",
            "Ambre",
            "Mia",
            "Anna",
            "Julia",
            "Inès",
            "Léna",
            "Juliette",
            "Zoé",
            "Manon",
            "Agathe",
            "Lou",
            "Véronique",
            "Caroline",
            "Delphine",
            "Camille",
            "Hélène",
            "Marie",
            "Ange",
            "Charlotte",
            "Aude",
            "Jeanne",
            "Julie",
            "Juliette",
            "Laure",
            "Aurélie",
            "Mathilde",
            "Valérie",
            "Pauline",
            "Iris",
            "Sophie",
            "Ghita",
            "Lamia",
            "Soukaina",
            "Lalie"
    };

    private final ArrayList<Fish> parents;
    private final String name;
    private final String species;
    private boolean sex;
    private final boolean isCarnivorous;
    private final Random random;

    /**
     * Instantiates a new Fish.
     *
     * @param species the species
     * @param sex     the sex
     */
    public Fish(int species, boolean sex) {
        super();
        this.parents = null;
        this.random = new Random();
        this.sex = sex;
        this.name = (sex) ? MALE_NAMES[random.nextInt(MALE_NAMES.length)] : FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
        this.species = KNOWN_SPECIES[species];
        this.isCarnivorous = SPECIES_REGIMES[species];
    }

    public Fish(Fish parent1, Fish parent2) {
        super();
        this.parents = new ArrayList<>();
        parents.add(parent1);
        parents.add(parent2);
        this.random = new Random();
        this.sex = random.nextInt() == 1;
        this.name = (this.sex) ? MALE_NAMES[random.nextInt(MALE_NAMES.length)] : FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
        this.species = parent1.getSpecies();
        this.isCarnivorous = parent1.isCarnivorous;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets species.
     *
     * @return the species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Is male boolean.
     *
     * @return the boolean
     */
    public boolean isMale() {
        return sex;
    }

    /**
     * Is carnivorous boolean.
     *
     * @return the boolean
     */
    public boolean isCarnivorous() {
        return isCarnivorous;
    }

    /**
     * Eat.
     *
     * @param livingCreature the living creature
     */
    public void eat(LivingCreature livingCreature) {
        livingCreature.getsEaten();
        int bonus = (isCarnivorous) ? 5 : 3;
        this.addLifePoints(bonus);
    }

    public boolean canMateWith(Fish fish) {
        if (fish.getSpecies().equals(this.getSpecies())) {
            if ((this.getSpecies().equals(KNOWN_SPECIES[2]) || this.getSpecies().equals(KNOWN_SPECIES[5])) && fish.isMale() == this.isMale()) {
                this.sex = !this.isMale();
            }
            return fish.isMale() == !this.isMale();
        }
        return false;
    }

    public double getBet() {
        return (random.nextGaussian() + this.getLifePoints()) * this.getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Fish) {
            return id.equals(((Fish) o).id);
        }
        return false;
    }

    @Override
    public void spendTime() {
        this.addLifePoints(-1);
        this.advanceAge();
        // poisson hermaphrodite avec l'âge
        if ((this.getSpecies().equals(KNOWN_SPECIES[1]) || this.getSpecies().equals(KNOWN_SPECIES[4])) && this.getAge() == 10) {
            this.sex = !this.isMale();
        }
    }

    @Override
    public void getsEaten() {
        this.addLifePoints(-4);
    }

    @Override
    public String toString() {
        return name.replaceAll("é", "e")
                .replaceAll("è", "e")
                .replaceAll("ë", "e")
                .replaceAll("ê", "e")
                .replaceAll("ô", "o");
    }
}
