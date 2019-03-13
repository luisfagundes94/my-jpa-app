# Aplicação JPA First APP

## Classes


### Application

```
package com.tbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


```

### Person

```
package com.tbp.model;

import javax.persistence.*;

@Entity
@Table(name = "pessoa")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nome")
    String name;
    @Column(name = "idade")
    Integer age;
    @ManyToOne
    @JoinColumn(name = "id_passatempo")
    Hobby favoriteHobby;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Hobby getFavoriteHobby() {
        return favoriteHobby;
    }

    public void setFavoriteHobby(Hobby favoriteHobby) {
        this.favoriteHobby = favoriteHobby;
    }
}


```
### PersonRepository

```
package com.tbp.repository;

import com.tbp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}

```

### Hobby

```

package com.tbp.model;


import javax.persistence.*;

@Entity
@Table(name = "passatempo")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nome")
    String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


```

### HobbyRepository

```

package com.tbp.repository;

import com.tbp.model.Hobby;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends CrudRepository<Hobby, Long> {

}


```

### PersonController

```

package com.tbp.controller;

import com.tbp.model.Hobby;
import com.tbp.model.User;
import com.tbp.repository.HobbyRepository;
import com.tbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    HobbyRepository hobbyRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Person index() {
        Person person = new Person();
        person.setName("John");
        person.setAge(12);
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }

    @RequestMapping(value = "/person/all", method = RequestMethod.GET)
    public Iterable<Person>  findAll() {
        Iterable<Person> all = personRepository.findAll();
        return all;
    }

    @RequestMapping(value = "/person/save", method = RequestMethod.GET)
    public Person save(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        personRepository.save(person);
        return person;
    }

    @RequestMapping(value = "/person/update", method = RequestMethod.GET)
    public Person update(@RequestParam("name") String name, @RequestParam("age") Integer age, @RequestParam("id") Long id) {
        Person person = personRepository.findOne(id);
        person.setName(name);
        person.setAge(age);
        personRepository.save(person);
        return person;
    }

    @RequestMapping(value = "/person/remove", method = RequestMethod.GET)
    public String remove(@RequestParam("id") Long id) {
        personRepository.delete(id);
        return "Person with id = " + id + " removed";
    }

    @RequestMapping(value = "/person/addHobby", method = RequestMethod.GET)
    public Person addHobby(@RequestParam("idPerson") Long idPerson, @RequestParam("idHobby") Long idHobby) {
        Hobby hobby = hobbyRepository.findOne(idHobby);
        Person person = personRepository.findOne(idPerson);
        person.setFavoriteHobby(hobby);
        return personRepository.save(person);
    }


}



```

### HobbyController

```

package com.tbp.controller;

import com.tbp.model.Hobby;
import com.tbp.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HobbyController {

    @Autowired
    HobbyRepository hobbyRepository;

    @RequestMapping(value = "/hobby/all", method = RequestMethod.GET)
    public Iterable<Hobby> findAll() {
        return hobbyRepository.findAll();
    }

    @RequestMapping(value = "/hobby/save", method = RequestMethod.GET)
    public Hobby save(@RequestParam("name") String name) {
        Hobby hobby = new Hobby();
        hobby.setName(name);
        Hobby savedHobby = hobbyRepository.save(hobby);
        return savedHobby;
    }

    @RequestMapping(value = "/hobby/update", method = RequestMethod.GET)
    public Hobby update(@RequestParam("name") String name, @RequestParam("id") Long id) {
        Hobby hobby = hobbyRepository.findOne(id);
        hobby.setName(name);
        Hobby savedHobby = hobbyRepository.save(hobby);
        return savedHobby;

    }


}



```

### application.properties

```
# H2 console - http://localhost:8080/jpa-app/h2
spring.h2.console.enabled=true
spring.h2.console.path=/h2


#Configuracoes do banco de dados

# url de conexao com o banco de dados
spring.datasource.url=jdbc:h2:file:~/test;DB_CLOSE_ON_EXIT=FALSE

# nome do usuario do banco
spring.datasource.username=sa

# senha do banco de dados
spring.datasource.password=

# nome da classe Java resposavel por conectar no banco de dados
spring.datasource.driver-class-name=org.h2.Driver

# propriedade que diz para a aplicacao criar as tabelas no banco de dados.
# Tudo que estiver com @Entity vai se transformar em uma tabela
spring.jpa.hibernate.ddl-auto=update

#contexto da aplicacao
server.context-path=/jpa-app

```
