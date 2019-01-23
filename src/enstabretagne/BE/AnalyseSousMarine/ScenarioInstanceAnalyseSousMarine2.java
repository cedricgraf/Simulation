package enstabretagne.BE.AnalyseSousMarine;

import java.util.HashMap;

import enstabretagne.BE.AnalyseSousMarine.Scenarios.AnalyseSousMarineScenario;
import enstabretagne.BE.AnalyseSousMarine.Scenarios.AnalyseSousMarineScenarioFeatures;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefactFeatures;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefactInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOceanFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOceanInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinInit;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.implementation.MovableState;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.core.IScenario;
import enstabretagne.simulation.core.IScenarioInstance;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class ScenarioInstanceAnalyseSousMarine2<BasicMvtScenario> implements IScenarioInstance {

	@Override
	public IScenario getScenarioInstance() {
		AnalyseSousMarineScenarioFeatures bsf = new AnalyseSousMarineScenarioFeatures("BSF");
		
		//Création du navire et des points de passage
		HashMap<String,Point3D> positionsCles = new HashMap<String, Point3D>();
		positionsCles.put("start", new Point3D(0,0,0));
		positionsCles.put("PointCible1", new Point3D(10,10,0));
		positionsCles.put("PointCible2", new Point3D(30,-10,0));
		positionsCles.put("PointCible3", new Point3D(20,0,0));
		positionsCles.put("PointDirection", new Point3D(20,20,0));
		positionsCles.put("PointSousEau", new Point3D(0,0,-10));
		positionsCles.put("ObservationMine", new Point3D(20,20,-20));

		
		MovableState mst; 
		EntityMouvementSequenceurInit msi; 
		EntityMouvementSequenceurFeature feat; 

		//Navire1
		mst= new MovableState(new Point3D(0,0,0), new Point3D(1,1,0), Point3D.ZERO, new Point3D(0,0,45.0), new Point3D(10,5,0.0), Point3D.ZERO);
		msi= new EntityMouvementSequenceurInit("MSI", mst, 10, 100,2,8, positionsCles);
		feat = new EntityMouvementSequenceurFeature("MSF");
		bsf.getSousMarins().put(new EntitySousMarinFeature("NavireF",5,3,Color.GREEN,feat), new EntitySousMarinInit("Navire Observation 2", msi));

		//Navire2
		mst = new MovableState(new Point3D(10,0,0), new Point3D(1,1,0), Point3D.ZERO, new Point3D(0,0,45.0), new Point3D(10,5,0.0), Point3D.ZERO);
		msi = new EntityMouvementSequenceurInit("MSI", mst, 10, 100,2,8, positionsCles);
		feat = new EntityMouvementSequenceurFeature("MSF");
		bsf.getSousMarins().put(new EntitySousMarinFeature("NavireF",5,3,Color.GREEN,feat), new EntitySousMarinInit("Navire Observation 2", msi));

		//Navire3
		mst = new MovableState(new Point3D(20,0,0), new Point3D(1,1,0), Point3D.ZERO, new Point3D(0,0,45.0), new Point3D(10,5,0.0), Point3D.ZERO);
		msi = new EntityMouvementSequenceurInit("MSI", mst, 10, 100,2,8, positionsCles);
		feat = new EntityMouvementSequenceurFeature("MSF");
		bsf.getSousMarins().put(new EntitySousMarinFeature("NavireF",5,3,Color.GREEN,feat), new EntitySousMarinInit("Navire Observation 2", msi));

		//Création de bouees
		int i=0;
		int N=10;
		positionsCles = new HashMap<String, Point3D>();
		for(i=0;i<N;i++) 
		{
			MovableState mstBouee;
			EntityMouvementSequenceurInit msiBouee;
			
			
			mstBouee = new MovableState(new Point3D(10*i,0,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiBouee= new EntityMouvementSequenceurInit("MSI", mstBouee, 0, 0,0,0, positionsCles);
			bsf.getArtefacts().put(new EntityArtefactFeatures("B1",5,1,3.0,1), new EntityArtefactInit("B"+i,msiBouee,Color.RED));

			
		}
		
		//Création de l'ocean
		positionsCles = new HashMap<String, Point3D>();
		MovableState mstOcean = new MovableState(Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		EntityMouvementSequenceurInit msiOcean = new EntityMouvementSequenceurInit("MSIOCEAN", mstOcean, 0, 0,0,0, positionsCles);
		bsf.getOcean().put(new EntityOceanFeature("O1"), new EntityOceanInit("Atlantique", msiOcean));
		
		
		LogicalDateTime start = new LogicalDateTime("05/12/2017 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));
		AnalyseSousMarineScenario bms = new AnalyseSousMarineScenario(new ScenarioId("S2"), bsf, start, end);
		
		return bms;
	}


}
