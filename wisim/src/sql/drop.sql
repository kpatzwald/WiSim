##/*   ********************************************************************   **
##**   Copyright notice                                                       **
##**                                                                          **
##**   (c) 2003-2021 WiSim Development Team                                   **
##**   https://github.com/kpatzwald/WiSim                                     **
##**                                                                          **
##**   All rights reserved                                                    **
##**                                                                          **
##**   This script is part of the WiSim Business Game project. The WiSim      **
##**   project is free software; you can redistribute it and/or modify        **
##**   it under the terms of the GNU General Public License as published by   **
##**   the Free Software Foundation; either version 2 of the License, or      **
##**   (at your option) any later version.                                    **
##**                                                                          **
##**   The GNU General Public License can be found at                         **
##**   http://www.gnu.org/copyleft/gpl.html.                                  **
##**   A copy is found in the textfile GPL.txt and important notices to the   **
##**   license from the team is found in the textfile LICENSE.txt distributed **
##**   in these package.                                                      **
##**                                                                          **
##**   This copyright notice MUST APPEAR in all copies of the file!           **
##**   ********************************************************************   */

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