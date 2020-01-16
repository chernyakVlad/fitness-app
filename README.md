# fitness-app

* install mongodb
[Install MongoDB Community Edition on Windows] (https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/)

* Start Mongo Server
`mongod.exe --dbpath c:\bin\mongodb\data`

* Start Mongo Admin Console
`mongodb.exe`

* Clear previous data
`use fgtastdb`
`db.dropDatabase();`

* Create initial data
`use fgtastdb`
`db.role.insert([{"_id" : "1", "role" : "ADMIN"}, {"_id" : "2", "role" : "USER"}])`
`db.user.insert({"_id": "1", "login": "admin", "password": "$2a$10$t6zQgTSVmroVyyXssSZ8We5VKEjIgV6Opo.gfpR0U2vRHxzKIY0kO"})`
`db.user.insert({"_id": "2", "login": "default", "password": "$2a$10$t6zQgTSVmroVyyXssSZ8We5VKEjIgV6Opo.gfpR0U2vRHxzKIY0kO"})`
`db.user.update({ "_id" : "1" }, { $set: { role : {"$ref": "role", "$id" : "1"} }} )`
`db.user.update({ "_id" : "2" }, { $set: { role : {"$ref": "role", "$id" : "2"} }} )`
