package com.example.mixins.client.fixes;

import net.optifine.Log;
import net.optifine.reflect.ReflectorClass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ReflectorClass.class)
public class ReflectorClassMixin {

    @Shadow public String targetClassName;

    // Fix the spam caused by the getTargetClass method when the class is not found
    @Redirect(method = "getTargetClass", at = @At(value = "INVOKE", target = "Ljava/lang/Throwable;printStackTrace()V"))
    public void onPrintStackTrace(Throwable throwable) {
        if (throwable instanceof NoClassDefFoundError || throwable instanceof ClassNotFoundException) {
            Log.log("(Reflector) Class not present: " + this.targetClassName);
            return;
        }
        throwable.printStackTrace();
    }
}
