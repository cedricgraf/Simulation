package enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.Representation3D;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.Representation3D.EntitySousMarin3DRepresentationInterface;
import enstabretagne.monitor.Contrat3D;
import enstabretagne.monitor.ObjTo3DMappingSettings;
import enstabretagne.monitor.implementation.Representation3D;
import enstabretagne.monitor.implementation.XYZRotator2;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

@Contrat3D(contrat = EntityBateau3DRepresentationInterface.class)
public class EntityBateau3DRepresentation  extends Representation3D {
	
	public EntityBateau3DRepresentation(ObjTo3DMappingSettings settings) {
		super(settings);
	}

	EntityBateau3DRepresentationInterface Bateau3D;
	Group bateau;
	int r1=10;
	int h=10;
	
	//ici mettre les objets3D représentant l'entité 
	//Sphere sph;

	@Override
	public void init(Group world, Object obj) {
		Bateau3D = (EntityBateau3DRepresentationInterface) obj;
	    bateau = new Group();
	    
	    
	    PhongMaterial material = new PhongMaterial(Bateau3D.getColor());

	    Cylinder cy = new Cylinder(r1, h*8); //h*8
	    cy.setMaterial(material);
	    cy.setRotationAxis(Rotate.Z_AXIS);
	    cy.setRotate(90.0);
	    cy.setTranslateX(-h/2);
	    bateau.getChildren().add(cy);

	    Sphere s = new Sphere(r1);
	    s.setMaterial(material);
	    s.setTranslateX(h/2);
	    bateau.getChildren().add(s);
	    
	    double c = r1;
	    Box box = new Box(c,c,c);
	    material = new PhongMaterial(Color.BLUEVIOLET);
	    box.setMaterial(material);
	    box.setTranslateZ(r1);
	    box.setTranslateX(2*c);
	    bateau.getChildren().add(box);
	    int c1 = 15;

	    Box box1 = new Box(c1,c1,c1);
	    material = new PhongMaterial(Color.BLUEVIOLET);
	    box1.setMaterial(material);
	    box1.setTranslateZ(r1);
	    box1.setTranslateX(-2*c);
	    bateau.getChildren().add(box1);

		world.getChildren().add(bateau);


	}

	@Override
	public void update() {
		Point3D p = Bateau3D.getPosition();

		bateau.setTranslateX(p.getX());
		bateau.setTranslateY(p.getY());
		bateau.setTranslateZ(p.getZ());
		
		Point3D rot = Bateau3D.getRotationXYZ();
		
		Affine a = XYZRotator2.getTransformByAngle(rot);
		bateau.getTransforms().setAll(a);

	}


}
