package ar.com.fdvs.dj.domain;

import ar.com.fdvs.dj.domain.entities.Entity;
import net.sf.jasperreports.charts.type.EdgeEnum;

/**
 * Labels to added in groups footer or headers variables (i.e: Subtotal).
 * 
 * A simple string can be used
 * 
 * @author mamana
 *
 */
public class DJGroupLabel extends DJLabel {

    private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;

    protected EdgeEnum edgeEnum = EdgeEnum.TOP;

    public DJGroupLabel() {
    }

    public DJGroupLabel(String text, Style labelStyle) {
        super(text, labelStyle);
    }

    public DJGroupLabel(String text, Style labelStyle, EdgeEnum edgeEnum) {
        super(text, labelStyle, edgeEnum);
        this.edgeEnum = edgeEnum;
    }

    public DJGroupLabel(CustomExpression labelExpression, Style labelStyle, EdgeEnum EdgeEnum) {
        super(labelExpression, labelStyle);
        this.edgeEnum = EdgeEnum;
    }

    public EdgeEnum getEdgeEnum() {
        return edgeEnum;
    }

    public void setEdgeEnum(EdgeEnum EdgeEnum) {
        this.edgeEnum = EdgeEnum;
    }

}
