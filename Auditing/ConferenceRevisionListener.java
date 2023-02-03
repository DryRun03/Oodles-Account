package com.oodles.Auditing;

import org.hibernate.envers.RevisionListener;

import com.oodles.domain.RevInfo;

/**
 * @author Shivangi
 */
public class ConferenceRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		RevInfo entity = (RevInfo) revisionEntity;
		entity.setAuditorId(AuditorDetails.auditorId);
		entity.setAuditorName(AuditorDetails.auditorName);

	}

}
