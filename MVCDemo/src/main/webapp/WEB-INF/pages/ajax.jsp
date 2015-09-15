<%-- 
This is a very basic JSP page. Notice that we can include other JSPs (so that we
can use the header and the footer across different pages). In this case, it is
a static include, which means that the header and footers will be included when
the JSP is compiled into a servlet.

The servlet (controller) that received the initial HTTP request and delegated
rendering of the view to this JSP did not send us any model. We only display
static content.
--%>

<%@include file="includes/header.jsp" %>

<script>

  function updateData() {
    updatePeople();
    updateGraph();
  }

  function updatePeople() {
    console.log("Updating people with an AJAX request...");
    $.ajax({
      dataType: "json",
      url: 'data/json',
      success: function (data) {
        $("#people table tbody").html("");
        $.each(data.people, function (index, person) {
          //$("#people ul").append("<li>" + person.firstName + " " + person.lastName + " : " + person.id + "</li>");
          $("#people table tbody").append("<tr><th scope=\"row\">" + person.id + "</th><td>" + person.firstName + "</td><td>" + person.lastName + "</td></tr>");
        });
      }
    });
  }

  function updateGraph() {
    function rc() {
      return Math.round(Math.random() * 255);
    }
    var bg = rc() + ";" + rc() + ";" + rc();
    var fg = rc() + ";" + rc() + ";" + rc();
    $("#graph img").attr("src", "data/graph?bg=" + bg + "&fg=" + fg);
  }

  $(document).ready(function () {
    console.log("Document ready.");
    updateData();
    $("#bUpdatePeople").click(updatePeople);
    $("#bUpdateGraph").click(updateGraph);
  });

</script>
<h2>AJAX demo</h2>

<p>This page shows that servlets can be invoked by adding resources in the HTML
  code (e.g. with IMG tags) and by making AJAX calls.</p>

<div class="panel panel-primary">
  <div class="panel-heading">
    <h2 class="panel-title pull-left">JSON data</h2>
    <span id="bUpdatePeople" class="glyphicon glyphicon-refresh pull-right" style="cursor: pointer;" aria-hidden="true"></span>
    <div class="clearfix"></div>
  </div>
  <div class="panel-body">
    <div id="people">
      <table class="table table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
    </div>
  </div>
</div>

<div class="panel panel-primary">
  <div class="panel-heading">
    <h2 class="panel-title pull-left">Dynamically generated image</h2>
    <span id="bUpdateGraph" class="glyphicon glyphicon-refresh pull-right" style="cursor: pointer;" aria-hidden="true"></span>
    <div class="clearfix"></div>
  </div>
  <div class="panel-body">
    <div id="graph">
      <img src="data/graph?bg=255;255;255&fg=255;0;0">
    </div>
  </div>
</div>



<%@include file="includes/footer.jsp" %>
