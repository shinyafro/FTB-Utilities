package com.feed_the_beast.ftbu.api;

import com.feed_the_beast.ftbl.api.IForgePlayer;
import com.feed_the_beast.ftbl.lib.util.StringUtils;
import net.minecraft.stats.StatBase;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author LatvianModder
 */
public class Leaderboard extends IForgeRegistryEntry.Impl<Leaderboard>
{
	private final ITextComponent title;
	private final Function<IForgePlayer, ITextComponent> playerToValue;
	private final Comparator<IForgePlayer> comparator;
	private final Predicate<IForgePlayer> validValue;

	public Leaderboard(ITextComponent t, Function<IForgePlayer, ITextComponent> v, Comparator<IForgePlayer> c, Predicate<IForgePlayer> vv)
	{
		title = t;
		playerToValue = v;
		comparator = c.thenComparing((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
		validValue = vv;
	}

	public final ITextComponent getTitle()
	{
		return title;
	}

	public final Comparator<IForgePlayer> getComparator()
	{
		return comparator;
	}

	public final ITextComponent createValue(IForgePlayer player)
	{
		return playerToValue.apply(player);
	}

	public final boolean hasValidValue(IForgePlayer player)
	{
		return validValue.test(player);
	}

	public static class FromStat extends Leaderboard
	{
		public FromStat(ITextComponent t, StatBase statBase, boolean from0to1)
		{
			super(t,
					player -> new TextComponentString(statBase.format(player.stats().readStat(statBase))),
					(o1, o2) -> {
						int i = Integer.compare(o1.stats().readStat(statBase), o2.stats().readStat(statBase));
						return from0to1 ? i : -i;
					},
					player -> player.stats().readStat(statBase) > 0);
		}

		public FromStat(StatBase statBase, boolean from0to1)
		{
			this(StringUtils.color(statBase.getStatName(), null), statBase, from0to1);
		}
	}
}