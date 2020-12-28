package divine.client.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import divine.capabilities.classes.ClassesCap;
import divine.capabilities.classes.IClasses;
import divine.capabilities.race.IRaces;
import divine.capabilities.race.RacesCap;
import divine.client.gui.buttons.MainButton;
import divine.main.Reference;
import divine.util.SimpleUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;


public class GuiTestCreation extends GuiScreen{
	
	private static ResourceLocation test;
	private static int imgWidth, imgHeight;
	private int curRace, curClass;
	private String raceStr, classStr;
	private String name;
	IRaces races;
	IClasses classes;
    protected int xSize = 176;
    protected int ySize = 166;
    private float oldMouseX;
    private float oldMouseY;
	
	public GuiTestCreation() {
		test = new ResourceLocation(Reference.MODID + ":textures/gui/background_cont.png");
		this.mc = Minecraft.getMinecraft();
		name = mc.player.getName();
		races = RacesCap.get(mc.player);
		classes = ClassesCap.get(mc.player);
		ScaledResolution scaled = new ScaledResolution(this.mc);
		this.width = scaled.getScaledWidth();
		this.height = scaled.getScaledHeight();
		imgWidth = 256;
		imgHeight = 166;
		curRace = 0;
		curClass = 0;
		raceStr = races.raceName();
	}
	
	@Override
    public void initGui() 
    {
        this.buttonList.clear();
        this.buttonList.add(new MainButton(100, 1, this.width/2, this.height/2, "Next"));
//        this.buttonList.add(new ArrowButtons(20, 1, this.width/2 - 50, this.height/2, 15, 15, false));
        this.buttonList.add(new MainButton(23, 1, this.width/2 -120, this.height/2 - 55, "<"));
        this.buttonList.add(new MainButton(24, 1, this.width/2 - 50, this.height/2 - 55, ">"));
   
        Keyboard.enableRepeatEvents(true);
        super.initGui();

    }
	@Override
    public void updateScreen() 
    {
		raceStr = races.raceName();
		classStr = classes.className();
    }
	@Override
	public void drawScreen(int parWidth, int parHeight, float partialTicks)
	{
		this.oldMouseX = (float)parWidth;
        this.oldMouseY = (float)parHeight;
		drawDefaultBackground();
        this.drawGuiContainerBackgroundLayer(partialTicks, parWidth, parHeight);
		mc.getTextureManager().bindTexture(test);
		GlStateManager.pushMatrix();
		GlStateManager.scale(1.2f, 1.2f, 1);
		this.drawTexturedModalRect((int)(((this.width/2)-(imgWidth/2))/1.2), (int)(((this.height/2)-(imgHeight/2))/1.2), 0, 0, imgWidth, imgHeight);
		GlStateManager.popMatrix();
		this.drawCenteredString(fontRenderer, name, ((this.width/2)-(imgWidth/2)) + 244, ((this.height/2)-(imgHeight/2)) + 4, 0x3f48cc);
		this.fontRenderer.drawString("Race:", this.width/2 - 90, this.height/2 - 70, 0);
		this.fontRenderer.drawString(raceStr, this.width/2 - 92, this.height/2 - 50, 0);
		this.fontRenderer.drawString("Class:", this.width/2 - 90, this.height/2 - 30, 0);
		super.drawScreen(parWidth, parHeight, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
    {
		switch(button.id) {
			case 23:
				curRace--;
				curRace = SimpleUtil.buttonWrap(curRace, 0, 2);
				races.setRace(curRace);
				break;
			case 24:
				curRace++;
				curRace = SimpleUtil.buttonWrap(curRace, 0, 2);
				races.setRace(curRace);
				break;
			case 100:
				
				break;
		}
    }
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int i = (this.width/2)-(imgWidth/2);
        int j = (this.height/2)-(imgHeight/2);
        drawEntityOnScreen(i + 244, j + 128, 30, (float)(i + 244) - this.oldMouseX, (float)(j + 75 - 20) - this.oldMouseY, this.mc.player);
    }
	 public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
	    {
	        GlStateManager.enableColorMaterial();
	        GlStateManager.pushMatrix();
	        GlStateManager.translate((float)posX, (float)posY, 50.0F);
	        GlStateManager.scale((float)(-scale) *1.5, (float)scale *1.5, (float)scale *1.5);
	        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
	        float f = ent.renderYawOffset;
	        float f1 = ent.rotationYaw;
	        float f2 = ent.rotationPitch;
	        float f3 = ent.prevRotationYawHead;
	        float f4 = ent.rotationYawHead;
	        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
	        RenderHelper.enableStandardItemLighting();
	        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
	        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
	        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
	        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
	        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
	        ent.rotationYawHead = ent.rotationYaw;
	        ent.prevRotationYawHead = ent.rotationYaw;
	        GlStateManager.translate(0.0F, 0.0F, 0.0F);
	        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
	        rendermanager.setPlayerViewY(180.0F);
	        rendermanager.setRenderShadow(false);
	        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
	        rendermanager.setRenderShadow(true);
	        ent.renderYawOffset = f;
	        ent.rotationYaw = f1;
	        ent.rotationPitch = f2;
	        ent.prevRotationYawHead = f3;
	        ent.rotationYawHead = f4;
	        GlStateManager.popMatrix();
	        RenderHelper.disableStandardItemLighting();
	        GlStateManager.disableRescaleNormal();
	        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
	        GlStateManager.disableTexture2D();
	        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	    }
}
