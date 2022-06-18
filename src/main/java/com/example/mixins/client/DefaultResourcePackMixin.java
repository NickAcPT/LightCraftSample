package com.example.mixins.client;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.resources.DefaultResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DefaultResourcePack.class)
public class DefaultResourcePackMixin {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;of(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"))
    private static ImmutableSet<Object> onCreateResourcePackDomains(Object e1, Object e2) {
        return ImmutableSet.of(e1, e2, "exampleclient");
    }
}
