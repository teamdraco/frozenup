package mod.teamdraco.frozenup.client.model;

import com.google.common.collect.ImmutableList;

import mod.teamdraco.frozenup.entity.ChillooEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;
import java.util.List;

public class ChillooModel<T extends Entity> extends AgeableModel<ChillooEntity> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer left_leg;
    public ModelRenderer right_leg;
    public ModelRenderer body_feathers;
    public ModelRenderer tail_feathers;
    public ModelRenderer head_feathers;

    private List<ModelRenderer> headParts;
    private List<ModelRenderer> bodyParts;

    public ChillooModel() {
        super(true, 8f, 3f);

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

        this.headParts = Collections.singletonList(head);
        this.bodyParts = Collections.singletonList(body);
    }

    @Override
    public void setRotationAngles(ChillooEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        if (entityIn.isEntitySleeping()) {
            if (entityIn.isChild()) {
                this.head.rotationPointY = 9f;
                this.head.rotationPointZ = -5f;
            }
            else {
                this.head.rotationPointY = 5.5f;
                this.head.rotationPointZ = -4.5f;
            }

            this.body.setRotationPoint(0.0F, 9.0F, -2.0F);
            this.setRotateAngle(body, -1.0471975511965976F, 0.0F, 0.0F);

            this.tail.setRotationPoint(0.0F, 0.0F, 14.0F);
            this.setRotateAngle(tail, 1.7453292519943295F, 0.0F, 0.0F);

            this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
            this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);

            this.left_leg.setRotationPoint(-4.5F, 4.0F, 12.0F);
            this.setRotateAngle(left_leg, -0.47123889803846897F, 0.17453292519943295F, 0.2617993877991494F);

            this.right_leg.setRotationPoint(4.5F, 4.0F, 12.0F);
            this.setRotateAngle(right_leg, -0.47123889803846897F, -0.17453292519943295F, -0.2617993877991494F);
        }
        else {
            this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
            this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
            this.head.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
            this.head.rotationPointY = 12f;
            this.head.rotationPointZ = -10f;

            this.tail.rotateAngleY = MathHelper.cos(f * speed * 0.2F) * degree * 0.6F * f1;
            this.tail.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 0.6F * f1 - 0.4F;
            this.tail.rotationPointZ = 14f;

            this.right_leg.rotateAngleX = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * 0.8F * f1;
            this.right_leg.rotateAngleY = 0f;
            this.right_leg.rotateAngleZ = 0f;
            this.right_leg.rotationPointX = 4.5f;
            this.right_leg.rotationPointY = 1.0f;
            this.right_leg.rotationPointZ = 7.5f;

            this.left_leg.rotateAngleX = MathHelper.cos(1.0F + f * speed * 0.4F) * degree * -0.8F * f1;
            this.left_leg.rotateAngleY = 0f;
            this.left_leg.rotateAngleZ = 0f;
            this.left_leg.rotationPointX = -4.5f;
            this.left_leg.rotationPointY = 1.0f;
            this.left_leg.rotationPointZ = 7.5f;

            this.body.setRotationPoint(0f, 12f, -8f);
            this.body.rotateAngleX = 0f;
            this.body.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1;
        }
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return this.headParts;
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return this.bodyParts;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

