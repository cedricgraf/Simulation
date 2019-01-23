package enstabretagne.BE.AnalyseSousMarine.SimEntity.Artefact;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;
import enstabretagne.simulation.components.data.SimInitParameters;
import javafx.scene.paint.Color;

public class EntityArtefactInit extends SimInitParameters {
	private String name;
	private Color couleur;
	
	private EntityMouvementSequenceurInit mvtSeqInit;
	
	
	public EntityArtefactInit(String nom,EntityMouvementSequenceurInit mvtSeqInit,Color c)
	{
		this.name = nom;
		this.couleur=c;
		this.mvtSeqInit = mvtSeqInit;
	}
	
	public EntityMouvementSequenceurInit getMvtSeqInit() {
		return mvtSeqInit;
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return couleur;
	}
	
	

}
