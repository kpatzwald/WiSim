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

DROP TABLE IF EXISTS rel_lt_et;

DROP TABLE IF EXISTS rel_art_et;

DROP TABLE IF EXISTS rel_et_lg;

DROP TABLE IF EXISTS rel_et_ap;

DROP TABLE IF EXISTS rel_art_lg;

DROP TABLE IF EXISTS rel_at_art;

DROP TABLE IF EXISTS rel_etat_et;

##----------------------------------------------------
##			Entities
##----------------------------------------------------

DROP TABLE IF EXISTS mwst;

DROP TABLE IF EXISTS lg;

DROP TABLE IF EXISTS ap;

DROP TABLE IF EXISTS lt;

DROP TABLE IF EXISTS ort;

DROP TABLE IF EXISTS kd;

DROP TABLE IF EXISTS et;

DROP TABLE IF EXISTS art;

DROP TABLE IF EXISTS etatr;

DROP TABLE IF EXISTS atr;

DROP TABLE IF EXISTS at;

DROP TABLE IF EXISTS etat;

DROP TABLE IF EXISTS note;

DROP TABLE IF EXISTS nf;

DROP TABLE IF EXISTS vg;


# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `ap`
#

CREATE TABLE ap (
  ap_Nr int(10) unsigned NOT NULL default '0',
  ap_Beschreibung varchar(50) NOT NULL default '',
  ap_Dauer int(10) unsigned NOT NULL default '0',
  ap_AnzArbeiter int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (ap_Nr)
) TYPE=MyISAM COMMENT='Entity: Arbeitsplatz';

#
# Daten für Tabelle `ap`
#

INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (1, 'Funktionstest der Platine', 5, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (2, 'LEDs auf Platine löten', 10, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (3, 'Ports auf die Platine löten', 10, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (4, 'Uplink-Schalter auf die Platine löten', 5, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (5, 'Netz-Schalter anbringen', 2, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (6, 'Gehäuse-Unterteil mit Abstandshalter bestücken', 4, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (7, 'Platine auf Abstandshalter setzen und verschrauben', 5, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (8, '2 Lüfter ins Gehäuse einbauen', 5, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (9, 'Schutzgitter für die Lüfter anbringen', 2, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (10, 'Netzteil anschrauben', 2, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (11, 'Kabel auf Platine stecken', 2, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (12, 'Sicherungshalterung und Sicherung montieren', 5, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (13, 'Folie auf Frontfolie kleben', 1, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (14, 'Funktionstest', 10, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (15, 'Gehäuse schließen und verschrauben', 1, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (16, 'Endtest', 10, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (17, 'Prüfprotokoll erstellen', 5, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (18, 'Garantiesiegel, Etikett mit Seriennummer aufkleben', 1, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (19, 'Gummifüße anbringen', 1, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (20, 'Karton falten', 2, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (21, 'Karton mit Verpackungspopcorn füllen', 1, 1);
INSERT INTO ap (ap_Nr, ap_Beschreibung, ap_Dauer, ap_AnzArbeiter) VALUES (22, 'Hub verpacken mit Handbuch und CD', 2, 1);
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `art`
#

CREATE TABLE art (
  art_Nr int(10) unsigned NOT NULL auto_increment,
  art_Name varchar(50) NOT NULL default '',
  art_Stueckpreis decimal(12,2) unsigned NOT NULL default '0.00',
  art_Mindestbestand int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (art_Nr)
) TYPE=MyISAM COMMENT='Entity: Artikel';

#
# Daten für Tabelle `art`
#

INSERT INTO art VALUES (1, "Hub", 250, 500);
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `at`
#

CREATE TABLE at (
  at_Nr int(10) unsigned NOT NULL auto_increment,
  at_Lieferdatum date NOT NULL default '0000-00-00',
  at_Skonto decimal(4,2) unsigned NOT NULL default '0.00',
  at_Skontofrist int(10) unsigned NOT NULL default '0',
  at_Rabatt decimal(4,2) unsigned NOT NULL default '0.00',
  at_Datum date NOT NULL default '0000-00-00',
  f_kd_Nr int(10) unsigned NOT NULL default '0',
  f_atr_Nr int(10) unsigned NOT NULL default '0',
  FOREIGN KEY (f_atr_Nr) REFERENCES atr (atr_Nr),
  FOREIGN KEY (f_kd_Nr) REFERENCES kd (kd_Nr),
  PRIMARY KEY  (at_Nr)
) TYPE=MyISAM COMMENT='Entity: Auftrag';

#
# Daten für Tabelle `at`
#

INSERT INTO at VALUES (1, '2003-09-2', '3.00', 0, '5.00', '2003-08-28', 1, 1);
INSERT INTO at VALUES (2, '2003-09-3', '3.00', 0, '5.00', '2003-08-29', 2, 2);
INSERT INTO at VALUES (3, '2003-09-4', '3.00', 0, '5.00', '2003-08-30', 3, 3);

# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `atr`
#

CREATE TABLE atr (
  atr_Nr int(10) unsigned NOT NULL auto_increment,
  atr_Betrag decimal(12,2) unsigned NOT NULL default '0.00',
  f_at_Nr int(10) unsigned NOT NULL default '0',
  f_mwst_Satz decimal(4,2) unsigned NOT NULL default '0.00',
  atr_zleingang enum('TRUE','FALSE') NOT NULL default 'FALSE',
  FOREIGN KEY (f_at_Nr) REFERENCES at (at_Nr),
  FOREIGN KEY (f_mwst_Satz) REFERENCES mwst (mwst_Satz),
  PRIMARY KEY  (atr_Nr)
) TYPE=MyISAM COMMENT='Entity: AuftragsRechnung';

#
# Daten für Tabelle `atr`
#

INSERT INTO atr VALUES (1, '2700.00', 1, '16.00','FALSE');
INSERT INTO atr VALUES (2, '5400.00', 2, '16.00','FALSE');
INSERT INTO atr VALUES (3, '8100.00', 3, '16.00','FALSE');

# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `et`
#

CREATE TABLE et (
  et_Nr int(11) NOT NULL auto_increment,
  et_Name varchar(50) NOT NULL default '',
  et_Mindestbestand int(11) NOT NULL default '0',
  PRIMARY KEY  (et_Nr)
) TYPE=MyISAM COMMENT='Entity: Einzelteil';

#
# Daten für Tabelle `et`
# Mindestbestände reichen aus für 500 HUBs

INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (1, 'Gehäuse Unterteil', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (2, 'Gehäuse Oberteil', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (3, 'Netzteil', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (4, 'LED (gelb)', 12000);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (5, 'LED (grün)', 24000);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (6, 'LED (rot)', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (7, 'Abstandshalter', 2500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (8, 'Platine', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (9, 'Stromkabel', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (10, 'Handbuch', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (11, 'CD', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (12, 'Schrauben für Platine', 2500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (13, 'RJ45-Buchsen', 12000);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (14, 'Sicherung', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (15, 'Lüfter', 1000);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (16, 'Uplink-Schalter', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (17, 'Netzschalter', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (18, 'Etikett mit Seriennummer', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (19, 'Frontfolie', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (20, 'Verpackungsfolie', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (21, 'Gummifuß', 2000);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (22, 'Gehäuseschrauben', 2000);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (23, 'Netzteil- und Lüfterschrauben', 6000);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (24, 'Schutzgitter für Lifter', 1000);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (25, 'Garantiesiegel', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (26, 'Unfertiges Hub', 0);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (27, 'Verpackungskarton', 500);
INSERT INTO et (et_Nr, et_Name, et_Mindestbestand) VALUES (28, 'Verpackungspopcorn', 500);

# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `etat`
#

CREATE TABLE etat (
  etat_Nr int(10) unsigned NOT NULL auto_increment,
  etat_Lieferrabatt decimal(4,2) unsigned NOT NULL default '0.00',
  etat_Skontofrist int(10) unsigned NOT NULL default '0',
  etat_Lieferdatum date NOT NULL default '0000-00-00',
  etat_Datum date NOT NULL default '0000-00-00',
  etat_Skonto decimal(4,2) unsigned NOT NULL default '0.00',
  f_lt_Nr int(10) unsigned NOT NULL default '0',
  f_etatr_Nr int(10) unsigned NOT NULL,
  FOREIGN KEY (f_etatr_Nr) REFERENCES etatr (etatr_Nr),
  FOREIGN KEY (f_lt_Nr) REFERENCES lt (lt_Nr),
  PRIMARY KEY  (etat_Nr)
) TYPE=MyISAM COMMENT='EinzelTeilAuftrag';

#
# Daten für Tabelle `etat`
#

INSERT INTO etat VALUES (3, '3.50', 4, '2003-09-04', '2003-08-27', '1.50', 3, 6);
INSERT INTO etat VALUES (2, '1.00', 5, '2003-09-03', '2003-08-28', '3.00', 2, 5);
INSERT INTO etat VALUES (1, '5.00', 5, '2003-09-02', '2003-08-29', '3.00', 1, 4);

# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `etatr`
#

CREATE TABLE etatr (
  etatr_Nr int(11) NOT NULL auto_increment,
  etatr_Betrag decimal(12,2) unsigned NOT NULL default '0.00',
  f_etat_Nr int(10) unsigned NOT NULL,
  f_mwst_Satz decimal(4,2) unsigned NOT NULL default '0.00',
  FOREIGN KEY (f_etat_Nr) REFERENCES etat (etat_Nr),
  FOREIGN KEY (f_mwst_Satz) REFERENCES mwst (mwst_Satz),
  PRIMARY KEY  (etatr_Nr)
) TYPE=MyISAM COMMENT='Entity: EinzelteilAuftragsRechnung';

#
# Daten für Tabelle `etatr`
#

INSERT INTO etatr VALUES (3, '1500.00', 4, '16.00');
INSERT INTO etatr VALUES (2, '17000.00', 5, '16.00');
INSERT INTO etatr VALUES (1, '3350.00', 6, '16.00');

# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `kd`
#

CREATE TABLE kd (
  kd_Nr int(10) unsigned NOT NULL auto_increment,
  kd_Typ enum('A','B','C') NOT NULL default 'B',
  kd_Anspruch enum('','Preisfeilscher','Service-Fan','Termin-Fan','Qualitäts-Fan','Atmosphäre-Typ') NOT NULL default '',
  kd_Zahlungsmoral enum('1','2','3','4','5','6') NOT NULL default '3',
  kd_Firma varchar(50) NOT NULL default '',
  kd_Name varchar(50) NOT NULL default '',
  kd_Vorname varchar(50) NOT NULL default '',
  kd_Strasse varchar(50) NOT NULL default '',
  kd_Email varchar(50) default NULL,
  kd_Telefon varchar(30) NOT NULL default '',
  kd_Fax varchar(30) default NULL,
  kd_deleted enum('TRUE','FALSE') NOT NULL default 'FALSE',
  f_ort_Nr int(5) unsigned NOT NULL default '0',
  PRIMARY KEY  (kd_Nr)
) TYPE=MyISAM COMMENT='Entity: Kunde';

#
# Daten für Tabelle `kd`
#

INSERT INTO kd VALUES (1, 'B', '', '3', 'ABC AG', 'Peters', 'Benjamin', 'Rotherstr. 9', 'peters@abc.com', '030-123455', '030-123456', 'FALSE', 1);
INSERT INTO kd VALUES (2, 'B', '', '3', 'ABC AG', 'Adams', 'Christian', 'Rotherstr. 9', 'adams@abc.com', '030-123455', '030-123456', 'FALSE', 1);
INSERT INTO kd VALUES (3, 'B', '', '3', 'ABC AG', 'English', 'Denise', 'Rotherstr. 9', 'english@abc.com', '030-123455', '030-123456', 'FALSE', 1);
INSERT INTO kd VALUES (4, 'B', '', '3', 'ABC AG', 'Miller', 'Janett', 'Rotherstr. 9', 'mill@abc.com', '030-123455', '030-123456', 'FALSE', 1);
INSERT INTO kd VALUES (5, 'B', '', '3', 'ABC AG', 'Anderson', 'Kay', 'Rotherstr. 9', 'anderson@abc.com', '030-123455', '030-123456', 'FALSE', 1);
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `lg`
#

CREATE TABLE lg (
  lg_StellplatzNr varchar(50) NOT NULL default '',
  PRIMARY KEY  (lg_StellplatzNr)
) TYPE=MyISAM COMMENT='Entity: Lager';

#
# Daten für Tabelle `lg`
#

INSERT INTO lg VALUES ('A10');
INSERT INTO lg VALUES ('A11');
INSERT INTO lg VALUES ('A12');
INSERT INTO lg VALUES ('A13');
INSERT INTO lg VALUES ('A14');
INSERT INTO lg VALUES ('A15');
INSERT INTO lg VALUES ('A16');
INSERT INTO lg VALUES ('A17');
INSERT INTO lg VALUES ('A18');
INSERT INTO lg VALUES ('A19');
INSERT INTO lg VALUES ('B10');
INSERT INTO lg VALUES ('B11');
INSERT INTO lg VALUES ('B12');
INSERT INTO lg VALUES ('B13');
INSERT INTO lg VALUES ('B14');
INSERT INTO lg VALUES ('B15');
INSERT INTO lg VALUES ('B16');
INSERT INTO lg VALUES ('B17');
INSERT INTO lg VALUES ('B18');
INSERT INTO lg VALUES ('B19');
INSERT INTO lg VALUES ('C10');
INSERT INTO lg VALUES ('C11');
INSERT INTO lg VALUES ('C12');
INSERT INTO lg VALUES ('C13');
INSERT INTO lg VALUES ('C14');
INSERT INTO lg VALUES ('C15');
INSERT INTO lg VALUES ('C16');
INSERT INTO lg VALUES ('C17');
INSERT INTO lg VALUES ('C18');
INSERT INTO lg VALUES ('C19');

# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `lt`
#

CREATE TABLE lt (
  lt_Nr int(10) unsigned NOT NULL auto_increment,
  lt_Name varchar(50) NOT NULL default '',
  lt_Vorname varchar(50) NOT NULL default '',
  lt_Strasse varchar(50) NOT NULL default '',
  lt_Firma varchar(50) NOT NULL default '',
  lt_Email varchar(50) default NULL,
  lt_Telefon varchar(30) NOT NULL default '',
  lt_Fax varchar(30) default NULL,
  lt_deleted enum('TRUE','FALSE') NOT NULL default 'FALSE',
  f_ort_Nr int(5) unsigned NOT NULL default '0',
  lt_Qualitaet enum('1','2','3','4','5','6') NOT NULL default '3',
  lt_Zuverlaessigkeit enum('1','2','3','4','5','6') NOT NULL default '3',
  PRIMARY KEY  (lt_Nr)
) TYPE=MyISAM COMMENT='Entity: Lieferant';

#
# Daten für Tabelle `lt`
#

INSERT INTO lt VALUES (1, 'Mustermann', 'Peter', 'Ledstr. 9', 'Led Meister', 'peter.mustermann@ledmeister.de', '030-123455', '030-123456', 'FALSE', 1, '3', '3');
INSERT INTO lt VALUES (2, 'Musterfrau', 'Petra', 'Gehäusestr. 9', 'Gehäuse Meister', 'petra.mustermann@gehaeusemeister.de', '030-123455', '030-123456', 'FALSE', 1, '3', '3');
INSERT INTO lt VALUES (3, 'Strolch', 'Martin', 'Zubehoerstr. 9', 'Zubehoer Meister', 'peter.strolch@zubehoermeister.de', '030-123455', '030-123456', 'FALSE', 1, '3', '3');
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `mwst`
#

CREATE TABLE mwst (
  mwst_satz decimal(4,2) unsigned NOT NULL default '0.00',
  PRIMARY KEY  (mwst_satz)
) TYPE=MyISAM;

#
# Daten für Tabelle `mwst`
#
INSERT INTO mwst VALUES ('16.00');


# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `note`
#

CREATE TABLE note (
  note_Nr int(10) unsigned NOT NULL auto_increment,
  note_txt text,
  note_Date date NOT NULL default '0000-00-00',
  f_kd_Nr int(10) unsigned NOT NULL default '0',
  FOREIGN KEY (f_kd_Nr) REFERENCES kd (kd_Nr),
  PRIMARY KEY  (note_Nr)
) TYPE=MyISAM;

#
# Daten für Tabelle `note`
#

INSERT INTO note VALUES (1, 'Dies ist der Kunde Benjamin Peters.', '2003-03-03', 1);
INSERT INTO note VALUES (2, 'Dies ist der Kunde Christian Adams.', '2003-03-03', 2);
INSERT INTO note VALUES (3, 'Dies ist die Kundin Denise English.', '2003-03-03', 3);
INSERT INTO note VALUES (4, 'Dies ist die Kundin Janett Miller.', '2003-03-04', 4);
INSERT INTO note VALUES (5, 'Dies ist der Kunde Kay Anderson.', '2003-03-04', 5);
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `ort`
#

CREATE TABLE ort (
  ort_Nr INT UNSIGNED NOT NULL AUTO_INCREMENT,
  ort_PLZ varchar(5) NOT NULL default '',
  ort_Name varchar(50) NOT NULL default '',
  PRIMARY KEY  (ort_Nr)
) TYPE=MyISAM;

#
# Daten für Tabelle `ort`
#

INSERT INTO ort VALUES (1, '10711', 'Berlin');
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `nf`
#

CREATE TABLE nf (
  f_ap_nr int(10) NOT NULL default '0',
  nf_nachfolger int(10) NOT NULL default '0',
  PRIMARY KEY  (f_ap_nr,nf_nachfolger)
) TYPE=MyISAM COMMENT='Nachfolger';

#
# Daten für Tabelle `nf`
#

INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (1, 2);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (2, 3);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (3, 4);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (4, 5);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (5, 7);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (6, 7);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (7, 8);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (8, 9);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (9, 10);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (10, 11);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (11, 12);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (12, 14);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (13, 14);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (14, 15);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (15, 16);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (16, 17);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (17, 18);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (18, 22);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (19, 22);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (20, 21);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (21, 22);
INSERT INTO nf (f_ap_nr, nf_nachfolger) VALUES (22, 0);

# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `vg`
#

CREATE TABLE vg (
  f_ap_nr int(10) NOT NULL default '0',
  vg_vorgaenger int(10) NOT NULL default '0',
  PRIMARY KEY  (f_ap_nr,vg_vorgaenger)
) TYPE=MyISAM COMMENT='Vorgaenger';

#
# Daten für Tabelle `vg`
#

INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (1, 0);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (2, 1);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (3, 2);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (4, 3);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (5, 4);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (6, 0);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (7, 5);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (7, 6);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (8, 7);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (9, 8);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (10, 9);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (11, 10);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (12, 11);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (13, 0);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (14, 12);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (14, 13);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (15, 14);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (16, 15);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (17, 16);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (18, 17);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (19, 0);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (20, 0);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (21, 20);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (22, 18);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (22, 19);
INSERT INTO vg (f_ap_nr, vg_vorgaenger) VALUES (22, 21);
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `rel_art_et`
#

CREATE TABLE rel_art_et (
  f_et_Nr int(11) NOT NULL default '0',
  f_art_Nr int(10) unsigned NOT NULL default '0',
  rel_art_et_EinzelteileMenge int(10) unsigned NOT NULL default '0',
  FOREIGN KEY (f_art_Nr) REFERENCES art (art_Nr),
  FOREIGN KEY (f_et_Nr) REFERENCES et (et_Nr),
  PRIMARY KEY  (f_et_Nr,f_art_Nr)
) TYPE=MyISAM COMMENT='Relationship: Artikel_Einzelteil (Stueckliste)';

#
# Daten für Tabelle `rel_art_et`
#

INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (1, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (2, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (3, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (4, 1, 24);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (5, 1, 48);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (6, 1, 6);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (7, 1, 5);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (8, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (9, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (10, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (11, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (12, 1, 5);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (13, 1, 24);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (14, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (15, 1, 2);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (16, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (17, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (18, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (19, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (20, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (21, 1, 4);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (22, 1, 4);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (23, 1, 12);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (24, 1, 2);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (25, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (27, 1, 1);
INSERT INTO rel_art_et (f_et_Nr, f_art_Nr, rel_art_et_EinzelteileMenge) VALUES (28, 1, 1);
# --------------------------------------------------------

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
) TYPE=MyISAM COMMENT='Relationship: Artikel_Lager (Artikellagerplatz)';

#
# Daten für Tabelle `rel_art_lg`
#

INSERT INTO rel_art_lg VALUES ("C16", 1, 600, 1000);
# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `rel_at_art`
#

CREATE TABLE rel_at_art (
  f_art_Nr int(10) unsigned NOT NULL default '0',
  f_at_Nr int(10) unsigned NOT NULL default '0',
  rel_at_art_Bestellmenge int(10) unsigned NOT NULL default '0',
  FOREIGN KEY (f_art_Nr) REFERENCES art (art_Nr),
  FOREIGN KEY (f_at_Nr) REFERENCES at (at_Nr),
  PRIMARY KEY  (f_art_Nr,f_at_Nr)
) TYPE=MyISAM COMMENT='Relationship: Auftrag_Artikel (Auftragsposition)';

#
# Daten für Tabelle `rel_at_art`
#

INSERT INTO rel_at_art VALUES (1, 1, 10);
INSERT INTO rel_at_art VALUES (1, 2, 20);
INSERT INTO rel_at_art VALUES (1, 3, 30);

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
) TYPE=MyISAM COMMENT='Relationship: Einzelteil_Arbeitsplatz (ArbeitsplatzEinzeltei';

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
) TYPE=MyISAM COMMENT='Relationship: Einzeilteil_Lager (EinzelteilLagerplatz)';

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

#
# Tabellenstruktur für Tabelle `rel_etat_et`
#

CREATE TABLE rel_etat_et (
  f_et_Nr int(11) NOT NULL default '0',
  f_etat_Nr int(10) unsigned NOT NULL default '0',
  rel_etat_et_Bestellmenge int(10) unsigned NOT NULL default '0',
  rel_etat_et_Stueckpreis decimal(12,2) unsigned NOT NULL default '0',
  FOREIGN KEY (f_et_Nr) REFERENCES et (et_Nr),
  FOREIGN KEY (f_etat_Nr) REFERENCES etat (etat_Nr),
  PRIMARY KEY  (f_et_Nr,f_etat_Nr)
) TYPE=MyISAM COMMENT='Relationship: Einzelteilauftrag_Einzelteil (EinzelteilAuftra';

#
# Daten für Tabelle `rel_etat_et`
#

INSERT INTO rel_etat_et VALUES (4, 1, 10000, '0.05');
INSERT INTO rel_etat_et VALUES (5, 1, 10000, '0.05');
INSERT INTO rel_etat_et VALUES (6, 1, 10000, '0.05');
INSERT INTO rel_etat_et VALUES (1, 2, 500, '10.00');
INSERT INTO rel_etat_et VALUES (2, 2, 1000, '12.00');
INSERT INTO rel_etat_et VALUES (7, 3, 10000, '0.05');
INSERT INTO rel_etat_et VALUES (9, 3, 500, '0.50');
INSERT INTO rel_etat_et VALUES (10, 3, 300, '5.00');
INSERT INTO rel_etat_et VALUES (20, 3, 5000, '0.10');
INSERT INTO rel_etat_et VALUES (19, 3, 5000, '0.10');
INSERT INTO rel_etat_et VALUES (25, 3, 10000, '0.01');

# --------------------------------------------------------

#
# Tabellenstruktur für Tabelle `rel_lt_et`
#

CREATE TABLE rel_lt_et (
  f_et_Nr int(11) NOT NULL default '0',
  f_lt_Nr int(11) NOT NULL default '0',
  rel_lt_et_Stueckpreis decimal(12,2) unsigned NOT NULL default '0.00',
  rel_lt_et_Mindestbestellmenge int(10) unsigned NOT NULL default '0',
  FOREIGN KEY (f_et_Nr) REFERENCES et (et_Nr),
  FOREIGN KEY (f_lt_Nr) REFERENCES lt (lt_Nr),
  PRIMARY KEY  (f_et_Nr,f_lt_Nr)
) TYPE=MyISAM COMMENT='Relationship: Lieferant_Einzelteil (Lieferliste)';

#
# Daten für Tabelle `rel_lt_et`
#

INSERT INTO rel_lt_et VALUES (4, 1, '0.05', 10000);
INSERT INTO rel_lt_et VALUES (5, 1, '0.05', 10000);
INSERT INTO rel_lt_et VALUES (6, 1, '0.05', 10000);
INSERT INTO rel_lt_et VALUES (1, 2, '10.00', 500);
INSERT INTO rel_lt_et VALUES (2, 2, '12.00', 1000);
INSERT INTO rel_lt_et VALUES (13, 2, '2.50', 100);
INSERT INTO rel_lt_et VALUES (7, 3, '0.05', 10000);
INSERT INTO rel_lt_et VALUES (9, 3, '0.50', 500);
INSERT INTO rel_lt_et VALUES (10, 3, '5.00', 300);
INSERT INTO rel_lt_et VALUES (20, 3, '0.10', 5000);
INSERT INTO rel_lt_et VALUES (19, 3, '0.10', 5000);
INSERT INTO rel_lt_et VALUES (25, 3, '0.01', 10000);

# --------------------------------------------------------