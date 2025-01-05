
// don't use 'local' for db name ( it's reserved for internal use)

//===========
db = db.getSiblingDB("insta-cred");

db.createUser({
  user: "mongo",
  pwd: "mongo",
  roles: [{ role: "readWrite", db: "insta-cred" }],
});

// we can do anything here


//=========