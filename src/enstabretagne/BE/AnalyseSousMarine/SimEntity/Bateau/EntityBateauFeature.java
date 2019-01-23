package enstabretagne.BE.AnalyseSousMarine.SimEntity.Bateau;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceurFeature;
import enstabretagne.simulation.components.data.SimFeatures;
import javafx.scene.paint.Color;

public class EntityBateauFeature extends SimFeatures {

	private double taille;
	private double rayon;
	private Color couleur;
	
	public EntityBateauFeature(String id,double taille,double rayon,Color couleur) {
		super(id);
		this.taille = taille;
		this.couleur = couleur;
		this.rayon=rayon;


	}

	public Color getCouleur() {
		return couleur;
	}
	
	public double getRayon() {
		return rayon;
	}
	public double getTaille() {
		return taille;
	}

	public EntityMouvementSequenceurFeature getSeqFeature() {
		return null;
	}

}
