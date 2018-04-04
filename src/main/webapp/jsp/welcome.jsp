 <%@ include file="top.jsp"%> 

<div class="container">
  <h2>${message}</h2>
  <h3>Number of rows: ${rows} Number of seats: ${seats}</h3>

  <p></p>
  

  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
<!--       <li data-target="#myCarousel" data-slide-to="2"></li> -->
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">

      <div class="item active">
        <img src="image-slider/slika_8.jpg" alt="Ticket Booking" style="width:100%;">
        <div class="carousel-caption">
          <h3>Cinema</h3>
          <p>Cinema is always so much fun!</p>
        </div>
      </div>

      <div class="item">
        <img src="image-slider/slika_7.jpg" alt="Chicago" style="width:100%;">
        <div class="carousel-caption">
          <h3>Online booking</h3>
          <p>Just do it</p>
        </div>
      </div>
    
<!--       <div class="item"> -->
<!--         <img src="ny.jpg" alt="New York" style="width:100%;"> -->
<!--         <div class="carousel-caption"> -->
<!--           <h3>New York</h3> -->
<!--           <p>We love the Big Apple!</p> -->
<!--         </div> -->
<!--       </div> -->
  
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  
    <h3>Version: ${version}</h3>
</div>

</body>
</html>


