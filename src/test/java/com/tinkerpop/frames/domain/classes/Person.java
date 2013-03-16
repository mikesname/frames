package com.tinkerpop.frames.domain.classes;

import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Incidence;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy;
import com.tinkerpop.frames.annotations.gremlin.GremlinParam;
import com.tinkerpop.frames.domain.incidences.Created;
import com.tinkerpop.frames.domain.incidences.Knows;

import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public interface Person extends NamedObject {

    @Property("age")
    public Integer getAge();

    @Property("age")
    public void setAge(Integer age);

    @Property("age")
    public void removeAge();

    @Incidence(label = "knows")
    public Iterable<Knows> getKnows();

    @Adjacency(label = "knows")
    public Iterable<Person> getKnowsPeople();

    @Adjacency(label = "knows")
    public void setKnowsPeople(final Iterable<Person> knows);

    @Incidence(label = "created")
    public Iterable<Created> getCreated();

    @Adjacency(label = "created")
    public Iterable<Project> getCreatedProjects();

    @Adjacency(label = "knows")
    public void addKnowsPerson(final Person person);
    
    @Adjacency(label = "knows")
    public Person addKnowsNewPerson();

    @Incidence(label = "knows")
    public Knows addKnows(final Person person);

    @Adjacency(label = "created")
    public void addCreatedProject(final Project project);

    @Incidence(label = "created")
    public Created addCreated(final Project project);

    @Adjacency(label = "knows")
    public void removeKnowsPerson(final Person person);

    @Incidence(label = "knows")
    public void removeKnows(final Knows knows);

    @Adjacency(label = "latestProject")
    public Project getLatestProject();

    @Adjacency(label = "latestProject")
    public void setLatestProject(final Project latestProject);

    @GremlinGroovy("it.as('x').out('created').in('created').except('x')")
    public Iterable<Person> getCoCreators();

    @GremlinGroovy("_().as('x').out('created').in('created').except('x').shuffle")
    public Person getRandomCoCreators();

    @GremlinGroovy("_().as('x').out('created').in('created').except('x').has('age',age)")
    public Person getCoCreatorOfAge(@GremlinParam("age") int age);

    @GremlinGroovy(value = "'aStringProperty'", frame = false)
    public String getAStringProperty();

    @GremlinGroovy(value = "['a','b','c']", frame = false)
    public Iterable<String> getListOfStrings();

    @GremlinGroovy("it.as('x').out('created').in('created').except('x').groupCount.cap.next()")
    public Map<Person, Long> getRankedCoauthors();

    @Property("boolean")
    public void setBoolean(boolean b);

    @Property("boolean")
    public boolean isBooleanPrimitive();

    @Property("boolean")
    public Boolean isBoolean();

    @Property("boolean")
    public boolean canBooleanPrimitive();

    @Property("boolean")
    public Boolean canBoolean();
}
