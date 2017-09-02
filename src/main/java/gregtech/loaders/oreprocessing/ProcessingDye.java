package gregtech.loaders.oreprocessing;

import gregtech.api.enums.Dyes;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IOreRecipeRegistrator;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.MatUnifier;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.Locale;

public class ProcessingDye implements IOreRecipeRegistrator {
    public ProcessingDye() {
        OrePrefixes.dye.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        Dyes aDye = Dyes.get(aOreDictName);
        if ((aDye.mIndex >= 0) && (aDye.mIndex < 16) && (GT_Utility.getContainerItem(aStack, true) == null)) {
            GT_ModHandler.addAlloySmelterRecipe(MatUnifier.get(OrePrefixes.dust, Materials.Glass, 8), aStack, new ItemStack(Blocks.stained_glass, 8, 15 - aDye.mIndex), 200, 8, false);
            GT_ModHandler.addAlloySmelterRecipe(new ItemStack(Blocks.glass, 8, 32767), aStack, new ItemStack(Blocks.stained_glass, 8, 15 - aDye.mIndex), 200, 8, false);
            GT_Values.RA.addMixerRecipe(aStack, null, null, null, Materials.Water.getFluid(216), FluidRegistry.getFluidStack("dye.watermixed." + aDye.name().toLowerCase(Locale.ENGLISH), 192), null, 16, 4);
            GT_Values.RA.addMixerRecipe(aStack, null, null, null, GT_ModHandler.getDistilledWater(288), FluidRegistry.getFluidStack("dye.watermixed." + aDye.name().toLowerCase(Locale.ENGLISH), 216), null, 16, 4);
            GT_Values.RA.addChemicalRecipe(aStack, MatUnifier.get(OrePrefixes.dust, Materials.Salt, 2), Materials.SulfuricAcid.getFluid(432), FluidRegistry.getFluidStack("dye.chemical." + aDye.name().toLowerCase(Locale.ENGLISH), 288), GT_Values.NI, 600, 48);
        }
    }
}
