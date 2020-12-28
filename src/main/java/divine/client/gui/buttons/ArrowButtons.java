package divine.client.gui.buttons;

import divine.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ArrowButtons extends GuiButton{
	
	
    protected static final ResourceLocation resource = new ResourceLocation(Reference.MODID + ":textures/gui/buttons.png");
    FontRenderer fontrenderer;
	private float scaleFactor;
//	private boolean leftArrow;

	public ArrowButtons(int id, float sF, int x, int y, int width, int height, boolean leftArrow) {
		super(id, x, y, width, height, "");
		this.scaleFactor = sF;
		this.visible = true;
		this.width = width;
		this.height = height;
//		this.leftArrow = leftArrow;
	}
	@Override
	 public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	    {
		 if (this.visible)
	        {
	            GlStateManager.pushMatrix();
			 	fontrenderer = mc.fontRenderer;
	            mc.getTextureManager().bindTexture(resource);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            int i = this.getHoverState(this.hovered);
	            GlStateManager.enableBlend();
	            GlStateManager.scale(this.scaleFactor, this.scaleFactor, 1);
	            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	            drawTexturedModalRect(x, y, 203, -15 + i * 15, width / 2, height);
	  	      	drawTexturedModalRect(x + width / 2, y, 220 - width / 2, -15 + i * 15, width / 2, height);
	            this.mouseDragged(mc, mouseX, mouseY);
	            int j = 14737632;
	            
	            if (packedFGColour != 0)
	            {
	                j = packedFGColour;
	            }
	            else
	            if (!this.enabled)
	            {
	                j = 10526880;
	            }
	            else if (this.hovered)
	            {
	                j = 16777120;
	            }
	            
	            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
	            GlStateManager.popMatrix();
	        }
	    }
	public void drawLeftArrow() {
		
	}
	
	
	public int getId()
	{
		return this.id;
	}

}
