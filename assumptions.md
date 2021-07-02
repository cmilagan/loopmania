Questions that need addressing:
- Battle Related Questions:
    - What are the battle and support (if applicable) radii values for each Enemy, Tower and Campfire?
        - Having a circular support circumference would make interacting with square tiles difficult. Should we have a square circumference instead? (with radii representing tile width)
    - Do characters and enemies keep moving in battle?
        - Should we use an animation to show the initiation/engagement of battle?
        - When battle is finished (and won), is the loot simply added to the inventory or should we animate it?
        - When the game ends, do we return back to main menu? (trivial but spec does not specify)
    - How strong are allied soldiers compared to the enemies?

- Item Related Questions:
    - What are the actual values of damage/defence for each entity? E.g., damage of sword? defence of shield? zombie damage?
    - Should items have a health level and damage stats?
        - If items have a health level, and it reduces to 0, do the items disappear? I.e., exhausted? Should we create an animation (or alternatively sound effects) to model this change?
    - Should the shield item reduce inflicted damage? Or should it simply reduce the chance of critical attack?
    - Health Potions (when aquired) will be displayed in Character's inventory. It can be used by simply clicking on it in the time of need or by pressing 'H'. When clicked, the potion item will disappear and the character's health will return to max. Should we include an animation or sound effects for this?
    
- Game State Related Questions:
    - Should the Game start with the Vampire Castle and Zombie Pit already built?
    - What are the actual values of loot (gold, experience, type of cards, items) dropped when enemy dies, card is destroyed due to having too many cards, or having too many items in inventory and oldest item is destroyed? Should we randomize the amount of loot gained?
    - What are the specified combination of goals for each level?
        - Obtaining a specified level of experience
        - Amassing a specified amount of gold
        - Completing a specified number of cycles of the path
    - Will the allied soldier join the main character when the main character is positioned on the tile right in front of the barracks? (may need Observer Pattern for this)
