package enstabretagne.BE.AnalyseSousMarine;

import java.util.HashMap;
import java.util.Random;

import enstabretagne.BE.AnalyseSousMarine.Scenarios.AnalyseSousMarineScenario;
import enstabretagne.BE.AnalyseSousMarine.Scenarios.AnalyseSousMarineScenarioFeatures;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefactFeatures;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.EntityArtefactInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.EntityBateauFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.EntityBateauInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOceanFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Ocean.EntityOceanInit;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinFeature;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.EntitySousMarinInit;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.implementation.MovableState;
import enstabretagne.simulation.components.ScenarioId;
import enstabretagne.simulation.core.IScenario;
import enstabretagne.simulation.core.IScenarioInstance;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class ScenarioInstanceAnalyseSousMarine implements IScenarioInstance {

	@Override
	public IScenario getScenarioInstance() {
		AnalyseSousMarineScenarioFeatures scenariFeatures = new AnalyseSousMarineScenarioFeatures("BSF");
		
		//Création du navire et des points de passage
		HashMap<String,Point3D> positionsCles = new HashMap<String, Point3D>();
		positionsCles.put("start", new Point3D(0,0,0));
		
		//Création sous marin
		MovableState mstSousMarin = new MovableState(new Point3D(0,0,0), new Point3D(1,1,0), Point3D.ZERO, new Point3D(0,0,45.0), new Point3D(10,5,0.0), Point3D.ZERO);
		EntityMouvementSequenceurInit msiSousMarin = new EntityMouvementSequenceurInit("MSI", mstSousMarin, 10, 100,2,8, positionsCles);
		EntityMouvementSequenceurFeature featSousMarin = new EntityMouvementSequenceurFeature("MSF");

		scenariFeatures.getSousMarins().put(new EntitySousMarinFeature("SousMarinF",5,3,Color.BLACK,featSousMarin), new EntitySousMarinInit("Sous marin Observation", msiSousMarin));

		
		//Création bateau
		positionsCles = new HashMap<String, Point3D>();
		MovableState mstBateau = new MovableState(new Point3D(10,10,10), new Point3D(0,0,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		EntityMouvementSequenceurInit msiBateau = new EntityMouvementSequenceurInit("MSI", mstBateau, 0, 0,0,0, positionsCles);
		scenariFeatures.getBateaux().put(new EntityBateauFeature("BateauF",10,6,Color.RED), new EntityBateauInit("Bateau Observation", msiBateau));

		// Variation du nombre d'artéfacts entre 30 et 40
		int borneSupOcean = 9999;
		int borneInfOcean=-10000;
		int borneInfEpaisseur = -3000;
		int borneSupEpaisseur = 0;
		Random rand = new Random(); 
		int nbreArtefact = rand.nextInt(40 - 30+1)+30;
		int nbreSphere = (nbreArtefact*60)/100;
		int nbreCylindre = (nbreArtefact*30)/100;
		int nbreCubique = (nbreArtefact*10)/100;

		
		// Création des artefacts sphériques
		positionsCles = new HashMap<String, Point3D>();
		for(int i=0;i<nbreSphere;i++) 
		 {
			int x = (int)rand.nextInt(borneSupOcean - (borneInfOcean) + 1) + borneInfOcean;
			int y= (int) rand.nextInt(borneSupOcean - (borneInfOcean) + 1) + borneInfOcean;
			int z = (int) rand.nextInt(borneSupEpaisseur - (borneInfEpaisseur) + 1) + borneInfEpaisseur;
			MovableState mstArtefactSphere;
			EntityMouvementSequenceurInit msiArtefactSphere;
			mstArtefactSphere = new MovableState(new Point3D(x,y,z), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiArtefactSphere= new EntityMouvementSequenceurInit("MSI", mstArtefactSphere, 0, 0,0,0, positionsCles);
			scenariFeatures.getArtefacts().put(new EntityArtefactFeatures("Sph 1",5,1,3.0,1), new EntityArtefactInit("Sph "+i,msiArtefactSphere,Color.RED));
		}
		
		//Création artéfact cylindre
		positionsCles = new HashMap<String, Point3D>();
		for(int i=0;i<nbreCylindre;i++) 
		{
			int x = (int)rand.nextInt(borneSupOcean - (borneInfOcean) + 1) + borneInfOcean;
			int y= (int) rand.nextInt(borneSupOcean - (borneInfOcean) + 1) + borneInfOcean;
			int z = (int) rand.nextInt(borneSupEpaisseur - (borneInfEpaisseur) + 1) + borneInfEpaisseur;
			MovableState mstArtefactCubique;
			EntityMouvementSequenceurInit msiArtefactCubique;
			mstArtefactCubique = new MovableState(new Point3D(x,y,z), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiArtefactCubique= new EntityMouvementSequenceurInit("MSI", mstArtefactCubique, 0, 0,0,0, positionsCles);
			scenariFeatures.getArtefacts().put(new EntityArtefactFeatures("Cub 1",5,1,3.0,2), new EntityArtefactInit("Cub "+i,msiArtefactCubique,Color.YELLOW));
			
		}
		
		//Création artéfact cubique
		positionsCles = new HashMap<String, Point3D>();
		for(int i=0;i<nbreCubique;i++) 
		{
			int x = (int)rand.nextInt(borneSupOcean - (borneInfOcean) + 1) + borneInfOcean;
			int y= (int) rand.nextInt(borneSupOcean - (borneInfOcean) + 1) + borneInfOcean;
			int z = (int) rand.nextInt(borneSupEpaisseur - (borneInfEpaisseur) + 1) + borneInfEpaisseur;
			MovableState mstArtefactCylindre;
			EntityMouvementSequenceurInit msiArtefactCyclindre;
			mstArtefactCylindre = new MovableState(new Point3D(10*i,0,0), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
			msiArtefactCyclindre= new EntityMouvementSequenceurInit("MSI", mstArtefactCylindre, 0, 0,0,0, positionsCles);
			scenariFeatures.getArtefacts().put(new EntityArtefactFeatures("Cyl 1",5,1,3.0,3), new EntityArtefactInit("Cyl "+i,msiArtefactCyclindre,Color.GREEN));
					
		}
		
		//Création du cube noir 
				positionsCles = new HashMap<String, Point3D>();
				for(int i=0;i<1;i++) 
				{
					int x = (int)rand.nextInt(borneSupOcean - (borneInfOcean) + 1) + borneInfOcean;
					int y= (int) rand.nextInt(borneSupOcean - (borneInfOcean) + 1) + borneInfOcean;
					int z = (int) rand.nextInt(borneSupEpaisseur - (borneInfEpaisseur) + 1) + borneInfEpaisseur;
					MovableState mstArtefactCylindre;
					EntityMouvementSequenceurInit msiArtefactCyclindre;
					mstArtefactCylindre = new MovableState(new Point3D(x,y,z), Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
					msiArtefactCyclindre= new EntityMouvementSequenceurInit("MSI", mstArtefactCylindre, 0, 0,0,0, positionsCles);
					scenariFeatures.getArtefacts().put(new EntityArtefactFeatures("CubN 1",5,1,3.0,4), new EntityArtefactInit("CubN "+i,msiArtefactCyclindre,Color.BLACK));
							
				}
		
		
		//Création de l'ocean
		positionsCles = new HashMap<String, Point3D>();
		MovableState mstOcean = new MovableState(Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO, Point3D.ZERO);
		EntityMouvementSequenceurInit msiOcean = new EntityMouvementSequenceurInit("MSIOCEAN", mstOcean, 0, 0,0,0, positionsCles);
		scenariFeatures.getOcean().put(new EntityOceanFeature("O1"), new EntityOceanInit("Atlantique", msiOcean));
		
		
		LogicalDateTime start = new LogicalDateTime("05/12/2017 06:00");
		LogicalDateTime end = start.add(LogicalDuration.ofMinutes(2));
		AnalyseSousMarineScenario bms = new AnalyseSousMarineScenario(new ScenarioId("S1"), scenariFeatures, start, end);
		
		return bms;
	}


}
