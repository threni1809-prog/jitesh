package com.example.model

object RecipeDatabase {

    val RECIPES: List<CraftingRecipe> = listOf(
        // ================= TOOLS =================
        CraftingRecipe(
            id = "diamond_pickaxe",
            name = "Diamond Pickaxe",
            category = RecipeCategory.TOOLS,
            description = "High tier mining tool capable of breaking obsidian, ancient debris, and all ores.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "diamond", "diamond", "diamond",
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 3),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Mining Speed: 8x | Durability: 1561"
        ),
        CraftingRecipe(
            id = "iron_pickaxe",
            name = "Iron Pickaxe",
            category = RecipeCategory.TOOLS,
            description = "Reliable mining tool required to mine Gold, Diamond, Redstone, and Emerald ores.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", "iron_ingot", "iron_ingot",
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 3),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Mining Speed: 6x | Durability: 250"
        ),
        CraftingRecipe(
            id = "stone_pickaxe",
            name = "Stone Pickaxe",
            category = RecipeCategory.TOOLS,
            description = "Early game mining tool required to mine Iron ore and Lapis Lazuli.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "cobblestone", "cobblestone", "cobblestone",
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 3),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Mining Speed: 4x | Durability: 131"
        ),
        CraftingRecipe(
            id = "wooden_pickaxe",
            name = "Wooden Pickaxe",
            category = RecipeCategory.TOOLS,
            description = "The first pickaxe crafted in survival, used to mine stone and coal.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "oak_planks", "oak_planks", "oak_planks",
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 3),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Mining Speed: 2x | Durability: 59"
        ),
        CraftingRecipe(
            id = "diamond_axe",
            name = "Diamond Axe",
            category = RecipeCategory.TOOLS,
            description = "Rapid wood chopping tool that also inflicts devastating melee combat damage.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "diamond", "diamond", null,
                "diamond", "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 3),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Attack Damage: 9 | Durability: 1561"
        ),
        CraftingRecipe(
            id = "iron_axe",
            name = "Iron Axe",
            category = RecipeCategory.TOOLS,
            description = "Cuts logs, wood blocks, and shields with high attack strength.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", "iron_ingot", null,
                "iron_ingot", "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 3),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Attack Damage: 9 | Durability: 250"
        ),
        CraftingRecipe(
            id = "diamond_shovel",
            name = "Diamond Shovel",
            category = RecipeCategory.TOOLS,
            description = "Instantly clears dirt, sand, gravel, clay, and snow layers.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, "diamond", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 1),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Dig Speed: Fast | Durability: 1561"
        ),
        CraftingRecipe(
            id = "iron_shovel",
            name = "Iron Shovel",
            category = RecipeCategory.TOOLS,
            description = "Standard digging tool for landscape terraforming and path creation.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, "iron_ingot", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 1),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Dig Speed: Medium | Durability: 250"
        ),
        CraftingRecipe(
            id = "diamond_hoe",
            name = "Diamond Hoe",
            category = RecipeCategory.TOOLS,
            description = "Tills dirt and grass into farmland, and rapidly clears leaves and sculk.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "diamond", "diamond", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 2),
                IngredientCount("stick", "Stick", 2)
            ),
            statInfo = "Durability: 1561"
        ),
        CraftingRecipe(
            id = "shears",
            name = "Shears",
            category = RecipeCategory.TOOLS,
            description = "Harvests wool from sheep without injury, and cuts leaves, cobwebs, and vines.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, "iron_ingot", null,
                "iron_ingot", null, null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 2)
            ),
            statInfo = "Durability: 238"
        ),
        CraftingRecipe(
            id = "flint_and_steel",
            name = "Flint and Steel",
            category = RecipeCategory.TOOLS,
            description = "Ignites fire, primes TNT blocks, and activates Nether Portals.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", null, null,
                null, "flint", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 1),
                IngredientCount("flint", "Flint", 1)
            ),
            statInfo = "Durability: 64 uses"
        ),
        CraftingRecipe(
            id = "fishing_rod",
            name = "Fishing Rod",
            category = RecipeCategory.TOOLS,
            description = "Catches fish, treasure, and junk from bodies of water.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, null, "stick",
                null, "stick", "string",
                "stick", null, "string"
            ),
            ingredients = listOf(
                IngredientCount("stick", "Stick", 3),
                IngredientCount("string", "String", 2)
            ),
            statInfo = "Durability: 64"
        ),
        CraftingRecipe(
            id = "bucket",
            name = "Bucket",
            category = RecipeCategory.TOOLS,
            description = "Holds water, lava, milk, powder snow, or aquatic creatures.",
            stackSize = 16,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", null, "iron_ingot",
                null, "iron_ingot", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 3)
            )
        ),

        // ================= COMBAT & ARMOR =================
        CraftingRecipe(
            id = "diamond_sword",
            name = "Diamond Sword",
            category = RecipeCategory.COMBAT,
            description = "Primary combat weapon with formidable slashing power and durability.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, "diamond", null,
                null, "diamond", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 2),
                IngredientCount("stick", "Stick", 1)
            ),
            statInfo = "Damage: +7 | Attack Speed: 1.6 | Durability: 1561"
        ),
        CraftingRecipe(
            id = "iron_sword",
            name = "Iron Sword",
            category = RecipeCategory.COMBAT,
            description = "Standard warrior weapon that handles most hostile mobs effectively.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, "iron_ingot", null,
                null, "iron_ingot", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 2),
                IngredientCount("stick", "Stick", 1)
            ),
            statInfo = "Damage: +6 | Attack Speed: 1.6 | Durability: 250"
        ),
        CraftingRecipe(
            id = "golden_sword",
            name = "Golden Sword",
            category = RecipeCategory.COMBAT,
            description = "Lustrous sword with exceptionally high enchantability but low durability.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, "gold_ingot", null,
                null, "gold_ingot", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 2),
                IngredientCount("stick", "Stick", 1)
            ),
            statInfo = "Damage: +4 | Attack Speed: 1.6 | Durability: 32"
        ),
        CraftingRecipe(
            id = "bow",
            name = "Bow",
            category = RecipeCategory.COMBAT,
            description = "Ranged weapon that fires arrows at distant foes.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, "stick", "string",
                "stick", null, "string",
                null, "stick", "string"
            ),
            ingredients = listOf(
                IngredientCount("stick", "Stick", 3),
                IngredientCount("string", "String", 3)
            ),
            statInfo = "Max Damage: 9-10 (Full draw) | Durability: 384"
        ),
        CraftingRecipe(
            id = "arrow",
            name = "Arrow",
            category = RecipeCategory.COMBAT,
            description = "Ammunition required for bows and dispensers.",
            stackSize = 64,
            outputCount = 4,
            grid = listOf(
                null, "flint", null,
                null, "stick", null,
                null, "feather", null
            ),
            ingredients = listOf(
                IngredientCount("flint", "Flint", 1),
                IngredientCount("stick", "Stick", 1),
                IngredientCount("feather", "Feather", 1)
            )
        ),
        CraftingRecipe(
            id = "shield",
            name = "Shield",
            category = RecipeCategory.COMBAT,
            description = "Equipped in off-hand to block incoming attacks, arrows, and explosions.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "oak_planks", "iron_ingot", "oak_planks",
                "oak_planks", "oak_planks", "oak_planks",
                null, "oak_planks", null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 6),
                IngredientCount("iron_ingot", "Iron Ingot", 1)
            ),
            statInfo = "Blocks 100% Melee/Ranged Damage | Durability: 336"
        ),
        CraftingRecipe(
            id = "diamond_helmet",
            name = "Diamond Helmet",
            category = RecipeCategory.COMBAT,
            description = "Head armor providing strong defense and armor toughness.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "diamond", "diamond", "diamond",
                "diamond", null, "diamond",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 5)
            ),
            statInfo = "Armor: +3 | Toughness: +2 | Durability: 363"
        ),
        CraftingRecipe(
            id = "diamond_chestplate",
            name = "Diamond Chestplate",
            category = RecipeCategory.COMBAT,
            description = "Heavy torso armor offering highest base damage absorption.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "diamond", null, "diamond",
                "diamond", "diamond", "diamond",
                "diamond", "diamond", "diamond"
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 8)
            ),
            statInfo = "Armor: +8 | Toughness: +2 | Durability: 528"
        ),
        CraftingRecipe(
            id = "diamond_leggings",
            name = "Diamond Leggings",
            category = RecipeCategory.COMBAT,
            description = "Leg armor covering the lower body in defensive diamond plating.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "diamond", "diamond", "diamond",
                "diamond", null, "diamond",
                "diamond", null, "diamond"
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 7)
            ),
            statInfo = "Armor: +6 | Toughness: +2 | Durability: 495"
        ),
        CraftingRecipe(
            id = "diamond_boots",
            name = "Diamond Boots",
            category = RecipeCategory.COMBAT,
            description = "Footwear offering armor protection and reducing fall damage with enchantments.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "diamond", null, "diamond",
                "diamond", null, "diamond",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 4)
            ),
            statInfo = "Armor: +3 | Toughness: +2 | Durability: 429"
        ),
        CraftingRecipe(
            id = "iron_helmet",
            name = "Iron Helmet",
            category = RecipeCategory.COMBAT,
            description = "Solid iron headgear guarding against critical strikes.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", "iron_ingot", "iron_ingot",
                "iron_ingot", null, "iron_ingot",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 5)
            ),
            statInfo = "Armor: +2 | Durability: 165"
        ),
        CraftingRecipe(
            id = "iron_chestplate",
            name = "Iron Chestplate",
            category = RecipeCategory.COMBAT,
            description = "The standard staple survival armor for cave exploration.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", "iron_ingot", "iron_ingot",
                "iron_ingot", "iron_ingot", "iron_ingot"
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 8)
            ),
            statInfo = "Armor: +6 | Durability: 240"
        ),

        // ================= BUILDING & ESSENTIALS =================
        CraftingRecipe(
            id = "crafting_table",
            name = "Crafting Table",
            category = RecipeCategory.BUILDING,
            description = "Expands the player's 2x2 crafting grid to a full 3x3 crafting matrix.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "oak_planks", "oak_planks", null,
                "oak_planks", "oak_planks", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 4)
            )
        ),
        CraftingRecipe(
            id = "furnace",
            name = "Furnace",
            category = RecipeCategory.BUILDING,
            description = "Smelts ores into ingots, cooks raw food, and refines stone blocks.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "cobblestone", "cobblestone", "cobblestone",
                "cobblestone", null, "cobblestone",
                "cobblestone", "cobblestone", "cobblestone"
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 8)
            )
        ),
        CraftingRecipe(
            id = "chest",
            name = "Chest",
            category = RecipeCategory.BUILDING,
            description = "Stores 27 slots of items. Place two side-by-side to make a Large Chest (54 slots).",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "oak_planks", "oak_planks", "oak_planks",
                "oak_planks", null, "oak_planks",
                "oak_planks", "oak_planks", "oak_planks"
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 8)
            )
        ),
        CraftingRecipe(
            id = "torch",
            name = "Torch",
            category = RecipeCategory.BUILDING,
            description = "Emits light level 14 to illuminate dark areas and prevent hostile mob spawning.",
            stackSize = 64,
            outputCount = 4,
            grid = listOf(
                null, "coal", null,
                null, "stick", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("coal", "Coal or Charcoal", 1),
                IngredientCount("stick", "Stick", 1)
            ),
            statInfo = "Light Level: 14"
        ),
        CraftingRecipe(
            id = "soul_torch",
            name = "Soul Torch",
            category = RecipeCategory.BUILDING,
            description = "Eerie blue flame that repels Piglins in the Nether.",
            stackSize = 64,
            outputCount = 4,
            grid = listOf(
                null, "coal", null,
                null, "stick", null,
                null, "sand", null
            ),
            ingredients = listOf(
                IngredientCount("coal", "Coal", 1),
                IngredientCount("stick", "Stick", 1),
                IngredientCount("sand", "Soul Sand/Soil", 1)
            ),
            statInfo = "Light Level: 10 | Repels Piglins"
        ),
        CraftingRecipe(
            id = "campfire",
            name = "Campfire",
            category = RecipeCategory.BUILDING,
            description = "Cooks up to 4 food items simultaneously without using fuel; sends smoke signals.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                null, "stick", null,
                "stick", "coal", "stick",
                "oak_planks", "oak_planks", "oak_planks"
            ),
            ingredients = listOf(
                IngredientCount("stick", "Stick", 3),
                IngredientCount("coal", "Coal", 1),
                IngredientCount("oak_planks", "Logs or Wood", 3)
            )
        ),
        CraftingRecipe(
            id = "bookshelf",
            name = "Bookshelf",
            category = RecipeCategory.BUILDING,
            description = "Placed around an Enchanting Table to boost available enchantment levels up to level 30.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "oak_planks", "oak_planks", "oak_planks",
                "book", "book", "book",
                "oak_planks", "oak_planks", "oak_planks"
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 6),
                IngredientCount("book", "Book", 3)
            )
        ),
        CraftingRecipe(
            id = "ladder",
            name = "Ladder",
            category = RecipeCategory.BUILDING,
            description = "Attached to vertical walls for ascending and descending mine shafts.",
            stackSize = 64,
            outputCount = 3,
            grid = listOf(
                "stick", null, "stick",
                "stick", "stick", "stick",
                "stick", null, "stick"
            ),
            ingredients = listOf(
                IngredientCount("stick", "Stick", 7)
            )
        ),
        CraftingRecipe(
            id = "oak_door",
            name = "Oak Door",
            category = RecipeCategory.BUILDING,
            description = "Provides secure entrance to buildings; blocks monster entry.",
            stackSize = 64,
            outputCount = 3,
            grid = listOf(
                "oak_planks", "oak_planks", null,
                "oak_planks", "oak_planks", null,
                "oak_planks", "oak_planks", null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 6)
            )
        ),
        CraftingRecipe(
            id = "bed",
            name = "Bed",
            category = RecipeCategory.BUILDING,
            description = "Allows skipping the night and resets the player spawn point.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                null, null, null,
                "feather", "feather", "feather",
                "oak_planks", "oak_planks", "oak_planks"
            ),
            ingredients = listOf(
                IngredientCount("feather", "Wool (Any Color)", 3),
                IngredientCount("oak_planks", "Oak Planks", 3)
            )
        ),
        CraftingRecipe(
            id = "anvil",
            name = "Anvil",
            category = RecipeCategory.BUILDING,
            description = "Repairs tools, renames items, and applies enchanted books.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", "iron_ingot", "iron_ingot",
                null, "iron_ingot", null,
                "iron_ingot", "iron_ingot", "iron_ingot"
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Blocks (x3) + Iron Ingots (x4)", 31)
            )
        ),

        // ================= REDSTONE & MECHANISMS =================
        CraftingRecipe(
            id = "redstone_torch",
            name = "Redstone Torch",
            category = RecipeCategory.REDSTONE,
            description = "Outputs continuous redstone power signal (power level 15) and inverts signals.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                null, "redstone", null,
                null, "stick", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("redstone", "Redstone Dust", 1),
                IngredientCount("stick", "Stick", 1)
            ),
            statInfo = "Power Output: 15"
        ),
        CraftingRecipe(
            id = "redstone_repeater",
            name = "Redstone Repeater",
            category = RecipeCategory.REDSTONE,
            description = "Boosts redstone signal to max strength (15) and delays transmission by 1-4 ticks.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "redstone_torch", "redstone", "redstone_torch",
                "stone", "stone", "stone",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("redstone_torch", "Redstone Torch", 2),
                IngredientCount("redstone", "Redstone Dust", 1),
                IngredientCount("stone", "Smooth Stone", 3)
            )
        ),
        CraftingRecipe(
            id = "redstone_comparator",
            name = "Redstone Comparator",
            category = RecipeCategory.REDSTONE,
            description = "Checks container fullness, maintains signal strengths, and performs signal subtraction.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                null, "redstone_torch", null,
                "redstone_torch", "quartz", "redstone_torch",
                "stone", "stone", "stone"
            ),
            ingredients = listOf(
                IngredientCount("redstone_torch", "Redstone Torch", 3),
                IngredientCount("quartz", "Nether Quartz", 1),
                IngredientCount("stone", "Smooth Stone", 3)
            )
        ),
        CraftingRecipe(
            id = "piston",
            name = "Piston",
            category = RecipeCategory.REDSTONE,
            description = "Pushes up to 12 blocks when activated by a redstone signal.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "oak_planks", "oak_planks", "oak_planks",
                "cobblestone", "iron_ingot", "cobblestone",
                "cobblestone", "redstone", "cobblestone"
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 3),
                IngredientCount("cobblestone", "Cobblestone", 4),
                IngredientCount("iron_ingot", "Iron Ingot", 1),
                IngredientCount("redstone", "Redstone Dust", 1)
            )
        ),
        CraftingRecipe(
            id = "sticky_piston",
            name = "Sticky Piston",
            category = RecipeCategory.REDSTONE,
            description = "Pushes and pulls blocks back when redstone signal toggles off.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                null, "slimeball", null,
                null, "piston", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("slimeball", "Slimeball", 1),
                IngredientCount("piston", "Piston", 1)
            )
        ),
        CraftingRecipe(
            id = "hopper",
            name = "Hopper",
            category = RecipeCategory.REDSTONE,
            description = "Transfers items into and out of containers automatically.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", "chest", "iron_ingot",
                null, "iron_ingot", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 5),
                IngredientCount("chest", "Chest", 1)
            )
        ),
        CraftingRecipe(
            id = "dispenser",
            name = "Dispenser",
            category = RecipeCategory.REDSTONE,
            description = "Shoots arrows, throws potions, places boats, and equips armor onto players.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "cobblestone", "cobblestone", "cobblestone",
                "cobblestone", "bow", "cobblestone",
                "cobblestone", "redstone", "cobblestone"
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 7),
                IngredientCount("bow", "Bow", 1),
                IngredientCount("redstone", "Redstone Dust", 1)
            )
        ),
        CraftingRecipe(
            id = "tnt",
            name = "TNT",
            category = RecipeCategory.REDSTONE,
            description = "Explosive block primed by fire, redstone pulse, or explosions.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "gunpowder", "sand", "gunpowder",
                "sand", "gunpowder", "sand",
                "gunpowder", "sand", "gunpowder"
            ),
            ingredients = listOf(
                IngredientCount("gunpowder", "Gunpowder", 5),
                IngredientCount("sand", "Sand", 4)
            )
        ),

        // ================= FOOD & AGRICULTURE =================
        CraftingRecipe(
            id = "bread",
            name = "Bread",
            category = RecipeCategory.FOOD,
            description = "Reliable staple food crafted easily from cultivated wheat.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                null, null, null,
                "wheat", "wheat", "wheat",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("wheat", "Wheat", 3)
            ),
            statInfo = "Hunger: 5 (2.5 drumsticks) | Saturation: 6.0"
        ),
        CraftingRecipe(
            id = "golden_apple",
            name = "Golden Apple",
            category = RecipeCategory.FOOD,
            description = "Nutritious magical fruit that grants Regeneration II and Absorption.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "gold_ingot", "gold_ingot", "gold_ingot",
                "gold_ingot", "apple", "gold_ingot",
                "gold_ingot", "gold_ingot", "gold_ingot"
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 8),
                IngredientCount("apple", "Apple", 1)
            ),
            statInfo = "Effects: Absorption (2 mins) + Regen II (5s)"
        ),
        CraftingRecipe(
            id = "golden_carrot",
            name = "Golden Carrot",
            category = RecipeCategory.FOOD,
            description = "Top tier food item offering the highest saturation points in the game.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "gold_ingot", "gold_ingot", "gold_ingot",
                "gold_ingot", "apple", "gold_ingot",
                "gold_ingot", "gold_ingot", "gold_ingot"
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Nuggets", 8),
                IngredientCount("apple", "Carrot", 1)
            ),
            statInfo = "Hunger: 6 | Saturation: 14.4 (Highest in game)"
        ),
        CraftingRecipe(
            id = "cake",
            name = "Cake",
            category = RecipeCategory.FOOD,
            description = "Can be placed on a surface and sliced up to 7 times to restore hunger.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "bucket", "bucket", "bucket",
                "sugar", "feather", "sugar",
                "wheat", "wheat", "wheat"
            ),
            ingredients = listOf(
                IngredientCount("bucket", "Milk Bucket", 3),
                IngredientCount("sugar", "Sugar", 2),
                IngredientCount("feather", "Egg", 1),
                IngredientCount("wheat", "Wheat", 3)
            ),
            statInfo = "Restores 14 hunger total (2 per slice)"
        ),
        CraftingRecipe(
            id = "cookie",
            name = "Cookie",
            category = RecipeCategory.FOOD,
            description = "Tasty snack baked with cocoa beans. Feeds in small quick bites.",
            stackSize = 64,
            outputCount = 8,
            grid = listOf(
                null, null, null,
                "wheat", "stick", "wheat",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("wheat", "Wheat", 2),
                IngredientCount("stick", "Cocoa Beans", 1)
            ),
            statInfo = "Hunger: 2 per cookie | Yield: 8 cookies"
        ),

        // ================= BREWING & MAGIC =================
        CraftingRecipe(
            id = "brewing_stand",
            name = "Brewing Stand",
            category = RecipeCategory.BREWING,
            description = "Essential workstation used to brew potions, splash potions, and lingering effects.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                null, "blaze", null,
                "cobblestone", "cobblestone", "cobblestone",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("blaze", "Blaze Rod", 1),
                IngredientCount("cobblestone", "Cobblestone", 3)
            )
        ),
        CraftingRecipe(
            id = "cauldron",
            name = "Cauldron",
            category = RecipeCategory.BREWING,
            description = "Holds water, lava, powder snow, or dyed water for leather armor coloring.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", "iron_ingot", "iron_ingot"
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 7)
            )
        ),
        CraftingRecipe(
            id = "glass_bottle",
            name = "Glass Bottle",
            category = RecipeCategory.BREWING,
            description = "Fills with water to form water bottles, the foundation of all brewing recipes.",
            stackSize = 64,
            outputCount = 3,
            grid = listOf(
                "glass", null, "glass",
                null, "glass", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("glass", "Glass Block", 3)
            )
        ),
        CraftingRecipe(
            id = "eye_of_ender",
            name = "Eye of Ender",
            category = RecipeCategory.BREWING,
            description = "Locates Strongholds and activates the End Portal frame.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                null, null, null,
                "pearl", "blaze", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("pearl", "Ender Pearl", 1),
                IngredientCount("blaze", "Blaze Powder", 1)
            )
        ),

        // ================= TRANSPORTATION =================
        CraftingRecipe(
            id = "minecart",
            name = "Minecart",
            category = RecipeCategory.TRANSPORT,
            description = "Vehicle that rides along rails to transport players, mobs, and items across long distances.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", "iron_ingot", "iron_ingot",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 5)
            )
        ),
        CraftingRecipe(
            id = "rail",
            name = "Rail",
            category = RecipeCategory.TRANSPORT,
            description = "Basic track for minecarts.",
            stackSize = 64,
            outputCount = 16,
            grid = listOf(
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", "stick", "iron_ingot",
                "iron_ingot", null, "iron_ingot"
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 6),
                IngredientCount("stick", "Stick", 1)
            )
        ),
        CraftingRecipe(
            id = "oak_boat",
            name = "Oak Boat",
            category = RecipeCategory.TRANSPORT,
            description = "Transports up to 2 passengers rapidly across water surfaces.",
            stackSize = 1,
            outputCount = 1,
            grid = listOf(
                "oak_planks", null, "oak_planks",
                "oak_planks", "oak_planks", "oak_planks",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 5)
            )
        ),

        // ================= BASE MATERIALS =================
        CraftingRecipe(
            id = "stick",
            name = "Stick",
            category = RecipeCategory.BUILDING,
            description = "Wooden rod used as the handle for every tool, weapon, torch, and ladder.",
            stackSize = 64,
            outputCount = 4,
            grid = listOf(
                null, "oak_planks", null,
                null, "oak_planks", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 2)
            )
        ),
        CraftingRecipe(
            id = "paper",
            name = "Paper",
            category = RecipeCategory.BUILDING,
            description = "Made from sugar cane; crafted into books, maps, and firework rockets.",
            stackSize = 64,
            outputCount = 3,
            grid = listOf(
                null, null, null,
                "stick", "stick", "stick",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("stick", "Sugar Cane", 3)
            )
        ),
        CraftingRecipe(
            id = "book",
            name = "Book",
            category = RecipeCategory.BUILDING,
            description = "Bound book used in bookshelf crafting and enchanting table creation.",
            stackSize = 64,
            outputCount = 1,
            grid = listOf(
                "paper", "paper", null,
                "paper", "leather", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("paper", "Paper", 3),
                IngredientCount("leather", "Leather", 1)
            )
        ),
// --- EXPANDED WEAPONS & COMBAT ---
        CraftingRecipe(
            id = "wooden_sword",
            name = "Wooden Sword",
            category = RecipeCategory.COMBAT,
            description = "Entry-level melee weapon crafted from oak planks and a stick.",
            stackSize = 1,
            statInfo = "Damage: 4.0 | Speed: 1.6 | Durability: 59",
            grid = listOf(
                null, "oak_planks", null,
                null, "oak_planks", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 2),
                IngredientCount("stick", "Stick", 1)
            )
        ),
        CraftingRecipe(
            id = "stone_sword",
            name = "Stone Sword",
            category = RecipeCategory.COMBAT,
            description = "Reliable early-game sword forged from cobblestone.",
            stackSize = 1,
            statInfo = "Damage: 5.0 | Speed: 1.6 | Durability: 131",
            grid = listOf(
                null, "cobblestone", null,
                null, "cobblestone", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 2),
                IngredientCount("stick", "Stick", 1)
            )
        ),
        CraftingRecipe(
            id = "crossbow",
            name = "Crossbow",
            category = RecipeCategory.COMBAT,
            description = "High-velocity projectile weapon with loading mechanics and enchantment support.",
            stackSize = 1,
            statInfo = "Damage: 6-11 | Velocity: High | Durability: 465",
            grid = listOf(
                "stick", "iron_ingot", "stick",
                "string", "tripwire_hook", "string",
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("stick", "Stick", 3),
                IngredientCount("iron_ingot", "Iron Ingot", 1),
                IngredientCount("string", "String", 2),
                IngredientCount("tripwire_hook", "Tripwire Hook", 1)
            )
        ),
        CraftingRecipe(
            id = "spectral_arrow",
            name = "Spectral Arrow",
            category = RecipeCategory.COMBAT,
            description = "Glowing arrow that outlines targets through walls when struck.",
            stackSize = 64,
            outputCount = 2,
            statInfo = "Gives Glowing Effect for 10 seconds",
            grid = listOf(
                null, "glowstone_dust", null,
                "glowstone_dust", "arrow", "glowstone_dust",
                null, "glowstone_dust", null
            ),
            ingredients = listOf(
                IngredientCount("glowstone_dust", "Glowstone Dust", 4),
                IngredientCount("arrow", "Arrow", 1)
            )
        ),

        // --- EXPANDED TOOLS ---
        CraftingRecipe(
            id = "wooden_axe",
            name = "Wooden Axe",
            category = RecipeCategory.TOOLS,
            description = "Basic woodworking tool for felling trees and chopping logs.",
            stackSize = 1,
            statInfo = "Damage: 7.0 | Speed: 0.8 | Durability: 59",
            grid = listOf(
                "oak_planks", "oak_planks", null,
                "oak_planks", "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 3),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "stone_axe",
            name = "Stone Axe",
            category = RecipeCategory.TOOLS,
            description = "Sturdy cobblestone axe offering high single-hit melee attack power.",
            stackSize = 1,
            statInfo = "Damage: 9.0 | Speed: 0.8 | Durability: 131",
            grid = listOf(
                "cobblestone", "cobblestone", null,
                "cobblestone", "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 3),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "golden_pickaxe",
            name = "Golden Pickaxe",
            category = RecipeCategory.TOOLS,
            description = "Ultra-fast mining pickaxe with very low durability.",
            stackSize = 1,
            statInfo = "Speed: 12.0 (Fastest) | Durability: 32",
            grid = listOf(
                "gold_ingot", "gold_ingot", "gold_ingot",
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 3),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "golden_axe",
            name = "Golden Axe",
            category = RecipeCategory.TOOLS,
            description = "Fastest chopping axe in the game, highly receptive to enchantments.",
            stackSize = 1,
            statInfo = "Speed: 12.0 | Durability: 32",
            grid = listOf(
                "gold_ingot", "gold_ingot", null,
                "gold_ingot", "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 3),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "golden_shovel",
            name = "Golden Shovel",
            category = RecipeCategory.TOOLS,
            description = "Lightning fast digging spade for excavation.",
            stackSize = 1,
            statInfo = "Speed: 12.0 | Durability: 32",
            grid = listOf(
                null, "gold_ingot", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 1),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "golden_hoe",
            name = "Golden Hoe",
            category = RecipeCategory.TOOLS,
            description = "Agricultural implement for tilling soil and harvesting sculk/leaves.",
            stackSize = 1,
            statInfo = "Speed: 12.0 | Durability: 32",
            grid = listOf(
                "gold_ingot", "gold_ingot", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 2),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "iron_hoe",
            name = "Iron Hoe",
            category = RecipeCategory.TOOLS,
            description = "Durable iron farming hoe for establishing expansive farmland.",
            stackSize = 1,
            statInfo = "Durability: 250 | High Harvest Speed",
            grid = listOf(
                "iron_ingot", "iron_ingot", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 2),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "wooden_shovel",
            name = "Wooden Shovel",
            category = RecipeCategory.TOOLS,
            description = "Simple wooden digging tool for dirt, sand, and gravel.",
            stackSize = 1,
            statInfo = "Durability: 59",
            grid = listOf(
                null, "oak_planks", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 1),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "stone_shovel",
            name = "Stone Shovel",
            category = RecipeCategory.TOOLS,
            description = "Solid stone digging tool for quick digging.",
            stackSize = 1,
            statInfo = "Durability: 131",
            grid = listOf(
                null, "cobblestone", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 1),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "wooden_hoe",
            name = "Wooden Hoe",
            category = RecipeCategory.TOOLS,
            description = "Basic agricultural hoe for tilling grass into farmland.",
            stackSize = 1,
            statInfo = "Durability: 59",
            grid = listOf(
                "oak_planks", "oak_planks", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 2),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "stone_hoe",
            name = "Stone Hoe",
            category = RecipeCategory.TOOLS,
            description = "Cobblestone farming tool for planting wheat, carrots, and potatoes.",
            stackSize = 1,
            statInfo = "Durability: 131",
            grid = listOf(
                "cobblestone", "cobblestone", null,
                null, "stick", null,
                null, "stick", null
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 2),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "clock",
            name = "Clock",
            category = RecipeCategory.TOOLS,
            description = "Navigational dial showing the time of day and position of the sun and moon.",
            stackSize = 64,
            statInfo = "Tracks daylight cycle even underground",
            grid = listOf(
                null, "gold_ingot", null,
                "gold_ingot", "redstone", "gold_ingot",
                null, "gold_ingot", null
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 4),
                IngredientCount("redstone", "Redstone Dust", 1)
            )
        ),
        CraftingRecipe(
            id = "compass",
            name = "Compass",
            category = RecipeCategory.TOOLS,
            description = "Navigational instrument pointing toward the world spawn point or lodestone.",
            stackSize = 64,
            statInfo = "Points to World Spawn Point",
            grid = listOf(
                null, "iron_ingot", null,
                "iron_ingot", "redstone", "iron_ingot",
                null, "iron_ingot", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 4),
                IngredientCount("redstone", "Redstone Dust", 1)
            )
        ),
        CraftingRecipe(
            id = "spyglass",
            name = "Spyglass",
            category = RecipeCategory.TOOLS,
            description = "Telescopic tool used to zoom in and survey distant terrain and structures.",
            stackSize = 1,
            statInfo = "Zooms FOV dramatically",
            grid = listOf(
                null, "amethyst_shard", null,
                null, "copper_ingot", null,
                null, "copper_ingot", null
            ),
            ingredients = listOf(
                IngredientCount("amethyst_shard", "Amethyst Shard", 1),
                IngredientCount("copper_ingot", "Copper Ingot", 2)
            )
        ),
        CraftingRecipe(
            id = "lead",
            name = "Lead",
            category = RecipeCategory.TOOLS,
            description = "Leash used to tether, guide, and tie passive mobs to fence posts.",
            stackSize = 64,
            outputCount = 2,
            statInfo = "Tethers animals and pets",
            grid = listOf(
                "string", "string", null,
                "string", "slimeball", null,
                null, null, "string"
            ),
            ingredients = listOf(
                IngredientCount("string", "String", 4),
                IngredientCount("slimeball", "Slimeball", 1)
            )
        ),

        // --- EXPANDED ARMOR ---
        CraftingRecipe(
            id = "iron_leggings",
            name = "Iron Leggings",
            category = RecipeCategory.COMBAT,
            description = "Heavy protective leg armor forged from solid iron plates.",
            stackSize = 1,
            statInfo = "Armor: +5 Defense | Durability: 225",
            grid = listOf(
                "iron_ingot", "iron_ingot", "iron_ingot",
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", null, "iron_ingot"
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 7)
            )
        ),
        CraftingRecipe(
            id = "iron_boots",
            name = "Iron Boots",
            category = RecipeCategory.COMBAT,
            description = "Reliable iron footwear offering substantial fall and physical defense.",
            stackSize = 1,
            statInfo = "Armor: +2 Defense | Durability: 195",
            grid = listOf(
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", null, "iron_ingot",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 4)
            )
        ),
        CraftingRecipe(
            id = "golden_helmet",
            name = "Golden Helmet",
            category = RecipeCategory.COMBAT,
            description = "Ornate crown offering Piglin neutrality in the Nether dimension.",
            stackSize = 1,
            statInfo = "Pacifies Piglins | Armor: +2 | Durability: 77",
            grid = listOf(
                "gold_ingot", "gold_ingot", "gold_ingot",
                "gold_ingot", null, "gold_ingot",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 5)
            )
        ),
        CraftingRecipe(
            id = "golden_chestplate",
            name = "Golden Chestplate",
            category = RecipeCategory.COMBAT,
            description = "Brilliant golden breastplate providing Piglin pacification.",
            stackSize = 1,
            statInfo = "Pacifies Piglins | Armor: +5 | Durability: 112",
            grid = listOf(
                "gold_ingot", null, "gold_ingot",
                "gold_ingot", "gold_ingot", "gold_ingot",
                "gold_ingot", "gold_ingot", "gold_ingot"
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 8)
            )
        ),
        CraftingRecipe(
            id = "golden_leggings",
            name = "Golden Leggings",
            category = RecipeCategory.COMBAT,
            description = "Protective golden pants keeping Piglins docile in the Nether.",
            stackSize = 1,
            statInfo = "Pacifies Piglins | Armor: +3 | Durability: 105",
            grid = listOf(
                "gold_ingot", "gold_ingot", "gold_ingot",
                "gold_ingot", null, "gold_ingot",
                "gold_ingot", null, "gold_ingot"
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 7)
            )
        ),
        CraftingRecipe(
            id = "golden_boots",
            name = "Golden Boots",
            category = RecipeCategory.COMBAT,
            description = "Most common footwear for Nether expeditions to avoid Piglin aggression.",
            stackSize = 1,
            statInfo = "Pacifies Piglins | Armor: +1 | Durability: 91",
            grid = listOf(
                "gold_ingot", null, "gold_ingot",
                "gold_ingot", null, "gold_ingot",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 4)
            )
        ),
        CraftingRecipe(
            id = "leather_helmet",
            name = "Leather Cap",
            category = RecipeCategory.COMBAT,
            description = "Lightweight headwear dyed in custom colors and insulating against freezing.",
            stackSize = 1,
            statInfo = "Armor: +1 | Prevents Powder Snow Freezing",
            grid = listOf(
                "leather", "leather", "leather",
                "leather", null, "leather",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("leather", "Leather", 5)
            )
        ),
        CraftingRecipe(
            id = "leather_chestplate",
            name = "Leather Tunic",
            category = RecipeCategory.COMBAT,
            description = "Flexible leather armor vest protecting against cold and impacts.",
            stackSize = 1,
            statInfo = "Armor: +3 | Prevents Powder Snow Freezing",
            grid = listOf(
                "leather", null, "leather",
                "leather", "leather", "leather",
                "leather", "leather", "leather"
            ),
            ingredients = listOf(
                IngredientCount("leather", "Leather", 8)
            )
        ),
        CraftingRecipe(
            id = "turtle_helmet",
            name = "Turtle Shell",
            category = RecipeCategory.COMBAT,
            description = "Special aquatic helmet crafted from scutes granting Water Breathing.",
            stackSize = 1,
            statInfo = "Water Breathing: +10s | Armor: +2",
            grid = listOf(
                "scute", "scute", "scute",
                "scute", null, "scute",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("scute", "Turtle Scute", 5)
            )
        ),

        // --- EXPANDED BUILDING BLOCKS ---
        CraftingRecipe(
            id = "blast_furnace",
            name = "Blast Furnace",
            category = RecipeCategory.BUILDING,
            description = "Specialized industrial furnace that smelts ores and armor at twice standard speed.",
            stackSize = 64,
            statInfo = "2x Smelting Speed for Ores & Metals",
            grid = listOf(
                "iron_ingot", "iron_ingot", "iron_ingot",
                "iron_ingot", "furnace", "iron_ingot",
                "smooth_stone", "smooth_stone", "smooth_stone"
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 5),
                IngredientCount("furnace", "Furnace", 1),
                IngredientCount("smooth_stone", "Smooth Stone", 3)
            )
        ),
        CraftingRecipe(
            id = "smoker",
            name = "Smoker",
            category = RecipeCategory.BUILDING,
            description = "Culinary workstation that cooks raw food items at twice standard furnace speed.",
            stackSize = 64,
            statInfo = "2x Cooking Speed for Food",
            grid = listOf(
                null, "oak_log", null,
                "oak_log", "furnace", "oak_log",
                null, "oak_log", null
            ),
            ingredients = listOf(
                IngredientCount("furnace", "Furnace", 1),
                IngredientCount("oak_log", "Oak Log", 4)
            )
        ),
        CraftingRecipe(
            id = "barrel",
            name = "Barrel",
            category = RecipeCategory.BUILDING,
            description = "Compact 27-slot storage container that can be opened even with solid blocks above.",
            stackSize = 64,
            statInfo = "27 Slots | Opens in tight spaces",
            grid = listOf(
                "oak_planks", "oak_slab", "oak_planks",
                "oak_planks", null, "oak_planks",
                "oak_planks", "oak_slab", "oak_planks"
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 6),
                IngredientCount("oak_slab", "Oak Slab", 2)
            )
        ),
        CraftingRecipe(
            id = "ender_chest",
            name = "Ender Chest",
            category = RecipeCategory.BUILDING,
            description = "Interdimensional storage chest sharing inventory across all ender chests anywhere.",
            stackSize = 64,
            statInfo = "Interdimensional Shared Inventory",
            grid = listOf(
                "obsidian", "obsidian", "obsidian",
                "obsidian", "eye_of_ender", "obsidian",
                "obsidian", "obsidian", "obsidian"
            ),
            ingredients = listOf(
                IngredientCount("obsidian", "Obsidian", 8),
                IngredientCount("eye_of_ender", "Eye of Ender", 1)
            )
        ),
        CraftingRecipe(
            id = "lantern",
            name = "Lantern",
            category = RecipeCategory.BUILDING,
            description = "Luminous lantern producing a bright light level of 15, hangable from chains or ceilings.",
            stackSize = 64,
            statInfo = "Light Level: 15 (Max Luminance)",
            grid = listOf(
                "iron_nugget", "iron_nugget", "iron_nugget",
                "iron_nugget", "torch", "iron_nugget",
                "iron_nugget", "iron_nugget", "iron_nugget"
            ),
            ingredients = listOf(
                IngredientCount("iron_nugget", "Iron Nugget", 8),
                IngredientCount("torch", "Torch", 1)
            )
        ),
        CraftingRecipe(
            id = "scaffolding",
            name = "Scaffolding",
            category = RecipeCategory.BUILDING,
            description = "Fast climbing structure for construction that collapses when base is broken.",
            stackSize = 64,
            outputCount = 6,
            statInfo = "Climbable Construction Frame",
            grid = listOf(
                "bamboo", "string", "bamboo",
                "bamboo", null, "bamboo",
                "bamboo", null, "bamboo"
            ),
            ingredients = listOf(
                IngredientCount("bamboo", "Bamboo", 6),
                IngredientCount("string", "String", 1)
            )
        ),
        CraftingRecipe(
            id = "oak_door",
            name = "Oak Door",
            category = RecipeCategory.BUILDING,
            description = "Classic wooden door operable by hand or redstone signals.",
            stackSize = 64,
            outputCount = 3,
            statInfo = "Hand / Redstone Operable",
            grid = listOf(
                "oak_planks", "oak_planks", null,
                "oak_planks", "oak_planks", null,
                "oak_planks", "oak_planks", null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 6)
            )
        ),
        CraftingRecipe(
            id = "iron_door",
            name = "Iron Door",
            category = RecipeCategory.BUILDING,
            description = "Heavy reinforced security door that only opens via redstone signal.",
            stackSize = 64,
            outputCount = 3,
            statInfo = "Redstone Only | Zombie Resistant",
            grid = listOf(
                "iron_ingot", "iron_ingot", null,
                "iron_ingot", "iron_ingot", null,
                "iron_ingot", "iron_ingot", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 6)
            )
        ),
        CraftingRecipe(
            id = "oak_trapdoor",
            name = "Oak Trapdoor",
            category = RecipeCategory.BUILDING,
            description = "Horizontal wooden hatch for floor openings, ladders, and crawlspaces.",
            stackSize = 64,
            outputCount = 2,
            statInfo = "Enables crawling in 1-block spaces",
            grid = listOf(
                "oak_planks", "oak_planks", "oak_planks",
                "oak_planks", "oak_planks", "oak_planks",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 6)
            )
        ),
        CraftingRecipe(
            id = "oak_fence",
            name = "Oak Fence",
            category = RecipeCategory.BUILDING,
            description = "1.5-block high barrier that mobs and players cannot jump over without effects.",
            stackSize = 64,
            outputCount = 3,
            statInfo = "Effective Height: 1.5 blocks",
            grid = listOf(
                "oak_planks", "stick", "oak_planks",
                "oak_planks", "stick", "oak_planks",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 4),
                IngredientCount("stick", "Stick", 2)
            )
        ),
        CraftingRecipe(
            id = "oak_fence_gate",
            name = "Oak Fence Gate",
            category = RecipeCategory.BUILDING,
            description = "Swinging entryway allowing passage through fences and walls.",
            stackSize = 64,
            statInfo = "Opens both inward and outward",
            grid = listOf(
                "stick", "oak_planks", "stick",
                "stick", "oak_planks", "stick",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("stick", "Stick", 4),
                IngredientCount("oak_planks", "Oak Planks", 2)
            )
        ),
        CraftingRecipe(
            id = "glass_pane",
            name = "Glass Pane",
            category = RecipeCategory.BUILDING,
            description = "Thin transparent window pane aligning with adjacent blocks and walls.",
            stackSize = 64,
            outputCount = 16,
            statInfo = "High-efficiency 16x output from 6 glass",
            grid = listOf(
                "glass", "glass", "glass",
                "glass", "glass", "glass",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("glass", "Glass Block", 6)
            )
        ),
        CraftingRecipe(
            id = "stonecutter",
            name = "Stonecutter",
            category = RecipeCategory.BUILDING,
            description = "Precision mason workstation allowing 1:1 recipe conversion for stairs, slabs, and stone variants.",
            stackSize = 64,
            statInfo = "Exact 1:1 Masonry Conversion",
            grid = listOf(
                null, "iron_ingot", null,
                "stone", "stone", "stone",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 1),
                IngredientCount("stone", "Stone", 3)
            )
        ),
        CraftingRecipe(
            id = "grindstone",
            name = "Grindstone",
            category = RecipeCategory.BUILDING,
            description = "Disenchants items, refunds experience, and repairs durability of combined items.",
            stackSize = 64,
            statInfo = "Disenchants & Repairs Equipment",
            grid = listOf(
                "stick", "stone_slab", "stick",
                "oak_planks", null, "oak_planks",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("stick", "Stick", 2),
                IngredientCount("stone_slab", "Stone Slab", 1),
                IngredientCount("oak_planks", "Oak Planks", 2)
            )
        ),

        // --- EXPANDED REDSTONE & MECHANISMS ---
        CraftingRecipe(
            id = "redstone_block",
            name = "Block of Redstone",
            category = RecipeCategory.REDSTONE,
            description = "Solid mineral block providing a permanent, pushable redstone power level of 15.",
            stackSize = 64,
            statInfo = "Pushable Power Source: Level 15",
            grid = listOf(
                "redstone", "redstone", "redstone",
                "redstone", "redstone", "redstone",
                "redstone", "redstone", "redstone"
            ),
            ingredients = listOf(
                IngredientCount("redstone", "Redstone Dust", 9)
            )
        ),
        CraftingRecipe(
            id = "lever",
            name = "Lever",
            category = RecipeCategory.REDSTONE,
            description = "Toggleable mechanical switch providing steady continuous redstone signal.",
            stackSize = 64,
            statInfo = "Toggleable ON/OFF Power Switch",
            grid = listOf(
                null, "stick", null,
                null, "cobblestone", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("stick", "Stick", 1),
                IngredientCount("cobblestone", "Cobblestone", 1)
            )
        ),
        CraftingRecipe(
            id = "stone_button",
            name = "Stone Button",
            category = RecipeCategory.REDSTONE,
            description = "Momentary pressure button providing a 1.0-second (10 redstone tick) pulse.",
            stackSize = 64,
            statInfo = "Pulse Duration: 10 Redstone Ticks (1.0s)",
            grid = listOf(
                null, null, null,
                null, "stone", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("stone", "Stone", 1)
            )
        ),
        CraftingRecipe(
            id = "oak_button",
            name = "Oak Button",
            category = RecipeCategory.REDSTONE,
            description = "Wooden trigger button that can also be activated by incoming arrows.",
            stackSize = 64,
            statInfo = "Pulse Duration: 15 Redstone Ticks (1.5s)",
            grid = listOf(
                null, null, null,
                null, "oak_planks", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 1)
            )
        ),
        CraftingRecipe(
            id = "stone_pressure_plate",
            name = "Stone Pressure Plate",
            category = RecipeCategory.REDSTONE,
            description = "Floor sensor activating only when stepped on by players or hostile/passive mobs.",
            stackSize = 64,
            statInfo = "Activated by Players and Mobs",
            grid = listOf(
                null, null, null,
                "stone", "stone", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("stone", "Stone", 2)
            )
        ),
        CraftingRecipe(
            id = "oak_pressure_plate",
            name = "Oak Pressure Plate",
            category = RecipeCategory.REDSTONE,
            description = "Floor sensor sensitive to players, mobs, dropped items, and incoming arrows.",
            stackSize = 64,
            statInfo = "Activated by Items, Arrows, and Mobs",
            grid = listOf(
                null, null, null,
                "oak_planks", "oak_planks", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("oak_planks", "Oak Planks", 2)
            )
        ),
        CraftingRecipe(
            id = "target",
            name = "Target Block",
            category = RecipeCategory.REDSTONE,
            description = "Archery target emitting a redstone signal proportional to hit proximity to center.",
            stackSize = 64,
            statInfo = "Signal strength 1-15 based on accuracy",
            grid = listOf(
                null, "redstone", null,
                "redstone", "hay_bale", "redstone",
                null, "redstone", null
            ),
            ingredients = listOf(
                IngredientCount("redstone", "Redstone Dust", 4),
                IngredientCount("hay_bale", "Hay Bale", 1)
            )
        ),
        CraftingRecipe(
            id = "daylight_detector",
            name = "Daylight Detector",
            category = RecipeCategory.REDSTONE,
            description = "Solar panel emitting a redstone signal corresponding to natural daylight level.",
            stackSize = 64,
            statInfo = "Invertible for night detection",
            grid = listOf(
                "glass", "glass", "glass",
                "quartz", "quartz", "quartz",
                "oak_slab", "oak_slab", "oak_slab"
            ),
            ingredients = listOf(
                IngredientCount("glass", "Glass Block", 3),
                IngredientCount("quartz", "Nether Quartz", 3),
                IngredientCount("oak_slab", "Oak Slab", 3)
            )
        ),
        CraftingRecipe(
            id = "observer",
            name = "Observer",
            category = RecipeCategory.REDSTONE,
            description = "Sensor that detects block state changes directly in front and outputs a 1-tick pulse.",
            stackSize = 64,
            statInfo = "Detects Block Updates • 1-Tick Pulse",
            grid = listOf(
                "cobblestone", "cobblestone", "cobblestone",
                "redstone", "redstone", "quartz",
                "cobblestone", "cobblestone", "cobblestone"
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 6),
                IngredientCount("redstone", "Redstone Dust", 2),
                IngredientCount("quartz", "Nether Quartz", 1)
            )
        ),
        CraftingRecipe(
            id = "dropper",
            name = "Dropper",
            category = RecipeCategory.REDSTONE,
            description = "Ejection device that drops items into the world or transfers them into adjacent inventories.",
            stackSize = 64,
            statInfo = "Transfers items to containers or ground",
            grid = listOf(
                "cobblestone", "cobblestone", "cobblestone",
                "cobblestone", null, "cobblestone",
                "cobblestone", "redstone", "cobblestone"
            ),
            ingredients = listOf(
                IngredientCount("cobblestone", "Cobblestone", 7),
                IngredientCount("redstone", "Redstone Dust", 1)
            )
        ),
        CraftingRecipe(
            id = "tripwire_hook",
            name = "Tripwire Hook",
            category = RecipeCategory.REDSTONE,
            description = "Pair with string to construct invisible perimeter alarm triggers.",
            stackSize = 64,
            outputCount = 2,
            statInfo = "Connects via String to trigger traps",
            grid = listOf(
                null, "iron_ingot", null,
                null, "stick", null,
                null, "oak_planks", null
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 1),
                IngredientCount("stick", "Stick", 1),
                IngredientCount("oak_planks", "Oak Planks", 1)
            )
        ),

        // --- EXPANDED TRANSPORT ---
        CraftingRecipe(
            id = "powered_rail",
            name = "Powered Rail",
            category = RecipeCategory.TRANSPORT,
            description = "Accelerates minecarts when powered with redstone, or acts as a brake when unpowered.",
            stackSize = 64,
            outputCount = 6,
            statInfo = "Max Speed: 8 m/s • Brake when off",
            grid = listOf(
                "gold_ingot", null, "gold_ingot",
                "gold_ingot", "stick", "gold_ingot",
                "gold_ingot", "redstone", "gold_ingot"
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 6),
                IngredientCount("stick", "Stick", 1),
                IngredientCount("redstone", "Redstone Dust", 1)
            )
        ),
        CraftingRecipe(
            id = "detector_rail",
            name = "Detector Rail",
            category = RecipeCategory.TRANSPORT,
            description = "Track segment that generates a redstone output whenever a minecart rolls over it.",
            stackSize = 64,
            outputCount = 6,
            statInfo = "Detects passing minecarts",
            grid = listOf(
                "iron_ingot", null, "iron_ingot",
                "iron_ingot", "stone_pressure_plate", "iron_ingot",
                "iron_ingot", "redstone", "iron_ingot"
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 6),
                IngredientCount("stone_pressure_plate", "Stone Pressure Plate", 1),
                IngredientCount("redstone", "Redstone Dust", 1)
            )
        ),
        CraftingRecipe(
            id = "chest_minecart",
            name = "Minecart with Chest",
            category = RecipeCategory.TRANSPORT,
            description = "Mobile freight container for transporting items across rail networks.",
            stackSize = 1,
            statInfo = "27 Cargo Slots on Rails",
            grid = listOf(
                null, "chest", null,
                null, "minecart", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("chest", "Chest", 1),
                IngredientCount("minecart", "Minecart", 1)
            )
        ),

        // --- EXPANDED FOOD & COOKING ---
        CraftingRecipe(
            id = "cookie",
            name = "Cookie",
            category = RecipeCategory.FOOD,
            description = "Bite-sized snack providing small hunger restoration in batches of 8.",
            stackSize = 64,
            outputCount = 8,
            statInfo = "Hunger: +2 | Saturation: +0.4",
            grid = listOf(
                null, null, null,
                "wheat", "cocoa_beans", "wheat",
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("wheat", "Wheat", 2),
                IngredientCount("cocoa_beans", "Cocoa Beans", 1)
            )
        ),
        CraftingRecipe(
            id = "pumpkin_pie",
            name = "Pumpkin Pie",
            category = RecipeCategory.FOOD,
            description = "Nutritious baked pie made from fresh pumpkin, sugar, and an egg.",
            stackSize = 64,
            isShapeless = true,
            statInfo = "Hunger: +8 | Saturation: +4.8",
            grid = listOf(
                "pumpkin", "sugar", "egg",
                null, null, null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("pumpkin", "Pumpkin", 1),
                IngredientCount("sugar", "Sugar", 1),
                IngredientCount("egg", "Egg", 1)
            )
        ),
        CraftingRecipe(
            id = "mushroom_stew",
            name = "Mushroom Stew",
            category = RecipeCategory.FOOD,
            description = "Warm hearty broth crafted from red and brown mushrooms.",
            stackSize = 1,
            isShapeless = true,
            statInfo = "Hunger: +6 | Saturation: +7.2",
            grid = listOf(
                "red_mushroom", "brown_mushroom", "bowl",
                null, null, null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("red_mushroom", "Red Mushroom", 1),
                IngredientCount("brown_mushroom", "Brown Mushroom", 1),
                IngredientCount("bowl", "Bowl", 1)
            )
        ),
        CraftingRecipe(
            id = "sugar",
            name = "Sugar",
            category = RecipeCategory.FOOD,
            description = "Refined sweet powder used in baking cakes, brewing speed potions, and fermented spider eyes.",
            stackSize = 64,
            isShapeless = true,
            statInfo = "Baking & Brewing Agent",
            grid = listOf(
                null, null, null,
                null, "sugar_cane", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("sugar_cane", "Sugar Cane", 1)
            )
        ),

        // --- EXPANDED BREWING, MAGIC & MATERIALS ---
        CraftingRecipe(
            id = "blaze_powder",
            name = "Blaze Powder",
            category = RecipeCategory.BREWING,
            description = "Fiery powder processed from blaze rods, essential as fuel for brewing stands and Eye of Ender.",
            stackSize = 64,
            outputCount = 2,
            isShapeless = true,
            statInfo = "Fuels 20 Brewing Operations",
            grid = listOf(
                null, null, null,
                null, "blaze_rod", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("blaze_rod", "Blaze Rod", 1)
            )
        ),
        CraftingRecipe(
            id = "magma_cream",
            name = "Magma Cream",
            category = RecipeCategory.BREWING,
            description = "Viscous hot compound for brewing Fire Resistance potions and crafting magma blocks.",
            stackSize = 64,
            isShapeless = true,
            statInfo = "Brews Fire Resistance Potion",
            grid = listOf(
                null, null, null,
                "blaze_powder", "slimeball", null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("blaze_powder", "Blaze Powder", 1),
                IngredientCount("slimeball", "Slimeball", 1)
            )
        ),
        CraftingRecipe(
            id = "fermented_spider_eye",
            name = "Fermented Spider Eye",
            category = RecipeCategory.BREWING,
            description = "Corrosive brewing catalyst that corrupts and inverts potion effects (e.g., Healing to Harming).",
            stackSize = 64,
            isShapeless = true,
            statInfo = "Inverts potion effects into negative variants",
            grid = listOf(
                "spider_eye", "brown_mushroom", "sugar",
                null, null, null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("spider_eye", "Spider Eye", 1),
                IngredientCount("brown_mushroom", "Brown Mushroom", 1),
                IngredientCount("sugar", "Sugar", 1)
            )
        ),
        CraftingRecipe(
            id = "fire_charge",
            name = "Fire Charge",
            category = RecipeCategory.BREWING,
            description = "Incendiary projectile that can be fired from dispensers as a ghast-like fireball.",
            stackSize = 64,
            outputCount = 3,
            isShapeless = true,
            statInfo = "Fires blazing projectile from dispenser",
            grid = listOf(
                "blaze_powder", "coal", "gunpowder",
                null, null, null,
                null, null, null
            ),
            ingredients = listOf(
                IngredientCount("blaze_powder", "Blaze Powder", 1),
                IngredientCount("coal", "Coal", 1),
                IngredientCount("gunpowder", "Gunpowder", 1)
            )
        ),
        CraftingRecipe(
            id = "end_crystal",
            name = "End Crystal",
            category = RecipeCategory.BREWING,
            description = "Mystical crystal that heals the Ender Dragon and can be used to respawn the boss.",
            stackSize = 64,
            statInfo = "Respawns Ender Dragon on bedrock altar",
            grid = listOf(
                "glass", "glass", "glass",
                "glass", "eye_of_ender", "glass",
                "glass", "ghast_tear", "glass"
            ),
            ingredients = listOf(
                IngredientCount("glass", "Glass Block", 7),
                IngredientCount("eye_of_ender", "Eye of Ender", 1),
                IngredientCount("ghast_tear", "Ghast Tear", 1)
            )
        ),
        CraftingRecipe(
            id = "beacon",
            name = "Beacon",
            category = RecipeCategory.BREWING,
            description = "Monumental beam projector granting powerful area buffs when placed atop pyramid.",
            stackSize = 64,
            statInfo = "Area Buffs: Haste, Speed, Strength, Regen",
            grid = listOf(
                "glass", "glass", "glass",
                "glass", "nether_star", "glass",
                "obsidian", "obsidian", "obsidian"
            ),
            ingredients = listOf(
                IngredientCount("glass", "Glass Block", 5),
                IngredientCount("nether_star", "Nether Star", 1),
                IngredientCount("obsidian", "Obsidian", 3)
            )
        ),
        CraftingRecipe(
            id = "enchanting_table",
            name = "Enchanting Table",
            category = RecipeCategory.BREWING,
            description = "Arcane workstation imbuing weapons, tools, and armor with magical powers using Lapis Lazuli.",
            stackSize = 64,
            statInfo = "Up to Level 30 Enchantments with 15 Bookshelves",
            grid = listOf(
                null, "book", null,
                "diamond", "obsidian", "diamond",
                "obsidian", "obsidian", "obsidian"
            ),
            ingredients = listOf(
                IngredientCount("book", "Book", 1),
                IngredientCount("diamond", "Diamond", 2),
                IngredientCount("obsidian", "Obsidian", 4)
            )
        ),
        CraftingRecipe(
            id = "hay_bale",
            name = "Hay Bale",
            category = RecipeCategory.BUILDING,
            description = "Compressed agricultural block reducing fall damage by 80% when landed on.",
            stackSize = 64,
            statInfo = "Reduces fall damage by 80%",
            grid = listOf(
                "wheat", "wheat", "wheat",
                "wheat", "wheat", "wheat",
                "wheat", "wheat", "wheat"
            ),
            ingredients = listOf(
                IngredientCount("wheat", "Wheat", 9)
            )
        ),
        CraftingRecipe(
            id = "iron_block",
            name = "Block of Iron",
            category = RecipeCategory.BUILDING,
            description = "Dense storage block of 9 iron ingots, essential for beacons and iron golems.",
            stackSize = 64,
            statInfo = "Beacon Base • Spawns Iron Golem",
            grid = listOf(
                "iron_ingot", "iron_ingot", "iron_ingot",
                "iron_ingot", "iron_ingot", "iron_ingot",
                "iron_ingot", "iron_ingot", "iron_ingot"
            ),
            ingredients = listOf(
                IngredientCount("iron_ingot", "Iron Ingot", 9)
            )
        ),
        CraftingRecipe(
            id = "gold_block",
            name = "Block of Gold",
            category = RecipeCategory.BUILDING,
            description = "Luxurious solid gold block for beacon pyramids and golden apple recipes.",
            stackSize = 64,
            statInfo = "Beacon Pyramid Base",
            grid = listOf(
                "gold_ingot", "gold_ingot", "gold_ingot",
                "gold_ingot", "gold_ingot", "gold_ingot",
                "gold_ingot", "gold_ingot", "gold_ingot"
            ),
            ingredients = listOf(
                IngredientCount("gold_ingot", "Gold Ingot", 9)
            )
        ),
        CraftingRecipe(
            id = "diamond_block",
            name = "Block of Diamond",
            category = RecipeCategory.BUILDING,
            description = "Ultimate flex block of condensed diamonds, providing beacon energy.",
            stackSize = 64,
            statInfo = "Beacon Base • Ultimate Compact Storage",
            grid = listOf(
                "diamond", "diamond", "diamond",
                "diamond", "diamond", "diamond",
                "diamond", "diamond", "diamond"
            ),
            ingredients = listOf(
                IngredientCount("diamond", "Diamond", 9)
            )
        )
    )

    fun getRecipeById(id: String): CraftingRecipe? {
        return RECIPES.find { it.id == id }
    }

    fun findById(id: String): CraftingRecipe? = getRecipeById(id)

    private data class GridDimensions(val rows: Int, val cols: Int, val pattern: List<List<String?>>)

    private fun normalizeGrid(grid: List<String?>): GridDimensions {
        val nonNull = mutableListOf<Triple<Int, Int, String>>()
        for (i in 0 until minOf(9, grid.size)) {
            val item = grid[i]
            if (item != null) {
                nonNull.add(Triple(i / 3, i % 3, item))
            }
        }
        if (nonNull.isEmpty()) {
            return GridDimensions(0, 0, emptyList())
        }
        val minR = nonNull.minOf { it.first }
        val maxR = nonNull.maxOf { it.first }
        val minC = nonNull.minOf { it.second }
        val maxC = nonNull.maxOf { it.second }
        val rows = maxR - minR + 1
        val cols = maxC - minC + 1

        val pattern = MutableList(rows) { MutableList<String?>(cols) { null } }
        for (coord in nonNull) {
            pattern[coord.first - minR][coord.second - minC] = coord.third
        }
        return GridDimensions(rows, cols, pattern)
    }

    private fun patternsEqual(a: List<List<String?>>, b: List<List<String?>>): Boolean {
        if (a.size != b.size) return false
        for (r in a.indices) {
            if (a[r].size != b[r].size) return false
            for (c in a[r].indices) {
                if (a[r][c] != b[r][c]) return false
            }
        }
        return true
    }

    fun matchRecipe(inputGrid: List<String?>): CraftingRecipe? {
        if (inputGrid.size != 9 || inputGrid.all { it == null }) return null

        val inputDims = normalizeGrid(inputGrid)
        val nonNullInputs = inputGrid.filterNotNull().sorted()

        for (recipe in RECIPES) {
            if (recipe.isShapeless) {
                val expectedItems = mutableListOf<String>()
                recipe.ingredients.forEach { ing ->
                    repeat(ing.count) { expectedItems.add(ing.itemId) }
                }
                if (nonNullInputs == expectedItems.sorted()) {
                    return recipe
                }
            } else {
                val recDims = normalizeGrid(recipe.grid)
                if (inputDims.rows == recDims.rows && inputDims.cols == recDims.cols) {
                    if (patternsEqual(inputDims.pattern, recDims.pattern)) {
                        return recipe
                    }
                    val mirrored = recDims.pattern.map { row -> row.reversed() }
                    if (patternsEqual(inputDims.pattern, mirrored)) {
                        return recipe
                    }
                }
            }
        }
        return null
    }
}
