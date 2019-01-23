package enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact.Representation3D;


import enstabretagne.monitor.Contrat3D;
import enstabretagne.monitor.ObjTo3DMappingSettings;
import enstabretagne.monitor.implementation.Representation3D;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

@Contrat3D(contrat = IArtefactRepresentation3D.class)
public class Artefact3DRepresentation2 extends Representation3D {
	
	public Artefact3DRepresentation2(ObjTo3DMappingSettings settings) {
		super(settings);
	}

	IArtefactRepresentation3D Artefact3D;
	Group monArtefact;
	@Override
	public void init(Group world, Object obj) {
		Artefact3D = (IArtefactRepresentation3D) obj;
		monArtefact = new Group();
	    
	    PhongMaterial material = new PhongMaterial(Artefact3D.getColor());

	    if(Artefact3D.getType()==1){
	    	 Sphere s = new Sphere(Artefact3D.getSize());
	    	 s.setMaterial(material);
	    	 monArtefact.getChildren().add(s);
	    }else if(Artefact3D.getType()==2){
	    	Cylinder c = new Cylinder(Artefact3D.getSize(), Artefact3D.getSize());
	    	 c.setMaterial(material);
	    	 monArtefact.getChildren().add(c);
	    }else if(Artefact3D.getType()==3){
	    	Box b = new Box(Artefact3D.getSize(), Artefact3D.getSize(), Artefact3D.getSize());
	    	b.setMaterial(material);
	    	 monArtefact.getChildren().add(b);
	    	 
	    }else {
	    	Box b = new Box(Artefact3D.getSize(), Artefact3D.getSize(), Artefact3D.getSize());
	    	b.setMaterial(material);
	    	 monArtefact.getChildren().add(b);
	    }

	    world.getChildren().add(monArtefact);
	    

	}
	
	@Override
	public void update() {
		Point3D p = Artefact3D.getPosition();

		monArtefact.setTranslateX(p.getX());
		monArtefact.setTranslateY(p.getY());
		monArtefact.setTranslateZ(p.getZ());

	}


}
	