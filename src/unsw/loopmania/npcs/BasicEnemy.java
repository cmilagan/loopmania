package unsw.loopmania.npcs;

import java.lang.management.ThreadInfo;
import java.util.List;

import org.javatuples.Pair;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemy extends MovingEntity {
    private int damage;
    private int health;
    private int experience;
    private int battleRadius;
    private int supportRadius;

    /**
     * Spawn an Enemy at position, with specified damage, health, experience, battleRadius, supportRadius
     * @param position
     * @param damage
     * @param health
     * @param experience
     * @param battleRadius
     * @param supportRadius
     */
    public BasicEnemy(PathPosition position, int damage, int health, int experience, int battleRadius, int supportRadius) {
        super(position);
        this.damage = damage;
        this.health = health;
        this.experience = experience;
        this.battleRadius = battleRadius;
        this.supportRadius = supportRadius;
    }

    /**
     * move the enemy
     */
    public void move() {}

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getExperience() {
        return experience;
    }

    public int getBattleRadius() {
        return battleRadius;
    }

    public int getSupportRadius() {
        return supportRadius;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public int applyCharacterDamage(Character character, List<AlliedSoldier> alliedSoldiers) {
        int damageDealt = character.getDamage();
        
         for (AlliedSoldier s: alliedSoldiers) {
            damageDealt += s.getDamage();
        }

        int leftHealth = Math.max(0, this.getHealth() - damageDealt);
        this.setHealth(leftHealth);
        return leftHealth;
    }

    public void applyBuildingDamage(int dmg) {
        setHealth(getHealth() - dmg);
    }

    public boolean rollCrit() {
        return false;
    }

    public void applyEnemyEffects(Character character, LoopManiaWorld world, List<Pair<Integer, Integer>> orderedPath) {
        /**
         * if character has a Staff, can apply trance if chance permits
         * 
         * Note: strategy pattern used here
         */
        if (character.inflictStaffTrance()) {
            /**
             * before we switch this enemy with an allied soldier,
             * 
             * we must first store this enemy into the allied soldier class
             * so that after the duration of trance ends, the allied soldier can
             * turn back into the original enemy it was
             * 
             * Note: allied soldier will spawn at the exact spot the zombie was there
             */
            AlliedSoldier transformedSoldier = new AlliedSoldier(new PathPosition(this.getCurrentPositionIndex(), orderedPath));

            /**
             * Note that we do the unbinding because if we dont, the enemies position 
             * remains tracked and hence the enemy appears stationary, despite of death.
             */
            this.x().unbind();
            this.y().unbind();
            transformedSoldier.setOriginalEnemy(this);

            world.getBattleEnemies().remove(this);
            world.addAlliedSoldier(transformedSoldier);
            world.killEnemy(this);
        }
    }
}
