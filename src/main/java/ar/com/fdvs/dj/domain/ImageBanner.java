/*
 * DynamicJasper: A library for creating reports dynamically by specifying
 * columns, groups, styles, etc. at runtime. It also saves a lot of development
 * time in many cases! (http://sourceforge.net/projects/dynamicjasper)
 *
 * Copyright (C) 2008  FDV Solutions (http://www.fdvsolutions.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 *
 * License as published by the Free Software Foundation; either
 *
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *
 */

package ar.com.fdvs.dj.domain;

import ar.com.fdvs.dj.domain.entities.Entity;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;

public class ImageBanner extends DJBaseElement {

	private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

	private String imagePath;
	private int width = 0;
	private int height= 0;
	private HorizontalImageAlignEnum align = HorizontalImageAlignEnum.LEFT;

	private ScaleImageEnum scaleMode = ScaleImageEnum.RETAIN_SHAPE;
	
	public ImageBanner(){}

    public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public HorizontalImageAlignEnum getAlign() {
		return align;
	}

	public void setAlign(HorizontalImageAlignEnum orientation) {
		this.align = orientation;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public ScaleImageEnum getScaleMode() {
		return scaleMode;
	}

	public void setScaleMode(ScaleImageEnum scaleMode) {
		this.scaleMode = scaleMode;
	}

	public ImageBanner(String imagePath, int width, int height, HorizontalImageAlignEnum align) {
		this.imagePath = imagePath;
		this.width = width;
		this.height = height;
		this.align = align!=null?align:HorizontalImageAlignEnum.LEFT;
	}

}
