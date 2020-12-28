package ar.com.fdvs.dj.test.util;

import java.awt.Color;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Transparency;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

public class StyleFactory {

	public static Style createDetailStyle(String name){
		Style detailStyle = new Style(name);
		detailStyle.setVerticalTextAlignEnum(VerticalTextAlignEnum.TOP);
		return detailStyle;
	}

	public static Style createGroupTileStyle(String name){
		Style groupTitleStyle = new Style(name);
		groupTitleStyle.setFont(Font.ARIAL_BIG);
		return groupTitleStyle;
	}

	public static Style createGroupDetailStyle(String name){
		Style style = new Style(name);
		style.setFont(Font.ARIAL_BIG_BOLD);
		style.setBorderBottom(Border.THIN());
		style.setVerticalTextAlignEnum(VerticalTextAlignEnum.TOP);
		return style;
	}

	public static Style createHeaderStyle(String name){
		Style headerStyle = new Style(name);
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBackgroundColor(Color.gray);
		headerStyle.setTextColor(Color.white);
		headerStyle.setHorizontalTextAlignEnum(HorizontalTextAlignEnum.CENTER);
		headerStyle.setVerticalTextAlignEnum(VerticalTextAlignEnum.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);
		return headerStyle;
	}
	public static Style createHeaderStyle2(String name){
		Style headerStyle = new Style(name);
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setHorizontalTextAlignEnum(HorizontalTextAlignEnum.CENTER);
		headerStyle.setBorderBottom(Border.THIN());
		headerStyle.setVerticalTextAlignEnum(VerticalTextAlignEnum.MIDDLE);
		headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
		headerStyle.setTransparency(Transparency.OPAQUE);
		return headerStyle;
	}

	public static Style createGroupVariableStyle(String name){
		Style style = new Style(name);
		style.setFont(Font.ARIAL_MEDIUM_BOLD);
		style.setBorderTop(Border.THIN());
		style.setHorizontalTextAlignEnum(HorizontalTextAlignEnum.RIGHT);
		style.setVerticalTextAlignEnum(VerticalTextAlignEnum.MIDDLE);
		style.setTextColor(new Color(50,50,150));
		return style;
	}

	public static Style createGroup2VariableStyle(String name){
		Style style = new Style(name);
		style.setFont(Font.ARIAL_MEDIUM_BOLD);
		style.setTextColor(new Color(150,150,150));
		style.setHorizontalTextAlignEnum(HorizontalTextAlignEnum.RIGHT);
		style.setVerticalTextAlignEnum(VerticalTextAlignEnum.MIDDLE);
		return style;
	}

	public static Style createTitleStyle(String name){
		Style titleStyle = new Style(name);
		titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
		return titleStyle;
	}

}
