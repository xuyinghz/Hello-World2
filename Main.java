package com.joust.codalot;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    private Main() { }

    public static void main(String[] args) {
        Codalot codalot = new Codalot();
        int n;
        n=(args.length > 0)?Integer.parseInt(args[0]):12;
        //if (args.length > 0) {
        //    n = Integer.parseInt(args[0]);
        //}
        //else{
        //    n = 12;
        //}
        ArrayList<Knight> knights = new ArrayList<Knight>();
        for (int i = 0; i < n; ++i) {
            knights.add(new Knight());
        }

        Random random = new Random(1);
        for (int i = 0; i < 24; ++i) {
            codalot.clearKnights();
            for (Knight knight : knights) {
                int randomVal = random.nextInt(2);
                if (randomVal == 0) {
                    codalot.addKnightToTrainingYard(knight);
                } else if (randomVal == 1) {
                    codalot.addKnightToTavern(knight);
                }
            }
            codalot.process();
        }
        codalot.grantBonusXp();

        int totalXp = 0;
        for (Knight knight : knights) {
            totalXp += knight.getXp();
        }
        System.out.println(String.format("Total XP earned by all %d knights: %d", knights.size(), totalXp));
    }

    public static class Knight {
        private int xp;
        private int stamina;
        private boolean isInTavern;
        private boolean isInTrainingYard;

        public Knight() {
            xp = 0;
            stamina = 0;
        }

        public int getXp() {
            return xp;
        }

        public void setXp(int xp) {
            this.xp = xp;
        }

        public void incrementXp(int xp) {
            this.xp += xp;
        }

        public int getStamina() {
            return stamina;
        }

        public void setStamina(int stamina) {
            this.stamina = stamina;
        }

        public void incrementStamina(int stamina) {
            this.stamina += stamina;
        }

        public boolean isInTavern() {
            return isInTavern;
        }

        public void setInTavern(boolean isInTavern) {
            this.isInTavern = isInTavern;
        }

        public boolean isInTrainingYard() {
            return isInTrainingYard;
        }

        public void setInTrainingYard(boolean isInTrainingYard) {
            this.isInTrainingYard = isInTrainingYard;
        }
    }

    public static class Codalot {
        private ArrayList<Knight> knights;

        public Codalot() {
            knights = new ArrayList<Knight>();
        }

        public void clearKnights() {
            knights.clear();
        }

        public void addKnightToTrainingYard(Knight knight) {
            knights.add(knight);
            knight.setInTrainingYard(true);
            knight.setInTavern(false);
        }

        public void addKnightToTavern(Knight knight) {
            knights.add(knight);
            knight.setInTavern(true);
            knight.setInTrainingYard(false);
        }

        public void process() {
            for (Knight knight : knights) {
                knight.incrementStamina(knight.isInTavern ? 1 : -1);
                knight.incrementXp(knight.isInTrainingYard ? 1 : 0);
            }
        }

        public void grantBonusXp() {
            int bonusKnights = 0;
            for (Knight knight : knights) {
                if (knight.getXp() >= 3) {
                    bonusKnights++;
                }
            }
            if (bonusKnights == 3) {
                for (Knight knight : knights) {
                    if (knight.getXp() >= 3) {
                        knight.setXp(knight.getXp() + 5);
                    }
                }
            }
            if (bonusKnights == 5) {
                for (Knight knight : knights) {
                    if (knight.getXp() >= 3) {
                        knight.setXp(knight.getXp() + 10);
                    }
                }
            }
            if (bonusKnights == 6) {
                for (Knight knight : knights) {
                    if (knight.getXp() >= 3) {
                        knight.setXp(knight.getXp() + 20);
                    }
                }
            }
        }
    }
}
