## Battle Related Assumptions:
- Battle and support (if applicable) radii values for each Enemy, Tower and Campfire:
    - Slug Battle Radius: 1
    - Slug Support Radius: 1
    - Zombies Battle Radius: 1
    - Zombies Support Radius: 2
    - Vampire Battle Radius: 2
    - Vampire Support Radius: 3
    - Tower Support Radius: 2
    - Campfire Support Radius: 2
    - Circular Support Radius calculation done by starter code
- Characters and enemies do not keep moving in battle
    - Notification and sound effects to show the initiation/engagement of battle and when the character dies
    - When battle is finished (and won) the loot is simply added to the inventory
    - When the game ends show an ending screen with achieved score and goals
- Assumptions regarding Allies:
    - As strong as slugs
    - Max of 5 allied soldiers at a time
    - Allied soldiers gain full HP when passing through barracks again
    - Allied soldiers should battle the enemies first
- The Initial values of damage/health/XP for each entity:
    - Main Character: 1, 100, 0
    - Slug Damage   : 5, 3, 50
    - Zombie Damage : 8, 10, 100
    - Vampire Damage: 20, 20, 200
    - Ally Damage   : 5, 5, 0
- When the main character defeats enemies, the character gains the specified amount of XP from the enemy. When an allied soldier defeats an enemy, that XP is redirected to the main character.
- The more the XP, the increased damage/defence stats of weapons available from loot and Hero's Castle, enhanced enemy and ally health and damage
    - Health Formula: initialHealth * (1 + XP / 1000)
    - Damage Formula: weaponDamage * (1 + XP / 1000)
- When a battle is initiated, there will be a set priority of attacks:
    1. Tower(s)
    2. Allied soldiers
    3. Enemies (remaining enemies will attack allied soldiers first (if applicable) then main character)
    4. Main Character
- When some enemy X is within the support radius of another enemy Y, the enemy Y teleports to X's position + 1 and the battle state should calculate the the total damage dealt on MC
    - Note: such teleportation only occurs when the enemy is in battle
- Critical Hits deal 3x Damage
- Defeating an enemy drops either a item or a card. (50/50)
- Defeating harder enemies has a better chance of dropping greater rewards
    - Defeating slug: Common-Uncommon drops
    - Defeating zombie: Uncommon-Epic drops
    - Defeating Vampire: Epic-Rare drops
 
## Building Related Assumptions:
- When the character reaches the Hero Castle, item shop pops up and game is paused until user exits the shop
- Each card has its own rarity: Common (60%), Uncommon (30%), Epic (10%)
    - Vampire Castle: Epic
    - Zombie Pit: Uncommon
    - Tower: Epic
    - Village: Epic
    - Barracks: Epic
    - Trap: Common
    - Campfire: Epic 
- The Initial Values of damage/expiry(/rounds) for building items:
    - Tower: 4, 5
    - Trap: 8, 1
    - Campfire: 0, 5
    - Barracks: 0, 5
    - Village: 0, 5
    - Vampire Castle: 0, 5
    - Zombie Pit: 0, 5
- You cannot place a building on an occupied tile (Occupied by another building)
- Villages heal 10 health to character
- You cannot move buildings once placed.
- Killing an enemy grants either an item drop or a building but not both.
- ZombiePits spawn 1 zombie every loop
- Vampire castles spawn vampires every 5 loops
- Zombies/vampires spawn on their respective buildings
- Heros castle is at 0,0:
- Character does not trigger traps
- Traps do not give the character any form of reward

## Inventory Related Assumptions:
- There will be a general ‘weapons’ slot in the equipped inventory section instead of a ‘sword’ slot. The Human Player will drag the weapon to be used from the unequipped inventory into this slot to use the weapon. Only items which are equippable can be dragged from the unequipped inventory into the equipped inventory 
 
## Item Related Assumptions:
- Initially, when the game starts, the main character has no weapons and will use the character base damage (fighting with fists). This base damage will then increase according to the XP 
- The Initial Values of damage/durability for attack items:
    - Sword: 8, 10
    - Staff: 3, 8
    - Stake Damage: 4, 8
- Item Costs at the Hero’s Castle:
    - Sword: 10
    - Stake: 8
    - Staff: 8
    - Armour: 20
    - Shield: 10
    - Helmet: 10
    - Health Potion: 20
- Item rarity (since some items cost more) - excluding rare items
    common 60%, uncommon 40%
    - Sword: common
    - Stake: common
    - helmet: common
    - Armour: uncommon
    - shield: uncommon
    - staff: uncommon
    - healthpotion: uncommon
- The Initial Values of defence/crit protection/durability/ for defence items:
    - Armour Defence: 40%, 0%, 10
    - Helmet Defence: 10%, 0%, 10
    - Shield Defence: 20%, 60%, 5
- Items have a set number of uses:
    - If item uses are reduced to 0, the item will disappear
- The shield item reduces inflicted damage by the defence stats
- Health Potions (when acquired) will be displayed in Character's inventory. It can be used by simply clicking on it in the time of need or by pressing 'H'. When used, the potion item will disappear and the character's health will return to max. A sound effect will also be added to signify the usage of a Health Potion.
- Basic items have a 99% chance of dropping with each item being equally as probable, rare items have a 1% chance of dropping. (this changes based on enemy type defeated)
- Defeating higher level enemies increases the chance of dropping rare items (promotes placing zombie pit buildings)
    
## Game State Related Assumptions:
- The Game starts with the Vampire Castle and Zombie Pit already built
    - It will be randomly located adjacent to a non-path tile. However, both cannot be placed on the same tile and cannot occupy the tile right in front of the Hero’s Castle 
- The actual values of loot (gold, experience, type of cards, items) dropped when an enemy dies, card is destroyed due to having too many cards, or having too many items in inventory will be randomized:
    - Gold Formula: random() * ((100 + XP) / 1000)
    - XP gained from enemies will be unique and are already defined in the ‘Battle Related Assumptions’
- The specified combination of goals XP/Gold/Cycles for each level:
    - Survival: 10,000/1,000/20
    - Berserker: 8,000/850/18
    - Normal: 20,000/2,000/40
- The allied soldier will join the main character when the main character is positioned on the tile right in front of the barracks
 
## Graphics Related Assumptions:
- What is the size of a tile?
    - 36px x 36px
- What is the size of the game screen?
    - 12 tiles x 20 tiles
    - What is the size of the map screen?
        - 8 tiles x 16 tiles
    - What is the size of the equipment screen?
        - 4 tiles x 12 tiles
    - What is the size of the inventory screen?
        - 4 tiles x 4 tiles
    - What is the size of the options screen?
        - 4 tiles x 4 tiles
    - What is the size of the card screen?
        - 8 tiles x 1 tiles
- What is the size of the item shop screen?
    - 8 tiles x 6 tiles
- What is the size of the main menu screen?
    - 8 tiles x 6 tiles
- What is the size of the new game screen?
    - 8 tiles x 6 tiles
- What is the size of the settings screen?
    - 8 tiles x 6 tiles