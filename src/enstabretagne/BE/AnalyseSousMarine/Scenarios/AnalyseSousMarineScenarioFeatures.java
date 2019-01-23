package enstabretagne.BE.AnalyseSousMarine.Scenarios;

import java.util.HashMap;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefactFeatures;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefactInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.EntityBateauFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.EntityBateauInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOceanFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOceanInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinInit;
import enstabretagne.simulation.components.data.SimFeatures;

public class AnalyseSousMarineScenarioFeatures extends SimFeatures {

	private HashMap<EntityArtefactFeatures, EntityArtefactInit> artefacts;
	private HashMap<EntitySousMarinFeature, EntitySousMarinInit> sousMarins;
	private HashMap<EntityOceanFeature,EntityOceanInit> ocean;
	private HashMap<EntityBateauFeature, EntityBateauInit> bateaux;
	
	public HashMap<EntityOceanFeature, EntityOceanInit> getOcean() {
		return ocean;
	}
	public HashMap<EntitySousMarinFeature, EntitySousMarinInit> getSousMarins() {
		return sousMarins;
	}
	
	public HashMap<EntityArtefactFeatures, EntityArtefactInit> getArtefacts() {
		return artefacts;
	}
	
	public HashMap<EntityBateauFeature, EntityBateauInit> getBateaux() {
		return bateaux;
	}
	
	public AnalyseSousMarineScenarioFeatures(String id) {
		super(id);
		artefacts = new HashMap<>();
		sousMarins = new HashMap<>();
		ocean = new HashMap<>();
		bateaux = new HashMap<>();
	}	

}
