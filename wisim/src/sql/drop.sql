## Filename:			"drop.sql"
## Project-Name: 		"WiSim"
## SQLAuthor: 			"Benjamin Pasero"
## Description: 		"Wirtschaftssimulation in einer HUB-Firma"
## Target DB:	 		"MySQL"
## Version: 			"0.9a"
## Datum: 				"24.02.2003"


##-------------------------------------------------------
##                  Relationships										
##-------------------------------------------------------

DROP TABLE rel_lt_et;

DROP TABLE rel_art_et;

DROP TABLE rel_et_lg;

DROP TABLE rel_et_ap;

DROP TABLE rel_art_lg;

DROP TABLE rel_at_art;

DROP TABLE rel_etat_et;

##----------------------------------------------------
##			Entities											  
##----------------------------------------------------

DROP TABLE mwst;

DROP TABLE lg;

DROP TABLE ap;

DROP TABLE lt;

DROP TABLE ort;

DROP TABLE kd;

DROP TABLE et;

DROP TABLE art;

DROP TABLE etatr;

DROP TABLE atr;

DROP TABLE at;

DROP TABLE etat;

DROP TABLE note;

DROP TABLE IF EXISTS nf;

DROP TABLE IF EXISTS vg;