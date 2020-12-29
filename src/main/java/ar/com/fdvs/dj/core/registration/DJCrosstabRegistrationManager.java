package ar.com.fdvs.dj.core.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DJCrosstab;
import ar.com.fdvs.dj.domain.DynamicJasperDesign;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.entities.Entity;

public class DJCrosstabRegistrationManager extends AbstractEntityRegistrationManager {

	private static final Logger log = LoggerFactory.getLogger(DJCrosstabRegistrationManager.class);
	private String type;
	
	public DJCrosstabRegistrationManager(String type, DynamicJasperDesign jd,  DynamicReport dr, LayoutManager layoutManager) {
		super(jd,dr,layoutManager);
		this.type = type;
	}

	protected void registerEntity(Entity entity) {
		log.debug("registering crosstab...");
		DJCrosstab crosstab = (DJCrosstab) entity;
		if (crosstab.getMeasures() != null && !crosstab.getMeasures().isEmpty()){
			DJCrosstabMeasureRegistrationManager measuresRm = new DJCrosstabMeasureRegistrationManager(type, getDjd(),getDynamicReport(),getLayoutManager());
			measuresRm.registerEntities(crosstab.getMeasures());
		}
	}

	protected Object transformEntity(Entity entity) {
		return null;
	}

}
