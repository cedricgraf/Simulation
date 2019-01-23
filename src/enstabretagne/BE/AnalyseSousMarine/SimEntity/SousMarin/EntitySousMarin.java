package enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceur;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceur_SousMarin;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.Representation3D.EntitySousMarin3DRepresentationInterface;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

@ToRecord(name="SousMarin")
public class EntitySousMarin extends SimEntity implements IMovable,EntitySousMarin3DRepresentationInterface{
	
	private EntityMouvementSequenceur rmv;
	private EntitySousMarinInit NavireInit;
	private EntitySousMarinFeature NavireFeature;
	
	public EntitySousMarin(String name, SimFeatures features) {
		super(name, features);
		NavireFeature = (EntitySousMarinFeature) features;
	}

	@Override
	public void onParentSet() {
		
	}

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		NavireInit = (EntitySousMarinInit) getInitParameters();

		rmv = (EntityMouvementSequenceur_SousMarin) createChild(EntityMouvementSequenceur_SousMarin.class, "monSequenceur", ((EntitySousMarinFeature) getFeatures()).getSeqFeature());
		rmv.initialize(NavireInit.getMvtSeqInitial());
	
	}

	@Override
	protected void BeforeActivating(IEntity sender, boolean starting) {
		
	}

	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation de Navire");
		rmv.activate();
	}
	

	@ToRecord(name="Position")
	@Override
	public Point3D getPosition() {
		return rmv.getPosition(getCurrentLogicalDate());
	}

	@ToRecord(name="Vitesse")
	@Override
	public Point3D getVitesse() {
		return rmv.getVitesse(getCurrentLogicalDate());
	}

	@ToRecord(name="Acceleration")
	@Override
	public Point3D getAcceleration() {
		return rmv.getAcceleration(getCurrentLogicalDate());
	}

	@ToRecord(name="VitesseRotation")
	@Override
	public Point3D getVitesseRotationXYZ() {
		return rmv.getVitesseRotationXYZ(getCurrentLogicalDate());
	}

	@ToRecord(name="AccelerationRotation")
	@Override
	public Point3D getAccelerationRotationXYZ() {
		return rmv.getAccelerationRotationXYZ(getCurrentLogicalDate());
	}

	@ToRecord(name="Rotation")
	@Override
	public Point3D getRotationXYZ() {
		return rmv.getRotationXYZ(getCurrentLogicalDate());
	}


	@Override
	protected void BeforeDeactivating(IEntity sender, boolean starting) {
		rmv.deactivate();
	}

	@Override
	protected void AfterDeactivated(IEntity sender, boolean starting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void AfterTerminated(IEntity sender, boolean restart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		return NavireFeature.getCouleur();
	}

	@Override
	public double getRayon() {
		return NavireFeature.getRayon();
	}

	@Override
	public double getLongueur() {
		return NavireFeature.getTaille();
	}

}
