package me.wildn00b.timegivesyoumoney.listener;

import me.wildn00b.timegivesyoumoney.TimeGivesYouMoney;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockListener implements Listener {

  private final TimeGivesYouMoney tgym;

  public BlockListener(TimeGivesYouMoney tgym) {
    this.tgym = tgym;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onBlockBreak(BlockBreakEvent event) {
    Sign sign;

    if (!event.isCancelled()
        && event.getBlock() != null
        && (event.getBlock().getType() == Material.OAK_WALL_SIGN || event.
            getBlock().getType() == Material.OAK_SIGN|| event.getBlock().getType() == Material.ACACIA_SIGN|| event.
            getBlock().getType() == Material.ACACIA_WALL_SIGN|| event.getBlock().getType() == Material.BIRCH_SIGN|| event.
            getBlock().getType() == Material.BIRCH_WALL_SIGN|| event.getBlock().getType() == Material.SPRUCE_WALL_SIGN|| event.
            getBlock().getType() == Material.SPRUCE_WALL_SIGN|| event.getBlock().getType() == Material.DARK_OAK_SIGN|| event.
            getBlock().getType() == Material.DARK_OAK_WALL_SIGN|| event.getBlock().getType() == Material.JUNGLE_SIGN|| event.
            getBlock().getType() == Material.JUNGLE_WALL_SIGN)) {
      sign = (Sign) event.getBlock().getState();

      if (sign.getLine(0).equals(
          "" + ChatColor.GRAY + ChatColor.BOLD + "[" + ChatColor.YELLOW + "ATM"
              + ChatColor.GRAY + "]")
          && !tgym.Vault.HasPermissions(event.getPlayer(), "tgym.atm.create"))
        event.setCancelled(true);
    }
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onBlockDamage(BlockDamageEvent event) {
    Sign sign;
    if (!event.isCancelled()
        && event.getBlock() != null
        && (event.getBlock().getType() == Material.OAK_WALL_SIGN || event.
            getBlock().getType() == Material.OAK_SIGN|| event.getBlock().getType() == Material.ACACIA_SIGN|| event.
            getBlock().getType() == Material.ACACIA_WALL_SIGN|| event.getBlock().getType() == Material.BIRCH_SIGN|| event.
            getBlock().getType() == Material.BIRCH_WALL_SIGN|| event.getBlock().getType() == Material.SPRUCE_WALL_SIGN|| event.
            getBlock().getType() == Material.SPRUCE_WALL_SIGN|| event.getBlock().getType() == Material.DARK_OAK_SIGN|| event.
            getBlock().getType() == Material.DARK_OAK_WALL_SIGN|| event.getBlock().getType() == Material.JUNGLE_SIGN|| event.
            getBlock().getType() == Material.JUNGLE_WALL_SIGN))  {
      sign = (Sign) event.getBlock().getState();

      if (sign.getLine(0).equals(
          "" + ChatColor.GRAY + ChatColor.BOLD + "[" + ChatColor.YELLOW + "ATM"
              + ChatColor.GRAY + "]")
          && !tgym.Vault.HasPermissions(event.getPlayer(), "tgym.atm.create"))
        event.setCancelled(true);
    }
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerInteract(PlayerInteractEvent event) {
    final Block block = event.getClickedBlock();

    if (!event.isCancelled() && block != null)
      if (event.getAction() == Action.LEFT_CLICK_BLOCK
          || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        if (block.getType() == Material.STONE_BUTTON
            || block.getType() == Material.OAK_BUTTON
            || block.getType() == Material.LEVER) {
          for (final BlockFace bf : BlockFace.values())
            if (testSign(event.getPlayer(), block.getRelative(bf)))
              return;
        } else if (block.getType() == Material.OAK_WALL_SIGN
            || block.getType() == Material.OAK_SIGN||block.getType() == Material.ACACIA_SIGN
                || block.getType() == Material.ACACIA_WALL_SIGN
                ||block.getType() == Material.BIRCH_SIGN
                || block.getType() == Material.BIRCH_WALL_SIGN
                ||block.getType() == Material.SPRUCE_WALL_SIGN
                || block.getType() == Material.SPRUCE_SIGN
                ||block.getType() == Material.DARK_OAK_SIGN
                || block.getType() == Material.DARK_OAK_WALL_SIGN
                ||block.getType() == Material.JUNGLE_SIGN
                || block.getType() == Material.JUNGLE_WALL_SIGN)
          testSign(event.getPlayer(), block);
      } else if (event.getAction() == Action.PHYSICAL
          && (block.getType() == Material.STONE_PRESSURE_PLATE || block.getType() == Material.OAK_PRESSURE_PLATE))
        for (final BlockFace bf : BlockFace.values())
          if (testSign(event.getPlayer(), block.getRelative(bf)))
            return;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onSignChange(SignChangeEvent event) {
    if (event.getLine(0).equalsIgnoreCase("[ATM]")
        && tgym.Vault.HasPermissions(event.getPlayer(), "tgym.atm.create"))
      event.setLine(0, "" + ChatColor.GRAY + ChatColor.BOLD + "["
          + ChatColor.YELLOW + "ATM" + ChatColor.GRAY + "]");
  }

  private boolean testSign(Player player, Block block) {
    Sign sign = null;
    if (block.getType() == Material.OAK_WALL_SIGN
            || block.getType() == Material.OAK_SIGN||block.getType() == Material.ACACIA_SIGN
            || block.getType() == Material.ACACIA_WALL_SIGN
            ||block.getType() == Material.BIRCH_SIGN
            || block.getType() == Material.BIRCH_WALL_SIGN
            ||block.getType() == Material.SPRUCE_WALL_SIGN
            || block.getType() == Material.SPRUCE_SIGN
            ||block.getType() == Material.DARK_OAK_SIGN
            || block.getType() == Material.DARK_OAK_WALL_SIGN
            ||block.getType() == Material.JUNGLE_SIGN
            || block.getType() == Material.JUNGLE_WALL_SIGN)
      sign = (Sign) block.getState();

    if (sign != null)
      if (sign.getLine(0).equals(
          "" + ChatColor.GRAY + ChatColor.BOLD + "[" + ChatColor.YELLOW + "ATM"
              + ChatColor.GRAY + "]")) {
        if (tgym.Vault.HasPermissions(player, "tgym.atm.use")) {
          final double result = tgym.Bank.CashOut(player.getName());
          final Object val = tgym.Settings._(
              "Group."
                  + tgym.Vault.GetGroup(tgym.getServer().getPlayer(
                      player.getName())) + ".MoneyPerMinute", (double) -1);

          double mps = -1;
          if (val instanceof Integer)
            mps = ((Integer) val).doubleValue();
          else
            mps = (Double) val;
          if (mps == -1)
            mps = 1;

          player.sendMessage(ChatColor.YELLOW
              + "[TimeGivesYouMoney] "
              + ChatColor.GOLD
              + tgym.Lang
                  ._("Command.Cashout.Success.Self")
                  .replaceAll("%MONEY%",
                      result + tgym.Vault.GetEconomy().currencyNamePlural())
                  .replaceAll("%TIME%", (result / mps) + ""));
        }
        return true;
      }
    return false;
  }
}
