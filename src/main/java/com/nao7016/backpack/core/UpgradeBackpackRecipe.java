// package com.nao7016.backpack.core;
//
// import net.minecraft.inventory.InventoryCrafting;
// import net.minecraft.item.ItemStack;
// import net.minecraft.item.crafting.IRecipe;
// import net.minecraft.world.World;
//
// public class UpgradeBackpackRecipe implements IRecipe {
//
// @Override
// public boolean matches(InventoryCrafting inv, World world) {
// boolean foundBackpack = false;
//
// for (int i = 0; i < inv.getSizeInventory(); i++) {
// if (inv.getStackInSlot(i) != null) {
// if (inv.getStackInSlot(i)
// .getItem() == BPItems.basicBackpack) {
// if (foundBackpack) {
// return false; // More than one backpack found
// }
// foundBackpack = true;
// } else {
// return false; // Invalid item found
// }
// }
// }
// }
//
// @Override
// public ItemStack getCraftingResult(InventoryCrafting inv) {
// ItemStack oldBackpack = null;
//
// for (int i = 0; i < inv.getSizeInventory(); i++) {
// ItemStack stack = inv.getStackInSlot(i);
// if (stack != null && stack.getItem() == BPItems.basicBackpack) {
// oldBackpack = stack;
// break;
// }
// }
// if (oldBackpack != null) {
//
// }
// }
//
// @Override
// public int getRecipeSize() {
// return 0;
// }
//
// @Override
// public ItemStack getRecipeOutput() {
// return null;
// }
// }
