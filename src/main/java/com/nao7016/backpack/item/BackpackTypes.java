package com.nao7016.backpack.item;

public enum BackpackTypes {

    basic(1, 27, 9, "basic_backpack"),
    iron(2, 45, 9, "iron_backpack"),
    gold(3, 63, 9, "gold_backpack"),
    diamond(4, 77, 11, "diamond_backpack");

    private int tier;
    private int size;
    private int cols;
    private String name;

    BackpackTypes(int tier, int size, int cols, String name) {
        this.tier = tier;
        this.size = size;
        this.cols = cols;
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public int getSize() {
        return size;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return size / cols;
    }

    public String getName() {
        return name;
    }

}
