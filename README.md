# Hingucker

Researching tool to gather information about a web resource.

## Args

* `--target` domain to *hinguck* eg. `domain.com`

## ENV

* `NAMELIST_FILEPATH` path to dictionary file
* `OUTPUT_FILEPATH` path to output file

## Namelist File

Dictionary file with one name per line. Currently, only used for the subdomain gatherer.

## Modules

### Subdomain Gatherer

Searching subdomains for given domain by namelist file. Working recursively.

### HTTP Probe

Sending HTTP request to every found domain (incl. `--target`). 

Supported Verbs
* `GET`
* `POST`
* `PUT`
* `DELETE`
* `HEAD`
* `OPTION`


