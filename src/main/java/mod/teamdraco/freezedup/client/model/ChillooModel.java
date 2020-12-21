package mod.teamdraco.freezedup.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.teamdraco.freezedup.entity.ChillooEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class ChillooModel<T extends Entity> extends AgeableModel<ChillooEntity> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer left_leg;
    public ModelRenderer right_leg;
    public ModelRenderer body_feathers;
    public ModelRenderer tail_feathers;
    public ModelRenderer head_feathers;

    public ChillooModel() {
        this.textureWidth = 112;
        this.textureHeight = 64;
        this.right_leg = new ModelRenderer(this, 0, 25);
        this.right_leg.setRotationPoint(4.5F, 1.0F, 7.5F);
        this.right_leg.addBox(-3.0F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.head_feathers = new ModelRenderer(this, 0, 55);
        this.head_feathers.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.head_feathers.addBox(-3.0F, 0.0F, -5.5F, 6.0F, 2.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 36, 0);
        this.head.setRotationPoint(0.0F, 12.0F, -10.0F);
        this.head.addBox(-4.0F, 0.0F, -7.0F, 8.0F, 6.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.tail_feathers = new ModelRenderer(this, 60, 42);
        this.tail_feathers.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail_feathers.addBox(-1.5F, 3.0F, 0.0F, 3.0F, 4.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.body_feathers = new ModelRenderer(this, 4, 25);
        this.body_feathers.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body_feathers.addBox(-5.0F, 6.0F, 0.0F, 10.0F, 3.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 12.0F, -8.0F);
        this.body.addBox(-5.0F, -3.0F, 0.0F, 10.0F, 9.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 52, 0);
        this.tail.setRotationPoint(0.0F, 0.0F, 14.0F);
        this.tail.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.5235987755982988F, 0.0F, 0.0F);
        this.left_leg = new ModelRenderer(this, 0, 25);
        this.left_leg.mirror = true;
        this.left_leg.setRotationPoint(-4.5F, 1.0F, 7.5F);
        this.left_leg.addBox(-2.0F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.right_leg);
        this.head.addChild(this.head_feathers);
        this.tail.addChild(this.tail_feathers);
        this.body.addChild(this.body_feathers);
        this.body.addChild(this.tail);
        this.body.addChild(this.left_leg);
    }

    @Override
    public void setRotationAngles(ChillooEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        if(this.isChild) {
            this.head.rotationPointY = 10;
            this.head.rotationPointZ = -8;
        }
        if (entityIn.isEntitySleeping()) {
            this.body.setRotationPoint(0.0F, 9.0F, -2.0F);
            this.setRotateAngle(body, -1.0471975511965976F, 0.0F, 0.0F);

            this.tail.setRotationPoint(0.0F, 0.0F, 14.0F);
            this.setRotateAngle(tail, 1.7453292519943295F, 0.0F, 0.0F);

            this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
            this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
            this.head.rotationPointZ = -4.5f;
            if (this.isChild) {
                this.head.rotationPointY = 6;
            }
            else {
                this.head.rotationPointY = 5.5f;
            }

            this.left_leg.setRotationPoint(-4.5F, 4.0F, 12.0F);
            this.setRotateAngle(left_leg, -0.47123889803846897F, 0.17453292519943295F, 0.2617993877991494F);

            this.right_leg.setRotationPoint(4.5F, 4.0F, 12.0F);
            this.setRotateAngle(right_leg, -0.47123889803846897F, -0.17453292519943295F, -0.2617993877991494F);
        }
        else {
            this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
            this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
            this.head.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
            this.head.rotationPointY = 10;
            this.head.rotationPointZ = -4;

            this.tail.rotateAngleY = MathHelper.cos(f * speed * 0.2F) * degree * 0.6F * f1;
            this.tail.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 0.6F * f1 - 0.4F;

            this.right_leg.rotateAngleX = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.8F * f1;
            this.right_leg.rotateAngleY = 0;
            this.right_leg.rotateAngleZ = 0;
            this.right_leg.rotationPointZ = 7.5f;

            this.left_leg.rotateAngleX = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * -0.8F * f1;
            this.left_leg.rotateAngleY = 0;
            this.left_leg.rotateAngleZ = 0;
            this.left_leg.rotationPointZ = 7.5f;

            this.body.rotateAngleX = 0;
            this.body.rotateAngleY = 12;
            this.body.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1;
        }
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(head);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(body);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
