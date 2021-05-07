package teamdraco.frozenup.client.model;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import teamdraco.frozenup.entity.ChillooEntity;

public class ChillooModel<T extends Entity> extends AgeableModel<ChillooEntity> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer bodyFeathers;
    public ModelRenderer tailFeathers;
    public ModelRenderer headFeathers;

    private final List<ModelRenderer> headParts;
    private final List<ModelRenderer> bodyParts;

    public ChillooModel() {
        super(true, 8f, 3f);

        this.textureWidth = 112;
        this.textureHeight = 64;
        this.rightLeg = new ModelRenderer(this, 0, 25);
        this.rightLeg.setRotationPoint(4.5F, 1.0F, 7.5F);
        this.rightLeg.addBox(-3.0F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.headFeathers = new ModelRenderer(this, 0, 55);
        this.headFeathers.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.headFeathers.addBox(-3.0F, 0.0F, -5.5F, 6.0F, 2.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 36, 0);
        this.head.setRotationPoint(0.0F, 12.0F, -10.0F);
        this.head.addBox(-4.0F, 0.0F, -7.0F, 8.0F, 6.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.tailFeathers = new ModelRenderer(this, 60, 42);
        this.tailFeathers.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailFeathers.addBox(-1.5F, 3.0F, 0.0F, 3.0F, 4.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.bodyFeathers = new ModelRenderer(this, 4, 25);
        this.bodyFeathers.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyFeathers.addBox(-5.0F, 6.0F, 0.0F, 10.0F, 3.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 12.0F, -8.0F);
        this.body.addBox(-5.0F, -3.0F, 0.0F, 10.0F, 9.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 52, 0);
        this.tail.setRotationPoint(0.0F, 0.0F, 14.0F);
        this.tail.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.5235987755982988F, 0.0F, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 25);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(-4.5F, 1.0F, 7.5F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.rightLeg);
        this.head.addChild(this.headFeathers);
        this.tail.addChild(this.tailFeathers);
        this.body.addChild(this.bodyFeathers);
        this.body.addChild(this.tail);
        this.body.addChild(this.leftLeg);

        this.headParts = Collections.singletonList(head);
        this.bodyParts = Collections.singletonList(body);
    }

    @Override
    public void setLivingAnimations(ChillooEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        float speed = 1.0f;
        float degree = 1.0f;
        if (entityIn.isEntitySleeping()) {
            if (entityIn.isChild()) {
                this.head.rotationPointY = 9f;
                this.head.rotationPointZ = -5f;
            } else {
                this.head.rotationPointY = 5.5f;
                this.head.rotationPointZ = -4.5f;
            }

            this.body.setRotationPoint(0.0F, 9.0F, -2.0F);
            this.setRotateAngle(body, -1.0471975511965976F, 0.0F, 0.0F);

            this.tail.setRotationPoint(0.0F, 0.0F, 14.0F);
            this.setRotateAngle(tail, 1.7453292519943295F, 0.0F, 0.0F);

            this.leftLeg.setRotationPoint(-4.5F, 4.0F, 12.0F);
            this.setRotateAngle(leftLeg, -0.47123889803846897F, 0.17453292519943295F, 0.2617993877991494F);

            this.rightLeg.setRotationPoint(4.5F, 4.0F, 12.0F);
            this.setRotateAngle(rightLeg, -0.47123889803846897F, -0.17453292519943295F, -0.2617993877991494F);
        } else {
            this.head.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
            this.head.rotationPointY = 12f;
            this.head.rotationPointZ = -10f;

            this.tail.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.6F * limbSwingAmount;
            this.tail.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount - 0.4F;
            this.tail.rotationPointZ = 14f;

            this.rightLeg.rotateAngleX = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
            this.rightLeg.rotateAngleY = 0f;
            this.rightLeg.rotateAngleZ = 0f;
            this.rightLeg.rotationPointX = 4.5f;
            this.rightLeg.rotationPointY = 1.0f;
            this.rightLeg.rotationPointZ = 7.5f;

            this.leftLeg.rotateAngleX = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount;
            this.leftLeg.rotateAngleY = 0f;
            this.leftLeg.rotateAngleZ = 0f;
            this.leftLeg.rotationPointX = -4.5f;
            this.leftLeg.rotationPointY = 1.0f;
            this.leftLeg.rotationPointZ = 7.5f;

            this.body.setRotationPoint(0f, 12f, -8f);
            this.body.rotateAngleX = 0f;
            this.body.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
        }

        int timer = entityIn.digTimer;
        if (!entityIn.isEntitySleeping() && timer > 0) {
            head.rotationPointY = getHeadRotationPointY(timer, partialTick);
            head.rotateAngleX = getHeadRotationAngleX(timer, partialTick);
        }
    }

    @Override
    public void setRotationAngles(ChillooEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityIn.digTimer <= 0) {
            head.rotateAngleX = headPitch * ((float) Math.PI / 180f);
            head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180f);
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

    private static float getHeadRotationPointY(int timer, float partialTicks) {
        if (timer >= 3 && timer <= 36) return 14f;
        return timer < 4 ? ((float) timer - partialTicks) + 12f : -((float) (timer - 40) - partialTicks) * 0.5f + 12f;
    }

    private static float getHeadRotationAngleX(int timer, float partialTicks) {
        if (timer >= 3 && timer <= 36) {
            float f = ((float)(timer - 4) - partialTicks) / 32.0F;
            return ((float)Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return timer > 0 ? ((float) Math.PI / 5F) : 0;
        }
    }
}
