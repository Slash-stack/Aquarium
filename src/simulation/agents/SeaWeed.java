package simulation.agents;

/**
 * The type Sea weed.
 */
public class SeaWeed extends LivingCreature {


    /**
     * Instantiates a new Sea weed.
     */
    public SeaWeed() {
        super();
    }

    /**
     * Instantiates a new Sea weed.
     *
     * @param newHP the new hp
     */
    public SeaWeed(int newHP) {
        super();
        this.addLifePoints(-10 + newHP);
    }

    /**
     * Splits the seaWeed into two by setting the current seaWeed's HP to half of its value and
     * returning the HP of the new one.
     *
     * @return the life points of the new seaWeed
     */
    public int split(){
        int newHP = this.getLifePoints()/2;
        this.addLifePoints(-newHP);
        return newHP;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SeaWeed) {
            return id.equals(((SeaWeed) o).id);
        }
        return false;
    }

    @Override
    public void spendTime() {
        this.addLifePoints(1);
        this.advanceAge();
    }

    @Override
    public void getsEaten() {
        this.addLifePoints(-2);
    }
}
