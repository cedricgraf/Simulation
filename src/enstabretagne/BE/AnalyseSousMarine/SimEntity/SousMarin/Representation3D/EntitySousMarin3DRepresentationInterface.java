package enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin.Representation3D;

import enstabretagne.monitor.interfaces.IMovable;
import javafx.scene.paint.Color;

public interface EntitySousMarin3DRepresentationInterface extends IMovable{
	Color getColor();
	double getRayon();
	double getLongueur();

}