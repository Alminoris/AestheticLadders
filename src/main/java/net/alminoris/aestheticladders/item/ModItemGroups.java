package net.alminoris.aestheticladders.item;

import net.alminoris.aestheticladders.AestheticLadders;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModItemGroups
{
    public static List<String> WF_WOOD_NAMES = List.of("olive", "tamarisk", "western_serviceberry", "trembling_aspen", "cottonwood");

    public static List<String> AN_WOOD_NAMES = List.of("hazelnut", "hornbeam", "hawthorn", "quince", "plum", "mango", "fig", "viburnum",
            "white_mulberry", "wild_cherry", "bauhinia", "pine", "fir", "cedar", "araucaria", "juniper",
            "bald_cypress", "thuja", "sequoia", "mountain_hemlock", "cryptomeria", "yew", "larch");

    public static List<String> EXTRA_STONES_WF = List.of("dolomite_block", "saltmarsh_block", "loessic_marl_block", "loamy_marl_block", "fossil_marlstone_block", "limestone_block");

    public static ItemGroup ALADRS_TAB = FabricItemGroupBuilder.build(new Identifier(AestheticLadders.MOD_ID, "aladrstab"),
            () -> new ItemStack(Blocks.LADDER));

    public static void registerModItemGroups()
    {
        WF_WOOD_NAMES = new ArrayList<>();

        AN_WOOD_NAMES = new ArrayList<>();

        EXTRA_STONES_WF = new ArrayList<>();

        if (FabricLoader.getInstance().isModLoaded("arborealnature"))
        {
            AN_WOOD_NAMES = List.of("hazelnut", "hornbeam", "hawthorn", "quince", "plum", "mango", "fig", "viburnum",
                    "white_mulberry", "wild_cherry", "bauhinia", "pine", "fir", "cedar", "araucaria", "juniper",
                    "bald_cypress", "thuja", "sequoia", "mountain_hemlock", "cryptomeria", "yew", "larch");
        }
        if (FabricLoader.getInstance().isModLoaded("wildfields"))
        {
            WF_WOOD_NAMES = List.of("olive", "tamarisk", "western_serviceberry", "trembling_aspen", "cottonwood");
            EXTRA_STONES_WF = List.of("dolomite_block", "saltmarsh_block", "loessic_marl_block", "loamy_marl_block", "fossil_marlstone_block", "limestone_block");
        }
    }
}