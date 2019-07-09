Generate server client keys
1
keytool -genkey -alias server -keyalg RSA -keystore server.jks -keysize 2048
keytool -genkey -alias client -keyalg RSA -keystore client.jks -keysize 2048
----------------
or
keytool -genkeypair -alias server -keyalg RSA -dname "CN=server,OU=Dev team,O=esempla,L=Chisinau,S=Moldova,C=MD" -keypass 123456 -keystore server.jks -storepass 123456
keytool -genkeypair -alias client -keyalg RSA -dname "CN=client,OU=Dev team,O=esempla,L=Chisinau,S=Moldova,C=MD" -keypass 123456 -keystore client.jks -storepass 123456

2
keytool -exportcert -alias client -file client.cer -keystore client.jks
keytool -exportcert -alias server -file server.cer -keystore server.jks 
-------------------
or
keytool -exportcert -alias client -file client.cer -keystore client.jks -storepass 123456
keytool -exportcert -alias server -file server.cer -keystore server.jks -storepass 123456

3
keytool -importcert -keystore server.jks -alias client -file client.cer 
keytool -importcert -keystore client.jks -alias server -file server.cer
------------------
or
keytool -importcert -keystore server.jks -alias client -file client.cer -storepass 123456 -noprompt
keytool -importcert -keystore client.jks -alias server -file server.cer -storepass 123456 -noprompt


from Garaba

####Generare certificat server
keytool -genkey -alias server -keyalg RSA -keystore server.jks -keysize 2048

####Generare certificat client
keytool -genkey -alias client -keyalg RSA -keystore client.jks -keysize 2048

####Extragere cheie publica server
keytool -export -alias server -keystore server.jks -file server.cer

####Extragere cheie publica client
keytool -export -alias client -keystore client.jks -file client.cer

####Importare cheie publica client in keystore 'server'
keytool -import -alias client -file client.cer -keystore server.jks

####Importare cheie publica server in keystore 'client'
keytool -import -alias server -file server.cer -keystore client.jks

####Vizualizare keystore 'server'
keytool --list -keystore server.jks

####Vizualizare keystore 'client'
keytool --list -keystore client.jks

