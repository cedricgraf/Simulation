package enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.Representation3D.IArtefactRepresentation3D;
import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceur;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.monitor.interfaces.IMovable;
import enstabretagne.simulation.components.IEntity;
import enstabretagne.simulation.components.data.SimFeatures;
import enstabretagne.simulation.components.data.SimInitParameters;
import enstabretagne.simulation.components.implementation.SimEntity;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

@ToRecord(name="Artefact")
public class EntityArtefact extends SimEntity implements IMovable,IArtefactRepresentation3D{
	

	
	private EntityMouvementSequenceur rmv;

	public EntityArtefact(String name, SimFeatures features) {
		super(name, features);
	}

	@Override
	public void onParentSet() {
		
	}

	@Override
	protected void initializeSimEntity(SimInitParameters init) {
		EntityArtefactInit bi = (EntityArtefactInit) getInitParameters();
		
		rmv = (EntityMouvementSequenceur) createChild(EntityMouvementSequenceur.class, "monSequenceur", ((EntityArtefactFeatures) getFeatures()).getSeqFeature());
		rmv.initialize(bi.getMvtSeqInit());
	}

	@Override
	protected void BeforeActivating(IEntity sender, boolean starting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void AfterActivate(IEntity sender, boolean starting) {
		Logger.Detail(this, "AfterActivate", "Activation de la bouée %s","test");
		rmv.activate();
//		Logger.Data(this);
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

	@Override
	public Color getColor() {
		return ((EntityArtefactInit) getInitParameters()).getColor();
	}

	@Override
	public double getSize() {
		return ((EntityArtefactFeatures) getFeatures()).getTaille();
	}
	
	@Override
	public int getType() {

		return ((EntityArtefactFeatures) getFeatures()).getType();
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
	public Point3D getVitesseRotationXYZ() {
		return rmv.getVitesseRotationXYZ(getCurrentLogicalDate());
	}

	@Override
	public Point3D getAccelerationRotationXYZ() {
		return rmv.getAccelerationRotationXYZ(getCurrentLogicalDate());
	}

	@Override
	public Point3D getRotationXYZ() {
		return rmv.getRotationXYZ(getCurrentLogicalDate());
	}

	

}
