package net.tabby.florafaunarebalance.item;

import ca.weblite.objc.Proxy;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;
import net.tabby.florafaunarebalance.Registry.pre.PreInitialisation;
import net.tabby.florafaunarebalance.block.FFRib;
import net.tabby.florafaunarebalance.block.fluid.FFRif;
import net.tabby.florafaunarebalance.entity.FFRenty;
import net.tabby.florafaunarebalance.item.unique.ChuteItem;
import net.tabby.florafaunarebalance.item.unique.DartItem;
import net.tabby.florafaunarebalance.Registry.FFRtt;
import net.tabby.florafaunarebalance.item.unique.SlingItem;
import net.tabby.florafaunarebalance.item.unique.tool.unique.ChiselItem;
import net.tabby.florafaunarebalance.util.FFR.FFRItemProperties;

import java.util.List;

public class FFRii {
    public static final int TPS = 20;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FloraFaunaRebalance.MOD_ID);




    public static final RegistryObject<Item> ACEQUIA_AQUA_BUCKET = ITEMS.register("acequia_aqua_bucket",
            () -> new BucketItem(FFRif.ACEQUIA_AQUA_SOURCE, new Item.Properties().stacksTo(1)
                    .tab(FFRCreativeTab.FFR_NATURE)));


    public static final RegistryObject<Item> LEATHER_SLING = ITEMS.register("leather_sling",
            () -> new SlingItem(new Item.Properties().tab(FFRCreativeTab.FFR_TOOLS)));

    public static final RegistryObject<Item> TANZANITE = PreInitialisation.TANZANITE; //due to cyclic dependency..
    public static final RegistryObject<Item> MYTHRIL_INGOT = PreInitialisation.MYTHRIL_INGOT; //same as ^^
// terraria star item and mana system???????? mayb? :3

    public static final RegistryObject<Item> COPPER_CHISEL = ITEMS.register("copper_chisel",
            () -> new ChiselItem(FFRtt.COPPER, 4.5f, 1.3f, new Item.Properties().tab(FFRCreativeTab.FFR_TOOLS)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(FFRtt.COPPER, 6f, -3.05f, new Item.Properties().tab(FFRCreativeTab.FFR_TOOLS))); //# default speed of 4...
// new Axel [idk maybe redstone related & minecart related?????].

    public static final RegistryObject<Item> CARVED_HANDLE = ITEMS.register("carved_handle",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TOOLS)));
    public static final RegistryObject<Item> PLUG = ITEMS.register("plug",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TOOLS)));
    public static final RegistryObject<Item> COPPER_BLADE = ITEMS.register("copper_blade",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TOOLS)));

    public static final RegistryObject<Item> PYRITE = ITEMS.register("pyrite",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_MINERALS)));
    public static final RegistryObject<Item> VALYRIAN_STEEL = ITEMS.register("valyrian_steel",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));


    public static final RegistryObject<Item> DUCK_SPAWN_EGG = ITEMS.register("duck_spawn_egg",
            () -> new ForgeSpawnEggItem(FFRenty.DUCK, 0x856B5B, 0x70df92, new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));
    public static final RegistryObject<Item> SKEETER_SPAWN_EGG = ITEMS.register("skeeter_spawn_egg",
            () -> new ForgeSpawnEggItem(FFRenty.WATER_SKEETER, 0x5D3FD3, 0xCF9FFF, new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));
    public static final RegistryObject<Item> NYMPHAEACEAE = ITEMS.register("nymphaeaceae",
            () -> new PlaceOnWaterBlockItem(FFRib.NYMPHAEACEAE.get(), new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));

    public static final RegistryObject<Item> DART_CHUTE = ITEMS.register("dart_chute",
            () -> new ChuteItem(new Item.Properties().tab(FFRCreativeTab.FFR_TAB).durability(43)));


    public static final RegistryObject<Item> DART = ITEMS.register("untipped_dart",
            () -> new DartItem(new Item.Properties().tab(FFRCreativeTab.FFR_TAB),
                    1.65f, null));
    public static final RegistryObject<Item> POISON_DART = ITEMS.register("poison_dart",
            () -> new DartItem(new Item.Properties().tab(FFRCreativeTab.FFR_TAB),
                    0.85f, List.of(new MobEffectInstance(MobEffects.POISON, 9 * TPS, 1), new MobEffectInstance(MobEffects.WEAKNESS, 27 * TPS, 0))));
    public static final RegistryObject<Item> DART_OF_HEALING = ITEMS.register("dart_of_healing",
            () -> new DartItem(new Item.Properties().tab(FFRCreativeTab.FFR_TAB),
                    0.35f, List.of(new MobEffectInstance(MobEffects.HEAL, 1, 0), (new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 5 * TPS, 0)))));




    //# TODO: fire/ash powder to ignite fireworks in chute...
    public static final RegistryObject<Item> FIRE_ASH_POWDER = ITEMS.register("fire_ash_powder",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));

    public static final RegistryObject<Item> TAPERED_FLINT = ITEMS.register("tapered_flint",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB))); //# rock + flint makes it sharp...
    //# lashing is when a sharp object is attached to a stick.



    public static final RegistryObject<Item> BARK_SCRAP = ITEMS.register("bark_scrap",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));

    public static final RegistryObject<Item> TWIG = ITEMS.register("twig",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));
    public static final RegistryObject<Item> POINTED_STICK = ITEMS.register("pointed_stick",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB))); //# marshmellows made with sugar, and boiled leather [gelatin]


    
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties().tab(FFRCreativeTab.FFR_TAB)));


    //galvanised steel x3 instead of regular steel or something?...


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
