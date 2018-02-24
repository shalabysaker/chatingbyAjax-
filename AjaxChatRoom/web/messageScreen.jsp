<%-- 
    Document   : messageScreen
    Created on : Feb 23, 2018, 12:56:21 AM
    Author     : shalaby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>message Screen</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <style>

            html,body {
                    width: 100%;
                    height: 100%;
                    padding: 0;
                    margin: 0;       
            }
                   h1, body {
                    background: -webkit-linear-gradient(45deg, #49a09d, #5f2c82);
                    background: linear-gradient(45deg, #49a09d, #5f2c82);
                    
                    font-weight: 100;
                    }
                    .container {
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    -webkit-transform: translate(-50%, -50%);
                    transform: translate(-50%, -50%);
                    }
                    table {
                    margin-top: 20px;
                    width: 500px;
                    border-collapse: collapse;
                    overflow: hidden;
                    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                    }
                    th,
                    td {
                    padding: 15px;
                    background-color: rgba(255, 255, 255, 0.2);
                    
                    }
                    th {
                    text-align: left;
                    }
                    thead th {
                    
                    }
                    tbody tr:hover {
                    background-color: rgba(255, 255, 255, 0.3);
                    }
                    tbody td {
                    position: relative;
                    }
                    tbody td:hover:before {
                    content: "";
                    position: absolute;
                    left: 0;
                    right: 0;
                    top: -9999px;
                    bottom: -9999px;
                    background-color: rgba(255, 255, 255, 0.2);
                    z-index: -1;
                    }
        </style>
       
    </head>
    <body>
        <div>
        <h1>${sessionScope.user.getUserName()} : messages</h1>
        <input type="text" id="name" value="${sessionScope.user.getUserName()}" disabled="true" />
        <input type="text" id="msg" value="" />
        <input type="button" value="send" id="send" />
        </div>
        <script>
            $(document).ready(function(){
                     $("#send").click(function(){ 
                      var jsonData = {"name" :$("#name").val(),"msg":$("#msg").val()};
                      $.ajax({url:'messageServlet',type:'POST',data: jsonData ,success: function () {
                  }});
               });
           });
        </script>

        
        <div>
            <table id="mytable">
                <tr>
                    <th>user</th>
                    
                    <th>message</th>
                    
                </tr>
                
            </table>
            
            
        </div>
        <script>
            setInterval(function (){
                
                $.get("messageServlet",function(data , status){
                     if (status==="success")
                     {
                         var myObject = JSON.parse(data);
                          
                          if (document.getElementById("mytable").rows.length===1)
                          {
                              myObject.forEach(function (index){
                              
                                   var html = "<tr><td>"+index.name+"</td><td>"+index.msg+"</td></tr>";
                               document.getElementById("mytable").innerHTML+=html;
                                  
                              });
                              
                          }
            
                         if(myObject.length>document.getElementById("mytable").rows.length-1 )
                             
                           {        
                              var index =  myObject[myObject.length-1];
                             //  myObject.forEach(function(index){
                               var html = "<tr><td>"+index.name+"</td><td>"+index.msg+"</td></tr>";
                               document.getElementById("mytable").innerHTML+=html;
                         // });
                           }
                     }
                 });
                 
            },4000);
          
            </script>
        
    </body>
</html>
