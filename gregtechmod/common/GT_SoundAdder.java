package gregtechmod.common;

import gregtechmod.api.util.GT_Log;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

public class GT_SoundAdder {

   public GT_SoundAdder() {
      MinecraftForge.EVENT_BUS.register(this);
   }

   @ForgeSubscribe
   public void onSound(SoundLoadEvent event) {
      try {
         event.manager.field_77379_b.func_77459_a("ic2:machines/ExtractorOp.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/MaceratorOp.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/InductionLoop.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/CompressorOp.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/RecyclerOp.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/MinerOp.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/PumpOp.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/ElectroFurnaceLoop.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/InductionLoop.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/MachineOverload.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/InterruptOne.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/KaChing.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:machines/MagnetizerLoop.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/chainsaw/ChainsawIdle.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/chainsaw/ChainsawStop.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/chainsaw/ChainsawUseOne.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/chainsaw/ChainsawUseTwo.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/drill/DrillHard.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/drill/DrillSoft.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/drill/DrillUseLoop.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/jetpack/JetpackFire.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/jetpack/JetpackLoop.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/mininglaser/MiningLaser.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/mininglaser/MiningLaserExplosive.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/mininglaser/MiningLaserLongRange.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/mininglaser/MiningLaserLowFocus.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/mininglaser/MiningLaserScatter.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/nanosabre/NanosabreIdle.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/nanosabre/NanosabrePowerup.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/nanosabre/NanosabreSwing1.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/nanosabre/NanosabreSwing2.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/nanosabre/NanosabreSwing3.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/quantumsuit/HelmetLoop.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/quantumsuit/QuantumsuitBoots.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/BatteryUse.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/Dynamiteomote.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/InsulationCutters.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/NukeExplosion.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/ODScanner.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/Painter.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/RubberTrampoline.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/Treetap.ogg");
         event.manager.field_77379_b.func_77459_a("ic2:tools/Wrench.ogg");
      } catch (Throwable var3) {
         var3.printStackTrace(GT_Log.err);
      }

   }
}
