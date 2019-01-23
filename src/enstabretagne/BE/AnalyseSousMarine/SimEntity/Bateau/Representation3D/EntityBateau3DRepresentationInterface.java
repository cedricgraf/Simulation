package enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau.Representation3D;

import enstabretagne.monitor.interfaces.IMovable;
import javafx.scene.paint.Color;

public interface EntityBateau3DRepresentationInterface extends IMovable{
	Color getColor();
	double getRayon();
	double getLongueur();

}
