package com.example.mixins.client.fixes;

import java.io.*;
import net.optifine.reflect.ReflectorForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ReflectorForge.class)
public class ReflectorForgeMixin {
    @Inject(method = "getOptiFineResourceStream", at = @At("HEAD"), cancellable = true)
    private static void onGetOptiFineResourceStream(String path, CallbackInfoReturnable<InputStream> cir) {
        // If we are using OrionLauncher (aka what is used by the LightClient gradle plugin),
        // we need to replace the find resource stuff
        if (ReflectorForge.class.getClassLoader().getClass().getName().contains("orion")) {
            if (path.startsWith("/")) {
                path = path.substring(1);
            }

            try (InputStream resource = ReflectorForge.class.getClassLoader().getResourceAsStream(path)) {
                if (resource == null) {
                    cir.setReturnValue(null);
                } else {
                    cir.setReturnValue(new ByteArrayInputStream(resource.readAllBytes()));
                }
            } catch (IOException e) {
                e.printStackTrace();
                cir.setReturnValue(null);
            }
        }
    }
}
