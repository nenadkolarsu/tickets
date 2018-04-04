# Tickets
 reservation

description of the application

The module is a combination of Spring Boot 2.0.0, Spring-web 5.0.4, Tomcat 8.5.28 Spring MVC, JPA, MySQL 5.7.11, Maven, Eclipse (Oxygen), Java 8, Bootstrap CSS.
Objects relations are defined in the model layer.

Cinemas OneToMany -> Theatres,
Theatres ManyToOne -> Cinemas,
Theatres OneToMany - > Projections,
Projections ManyToOne -> Theatres,
Projections ManyToOne -> Movies,
Projections OneToMany -> Reservations,
Reservations ManyToOne -> Projections.

Validations based on Hibernate and defined in the model layer.
Repositories used to provide basic CRUD functionality for all entities. 
View layer is defined as JSP or HTML pages that using AngularJS. 
All forms contain pagination, instant search, print preview of basic 
table’s content and multi-column ordering.

brief preview available at 
https://youtu.be/XfEAh9y3M4U 
