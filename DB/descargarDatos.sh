#!/bin/bash
wget -P $1 -cO - https://datosabiertos.bogota.gov.co/dataset/44eacdb7-a535-45ed-be03-16dbbea6f6da/resource/b64ba3c4-9e41-41b8-b3fd-2da21d627558/download/osb_enftransm-covid-19.csv > datosBogota_iso.csv
iconv -f ISO-8859-1 -t UTF-8 datosBogota_iso.csv > datosBogota.csv
rm datosBogota_iso.csv
wget -P $1 -cO - https://www.datos.gov.co/api/views/gt2j-8ykr/rows.csv?accessType=DOWNLOAD > datosColombia.csv