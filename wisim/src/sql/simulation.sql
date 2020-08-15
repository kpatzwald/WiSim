##/*   ********************************************************************   **
##**   Copyright notice                                                       **
##**                                                                          **
##**   (c) 2003 WiSim Development Team					                              **
##**   http://wisim.sourceforge.net/   			                                  **
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

## Filename:			"complete.sql"
## Project-Name: 		"WiSim"
## SQLAuthor: 			"Benjamin Pasero"
## Description: 		"Wirtschaftssimulation in einer HUB-Firma"
## Target DB:	 		"MySQL"
## Version: 			"1.0a"
## Datum: 				"04.03.2003"


##-------------------------------------------------------
##                  Relationships
##-------------------------------------------------------

DROP TABLE IF EXISTS rel_et_lg;

DROP TABLE IF EXISTS rel_et_ap;

DROP TABLE IF EXISTS rel_art_lg;


#
# Tabellenstruktur für Tabelle `rel_art_lg`
#

CREATE TABLE rel_art_lg (
  f_lg_StellplatzNr varchar(50) NOT NULL default '0',
  f_art_Nr int(10) unsigned NOT NULL default '0',
  rel_art_lg_Bestand int(10) unsigned NOT NULL default '0',
  rel_art_lg_MaxBestand int(10) unsigned NOT NULL default '0',
  FOREIGN KEY (f_art_Nr) REFERENCES art (art_Nr),
  FOREIGN KEY (f_lg_StellplatzNr) REFERENCES lg (lg_StellplatzNr),
  PRIMARY KEY  (f_lg_StellplatzNr,f_art_Nr)
) ENGINE=MyISAM COMMENT='Relationship: Artikel_Lager (Artikellagerplatz)';

#
# Daten für Tabelle `rel_art_lg`
#

INSERT INTO rel_art_lg VALUES ("C16", 1, 600, 1000);
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `rel_et_ap`
#

CREATE TABLE rel_et_ap (
  f_ap_Nr int(10) unsigned NOT NULL default '0',
  f_et_Nr int(11) NOT NULL default '0',
  rel_et_ap_Lagertyp enum('Eingang','Ausgang','Einzelteil') NOT NULL default 'Einzelteil',
  rel_et_ap_Bestand int(10) unsigned NOT NULL default '0',
  rel_et_ap_MaxBestand int(10) unsigned NOT NULL default '0',
  rel_et_ap_Benoetigt int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (f_ap_Nr,f_et_Nr,rel_et_ap_Lagertyp)
) ENGINE=MyISAM COMMENT='Relationship: Einzelteil_Arbeitsplatz (ArbeitsplatzEinzeltei';

#
# Daten für Tabelle `rel_et_ap`
#

INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (1, 8, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (1, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (1, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (2, 4, 'Einzelteil', 1200, 1200, 24);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (2, 5, 'Einzelteil', 2400, 2400, 48);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (2, 6, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (2, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (2, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (3, 13, 'Einzelteil', 1200, 1200, 24);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (3, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (3, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (4, 16, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (4, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (4, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (5, 17, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (5, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (5, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (6, 1, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (6, 7, 'Einzelteil', 250, 250, 5);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (6, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (6, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (7, 12, 'Einzelteil', 250, 250, 5);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (7, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (7, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (8, 15, 'Einzelteil', 100, 100, 2);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (8, 23, 'Einzelteil', 400, 400, 8);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (8, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (8, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (9, 24, 'Einzelteil', 100, 100, 2);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (9, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (9, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (10, 3, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (10, 23, 'Einzelteil', 200, 200, 4);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (10, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (10, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (11, 9, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (11, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (11, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (12, 14, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (12, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (12, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (13, 19, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (13, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (13, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (14, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (14, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (15, 2, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (15, 22, 'Einzelteil', 200, 200, 4);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (15, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (15, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (16, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (16, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (17, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (17, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (18, 25, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (18, 18, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (18, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (18, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (19, 21, 'Einzelteil', 200, 200, 4);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (19, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (19, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (20, 27, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (20, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (20, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (21, 28, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (21, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (21, 26, 'Ausgang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (22, 20, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (22, 10, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (22, 11, 'Einzelteil', 50, 50, 1);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (22, 26, 'Eingang', 0, 10, 0);
INSERT INTO rel_et_ap (f_ap_Nr, f_et_Nr, rel_et_ap_Lagertyp, rel_et_ap_Bestand, rel_et_ap_MaxBestand, rel_et_ap_Benoetigt) VALUES (22, 26, 'Ausgang', 0, 10, 0);
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `rel_et_lg`
#

CREATE TABLE rel_et_lg (
  f_lg_StellplatzNr varchar(50) NOT NULL default '0',
  f_et_Nr int(11) NOT NULL default '0',
  rel_et_lg_Bestand int(10) unsigned NOT NULL default '0',
  rel_et_lg_MaxBestand int(10) unsigned NOT NULL default '0',
  FOREIGN KEY (f_et_Nr) REFERENCES et (et_Nr),
  FOREIGN KEY (f_lg_StellplatzNr) REFERENCES lg (lg_StellplatzNr),
  PRIMARY KEY  (f_lg_StellplatzNr,f_et_Nr)
) ENGINE=MyISAM COMMENT='Relationship: Einzeilteil_Lager (EinzelteilLagerplatz)';

#
# Daten für Tabelle `rel_et_lg`
#

INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A10', 1, 1350, 1500);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A11', 2, 1300, 1500);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A12', 3, 1000, 1500);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A13', 4, 20000, 24000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A14', 5, 30000, 48000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A15', 6, 14000, 15000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A16', 7, 4000, 5000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A17', 8, 900, 1000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A18', 9, 1500, 2000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('A19', 10, 7000, 10000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B10', 11, 20000, 30000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B11', 12, 15000, 20000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B12', 13, 200, 16000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B13', 14, 1000, 1000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B14', 15, 1800, 2000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B15', 16, 8000, 10000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B16', 17, 8000, 10000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B17', 18, 20000, 40000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B18', 19, 4000, 5000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('B10', 20, 800, 1000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('C11', 21, 6000, 8000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('C12', 22, 12000, 20000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('C13', 23, 7000, 8000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('C14', 24, 1300, 2000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('C15', 25, 20000, 40000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('C17', 27, 1500, 2000);
INSERT INTO rel_et_lg (f_lg_StellplatzNr, f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand) VALUES ('C18', 28, 1500, 2000);
# --------------------------------------------------------
