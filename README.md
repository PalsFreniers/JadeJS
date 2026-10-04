# JadeJS

Jade extension for KubeJS. It lets you customise the Jade block tooltip from KubeJS scripts.

## Requirements

- NeoForge
- KubeJS
- Jade

All three are required dependencies.

## How it works

JadeJS registers a Jade plugin that adds a block component provider on every block. When Jade builds a block tooltip, the provider fires the client-side KubeJS event `JadeEvents.blockTooltip`. Your script can then add, remove or change tooltip lines.

It also exposes two global bindings to scripts: `Jade` (styles and fluid helpers) and `JadeElements` (tooltip element factories).

## Usage

Put scripts in `kubejs/client_scripts/`.

```js
JadeEvents.blockTooltip(event => {
    if (event.blockId === 'minecraft:furnace') {
        event.addComponent(Text.of('Custom line'))
    }
})
```

## Event: JadeEvents.blockTooltip

Client side only.

| Member | Description |
|---|---|
| `blockId` | Id of the block being looked at, e.g. `minecraft:stone` |
| `player` | The local player |
| `tooltip` | The underlying Jade `ITooltip` |
| `addComponent(line)` | Append a text line |
| `addComponent(index, line)` | Insert a text line at an index |
| `addComponent(tag, line)` | Append a text line with a custom tag |
| `addComponent(index, tag, line)` | Insert a text line at an index with a custom tag |
| `addElement(...)` | Same four overloads as `addComponent`, but takes a Jade `IElement` |
| `remove(tag)` | Remove the tooltip entries with this tag, returns whether something was removed |
| `setHarvestTools(items, valid)` | Replace the harvest tool display with the given items and a valid/invalid mark |
| `setHarvestToolValid(valid)` | Keep the existing harvest tool display but change the valid/invalid mark |
| `parseComponentPatch(string)` | Parse a component string such as `[minecraft:damage=5]` into a `DataComponentPatch` |

Notes:

- Tags are resource location strings. Lines added without a tag use `jadejs:kubejs_custom`.
- In `setHarvestTools`, `items` is a list of item ids. An entry starting with `#` is read as an item tag and expands to every item in it.

## Binding: Jade

| Method | Description |
|---|---|
| `vec2(x, y)` | Create a `Vec2` |
| `tooltipBox()`, `nestedBox()`, `viewGroupBox()` | Box styles from the current Jade theme |
| `emptyGradientBorderBox()` | Transparent gradient border box style |
| `colorPalette()` | Default Jade color palette |
| `colorPalette(normal, info, title, success, warning, danger, failure)` | Custom color palette from seven int colors |
| `spriteBox(...)` | Sprite based box style. Overloads accept an optional progress offset (`float[]`), a color palette, an optional padding (`int[]`) and a sprite id |
| `fluidObject()` | Empty fluid object |
| `fluidObject(fluid)` | Fluid object with a full block volume |
| `fluidObject(fluid, amount)` | Fluid object with a given amount |
| `fluidObject(fluid, amount, components)` | Fluid object with a given amount and data components |
| `progressStyle()` | Default Jade progress style |

## Binding: JadeElements

Thin wrappers around Jade's element helper. Each method returns an `IElement` usable with `addElement`.

| Method | Description |
|---|---|
| `text(component)` | Text element |
| `spacer(width, height)` | Empty space |
| `item(stack)`, `item(stack, scale)`, `item(stack, scale, text)` | Item icon |
| `smallItem(stack)` | Small item icon |
| `fluid(fluidObject)` | Fluid element |
| `progress(progress)` | Progress bar |
| `progress(progress, text, style, boxStyle, canDecrease)` | Progress bar with text and styles |
| `progress(progress, baseSprite, progressSprite, width, height, canDecrease)` | Progress bar drawn from sprites |
| `box(tooltip, boxStyle)` | Tooltip wrapped in a box |
| `sprite(sprite, width, height)` | Sprite element |

## Project layout

```
src/main/java/org/palsfreniers/jadejs/
    PMain.java                      Mod entry point (mod id: jadejs)
    jade/                           Jade side: plugin and tooltip provider
    kubejs/                         KubeJS side: plugin, events, bindings
src/main/resources/
    META-INF/neoforge.mods.toml     Mod metadata and dependencies
    kubejs.plugins.txt              Registers the KubeJS plugin
```

## Links

- Author: PalsFreniers
- Issues: https://github.com/PalsFreniers/JadeJS/issues
