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

package ar.com.fdvs.dj.test.variables;

import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import ar.com.fdvs.dj.test.BaseDjReportTest;
import ar.com.fdvs.dj.test.TestRepositoryProducts;
import ar.com.fdvs.dj.util.SortUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 * This test demonstrates how to add a variable into a group and use it later inside an custom expression.
 */
public class GroupsVariableTest2 extends BaseDjReportTest {

	public DynamicReport buildReport() throws Exception {

		Style detailStyle = new Style("detail");

		Style headerStyle = new Style("header");
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderBottom(Border.PEN_1_POINT());
		headerStyle.setBackgroundColor(Color.gray);
		headerStyle.setTextColor(Color.white);
		headerStyle.setHorizontalTextAlignEnum(HorizontalTextAlignEnum.CENTER);
		headerStyle.setVerticalTextAlignEnum(VerticalTextAlignEnum.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);

		Style headerVariables = new Style("headerVariables");
		headerVariables.setFont(Font.ARIAL_BIG_BOLD);
		headerVariables.setBorderBottom(Border.THIN());
		headerVariables.setHorizontalTextAlignEnum(HorizontalTextAlignEnum.RIGHT);
		headerVariables.setVerticalTextAlignEnum(VerticalTextAlignEnum.TOP);
		headerVariables.setStretchWithOverflow(true);

		Style groupVariables = new Style("groupVariables");
		groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
		groupVariables.setTextColor(Color.BLUE);
		groupVariables.setBorderBottom(Border.THIN());
		groupVariables.setHorizontalTextAlignEnum(HorizontalTextAlignEnum.RIGHT);
		groupVariables.setVerticalTextAlignEnum(VerticalTextAlignEnum.BOTTOM);

		Style titleStyle = new Style("titleStyle");
		titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
		Style importeStyle = new Style();
		importeStyle.setHorizontalTextAlignEnum(HorizontalTextAlignEnum.RIGHT);
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER());
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);

		DynamicReportBuilder drb = new DynamicReportBuilder();
		Integer margin = 20;
		drb
			.setTitleStyle(titleStyle)
			.setTitle("November " + getYear() +" sales report")					//defines the title of the report
			.setSubtitle("The items in this report correspond "
					+"to the main products: DVDs, Books, Foods and Magazines")
			.setDetailHeight(15).setLeftMargin(margin)
			.setRightMargin(margin).setTopMargin(margin).setBottomMargin(margin)
			.setPrintBackgroundOnOddRows(true)
			.setGrandTotalLegend("Grand Total")
			.setGrandTotalLegendStyle(headerVariables)
			.setOddRowBackgroundStyle(oddRowStyle);


		AbstractColumn columnState = ColumnBuilder.getNew()
				.setColumnProperty("state", String.class.getName()).setTitle(
						"State").setWidth(new Integer(85))
				.setStyle(titleStyle).setHeaderStyle(titleStyle).build();

		AbstractColumn columnBranch = ColumnBuilder.getNew()
				.setColumnProperty("branch", String.class.getName()).setTitle(
						"Branch").setWidth(new Integer(85)).setStyle(
						detailStyle).setHeaderStyle(headerStyle).build();

		AbstractColumn columnaProductLine = ColumnBuilder.getNew()
				.setColumnProperty("productLine", String.class.getName())
				.setTitle("Product Line").setWidth(new Integer(85)).setStyle(
						detailStyle).setHeaderStyle(headerStyle).build();

		AbstractColumn columnaItem = ColumnBuilder.getNew()
				.setColumnProperty("item", String.class.getName()).setTitle(
						"Item").setWidth(new Integer(85)).setStyle(detailStyle)
				.setHeaderStyle(headerStyle).build();

		AbstractColumn columnCode = ColumnBuilder.getNew()
				.setColumnProperty("id", Long.class.getName()).setTitle("ID")
				.setWidth(new Integer(40)).setStyle(importeStyle)
				.setHeaderStyle(headerStyle).build();

		AbstractColumn columnaQuantity = ColumnBuilder.getNew()
				.setColumnProperty("quantity", Long.class.getName())
                .setTitle("Quantity")
                .setWidth(new Integer(25))
                .setStyle(importeStyle)
                .setHeaderStyle(headerStyle).build();

		AbstractColumn columnAmount = ColumnBuilder.getNew()
				.setColumnProperty("amount", Float.class.getName()).setTitle(
						"Amount").setWidth(new Integer(100))
				.setPattern("$ 0.00").setStyle(importeStyle).setHeaderStyle(
						headerStyle).build();

//		drb.addGlobalHeaderVariable(columnAmount, DJCalculation.SUM,headerVariables);
//		drb.addGlobalHeaderVariable(columnaQuantity, DJCalculation.SUM,headerVariables);
//		drb.addGlobalFooterVariable(columnAmount, DJCalculation.SUM,headerVariables);
//		drb.addGlobalFooterVariable(columnaQuantity, DJCalculation.SUM,headerVariables);
//		drb.setGlobalHeaderVariableHeight(25);
//		drb.setGlobalFooterVariableHeight(25);

		GroupBuilder gb1 = new GroupBuilder();

//		 define the criteria column to group by (columnState)
		gb1.setCriteriaColumn((PropertyColumn) columnState)
				.setGroupLayout(GroupLayout.VALUE_IN_HEADER)
				.setFooterVariablesHeight(20)
				.setFooterHeight(50,true)
				.setHeaderVariablesHeight(100)
				.addHeaderVariable(columnaProductLine, new CustomExpression() {
					public Object evaluate(Map fields, Map variables, Map parameters) {
						String msg = "ALL field values in header: ";
						Iterator it = fields.keySet().iterator();
						while (it.hasNext()) {
							String key = (String) it.next();
							Object value = fields.get(key);
							msg += key + ":" + value + " ::: ";
						}
						Long idVar = (Long) variables.get("id"); //And here we use it!

						msg += " ::: id_var: " + idVar;
						return msg;
					}

					public String getClassName() {
						return String.class.getName();
					}
				})
                .addFooterVariable(columnAmount, new CustomExpression() {
                    public Object evaluate(Map fields, Map variables, Map parameters) {
                    	String msg = "ALL field values in footer: ";
						Iterator it = variables.keySet().iterator();
						while (it.hasNext()) {
							String key = (String) it.next();
							Object value = variables.get(key);
							msg += key + ":" + value + "; ";
						}
                        return msg;
                    }

                    public String getClassName() {
                        return String.class.getName();
                    }
                })
		;

		drb.addColumn(columnState);
		drb.addColumn(columnBranch);
		drb.addColumn(columnaItem);
		drb.addColumn(columnaProductLine);
		drb.addColumn(columnCode);
		drb.addColumn(columnaQuantity);
		drb.addColumn(columnAmount);

		for (Object column: drb.getColumns()) {
			PropertyColumn abstractColumn = (PropertyColumn) column;
			gb1.addVariable("var_" +abstractColumn.getColumnProperty().getProperty(), abstractColumn,DJCalculation.NOTHING);
		}

		DJGroup g1 = gb1.build();

		drb.addGroup(g1); // add group g1

		drb.setUseFullPageWidth(true);
		drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);

		DynamicReport dr = drb.build();
		return dr;
	}

	@Override
	protected JRDataSource getDataSource() {
		Collection dummyCollection = TestRepositoryProducts.getDummyCollectionSmallVariation1();
		dummyCollection = SortUtils.sortCollection(dummyCollection, dr.getColumns());

		//here contains dummy hardcoded objects...
		return new JRBeanCollectionDataSource(dummyCollection);
	}

	public static void main(String[] args) throws Exception {
		GroupsVariableTest2 test = new GroupsVariableTest2();
		test.testReport();
		test.exportToJRXML();
		JasperViewer.viewReport(test.jp);
		JasperDesignViewer.viewReportDesign(test.jr);
	}

}