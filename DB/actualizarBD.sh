#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd $DIR
wget -P $DIR -cO - https://datosabiertos.bogota.gov.co/dataset/44eacdb7-a535-45ed-be03-16dbbea6f6da/resource/b64ba3c4-9e41-41b8-b3fd-2da21d627558/download/osb_enftransm-covid-19.csv > datosBogota_iso.csv
wget -P $DIR -cO - https://www.datos.gov.co/api/views/gt2j-8ykr/rows.csv?accessType=DOWNLOAD > datosColombia.csv
iconv -f ISO-8859-1  -t UTF-8  datosBogota_iso.csv > datosBogota.csv
rm datosBogota_iso.csv
mysql -h 35.199.107.44 -u root -pPassword1234! --local-infile=1 < cargarDatos.sql

