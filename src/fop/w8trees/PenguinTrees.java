package fop.w8trees;

import java.util.Comparator;

public class PenguinTrees {
    public static Tree<Penguin> getAgeTree() {
        return new Tree<Penguin>(new Comparator<Penguin>(){
            @Override
            public int compare(Penguin p1, Penguin p2) {
                return p1.getAge()-p2.getAge();
            }
        });
    }

    public static Tree<Penguin> getHeightTree() {
        return new Tree<Penguin>(new Comparator<Penguin>(){
            @Override
            public int compare(Penguin p1, Penguin p2) {
                return (int) Math.signum(p2.getHeight()-p1.getHeight());
            }
        });
    }

    public static int getMinorsAmount(Tree<Penguin> tree) {
        return tree.countMatches(p -> p.getAge()<5);
    }

    public static Object[] getEvenNames(Tree<Penguin> tree) {
        return tree.getAll(p -> p.getName().length() % 2 == 0);
    }
}
