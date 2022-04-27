package simulation.agents;

import java.util.UUID;

/**
 * The type Living creature.
 */
public abstract class LivingCreature {

    /**
     * The Id.
     */
    protected final UUID id;
    private int lifePoints = 10;
    private int age = 0;

    /**
     * Instantiates a new Living creature.
     */
    protected LivingCreature() {
        this.id = UUID.randomUUID();
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Advance age.
     */
    public void advanceAge() {
        this.age++;
    }

    /**
     * Gets life points.
     *
     * @return the life points
     */
    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Add life points.
     *
     * @param lifePoints the life points
     */
    public void addLifePoints(int lifePoints) {
        this.lifePoints = this.lifePoints + lifePoints;
    }

    /**
     * Spend time.
     */
    public abstract void spendTime();

    /**
     * Gets eaten.
     */
    public abstract void getsEaten();

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
