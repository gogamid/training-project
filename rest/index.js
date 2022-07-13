var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql2');
const { query } = require('express');
const port = 3000;
app.use(express.json());

require('dotenv').config()

app.use(bodyParser.json());

app.use(bodyParser.urlencoded({
  extended: true
}));


app.use(express.json());
app.use(
  express.urlencoded({
    extended: true,
  })
);


app.get("/", async (req, res) => {
  res.json({ status: "Rest Api is ready to roll!" });
});

// connection configurations
var dbConn = mysql.createConnection({
  host: process.env.HOSTNAME,
  user: process.env.USERNAME,
  password: process.env.PASSWORD,
  database: process.env.DB_NAME
});

dbConn.connect(function (err) {
  if (err) {
    console.error('error connecting: ' + err.stack);
    return;
  }

  console.log('connected as id ' + dbConn.threadId);
});


app.get("/users", async (req, res) => {
  const query = "SELECT distinct users.mailadresse, role.UserRole FROM users natural join role;";
  dbConn.query(query, (err, results) => {
    if (!results) {
      res.json({ status: "Not found!" });
    } else {
      res.json(results);
    }
  });
});

app.get("/vehicles", async (req, res) => {
  const query = "SELECT distinct users.mailadresse,vehicles.Vehicle from uservehicles natural join users natural join vehicles";
  dbConn.query(query, (err, results) => {
    if (!results) {
      res.json({ status: "Not found!" });
    } else {
      res.json(results);
    }
  });
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});