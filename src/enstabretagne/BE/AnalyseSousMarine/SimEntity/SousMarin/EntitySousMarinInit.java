package enstabretagne.BE.AnalyseSousMarine.SimEntity.SousMarin;

import enstabretagne.BE.AnalyseSousMarine.SimEntity.MouvementSequenceur.EntityMouvementSequenceurInit;
import enstabretagne.simulation.components.data.SimInitParameters;

public class EntitySousMarinInit extends SimInitParameters {
	private EntityMouvementSequenceurInit mvtSeqIni;
	private String name;
	
	public EntitySousMarinInit(String nom,EntityMouvementSequenceurInit mvtSeqIni)
	{
		this.mvtSeqIni = mvtSeqIni;
		this.name = nom;
	}
	
	public String getName() {
		return name;
	}
	
	public EntityMouvementSequenceurInit getMvtSeqInitial() {
		return  mvtSeqIni;
	}

}
