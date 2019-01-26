package gregtechmod.api.util;

import buildcraft.api.transport.IPipeTile;
import cofh.api.transport.IItemConduit;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.xcompwiz.mystcraft.world.WorldProviderMyst;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.events.GT_ScannerEvent;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IDebugableBlock;
import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.interfaces.IUpgradableMachine;
import gregtechmod.api.items.GT_EnergyArmor_Item;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_PlayedSound;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorChamber;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.RecipeOutput;
import ic2.api.tile.IEnergyStorage;
import ic2.api.tile.IWrenchable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet41EntityEffect;
import net.minecraft.network.packet.Packet9Respawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import twilightforest.world.WorldProviderTwilightForest;

public class GT_Utility {

   public static volatile int VERSION = 408;
   public static final List<Character> sNumberedCharacters = Arrays.asList(new Character[]{Character.valueOf('0'), Character.valueOf('1'), Character.valueOf('2'), Character.valueOf('3'), Character.valueOf('4'), Character.valueOf('5'), Character.valueOf('6'), Character.valueOf('7'), Character.valueOf('8'), Character.valueOf('9')});
   public static final List<Character> sUpperCasedCharacters = Arrays.asList(new Character[]{Character.valueOf('A'), Character.valueOf('B'), Character.valueOf('C'), Character.valueOf('D'), Character.valueOf('E'), Character.valueOf('F'), Character.valueOf('G'), Character.valueOf('H'), Character.valueOf('I'), Character.valueOf('J'), Character.valueOf('K'), Character.valueOf('L'), Character.valueOf('M'), Character.valueOf('N'), Character.valueOf('O'), Character.valueOf('P'), Character.valueOf('Q'), Character.valueOf('R'), Character.valueOf('S'), Character.valueOf('T'), Character.valueOf('U'), Character.valueOf('V'), Character.valueOf('W'), Character.valueOf('X'), Character.valueOf('Y'), Character.valueOf('Z')});
   public static final List<Character> sLowerCasedCharacters = Arrays.asList(new Character[]{Character.valueOf('a'), Character.valueOf('b'), Character.valueOf('c'), Character.valueOf('d'), Character.valueOf('e'), Character.valueOf('f'), Character.valueOf('g'), Character.valueOf('h'), Character.valueOf('i'), Character.valueOf('j'), Character.valueOf('k'), Character.valueOf('l'), Character.valueOf('m'), Character.valueOf('n'), Character.valueOf('o'), Character.valueOf('p'), Character.valueOf('q'), Character.valueOf('r'), Character.valueOf('s'), Character.valueOf('t'), Character.valueOf('u'), Character.valueOf('v'), Character.valueOf('w'), Character.valueOf('x'), Character.valueOf('y'), Character.valueOf('z')});
   public static boolean TE_CHECK = false;
   public static boolean BC_CHECK = false;
   public static boolean CHECK_ALL = true;
   private static int sBookCount = 0;
   public static Map<GT_PlayedSound, Integer> sPlayedSoundMap = new HashMap();


   public static Field getPublicField(Object aObject, String aField) {
      Field rField = null;

      try {
         rField = aObject.getClass().getDeclaredField(aField);
      } catch (Throwable var4) {
         ;
      }

      return rField;
   }

   public static Field getField(Object aObject, String aField) {
      Field rField = null;

      try {
         rField = aObject.getClass().getDeclaredField(aField);
         rField.setAccessible(true);
      } catch (Throwable var4) {
         ;
      }

      return rField;
   }

   public static Field getField(Class aObject, String aField) {
      Field rField = null;

      try {
         rField = aObject.getDeclaredField(aField);
         rField.setAccessible(true);
      } catch (Throwable var4) {
         ;
      }

      return rField;
   }

   public static Method getMethod(Class aObject, String aMethod, Class<?> ... aParameterTypes) {
      Method rMethod = null;

      try {
         rMethod = aObject.getMethod(aMethod, aParameterTypes);
         rMethod.setAccessible(true);
      } catch (Throwable var5) {
         ;
      }

      return rMethod;
   }

   public static Method getMethod(Object aObject, String aMethod, Class<?> ... aParameterTypes) {
      Method rMethod = null;

      try {
         rMethod = aObject.getClass().getMethod(aMethod, aParameterTypes);
         rMethod.setAccessible(true);
      } catch (Throwable var5) {
         ;
      }

      return rMethod;
   }

   public static Field getField(Object aObject, String aField, boolean aPrivate, boolean aLogErrors) {
      try {
         Field e = aObject instanceof Class?((Class)aObject).getDeclaredField(aField):(aObject instanceof String?Class.forName((String)aObject).getDeclaredField(aField):aObject.getClass().getDeclaredField(aField));
         if(aPrivate) {
            e.setAccessible(true);
         }

         return e;
      } catch (Throwable var5) {
         if(aLogErrors) {
            var5.printStackTrace(GT_Log.err);
         }

         return null;
      }
   }

   public static Object getFieldContent(Object aObject, String aField, boolean aPrivate, boolean aLogErrors) {
      try {
         Field e = aObject instanceof Class?((Class)aObject).getDeclaredField(aField):(aObject instanceof String?Class.forName((String)aObject).getDeclaredField(aField):aObject.getClass().getDeclaredField(aField));
         if(aPrivate) {
            e.setAccessible(true);
         }

         return e.get(!(aObject instanceof Class) && !(aObject instanceof String)?aObject:null);
      } catch (Throwable var5) {
         if(aLogErrors) {
            var5.printStackTrace(GT_Log.err);
         }

         return null;
      }
   }

   public static Object callPublicMethod(Object aObject, String aMethod, Object ... aParameters) {
      return callMethod(aObject, aMethod, false, false, true, aParameters);
   }

   public static Object callPrivateMethod(Object aObject, String aMethod, Object ... aParameters) {
      return callMethod(aObject, aMethod, true, false, true, aParameters);
   }

   public static Object callMethod(Object aObject, String aMethod, boolean aPrivate, boolean aUseUpperCasedDataTypes, boolean aLogErrors, Object ... aParameters) {
      try {
         Class[] e = new Class[aParameters.length];

         for(byte tMethod = 0; tMethod < aParameters.length; ++tMethod) {
            if(aParameters[tMethod] instanceof Class) {
               e[tMethod] = (Class)aParameters[tMethod];
               aParameters[tMethod] = null;
            } else {
               e[tMethod] = aParameters[tMethod].getClass();
            }

            if(!aUseUpperCasedDataTypes) {
               if(e[tMethod] == Boolean.class) {
                  e[tMethod] = Boolean.TYPE;
               } else if(e[tMethod] == Byte.class) {
                  e[tMethod] = Byte.TYPE;
               } else if(e[tMethod] == Short.class) {
                  e[tMethod] = Short.TYPE;
               } else if(e[tMethod] == Integer.class) {
                  e[tMethod] = Integer.TYPE;
               } else if(e[tMethod] == Long.class) {
                  e[tMethod] = Long.TYPE;
               } else if(e[tMethod] == Float.class) {
                  e[tMethod] = Float.TYPE;
               } else if(e[tMethod] == Double.class) {
                  e[tMethod] = Double.TYPE;
               }
            }
         }

         Method var9 = aObject instanceof Class?((Class)aObject).getMethod(aMethod, e):aObject.getClass().getMethod(aMethod, e);
         if(aPrivate) {
            var9.setAccessible(true);
         }

         return var9.invoke(aObject, aParameters);
      } catch (Throwable var8) {
         if(aLogErrors) {
            var8.printStackTrace(GT_Log.err);
         }

         return null;
      }
   }

   public static Object callConstructor(String aClass, int aConstructorIndex, Object aReplacementObject, boolean aLogErrors, Object ... aParameters) {
      if(aConstructorIndex < 0) {
         try {
            Constructor[] e = Class.forName(aClass).getConstructors();
            int len$ = e.length;
            int i$ = 0;

            while(i$ < len$) {
               Constructor tConstructor = e[i$];

               try {
                  return tConstructor.newInstance(aParameters);
               } catch (Throwable var10) {
                  ++i$;
               }
            }
         } catch (Throwable var11) {
            if(aLogErrors) {
               var11.printStackTrace(GT_Log.err);
            }
         }
      } else {
         try {
            return Class.forName(aClass).getConstructors()[aConstructorIndex].newInstance(aParameters);
         } catch (Throwable var12) {
            if(aLogErrors) {
               var12.printStackTrace(GT_Log.err);
            }
         }
      }

      return aReplacementObject;
   }

   public static String capitalizeString(String aString) {
      return aString != null && aString.length() > 0?aString.substring(0, 1).toUpperCase() + aString.substring(1):"";
   }

   public static boolean getPotion(EntityLivingBase aPlayer, int aPotionIndex) {
      try {
         Field e = null;
         Field[] var3 = EntityLiving.class.getDeclaredFields();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Field var6 = var3[var5];
            if(var6.getType() == HashMap.class) {
               e = var6;
               var6.setAccessible(true);
               break;
            }
         }

         if(e != null) {
            return ((HashMap)e.get(aPlayer)).get(Integer.valueOf(aPotionIndex)) != null;
         }
      } catch (Throwable var7) {
         if(GregTech_API.DEBUG_MODE) {
            var7.printStackTrace(GT_Log.err);
         }
      }

      return false;
   }

   public static String getClassName(Object aObject) {
      return aObject == null?"null":aObject.getClass().getName().substring(aObject.getClass().getName().lastIndexOf(".") + 1);
   }

   public static void removePotion(EntityLivingBase aPlayer, int aPotionIndex) {
      try {
         Field e = null;
         Field[] var3 = EntityLiving.class.getDeclaredFields();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Field var6 = var3[var5];
            if(var6.getType() == HashMap.class) {
               e = var6;
               var6.setAccessible(true);
               break;
            }
         }

         if(e != null) {
            ((HashMap)e.get(aPlayer)).remove(Integer.valueOf(aPotionIndex));
         }
      } catch (Throwable var7) {
         if(GregTech_API.DEBUG_MODE) {
            var7.printStackTrace(GT_Log.err);
         }
      }

   }

   public static boolean getFullInvisibility(EntityPlayer aPlayer) {
      try {
         if(aPlayer.func_82150_aj()) {
            for(int e = 0; e < 4; ++e) {
               if(aPlayer.field_71071_by.field_70460_b[e] != null && aPlayer.field_71071_by.field_70460_b[e].func_77973_b() instanceof GT_EnergyArmor_Item && (((GT_EnergyArmor_Item)aPlayer.field_71071_by.field_70460_b[e].func_77973_b()).mSpecials & 512) != 0 && GT_ModHandler.canUseElectricItem(aPlayer.field_71071_by.field_70460_b[e], 10000)) {
                  return true;
               }
            }
         }
      } catch (Throwable var2) {
         if(GregTech_API.DEBUG_MODE) {
            var2.printStackTrace(GT_Log.err);
         }
      }

      return false;
   }

   public static ItemStack suckOneItemStackAt(World aWorld, double aX, double aY, double aZ, double aL, double aH, double aW) {
      Iterator i$ = ((ArrayList)aWorld.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(aX, aY, aZ, aX + aL, aY + aH, aZ + aW))).iterator();

      EntityItem tItem;
      do {
         if(!i$.hasNext()) {
            return null;
         }

         tItem = (EntityItem)i$.next();
      } while(tItem.field_70128_L);

      aWorld.func_72900_e(tItem);
      tItem.func_70106_y();
      return tItem.func_92059_d();
   }

   public static byte getOppositeSide(int aSide) {
      return (byte)ForgeDirection.getOrientation(aSide).getOpposite().ordinal();
   }

   public static byte getTier(int aValue) {
      byte i = -1;

      do {
         ++i;
         if(i >= GregTech_API.VOLTAGES.length) {
            return i;
         }
      } while(aValue > GregTech_API.VOLTAGES[i]);

      return i;
   }

   public static void sendChatToPlayer(EntityPlayer aPlayer, String aChatMessage) {
      if(aPlayer != null && aPlayer instanceof EntityPlayerMP && aChatMessage != null) {
         aPlayer.func_71035_c(aChatMessage);
      }

   }

   public static void checkAvailabilities() {
      if(CHECK_ALL) {
         Class e;
         try {
            e = IItemConduit.class;
            e.getCanonicalName();
            TE_CHECK = true;
         } catch (Throwable var2) {
            ;
         }

         try {
            e = IPipeTile.class;
            e.getCanonicalName();
            BC_CHECK = true;
         } catch (Throwable var1) {
            ;
         }

         CHECK_ALL = false;
      }

   }

   public static boolean isConnectableNonInventoryPipe(Object aTileEntity, int aSide) {
      if(aTileEntity == null) {
         return false;
      } else {
         checkAvailabilities();
         return TE_CHECK && aTileEntity instanceof IItemConduit?true:(BC_CHECK && aTileEntity instanceof IPipeTile?((IPipeTile)aTileEntity).isPipeConnected(ForgeDirection.getOrientation(aSide)):false);
      }
   }

   public static byte moveStackIntoPipe(IInventory aTileEntity1, Object aTileEntity2, int[] aGrabSlots, int aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
      if(aTileEntity1 != null && aMaxTargetStackSize > 0 && aMinTargetStackSize > 0 && aMinTargetStackSize <= aMaxTargetStackSize && aMaxMoveAtOnce > 0 && aMinMoveAtOnce <= aMaxMoveAtOnce) {
         if(aTileEntity2 != null) {
            checkAvailabilities();
            int var18;
            ItemStack var19;
            if(TE_CHECK && aTileEntity2 instanceof IItemConduit) {
               for(var18 = 0; var18 < aGrabSlots.length; ++var18) {
                  if(listContainsItem(aFilter, aTileEntity1.func_70301_a(aGrabSlots[var18]), true, aInvertFilter) && isAllowedToTakeFromSlot(aTileEntity1, aGrabSlots[var18], (byte)aGrabFrom, aTileEntity1.func_70301_a(aGrabSlots[var18])) && Math.max(aMinMoveAtOnce, aMinTargetStackSize) <= aTileEntity1.func_70301_a(aGrabSlots[var18]).field_77994_a) {
                     var19 = copyAmount((long)Math.min(aTileEntity1.func_70301_a(aGrabSlots[var18]).field_77994_a, Math.min(aMaxMoveAtOnce, aMaxTargetStackSize)), new Object[]{aTileEntity1.func_70301_a(aGrabSlots[var18])});
                     ItemStack var21 = ((IItemConduit)aTileEntity2).insertItem(ForgeDirection.getOrientation(aPutTo), copy(new Object[]{var19}), false);
                     byte var22 = (byte)(var19.field_77994_a - (var21 == null?0:var21.field_77994_a));
                     if(var22 >= 1) {
                        aTileEntity1.func_70298_a(aGrabSlots[var18], var22);
                        aTileEntity1.func_70296_d();
                        return var22;
                     }
                  }
               }

               return (byte)0;
            }

            if(BC_CHECK && aTileEntity2 instanceof IPipeTile) {
               for(var18 = 0; var18 < aGrabSlots.length; ++var18) {
                  if(listContainsItem(aFilter, aTileEntity1.func_70301_a(aGrabSlots[var18]), true, aInvertFilter) && isAllowedToTakeFromSlot(aTileEntity1, aGrabSlots[var18], (byte)aGrabFrom, aTileEntity1.func_70301_a(aGrabSlots[var18])) && Math.max(aMinMoveAtOnce, aMinTargetStackSize) <= aTileEntity1.func_70301_a(aGrabSlots[var18]).field_77994_a) {
                     var19 = copyAmount((long)Math.min(aTileEntity1.func_70301_a(aGrabSlots[var18]).field_77994_a, Math.min(aMaxMoveAtOnce, aMaxTargetStackSize)), new Object[]{aTileEntity1.func_70301_a(aGrabSlots[var18])});
                     byte var20 = (byte)((IPipeTile)aTileEntity2).injectItem(copy(new Object[]{var19}), false, ForgeDirection.getOrientation(aPutTo));
                     if(var20 >= Math.max(aMinMoveAtOnce, aMinTargetStackSize)) {
                        var20 = (byte)((IPipeTile)aTileEntity2).injectItem(copyAmount((long)var20, new Object[]{var19}), true, ForgeDirection.getOrientation(aPutTo));
                        aTileEntity1.func_70298_a(aGrabSlots[var18], var20);
                        aTileEntity1.func_70296_d();
                        return var20;
                     }
                  }
               }

               return (byte)0;
            }
         }

         ForgeDirection tDirection = ForgeDirection.getOrientation(aGrabFrom);
         if(aTileEntity1 instanceof TileEntity && tDirection != ForgeDirection.UNKNOWN && tDirection.getOpposite() == ForgeDirection.getOrientation(aPutTo)) {
            int tX = ((TileEntity)aTileEntity1).field_70329_l + tDirection.offsetX;
            int tY = ((TileEntity)aTileEntity1).field_70330_m + tDirection.offsetY;
            int tZ = ((TileEntity)aTileEntity1).field_70327_n + tDirection.offsetZ;
            if(!hasBlockHitBox(((TileEntity)aTileEntity1).field_70331_k, tX, tY, tZ)) {
               for(int i = 0; i < aGrabSlots.length; ++i) {
                  if(listContainsItem(aFilter, aTileEntity1.func_70301_a(aGrabSlots[i]), true, aInvertFilter) && isAllowedToTakeFromSlot(aTileEntity1, aGrabSlots[i], (byte)aGrabFrom, aTileEntity1.func_70301_a(aGrabSlots[i])) && Math.max(aMinMoveAtOnce, aMinTargetStackSize) <= aTileEntity1.func_70301_a(aGrabSlots[i]).field_77994_a) {
                     ItemStack tStack = copyAmount((long)Math.min(aTileEntity1.func_70301_a(aGrabSlots[i]).field_77994_a, Math.min(aMaxMoveAtOnce, aMaxTargetStackSize)), new Object[]{aTileEntity1.func_70301_a(aGrabSlots[i])});
                     EntityItem tEntity = new EntityItem(((TileEntity)aTileEntity1).field_70331_k, (double)tX + 0.5D, (double)tY + 0.5D, (double)tZ + 0.5D, tStack);
                     tEntity.field_70159_w = tEntity.field_70181_x = tEntity.field_70179_y = 0.0D;
                     ((TileEntity)aTileEntity1).field_70331_k.func_72838_d(tEntity);
                     aTileEntity1.func_70298_a(aGrabSlots[i], tStack.field_77994_a);
                     aTileEntity1.func_70296_d();
                     return (byte)tStack.field_77994_a;
                  }
               }
            }
         }

         return (byte)0;
      } else {
         return (byte)0;
      }
   }

   public static byte moveStackFromSlotAToSlotB(IInventory aTileEntity1, IInventory aTileEntity2, int aGrabFrom, int aPutTo, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
      if(aTileEntity1 != null && aTileEntity2 != null && aMaxTargetStackSize > 0 && aMinTargetStackSize > 0 && aMinTargetStackSize <= aMaxTargetStackSize && aMaxMoveAtOnce > 0 && aMinMoveAtOnce <= aMaxMoveAtOnce) {
         ItemStack tStack1 = aTileEntity1.func_70301_a(aGrabFrom);
         ItemStack tStack2 = aTileEntity2.func_70301_a(aPutTo);
         ItemStack tStack3 = null;
         if(tStack1 != null) {
            if(tStack2 != null && !areStacksEqual(tStack1, tStack2)) {
               return (byte)0;
            }

            tStack3 = copy(new Object[]{tStack1});
            aMaxTargetStackSize = (byte)Math.min(aMaxTargetStackSize, Math.min(tStack3.func_77976_d(), Math.min(tStack2 == null?Integer.MAX_VALUE:tStack2.func_77976_d(), aTileEntity2.func_70297_j_())));
            tStack3.field_77994_a = Math.min(tStack3.field_77994_a, aMaxTargetStackSize - (tStack2 == null?0:tStack2.field_77994_a));
            if(tStack3.field_77994_a > aMaxMoveAtOnce) {
               tStack3.field_77994_a = aMaxMoveAtOnce;
            }

            if(tStack3.field_77994_a + (tStack2 == null?0:tStack2.field_77994_a) >= aMinTargetStackSize && tStack3.field_77994_a >= aMinMoveAtOnce) {
               tStack3 = aTileEntity1.func_70298_a(aGrabFrom, tStack3.field_77994_a);
               aTileEntity1.func_70296_d();
               if(tStack3 != null) {
                  if(tStack2 == null) {
                     aTileEntity2.func_70299_a(aPutTo, copy(new Object[]{tStack3}));
                     aTileEntity2.func_70296_d();
                  } else {
                     tStack2.field_77994_a += tStack3.field_77994_a;
                  }

                  return (byte)tStack3.field_77994_a;
               }
            }
         }

         return (byte)0;
      } else {
         return (byte)0;
      }
   }

   public static boolean isAllowedToTakeFromSlot(IInventory aTileEntity, int aSlot, byte aSide, ItemStack aStack) {
      return ForgeDirection.getOrientation(aSide) != ForgeDirection.UNKNOWN?(aTileEntity instanceof ISidedInventory?((ISidedInventory)aTileEntity).func_102008_b(aSlot, aStack, aSide):true):isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)0, aStack) || isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)1, aStack) || isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)2, aStack) || isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)3, aStack) || isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)4, aStack) || isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)5, aStack);
   }

   public static boolean isAllowedToPutIntoSlot(IInventory aTileEntity, int aSlot, byte aSide, ItemStack aStack) {
      return ForgeDirection.getOrientation(aSide) != ForgeDirection.UNKNOWN?(aTileEntity instanceof ISidedInventory && !((ISidedInventory)aTileEntity).func_102007_a(aSlot, aStack, aSide)?false:aTileEntity.func_94041_b(aSlot, aStack)):isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)0, aStack) || isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)1, aStack) || isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)2, aStack) || isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)3, aStack) || isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)4, aStack) || isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)5, aStack);
   }

   public static byte moveOneItemStack(Object aTileEntity1, Object aTileEntity2, byte aGrabFrom, byte aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
      return aTileEntity1 != null && aTileEntity1 instanceof IInventory?moveOneItemStack((IInventory)aTileEntity1, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, true):0;
   }

   private static byte moveOneItemStack(IInventory aTileEntity1, Object aTileEntity2, byte aGrabFrom, byte aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce, boolean aDoCheckChests) {
      if(aTileEntity1 != null && aMaxTargetStackSize > 0 && aMinTargetStackSize > 0 && aMaxMoveAtOnce > 0 && aMinTargetStackSize <= aMaxTargetStackSize && aMinMoveAtOnce <= aMaxMoveAtOnce) {
         int[] tGrabSlots = null;
         if(aTileEntity1 instanceof ISidedInventory) {
            tGrabSlots = ((ISidedInventory)aTileEntity1).func_94128_d(aGrabFrom);
         }

         if(tGrabSlots == null) {
            tGrabSlots = new int[aTileEntity1.func_70302_i_()];

            for(int tPutSlots = 0; tPutSlots < tGrabSlots.length; tGrabSlots[tPutSlots] = tPutSlots++) {
               ;
            }
         }

         if(aTileEntity2 != null && aTileEntity2 instanceof IInventory) {
            int[] var16 = null;
            if(aTileEntity2 instanceof ISidedInventory) {
               var16 = ((ISidedInventory)aTileEntity2).func_94128_d(aPutTo);
            }

            int tTileEntity2;
            if(var16 == null) {
               var16 = new int[((IInventory)aTileEntity2).func_70302_i_()];

               for(tTileEntity2 = 0; tTileEntity2 < var16.length; var16[tTileEntity2] = tTileEntity2++) {
                  ;
               }
            }

            for(tTileEntity2 = 0; tTileEntity2 < tGrabSlots.length; ++tTileEntity2) {
               for(int tAmount = 0; tAmount < var16.length; ++tAmount) {
                  if(listContainsItem(aFilter, aTileEntity1.func_70301_a(tGrabSlots[tTileEntity2]), true, aInvertFilter) && isAllowedToTakeFromSlot(aTileEntity1, tGrabSlots[tTileEntity2], aGrabFrom, aTileEntity1.func_70301_a(tGrabSlots[tTileEntity2])) && isAllowedToPutIntoSlot((IInventory)aTileEntity2, var16[tAmount], aPutTo, aTileEntity1.func_70301_a(tGrabSlots[tTileEntity2]))) {
                     byte tMovedItemCount = moveStackFromSlotAToSlotB(aTileEntity1, (IInventory)aTileEntity2, tGrabSlots[tTileEntity2], var16[tAmount], aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                     if(tMovedItemCount > 0) {
                        return tMovedItemCount;
                     }
                  }
               }
            }

            TileEntityChest var17;
            byte var18;
            if(aDoCheckChests && aTileEntity1 instanceof TileEntityChest) {
               var17 = (TileEntityChest)aTileEntity1;
               if(var17.field_70425_a) {
                  var18 = 0;
                  if(var17.field_70421_d != null) {
                     var18 = moveOneItemStack(var17.field_70421_d, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
                  } else if(var17.field_70423_b != null) {
                     var18 = moveOneItemStack(var17.field_70423_b, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
                  } else if(var17.field_70424_c != null) {
                     var18 = moveOneItemStack(var17.field_70424_c, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
                  } else if(var17.field_70422_e != null) {
                     var18 = moveOneItemStack(var17.field_70422_e, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
                  }

                  if(var18 != 0) {
                     return var18;
                  }
               }
            }

            if(aDoCheckChests && aTileEntity2 instanceof TileEntityChest) {
               var17 = (TileEntityChest)aTileEntity2;
               if(var17.field_70425_a) {
                  var18 = 0;
                  if(var17.field_70421_d != null) {
                     var18 = moveOneItemStack(aTileEntity1, var17.field_70421_d, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
                  } else if(var17.field_70423_b != null) {
                     var18 = moveOneItemStack(aTileEntity1, var17.field_70423_b, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
                  } else if(var17.field_70424_c != null) {
                     var18 = moveOneItemStack(aTileEntity1, var17.field_70424_c, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
                  } else if(var17.field_70422_e != null) {
                     var18 = moveOneItemStack(aTileEntity1, var17.field_70422_e, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
                  }

                  if(var18 != 0) {
                     return var18;
                  }
               }
            }
         }

         moveStackIntoPipe(aTileEntity1, aTileEntity2, tGrabSlots, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
         return (byte)0;
      } else {
         return (byte)0;
      }
   }

   public static byte moveOneItemStackIntoSlot(Object aTileEntity1, Object aTileEntity2, byte aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
      if(aTileEntity1 != null && aTileEntity1 instanceof IInventory && aPutTo >= 0 && aMaxTargetStackSize > 0 && aMinTargetStackSize > 0 && aMaxMoveAtOnce > 0 && aMinTargetStackSize <= aMaxTargetStackSize && aMinMoveAtOnce <= aMaxMoveAtOnce) {
         int[] tGrabSlots = null;
         if(aTileEntity1 instanceof ISidedInventory) {
            tGrabSlots = ((ISidedInventory)aTileEntity1).func_94128_d(aGrabFrom);
         }

         int i;
         if(tGrabSlots == null) {
            tGrabSlots = new int[((IInventory)aTileEntity1).func_70302_i_()];

            for(i = 0; i < tGrabSlots.length; tGrabSlots[i] = i++) {
               ;
            }
         }

         if(aTileEntity2 != null && aTileEntity2 instanceof IInventory) {
            for(i = 0; i < tGrabSlots.length; ++i) {
               if(listContainsItem(aFilter, ((IInventory)aTileEntity1).func_70301_a(tGrabSlots[i]), true, aInvertFilter) && isAllowedToTakeFromSlot((IInventory)aTileEntity1, tGrabSlots[i], aGrabFrom, ((IInventory)aTileEntity1).func_70301_a(tGrabSlots[i])) && isAllowedToPutIntoSlot((IInventory)aTileEntity2, aPutTo, (byte)6, ((IInventory)aTileEntity1).func_70301_a(tGrabSlots[i]))) {
                  byte tMovedItemCount = moveStackFromSlotAToSlotB((IInventory)aTileEntity1, (IInventory)aTileEntity2, tGrabSlots[i], aPutTo, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
                  if(tMovedItemCount > 0) {
                     return tMovedItemCount;
                  }
               }
            }
         }

         moveStackIntoPipe((IInventory)aTileEntity1, aTileEntity2, tGrabSlots, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
         return (byte)0;
      } else {
         return (byte)0;
      }
   }

   public static byte moveFromSlotToSlot(IInventory aTileEntity1, IInventory aTileEntity2, int aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
      if(aTileEntity1 != null && aTileEntity2 != null && aGrabFrom >= 0 && aPutTo >= 0 && aMaxTargetStackSize > 0 && aMinTargetStackSize > 0 && aMaxMoveAtOnce > 0 && aMinTargetStackSize <= aMaxTargetStackSize && aMinMoveAtOnce <= aMaxMoveAtOnce) {
         if(listContainsItem(aFilter, aTileEntity1.func_70301_a(aGrabFrom), true, aInvertFilter) && isAllowedToTakeFromSlot(aTileEntity1, aGrabFrom, (byte)6, aTileEntity1.func_70301_a(aGrabFrom)) && isAllowedToPutIntoSlot(aTileEntity2, aPutTo, (byte)6, aTileEntity1.func_70301_a(aGrabFrom))) {
            byte tMovedItemCount = moveStackFromSlotAToSlotB(aTileEntity1, aTileEntity2, aGrabFrom, aPutTo, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
            if(tMovedItemCount > 0) {
               return tMovedItemCount;
            }
         }

         return (byte)0;
      } else {
         return (byte)0;
      }
   }

   public static boolean listContainsItem(Collection<ItemStack> aList, ItemStack aStack, boolean aTrueIfListEmpty, boolean aInvertFilter) {
      if(aStack != null && aStack.field_77994_a >= 1) {
         if(aList == null) {
            return aTrueIfListEmpty;
         } else {
            while(aList.contains((Object)null)) {
               aList.remove((Object)null);
            }

            if(aList.size() < 1) {
               return aTrueIfListEmpty;
            } else {
               Iterator tIterator = aList.iterator();
               ItemStack tStack = null;

               do {
                  if(!tIterator.hasNext()) {
                     return aInvertFilter;
                  }
               } while((tStack = (ItemStack)tIterator.next()) == null || !areStacksEqual(aStack, tStack));

               return !aInvertFilter;
            }
         }
      } else {
         return false;
      }
   }

   public static boolean areStacksOrToolsEqual(ItemStack aStack1, ItemStack aStack2) {
      return aStack1 != null && aStack2 != null && aStack1.func_77973_b() == aStack2.func_77973_b()?(aStack1.func_77973_b().func_77645_m()?true:aStack1.func_77978_p() == null == (aStack2.func_77978_p() == null) && (aStack1.func_77978_p() == null || aStack1.func_77978_p().equals(aStack2.func_77978_p())) && (Item.field_77676_L.getDamage(aStack1) == Item.field_77676_L.getDamage(aStack2) || Item.field_77676_L.getDamage(aStack1) == 32767 || Item.field_77676_L.getDamage(aStack2) == 32767)):false;
   }

   public static boolean areStacksEqual(ItemStack aStack1, ItemStack aStack2) {
      return areStacksEqual(aStack1, aStack2, false);
   }

   public static boolean areStacksEqual(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {
      return aStack1 != null && aStack2 != null && aStack1.func_77973_b() == aStack2.func_77973_b() && (aIgnoreNBT || aStack1.func_77978_p() == null == (aStack2.func_77978_p() == null) && (aStack1.func_77978_p() == null || aStack1.func_77978_p().equals(aStack2.func_77978_p()))) && (Item.field_77676_L.getDamage(aStack1) == Item.field_77676_L.getDamage(aStack2) || Item.field_77676_L.getDamage(aStack1) == 32767 || Item.field_77676_L.getDamage(aStack2) == 32767);
   }

   public static boolean areUnificationsEqual(ItemStack aStack1, ItemStack aStack2) {
      return areUnificationsEqual(aStack1, aStack2, false);
   }

   public static boolean areUnificationsEqual(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {
      return areStacksEqual(GT_OreDictUnificator.get(aStack1), GT_OreDictUnificator.get(aStack2), aIgnoreNBT);
   }

   public static String getFluidName(Fluid aFluid, boolean aLocalized) {
      if(aFluid == null) {
         return "";
      } else {
         String rName = aLocalized?aFluid.getLocalizedName():aFluid.getUnlocalizedName();
         return rName.contains(".")?capitalizeString(rName.replaceAll("fluid.", "").replaceAll("tile.", "")):rName;
      }
   }

   public static String getFluidName(FluidStack aFluid, boolean aLocalized) {
      return aFluid == null?"":getFluidName(aFluid.getFluid(), aLocalized);
   }

   public static ItemStack fillFluidContainer(FluidStack aFluid, ItemStack aStack) {
      if(!isStackInvalid(aStack) && aFluid != null) {
         if(aStack.func_77973_b() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.func_77973_b()).getFluid(aStack) == null && ((IFluidContainerItem)aStack.func_77973_b()).getCapacity(aStack) <= aFluid.amount) {
            ((IFluidContainerItem)aStack.func_77973_b()).fill(aStack = copyAmount(1L, new Object[]{aStack}), aFluid, true);
            return aStack;
         } else {
            return FluidContainerRegistry.fillFluidContainer(aFluid, aStack);
         }
      } else {
         return null;
      }
   }

   public static boolean containsFluid(ItemStack aStack, FluidStack aFluid) {
      return !isStackInvalid(aStack) && aFluid != null?(aStack.func_77973_b() instanceof IFluidContainerItem?FluidStack.areFluidStackTagsEqual(aFluid, ((IFluidContainerItem)aStack.func_77973_b()).getFluid(copyAmount(1L, new Object[]{aStack}))):FluidContainerRegistry.containsFluid(aStack, aFluid)):false;
   }

   public static FluidStack getFluidForFilledItem(ItemStack aStack) {
      if(isStackInvalid(aStack)) {
         return null;
      } else if(aStack.func_77973_b() instanceof IFluidContainerItem) {
         return ((IFluidContainerItem)aStack.func_77973_b()).drain(copyAmount(1L, new Object[]{aStack}), Integer.MAX_VALUE, true);
      } else {
         FluidStack rFluid = FluidContainerRegistry.getFluidForFilledItem(aStack);
         return rFluid != null?rFluid.copy():null;
      }
   }

   public static ItemStack getContainerForFilledItem(ItemStack aStack) {
      if(isStackInvalid(aStack)) {
         return null;
      } else {
         FluidContainerData[] arr$ = FluidContainerRegistry.getRegisteredFluidContainerData();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            FluidContainerData tData = arr$[i$];
            if(areStacksEqual(tData.filledContainer, aStack)) {
               return copyAmount(1L, new Object[]{tData.emptyContainer});
            }
         }

         if(aStack.func_77973_b() instanceof IFluidContainerItem) {
            ((IFluidContainerItem)aStack.func_77973_b()).drain(aStack = copyAmount(1L, new Object[]{aStack}), Integer.MAX_VALUE, true);
            return aStack;
         } else {
            return null;
         }
      }
   }

   public static ItemStack getContainerItem(ItemStack aStack) {
      if(isStackInvalid(aStack)) {
         return null;
      } else if(aStack.func_77973_b().func_77634_r()) {
         return aStack.func_77973_b().getContainerItemStack(aStack);
      } else if(GT_Items.Cell_Empty.isStackEqual(aStack, false, true)) {
         return null;
      } else if(GT_Items.IC2_Fuel_Can_Filled.isStackEqual(aStack, false, true)) {
         return GT_Items.IC2_Fuel_Can_Empty.get(1L, new Object[0]);
      } else if(GT_Items.Fuel_Can_Plastic_Filled.isStackEqual(aStack, false, true)) {
         return GT_Items.Fuel_Can_Plastic_Empty.get(1L, new Object[0]);
      } else {
         int tCapsuleCount = GT_ModHandler.getCapsuleCellContainerCount(aStack);
         return tCapsuleCount > 0?GT_Items.Cell_Empty.get((long)tCapsuleCount, new Object[0]):(!GT_OreDictUnificator.isItemStackInstanceOf(aStack, GT_ToolDictNames.craftingToolForgeHammer) && !GT_OreDictUnificator.isItemStackInstanceOf(aStack, GT_ToolDictNames.craftingToolWireCutter)?null:copyMetaData((long)(Item.field_77676_L.getDamage(aStack) + 1), new Object[]{aStack}));
      }
   }

   public static synchronized boolean removeSimpleIC2MachineRecipe(ItemStack aInput, Map<IRecipeInput, RecipeOutput> aRecipeList, ItemStack aOutput) {
      if((!isStackInvalid(aInput) || !isStackInvalid(aOutput)) && aRecipeList != null) {
         boolean rReturn = false;
         Iterator tIterator = aRecipeList.entrySet().iterator();
         aOutput = GT_OreDictUnificator.get(aOutput);

         label38:
         while(tIterator.hasNext()) {
            Entry tEntry = (Entry)tIterator.next();
            if(aInput == null || ((IRecipeInput)tEntry.getKey()).matches(aInput)) {
               List tList = ((RecipeOutput)tEntry.getValue()).items;
               if(tList != null) {
                  Iterator i$ = tList.iterator();

                  ItemStack tOutput;
                  do {
                     if(!i$.hasNext()) {
                        continue label38;
                     }

                     tOutput = (ItemStack)i$.next();
                  } while(aOutput != null && !areStacksEqual(GT_OreDictUnificator.get(tOutput), aOutput));

                  tIterator.remove();
                  rReturn = true;
               }
            }
         }

         return rReturn;
      } else {
         return false;
      }
   }

   public static boolean addSimpleIC2MachineRecipe(ItemStack aInput, Map<IRecipeInput, RecipeOutput> aRecipeList, NBTTagCompound aNBT, Object ... aOutput) {
      if(!isStackInvalid(aInput) && aOutput.length != 0 && aRecipeList != null) {
         String tOreName = GT_OreDictUnificator.getAssociation(aInput);
         if(isStringValid(tOreName)) {
            aRecipeList.put(new RecipeInputOreDict(tOreName, aInput.field_77994_a), new RecipeOutput(aNBT, GT_OreDictUnificator.getStackArray(true, aOutput)));
         } else {
            aRecipeList.put(new RecipeInputItemStack(copy(new Object[]{aInput}), aInput.field_77994_a), new RecipeOutput(aNBT, GT_OreDictUnificator.getStackArray(true, aOutput)));
         }

         return true;
      } else {
         return false;
      }
   }

   public static ItemStack getWrittenBook(String aTitle, String aAuthor, String ... aPages) {
      if(!isStringInvalid(aTitle) && !isStringInvalid(aAuthor) && aPages.length > 0) {
         ++sBookCount;
         ItemStack rStack = new ItemStack(Item.field_77823_bG, 1);
         NBTTagCompound tNBT = new NBTTagCompound();
         tNBT.func_74778_a("title", GT_LanguageManager.addStringLocalization("Book." + aTitle + ".Name", aTitle));
         tNBT.func_74778_a("author", aAuthor);
         NBTTagList tNBTList = new NBTTagList("pages");

         for(byte i = 0; i < aPages.length; ++i) {
            aPages[i] = GT_LanguageManager.addStringLocalization("Book." + aTitle + ".Page" + (i < 10?"0" + i:Byte.valueOf(i)), aPages[i]);
            if(i >= 48) {
               GT_Log.err.println("WARNING: Too much Pages for written Book! -> " + aTitle);
               break;
            }

            if(aPages[i].length() < 256) {
               tNBTList.func_74742_a(new NBTTagString("PAGE " + i, aPages[i]));
            } else {
               GT_Log.err.println("WARNING: String for written Book too long! -> " + aPages[i]);
            }
         }

         tNBTList.func_74742_a(new NBTTagString("FINAL PAGE", "Credits to " + aAuthor + " for writing this Book. This was Book Nr. " + sBookCount + " at its creation. Gotta get \'em all!"));
         tNBT.func_74782_a("pages", tNBTList);
         rStack.func_77982_d(tNBT);
         GregTech_API.sBookList.put(aTitle, rStack);
         return copy(new Object[]{rStack});
      } else {
         return null;
      }
   }

   public static boolean doSoundAtClient(String aSoundName, int aTimeUntilNextSound, float aSoundStrength) {
      return doSoundAtClient(aSoundName, aTimeUntilNextSound, aSoundStrength, GregTech_API.gregtechmod.getThePlayer());
   }

   public static boolean doSoundAtClient(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, Entity aEntity) {
      return aEntity == null?false:doSoundAtClient(aSoundName, aTimeUntilNextSound, aSoundStrength, aEntity.field_70165_t, aEntity.field_70163_u, aEntity.field_70161_v);
   }

   public static boolean doSoundAtClient(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, double aX, double aY, double aZ) {
      return doSoundAtClient(aSoundName, aTimeUntilNextSound, aSoundStrength, 0.9F + (new Random()).nextFloat() * 0.2F, aX, aY, aZ);
   }

   public static boolean doSoundAtClient(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, float aSoundModulation, double aX, double aY, double aZ) {
      GT_PlayedSound tSound;
      if(!isStringInvalid(aSoundName) && FMLCommonHandler.instance().getEffectiveSide().isClient() && !sPlayedSoundMap.keySet().contains(tSound = new GT_PlayedSound(aSoundName, aX, aY, aZ)) && GregTech_API.gregtechmod.getThePlayer() != null && GregTech_API.gregtechmod.getThePlayer().field_70170_p.field_72995_K) {
         GregTech_API.gregtechmod.getThePlayer().field_70170_p.func_72980_b(aX, aY, aZ, aSoundName, aSoundStrength, aSoundModulation, false);
         sPlayedSoundMap.put(tSound, Integer.valueOf(aTimeUntilNextSound));
         return true;
      } else {
         return false;
      }
   }

   public static boolean sendSoundToPlayers(World aWorld, String aSoundName, float aSoundStrength, float aSoundModulation, int aX, int aY, int aZ) {
      if(!isStringInvalid(aSoundName) && aWorld != null && !aWorld.field_72995_K) {
         Packet250CustomPayload tPacket = new Packet250CustomPayload();
         tPacket.field_73630_a = "GTSound";
         tPacket.field_73287_r = false;
         ByteArrayDataOutput tOut = ByteStreams.newDataOutput();
         tOut.writeInt(aX);
         tOut.writeShort(aY);
         tOut.writeInt(aZ);
         tOut.writeUTF(aSoundName);
         tOut.writeFloat(aSoundStrength);
         tOut.writeFloat(aSoundModulation < 0.0F?0.9F + aWorld.field_73012_v.nextFloat() * 0.2F:aSoundModulation);
         tPacket.field_73629_c = tOut.toByteArray();
         tPacket.field_73628_b = tPacket.field_73629_c.length;
         sendPacketToAllPlayersInRange(aWorld, tPacket, aX, aZ);
         return true;
      } else {
         return false;
      }
   }

   public static void sendPacketToAllPlayersInRange(World aWorld, Packet aPacket, int aX, int aZ) {
      Iterator i$ = aWorld.field_73010_i.iterator();

      while(i$.hasNext()) {
         Object tObject = i$.next();
         if(!(tObject instanceof EntityPlayerMP)) {
            break;
         }

         EntityPlayerMP tPlayer = (EntityPlayerMP)tObject;
         if(GregTech_API.gregtechmod.allowPacketToBeSent(aPacket, tPlayer)) {
            Chunk tChunk = aWorld.func_72938_d(aX, aZ);
            if(tPlayer.func_71121_q().func_73040_p().func_72694_a(tPlayer, tChunk.field_76635_g, tChunk.field_76647_h)) {
               if(GregTech_API.DEBUG_MODE) {
                  GT_Log.out.println("sent Packet to " + tPlayer.field_71092_bJ);
               }

               tPlayer.field_71135_a.func_72567_b(aPacket);
            }
         }
      }

   }

   public static int stackToInt(ItemStack aStack) {
      return isStackInvalid(aStack)?0:aStack.field_77993_c | Item.field_77676_L.getDamage(aStack) << 16;
   }

   public static int stackToWildcard(ItemStack aStack) {
      return isStackInvalid(aStack)?0:aStack.field_77993_c | 2147418112;
   }

   public static ItemStack intToStack(int aStack) {
      int tID = aStack & '\uffff';
      int tMeta = aStack >>> 16;
      return tID > 0 && tID < Item.field_77698_e.length && Item.field_77698_e[tID] != null?new ItemStack(tID, 1, tMeta):null;
   }

   public static Integer[] stacksToIntegerArray(ItemStack ... aStacks) {
      Integer[] rArray = new Integer[aStacks.length];

      for(int i = 0; i < rArray.length; ++i) {
         rArray[i] = Integer.valueOf(stackToInt(aStacks[i]));
      }

      return rArray;
   }

   public static int[] stacksToIntArray(ItemStack ... aStacks) {
      int[] rArray = new int[aStacks.length];

      for(int i = 0; i < rArray.length; ++i) {
         rArray[i] = stackToInt(aStacks[i]);
      }

      return rArray;
   }

   public static long stacksToLong(ItemStack aStack1, ItemStack aStack2) {
      return (long)stackToInt(aStack1) | (long)stackToInt(aStack2) << 32;
   }

   public static boolean arrayContains(Object aObject, Object ... aObjects) {
      return listContains(aObject, Arrays.asList(aObjects));
   }

   public static boolean listContains(Object aObject, Collection aObjects) {
      return aObjects == null?false:aObjects.contains(aObject);
   }

   public static short getIDOfBlock(Object aBlock) {
      return isBlockInvalid(aBlock)?0:(short)((Block)aBlock).field_71990_ca;
   }

   public static Block getBlockFromStack(Object aStack) {
      if(isStackInvalid(aStack)) {
         return null;
      } else if(((ItemStack)aStack).func_77973_b() instanceof ItemBlock) {
         Block rBlock = Block.field_71973_m[((ItemBlock)((ItemStack)aStack).func_77973_b()).func_77883_f()];
         return isBlockInvalid(rBlock)?null:rBlock;
      } else {
         return null;
      }
   }

   public static boolean isBlockValid(Object aBlock) {
      return aBlock != null && aBlock instanceof Block && ((Block)aBlock).field_71990_ca != 0;
   }

   public static boolean isBlockInvalid(Object aBlock) {
      return aBlock == null || !(aBlock instanceof Block) || ((Block)aBlock).field_71990_ca == 0;
   }

   public static boolean isStringValid(Object aString) {
      return aString != null && !aString.toString().isEmpty();
   }

   public static boolean isStringInvalid(Object aString) {
      return aString == null || aString.toString().isEmpty();
   }

   public static boolean isStackValid(Object aStack) {
      return aStack != null && aStack instanceof ItemStack && ((ItemStack)aStack).func_77973_b() != null && ((ItemStack)aStack).field_77994_a >= 0;
   }

   public static boolean isStackInvalid(Object aStack) {
      return aStack == null || !(aStack instanceof ItemStack) || ((ItemStack)aStack).func_77973_b() == null || ((ItemStack)aStack).field_77994_a < 0;
   }

   public static boolean isDebugItem(ItemStack aStack) {
      return GT_Items.Armor_Cheat.isStackEqual(aStack, true, true) || areStacksEqual(GT_ModHandler.getIC2Item("debug", 1L), aStack, true);
   }

   public static boolean isItemStackInList(ItemStack aStack, Collection<Integer> aList) {
      return !isStackInvalid(aStack) && aList != null?aList.contains(Integer.valueOf(stackToInt(aStack))) || aList.contains(Integer.valueOf(stackToWildcard(aStack))):false;
   }

   public static boolean isOpaqueBlock(World aWorld, int aX, int aY, int aZ) {
      int tID = aWorld.func_72798_a(aX, aY, aZ);
      return tID > 0 && tID < Block.field_71973_m.length && Block.field_71973_m[tID] != null?Block.field_71973_m[tID].func_71926_d():false;
   }

   public static boolean isAirBlock(World aWorld, int aX, int aY, int aZ) {
      int tID = aWorld.func_72798_a(aX, aY, aZ);
      return tID > 0 && tID < Block.field_71973_m.length && Block.field_71973_m[tID] != null?Block.field_71973_m[tID].isAirBlock(aWorld, aX, aY, aZ):true;
   }

   public static boolean hasBlockHitBox(World aWorld, int aX, int aY, int aZ) {
      int tID = aWorld.func_72798_a(aX, aY, aZ);
      return tID < Block.field_71973_m.length && Block.field_71973_m[tID] != null?Block.field_71973_m[tID].func_71872_e(aWorld, aX, aY, aZ) != null:false;
   }

   public static String parseNumberToString(int aNumber) {
      String tString = "";
      boolean temp = true;
      boolean negative = false;
      if(aNumber < 0) {
         aNumber *= -1;
         negative = true;
      }

      for(int i = 1000000000; i > 0; i /= 10) {
         int tDigit = aNumber / i % 10;
         if(temp && tDigit != 0) {
            temp = false;
         }

         if(!temp) {
            tString = tString + tDigit;
            if(i != 1) {
               for(int j = i; j > 0; j /= 1000) {
                  if(j == 1) {
                     tString = tString + ",";
                  }
               }
            }
         }
      }

      if(tString.equals("")) {
         tString = "0";
      }

      return negative?"-" + tString:tString;
   }

   public static NBTTagCompound getNBTContainingBoolean(NBTTagCompound aNBT, Object aTag, boolean aValue) {
      if(aNBT == null) {
         aNBT = new NBTTagCompound();
      }

      aNBT.func_74757_a(aTag.toString(), aValue);
      return aNBT;
   }

   public static NBTTagCompound getNBTContainingByte(NBTTagCompound aNBT, Object aTag, byte aValue) {
      if(aNBT == null) {
         aNBT = new NBTTagCompound();
      }

      aNBT.func_74774_a(aTag.toString(), aValue);
      return aNBT;
   }

   public static NBTTagCompound getNBTContainingShort(NBTTagCompound aNBT, Object aTag, short aValue) {
      if(aNBT == null) {
         aNBT = new NBTTagCompound();
      }

      aNBT.func_74777_a(aTag.toString(), aValue);
      return aNBT;
   }

   public static NBTTagCompound getNBTContainingInteger(NBTTagCompound aNBT, Object aTag, int aValue) {
      if(aNBT == null) {
         aNBT = new NBTTagCompound();
      }

      aNBT.func_74768_a(aTag.toString(), aValue);
      return aNBT;
   }

   public static NBTTagCompound getNBTContainingFloat(NBTTagCompound aNBT, Object aTag, float aValue) {
      if(aNBT == null) {
         aNBT = new NBTTagCompound();
      }

      aNBT.func_74776_a(aTag.toString(), aValue);
      return aNBT;
   }

   public static NBTTagCompound getNBTContainingDouble(NBTTagCompound aNBT, Object aTag, double aValue) {
      if(aNBT == null) {
         aNBT = new NBTTagCompound();
      }

      aNBT.func_74780_a(aTag.toString(), aValue);
      return aNBT;
   }

   public static NBTTagCompound getNBTContainingString(NBTTagCompound aNBT, Object aTag, Object aValue) {
      if(aNBT == null) {
         aNBT = new NBTTagCompound();
      }

      if(aValue == null) {
         return aNBT;
      } else {
         aNBT.func_74778_a(aTag.toString(), aValue.toString());
         return aNBT;
      }
   }

   public static ItemStack setStack(Object aSetStack, Object aToStack) {
      if(!isStackInvalid(aSetStack) && !isStackInvalid(aToStack)) {
         ((ItemStack)aSetStack).field_77993_c = ((ItemStack)aToStack).field_77993_c;
         ((ItemStack)aSetStack).field_77994_a = ((ItemStack)aToStack).field_77994_a;
         Item.field_77676_L.setDamage((ItemStack)aSetStack, Item.field_77676_L.getDamage((ItemStack)aToStack));
         ((ItemStack)aSetStack).func_77982_d(((ItemStack)aToStack).func_77978_p());
         return (ItemStack)aSetStack;
      } else {
         return null;
      }
   }

   public static ItemStack[] copyStackArray(Object ... aStacks) {
      ItemStack[] rStacks = new ItemStack[aStacks.length];

      for(int i = 0; i < aStacks.length; ++i) {
         rStacks[i] = copy(new Object[]{aStacks[i]});
      }

      return rStacks;
   }

   public static ItemStack copy(Object ... aStacks) {
      Object[] arr$ = aStacks;
      int len$ = aStacks.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Object tStack = arr$[i$];
         if(isStackValid(tStack)) {
            return ((ItemStack)tStack).func_77946_l();
         }
      }

      return null;
   }

   public static ItemStack copyAmount(long aAmount, Object ... aStacks) {
      ItemStack rStack = copy(aStacks);
      if(isStackInvalid(rStack)) {
         return null;
      } else {
         if(aAmount > 64L) {
            aAmount = 64L;
         } else if(aAmount == -1L) {
            aAmount = 111L;
         } else if(aAmount < 0L) {
            aAmount = 0L;
         }

         rStack.field_77994_a = (byte)((int)aAmount);
         return rStack;
      }
   }

   public static ItemStack copyMetaData(long aMetaData, Object ... aStacks) {
      ItemStack rStack = copy(aStacks);
      if(isStackInvalid(rStack)) {
         return null;
      } else {
         Item.field_77676_L.setDamage(rStack, (short)((int)aMetaData));
         return rStack;
      }
   }

   public static ItemStack copyAmountAndMetaData(long aAmount, long aMetaData, Object ... aStacks) {
      ItemStack rStack = copyAmount(aAmount, aStacks);
      if(isStackInvalid(rStack)) {
         return null;
      } else {
         Item.field_77676_L.setDamage(rStack, (short)((int)aMetaData));
         return rStack;
      }
   }

   public static ItemStack mul(long aMultiplier, Object ... aStacks) {
      ItemStack rStack = copy(aStacks);
      if(rStack == null) {
         return null;
      } else {
         rStack.field_77994_a = (int)((long)rStack.field_77994_a * aMultiplier);
         return rStack;
      }
   }

   public static ItemStack loadItem(NBTTagCompound aNBT) {
      ItemStack rStack = ItemStack.func_77949_a(aNBT);

      try {
         if(rStack != null && rStack.func_77973_b().getClass().getName().startsWith("ic2.core.migration")) {
            rStack.func_77973_b().func_77663_a(rStack, GregTech_API.sDummyWorld, (Entity)null, 0, false);
         }
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
      }

      return GT_OreDictUnificator.get(true, rStack);
   }

   public static <E extends Object> E selectItemInList(int aIndex, E aReplacement, List<E> aList) {
      return aList != null && !aList.isEmpty()?(aList.size() <= aIndex?aList.get(aList.size() - 1):(aIndex < 0?aList.get(0):aList.get(aIndex))):aReplacement;
   }

   public static <E extends Object> E selectItemInList(int aIndex, E aReplacement, E ... aList) {
      return aList != null && aList.length != 0?(aList.length <= aIndex?aList[aList.length - 1]:(aIndex < 0?aList[0]:aList[aIndex])):aReplacement;
   }

   public static Map<GT_MetaPipeEntity_Item, Long> scanPipes(GT_MetaPipeEntity_Item aMetaTileEntity, Map<GT_MetaPipeEntity_Item, Long> aMap, long aStep, boolean aSuckItems) {
      aStep += (long)aMetaTileEntity.getStepSize();
      if(aMetaTileEntity.pipeCapacityCheck() && (aMap.get(aMetaTileEntity) == null || ((Long)aMap.get(aMetaTileEntity)).longValue() > aStep)) {
         aMap.put(aMetaTileEntity, Long.valueOf(aStep));

         for(byte i = 0; i < 6; ++i) {
            IGregTechTileEntity tItemPipe;
            byte tMetaTileEntity;
            IMetaTileEntity var8;
            if(aSuckItems) {
               if(aMetaTileEntity.getBaseMetaTileEntity().getCoverBehaviorAtSide(i).letsItemsIn(i, aMetaTileEntity.getBaseMetaTileEntity().getCoverIDAtSide(i), aMetaTileEntity.getBaseMetaTileEntity().getCoverDataAtSide(i), aMetaTileEntity.getBaseMetaTileEntity())) {
                  tItemPipe = aMetaTileEntity.getBaseMetaTileEntity().getIGregTechTileEntityAtSide(i);
                  if(aMetaTileEntity.getBaseMetaTileEntity().getColorization() >= 0) {
                     tMetaTileEntity = tItemPipe.getColorization();
                     if(tMetaTileEntity >= 0 && (tMetaTileEntity & 15) != (aMetaTileEntity.getBaseMetaTileEntity().getColorization() & 15)) {
                        continue;
                     }
                  }

                  if(tItemPipe != null && tItemPipe instanceof BaseMetaPipeEntity) {
                     var8 = tItemPipe.getMetaTileEntity();
                     if(var8 != null && var8 instanceof GT_MetaPipeEntity_Item && tItemPipe.getCoverBehaviorAtSide(getOppositeSide(i)).letsItemsOut(getOppositeSide(i), tItemPipe.getCoverIDAtSide(getOppositeSide(i)), tItemPipe.getCoverDataAtSide(getOppositeSide(i)), tItemPipe)) {
                        scanPipes((GT_MetaPipeEntity_Item)var8, aMap, aStep, aSuckItems);
                     }
                  }
               }
            } else if(aMetaTileEntity.getBaseMetaTileEntity().getCoverBehaviorAtSide(i).letsItemsOut(i, aMetaTileEntity.getBaseMetaTileEntity().getCoverIDAtSide(i), aMetaTileEntity.getBaseMetaTileEntity().getCoverDataAtSide(i), aMetaTileEntity.getBaseMetaTileEntity())) {
               tItemPipe = aMetaTileEntity.getBaseMetaTileEntity().getIGregTechTileEntityAtSide(i);
               if(tItemPipe != null) {
                  if(aMetaTileEntity.getBaseMetaTileEntity().getColorization() >= 0) {
                     tMetaTileEntity = tItemPipe.getColorization();
                     if(tMetaTileEntity >= 0 && (tMetaTileEntity & 15) != (aMetaTileEntity.getBaseMetaTileEntity().getColorization() & 15)) {
                        continue;
                     }
                  }

                  if(tItemPipe instanceof BaseMetaPipeEntity) {
                     var8 = tItemPipe.getMetaTileEntity();
                     if(var8 != null && var8 instanceof GT_MetaPipeEntity_Item && tItemPipe.getCoverBehaviorAtSide(getOppositeSide(i)).letsItemsIn(getOppositeSide(i), tItemPipe.getCoverIDAtSide(getOppositeSide(i)), tItemPipe.getCoverDataAtSide(getOppositeSide(i)), tItemPipe)) {
                        scanPipes((GT_MetaPipeEntity_Item)var8, aMap, aStep, aSuckItems);
                     }
                  }
               }
            }
         }
      }

      return aMap;
   }

   public static <K extends Object, V extends Object & Comparable> LinkedHashMap<K, V> sortMapByValuesAcending(Map<K, V> aMap) {
      LinkedList tEntrySet = new LinkedList(aMap.entrySet());
      Collections.sort(tEntrySet, new Comparator() {
         public int compare(Entry<K, V> aValue1, Entry<K, V> aValue2) {
            return ((Comparable)aValue1.getValue()).compareTo(aValue2.getValue());
         }
      });
      LinkedHashMap rMap = new LinkedHashMap();
      Iterator i$ = tEntrySet.iterator();

      while(i$.hasNext()) {
         Entry tEntry = (Entry)i$.next();
         rMap.put(tEntry.getKey(), tEntry.getValue());
      }

      return rMap;
   }

   public static <K extends Object, V extends Object & Comparable> LinkedHashMap<K, V> sortMapByValuesDescending(Map<K, V> aMap) {
      LinkedList tEntrySet = new LinkedList(aMap.entrySet());
      Collections.sort(tEntrySet, new Comparator() {
         public int compare(Entry<K, V> aValue1, Entry<K, V> aValue2) {
            return -((Comparable)aValue1.getValue()).compareTo(aValue2.getValue());
         }
      });
      LinkedHashMap rMap = new LinkedHashMap();
      Iterator i$ = tEntrySet.iterator();

      while(i$.hasNext()) {
         Entry tEntry = (Entry)i$.next();
         rMap.put(tEntry.getKey(), tEntry.getValue());
      }

      return rMap;
   }

   public static boolean isRealDimension(int aDimensionID) {
      try {
         if(DimensionManager.getProvider(aDimensionID) instanceof WorldProviderMyst) {
            return true;
         }
      } catch (Throwable var3) {
         ;
      }

      try {
         if(DimensionManager.getProvider(aDimensionID) instanceof WorldProviderTwilightForest) {
            return true;
         }
      } catch (Throwable var2) {
         ;
      }

      return GregTech_API.sDimensionalList.contains(Integer.valueOf(aDimensionID));
   }

   public static boolean moveEntityToDimensionAtCoords(Entity aEntity, int aDimension, double aX, double aY, double aZ) {
      WorldServer tTargetWorld = DimensionManager.getWorld(aDimension);
      WorldServer tOriginalWorld = DimensionManager.getWorld(aEntity.field_70170_p.field_73011_w.field_76574_g);
      if(tTargetWorld != null && tOriginalWorld != null) {
         if(aEntity.field_70154_o != null) {
            aEntity.func_70078_a((Entity)null);
         }

         if(aEntity.field_70153_n != null) {
            aEntity.field_70153_n.func_70078_a((Entity)null);
         }

         if(aEntity instanceof EntityPlayerMP) {
            EntityPlayerMP tNewEntity = (EntityPlayerMP)aEntity;
            tNewEntity.field_71093_bK = aDimension;
            tNewEntity.field_71135_a.func_72567_b(new Packet9Respawn(tNewEntity.field_71093_bK, (byte)tNewEntity.field_70170_p.field_73013_u, tTargetWorld.func_72912_H().func_76067_t(), tTargetWorld.func_72800_K(), tNewEntity.field_71134_c.func_73081_b()));
            tOriginalWorld.func_72973_f(tNewEntity);
            tNewEntity.field_70128_L = false;
            tNewEntity.func_70029_a(tTargetWorld);
            MinecraftServer.func_71276_C().func_71203_ab().func_72375_a(tNewEntity, tOriginalWorld);
            tNewEntity.field_71135_a.func_72569_a(aX + 0.5D, aY + 0.5D, aZ + 0.5D, tNewEntity.field_70177_z, tNewEntity.field_70125_A);
            tNewEntity.field_71134_c.func_73080_a(tTargetWorld);
            MinecraftServer.func_71276_C().func_71203_ab().func_72354_b(tNewEntity, tTargetWorld);
            MinecraftServer.func_71276_C().func_71203_ab().func_72385_f(tNewEntity);
            Iterator temp = tNewEntity.func_70651_bq().iterator();

            while(temp.hasNext()) {
               PotionEffect potioneffect = (PotionEffect)temp.next();
               tNewEntity.field_71135_a.func_72567_b(new Packet41EntityEffect(tNewEntity.field_70157_k, potioneffect));
            }

            tNewEntity.field_71135_a.func_72569_a(aX + 0.5D, aY + 0.5D, aZ + 0.5D, tNewEntity.field_70177_z, tNewEntity.field_70125_A);
            GameRegistry.onPlayerChangedDimension(tNewEntity);
         } else {
            aEntity.func_70107_b(aX + 0.5D, aY + 0.5D, aZ + 0.5D);
            aEntity.field_70170_p.func_72900_e(aEntity);
            aEntity.field_71093_bK = aDimension;
            aEntity.field_70128_L = false;
            Entity tNewEntity1 = EntityList.func_75620_a(EntityList.func_75621_b(aEntity), tTargetWorld);
            if(tNewEntity1 != null) {
               tNewEntity1.func_82141_a(aEntity, true);
               aEntity.func_70106_y();
               tNewEntity1.field_70128_L = false;
               boolean temp1 = tNewEntity1.field_98038_p;
               tNewEntity1.field_98038_p = true;
               tTargetWorld.func_72838_d(tNewEntity1);
               tNewEntity1.field_98038_p = temp1;
               tNewEntity1.field_70128_L = false;
               aEntity = tNewEntity1;
            }
         }

         if(aEntity instanceof EntityLivingBase) {
            ((EntityLivingBase)aEntity).func_70634_a(aX, aY, aZ);
         } else {
            aEntity.func_70107_b(aX, aY, aZ);
         }

         tOriginalWorld.func_82742_i();
         tTargetWorld.func_82742_i();
         return true;
      } else {
         return false;
      }
   }

   public static int getCoordinateScan(ArrayList<String> aList, EntityPlayer aPlayer, World aWorld, int aScanLevel, int aX, int aY, int aZ, int aSide, float aClickX, float aClickY, float aClickZ) {
      if(aList == null) {
         return 0;
      } else {
         ArrayList tList = new ArrayList();
         int rEUAmount = 0;
         TileEntity tTileEntity = aWorld.func_72796_p(aX, aY, aZ);
         Block tBlock = Block.field_71973_m[aWorld.func_72798_a(aX, aY, aZ)];
         tList.add("-----");

         try {
            if(tTileEntity != null && tTileEntity instanceof IInventory) {
               tList.add("Name: " + ((IInventory)tTileEntity).func_70303_b() + "  ID: " + tBlock.field_71990_ca + "  MetaData: " + aWorld.func_72805_g(aX, aY, aZ));
            } else {
               tList.add("Name: " + tBlock.func_71917_a() + "  ID: " + tBlock.field_71990_ca + "  MetaData: " + aWorld.func_72805_g(aX, aY, aZ));
            }

            tList.add("Hardness: " + tBlock.func_71934_m(aWorld, aX, aY, aZ) + "  Blast Resistance: " + tBlock.getExplosionResistance(aPlayer, aWorld, aX, aY, aZ, aPlayer.field_70165_t, aPlayer.field_70163_u, aPlayer.field_70161_v));
            if(tBlock.isBeaconBase(aWorld, aX, aY, aZ, aX, aY + 1, aZ)) {
               tList.add("Is valid Beacon Pyramid Material");
            }
         } catch (Throwable var36) {
            if(GregTech_API.DEBUG_MODE) {
               var36.printStackTrace(GT_Log.err);
            }
         }

         if(tTileEntity != null) {
            try {
               if(tTileEntity instanceof IFluidHandler) {
                  rEUAmount += 500;
                  FluidTankInfo[] tEvent = ((IFluidHandler)tTileEntity).getTankInfo(ForgeDirection.getOrientation(aSide));
                  if(tEvent != null) {
                     for(byte arr$ = 0; arr$ < tEvent.length; ++arr$) {
                        tList.add("Tank " + arr$ + ": " + (tEvent[arr$].fluid == null?0:tEvent[arr$].fluid.amount) + " / " + tEvent[arr$].capacity + " " + getFluidName(tEvent[arr$].fluid, true));
                     }
                  }
               }
            } catch (Throwable var35) {
               if(GregTech_API.DEBUG_MODE) {
                  var35.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IReactorChamber) {
                  rEUAmount += 500;
                  tTileEntity = (TileEntity)((TileEntity)((IReactorChamber)tTileEntity).getReactor());
               }
            } catch (Throwable var34) {
               if(GregTech_API.DEBUG_MODE) {
                  var34.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IReactor) {
                  rEUAmount += 500;
                  tList.add("Heat: " + ((IReactor)tTileEntity).getHeat() + "/" + ((IReactor)tTileEntity).getMaxHeat() + "  HEM: " + ((IReactor)tTileEntity).getHeatEffectModifier() + "  Base EU Output: ");
               }
            } catch (Throwable var33) {
               if(GregTech_API.DEBUG_MODE) {
                  var33.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IWrenchable) {
                  rEUAmount += 100;
                  tList.add("Facing: " + ((IWrenchable)tTileEntity).getFacing() + " / Chance: " + ((IWrenchable)tTileEntity).getWrenchDropRate() * 100.0F + "%");
                  tList.add(((IWrenchable)tTileEntity).wrenchCanRemove(aPlayer)?"You can remove this with a Wrench":"You can NOT remove this with a Wrench");
               }
            } catch (Throwable var32) {
               if(GregTech_API.DEBUG_MODE) {
                  var32.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IEnergyTile) {
                  rEUAmount += 200;
               }
            } catch (Throwable var31) {
               if(GregTech_API.DEBUG_MODE) {
                  var31.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IEnergySink) {
                  rEUAmount += 400;
                  tList.add("Max Safe Input: " + ((IEnergySink)tTileEntity).getMaxSafeInput());
               }
            } catch (Throwable var30) {
               if(GregTech_API.DEBUG_MODE) {
                  var30.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IEnergySource) {
                  rEUAmount += 400;
               }
            } catch (Throwable var29) {
               if(GregTech_API.DEBUG_MODE) {
                  var29.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IEnergyConductor) {
                  rEUAmount += 200;
                  tList.add("Conduction Loss: " + ((IEnergyConductor)tTileEntity).getConductionLoss());
               }
            } catch (Throwable var28) {
               if(GregTech_API.DEBUG_MODE) {
                  var28.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IEnergyStorage) {
                  rEUAmount += 200;
                  tList.add("Contained Energy: " + ((IEnergyStorage)tTileEntity).getStored() + " of " + ((IEnergyStorage)tTileEntity).getCapacity());
               }
            } catch (Throwable var27) {
               if(GregTech_API.DEBUG_MODE) {
                  var27.printStackTrace(GT_Log.err);
               }
            }

            boolean var37;
            int var39;
            try {
               if(tTileEntity instanceof IUpgradableMachine) {
                  rEUAmount += 500;
                  var37 = false;
                  if(((IUpgradableMachine)tTileEntity).hasMufflerUpgrade()) {
                     tList.add("Has Muffler Upgrade");
                  }

                  byte var38;
                  if(0 < (var38 = ((IUpgradableMachine)tTileEntity).getOverclockerUpgradeCount())) {
                     tList.add(var38 + " Overclocker Upgrades");
                  }

                  if(0 < (var38 = ((IUpgradableMachine)tTileEntity).getTransformerUpgradeCount())) {
                     tList.add(var38 + " Transformer Upgrades");
                  }

                  if(0 < (var39 = ((IUpgradableMachine)tTileEntity).getUpgradeStorageVolume())) {
                     tList.add(var39 + " Upgraded EU Capacity");
                  }
               }
            } catch (Throwable var26) {
               if(GregTech_API.DEBUG_MODE) {
                  var26.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IMachineProgress) {
                  rEUAmount += 400;
                  var37 = false;
                  if(0 < (var39 = ((IMachineProgress)tTileEntity).getMaxProgress())) {
                     tList.add("Progress: " + var39 + " / " + ((IMachineProgress)tTileEntity).getProgress());
                  }
               }
            } catch (Throwable var25) {
               if(GregTech_API.DEBUG_MODE) {
                  var25.printStackTrace(GT_Log.err);
               }
            }

            String var41;
            try {
               if(tTileEntity instanceof ICoverable) {
                  rEUAmount += 300;
                  var41 = ((ICoverable)tTileEntity).getCoverBehaviorAtSide((byte)aSide).getDescription((byte)aSide, ((ICoverable)tTileEntity).getCoverIDAtSide((byte)aSide), ((ICoverable)tTileEntity).getCoverDataAtSide((byte)aSide), (ICoverable)tTileEntity);
                  if(var41 != null && !var41.equals("")) {
                     tList.add(var41);
                  }
               }
            } catch (Throwable var24) {
               if(GregTech_API.DEBUG_MODE) {
                  var24.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IGregTechTileEntity) {
                  tList.add("Owned by: " + ((IGregTechTileEntity)tTileEntity).getOwnerName());
               }
            } catch (Throwable var23) {
               if(GregTech_API.DEBUG_MODE) {
                  var23.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
                  tList.addAll(Arrays.asList(((IGregTechDeviceInformation)tTileEntity).getInfoData()));
               }
            } catch (Throwable var22) {
               if(GregTech_API.DEBUG_MODE) {
                  var22.printStackTrace(GT_Log.err);
               }
            }

            try {
               if(tTileEntity instanceof ICropTile) {
                  if(((ICropTile)tTileEntity).getScanLevel() < 4) {
                     rEUAmount += 10000;
                     ((ICropTile)tTileEntity).setScanLevel((byte)4);
                  }

                  if(((ICropTile)tTileEntity).getID() >= 0 && ((ICropTile)tTileEntity).getID() < Crops.instance.getCropList().length && Crops.instance.getCropList()[((ICropTile)tTileEntity).getID()] != null) {
                     rEUAmount += 1000;
                     tList.add("Type -- Crop-Name: " + Crops.instance.getCropList()[((ICropTile)tTileEntity).getID()].name() + "  Growth: " + ((ICropTile)tTileEntity).getGrowth() + "  Gain: " + ((ICropTile)tTileEntity).getGain() + "  Resistance: " + ((ICropTile)tTileEntity).getResistance());
                     tList.add("Plant -- Fertilizer: " + ((ICropTile)tTileEntity).getNutrientStorage() + "  Water: " + ((ICropTile)tTileEntity).getHydrationStorage() + "  Weed-Ex: " + ((ICropTile)tTileEntity).getWeedExStorage() + "  Scan-Level: " + ((ICropTile)tTileEntity).getScanLevel());
                     tList.add("Environment -- Nutrients: " + ((ICropTile)tTileEntity).getNutrients() + "  Humidity: " + ((ICropTile)tTileEntity).getHumidity() + "  Air-Quality: " + ((ICropTile)tTileEntity).getAirQuality());
                     var41 = "";
                     String[] var40 = Crops.instance.getCropList()[((ICropTile)tTileEntity).getID()].attributes();
                     int len$ = var40.length;

                     for(int i$ = 0; i$ < len$; ++i$) {
                        String tAttribute = var40[i$];
                        var41 = var41 + ", " + tAttribute;
                     }

                     tList.add("Attributes:" + var41.replaceFirst(",", ""));
                     tList.add("Discovered by: " + Crops.instance.getCropList()[((ICropTile)tTileEntity).getID()].discoveredBy());
                  }
               }
            } catch (Throwable var21) {
               if(GregTech_API.DEBUG_MODE) {
                  var21.printStackTrace(GT_Log.err);
               }
            }
         }

         try {
            if(tBlock instanceof IDebugableBlock) {
               rEUAmount += 500;
               ArrayList var42 = ((IDebugableBlock)tBlock).getDebugInfo(aPlayer, aX, aY, aZ, 3);
               if(var42 != null) {
                  tList.addAll(var42);
               }
            }
         } catch (Throwable var20) {
            if(GregTech_API.DEBUG_MODE) {
               var20.printStackTrace(GT_Log.err);
            }
         }

         GT_ScannerEvent var43 = new GT_ScannerEvent(aWorld, aPlayer, aX, aY, aZ, (byte)aSide, aScanLevel, tBlock, tTileEntity, tList, aClickX, aClickY, aClickZ);
         var43.mEUCost = rEUAmount;
         MinecraftForge.EVENT_BUS.post(var43);
         if(!var43.isCanceled()) {
            aList.addAll(tList);
         }

         return var43.mEUCost;
      }
   }

   public static float[] getClickedFacingCoords(byte aSide, float aX, float aY, float aZ) {
      switch(aSide) {
      case 0:
         return new float[]{1.0F - aX, aZ};
      case 1:
         return new float[]{aX, aZ};
      case 2:
         return new float[]{1.0F - aX, 1.0F - aY};
      case 3:
         return new float[]{aX, 1.0F - aY};
      case 4:
         return new float[]{aZ, 1.0F - aY};
      case 5:
         return new float[]{1.0F - aZ, 1.0F - aY};
      default:
         return new float[]{0.5F, 0.5F};
      }
   }

   public static byte determineWrenchingSide(byte aSide, float aX, float aY, float aZ) {
      byte tBack = getOppositeSide(aSide);
      switch(aSide) {
      case 0:
      case 1:
         if((double)aX < 0.25D) {
            if((double)aZ < 0.25D) {
               return tBack;
            } else {
               if((double)aZ > 0.75D) {
                  return tBack;
               }

               return (byte)4;
            }
         } else if((double)aX > 0.75D) {
            if((double)aZ < 0.25D) {
               return tBack;
            } else {
               if((double)aZ > 0.75D) {
                  return tBack;
               }

               return (byte)5;
            }
         } else if((double)aZ < 0.25D) {
            return (byte)2;
         } else {
            if((double)aZ > 0.75D) {
               return (byte)3;
            }

            return aSide;
         }
      case 2:
      case 3:
         if((double)aX < 0.25D) {
            if((double)aY < 0.25D) {
               return tBack;
            } else {
               if((double)aY > 0.75D) {
                  return tBack;
               }

               return (byte)4;
            }
         } else if((double)aX > 0.75D) {
            if((double)aY < 0.25D) {
               return tBack;
            } else {
               if((double)aY > 0.75D) {
                  return tBack;
               }

               return (byte)5;
            }
         } else if((double)aY < 0.25D) {
            return (byte)0;
         } else {
            if((double)aY > 0.75D) {
               return (byte)1;
            }

            return aSide;
         }
      case 4:
      case 5:
         if((double)aZ < 0.25D) {
            if((double)aY < 0.25D) {
               return tBack;
            } else {
               if((double)aY > 0.75D) {
                  return tBack;
               }

               return (byte)2;
            }
         } else if((double)aZ > 0.75D) {
            if((double)aY < 0.25D) {
               return tBack;
            } else {
               if((double)aY > 0.75D) {
                  return tBack;
               }

               return (byte)3;
            }
         } else if((double)aY < 0.25D) {
            return (byte)0;
         } else {
            if((double)aY > 0.75D) {
               return (byte)1;
            }

            return aSide;
         }
      default:
         return (byte)-1;
      }
   }

}
