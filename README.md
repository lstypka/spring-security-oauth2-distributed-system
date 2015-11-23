# Spring Boot JWT OAuth2

This is a simple springBoot project consists of two RESTful web-services (authentication-service and client1).
Authorisation service generates JWT based session tokens  [https://en.wikipedia.org/wiki/JSON_Web_Token](https://en.wikipedia.org/wiki/JSON_Web_Token)
The second web-service has two endpoints (/client1/time and /client1/user). Both of them are secured, therefore access to them is possible only with session token header 
`curl http://localhost:8075/client1/user -H "Content-Type: application/json" -H "Authorization: Bearer ==HERE_YOUR_TOKEN==`

How to generate keystore and public key
__Generating keystore__

`keytool -genkeypair -alias authentication-server -keyalg RSA -keypass lstypkaSecretPassword -keystore authenticationServer.jks -storepass lstypkaSecretPassword`

__Generating public key__

`keytool -list -rfc --keystore authenticationServer.jks | openssl x509 -inform pem -pubkey`



__Generating session token__

`curl -u client1ResourceId: http://localhost:8091/authentication-server/oauth/token -d "grant_type=password&username=admin&password=s3cr3t"`

Expected response

```
{"access_token":"eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE0NDgzMTY2NzgsInVzZXJfbmFtZSI6Im
FkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJiM2M4Njg0YS0xZjk2LTRiNG
MtYjg0Zi1lZTQxNTQ4ZjBiZTYiLCJjbGllbnRfaWQiOiJjbGllbnQxUmVzb3VyY2VJZCIsInNjb3BlIj
pbInJlYWQiLCJ3cml0ZSIsInRydXN0Il19.BJrspOdDvKigdU_Ri-nHv0w-Cj691UNnvhftb3Yd1eQgY
6Xq5iuqsSw9yBjGqnpTbQg_AFGhGTwoHuyLyvv3KU_BfmnqytgBZaoZckATVbE2FlETnU1Lu0jId9OXh
eUCGAeV1kMGDAxz6RDZvjeVnCOASvNwmuM6P4ow5Y7DsI9fSCARAgsBCHgypvQ4Rsb-eR9o2-ETl3RU-
iwrb6Ha3oY9xRxrN4GuOgfyuvJHxIWahRr3ZbvIMIude-snVUxcihMjtPDhaQov-d5T8ZCtTLqQhgR-N
QJS-_XnFQK6lNmN_UbKsmwWcuoBIv1U4hepMJTx9aA9aJv-lDWAeA9glg","token_type":"bearer"
,"refresh_token":"eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbI
nJlYWQiLCJ3cml0ZSIsInRydXN0Il0sImF0aSI6ImIzYzg2ODRhLTFmOTYtNGI0Yy1iODRmLWVlNDE1N
DhmMGJlNiIsImV4cCI6MTQ1MDkwMjY3OCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiO
iJhYTI0NGE5Yi1mMzlkLTQzZWEtYmRlNS04YWNiNmZiMzM2MzgiLCJjbGllbnRfaWQiOiJjbGllbnQxU
mVzb3VyY2VJZCJ9.ra-P6UlT5YwAxornY9T4szeo85A1wYcrt0yug3dVOVKhxTM6eiskINcpVoYhtA1z
icZuiDatNClYrPXsfiULJzTasEEm_NrmH5E3v1UYNF9rVjMaSUkRU6UpD4TZxif4uCdIZaxNr7wt_lLU
JV1i4Wauf3vG2ZwtkbGLRoUthY-BBXo4lLTSV4cpCtIWY8HTB0Z21bEgecJA0rOKUGES2M0lNl5gVP4b
KjhwNF5uGuUF8oMi5aKgd2gAgCBWDDCPZbaBMgpS9rM0BmmiRdLglZTCgdz26ksdW8fBdlgxGUYPqYK0
vB1UJ0qqJXWI3UdbQlrFqbPz9AoTiZYjaUoZCw","expires_in":5999,"scope":"read write tr
ust","jti":"b3c8684a-1f96-4b4c-b84f-ee41548f0be6"}
```

Copy value of "access_token" and invoke client1 endpoint (http://localhost:8075/client1/time)

```
curl http://localhost:8075/client1/time -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE0NDgzMTY2NzgsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJiM2M4Njg0YS0xZjk2LTRiNGMtYjg0Zi1lZTQxNTQ4ZjBiZTYiLCJjbGllbnRfaWQiOiJjbGllbnQxUmVzb3VyY2VJZCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il19.BJrspOdDvKigdU_Ri-nHv0w-Cj691UNnvhftb3Yd1eQgY6Xq5iuqsSw9yBjGqnpTbQg_AFGhGTwoHuyLyvv3KU_BfmnqytgBZaoZckATVbE2FlETnU1Lu0jId9OXheUCGAeV1kMGDAxz6RDZvjeVnCOASvNwmuM6P4ow5Y7DsI9fSCARAgsBCHgypvQ4Rsb-eR9o2-ETl3RU-iwrb6Ha3oY9xRxrN4GuOgfyuvJHxIWahRr3ZbvIMIude-snVUxcihMjtPDhaQov-d5T8ZCtTLqQhgR-NQJS-_XnFQK6lNmN_UbKsmwWcuoBIv1U4hepMJTx9aA9aJv-lDWAeA9glg"
```

Expected result:
`2015-11-23T22:49:29.224`


Get details of logged user:

```
curl http://localhost:8075/client1/user -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJleHAiOjE0NDgzMTY2NzgsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJiM2M4Njg0YS0xZjk2LTRiNGMtYjg0Zi1lZTQxNTQ4ZjBiZTYiLCJjbGllbnRfaWQiOiJjbGllbnQxUmVzb3VyY2VJZCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il19.BJrspOdDvKigdU_Ri-nHv0w-Cj691UNnvhftb3Yd1eQgY6Xq5iuqsSw9yBjGqnpTbQg_AFGhGTwoHuyLyvv3KU_BfmnqytgBZaoZckATVbE2FlETnU1Lu0jId9OXheUCGAeV1kMGDAxz6RDZvjeVnCOASvNwmuM6P4ow5Y7DsI9fSCARAgsBCHgypvQ4Rsb-eR9o2-ETl3RU-iwrb6Ha3oY9xRxrN4GuOgfyuvJHxIWahRr3ZbvIMIude-snVUxcihMjtPDhaQov-d5T8ZCtTLqQhgR-NQJS-_XnFQK6lNmN_UbKsmwWcuoBIv1U4hepMJTx9aA9aJv-lDWAeA9glg" 
```

Expected result:

```
{"details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":null,"tokenValue":"eyJ
hbGciOiJSUzI1NiJ9.eyJleHAiOjE0NDgzMTY2NzgsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaX
RpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJiM2M4Njg0YS0xZjk2LTRiNGMtYjg0Zi1lZTQxNTQ4Zj
BiZTYiLCJjbGllbnRfaWQiOiJjbGllbnQxUmVzb3VyY2VJZCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZS
IsInRydXN0Il19.BJrspOdDvKigdU_Ri-nHv0w-Cj691UNnvhftb3Yd1eQgY6Xq5iuqsSw9yBjGqnpTb
Qg_AFGhGTwoHuyLyvv3KU_BfmnqytgBZaoZckATVbE2FlETnU1Lu0jId9OXheUCGAeV1kMGDAxz6RDZv
jeVnCOASvNwmuM6P4ow5Y7DsI9fSCARAgsBCHgypvQ4Rsb-eR9o2-ETl3RU-iwrb6Ha3oY9xRxrN4GuO
gfyuvJHxIWahRr3ZbvIMIude-snVUxcihMjtPDhaQov-d5T8ZCtTLqQhgR-NQJS-_XnFQK6lNmN_UbKs
mwWcuoBIv1U4hepMJTx9aA9aJv-lDWAeA9glg","tokenType":"Bearer","decodedDetails":nul
l},"authorities":[{"authority":"ROLE_ADMIN"}],"authenticated":true,"userAuthenti
cation":{"details":null,"authorities":[{"authority":"ROLE_ADMIN"}],"authenticate
d":true,"principal":"admin","credentials":"N/A","name":"admin"},"principal":"adm
in","credentials":"","oauth2Request":{"clientId":"client1ResourceId","scope":["r
ead","write","trust"],"requestParameters":{"client_id":"client1ResourceId"},"res
ourceIds":[],"authorities":[],"approved":true,"refresh":false,"redirectUri":null
,"responseTypes":[],"extensions":{},"refreshTokenRequest":null,"grantType":null}
,"clientOnly":false,"name":"admin"}
```
