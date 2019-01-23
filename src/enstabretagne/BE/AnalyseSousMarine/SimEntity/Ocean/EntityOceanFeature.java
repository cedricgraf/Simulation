package enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;
import enstabretagne.simulation.components.data.SimFeatures;

public class EntityOceanFeature extends SimFeatures {

	
	public EntityOceanFeature(String id) {
		super(id);
	}
	
	public EntityMouvementSequenceurFeature getSeqFeature() {
		return null;
	}


}
