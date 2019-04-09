# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM tomcat:9.0.17-jre8

COPY target/RESTServiceInHeroku.war ${CATALINA_HOME}/webapps

CMD ["catalina.sh", "run"]