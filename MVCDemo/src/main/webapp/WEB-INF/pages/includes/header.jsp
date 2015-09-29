<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="${pageContext.request.contextPath}/">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${pageTitle}</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <script src="static/js/jquery.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>

  </head>
  <body>
    <div class="container">
      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">MVC Demo</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="pages/about">About</a></li>
              <li class="dropdown">
                <a id="menuExamples" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Examples <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a id="menuItemBeers" href="pages/beers">Beers</a></li>
                  <li><a id="menuItemUglyServlet" href="pages/ugly">Ugly servlet</a></li>
                  <li><a id="menuItemJsonServlet" href="data/json">Json servlet</a></li>
                  <li><a id="menuItemGraphServlet" href="data/graph">Graph servlet</a></li>
                  <li><a id="menuItemAJAXPage" href="pages/ajax">AJAX page</a></li>
                  <li><a id="menuItemConcurrency" href="pages/concurrency">Concurrency</a></li>
                  <li role="separator" class="divider"></li>
                  <li><a id="menuItemGenerateTestData" href="generateTestData">Generate test data</a></li>
                  <li><a id="menuItemShowCorporateInformation" href="pages/corporateInformation">Show corporate information</a></li>
                  <!--
                  <li class="dropdown-header">Nav header</li>
                  -->
                </ul>
              </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="./auth?action=logout">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>