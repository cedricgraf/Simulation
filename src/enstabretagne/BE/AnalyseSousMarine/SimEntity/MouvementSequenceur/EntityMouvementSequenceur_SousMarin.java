package enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.IMover;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.monitor.implementation.XYZRotator2;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.core.implementation.SimEvent;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;

@ToRecord(name="MouvementSequenceur")
public class EntityMouvementSequenceur_SousMarin extends EntityMouvementSequenceur implements IMover{
	
	
	public EntityMouvementSequenceur_SousMarin(String name, SimFeatures features) {
		super(name, features);
	}

	@Override
	public void onParentSet() {
		
	}

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		super.initializeSimEntity(init);
	}


	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation de MouvementSequenceur");
		//attente
		Post(new FinStaticPhase1(),LogicalDuration.ofSeconds(1));
	}
	

	public class FinStaticPhase1 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("start").add(new Point3D(10,10,0)), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new BugCorrection(),mv.getDurationToReach());
		}
	}

	public class BugCorrection extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinCircularPhase3", "Fin de la troisème phase");
			LogicalDateTime d = getCurrentLogicalDate();
			Point3D dir = new Point3D(1,1,0);
			selfRotator = new SelfRotator(d, mv.getPosition(d), mv.getVitesse(d), mv.getPosition(d).add(dir),ini.getMaxSelfRotationSpeed());
			mv= selfRotator;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Logger.Information(Owner(), "Process Arret", "Mode Circulaire : %s", mv.getPosition(d));
			Post(new FinLinearPhase2(),mv.getDurationToReach());
			
		}
	}

	public class FinLinearPhase2 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinLinearPhase2", "Fin de la deuxième phase");
			LogicalDateTime d = getCurrentLogicalDate();
			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("start").add( new Point3D(30,-10,0)));
			mv= circulrMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Logger.Information(Owner(), "Process Arret", "Mode Circulaire2 : %s", mv.getPosition(d));
			Post(new FinCircularPhase3(),mv.getDurationToReach());
		}
	}
	
	public class FinCircularPhase3 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinCircularPhase3", "Fin de la troisème phase");
			LogicalDateTime d = getCurrentLogicalDate();
			selfRotator = new SelfRotator(d, mv.getPosition(d), mv.getVitesse(d), ini.getPositionsCles().get("start").add(new Point3D(20,0,0)),ini.getMaxSelfRotationSpeed());
			mv= selfRotator;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Logger.Information(Owner(), "Process Arret", "Mode Circulaire3 : %s", mv.getPosition(d));
			Post(new FinSelfRotatePhase4(),mv.getDurationToReach());
			
		}
	}

	public class FinSelfRotatePhase4 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première phase statique");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("start").add(new Point3D(20,0,0)), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new FinLinearPhase5(),mv.getDurationToReach());
		}
	}
	
	public class FinLinearPhase5 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinLinearPhase5", "FinLinearPhase5");
			LogicalDateTime d = getCurrentLogicalDate();
			selfRotator = new SelfRotator(d, mv.getPosition(d), mv.getVitesse(d), ini.getPositionsCles().get("start").add( new Point3D(20,20,0)),ini.getMaxSelfRotationSpeed());
			mv= selfRotator;
			Logger.Information(Owner(), "Process FinLinearPhase5", "FinLinearPhase5");
			Post(new FinRotatorPhase6(),mv.getDurationToReach());
			
		}
	}
	
	public class FinRotatorPhase6 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinRotatorPhase6", "FinRotatorPhase6");
			LogicalDateTime d = getCurrentLogicalDate();
			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("start"));
			mv= circulrMover;
			Logger.Information(Owner(), "Process FinRotatorPhase6", "FinRotatorPhase6");
			Post(new FinCircularPhase7(),mv.getDurationToReach());
		}
	}

	public class FinCircularPhase7 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinCircularPhase7", "FinCircularPhase7");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), mv.getVitesse(d), ini.getPositionsCles().get("start").add( new Point3D(0,0,-10)), ini.getMaxPlongeeSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinCircularPhase7", "FinCircularPhase7");
			Post(new FinPlongee(),mv.getDurationToReach());
		}
	}

	public class FinPlongee extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinPlongee", "FinPlongee");
			LogicalDateTime d = getCurrentLogicalDate();
			
			Point3D dir = XYZRotator2.getTransformByAngle(mv.getRotationXYZ(d)).transform(Rotate.X_AXIS);; 
			selfRotator = new SelfRotator(d, mv.getPosition(d), dir, ini.getPositionsCles().get("start").add(new Point3D(20,20,-20)),ini.getMaxSelfRotationSpeed());
			mv= selfRotator;
			Post(new AtteinteCible(),mv.getDurationToReach());
			
		}
	}

	public class AtteinteCible extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process AtteinteCible", "AtteinteCible");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), new Point3D(20,20,-20), 10);
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process AtteinteCible", "AtteinteCible");
			Post(new RotationUne(),mv.getDurationToReach());
		}
	}
	
	//Mouvement du sous sous marin
	
	public class RotationUne extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinDescente", "FinDescente");
			LogicalDateTime d = getCurrentLogicalDate();
			
			Point3D dir = XYZRotator2.getTransformByAngle(mv.getRotationXYZ(d)).transform(Rotate.X_AXIS);; 
			selfRotator = new SelfRotator(d, mv.getPosition(d), dir, ini.getPositionsCles().get("start"),ini.getMaxSelfRotationSpeed());
			mv= selfRotator;
			Post(new PremiereMontee(),mv.getDurationToReach());
			
		}
	}
	
	public class PremiereMontee extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinStaticPhase1", "Fin de la première rotation");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), ini.getPositionsCles().get("start"), ini.getMaxLinearSpeed());
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement linéaire enclenché");
			Post(new ArcDeCercle(),mv.getDurationToReach());
		}
	}

	public class ArcDeCercle extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinCircularPhase3", "Fin de la troisème phase");
			LogicalDateTime d = getCurrentLogicalDate();
			Point3D dir = new Point3D(-1,-1,0);
			selfRotator = new SelfRotator(d, mv.getPosition(d), mv.getVitesse(d), mv.getPosition(d).add(dir),ini.getMaxSelfRotationSpeed());
			mv= selfRotator;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Logger.Information(Owner(), "Process Arret", "Mode Circulaire : %s", mv.getPosition(d));
			Post(new FinArcDeCercle(),mv.getDurationToReach());
			
		}
	}

	public class FinArcDeCercle extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinLinearPhase2", "Fin de la deuxième phase");
			LogicalDateTime d = getCurrentLogicalDate();
			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("start").add(new Point3D(-30,10,0)));
			mv= circulrMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Logger.Information(Owner(), "Process Arret", "Mode Circulaire2 : %s", mv.getPosition(d));
			Post(new PremierDescente(),mv.getDurationToReach());
		}
	}
	
	public class PremierDescente extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process AtteinteCible", "AtteinteCible");
			LogicalDateTime d = getCurrentLogicalDate();
			rectilinearMover = new RectilinearMover(d, mv.getPosition(d), new Point3D(30,70,-20), 10);
			mv= rectilinearMover;
			Logger.Information(Owner(), "Process AtteinteCible", "AtteinteCible");
			Post(new ArcDeCercle1(),mv.getDurationToReach());
		}
	}
	
	public class ArcDeCercle1 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinCircularPhase3", "Fin de la troisème phase");
			LogicalDateTime d = getCurrentLogicalDate();
			Point3D dir = new Point3D(1,1,0);
			selfRotator = new SelfRotator(d, mv.getPosition(d), mv.getVitesse(d), mv.getPosition(d).add(dir),ini.getMaxSelfRotationSpeed());
			mv= selfRotator;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Logger.Information(Owner(), "Process Arret", "Mode Circulaire : %s", mv.getPosition(d));
			Post(new FinArcDeCercle1(),mv.getDurationToReach());
			
		}
	}

	public class FinArcDeCercle1 extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process FinLinearPhase2", "Fin de la deuxième phase");
			LogicalDateTime d = getCurrentLogicalDate();
			circulrMover = new CircularMover(d, mv.getPosition(d), mv.getVitesse(d).normalize().multiply(ini.getMaxLinearSpeed()), ini.getPositionsCles().get("start").add(new Point3D(-30,10,0)));
			mv= circulrMover;
			Logger.Information(Owner(), "Process FinStaticPhase1", "Phase mouvement circulaire enclenché");
			Logger.Information(Owner(), "Process Arret", "Mode Circulaire2 : %s", mv.getPosition(d));
			Post(new Arret(),mv.getDurationToReach());
		}
	}
	
	

	public class Arret extends SimEvent {

		@Override
		public void Process() {
			Logger.Information(Owner(), "Process Arret", "Fin de la phase");
			LogicalDateTime d = getCurrentLogicalDate();
			staticMover =new StaticMover(mv.getPosition(d), mv.getVitesse(d));			
			Logger.Information(Owner(), "Process Arret", "Mode arrêt : %s", mv.getPosition(d));
			mv = staticMover;
		}
		
	}

}
