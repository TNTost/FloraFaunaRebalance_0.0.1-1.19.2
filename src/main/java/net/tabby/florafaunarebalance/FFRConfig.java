package net.tabby.florafaunarebalance;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = FloraFaunaRebalance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FFRConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.BooleanValue USE_AUTHENTIC_RUBBER_WOOD = BUILDER.comment("Whether to use GregTechCEu-Modern rubber log or let FFR Register one")
            .define("useAuthenticRubber", false); //config name + default
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty(Collections.singletonList("items"), () -> List.of("minecraft:iron_ingot"), FFRConfig::validateItemName);



    public static final ForgeConfigSpec SPEC = BUILDER.build();
    public static boolean useAuthenticRubber;
    public static Set<Item> items;


    @SubscribeEvent
    public void onLoad(final ModConfigEvent event)
    {
        useAuthenticRubber = USE_AUTHENTIC_RUBBER_WOOD.get();

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());
    }

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }
}
