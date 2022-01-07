const express = require('express');
const app = express();
var mysql = require('mysql');
const port = 3000;
var url = require('url');
const util = require("util");
const bodyParser = require('body-parser');
const { exec } = require('child_process');

app.use(bodyParser.urlencoded({ extended: true }));



var val = "fa";

app.get('/', function(req, res) {
const url = req.url;

  res.setHeader('Content-Type', 'text/html');
	
var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "Legend27#",
  database: "chessboard"
});
  con.connect(function(err) {
  if (err) throw err;
  con.query("SELECT * FROM board", function (err, result, fields) {
      if (err) throw err;
   
      
   res.write("<!DOCTYPE html>");
   res.write("<html>");
   res.write("<head>");
   res.write("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>");
   res.write("</head>");
   res.write("<body>");
   res.write("<table align='center' id='tblMain' border='1' style='cursor: pointer;'>");
  
      for(var i = 8;i>=0;i--){
   res.write("<tr>");
   
 res.write("<td style='height:40px;width:40px'>"+result[i]['O']+"</th>");
 res.write("<td style='height:40px;width:40px'>"+result[i]['a']+"</th>");
 res.write("<td style='height:40px;width:40px'>"+result[i]['b']+"</th>");
 res.write("<td style='height:40px;width:40px'>"+result[i]['c']+"</th>");
 res.write("<td style='height:40px;width:40px'>"+result[i]['d']+"</th>");
 res.write("<td style='height:40px;width:40px'>"+result[i]['e']+"</th>");
 res.write("<td style='height:40px;width:40px'>"+result[i]['f']+"</th>");
 res.write("<td style='height:40px;width:40px'>"+result[i]['g']+"</th>");
 res.write("<td style='height:40px;width:40px'>"+result[i]['h']+"</th>");
 res.write("</tr>");
	  }
 res.write("</table>");
res.write("<script>");
res.write("var iter = 0;");
res.write("x1 = 0;");
res.write("y1 = 0;");
res.write("x2 = 0;");
res.write("y2 = 0;");
 res.write("$('table').find('td').click(function(){");
 res.write("var col = $(this).parent().children().index($(this));");
  res.write("  var row = Math.abs($(this).parent().parent().children().index($(this).parent())-8);");
   res.write("  console.log('Row: ' + row + ', Column: ' + col); ");
   res.write("if(iter == 0){");
   res.write("x1 = col;");
   res.write("y1 = row;");
   res.write("iter++;");
   res.write("} else{");
   res.write("x2 = col;");
   res.write("y2 = row;");
   res.write("iter=0;");
   res.write("alert(x1+''+y1+''+''+x2+''+y2);");
      res.write("$.post('/', { key1: x1, key2: y1, key3:x2, key4:y2 }, function (result) {");
      res.write("alert('successfully posted key1=value1&key2=value2 to foo.php');");
      res.write("});");
 res.write("}});");
   
   
      res.write("</script>");
      res.write("</body>");
 res.write("</html>");
 

  });
});







});

app.post('/', function requestHandler(req, res) {
    
    console.log("qfesd");
    var x1 = req.body.key1;
    var y1 = req.body.key2;
    var x2 = req.body.key3;
    var y2 = req.body.key4;
    var  moves= '(' + x1 + ',' + y1 + ',' + x2 + ',' + y2 + ')';
    console.log(req.body.key1 + req.body.key2 + req.body.key3 + req.body.key4);


    var con = mysql.createConnection({
        host: "localhost",
        user: "root",
        password: "Legend27#",
        database: "chessboard"
    });
    con.connect(function (err) {
        if (err) throw err;
        con.query("DELETE FROM moves", function (err, result, fields) {
            con.query("INSERT INTO moves (x1,y1,x2,y2) VALUES "+moves);
            if (err) throw err;

            console.log(result);
        });
    });


    /*
    exec('javac Play.java', (error, stdout, stderr) => {
        if (error) {
            console.error(`error: ${error.message}`);
            return;
        }

        if (stderr) {
            console.error(`stderr: ${stderr}`);
            return;
        }

        console.log(`stdout:n${stdout}`);
    });

 

    exec('javac boardConn.java', (error, stdout, stderr) => {
        if (error) {
            console.error(`error: ${error.message}`);
            return;
        }

        if (stderr) {
            console.error(`stderr: ${stderr}`);
            return;
        }

        console.log(`stdout:n${stdout}`);
    });

    exec('java boardConn.java', (error, stdout, stderr) => {
        if (error) {
            console.error(`error: ${error.message}`);
            return;
        }

        if (stderr) {
            console.error(`stderr: ${stderr}`);
            return;
        }

        console.log(`stdout:n${stdout}`);
    });
    */

        con.query("SELECT * FROM board", function (err, result, fields) {
            if (err) throw err;


            res.write("<!DOCTYPE html>");
            res.write("<html>");
            res.write("<head>");
            res.write("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>");
            res.write("</head>");
            res.write("<body>");
            res.write("<table align='center' id='tblMain' border='1' style='cursor: pointer;'>");

            for (var i = 8; i >= 0; i--) {
                res.write("<tr>");

                res.write("<td style='height:40px;width:40px'>" + result[i]['O'] + "</th>");
                res.write("<td style='height:40px;width:40px'>" + result[i]['a'] + "</th>");
                res.write("<td style='height:40px;width:40px'>" + result[i]['b'] + "</th>");
                res.write("<td style='height:40px;width:40px'>" + result[i]['c'] + "</th>");
                res.write("<td style='height:40px;width:40px'>" + result[i]['d'] + "</th>");
                res.write("<td style='height:40px;width:40px'>" + result[i]['e'] + "</th>");
                res.write("<td style='height:40px;width:40px'>" + result[i]['f'] + "</th>");
                res.write("<td style='height:40px;width:40px'>" + result[i]['g'] + "</th>");
                res.write("<td style='height:40px;width:40px'>" + result[i]['h'] + "</th>");
                res.write("</tr>");
            }
            res.write("</table>");
            res.write("<script>");
            res.write("var iter = 0;");
            res.write("x1 = 0;");
            res.write("y1 = 0;");
            res.write("x2 = 0;");
            res.write("y2 = 0;");
            res.write("$('table').find('td').click(function(){");
            res.write("var col = $(this).parent().children().index($(this));");
            res.write("  var row = Math.abs($(this).parent().parent().children().index($(this).parent())-8);");
            res.write("  console.log('Row: ' + row + ', Column: ' + col); ");
            res.write("if(iter == 0){");
            res.write("x1 = col;");
            res.write("y1 = row;");
            res.write("iter++;");
            res.write("} else{");
            res.write("x2 = col;");
            res.write("y2 = row;");
            res.write("iter=0;");
            res.write("alert(x1+''+y1+''+''+x2+''+y2);");
            res.write("$.post('/', { key1: x1, key2: y1, key3:x2, key4:y2 }, function (result) {");
            res.write("alert('successfully posted key1=value1&key2=value2 to foo.php');");
            res.write("});");
            res.write("}});");


            res.write("</script>");
            res.write("</body>");
            res.write("</html>");

           
       
    });

});

app.listen(port, function() {
  console.log(`Example app listening on port ${port}!`)
});