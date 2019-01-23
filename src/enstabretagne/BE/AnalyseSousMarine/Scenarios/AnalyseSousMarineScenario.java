package enstabretagne.BE.AnalyseSousMarine.Scenarios;

import java.util.Map;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefact;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefactFeatures;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefactInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.EntityBateau;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.EntityBateauFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.EntityBateauInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOcean;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOceanFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOceanInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarin;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinInit;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import enstabretagne.simulation.components.implementation.SimScenario;
import enstabretagne.simulation.core.implementation.SimEvent;

public class AnalyseSousMarineScenario extends SimScenario{

	public AnalyseSousMarineScenario(ScenarioId id, SimFeatures features, LogicalDateTime start, LogicalDateTime end) {
		super(id, features, start, end);
		
	}
	
	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		super.initializeSimEntity(init);
	}
	
	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		super.AfterActivate(sender, starting);
		
		AnalyseSousMarineScenarioFeatures feature = (AnalyseSousMarineScenarioFeatures) getFeatures();
		Logger.Detail(this, "afteractivate", "taille liste d'artefacts = %s", feature.getArtefacts().size());

		for(Map.Entry<EntityArtefactFeatures, EntityArtefactInit> e : feature.getArtefacts().entrySet())
		{
			Logger.Detail(this, "afteractivate", "Artefact à créer = %s , %s", e.getValue(),e.getKey());
			Post(new ArtefactArrival(e.getValue(),e.getKey()));
		}
		for(Map.Entry<EntitySousMarinFeature, EntitySousMarinInit> e : feature.getSousMarins().entrySet())
		{
			Logger.Detail(this, "afteractivate", "Sous Marin à créer = %s , %s", e.getValue(),e.getKey());
			Post(new SousMarinArrival(e.getValue(),e.getKey()));
		}
		/*
		for(Map.Entry<EntityBateauFeature, EntityBateauInit> e : feature.getBateaux().entrySet())
		{
			Logger.Detail(this, "afteractivate", "Bateau à créer = %s , %s", e.getValue(),e.getKey());
			Post(new BateauArrival(e.getValue(),e.getKey()));
		}
		*/
		
		for(Map.Entry<EntityOceanFeature, EntityOceanInit> e : feature.getOcean().entrySet())
		{
			Logger.Detail(this, "afteractivate", "Océan à créer = %s , %s", e.getValue(),e.getKey());
			Post(new OceanArrival(e.getValue(),e.getKey()));
		}
	}
	
	class ArtefactArrival extends SimEvent
	{
		private EntityArtefactInit i;
		private EntityArtefactFeatures f;
		public EntityArtefactInit getI() {
			return i;
		}
		
		public EntityArtefactFeatures getF() {
			return f;
		}
		
		
		public ArtefactArrival(EntityArtefactInit i, EntityArtefactFeatures f) {
			this.i=i;
			this.f=f;
		}

		@Override
		public void Process() {
			Logger.Detail(this, "ArteefactArrival.Process", "Création de l' artéfact " + i);
			SimEntity b = createChild(EntityArtefact.class, i.getName() , f);
			b.initialize(getI());
			b.activate();
		}
		
	}

	class SousMarinArrival extends SimEvent
	{
		private EntitySousMarinInit i;
		private EntitySousMarinFeature f;
		
		public EntitySousMarinInit getI() {
			return i;
		}
		
		public EntitySousMarinFeature getF() {
			return f;
		}
		
		
		public SousMarinArrival(EntitySousMarinInit i, EntitySousMarinFeature f) {
			this.i=i;
			this.f=f;
		}

		@Override
		public void Process() {
			Logger.Detail(this, "SousMarinArrival.Process", "Création du Sous marin " + i);
			SimEntity b = createChild(EntitySousMarin.class, i.getName() , f);
			b.initialize(getI());
			b.activate();
		}
		
	}
	

	class OceanArrival extends SimEvent
	{
		private EntityOceanInit i;
		private EntityOceanFeature f;
		
		public EntityOceanInit getI() {
			return i;
		}
		
		public EntityOceanFeature getF() {
			return f;
		}
		
		
		public OceanArrival(EntityOceanInit i, EntityOceanFeature f) {
			this.i=i;
			this.f=f;
		}

		@Override
		public void Process() {
			Logger.Detail(this, "OceanArrival.Process", "Création de l'océan " + i);
			SimEntity b = createChild(EntityOcean.class, i.getName() , f);
			b.initialize(getI());
			b.activate();
		}
		
	}
	
	class BateauArrival extends SimEvent
	{
		private EntityBateauInit i;
		private EntityBateauFeature f;
		
		public EntityBateauInit getI() {
			return i;
		}
		
		public EntityBateauFeature getF() {
			return f;
		}
		
		
		public BateauArrival(EntityBateauInit i, EntityBateauFeature f) {
			this.i=i;
			this.f=f;
		}

		@Override
		public void Process() {
			Logger.Detail(this, "SousMarinArrival.Process", "Création du Bateau " + i);
			SimEntity b = createChild(EntityBateau.class, i.getName() , f);
			b.initialize(getI());
			b.activate();
		}
		
	}

}
